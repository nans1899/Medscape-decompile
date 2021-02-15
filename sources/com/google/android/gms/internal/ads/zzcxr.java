package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcxr implements zzctb<zzaph, zzcuu> {
    private final zzcyy zzgqy;

    public zzcxr(zzcyy zzcyy) {
        this.zzgqy = zzcyy;
    }

    public final zzctc<zzaph, zzcuu> zzf(String str, JSONObject jSONObject) throws zzdos {
        zzaph zzgp = this.zzgqy.zzgp(str);
        if (zzgp == null) {
            return null;
        }
        return new zzctc<>(zzgp, new zzcuu(), str);
    }
}
