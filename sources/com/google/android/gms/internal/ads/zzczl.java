package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzczl implements Runnable {
    private final zzczs zzgsz;

    private zzczl(zzczs zzczs) {
        this.zzgsz = zzczs;
    }

    static Runnable zzb(zzczs zzczs) {
        return new zzczl(zzczs);
    }

    public final void run() {
        this.zzgsz.onAdLoaded();
    }
}
