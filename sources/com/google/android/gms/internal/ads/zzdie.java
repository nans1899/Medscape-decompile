package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdie implements Callable {
    private final zzdif zzgzd;

    zzdie(zzdif zzdif) {
        this.zzgzd = zzdif;
    }

    public final Object call() {
        zzdif zzdif = this.zzgzd;
        return new zzdic(zzdif.zzgze.zzf(zzdif.zzvr));
    }
}
