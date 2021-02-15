package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhl implements zzdgx<zzdhi> {
    private final Executor executor;
    private final ScheduledExecutorService zzfmv;
    private final zzaxx zzgyr;
    private final Context zzvr;

    public zzdhl(zzaxx zzaxx, Context context, ScheduledExecutorService scheduledExecutorService, Executor executor2) {
        this.zzgyr = zzaxx;
        this.zzvr = context;
        this.zzfmv = scheduledExecutorService;
        this.executor = executor2;
    }

    public final zzdzc<zzdhi> zzarj() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcoq)).booleanValue()) {
            return zzdyq.immediateFailedFuture(new Exception("Did not ad Ad ID into query param."));
        }
        return zzdyl.zzg(this.zzgyr.zzal(this.zzvr)).zza(zzdhk.zzdwz, this.executor).zza(((Long) zzwm.zzpx().zzd(zzabb.zzcor)).longValue(), TimeUnit.MILLISECONDS, this.zzfmv).zza(Throwable.class, new zzdhn(this), this.executor);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdhi zzf(Throwable th) {
        zzwm.zzpt();
        return new zzdhi((AdvertisingIdClient.Info) null, zzbbg.zzbo(this.zzvr));
    }
}
