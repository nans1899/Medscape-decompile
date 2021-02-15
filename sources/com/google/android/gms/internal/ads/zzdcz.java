package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcz implements zzdgx<zzdcw> {
    private final Executor executor;
    private final ScheduledExecutorService zzfmv;
    private final Context zzvr;

    public zzdcz(Context context, ScheduledExecutorService scheduledExecutorService, Executor executor2) {
        this.zzvr = context;
        this.zzfmv = scheduledExecutorService;
        this.executor = executor2;
    }

    public final zzdzc<zzdcw> zzarj() {
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwp)).booleanValue()) {
            return zzdyq.zzaf(null);
        }
        zzbcg zzbcg = new zzbcg();
        try {
            new zzdcy(zzbcg).zzbm(false);
        } catch (Throwable unused) {
            zzayp.zzfc("ArCoreApk is not ready.");
            zzbcg.set(false);
        }
        return zzdyq.zzb(zzdyq.zzb(zzdyq.zza(zzbcg, 200, TimeUnit.MILLISECONDS, this.zzfmv), new zzddb(this), this.executor), Throwable.class, zzdda.zzboo, this.executor);
    }
}
