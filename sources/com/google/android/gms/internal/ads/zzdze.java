package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdze {
    public static Executor zzayh() {
        return zzdyj.INSTANCE;
    }

    public static zzdzb zza(ExecutorService executorService) {
        if (executorService instanceof zzdzb) {
            return (zzdzb) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            return new zzdzi((ScheduledExecutorService) executorService);
        }
        return new zzdzf(executorService);
    }

    static Executor zza(Executor executor, zzdxq<?> zzdxq) {
        zzdwd.checkNotNull(executor);
        zzdwd.checkNotNull(zzdxq);
        if (executor == zzdyj.INSTANCE) {
            return executor;
        }
        return new zzdzd(executor, zzdxq);
    }
}
