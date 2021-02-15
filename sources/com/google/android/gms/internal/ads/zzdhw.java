package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhw implements zzdgu<JSONObject> {
    private String zzgyz;

    public zzdhw(String str) {
        this.zzgyz = str;
    }

    public final /* synthetic */ void zzs(Object obj) {
        try {
            JSONObject zzb = zzbao.zzb((JSONObject) obj, "pii");
            if (!TextUtils.isEmpty(this.zzgyz)) {
                zzb.put("attok", this.zzgyz);
            }
        } catch (JSONException e) {
            zzayp.zza("Failed putting attestation token.", e);
        }
    }
}
