package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdam implements Runnable {
    private final zzdaj zzgub;

    zzdam(zzdaj zzdaj) {
        this.zzgub = zzdaj;
    }

    public final void run() {
        this.zzgub.zzgua.zzgtw.zzaqz().onAdLoaded();
    }
}
