package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzczv implements Runnable {
    private final zzczt zzgtn;

    zzczv(zzczt zzczt) {
        this.zzgtn = zzczt;
    }

    public final void run() {
        this.zzgtn.zzgtj.zzgtk.onAdLoaded();
    }
}
