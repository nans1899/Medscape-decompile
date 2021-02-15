package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzayk implements Runnable {
    private final /* synthetic */ zzayl zzead;

    zzayk(zzayl zzayl) {
        this.zzead = zzayl;
    }

    public final void run() {
        Thread unused = this.zzead.thread = Thread.currentThread();
        this.zzead.zzvb();
    }
}
