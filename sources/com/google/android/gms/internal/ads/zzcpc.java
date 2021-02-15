package com.google.android.gms.internal.ads;

import android.os.Binder;
import com.google.android.gms.ads.internal.zzp;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpc {
    private final ScheduledExecutorService zzfmv;
    private final zzdzb zzgcz;
    private final zzeos<zzcqv> zzgjf;
    private final zzcpx zzgjq;

    public zzcpc(ScheduledExecutorService scheduledExecutorService, zzdzb zzdzb, zzcpx zzcpx, zzeos<zzcqv> zzeos) {
        this.zzfmv = scheduledExecutorService;
        this.zzgcz = zzdzb;
        this.zzgjq = zzcpx;
        this.zzgjf = zzeos;
    }

    public final zzdzc<InputStream> zzg(zzatc zzatc) {
        zzdzc<InputStream> zzdzc;
        String str = zzatc.packageName;
        zzp.zzkr();
        if (zzayu.zzer(str)) {
            zzdzc = zzdyq.immediateFailedFuture(new zzcqm(zzdpg.INTERNAL_ERROR));
        } else {
            zzdzc = this.zzgjq.zzj(zzatc);
        }
        int callingUid = Binder.getCallingUid();
        return zzdyl.zzg(zzdzc).zza((long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcvb)).intValue(), TimeUnit.SECONDS, this.zzfmv).zza(Throwable.class, new zzcpb(this, zzatc, callingUid), (Executor) this.zzgcz);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzatc zzatc, int i, Throwable th) throws Exception {
        return this.zzgjf.get().zzd(zzatc, i);
    }
}
