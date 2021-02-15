package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbeg implements Runnable {
    private final int zzebs;
    private final int zzebt;
    private final zzbeb zzejq;

    zzbeg(zzbeb zzbeb, int i, int i2) {
        this.zzejq = zzbeb;
        this.zzebs = i;
        this.zzebt = i2;
    }

    public final void run() {
        this.zzejq.zzp(this.zzebs, this.zzebt);
    }
}
