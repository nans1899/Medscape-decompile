package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdyv<V> {
    private final boolean zzhsj;
    private final zzdwp<zzdzc<? extends V>> zzhtg;

    private zzdyv(boolean z, zzdwp<zzdzc<? extends V>> zzdwp) {
        this.zzhsj = z;
        this.zzhtg = zzdwp;
    }

    public final <C> zzdzc<C> zza(Callable<C> callable, Executor executor) {
        return new zzdye(this.zzhtg, this.zzhsj, executor, callable);
    }

    /* synthetic */ zzdyv(boolean z, zzdwp zzdwp, zzdyt zzdyt) {
        this(z, zzdwp);
    }
}
