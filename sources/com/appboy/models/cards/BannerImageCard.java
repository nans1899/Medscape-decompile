package com.appboy.models.cards;

import bo.app.bl;
import bo.app.c;
import bo.app.dm;
import bo.app.ed;
import com.appboy.enums.CardKey;
import com.appboy.enums.CardType;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONObject;

public final class BannerImageCard extends Card {
    private final String a;
    private final String b;
    private final String c;
    private final float d;

    public BannerImageCard(JSONObject jSONObject, CardKey.Provider provider) {
        this(jSONObject, provider, (bl) null, (dm) null, (c) null);
    }

    public BannerImageCard(JSONObject jSONObject, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        super(jSONObject, provider, blVar, dmVar, cVar);
        this.a = jSONObject.getString(provider.getKey(CardKey.BANNER_IMAGE_IMAGE));
        this.b = ed.a(jSONObject, provider.getKey(CardKey.BANNER_IMAGE_URL));
        this.c = ed.a(jSONObject, provider.getKey(CardKey.BANNER_IMAGE_DOMAIN));
        this.d = (float) jSONObject.optDouble(provider.getKey(CardKey.BANNER_IMAGE_ASPECT_RATIO), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public String getImageUrl() {
        return this.a;
    }

    public String getUrl() {
        return this.b;
    }

    public String getDomain() {
        return this.c;
    }

    public float getAspectRatio() {
        return this.d;
    }

    public CardType getCardType() {
        return CardType.BANNER;
    }

    public String toString() {
        return "BannerImageCard{" + super.toString() + ", mImageUrl='" + this.a + '\'' + ", mUrl='" + this.b + '\'' + ", mDomain='" + this.c + '\'' + ", mAspectRatio='" + this.d + '\'' + "}";
    }
}
