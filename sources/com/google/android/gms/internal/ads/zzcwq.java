package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwq<AdT> implements zzdya<zzdog, AdT> {
    private final Executor executor;
    private final zzdsr zzfmy;
    private final zzdst zzfnf;
    private final ScheduledExecutorService zzfoz;
    private final zzdsi zzfsl;
    private final zzctd zzfst;
    private final zzbug zzfwd;
    private final zzbqp<AdT> zzgqg;
    private final zzcwj zzgqh;

    public zzcwq(zzdsi zzdsi, zzcwj zzcwj, zzbug zzbug, zzdsr zzdsr, zzdst zzdst, zzbqp<AdT> zzbqp, Executor executor2, ScheduledExecutorService scheduledExecutorService, zzctd zzctd) {
        this.zzfsl = zzdsi;
        this.zzgqh = zzcwj;
        this.zzfwd = zzbug;
        this.zzfmy = zzdsr;
        this.zzfnf = zzdst;
        this.zzgqg = zzbqp;
        this.executor = executor2;
        this.zzfoz = scheduledExecutorService;
        this.zzfst = zzctd;
    }

    public final /* synthetic */ zzdzc zzf(Object obj) throws Exception {
        boolean z;
        zzdog zzdog = (zzdog) obj;
        zzdnx zzdnx = zzdog.zzhew.zzeou.zzhem;
        zzdrr<E, I> zzavs = this.zzfsl.zzu(zzdsf.RENDER_CONFIG_INIT).zze(zzdyq.immediateFailedFuture(new zzcwn(zzdpg.NO_FILL, zzdnx != null ? zzdnx.getDescription() : "No ad config."))).zzavs();
        this.zzfwd.zza(new zzbmi(zzdog, this.zzfnf, this.zzfmy), this.executor);
        int i = 0;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue()) {
            for (zzdnv next : zzdog.zzhew.zzhet) {
                this.zzfst.zze(next);
                Iterator<String> it = next.zzhdi.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    zzcta<AdT> zze = this.zzgqg.zze(next.zzhdj, it.next());
                    if (zze != null && zze.zza(zzdog, next)) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    this.zzfst.zza(next, 0, zzdpe.zza(zzdpg.INTERNAL_ERROR, (String) null, (zzva) null));
                }
            }
        }
        for (zzdnv next2 : zzdog.zzhew.zzhet) {
            Iterator<String> it2 = next2.zzhdi.iterator();
            while (true) {
                if (it2.hasNext()) {
                    String next3 = it2.next();
                    zzcta<AdT> zze2 = this.zzgqg.zze(next2.zzhdj, next3);
                    if (zze2 != null && zze2.zza(zzdog, next2)) {
                        zzdsa<I> zza = this.zzfsl.zza(zzdsf.RENDER_CONFIG_WATERFALL, zzavs);
                        StringBuilder sb = new StringBuilder(String.valueOf(next3).length() + 26);
                        sb.append("render-config-");
                        sb.append(i);
                        sb.append("-");
                        sb.append(next3);
                        zzavs = zza.zzgx(sb.toString()).zza(Throwable.class, new zzcwp(this, zzdog, next2, zze2)).zzavs();
                        break;
                    }
                } else {
                    break;
                }
            }
            i++;
        }
        return zzavs;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdog zzdog, zzdnv zzdnv, zzcta zzcta, Throwable th) throws Exception {
        return this.zzgqh.zza(zzdog.zzhew.zzeou, zzdnv, zzdyq.zza(zzcta.zzb(zzdog, zzdnv), (long) zzdnv.zzheb, TimeUnit.MILLISECONDS, this.zzfoz));
    }
}
