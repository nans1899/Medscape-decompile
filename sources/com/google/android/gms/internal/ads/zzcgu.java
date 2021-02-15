package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcgu implements Callable {
    private final zzdzc zzfsc;
    private final zzdzc zzftx;
    private final zzcgs zzgcw;
    private final zzdzc zzgde;
    private final zzdzc zzgdf;
    private final zzdzc zzgdg;
    private final JSONObject zzgdh;
    private final zzdzc zzgdi;
    private final zzdzc zzgdj;
    private final zzdzc zzgdk;

    zzcgu(zzcgs zzcgs, zzdzc zzdzc, zzdzc zzdzc2, zzdzc zzdzc3, zzdzc zzdzc4, zzdzc zzdzc5, JSONObject jSONObject, zzdzc zzdzc6, zzdzc zzdzc7, zzdzc zzdzc8) {
        this.zzgcw = zzcgs;
        this.zzftx = zzdzc;
        this.zzfsc = zzdzc2;
        this.zzgde = zzdzc3;
        this.zzgdf = zzdzc4;
        this.zzgdg = zzdzc5;
        this.zzgdh = jSONObject;
        this.zzgdi = zzdzc6;
        this.zzgdj = zzdzc7;
        this.zzgdk = zzdzc8;
    }

    public final Object call() {
        zzdzc zzdzc = this.zzftx;
        zzdzc zzdzc2 = this.zzfsc;
        zzdzc zzdzc3 = this.zzgde;
        zzdzc zzdzc4 = this.zzgdf;
        zzdzc zzdzc5 = this.zzgdg;
        JSONObject jSONObject = this.zzgdh;
        zzdzc zzdzc6 = this.zzgdi;
        zzdzc zzdzc7 = this.zzgdj;
        zzdzc zzdzc8 = this.zzgdk;
        zzcei zzcei = (zzcei) zzdzc.get();
        zzcei.setImages((List) zzdzc2.get());
        zzcei.zza((zzaee) zzdzc3.get());
        zzcei.zzb((zzaee) zzdzc4.get());
        zzcei.zza((zzadw) zzdzc5.get());
        zzcei.zzh(zzcgw.zzj(jSONObject));
        zzcei.zza(zzcgw.zzk(jSONObject));
        zzbgj zzbgj = (zzbgj) zzdzc6.get();
        if (zzbgj != null) {
            zzcei.zzi(zzbgj);
            zzcei.zzac(zzbgj.getView());
            zzcei.zzb((zzyo) zzbgj.zzzs());
        }
        zzbgj zzbgj2 = (zzbgj) zzdzc7.get();
        if (zzbgj2 != null) {
            zzcei.zzj(zzbgj2);
        }
        for (zzchh zzchh : (List) zzdzc8.get()) {
            int i = zzchh.type;
            if (i == 1) {
                zzcei.zzn(zzchh.zzcn, zzchh.zzgdu);
            } else if (i == 2) {
                zzcei.zza(zzchh.zzcn, zzchh.zzgdv);
            }
        }
        return zzcei;
    }
}
