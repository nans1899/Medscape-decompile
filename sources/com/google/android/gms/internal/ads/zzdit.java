package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdit implements zzdgx<zzdiq> {
    private ApplicationInfo applicationInfo;
    private boolean zzdtz;
    private ScheduledExecutorService zzfmv;
    private zzams zzgwp;

    public zzdit(zzams zzams, ScheduledExecutorService scheduledExecutorService, boolean z, ApplicationInfo applicationInfo2) {
        this.zzgwp = zzams;
        this.zzfmv = scheduledExecutorService;
        this.zzdtz = z;
        this.applicationInfo = applicationInfo2;
    }

    public final zzdzc<zzdiq> zzarj() {
        if (!zzacl.zzdac.get().booleanValue()) {
            return zzdyq.immediateFailedFuture(new Exception("Auto Collect Location by gms is disabled."));
        }
        if (!this.zzdtz) {
            return zzdyq.immediateFailedFuture(new Exception("Auto Collect Location is false."));
        }
        return zzdyq.zzb(zzdyq.zza(this.zzgwp.zza(this.applicationInfo), ((Long) zzwm.zzpx().zzd(zzabb.zzcsm)).longValue(), TimeUnit.MILLISECONDS, this.zzfmv), zzdis.zzdwz, (Executor) zzbbz.zzeep);
    }
}
