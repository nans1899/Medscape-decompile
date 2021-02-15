package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcup implements Runnable {
    private final zzdnv zzfqn;
    private final zzdog zzgcx;
    private final zzcun zzgoy;

    zzcup(zzcun zzcun, zzdog zzdog, zzdnv zzdnv) {
        this.zzgoy = zzcun;
        this.zzgcx = zzdog;
        this.zzfqn = zzdnv;
    }

    public final void run() {
        this.zzgoy.zzd(this.zzgcx, this.zzfqn);
    }
}
