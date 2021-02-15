package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.enums.CardKey;
import com.appboy.events.ContentCardsUpdatedEvent;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dg implements dm<ContentCardsUpdatedEvent> {
    private static final String a = AppboyLogger.getAppboyLogTag(dg.class);
    private static final Set<String> b;
    private final SharedPreferences c;
    private final SharedPreferences d;
    private final c e = new a();
    private final String f;
    private bl g;

    static {
        HashSet hashSet = new HashSet();
        b = hashSet;
        hashSet.add(CardKey.VIEWED.getContentCardsKey());
        b.add(CardKey.DISMISSED.getContentCardsKey());
    }

    public dg(Context context, String str, String str2) {
        this.f = str;
        String cacheFileSuffix = StringUtils.getCacheFileSuffix(context, str, str2);
        this.d = context.getSharedPreferences("com.appboy.storage.content_cards_storage_provider.metadata" + cacheFileSuffix, 0);
        this.c = context.getSharedPreferences("com.appboy.storage.content_cards_storage_provider.cards" + cacheFileSuffix, 0);
    }

    public void a(bl blVar) {
        this.g = blVar;
    }

    public void a(String str) {
        a(str, CardKey.READ, true);
    }

    public void b(String str) {
        a(str, CardKey.VIEWED, true);
    }

    public void c(String str) {
        a(str, CardKey.CLICKED, true);
    }

    public void d(String str) {
        f(str);
        a(str, (JSONObject) null);
    }

    public ContentCardsUpdatedEvent a(cm cmVar, String str) {
        if (str == null) {
            AppboyLogger.d(a, "Input user id was null. Defaulting to the empty user id");
            str = "";
        }
        if (!this.f.equals(str)) {
            String str2 = a;
            AppboyLogger.i(str2, "The received cards are for user " + str + " and the current user is " + this.f + " , the cards will be discarded and no changes will be made.");
            return null;
        }
        String str3 = a;
        AppboyLogger.i(str3, "Updating offline Content Cards for user with id: " + str);
        a(cmVar);
        e();
        HashSet hashSet = new HashSet();
        JSONArray d2 = cmVar.d();
        if (!(d2 == null || d2.length() == 0)) {
            Set<String> d3 = d();
            for (int i = 0; i < d2.length(); i++) {
                JSONObject jSONObject = d2.getJSONObject(i);
                String string = jSONObject.getString(CardKey.ID.getContentCardsKey());
                JSONObject e2 = e(string);
                if (a(e2, jSONObject)) {
                    AppboyLogger.i(a, "The server card received is older than the cached card. Discarding the server card.");
                    String str4 = a;
                    AppboyLogger.d(str4, "Server card json: " + jSONObject.toString());
                    String str5 = a;
                    AppboyLogger.d(str5, "Cached card json: " + jSONObject.toString());
                } else {
                    hashSet.add(string);
                    if (jSONObject.has(CardKey.REMOVED.getContentCardsKey()) && jSONObject.getBoolean(CardKey.REMOVED.getContentCardsKey())) {
                        String str6 = a;
                        AppboyLogger.d(str6, "Server card is marked as removed. Removing from card storage with id: " + string);
                        g(string);
                        a(string, (JSONObject) null);
                    } else if (d3.contains(string)) {
                        String str7 = a;
                        AppboyLogger.d(str7, "Server card was locally dismissed already. Not adding card to storage. Server card: " + jSONObject);
                    } else if (!jSONObject.has(CardKey.DISMISSED.getContentCardsKey()) || !jSONObject.getBoolean(CardKey.DISMISSED.getContentCardsKey())) {
                        a(string, b(e2, jSONObject));
                    } else {
                        String str8 = a;
                        AppboyLogger.d(str8, "Server card is marked as dismissed. Adding to dismissed cached and removing from card storage with id: " + string);
                        f(string);
                        a(string, (JSONObject) null);
                    }
                }
            }
        }
        if (cmVar.c()) {
            a((Set<String>) hashSet);
            b((Set<String>) hashSet);
        }
        return a(false);
    }

    public ContentCardsUpdatedEvent a() {
        return a(true);
    }

    /* access modifiers changed from: package-private */
    public ContentCardsUpdatedEvent a(boolean z) {
        CardKey.Provider provider = new CardKey.Provider(true);
        Map<String, ?> all = this.c.getAll();
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = all.values().iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        List<Card> a2 = by.a(jSONArray, provider, this.g, (dm) this, this.e);
        Iterator<Card> it2 = a2.iterator();
        while (it2.hasNext()) {
            Card next = it2.next();
            if (next.isExpired()) {
                String str = a;
                AppboyLogger.d(str, "Deleting expired card from storage with id: " + next.getId());
                a(next.getId(), (JSONObject) null);
                it2.remove();
            }
        }
        return new ContentCardsUpdatedEvent(a2, this.f, f(), z);
    }

    public long b() {
        return this.d.getLong("last_card_updated_at", 0);
    }

    public long c() {
        return this.d.getLong("last_full_sync_at", 0);
    }

    private void e() {
        SharedPreferences.Editor edit = this.d.edit();
        edit.putLong("last_storage_update_timestamp", du.a());
        edit.apply();
    }

    private long f() {
        return this.d.getLong("last_storage_update_timestamp", 0);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, CardKey cardKey, boolean z) {
        JSONObject e2 = e(str);
        if (e2 == null) {
            String str2 = a;
            AppboyLogger.d(str2, "Can't update card field. Json cannot be parsed from disk or is not present. Id: " + str);
            return;
        }
        try {
            e2.put(cardKey.getContentCardsKey(), z);
            a(str, e2);
        } catch (JSONException e3) {
            String str3 = a;
            AppboyLogger.e(str3, "Failed to update card json field to " + z + " with key: " + cardKey, e3);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(cm cmVar) {
        SharedPreferences.Editor edit = this.d.edit();
        if (cmVar.b() != -1) {
            edit.putLong("last_card_updated_at", cmVar.b());
        }
        if (cmVar.a() != -1) {
            edit.putLong("last_full_sync_at", cmVar.a());
        }
        edit.apply();
    }

    static boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 == null) {
            return true;
        }
        if (jSONObject == null) {
            return false;
        }
        String contentCardsKey = CardKey.CREATED.getContentCardsKey();
        if (!jSONObject.has(contentCardsKey) || !jSONObject2.has(contentCardsKey) || jSONObject.getLong(contentCardsKey) <= jSONObject2.getLong(contentCardsKey)) {
            return false;
        }
        return true;
    }

    static JSONObject b(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null) {
            return jSONObject2;
        }
        JSONObject jSONObject3 = new JSONObject();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONObject3.put(next, jSONObject.get(next));
        }
        Iterator<String> keys2 = jSONObject2.keys();
        while (keys2.hasNext()) {
            String next2 = keys2.next();
            if (b.contains(next2)) {
                jSONObject3.put(next2, jSONObject.getBoolean(next2) || jSONObject2.getBoolean(next2));
            } else {
                jSONObject3.put(next2, jSONObject2.get(next2));
            }
        }
        return jSONObject3;
    }

    /* access modifiers changed from: package-private */
    public JSONObject e(String str) {
        String string = this.c.getString(str, (String) null);
        if (string == null) {
            String str2 = a;
            AppboyLogger.d(str2, "Card not present in storage for id: " + str);
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException e2) {
            String str3 = a;
            AppboyLogger.e(str3, "Failed to read card json from storage. Json: " + string, e2);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, JSONObject jSONObject) {
        SharedPreferences.Editor edit = this.c.edit();
        if (jSONObject != null) {
            edit.putString(str, jSONObject.toString());
        } else {
            edit.remove(str);
        }
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public Set<String> d() {
        return new HashSet(this.d.getStringSet("dismissed", new HashSet()));
    }

    /* access modifiers changed from: package-private */
    public void f(String str) {
        Set<String> d2 = d();
        d2.add(str);
        this.d.edit().putStringSet("dismissed", d2).apply();
    }

    /* access modifiers changed from: package-private */
    public void g(String str) {
        Set<String> d2 = d();
        d2.remove(str);
        this.d.edit().putStringSet("dismissed", d2).apply();
    }

    /* access modifiers changed from: package-private */
    public void a(Set<String> set) {
        Set<String> d2 = d();
        d2.retainAll(set);
        this.d.edit().putStringSet("dismissed", d2).apply();
    }

    /* access modifiers changed from: package-private */
    public void b(Set<String> set) {
        Set<String> keySet = this.c.getAll().keySet();
        SharedPreferences.Editor edit = this.c.edit();
        for (String next : keySet) {
            if (!set.contains(next)) {
                String str = a;
                AppboyLogger.d(str, "Removing card from storage with id: " + next);
                edit.remove(next);
            }
        }
        edit.apply();
    }
}
