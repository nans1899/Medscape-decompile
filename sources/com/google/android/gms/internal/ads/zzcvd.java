package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvd implements zzcti<zzcbe, zzdpa, zzcuu> {
    private final zzbbx zzbot;
    private final Executor zzfoc;
    private final zzcce zzgos;
    private final Context zzvr;

    public zzcvd(Context context, zzbbx zzbbx, zzcce zzcce, Executor executor) {
        this.zzvr = context;
        this.zzbot = zzbbx;
        this.zzgos = zzcce;
        this.zzfoc = executor;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzdpa, zzcuu> zzctc) throws zzdos {
        if (this.zzbot.zzeem < 4100000) {
            ((zzdpa) zzctc.zzdlf).zza(this.zzvr, zzdog.zzhev.zzfsk.zzhfa, zzdnv.zzhdu.toString(), (zzanh) zzctc.zzgns);
        } else {
            ((zzdpa) zzctc.zzdlf).zza(this.zzvr, zzdog.zzhev.zzfsk.zzhfa, zzdnv.zzhdu.toString(), zzbao.zza((zzbat) zzdnv.zzhdr), (zzanh) zzctc.zzgns);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzcbg zza = this.zzgos.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzcbf(new zzcvg(zzctc)));
        zza.zzaez().zza(new zzbmf((zzdpa) zzctc.zzdlf), this.zzfoc);
        ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzafe());
        return zza.zzagj();
    }
}
