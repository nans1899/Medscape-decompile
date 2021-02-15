package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcin implements zzdya {
    private final String zzdgm;
    private final zzcil zzgek;
    private final JSONObject zzgel;

    zzcin(zzcil zzcil, String str, JSONObject jSONObject) {
        this.zzgek = zzcil;
        this.zzdgm = str;
        this.zzgel = jSONObject;
    }

    public final zzdzc zzf(Object obj) {
        return this.zzgek.zza(this.zzdgm, this.zzgel, (zzbgj) obj);
    }
}
