package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdyw;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdyq extends zzdyx {
    public static <V> zzdzc<V> zzaf(@NullableDecl V v) {
        if (v == null) {
            return zzdyw.zzhth;
        }
        return new zzdyw(v);
    }

    public static <V> zzdzc<V> immediateFailedFuture(Throwable th) {
        zzdwd.checkNotNull(th);
        return new zzdyw.zza(th);
    }

    public static <O> zzdzc<O> zza(zzdyb<O> zzdyb, Executor executor) {
        zzdzq zzdzq = new zzdzq(zzdyb);
        executor.execute(zzdzq);
        return zzdzq;
    }

    public static <V, X extends Throwable> zzdzc<V> zzb(zzdzc<? extends V> zzdzc, Class<X> cls, zzdya<? super X, ? extends V> zzdya, Executor executor) {
        return zzdxm.zza(zzdzc, cls, zzdya, executor);
    }

    public static <V> zzdzc<V> zza(zzdzc<V> zzdzc, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        if (zzdzc.isDone()) {
            return zzdzc;
        }
        return zzdzm.zzb(zzdzc, j, timeUnit, scheduledExecutorService);
    }

    public static <I, O> zzdzc<O> zzb(zzdzc<I> zzdzc, zzdya<? super I, ? extends O> zzdya, Executor executor) {
        return zzdxt.zza(zzdzc, zzdya, executor);
    }

    public static <I, O> zzdzc<O> zzb(zzdzc<I> zzdzc, zzdvu<? super I, ? extends O> zzdvu, Executor executor) {
        return zzdxt.zza(zzdzc, zzdvu, executor);
    }

    public static <V> zzdzc<List<V>> zzi(Iterable<? extends zzdzc<? extends V>> iterable) {
        return new zzdyc(zzdwp.zzh(iterable), true);
    }

    @SafeVarargs
    public static <V> zzdyv<V> zza(zzdzc<? extends V>... zzdzcArr) {
        return new zzdyv<>(false, zzdwp.zzb((E[]) zzdzcArr), (zzdyt) null);
    }

    public static <V> zzdyv<V> zzj(Iterable<? extends zzdzc<? extends V>> iterable) {
        return new zzdyv<>(false, zzdwp.zzh(iterable), (zzdyt) null);
    }

    @SafeVarargs
    public static <V> zzdyv<V> zzb(zzdzc<? extends V>... zzdzcArr) {
        return new zzdyv<>(true, zzdwp.zzb((E[]) zzdzcArr), (zzdyt) null);
    }

    public static <V> zzdyv<V> zzk(Iterable<? extends zzdzc<? extends V>> iterable) {
        return new zzdyv<>(true, zzdwp.zzh(iterable), (zzdyt) null);
    }

    public static <V> void zza(zzdzc<V> zzdzc, zzdyr<? super V> zzdyr, Executor executor) {
        zzdwd.checkNotNull(zzdyr);
        zzdzc.addListener(new zzdys(zzdzc, zzdyr), executor);
    }

    public static <V> V zza(Future<V> future) throws ExecutionException {
        if (future.isDone()) {
            return zzdzu.getUninterruptibly(future);
        }
        throw new IllegalStateException(zzdwf.zzb("Future was expected to be done: %s", future));
    }

    public static <V> V zzb(Future<V> future) {
        zzdwd.checkNotNull(future);
        try {
            return zzdzu.getUninterruptibly(future);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Error) {
                throw new zzdyi((Error) cause);
            }
            throw new zzdzr(cause);
        }
    }
}
