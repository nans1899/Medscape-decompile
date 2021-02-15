package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdzi extends zzdzf implements zzdzb, ScheduledExecutorService {
    private final ScheduledExecutorService zzhts;

    zzdzi(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        this.zzhts = (ScheduledExecutorService) zzdwd.checkNotNull(scheduledExecutorService);
    }

    public final /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzdzk zzdzk = new zzdzk(runnable);
        return new zzdzh(zzdzk, this.zzhts.scheduleWithFixedDelay(zzdzk, j, j2, timeUnit));
    }

    public final /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzdzk zzdzk = new zzdzk(runnable);
        return new zzdzh(zzdzk, this.zzhts.scheduleAtFixedRate(zzdzk, j, j2, timeUnit));
    }

    public final /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        zzdzq zzf = zzdzq.zzf(callable);
        return new zzdzh(zzf, this.zzhts.schedule(zzf, j, timeUnit));
    }

    public final /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        zzdzq zza = zzdzq.zza(runnable, null);
        return new zzdzh(zza, this.zzhts.schedule(zza, j, timeUnit));
    }
}
