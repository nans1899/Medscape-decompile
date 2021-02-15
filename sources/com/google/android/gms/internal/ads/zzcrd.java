package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcrd implements Callable {
    private final zzdzc zzfsc;
    private final zzdzc zzftx;
    private final zzcqv zzglg;

    zzcrd(zzcqv zzcqv, zzdzc zzdzc, zzdzc zzdzc2) {
        this.zzglg = zzcqv;
        this.zzftx = zzdzc;
        this.zzfsc = zzdzc2;
    }

    public final Object call() {
        return this.zzglg.zza(this.zzftx, this.zzfsc);
    }
}
