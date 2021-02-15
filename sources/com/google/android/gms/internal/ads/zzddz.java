package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddz implements zzdgx<zzddw> {
    private ApplicationInfo applicationInfo;
    private zzayr zzdzr;
    private ScheduledExecutorService zzfmv;
    zzdok zzfsk;
    private zzams zzgwp;

    public zzddz(zzams zzams, ScheduledExecutorService scheduledExecutorService, zzayr zzayr, ApplicationInfo applicationInfo2, zzdok zzdok) {
        this.zzgwp = zzams;
        this.zzfmv = scheduledExecutorService;
        this.zzdzr = zzayr;
        this.applicationInfo = applicationInfo2;
        this.zzfsk = zzdok;
    }

    public final zzdzc<zzddw> zzarj() {
        zzdzc<V> zzdzc;
        if (!zzacl.zzdab.get().booleanValue()) {
            zzdzc = zzdyq.zzaf(null);
        } else if (!this.zzdzr.zzxi()) {
            zzdzc = zzdyq.zzaf(null);
        } else {
            zzdzc = zzdyq.zzb(zzdyq.zza(this.zzgwp.zza(this.applicationInfo), ((Long) zzwm.zzpx().zzd(zzabb.zzcsm)).longValue(), TimeUnit.MILLISECONDS, this.zzfmv), Throwable.class, zzdea.zzboo, zzbbz.zzeeu);
        }
        return zzdyq.zzb(zzdyq.zzb(zzdzc, new zzdeb(this), (Executor) this.zzfmv), zzddy.zzdwz, (Executor) zzbbz.zzeep);
    }
}
