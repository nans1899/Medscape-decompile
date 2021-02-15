package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzctq implements zzcta<zzbnv> {
    private final Executor zzfoc;
    private final zzcjt zzgbx;
    private final zzbnq zzgoh;
    private final Context zzvr;

    public zzctq(zzbnq zzbnq, Context context, Executor executor, zzcjt zzcjt) {
        this.zzvr = context;
        this.zzgoh = zzbnq;
        this.zzfoc = executor;
        this.zzgbx = zzcjt;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<zzbnv> zzb(zzdog zzdog, zzdnv zzdnv) {
        return zzdyq.zzb(zzdyq.zzaf(null), new zzctp(this, zzdog, zzdnv), this.zzfoc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdog zzdog, zzdnv zzdnv, Object obj) throws Exception {
        zzvn zzb = zzdoq.zzb(this.zzvr, zzdnv.zzhdt);
        zzbgj zza = this.zzgbx.zza(zzb, zzdnv, zzdog.zzhew.zzeou, zzdnv.zzeos);
        zzbnk zza2 = this.zzgoh.zza(new zzbre(zzdog, zzdnv, (String) null), new zzbnj(zza.getView(), zza, zzdoq.zzf(zzb), zzdnv.zzfpf, zzdnv.zzfpg, zzdnv.zzfph));
        zza2.zzafq().zzb(zza, false);
        zza2.zzafc().zza(new zzcts(zza), zzbbz.zzeeu);
        zza2.zzafq();
        return zzdyq.zzb(zzcjv.zza(zza, zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp), new zzctr(zza2), (Executor) zzbbz.zzeeu);
    }
}
