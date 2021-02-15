package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdrr<E, V> implements zzdzc<V> {
    private final E zzhjx;
    private final String zzhjy;
    private final zzdzc<V> zzhjz;

    public zzdrr(E e, String str, zzdzc<V> zzdzc) {
        this.zzhjx = e;
        this.zzhjy = str;
        this.zzhjz = zzdzc;
    }

    public final void addListener(Runnable runnable, Executor executor) {
        this.zzhjz.addListener(runnable, executor);
    }

    public final boolean cancel(boolean z) {
        return this.zzhjz.cancel(z);
    }

    public final V get() throws InterruptedException, ExecutionException {
        return this.zzhjz.get();
    }

    public final V get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.zzhjz.get(j, timeUnit);
    }

    public final boolean isCancelled() {
        return this.zzhjz.isCancelled();
    }

    public final boolean isDone() {
        return this.zzhjz.isDone();
    }

    public final E zzavp() {
        return this.zzhjx;
    }

    public final String zzavq() {
        return this.zzhjy;
    }

    public final String toString() {
        String str = this.zzhjy;
        int identityHashCode = System.identityHashCode(this);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append(str);
        sb.append("@");
        sb.append(identityHashCode);
        return sb.toString();
    }
}
