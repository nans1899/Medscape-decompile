package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaxc implements ThreadFactory {
    private final AtomicInteger zzzm = new AtomicInteger(1);

    zzaxc(zzawo zzawo) {
    }

    public final Thread newThread(Runnable runnable) {
        int andIncrement = this.zzzm.getAndIncrement();
        StringBuilder sb = new StringBuilder(42);
        sb.append("AdWorker(SCION_TASK_EXECUTOR) #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
