package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhz implements zzdgx<zzdhw> {
    private final Executor executor;
    private final zzayb zzbpw;
    private final ScheduledExecutorService zzfmv;
    private final int zzgls;
    private final zzaxx zzgyr;
    private final Context zzvr;

    public zzdhz(zzaxx zzaxx, int i, Context context, zzayb zzayb, ScheduledExecutorService scheduledExecutorService, Executor executor2) {
        this.zzgyr = zzaxx;
        this.zzgls = i;
        this.zzvr = context;
        this.zzbpw = zzayb;
        this.zzfmv = scheduledExecutorService;
        this.executor = executor2;
    }

    public final zzdzc<zzdhw> zzarj() {
        return zzdyl.zzg(zzdyq.zza(new zzdhy(this), this.executor)).zza(zzdib.zzdwz, this.executor).zza(((Long) zzwm.zzpx().zzd(zzabb.zzcor)).longValue(), TimeUnit.MILLISECONDS, this.zzfmv).zza(Exception.class, new zzdia(this), zzdze.zzayh());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdhw zzb(Exception exc) {
        this.zzbpw.zza(exc, "AttestationTokenSignal");
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzase() throws Exception {
        return this.zzgyr.zza(this.zzvr, this.zzgls);
    }
}
