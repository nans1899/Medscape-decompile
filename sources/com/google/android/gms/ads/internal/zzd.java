package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzdya;
import com.google.android.gms.internal.ads.zzdyq;
import com.google.android.gms.internal.ads.zzdzc;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzd implements zzdya {
    static final zzdya zzboo = new zzd();

    private zzd() {
    }

    public final zzdzc zzf(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("isSuccessful", false)) {
            zzp.zzkv().zzwt().zzel(jSONObject.getString("appSettingsJson"));
        }
        return zzdyq.zzaf(null);
    }
}
