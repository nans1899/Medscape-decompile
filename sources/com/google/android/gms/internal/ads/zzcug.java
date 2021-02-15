package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcug implements zzcti<zzboq, zzaph, zzcuu> {
    /* access modifiers changed from: private */
    public View view;
    private final zzbpm zzgoj;
    private final Context zzvr;

    public zzcug(Context context, zzbpm zzbpm) {
        this.zzvr = context;
        this.zzgoj = zzbpm;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzaph, zzcuu> zzctc) throws zzdos {
        try {
            ((zzaph) zzctc.zzdlf).zzdr(zzdnv.zzdmk);
            ((zzaph) zzctc.zzdlf).zza(zzdnv.zzera, zzdnv.zzhdu.toString(), zzdog.zzhev.zzfsk.zzhfa, ObjectWrapper.wrap(this.zzvr), new zzcuh(this, zzctc), (zzanh) zzctc.zzgns, zzdog.zzhev.zzfsk.zzbpf);
        } catch (RemoteException e) {
            throw new zzdos(e);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzbop zza = this.zzgoj.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzbot(this.view, (zzbgj) null, new zzcuf(zzctc), zzdnv.zzhdt.get(0)));
        zza.zzagb().zzv(this.view);
        ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzaff());
        return zza.zzaga();
    }

    static final /* synthetic */ zzyo zza(zzctc zzctc) throws zzdos {
        try {
            return ((zzaph) zzctc.zzdlf).getVideoController();
        } catch (RemoteException e) {
            throw new zzdos(e);
        }
    }
}
