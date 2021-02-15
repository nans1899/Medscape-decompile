package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdir implements Callable {
    private final zzdio zzgzl;

    zzdir(zzdio zzdio) {
        this.zzgzl = zzdio;
    }

    public final Object call() {
        zzdio zzdio = this.zzgzl;
        return new zzdip(zzdio.zzgzk.zzf(zzdio.zzdrc));
    }
}
