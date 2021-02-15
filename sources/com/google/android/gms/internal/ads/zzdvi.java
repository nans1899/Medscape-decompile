package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzdvi implements zzdvd {
    private zzdvi() {
    }

    public final ExecutorService zzej(int i) {
        return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
    }

    public final ExecutorService zza(ThreadFactory threadFactory, int i) {
        return Executors.unconfigurableExecutorService(Executors.newCachedThreadPool(threadFactory));
    }

    public final ExecutorService zza(int i, ThreadFactory threadFactory, int i2) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor);
    }

    public final ExecutorService zzek(int i) {
        return zza(1, Executors.defaultThreadFactory(), i);
    }

    public final ExecutorService zzb(ThreadFactory threadFactory, int i) {
        return zza(1, threadFactory, i);
    }

    public final ScheduledExecutorService zzb(int i, ThreadFactory threadFactory, int i2) {
        return Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, threadFactory));
    }
}
