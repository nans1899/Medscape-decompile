package com.medscape.android.ads.proclivity;

import com.wbmd.ads.model.AdContentData;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProclivityConverter {
    public static JSONObject mapToJsonBodyRequest(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            JSONObject createPageSegVars = createPageSegVars(map);
            JSONObject createUserSegVars = createUserSegVars(map);
            JSONObject createWebSegVars = createWebSegVars(map);
            if (createPageSegVars != null && createPageSegVars.length() > 0) {
                jSONObject2.put("pageSegVars", createPageSegVars);
            }
            jSONObject2.put("reqHeaders", getReqHeader());
            if (createUserSegVars != null && createUserSegVars.length() > 0) {
                jSONObject2.put("userSegVars", createUserSegVars);
            }
            if (createWebSegVars != null && createWebSegVars.length() > 0) {
                jSONObject2.put("webSegVars", createWebSegVars);
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put("pb");
            jSONObject.put("dfpData", jSONObject2);
            jSONObject.put("requestedSteps", jSONArray);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static JSONObject createPageSegVars(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (((String) next.getKey()).equals("art") || ((String) next.getKey()).equals(AdContentData.LEAD_SPECIALITY) || ((String) next.getKey()).equals("pub") || ((String) next.getKey()).equals(AdContentData.LEAD_CONCEPT) || ((String) next.getKey()).equals("cg")) {
                    try {
                        jSONObject.put((String) next.getKey(), next.getValue());
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return jSONObject;
    }

    public static JSONObject getReqHeader() {
        JSONObject jSONObject = new JSONObject();
        try {
            return new JSONObject("{\"domainCategory\" : \"\",\"device\":\"android\",\"ep\":\"\",\"domain\":\"\",\"kw\":\"\",\"appName\":\"medscape\"}");
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public static JSONObject createUserSegVars(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (((String) next.getKey()).equals("dt") || ((String) next.getKey()).equals("usp") || ((String) next.getKey()).equals("pf")) {
                    try {
                        jSONObject.put((String) next.getKey(), next.getValue());
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return jSONObject;
    }

    public static JSONObject createWebSegVars(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                if (((String) next.getKey()).equals("env") || ((String) next.getKey()).equals("pc") || ((String) next.getKey()).equals("spon") || ((String) next.getKey()).equals("envp")) {
                    try {
                        jSONObject.put((String) next.getKey(), next.getValue());
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return jSONObject;
    }
}
