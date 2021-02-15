package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzdyl<V> extends zzdyu<V> {
    zzdyl() {
    }

    public static <V> zzdyl<V> zzg(zzdzc<V> zzdzc) {
        if (zzdzc instanceof zzdyl) {
            return (zzdyl) zzdzc;
        }
        return new zzdyn(zzdzc);
    }

    public final <X extends Throwable> zzdyl<V> zza(Class<X> cls, zzdvu<? super X, ? extends V> zzdvu, Executor executor) {
        zzdxo zzdxo = new zzdxo(this, cls, zzdvu);
        addListener(zzdxo, zzdze.zza(executor, zzdxo));
        return zzdxo;
    }

    public final <X extends Throwable> zzdyl<V> zza(Class<X> cls, zzdya<? super X, ? extends V> zzdya, Executor executor) {
        zzdxp zzdxp = new zzdxp(this, cls, zzdya);
        addListener(zzdxp, zzdze.zza(executor, zzdxp));
        return zzdxp;
    }

    public final zzdyl<V> zza(long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return (zzdyl) zzdyq.zza(this, j, timeUnit, scheduledExecutorService);
    }

    public final <T> zzdyl<T> zzb(zzdya<? super V, T> zzdya, Executor executor) {
        zzdwd.checkNotNull(executor);
        zzdxs zzdxs = new zzdxs(this, zzdya);
        addListener(zzdxs, zzdze.zza(executor, zzdxs));
        return zzdxs;
    }

    public final <T> zzdyl<T> zza(zzdvu<? super V, T> zzdvu, Executor executor) {
        zzdwd.checkNotNull(zzdvu);
        zzdxv zzdxv = new zzdxv(this, zzdvu);
        addListener(zzdxv, zzdze.zza(executor, zzdxv));
        return zzdxv;
    }
}
