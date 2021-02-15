package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvv implements zzcti<zzcdx, zzaph, zzcuu> {
    private final zzcda zzgpj;
    /* access modifiers changed from: private */
    public zzanu zzgpr;
    private final Context zzvr;

    public zzcvv(Context context, zzcda zzcda) {
        this.zzvr = context;
        this.zzgpj = zzcda;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzaph, zzcuu> zzctc) throws zzdos {
        try {
            ((zzaph) zzctc.zzdlf).zzdr(zzdnv.zzdmk);
            ((zzaph) zzctc.zzdlf).zza(zzdnv.zzera, zzdnv.zzhdu.toString(), zzdog.zzhev.zzfsk.zzhfa, ObjectWrapper.wrap(this.zzvr), (zzapb) new zzcvx(this, zzctc), (zzanh) zzctc.zzgns);
        } catch (RemoteException e) {
            throw new zzdos(e);
        }
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        if (zzdog.zzhev.zzfsk.zzhfc.contains(Integer.toString(6))) {
            zzcei zzb = zzcei.zzb(this.zzgpr);
            if (zzdog.zzhev.zzfsk.zzhfc.contains(Integer.toString(zzb.zzama()))) {
                zzcej zza = this.zzgpj.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzceu(zzb), new zzcgf((zzanp) null, (zzano) null, this.zzgpr));
                ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzaff());
                return zza.zzafg();
            }
            throw new zzcwn(zzdpg.INTERNAL_ERROR, "No corresponding native ad listener");
        }
        throw new zzcwn(zzdpg.INVALID_REQUEST, "Unified must be used for RTB.");
    }
}
