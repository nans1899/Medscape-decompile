package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcth implements zzcta<zzboh> {
    private final zzbbx zzbot;
    private final Executor zzfoc;
    private final zzdok zzfsk;
    private final zzcjt zzgbx;
    private final zzbob zzgnw;
    private final Context zzvr;

    public zzcth(zzbob zzbob, Context context, Executor executor, zzcjt zzcjt, zzdok zzdok, zzbbx zzbbx) {
        this.zzvr = context;
        this.zzgnw = zzbob;
        this.zzfoc = executor;
        this.zzgbx = zzcjt;
        this.zzfsk = zzdok;
        this.zzbot = zzbbx;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<zzboh> zzb(zzdog zzdog, zzdnv zzdnv) {
        zzckj zzckj = new zzckj();
        zzdzc<zzboh> zzb = zzdyq.zzb(zzdyq.zzaf(null), new zzctk(this, zzdnv, zzdog, zzckj), this.zzfoc);
        zzckj.getClass();
        zzb.addListener(zzctj.zza(zzckj), this.zzfoc);
        return zzb;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdnv zzdnv, zzdog zzdog, zzckj zzckj, Object obj) throws Exception {
        zzdzc<?> zzdzc;
        zzdoq.zzb(this.zzvr, zzdnv.zzhdt);
        zzbgj zza = this.zzgbx.zza(this.zzfsk.zzbpf, zzdnv, zzdog.zzhew.zzeou, zzdnv.zzeos);
        zza.zzax(zzdnv.zzdtp);
        zzckj.zzc(this.zzvr, zza.getView());
        zzbcg zzbcg = new zzbcg();
        zzbnx zza2 = this.zzgnw.zza(new zzbre(zzdog, zzdnv, (String) null), new zzcbf(new zzctn(this.zzvr, this.zzbot, zzbcg, zzdnv, zza, this.zzfsk), zza), new zzboa(zza.getView(), zza, zzdnv.zzfpf, zzdnv.zzfpg, zzdnv.zzfph));
        zza2.zzafq().zzb(zza, false);
        zzbcg.set(zza2);
        zza2.zzafc().zza(new zzctm(zza), zzbbz.zzeeu);
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcwp)).booleanValue() || !zzdnv.zzeos) {
            zza2.zzafq();
            zzdzc = zzcjv.zza(zza, zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp);
        } else {
            zzdzc = zzdyq.zzaf(null);
        }
        return zzdyq.zzb(zzdzc, new zzctl(this, zza, zzdnv, zza2), this.zzfoc);
    }
}
