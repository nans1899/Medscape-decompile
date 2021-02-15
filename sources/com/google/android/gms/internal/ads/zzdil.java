package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdil implements zzdgu<JSONObject> {
    private String zzgzi;
    private String zzgzj;

    public zzdil(String str, String str2) {
        this.zzgzi = str;
        this.zzgzj = str2;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            JSONObject zzb = zzbao.zzb((JSONObject) obj, "pii");
            zzb.put("doritos", this.zzgzi);
            zzb.put("doritos_v2", this.zzgzj);
        } catch (JSONException unused) {
            zzayp.zzei("Failed putting doritos string.");
        }
    }
}
