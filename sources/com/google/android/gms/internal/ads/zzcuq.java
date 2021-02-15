package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcuq implements Callable {
    private final zzdnv zzfqn;
    private final zzdog zzgcx;
    private final zzcun zzgoy;

    zzcuq(zzcun zzcun, zzdog zzdog, zzdnv zzdnv) {
        this.zzgoy = zzcun;
        this.zzgcx = zzdog;
        this.zzfqn = zzdnv;
    }

    public final Object call() {
        return this.zzgoy.zzc(this.zzgcx, this.zzfqn);
    }
}
