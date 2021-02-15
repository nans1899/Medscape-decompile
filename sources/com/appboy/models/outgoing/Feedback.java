package com.appboy.models.outgoing;

import bo.app.ch;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.AccessToken;
import com.facebook.share.internal.ShareConstants;
import org.json.JSONException;
import org.json.JSONObject;

public final class Feedback implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(Feedback.class);
    private final String b;
    private final String c;
    private final boolean d;
    private final ch e;
    private final String f;

    public Feedback(String str, String str2, boolean z, ch chVar, String str3) {
        if (!StringUtils.isNullOrBlank(str)) {
            this.b = str;
            this.c = str2;
            this.d = z;
            this.e = chVar;
            this.f = str3;
            return;
        }
        throw new IllegalArgumentException("Message cannot be null or blank");
    }

    public String getMessage() {
        return this.b;
    }

    public String getReplyToEmail() {
        return this.c;
    }

    public boolean isReportingABug() {
        return this.d;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, StringUtils.checkNotNullOrEmpty(this.b));
            jSONObject.put("reply_to", this.c);
            jSONObject.put("is_bug", this.d);
            if (this.e != null) {
                jSONObject.put("device", this.e.forJsonPut());
            }
            if (!StringUtils.isNullOrEmpty(this.f)) {
                jSONObject.put(AccessToken.USER_ID_KEY, this.f);
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating feedback Json.", e2);
        }
        return jSONObject;
    }
}
