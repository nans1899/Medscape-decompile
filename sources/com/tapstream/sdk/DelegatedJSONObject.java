package com.tapstream.sdk;

import org.json.JSONException;
import org.json.JSONObject;

public class DelegatedJSONObject {
    protected final JSONObject delegate;

    protected DelegatedJSONObject(JSONObject jSONObject) {
        this.delegate = jSONObject;
    }

    /* access modifiers changed from: protected */
    public String getOrDefault(String str, String str2) {
        try {
            if (this.delegate.has(str)) {
                if (!this.delegate.isNull(str)) {
                    return this.delegate.getString(str);
                }
            }
        } catch (JSONException unused) {
        }
        return str2;
    }

    /* access modifiers changed from: protected */
    public int getOrDefault(String str, int i) {
        try {
            if (this.delegate.has(str)) {
                if (!this.delegate.isNull(str)) {
                    return this.delegate.getInt(str);
                }
            }
        } catch (JSONException unused) {
        }
        return i;
    }
}
