package com.appboy.models.outgoing;

import bo.app.du;
import bo.app.u;
import com.appboy.models.IPutIntoJson;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppboyProperties implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyProperties.class);
    private JSONObject b = new JSONObject();

    public AppboyProperties() {
    }

    public AppboyProperties(JSONObject jSONObject) {
        this.b = jSONObject;
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        for (String str : arrayList) {
            if (!a(str)) {
                this.b.remove(str);
            } else {
                try {
                    if (jSONObject.get(str) instanceof String) {
                        if (!b(jSONObject.getString(str))) {
                            this.b.remove(str);
                        }
                    } else if (jSONObject.get(str) == JSONObject.NULL) {
                        this.b.remove(str);
                    }
                } catch (JSONException e) {
                    String str2 = a;
                    AppboyLogger.e(str2, "Caught json exception validating property with key name: " + str, e);
                }
            }
        }
    }

    public AppboyProperties addProperty(String str, int i) {
        if (!a(str)) {
            return this;
        }
        try {
            this.b.put(ValidationUtils.ensureAppboyFieldLength(str), i);
        } catch (JSONException e) {
            AppboyLogger.e(a, "Caught json exception trying to add property.", e);
        }
        return this;
    }

    public AppboyProperties addProperty(String str, double d) {
        if (!a(str)) {
            return this;
        }
        try {
            this.b.put(ValidationUtils.ensureAppboyFieldLength(str), d);
        } catch (JSONException e) {
            AppboyLogger.e(a, "Caught json exception trying to add property.", e);
        }
        return this;
    }

    public AppboyProperties addProperty(String str, boolean z) {
        if (!a(str)) {
            return this;
        }
        try {
            this.b.put(ValidationUtils.ensureAppboyFieldLength(str), z);
        } catch (JSONException e) {
            AppboyLogger.e(a, "Caught json exception trying to add property.", e);
        }
        return this;
    }

    public AppboyProperties addProperty(String str, Date date) {
        if (!a(str) || date == null) {
            return this;
        }
        try {
            this.b.put(ValidationUtils.ensureAppboyFieldLength(str), du.a(date, u.LONG));
        } catch (JSONException e) {
            AppboyLogger.e(a, "Caught json exception trying to add property.", e);
        }
        return this;
    }

    public AppboyProperties addProperty(String str, String str2) {
        if (a(str) && b(str2)) {
            try {
                this.b.put(ValidationUtils.ensureAppboyFieldLength(str), ValidationUtils.ensureAppboyFieldLength(str2));
            } catch (JSONException e) {
                AppboyLogger.e(a, "Caught json exception trying to add property.", e);
            }
        }
        return this;
    }

    public int size() {
        return this.b.length();
    }

    static boolean a(String str) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.w(a, "The AppboyProperties key cannot be null or contain only whitespaces. Not adding property.");
            return false;
        } else if (!str.startsWith("$")) {
            return true;
        } else {
            AppboyLogger.w(a, "The leading character in the key string may not be '$'. Not adding property.");
            return false;
        }
    }

    static boolean b(String str) {
        if (str != null) {
            return true;
        }
        AppboyLogger.w(a, "The AppboyProperties value cannot be null. Not adding property.");
        return false;
    }

    public JSONObject forJsonPut() {
        return this.b;
    }
}
