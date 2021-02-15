package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgw implements Runnable {
    private final /* synthetic */ zzbgu zzeoi;

    zzbgw(zzbgu zzbgu) {
        this.zzeoi = zzbgu;
    }

    public final void run() {
        this.zzeoi.zzeof.destroy();
    }
}
