package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvi implements zzcti<zzcbe, zzaph, zzcuu> {
    private final zzcce zzgos;
    private final Context zzvr;

    public zzcvi(Context context, zzcce zzcce) {
        this.zzvr = context;
        this.zzgos = zzcce;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzaph, zzcuu> zzctc) throws zzdos {
        try {
            ((zzaph) zzctc.zzdlf).zzdr(zzdnv.zzdmk);
            ((zzaph) zzctc.zzdlf).zza(zzdnv.zzera, zzdnv.zzhdu.toString(), zzdog.zzhev.zzfsk.zzhfa, ObjectWrapper.wrap(this.zzvr), (zzapa) new zzcvk(this, zzctc), (zzanh) zzctc.zzgns);
        } catch (RemoteException e) {
            throw new zzdos(e);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzcve zzcve = new zzcve(zzdnv, (zzaph) zzctc.zzdlf, false);
        zzcbg zza = this.zzgos.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzcbf(zzcve));
        zzcve.zza(zza.zzafc());
        ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzaff());
        return zza.zzagj();
    }
}
