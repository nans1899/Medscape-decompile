package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbpy implements Runnable {
    private final Runnable zzfmg;
    private final zzbpw zzfrb;

    zzbpy(zzbpw zzbpw, Runnable runnable) {
        this.zzfrb = zzbpw;
        this.zzfmg = runnable;
    }

    public final void run() {
        this.zzfrb.zze(this.zzfmg);
    }
}
