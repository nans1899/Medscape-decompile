package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzctt implements zzcta<zzboq> {
    private final Executor zzfoc;
    private final zzdok zzfsk;
    private final zzcjt zzgbx;
    private final zzbpm zzgoj;
    private final zzdvu<zzdnv, zzazp> zzgok;
    private final Context zzvr;

    public zzctt(zzbpm zzbpm, Context context, Executor executor, zzcjt zzcjt, zzdok zzdok, zzdvu<zzdnv, zzazp> zzdvu) {
        this.zzvr = context;
        this.zzgoj = zzbpm;
        this.zzfoc = executor;
        this.zzgbx = zzcjt;
        this.zzfsk = zzdok;
        this.zzgok = zzdvu;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (zzdnv.zzhdr == null || zzdnv.zzhdr.zzdpp == null) ? false : true;
    }

    public final zzdzc<zzboq> zzb(zzdog zzdog, zzdnv zzdnv) {
        return zzdyq.zzb(zzdyq.zzaf(null), new zzctw(this, zzdog, zzdnv), this.zzfoc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(zzbgj zzbgj) {
        zzbgj.zzabq();
        zzbhd zzzs = zzbgj.zzzs();
        if (this.zzfsk.zzhez != null && zzzs != null) {
            zzzs.zzb(this.zzfsk.zzhez);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzb(zzdog zzdog, zzdnv zzdnv, Object obj) throws Exception {
        View view;
        zzvn zzb = zzdoq.zzb(this.zzvr, zzdnv.zzhdt);
        zzbgj zza = this.zzgbx.zza(zzb, zzdnv, zzdog.zzhew.zzeou, zzdnv.zzeos);
        zza.zzax(zzdnv.zzdtp);
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcxu)).booleanValue() || !zzdnv.zzhei) {
            view = new zzckk(this.zzvr, zza.getView(), this.zzgok.apply(zzdnv));
        } else {
            view = zzbqe.zza(this.zzvr, zza.getView(), zzdnv);
        }
        zzbpm zzbpm = this.zzgoj;
        zzbre zzbre = new zzbre(zzdog, zzdnv, (String) null);
        zza.getClass();
        zzbop zza2 = zzbpm.zza(zzbre, new zzbot(view, zza, zzctv.zzp(zza), zzdoq.zzf(zzb)));
        zza2.zzafq().zzb(zza, false);
        zza2.zzafc().zza(new zzcty(zza), zzbbz.zzeeu);
        zza2.zzafq();
        zzdzc<?> zza3 = zzcjv.zza(zza, zzdnv.zzhdr.zzdpn, zzdnv.zzhdr.zzdpp);
        if (zzdnv.zzduj) {
            zza.getClass();
            zza3.addListener(zzctx.zzh(zza), this.zzfoc);
        }
        zza3.addListener(new zzcua(this, zza), this.zzfoc);
        return zzdyq.zzb(zza3, new zzctz(zza2), (Executor) zzbbz.zzeeu);
    }
}
