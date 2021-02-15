package com.google.android.gms.ads.query;

import com.google.android.gms.internal.ads.zzbbq;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class AdInfo {
    private final QueryInfo zzhlq;
    private final String zzhlr;

    public static String getRequestId(String str) {
        if (str == null) {
            zzbbq.zzfe("adString passed to AdInfo.getRequestId() cannot be null. Returning empty string.");
            return "";
        }
        try {
            return new JSONObject(str).optString("request_id", "");
        } catch (JSONException unused) {
            zzbbq.zzfe("Invalid adString passed to AdInfo.getRequestId(). Returning empty string.");
            return "";
        }
    }

    public AdInfo(QueryInfo queryInfo, String str) {
        this.zzhlq = queryInfo;
        this.zzhlr = str;
    }

    public QueryInfo getQueryInfo() {
        return this.zzhlq;
    }

    public String getAdString() {
        return this.zzhlr;
    }
}
