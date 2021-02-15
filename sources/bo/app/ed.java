package bo.app;

import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ed extends JSONObject {
    private static final String a = AppboyLogger.getAppboyLogTag(ed.class);

    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum a(JSONObject jSONObject, String str, Class<TargetEnum> cls) {
        return dv.a(jSONObject.getString(str).toUpperCase(Locale.US), cls);
    }

    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum a(JSONObject jSONObject, String str, Class<TargetEnum> cls, TargetEnum targetenum) {
        try {
            return a(jSONObject, str, cls);
        } catch (Exception unused) {
            return targetenum;
        }
    }

    public static <T> JSONArray a(Collection<? extends IPutIntoJson<T>> collection) {
        JSONArray jSONArray = new JSONArray();
        for (IPutIntoJson forJsonPut : collection) {
            jSONArray.put(forJsonPut.forJsonPut());
        }
        return jSONArray;
    }

    public static <T> JSONArray a(T[] tArr) {
        JSONArray jSONArray = new JSONArray();
        for (T put : tArr) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public static String a(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.optString(str, (String) null);
    }

    public static Map<String, String> a(JSONObject jSONObject, Map<String, String> map) {
        if (jSONObject != null) {
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } else if (map != null) {
            return map;
        } else {
            AppboyLogger.d(a, "Cannot convert JSONObject to Map because JSONObject is null and no default was provided.");
            throw new JSONException("Cannot convert JSONObject to Map because JSONObject is null and no default was provided.");
        }
    }

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject3.put(next, jSONObject.get(next));
            }
            Iterator<String> keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                jSONObject3.put(next2, jSONObject2.get(next2));
            }
            return jSONObject3;
        } catch (JSONException e) {
            AppboyLogger.e(a, "Caught exception merging Json objects.", e);
            return null;
        }
    }

    public static String a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            return jSONObject.toString(2);
        } catch (JSONException e) {
            AppboyLogger.e(a, "Caught JSONException while generating pretty printed json. Returning standard toString().", e);
            return jSONObject.toString();
        }
    }
}
