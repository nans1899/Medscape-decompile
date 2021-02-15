package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcra implements Callable {
    private final zzdzc zzftx;
    private final zzdzc zzgdq;

    zzcra(zzdzc zzdzc, zzdzc zzdzc2) {
        this.zzgdq = zzdzc;
        this.zzftx = zzdzc2;
    }

    public final Object call() {
        zzdzc zzdzc = this.zzgdq;
        zzdzc zzdzc2 = this.zzftx;
        return new zzcrj((zzcrq) zzdzc.get(), ((zzcrk) zzdzc2.get()).zzglk, ((zzcrk) zzdzc2.get()).zzgll);
    }
}
