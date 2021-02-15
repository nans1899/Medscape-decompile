package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdyn<V> extends zzdyl<V> {
    private final zzdzc<V> zzhtd;

    zzdyn(zzdzc<V> zzdzc) {
        this.zzhtd = (zzdzc) zzdwd.checkNotNull(zzdzc);
    }

    public final void addListener(Runnable runnable, Executor executor) {
        this.zzhtd.addListener(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        return this.zzhtd.cancel(z);
    }

    public final boolean isCancelled() {
        return this.zzhtd.isCancelled();
    }

    public final boolean isDone() {
        return this.zzhtd.isDone();
    }

    public final V get() throws InterruptedException, ExecutionException {
        return this.zzhtd.get();
    }

    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.zzhtd.get(j, timeUnit);
    }

    public final String toString() {
        return this.zzhtd.toString();
    }
}
