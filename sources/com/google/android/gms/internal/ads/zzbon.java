package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbon implements zzeoy<zzbwk> {
    private final zzeph<Clock> zzfok;
    private final zzeph<ScheduledExecutorService> zzfpy;

    public zzbon(zzeph<ScheduledExecutorService> zzeph, zzeph<Clock> zzeph2) {
        this.zzfpy = zzeph;
        this.zzfok = zzeph2;
    }

    public static zzbwk zza(ScheduledExecutorService scheduledExecutorService, Clock clock) {
        return (zzbwk) zzepe.zza(new zzbwk(scheduledExecutorService, clock), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfpy.get(), this.zzfok.get());
    }
}
