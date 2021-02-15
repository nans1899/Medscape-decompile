package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbei implements Runnable {
    private final int zzebs;
    private final zzbeb zzejq;

    zzbei(zzbeb zzbeb, int i) {
        this.zzejq = zzbeb;
        this.zzebs = i;
    }

    public final void run() {
        this.zzejq.zzdr(this.zzebs);
    }
}
