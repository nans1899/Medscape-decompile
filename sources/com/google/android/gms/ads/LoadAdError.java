package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzabb;
import com.google.android.gms.internal.ads.zzwm;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class LoadAdError extends AdError {
    private final ResponseInfo zzadj;

    public LoadAdError(int i, String str, String str2, AdError adError, ResponseInfo responseInfo) {
        super(i, str, str2, adError);
        this.zzadj = responseInfo;
    }

    public final ResponseInfo getResponseInfo() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue()) {
            return this.zzadj;
        }
        return null;
    }

    public final JSONObject zzdp() throws JSONException {
        JSONObject zzdp = super.zzdp();
        ResponseInfo responseInfo = getResponseInfo();
        if (responseInfo == null) {
            zzdp.put("Response Info", "null");
        } else {
            zzdp.put("Response Info", responseInfo.zzdp());
        }
        return zzdp;
    }

    public final String toString() {
        try {
            return zzdp().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }
}
