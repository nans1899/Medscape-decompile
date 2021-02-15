package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcvt implements zzcti<zzcdx, zzdpa, zzcuu> {
    private final Executor zzfoc;
    private final zzcda zzgpj;
    private final Context zzvr;

    public zzcvt(Context context, zzcda zzcda, Executor executor) {
        this.zzvr = context;
        this.zzgpj = zzcda;
        this.zzfoc = executor;
    }

    public final void zza(zzdog zzdog, zzdnv zzdnv, zzctc<zzdpa, zzcuu> zzctc) throws zzdos {
        ((zzdpa) zzctc.zzdlf).zza(this.zzvr, zzdog.zzhev.zzfsk.zzhfa, zzdnv.zzhdu.toString(), zzbao.zza((zzbat) zzdnv.zzhdr), (zzanh) zzctc.zzgns, zzdog.zzhev.zzfsk.zzdly, zzdog.zzhev.zzfsk.zzhfc);
    }

    private static boolean zza(zzdog zzdog, int i) {
        return zzdog.zzhev.zzfsk.zzhfc.contains(Integer.toString(i));
    }

    public final /* synthetic */ Object zzb(zzdog zzdog, zzdnv zzdnv, zzctc zzctc) throws zzdos, zzcwn {
        zzcei zzcei;
        zzano zztt = ((zzdpa) zzctc.zzdlf).zztt();
        zzanp zztu = ((zzdpa) zzctc.zzdlf).zztu();
        zzanu zztz = ((zzdpa) zzctc.zzdlf).zztz();
        if (zztz != null && zza(zzdog, 6)) {
            zzcei = zzcei.zzb(zztz);
        } else if (zztt != null && zza(zzdog, 6)) {
            zzcei = zzcei.zzb(zztt);
        } else if (zztt != null && zza(zzdog, 2)) {
            zzcei = zzcei.zza(zztt);
        } else if (zztu != null && zza(zzdog, 6)) {
            zzcei = zzcei.zzb(zztu);
        } else if (zztu == null || !zza(zzdog, 1)) {
            throw new zzcwn(zzdpg.INTERNAL_ERROR, "No native ad mappers");
        } else {
            zzcei = zzcei.zza(zztu);
        }
        if (zzdog.zzhev.zzfsk.zzhfc.contains(Integer.toString(zzcei.zzama()))) {
            zzcej zza = this.zzgpj.zza(new zzbre(zzdog, zzdnv, zzctc.zzcib), new zzceu(zzcei), new zzcgf(zztu, zztt, zztz));
            ((zzcuu) zzctc.zzgns).zzb((zzanh) zza.zzafe());
            zza.zzaez().zza(new zzbmf((zzdpa) zzctc.zzdlf), this.zzfoc);
            return zza.zzafg();
        }
        throw new zzcwn(zzdpg.INTERNAL_ERROR, "No corresponding native ad listener");
    }
}
