package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.enums.CardKey;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.models.cards.Card;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONObject;

public class dk implements dm<FeedUpdatedEvent> {
    private static final String a = AppboyLogger.getAppboyLogTag(dk.class);
    private final SharedPreferences b;
    private final Set<String> c;
    private final Set<String> d;
    private final c e = new b();
    private bl f;

    public void c(String str) {
    }

    public void d(String str) {
    }

    enum a {
        READ_CARDS("read_cards_set", "read_cards_flat"),
        VIEWED_CARDS("viewed_cards_set", "viewed_cards_flat");
        
        private final String c;
        private final String d;

        private a(String str, String str2) {
            this.c = str;
            this.d = str2;
        }

        public String a() {
            return this.c;
        }

        public String b() {
            return this.d;
        }
    }

    public dk(Context context, String str) {
        String cacheFileSuffix = StringUtils.getCacheFileSuffix(context, str == null ? "" : str);
        this.b = context.getSharedPreferences("com.appboy.storage.feedstorageprovider" + cacheFileSuffix, 0);
        this.c = a(a.VIEWED_CARDS);
        this.d = a(a.READ_CARDS);
        f(str);
    }

    public void a(String str) {
        if (!this.d.contains(str)) {
            this.d.add(str);
            a(this.d, a.READ_CARDS);
        }
    }

    public void b(String str) {
        if (!this.c.contains(str)) {
            this.c.add(str);
            a(this.c, a.VIEWED_CARDS);
        }
    }

    public void a(bl blVar) {
        this.f = blVar;
    }

    public FeedUpdatedEvent a(JSONArray jSONArray, String str) {
        String str2 = str == null ? "" : str;
        String string = this.b.getString("uid", "");
        if (string.equals(str2)) {
            String str3 = a;
            AppboyLogger.i(str3, "Updating offline feed for user with id: " + str);
            long a2 = du.a();
            a(jSONArray, a2);
            this.c.retainAll(a(jSONArray));
            a(this.c, a.VIEWED_CARDS);
            this.d.retainAll(a(jSONArray));
            a(this.d, a.READ_CARDS);
            return a(jSONArray, str, false, a2);
        }
        String str4 = a;
        AppboyLogger.i(str4, "The received cards are for user " + str + " and the current user is " + string + " , the cards will be discarded and no changes will be made.");
        return null;
    }

    public FeedUpdatedEvent a() {
        return a(new JSONArray(this.b.getString("cards", HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)), this.b.getString("uid", ""), true, this.b.getLong("cards_timestamp", -1));
    }

    private FeedUpdatedEvent a(JSONArray jSONArray, String str, boolean z, long j) {
        List list;
        if (jSONArray == null || jSONArray.length() == 0) {
            list = new ArrayList();
        } else {
            list = by.a(jSONArray, new CardKey.Provider(false), this.f, (dm) this, this.e);
        }
        List<Card> list2 = list;
        for (Card card : list2) {
            if (this.c.contains(card.getId())) {
                card.setViewed(true);
                card.setIndicatorHighlighted(true);
            }
            if (this.d.contains(card.getId())) {
                card.setIndicatorHighlighted(true);
            }
        }
        return new FeedUpdatedEvent(list2, str, z, j);
    }

    private Set<String> a(a aVar) {
        String a2 = aVar.a();
        String b2 = aVar.b();
        if (!this.b.contains(b2)) {
            return new ConcurrentSkipListSet(this.b.getStringSet(a2, new HashSet()));
        }
        Set<String> e2 = e(this.b.getString(b2, (String) null));
        SharedPreferences.Editor edit = this.b.edit();
        edit.remove(b2);
        edit.apply();
        a(e2, aVar);
        return e2;
    }

    private void a(JSONArray jSONArray, long j) {
        SharedPreferences.Editor edit = this.b.edit();
        if (jSONArray == null || jSONArray.length() == 0) {
            edit.remove("cards");
        } else {
            edit.putString("cards", jSONArray.toString());
        }
        edit.putLong("cards_timestamp", j);
        edit.apply();
    }

    private void f(String str) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString("uid", str);
        edit.apply();
    }

    /* access modifiers changed from: package-private */
    public void a(Set<String> set, a aVar) {
        String a2 = aVar.a();
        SharedPreferences.Editor edit = this.b.edit();
        if (set == null || set.isEmpty()) {
            edit.remove(a2);
        } else {
            edit.putStringSet(a2, set);
        }
        edit.apply();
    }

    static Set<String> e(String str) {
        HashSet hashSet = new HashSet();
        if (str != null) {
            Collections.addAll(hashSet, str.split(";"));
        }
        return hashSet;
    }

    static Set<String> a(JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.has(CardKey.ID.getFeedKey())) {
                    hashSet.add(jSONObject.getString(CardKey.ID.getFeedKey()));
                }
            }
        }
        return hashSet;
    }
}
