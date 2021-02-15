package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcxf implements zzdvu {
    private final zzdnv zzfqn;
    private final zzbgj zzgci;
    private final zzcxb zzgqm;
    private final zzcji zzgqn;

    zzcxf(zzcxb zzcxb, zzbgj zzbgj, zzdnv zzdnv, zzcji zzcji) {
        this.zzgqm = zzcxb;
        this.zzgci = zzbgj;
        this.zzfqn = zzdnv;
        this.zzgqn = zzcji;
    }

    public final Object apply(Object obj) {
        zzbgj zzbgj = this.zzgci;
        zzdnv zzdnv = this.zzfqn;
        zzcji zzcji = this.zzgqn;
        if (zzdnv.zzduj) {
            zzbgj.zzacj();
        }
        zzbgj.zzabq();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcoc)).booleanValue()) {
            zzp.zzkt();
            zzazd.zza(zzbgj);
        }
        return zzcji.zzago();
    }
}
