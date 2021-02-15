package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbcv implements Runnable {
    private final int zzebs;
    private final zzbcs zzefz;

    zzbcv(zzbcs zzbcs, int i) {
        this.zzefz = zzbcs;
        this.zzebs = i;
    }

    public final void run() {
        this.zzefz.zzdk(this.zzebs);
    }
}
