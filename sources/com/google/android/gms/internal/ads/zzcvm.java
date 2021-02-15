package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvm implements zzctb<zzdpa, zzcuu> {
    private final zzcku zzgij;

    public zzcvm(zzcku zzcku) {
        this.zzgij = zzcku;
    }

    public final zzctc<zzdpa, zzcuu> zzf(String str, JSONObject jSONObject) throws zzdos {
        zzdpa zzd = this.zzgij.zzd(str, jSONObject);
        if (zzd == null) {
            return null;
        }
        return new zzctc<>(zzd, new zzcuu(), str);
    }
}
