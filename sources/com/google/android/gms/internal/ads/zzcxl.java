package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcxl implements zzcti<zzcjg, zzdpa, zzcut> {
    /* access modifiers changed from: private */
    public final Executor zzfoc;
    private final zzcjj zzgqi;
    private final Context zzvr;

    public zzcxl(Context context, Executor executor, zzcjj zzcjj) {
        this.zzvr = context;
        this.zzfoc = executor;
        this.zzgqi = zzcjj;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzdpa, zzcut> zzctc) throws zzdos {
        if (!((zzdpa) zzctc.zzdlf).isInitialized()) {
            ((zzcut) zzctc.zzgns).zza((zzcas) new zzcxn(this, zzdog, zzdnv, zzctc));
            ((zzdpa) zzctc.zzdlf).zza(this.zzvr, zzdog.zzhev.zzfsk.zzhfa, (String) null, (zzaur) zzctc.zzgns, zzdnv.zzhdu.toString());
            return;
        }
        zzc(zzdog, zzdnv, zzctc);
    }

    /* access modifiers changed from: private */
    public static void zzc(zzdog zzdog, zzdnv zzdnv, zzctc<zzdpa, zzcut> zzctc) {
        try {
            ((zzdpa) zzctc.zzdlf).zza(zzdog.zzhev.zzfsk.zzhfa, zzdnv.zzhdu.toString());
        } catch (Exception e) {
            String valueOf = String.valueOf(zzctc.zzcib);
            zzayp.zzd(valueOf.length() != 0 ? "Fail to load ad from adapter ".concat(valueOf) : new String("Fail to load ad from adapter "), e);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzcji zza = this.zzgqi.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzcjh(new zzcxo(zzctc)));
        zza.zzaez().zza(new zzbmf((zzdpa) zzctc.zzdlf), this.zzfoc);
        zzbvh zzafa = zza.zzafa();
        zzbuc zzafb = zza.zzafb();
        ((zzcut) zzctc.zzgns).zza((zzaur) new zzcxp(this, zza.zzagk(), zzafb, zzafa, zza.zzagp()));
        return zza.zzago();
    }
}
