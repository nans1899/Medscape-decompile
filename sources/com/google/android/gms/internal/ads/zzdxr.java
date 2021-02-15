package com.google.android.gms.internal.ads;

import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzdxr extends AbstractExecutorService implements zzdzb {
    /* access modifiers changed from: protected */
    public final <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return zzdzq.zza(runnable, t);
    }

    /* access modifiers changed from: protected */
    public final <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return zzdzq.zzf(callable);
    }

    /* renamed from: zzg */
    public final zzdzc<?> submit(Runnable runnable) {
        return (zzdzc) super.submit(runnable);
    }

    /* renamed from: zze */
    public final <T> zzdzc<T> submit(Callable<T> callable) {
        return (zzdzc) super.submit(callable);
    }

    public /* synthetic */ Future submit(Runnable runnable, @NullableDecl Object obj) {
        return (zzdzc) super.submit(runnable, obj);
    }
}
