package com.appboy.models.cards;

import bo.app.bq;
import bo.app.c;
import bo.app.dm;
import bo.app.du;
import bo.app.ed;
import com.appboy.enums.CardCategory;
import com.appboy.enums.CardKey;
import com.appboy.enums.CardType;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import org.json.JSONArray;
import org.json.JSONObject;

public class Card extends Observable implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(Card.class);
    private final JSONObject b;
    private final Map<String, String> c;
    private final String d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private final long k;
    private final long l;
    private final long m;
    private boolean n;
    private final EnumSet<CardCategory> o;
    private boolean p;
    private final bq q;
    private final dm r;
    private final c s;

    public String getUrl() {
        return null;
    }

    public Card(JSONObject jSONObject, CardKey.Provider provider) {
        this(jSONObject, provider, (bq) null, (dm) null, (c) null);
    }

    public Card(JSONObject jSONObject, CardKey.Provider provider, bq bqVar, dm dmVar, c cVar) {
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.n = false;
        this.b = jSONObject;
        this.q = bqVar;
        this.r = dmVar;
        this.s = cVar;
        this.c = ed.a(jSONObject.optJSONObject(provider.getKey(CardKey.EXTRAS)), (Map<String, String>) new HashMap());
        this.d = jSONObject.getString(provider.getKey(CardKey.ID));
        this.e = jSONObject.optBoolean(provider.getKey(CardKey.VIEWED));
        this.g = jSONObject.optBoolean(provider.getKey(CardKey.DISMISSED), false);
        this.i = jSONObject.optBoolean(provider.getKey(CardKey.PINNED), false);
        this.k = jSONObject.getLong(provider.getKey(CardKey.CREATED));
        this.m = jSONObject.optLong(provider.getKey(CardKey.EXPIRES_AT), -1);
        this.n = jSONObject.optBoolean(provider.getKey(CardKey.OPEN_URI_IN_WEBVIEW), false);
        this.h = jSONObject.optBoolean(provider.getKey(CardKey.REMOVED), false);
        JSONArray optJSONArray = jSONObject.optJSONArray(provider.getKey(CardKey.CATEGORIES));
        if (optJSONArray == null || optJSONArray.length() == 0) {
            this.o = EnumSet.of(CardCategory.NO_CATEGORY);
        } else {
            this.o = EnumSet.noneOf(CardCategory.class);
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                CardCategory cardCategory = CardCategory.get(optJSONArray.getString(i2));
                if (cardCategory != null) {
                    this.o.add(cardCategory);
                }
            }
        }
        this.l = jSONObject.optLong(provider.getKey(CardKey.UPDATED), this.k);
        this.p = jSONObject.optBoolean(provider.getKey(CardKey.DISMISSIBLE), false);
        this.f = jSONObject.optBoolean(provider.getKey(CardKey.READ), this.e);
        this.j = jSONObject.optBoolean(provider.getKey(CardKey.CLICKED), false);
    }

    public boolean logImpression() {
        try {
            if (this.q == null || this.s == null || this.r == null || !a()) {
                return false;
            }
            if (!isControl()) {
                String str = a;
                AppboyLogger.v(str, "Logging impression event for card with id: " + this.d);
                this.q.a(this.s.a(this.d));
            } else {
                String str2 = a;
                AppboyLogger.v(str2, "Logging control impression event for card with id: " + this.d);
                this.q.a(this.s.d(this.d));
            }
            this.r.b(this.d);
            return true;
        } catch (Exception e2) {
            String str3 = a;
            AppboyLogger.w(str3, "Failed to log card impression for card id: " + this.d, e2);
            return false;
        }
    }

    public boolean logClick() {
        try {
            this.j = true;
            if (this.q == null || this.s == null || this.r == null || !a()) {
                AppboyLogger.w(a, "Failed to log card clicked.");
                return false;
            }
            this.q.a(this.s.e(this.d));
            this.r.c(this.d);
            return true;
        } catch (Exception e2) {
            AppboyLogger.w(a, "Failed to log card as clicked.", e2);
            return false;
        }
    }

    public boolean isClicked() {
        return this.j;
    }

    @Deprecated
    public boolean isRead() {
        return isIndicatorHighlighted();
    }

    public boolean isIndicatorHighlighted() {
        return this.f;
    }

    @Deprecated
    public void setIsRead(boolean z) {
        setIndicatorHighlighted(z);
    }

    public void setIndicatorHighlighted(boolean z) {
        dm dmVar;
        this.f = z;
        setChanged();
        notifyObservers();
        if (z && (dmVar = this.r) != null) {
            try {
                dmVar.a(this.d);
            } catch (Exception e2) {
                AppboyLogger.d(a, "Failed to mark card indicator as highlighted.", (Throwable) e2);
            }
        }
    }

    public boolean isDismissed() {
        return this.g;
    }

    public void setIsDismissed(boolean z) {
        if (!this.g || !z) {
            this.g = z;
            dm dmVar = this.r;
            if (dmVar != null) {
                dmVar.d(this.d);
            }
            if (z) {
                try {
                    if (this.q != null && this.s != null && a()) {
                        this.q.a(this.s.c(this.d));
                    }
                } catch (Exception e2) {
                    AppboyLogger.w(a, "Failed to log card dismissed.", e2);
                }
            }
        } else {
            AppboyLogger.w(a, "Cannot dismiss a card more than once. Doing nothing.");
        }
    }

    public boolean getViewed() {
        return this.e;
    }

    public void setViewed(boolean z) {
        this.e = z;
        dm dmVar = this.r;
        if (dmVar != null) {
            dmVar.b(this.d);
        }
    }

    public boolean isEqualToCard(Card card) {
        return this.d.equals(card.getId()) && this.l == card.getUpdated() && this.q == card.q;
    }

    public String getId() {
        return this.d;
    }

    public Map<String, String> getExtras() {
        return this.c;
    }

    public boolean getIsPinned() {
        return this.i;
    }

    public boolean getOpenUriInWebView() {
        return this.n;
    }

    public long getCreated() {
        return this.k;
    }

    public long getUpdated() {
        return this.l;
    }

    public boolean isRemoved() {
        return this.h;
    }

    public boolean getIsDismissible() {
        return this.p;
    }

    public void setIsPinned(boolean z) {
        this.i = z;
    }

    public long getExpiresAt() {
        return this.m;
    }

    public boolean isExpired() {
        return getExpiresAt() != -1 && getExpiresAt() <= du.a();
    }

    public EnumSet<CardCategory> getCategories() {
        return this.o;
    }

    public boolean isInCategorySet(EnumSet<CardCategory> enumSet) {
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            if (this.o.contains((CardCategory) it.next())) {
                return true;
            }
        }
        return false;
    }

    public CardType getCardType() {
        return CardType.DEFAULT;
    }

    public boolean isControl() {
        return getCardType() == CardType.CONTROL;
    }

    public JSONObject forJsonPut() {
        return this.b;
    }

    public String toString() {
        return "mId='" + this.d + '\'' + ", mViewed='" + this.e + '\'' + ", mCreated='" + this.k + '\'' + ", mUpdated='" + this.l + '\'' + ", mIsClicked='" + this.j + '\'';
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        if (!StringUtils.isNullOrBlank(this.d)) {
            return true;
        }
        AppboyLogger.e(a, "Card ID cannot be null");
        return false;
    }
}
