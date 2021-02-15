package com.appboy.models.outgoing;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class AttributionData implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(AttributionData.class);
    private final String b;
    private final String c;
    private final String d;
    private final String e;

    public AttributionData(String str, String str2, String str3, String str4) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!StringUtils.isNullOrBlank(this.b)) {
                jSONObject.put("source", this.b);
            }
            if (!StringUtils.isNullOrBlank(this.c)) {
                jSONObject.put("campaign", this.c);
            }
            if (!StringUtils.isNullOrBlank(this.d)) {
                jSONObject.put("adgroup", this.d);
            }
            if (!StringUtils.isNullOrBlank(this.e)) {
                jSONObject.put("ad", this.e);
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating AttributionData Json.", e2);
        }
        return jSONObject;
    }
}
