package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzq<V> extends zzdyk<V> implements RunnableFuture<V> {
    private volatile zzdyy<?> zzhtz;

    static <V> zzdzq<V> zzf(Callable<V> callable) {
        return new zzdzq<>(callable);
    }

    static <V> zzdzq<V> zza(Runnable runnable, @NullableDecl V v) {
        return new zzdzq<>(Executors.callable(runnable, v));
    }

    private zzdzq(Callable<V> callable) {
        this.zzhtz = new zzdzs(this, callable);
    }

    zzdzq(zzdyb<V> zzdyb) {
        this.zzhtz = new zzdzp(this, zzdyb);
    }

    public final void run() {
        zzdyy<?> zzdyy = this.zzhtz;
        if (zzdyy != null) {
            zzdyy.run();
        }
        this.zzhtz = null;
    }

    /* access modifiers changed from: protected */
    public final void afterDone() {
        zzdyy<?> zzdyy;
        super.afterDone();
        if (wasInterrupted() && (zzdyy = this.zzhtz) != null) {
            zzdyy.interruptTask();
        }
        this.zzhtz = null;
    }

    /* access modifiers changed from: protected */
    public final String pendingToString() {
        zzdyy<?> zzdyy = this.zzhtz;
        if (zzdyy == null) {
            return super.pendingToString();
        }
        String valueOf = String.valueOf(zzdyy);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 7);
        sb.append("task=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
