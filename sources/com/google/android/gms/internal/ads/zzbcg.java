package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbcg<T> implements zzdzc<T> {
    private final zzdzj<T> zzeez = zzdzj.zzayi();

    public final boolean set(T t) {
        return zzas(this.zzeez.set(t));
    }

    public final boolean setException(Throwable th) {
        return zzas(this.zzeez.setException(th));
    }

    private static boolean zzas(boolean z) {
        if (!z) {
            zzp.zzkv().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture");
        }
        return z;
    }

    public void addListener(Runnable runnable, Executor executor) {
        this.zzeez.addListener(runnable, executor);
    }

    public boolean cancel(boolean z) {
        return this.zzeez.cancel(z);
    }

    public boolean isCancelled() {
        return this.zzeez.isCancelled();
    }

    public boolean isDone() {
        return this.zzeez.isDone();
    }

    public T get() throws ExecutionException, InterruptedException {
        return this.zzeez.get();
    }

    public T get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.zzeez.get(j, timeUnit);
    }
}
