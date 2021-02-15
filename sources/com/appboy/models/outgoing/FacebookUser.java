package com.appboy.models.outgoing;

import com.appboy.enums.Gender;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.medscape.android.settings.Settings;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookUser implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(FacebookUser.class);
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final Gender h;
    private final Integer i;
    private final Collection<String> j;
    private final String k;

    public FacebookUser(String str, String str2, String str3, String str4, String str5, String str6, Gender gender, Integer num, Collection<String> collection, String str7) {
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = gender;
        this.i = num;
        this.j = collection;
        this.k = str7;
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!StringUtils.isNullOrBlank(this.b)) {
                jSONObject.put("id", this.b);
            }
            if (!StringUtils.isNullOrBlank(this.c)) {
                jSONObject.put("first_name", this.c);
            }
            if (!StringUtils.isNullOrBlank(this.d)) {
                jSONObject.put("last_name", this.d);
            }
            if (!StringUtils.isNullOrBlank(this.e)) {
                jSONObject.put("email", this.e);
            }
            if (!StringUtils.isNullOrBlank(this.f)) {
                jSONObject.put("bio", this.f);
            }
            if (!StringUtils.isNullOrBlank(this.k)) {
                jSONObject.put("birthday", this.k);
            }
            if (!StringUtils.isNullOrBlank(this.g)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("name", this.g);
                jSONObject.put("location", jSONObject2);
            }
            if (this.h != null) {
                jSONObject.put(Settings.GENDER, this.h.forJsonPut());
            }
            jSONObject.put("num_friends", this.i);
            if (this.j != null && !this.j.isEmpty()) {
                jSONObject.put("likes", a());
            }
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating facebook user Json.", e2);
        }
        return jSONObject;
    }

    private JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        for (String put : this.j) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("name", put);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }
}
