package com.appboy.models.outgoing;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

public class TwitterUser implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(TwitterUser.class);
    private final Integer b;
    private final String c;
    private final String d;
    private final String e;
    private final Integer f;
    private final Integer g;
    private final Integer h;
    private final String i;

    public TwitterUser(Integer num, String str, String str2, String str3, Integer num2, Integer num3, Integer num4, String str4) {
        this.b = num;
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = num2;
        this.g = num3;
        this.h = num4;
        this.i = str4;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!StringUtils.isNullOrBlank(this.c)) {
                jSONObject.put(FirebaseAnalytics.Param.SCREEN_NAME, this.c);
            }
            if (!StringUtils.isNullOrBlank(this.d)) {
                jSONObject.put("name", this.d);
            }
            if (!StringUtils.isNullOrBlank(this.e)) {
                jSONObject.put("description", this.e);
            }
            if (!StringUtils.isNullOrBlank(this.i)) {
                jSONObject.put("profile_image_url", this.i);
            }
            jSONObject.put("id", this.b);
            jSONObject.put("followers_count", this.f);
            jSONObject.put("friends_count", this.g);
            jSONObject.put("statuses_count", this.h);
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating twitter user Json.", e2);
        }
        return jSONObject;
    }
}
