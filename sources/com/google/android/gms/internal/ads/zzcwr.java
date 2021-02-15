package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwr implements zzcti<zzcjg, zzdpa, zzcuu> {
    private final Executor zzfoc;
    private final zzcjj zzgqi;
    private final Context zzvr;

    public zzcwr(Context context, Executor executor, zzcjj zzcjj) {
        this.zzvr = context;
        this.zzfoc = executor;
        this.zzgqi = zzcjj;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzdpa, zzcuu> zzctc) throws zzdos {
        try {
            zzdok zzdok = zzdog.zzhev.zzfsk;
            if (zzdok.zzhfh.zzheo == zzdoc.zzher) {
                ((zzdpa) zzctc.zzdlf).zzc(this.zzvr, zzdok.zzhfa, zzdnv.zzhdu.toString(), (zzanh) zzctc.zzgns);
            } else {
                ((zzdpa) zzctc.zzdlf).zzb(this.zzvr, zzdok.zzhfa, zzdnv.zzhdu.toString(), (zzanh) zzctc.zzgns);
            }
        } catch (Exception e) {
            String valueOf = String.valueOf(zzctc.zzcib);
            zzayp.zzd(valueOf.length() != 0 ? "Fail to load ad from adapter ".concat(valueOf) : new String("Fail to load ad from adapter "), e);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzcji zza = this.zzgqi.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzcjh(new zzcwu(zzctc)));
        zza.zzaez().zza(new zzbmf((zzdpa) zzctc.zzdlf), this.zzfoc);
        ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzagq());
        return zza.zzago();
    }
}
