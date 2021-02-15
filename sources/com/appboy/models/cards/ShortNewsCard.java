package com.appboy.models.cards;

import bo.app.bl;
import bo.app.c;
import bo.app.dm;
import bo.app.ed;
import com.appboy.enums.CardKey;
import com.appboy.enums.CardType;
import org.json.JSONObject;

public final class ShortNewsCard extends Card {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    public ShortNewsCard(JSONObject jSONObject, CardKey.Provider provider) {
        this(jSONObject, provider, (bl) null, (dm) null, (c) null);
    }

    public ShortNewsCard(JSONObject jSONObject, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        super(jSONObject, provider, blVar, dmVar, cVar);
        this.a = jSONObject.getString(provider.getKey(CardKey.SHORT_NEWS_DESCRIPTION));
        this.b = jSONObject.getString(provider.getKey(CardKey.SHORT_NEWS_IMAGE));
        this.c = ed.a(jSONObject, provider.getKey(CardKey.SHORT_NEWS_TITLE));
        this.d = ed.a(jSONObject, provider.getKey(CardKey.SHORT_NEWS_URL));
        this.e = ed.a(jSONObject, provider.getKey(CardKey.SHORT_NEWS_DOMAIN));
    }

    public String getDescription() {
        return this.a;
    }

    public String getImageUrl() {
        return this.b;
    }

    public String getTitle() {
        return this.c;
    }

    public String getUrl() {
        return this.d;
    }

    public String getDomain() {
        return this.e;
    }

    public CardType getCardType() {
        return CardType.SHORT_NEWS;
    }

    public String toString() {
        return "ShortNewsCard{" + super.toString() + ", mDescription='" + this.a + '\'' + ", mImageUrl='" + this.b + '\'' + ", mTitle='" + this.c + '\'' + ", mUrl='" + this.d + '\'' + ", mDomain='" + this.e + '\'' + "}";
    }
}
