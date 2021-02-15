package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
class zzdzf extends zzdxr {
    private final ExecutorService zzhto;

    zzdzf(ExecutorService executorService) {
        this.zzhto = (ExecutorService) zzdwd.checkNotNull(executorService);
    }

    public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.zzhto.awaitTermination(j, timeUnit);
    }

    public final boolean isShutdown() {
        return this.zzhto.isShutdown();
    }

    public final boolean isTerminated() {
        return this.zzhto.isTerminated();
    }

    public final void shutdown() {
        this.zzhto.shutdown();
    }

    public final List<Runnable> shutdownNow() {
        return this.zzhto.shutdownNow();
    }

    public final void execute(Runnable runnable) {
        this.zzhto.execute(runnable);
    }
}
