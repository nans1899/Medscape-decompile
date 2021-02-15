package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzblg implements Runnable {
    private final zzbld zzfmf;
    private final Runnable zzfmg;

    zzblg(zzbld zzbld, Runnable runnable) {
        this.zzfmf = zzbld;
        this.zzfmg = runnable;
    }

    public final void run() {
        zzbbz.zzeet.execute(new zzblf(this.zzfmf, this.zzfmg));
    }
}
