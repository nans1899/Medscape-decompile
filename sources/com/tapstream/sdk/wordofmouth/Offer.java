package com.tapstream.sdk.wordofmouth;

import com.facebook.share.internal.ShareConstants;
import com.tapstream.sdk.DelegatedJSONObject;
import org.json.JSONObject;

public class Offer extends DelegatedJSONObject {
    public static Offer fromApiResponse(JSONObject jSONObject) {
        return new Offer(jSONObject);
    }

    public Offer(JSONObject jSONObject) {
        super(jSONObject);
    }

    public String prepareMessage(String str) {
        return getMessage().replace("OFFER_URL", getOfferUrl()).replace("SDK_SESSION_ID", str);
    }

    public String getMessage() {
        return getOrDefault(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "");
    }

    public String getOfferButtonText() {
        return getOrDefault("offer_button_text", "");
    }

    public String getOfferText() {
        return getOrDefault("offer_text", "");
    }

    public String getOfferTitle() {
        return getOrDefault("offer_title", "");
    }

    public String getOfferUrl() {
        return getOrDefault("offer_url", "");
    }

    public String getMarkup() {
        return getOrDefault("markup", "");
    }
}
