package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcww implements zzcti<zzcjg, zzaph, zzcuu> {
    private final zzcjj zzgqi;
    private final Context zzvr;

    public zzcww(Context context, zzcjj zzcjj) {
        this.zzvr = context;
        this.zzgqi = zzcjj;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzaph, zzcuu> zzctc) throws zzdos {
        try {
            ((zzaph) zzctc.zzdlf).zzdr(zzdnv.zzdmk);
            if (zzdog.zzhev.zzfsk.zzhfh.zzheo == zzdoc.zzher) {
                ((zzaph) zzctc.zzdlf).zzb(zzdnv.zzera, zzdnv.zzhdu.toString(), zzdog.zzhev.zzfsk.zzhfa, ObjectWrapper.wrap(this.zzvr), new zzcwy(this, zzctc), (zzanh) zzctc.zzgns);
            } else {
                ((zzaph) zzctc.zzdlf).zza(zzdnv.zzera, zzdnv.zzhdu.toString(), zzdog.zzhev.zzfsk.zzhfa, ObjectWrapper.wrap(this.zzvr), (zzapg) new zzcwy(this, zzctc), (zzanh) zzctc.zzgns);
            }
        } catch (RemoteException e) {
            zzayp.zza("Remote exception loading a rewarded RTB ad", e);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzcve zzcve = new zzcve(zzdnv, (zzaph) zzctc.zzdlf, true);
        zzcji zza = this.zzgqi.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzcjh(zzcve));
        zzcve.zza(zza.zzafc());
        ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzagr());
        return zza.zzago();
    }
}
