package com.appboy.models.cards;

import bo.app.bl;
import bo.app.c;
import bo.app.dm;
import bo.app.ed;
import com.appboy.enums.CardKey;
import com.appboy.enums.CardType;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONObject;

public final class CaptionedImageCard extends Card {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final float f;

    public CaptionedImageCard(JSONObject jSONObject, CardKey.Provider provider) {
        this(jSONObject, provider, (bl) null, (dm) null, (c) null);
    }

    public CaptionedImageCard(JSONObject jSONObject, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        super(jSONObject, provider, blVar, dmVar, cVar);
        this.a = jSONObject.getString(provider.getKey(CardKey.CAPTIONED_IMAGE_IMAGE));
        this.b = jSONObject.getString(provider.getKey(CardKey.CAPTIONED_IMAGE_TITLE));
        this.c = jSONObject.getString(provider.getKey(CardKey.CAPTIONED_IMAGE_DESCRIPTION));
        this.d = ed.a(jSONObject, provider.getKey(CardKey.CAPTIONED_IMAGE_URL));
        this.e = ed.a(jSONObject, provider.getKey(CardKey.CAPTIONED_IMAGE_DOMAIN));
        this.f = (float) jSONObject.optDouble(provider.getKey(CardKey.CAPTIONED_IMAGE_ASPECT_RATIO), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public String getImageUrl() {
        return this.a;
    }

    public String getTitle() {
        return this.b;
    }

    public String getDescription() {
        return this.c;
    }

    public String getUrl() {
        return this.d;
    }

    public String getDomain() {
        return this.e;
    }

    public float getAspectRatio() {
        return this.f;
    }

    public CardType getCardType() {
        return CardType.CAPTIONED_IMAGE;
    }

    public String toString() {
        return "CaptionedImageCard{" + super.toString() + ", mImageUrl='" + this.a + '\'' + ", mTitle='" + this.b + '\'' + ", mDescription='" + this.c + '\'' + ", mUrl='" + this.d + '\'' + ", mDomain='" + this.e + '\'' + ", mAspectRatio='" + this.f + '\'' + "}";
    }
}
