package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzctl implements zzdvu {
    private final zzdnv zzfqn;
    private final zzbgj zzgci;
    private final zzcth zzgny;
    private final zzbnx zzgoc;

    zzctl(zzcth zzcth, zzbgj zzbgj, zzdnv zzdnv, zzbnx zzbnx) {
        this.zzgny = zzcth;
        this.zzgci = zzbgj;
        this.zzfqn = zzdnv;
        this.zzgoc = zzbnx;
    }

    public final Object apply(Object obj) {
        zzbgj zzbgj = this.zzgci;
        zzdnv zzdnv = this.zzfqn;
        zzbnx zzbnx = this.zzgoc;
        if (zzdnv.zzduj) {
            zzbgj.zzacj();
        }
        zzbgj.zzabq();
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcoc)).booleanValue()) {
            zzp.zzkt();
            zzazd.zza(zzbgj);
        }
        return zzbnx.zzafp();
    }
}
