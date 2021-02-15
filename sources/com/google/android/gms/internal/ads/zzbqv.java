package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqv {
    private final Executor executor;
    private volatile boolean zzafc = true;
    private final ScheduledExecutorService zzfmv;
    private final zzdzc<zzbqu> zzfry;

    public zzbqv(Executor executor2, ScheduledExecutorService scheduledExecutorService, zzdzc<zzbqu> zzdzc) {
        this.executor = executor2;
        this.zzfmv = scheduledExecutorService;
        this.zzfry = zzdzc;
    }

    public final void zza(zzdyr<zzbqo> zzdyr) {
        zzdyq.zza(this.zzfry, new zzbrc(this, zzdyr), this.executor);
    }

    /* access modifiers changed from: private */
    public final void zza(List<? extends zzdzc<? extends zzbqo>> list, zzdyr<zzbqo> zzdyr) {
        if (list == null || list.isEmpty()) {
            this.executor.execute(new zzbqy(zzdyr));
            return;
        }
        zzdzc<O> zzaf = zzdyq.zzaf(null);
        for (zzdzc zzbra : list) {
            zzaf = zzdyq.zzb(zzdyq.zzb(zzaf, Throwable.class, new zzbqx(zzdyr), this.executor), new zzbra(this, zzdyr, zzbra), this.executor);
        }
        zzdyq.zza(zzaf, new zzbrb(this, zzdyr), this.executor);
    }

    public final boolean isLoading() {
        return this.zzafc;
    }

    /* access modifiers changed from: private */
    public final void zzaja() {
        zzbbz.zzeet.execute(new zzbqz(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzajb() {
        this.zzafc = false;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdyr zzdyr, zzdzc zzdzc, zzbqo zzbqo) throws Exception {
        if (zzbqo != null) {
            zzdyr.onSuccess(zzbqo);
        }
        return zzdyq.zza(zzdzc, zzadf.zzdcp.get().longValue(), TimeUnit.MILLISECONDS, this.zzfmv);
    }
}
