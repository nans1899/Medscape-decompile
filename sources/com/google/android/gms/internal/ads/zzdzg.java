package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzg implements Runnable {
    private final /* synthetic */ Runnable zzhtp;
    private final /* synthetic */ zzdzd zzhtq;

    zzdzg(zzdzd zzdzd, Runnable runnable) {
        this.zzhtq = zzdzd;
        this.zzhtp = runnable;
    }

    public final void run() {
        this.zzhtq.zzhtl = false;
        this.zzhtp.run();
    }

    public final String toString() {
        return this.zzhtp.toString();
    }
}
