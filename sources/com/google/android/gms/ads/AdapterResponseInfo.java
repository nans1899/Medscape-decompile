package com.google.android.gms.ads;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzvr;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class AdapterResponseInfo {
    private final zzvr zzadf;
    private final AdError zzadg;

    private AdapterResponseInfo(zzvr zzvr) {
        AdError adError;
        this.zzadf = zzvr;
        if (zzvr.zzcid == null) {
            adError = null;
        } else {
            adError = zzvr.zzcid.zzph();
        }
        this.zzadg = adError;
    }

    public static AdapterResponseInfo zza(zzvr zzvr) {
        if (zzvr != null) {
            return new AdapterResponseInfo(zzvr);
        }
        return null;
    }

    public final String getAdapterClassName() {
        return this.zzadf.zzcib;
    }

    public final long getLatencyMillis() {
        return this.zzadf.zzcic;
    }

    public final AdError getAdError() {
        return this.zzadg;
    }

    public final Bundle getCredentials() {
        return this.zzadf.zzcie;
    }

    public final JSONObject zzdp() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Adapter", this.zzadf.zzcib);
        jSONObject.put("Latency", this.zzadf.zzcic);
        JSONObject jSONObject2 = new JSONObject();
        for (String str : this.zzadf.zzcie.keySet()) {
            jSONObject2.put(str, this.zzadf.zzcie.get(str));
        }
        jSONObject.put("Credentials", jSONObject2);
        AdError adError = this.zzadg;
        if (adError == null) {
            jSONObject.put("Ad Error", "null");
        } else {
            jSONObject.put("Ad Error", adError.zzdp());
        }
        return jSONObject;
    }

    public final String toString() {
        try {
            return zzdp().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }
}
