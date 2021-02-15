package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.zzb;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcub implements zzcti<zzboq, zzdpa, zzcuu> {
    private final zzbbx zzbot;
    private final Executor zzfoc;
    private final zzbpm zzgoj;
    private final Context zzvr;

    public zzcub(Context context, zzbbx zzbbx, zzbpm zzbpm, Executor executor) {
        this.zzvr = context;
        this.zzbot = zzbbx;
        this.zzgoj = zzbpm;
        this.zzfoc = executor;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzdpa, zzcuu> zzctc) throws zzdos {
        zzvn zzvn;
        zzvn zzvn2 = zzdog.zzhev.zzfsk.zzbpf;
        if (zzvn2.zzcia) {
            zzvn = new zzvn(this.zzvr, zzb.zza(zzvn2.width, zzvn2.height));
        } else {
            zzvn = zzdoq.zzb(this.zzvr, zzdnv.zzhdt);
        }
        zzvn zzvn3 = zzvn;
        if (this.zzbot.zzeem < 4100000) {
            ((zzdpa) zzctc.zzdlf).zza(this.zzvr, zzvn3, zzdog.zzhev.zzfsk.zzhfa, zzdnv.zzhdu.toString(), (zzanh) zzctc.zzgns);
        } else {
            ((zzdpa) zzctc.zzdlf).zza(this.zzvr, zzvn3, zzdog.zzhev.zzfsk.zzhfa, zzdnv.zzhdu.toString(), zzbao.zza((zzbat) zzdnv.zzhdr), (zzanh) zzctc.zzgns);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzbpm zzbpm = this.zzgoj;
        zzbre zzbre = new zzbre(zzdog, zzdnv, zzctc.zzcib);
        View view = ((zzdpa) zzctc.zzdlf).getView();
        zzdpa zzdpa = (zzdpa) zzctc.zzdlf;
        zzdpa.getClass();
        zzbop zza = zzbpm.zza(zzbre, new zzbot(view, (zzbgj) null, zzcue.zza(zzdpa), zzdnv.zzhdt.get(0)));
        zza.zzagb().zzv(((zzdpa) zzctc.zzdlf).getView());
        zza.zzaez().zza(new zzbmf((zzdpa) zzctc.zzdlf), this.zzfoc);
        ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzafe());
        return zza.zzaga();
    }
}
