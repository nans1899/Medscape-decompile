package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcoh implements Callable {
    private final zzcoi zzgjb;
    private final zzatc zzgjc;

    zzcoh(zzcoi zzcoi, zzatc zzatc) {
        this.zzgjb = zzcoi;
        this.zzgjc = zzatc;
    }

    public final Object call() {
        return this.zzgjb.zzf(this.zzgjc);
    }
}
