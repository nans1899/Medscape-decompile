package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcxb implements zzcta<zzcjg> {
    private final zzbbx zzbot;
    private final Executor zzfoc;
    private final zzdok zzfsk;
    private final zzcjt zzgbx;
    private final zzcjj zzgqi;
    private final Context zzvr;

    public zzcxb(Context context, zzbbx zzbbx, zzdok zzdok, Executor executor, zzcjj zzcjj, zzcjt zzcjt) {
        this.zzvr = context;
        this.zzfsk = zzdok;
        this.zzgqi = zzcjj;
        this.zzfoc = executor;
        this.zzbot = zzbbx;
        this.zzgbx = zzcjt;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<zzcjg> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzckj zzckj = new zzckj();
        zzdzc<zzcjg> zzb = zzdyq.zzb(zzdyq.zzaf(null), new zzcxe(this, zzdnv, zzdog, zzckj), this.zzfoc);
        zzckj.getClass();
        zzb.addListener(zzcxd.zza(zzckj), this.zzfoc);
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzc(zzdnv zzdnv, zzdog zzdog, zzckj zzckj, Object obj) throws Exception {
        zzdzc<?> zzdzc;
        zzdnv zzdnv2 = zzdnv;
        zzdog zzdog2 = zzdog;
        zzbgj zza = this.zzgbx.zza(this.zzfsk.zzbpf, zzdnv2, zzdog2.zzhew.zzeou, zzdnv2.zzeos);
        zza.zzax(zzdnv2.zzdtp);
        zzckj.zzc(this.zzvr, zza.getView());
        zzbcg zzbcg = new zzbcg();
        zzcjj zzcjj = this.zzgqi;
        zzbre zzbre = new zzbre(zzdog2, zzdnv2, (String) null);
        zzcxh zzcxh = r1;
        zzcxh zzcxh2 = new zzcxh(this.zzvr, this.zzgbx, this.zzfsk, this.zzbot, zzdnv, zzbcg, zza);
        zzcji zza2 = zzcjj.zza(zzbre, new zzcjh(zzcxh, zza));
        zzbcg.set(zza2);
        zzahx.zza(zza, (zzaia) zza2.zzagp());
        zza2.zzafc().zza(new zzcxg(zza), zzbbz.zzeeu);
        zza2.zzafq().zzb(zza, true);
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwp)).booleanValue() || !zzdnv2.zzeos) {
            zza2.zzafq();
            zzdzc = zzcjv.zza(zza, zzdnv2.zzhdr.zzdpn, zzdnv2.zzhdr.zzdpp);
        } else {
            zzdzc = zzdyq.zzaf(null);
        }
        return zzdyq.zzb(zzdzc, new zzcxf(this, zza, zzdnv2, zza2), this.zzfoc);
    }
}
