package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzdym<V> extends zzdwi implements Future<V> {
    protected zzdym() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzayf */
    public abstract Future<? extends V> zzaxh();

    public boolean cancel(boolean z) {
        return zzaxh().cancel(z);
    }

    public boolean isCancelled() {
        return zzaxh().isCancelled();
    }

    public boolean isDone() {
        return zzaxh().isDone();
    }

    public V get() throws InterruptedException, ExecutionException {
        return zzaxh().get();
    }

    public V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return zzaxh().get(j, timeUnit);
    }
}
