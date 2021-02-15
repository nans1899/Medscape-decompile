package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcuw implements zzcta<zzcbe> {
    private final zzbbx zzbot;
    private final Executor zzfoc;
    private final zzdok zzfsk;
    private final zzcjt zzgbx;
    private final zzcce zzgos;
    private final Context zzvr;

    public zzcuw(Context context, zzbbx zzbbx, zzdok zzdok, Executor executor, zzcce zzcce, zzcjt zzcjt) {
        this.zzvr = context;
        this.zzfsk = zzdok;
        this.zzgos = zzcce;
        this.zzfoc = executor;
        this.zzbot = zzbbx;
        this.zzgbx = zzcjt;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<zzcbe> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzckj zzckj = new zzckj();
        zzdzc<zzcbe> zzb = zzdyq.zzb(zzdyq.zzaf(null), new zzcuv(this, zzdnv, zzdog, zzckj), this.zzfoc);
        zzckj.getClass();
        zzb.addListener(zzcuy.zza(zzckj), this.zzfoc);
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzb(zzdnv zzdnv, zzdog zzdog, zzckj zzckj, Object obj) throws Exception {
        zzdzc<?> zzdzc;
        zzbgj zza = this.zzgbx.zza(this.zzfsk.zzbpf, zzdnv, zzdog.zzhew.zzeou, zzdnv.zzeos);
        zza.zzax(zzdnv.zzdtp);
        zzckj.zzc(this.zzvr, zza.getView());
        zzbcg zzbcg = new zzbcg();
        zzcbg zza2 = this.zzgos.zza(new zzbre(zzdog, zzdnv, (String) null), new zzcbf(new zzcvc(this.zzvr, this.zzbot, zzbcg, zzdnv, zza), zza));
        zzbcg.set(zza2);
        zza2.zzafc().zza(new zzcux(zza), zzbbz.zzeeu);
        zza2.zzafq().zzb(zza, true);
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwp)).booleanValue() || !zzdnv.zzeos) {
            zza2.zzafq();
            zzdzc = zzcjv.zza(zza, zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp);
        } else {
            zzdzc = zzdyq.zzaf(null);
        }
        return zzdyq.zzb(zzdzc, new zzcva(this, zza, zzdnv, zza2), this.zzfoc);
    }
}
