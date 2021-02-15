package com.google.android.gms.internal.ads;

import org.json.JSONArray;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcvq implements zzdya {
    private final zzdnv zzfqn;
    private final zzdog zzgcx;
    private final zzcvo zzgpi;

    zzcvq(zzcvo zzcvo, zzdog zzdog, zzdnv zzdnv) {
        this.zzgpi = zzcvo;
        this.zzgcx = zzdog;
        this.zzfqn = zzdnv;
    }

    public final zzdzc zzf(Object obj) {
        return this.zzgpi.zza(this.zzgcx, this.zzfqn, (JSONArray) obj);
    }
}
