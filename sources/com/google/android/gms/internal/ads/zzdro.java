package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdro implements zzeoy<ScheduledExecutorService> {
    private final zzeph<ThreadFactory> zzhjv;

    public zzdro(zzeph<ThreadFactory> zzeph) {
        this.zzhjv = zzeph;
    }

    public final /* synthetic */ Object get() {
        return (ScheduledExecutorService) zzepe.zza(zzdvg.zzaxc().zzb(1, this.zzhjv.get(), zzdvl.zzhov), "Cannot return null from a non-@Nullable @Provides method");
    }
}
