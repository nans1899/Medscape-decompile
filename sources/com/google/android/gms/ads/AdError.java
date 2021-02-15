package com.google.android.gms.ads;

import android.os.IBinder;
import com.google.android.gms.internal.ads.zzva;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class AdError {
    public static final String UNDEFINED_DOMAIN = "undefined";
    private final int code;
    private final String zzacm;
    private final String zzacn;
    private final AdError zzaco;

    public AdError(int i, String str, String str2) {
        this.code = i;
        this.zzacm = str;
        this.zzacn = str2;
        this.zzaco = null;
    }

    public AdError(int i, String str, String str2, AdError adError) {
        this.code = i;
        this.zzacm = str;
        this.zzacn = str2;
        this.zzaco = adError;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.zzacm;
    }

    public String getDomain() {
        return this.zzacn;
    }

    public AdError getCause() {
        return this.zzaco;
    }

    public final zzva zzdo() {
        zzva zzva;
        if (this.zzaco == null) {
            zzva = null;
        } else {
            AdError adError = this.zzaco;
            zzva = new zzva(adError.code, adError.zzacm, adError.zzacn, (zzva) null, (IBinder) null);
        }
        return new zzva(this.code, this.zzacm, this.zzacn, zzva, (IBinder) null);
    }

    public JSONObject zzdp() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("Code", this.code);
        jSONObject.put("Message", this.zzacm);
        jSONObject.put("Domain", this.zzacn);
        AdError adError = this.zzaco;
        if (adError == null) {
            jSONObject.put("Cause", "null");
        } else {
            jSONObject.put("Cause", adError.zzdp());
        }
        return jSONObject;
    }

    public String toString() {
        try {
            return zzdp().toString(2);
        } catch (JSONException unused) {
            return "Error forming toString output.";
        }
    }
}
