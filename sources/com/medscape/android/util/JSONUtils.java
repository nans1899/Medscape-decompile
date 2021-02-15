package com.medscape.android.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static Map<String, Object> getMapFromJson(String str) {
        Object obj;
        if (str == null || str.trim().equalsIgnoreCase("")) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap hashMap = new HashMap();
            try {
                Iterator<String> keys = jSONObject.keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!(next == null || next.trim().equalsIgnoreCase("") || (obj = jSONObject.get(next)) == null)) {
                            if (obj instanceof JSONArray) {
                                obj = getListFromJSONArray((JSONArray) obj);
                            } else if (obj instanceof JSONObject) {
                                obj = getMapFromJson(obj.toString());
                            }
                            hashMap.put(next, obj);
                        }
                    }
                }
            } catch (Exception unused) {
            }
            return hashMap;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static List<Object> getListFromJSONArray(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                Object obj = jSONArray.get(i);
                if (obj instanceof JSONObject) {
                    obj = getMapFromJson(obj.toString());
                } else if (obj instanceof JSONArray) {
                    obj = getListFromJSONArray((JSONArray) obj);
                }
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static JSONArray optJSONArrayFromDSV(String str, String str2) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (StringUtil.isNotEmpty(str)) {
            if (!StringUtil.isNotEmpty(str2)) {
                str2 = ",";
            }
            for (String str3 : str.split(str2)) {
                if (str3.length() > 0) {
                    jSONArray.put(str3);
                }
            }
        }
        return jSONArray;
    }
}
