package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzczo implements Runnable {
    private final zzczm zzgtd;
    private final zzva zzgte;

    zzczo(zzczm zzczm, zzva zzva) {
        this.zzgtd = zzczm;
        this.zzgte = zzva;
    }

    public final void run() {
        zzczm zzczm = this.zzgtd;
        zzczm.zzgtb.zzgsq.zzk(this.zzgte);
    }
}
