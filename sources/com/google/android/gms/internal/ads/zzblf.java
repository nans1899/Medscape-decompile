package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzblf implements Runnable {
    private final zzbld zzfmf;
    private final Runnable zzfmg;

    zzblf(zzbld zzbld, Runnable runnable) {
        this.zzfmf = zzbld;
        this.zzfmg = runnable;
    }

    public final void run() {
        this.zzfmf.zzd(this.zzfmg);
    }
}
