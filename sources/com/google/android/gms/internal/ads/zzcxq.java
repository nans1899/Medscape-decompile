package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcxq implements Runnable {
    private final zzdnv zzfqn;
    private final zzdog zzgcx;
    private final zzcxn zzgqw;
    private final zzctc zzgqx;

    zzcxq(zzcxn zzcxn, zzdog zzdog, zzdnv zzdnv, zzctc zzctc) {
        this.zzgqw = zzcxn;
        this.zzgcx = zzdog;
        this.zzfqn = zzdnv;
        this.zzgqx = zzctc;
    }

    public final void run() {
        zzcxn zzcxn = this.zzgqw;
        zzcxl.zzc(this.zzgcx, this.zzfqn, this.zzgqx);
    }
}
