package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzsv implements Runnable {
    private final /* synthetic */ zzsw zzbuz;

    zzsv(zzsw zzsw) {
        this.zzbuz = zzsw;
    }

    public final void run() {
        this.zzbuz.disconnect();
    }
}
