package com.appboy.models.cards;

import bo.app.bl;
import bo.app.c;
import bo.app.dm;
import bo.app.ed;
import com.appboy.enums.CardKey;
import com.appboy.enums.CardType;
import org.json.JSONObject;

public final class TextAnnouncementCard extends Card {
    private final String a;
    private final String b;
    private final String c;
    private final String d;

    public TextAnnouncementCard(JSONObject jSONObject, CardKey.Provider provider) {
        this(jSONObject, provider, (bl) null, (dm) null, (c) null);
    }

    public TextAnnouncementCard(JSONObject jSONObject, CardKey.Provider provider, bl blVar, dm dmVar, c cVar) {
        super(jSONObject, provider, blVar, dmVar, cVar);
        this.b = ed.a(jSONObject, provider.getKey(CardKey.TEXT_ANNOUNCEMENT_TITLE));
        this.a = jSONObject.getString(provider.getKey(CardKey.TEXT_ANNOUNCEMENT_DESCRIPTION));
        this.c = ed.a(jSONObject, provider.getKey(CardKey.TEXT_ANNOUNCEMENT_URL));
        this.d = ed.a(jSONObject, provider.getKey(CardKey.TEXT_ANNOUNCEMENT_DOMAIN));
    }

    public String getDescription() {
        return this.a;
    }

    public String getTitle() {
        return this.b;
    }

    public String getUrl() {
        return this.c;
    }

    public String getDomain() {
        return this.d;
    }

    public CardType getCardType() {
        return CardType.TEXT_ANNOUNCEMENT;
    }

    public String toString() {
        return "TextAnnouncementCard{" + super.toString() + ", mDescription='" + this.a + '\'' + ", mTitle='" + this.b + '\'' + ", mUrl='" + this.c + '\'' + ", mDomain='" + this.d + '\'' + "}";
    }
}
