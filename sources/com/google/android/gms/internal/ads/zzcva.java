package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcva implements zzdvu {
    private final zzdnv zzfqn;
    private final zzbgj zzgci;
    private final zzcuw zzgpe;
    private final zzcbg zzgpf;

    zzcva(zzcuw zzcuw, zzbgj zzbgj, zzdnv zzdnv, zzcbg zzcbg) {
        this.zzgpe = zzcuw;
        this.zzgci = zzbgj;
        this.zzfqn = zzdnv;
        this.zzgpf = zzcbg;
    }

    public final Object apply(Object obj) {
        zzbgj zzbgj = this.zzgci;
        zzdnv zzdnv = this.zzfqn;
        zzcbg zzcbg = this.zzgpf;
        if (zzdnv.zzduj) {
            zzbgj.zzacj();
        }
        zzbgj.zzabq();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcoc)).booleanValue()) {
            zzp.zzkt();
            zzazd.zza(zzbgj);
        }
        return zzcbg.zzagj();
    }
}
