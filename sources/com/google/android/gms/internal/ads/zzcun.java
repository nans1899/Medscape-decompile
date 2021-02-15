package com.google.android.gms.internal.ads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcun implements zzcta<zzboq> {
    private final ScheduledExecutorService zzfmv;
    /* access modifiers changed from: private */
    public final zzbug zzfwd;
    private final zzdzb zzgcz;
    /* access modifiers changed from: private */
    public final zzbpm zzgov;
    private final zzctt zzgow;

    public zzcun(zzbpm zzbpm, zzctt zzctt, zzbug zzbug, ScheduledExecutorService scheduledExecutorService, zzdzb zzdzb) {
        this.zzgov = zzbpm;
        this.zzgow = zzctt;
        this.zzfwd = zzbug;
        this.zzfmv = scheduledExecutorService;
        this.zzgcz = zzdzb;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return zzdog.zzhev.zzfsk.zzati() != null && this.zzgow.zza(zzdog, zzdnv);
    }

    public final zzdzc<zzboq> zzb(zzdog zzdog, zzdnv zzdnv) {
        return this.zzgcz.zze(new zzcuq(this, zzdog, zzdnv));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzboq zzc(zzdog zzdog, zzdnv zzdnv) throws Exception {
        return this.zzgov.zza(new zzbre(zzdog, zzdnv, (String) null), new zzbqa(zzdog.zzhev.zzfsk.zzati(), new zzcup(this, zzdog, zzdnv))).zzagg();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzdog zzdog, zzdnv zzdnv) {
        zzdyq.zza(zzdyq.zza(this.zzgow.zzb(zzdog, zzdnv), (long) zzdnv.zzheb, TimeUnit.SECONDS, this.zzfmv), new zzcus(this), this.zzgcz);
    }
}
