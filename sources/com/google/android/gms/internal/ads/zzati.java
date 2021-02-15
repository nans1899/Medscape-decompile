package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzati {
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzdrb;
    private final String zzdto;
    private final boolean zzdtp;
    private final String zzduc;
    private final List<String> zzdue;
    private final String zzduf;
    private final String zzdug;
    private final boolean zzduh;
    private final String zzdui;
    private final boolean zzduj;
    private final JSONObject zzduk;

    public zzati(JSONObject jSONObject) {
        List<String> list;
        this.url = jSONObject.optString("url");
        this.zzduf = jSONObject.optString("base_uri");
        this.zzdug = jSONObject.optString("post_parameters");
        String optString = jSONObject.optString("drt_include");
        int i = 1;
        this.zzduh = optString != null && (optString.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || optString.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
        this.zzdrb = jSONObject.optString("request_id");
        this.type = jSONObject.optString("type");
        String optString2 = jSONObject.optString(JSONAPISpecConstants.ERRORS);
        if (optString2 == null) {
            list = null;
        } else {
            list = Arrays.asList(optString2.split(","));
        }
        this.zzdue = list;
        this.errorCode = jSONObject.optInt("valid", 0) == 1 ? -2 : i;
        this.zzdui = jSONObject.optString("fetched_ad");
        this.zzduj = jSONObject.optBoolean("render_test_ad_label");
        JSONObject optJSONObject = jSONObject.optJSONObject("preprocessor_flags");
        this.zzduk = optJSONObject == null ? new JSONObject() : optJSONObject;
        this.zzdto = jSONObject.optString("analytics_query_ad_event_id");
        this.zzdtp = jSONObject.optBoolean("is_analytics_logging_enabled");
        this.zzduc = jSONObject.optString("pool_key");
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final List<String> zzvh() {
        return this.zzdue;
    }

    public final String zzvi() {
        return this.zzduf;
    }

    public final String zzvj() {
        return this.zzdug;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean zzvk() {
        return this.zzduh;
    }

    public final JSONObject zzvl() {
        return this.zzduk;
    }

    public final String zzvm() {
        return this.zzduc;
    }
}
