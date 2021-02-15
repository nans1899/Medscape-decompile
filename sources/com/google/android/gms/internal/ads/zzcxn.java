package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcxn implements zzcas {
    private final /* synthetic */ zzdnv zzgqc;
    private final /* synthetic */ zzdog zzgqp;
    private final /* synthetic */ zzctc zzgqq;
    final /* synthetic */ zzcxl zzgqr;

    zzcxn(zzcxl zzcxl, zzdog zzdog, zzdnv zzdnv, zzctc zzctc) {
        this.zzgqr = zzcxl;
        this.zzgqp = zzdog;
        this.zzgqc = zzdnv;
        this.zzgqq = zzctc;
    }

    public final void onInitializationSucceeded() {
        this.zzgqr.zzfoc.execute(new zzcxq(this, this.zzgqp, this.zzgqc, this.zzgqq));
    }

    public final void zzdx(int i) {
        String valueOf = String.valueOf(this.zzgqq.zzcib);
        zzayp.zzfe(valueOf.length() != 0 ? "Fail to initialize adapter ".concat(valueOf) : new String("Fail to initialize adapter "));
    }
}
