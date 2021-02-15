package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbtl implements Callable {
    private final zzbtm zzftw;
    private final zzdzc zzftx;

    zzbtl(zzbtm zzbtm, zzdzc zzdzc) {
        this.zzftw = zzbtm;
        this.zzftx = zzdzc;
    }

    public final Object call() {
        return this.zzftw.zzc(this.zzftx);
    }
}
