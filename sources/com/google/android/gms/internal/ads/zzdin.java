package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdin implements Callable {
    private final zzdzc zzftx;
    private final zzdzc zzgdq;

    zzdin(zzdzc zzdzc, zzdzc zzdzc2) {
        this.zzgdq = zzdzc;
        this.zzftx = zzdzc2;
    }

    public final Object call() {
        return new zzdil((String) this.zzgdq.get(), (String) this.zzftx.get());
    }
}
