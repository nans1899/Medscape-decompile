package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdgu;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfi<S extends zzdgu<?>> implements zzdgx<S> {
    private final ScheduledExecutorService zzfoz;
    private final zzdgx<S> zzgwj;
    private final long zzgxd;

    public zzdfi(zzdgx<S> zzdgx, long j, ScheduledExecutorService scheduledExecutorService) {
        this.zzgwj = zzdgx;
        this.zzgxd = j;
        this.zzfoz = scheduledExecutorService;
    }

    public final zzdzc<S> zzarj() {
        zzdzc<S> zzarj = this.zzgwj.zzarj();
        long j = this.zzgxd;
        if (j > 0) {
            zzarj = zzdyq.zza(zzarj, j, TimeUnit.MILLISECONDS, this.zzfoz);
        }
        return zzdyq.zzb(zzarj, Throwable.class, zzdfl.zzboo, zzbbz.zzeeu);
    }
}
