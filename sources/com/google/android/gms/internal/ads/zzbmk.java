package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbmk implements zzdyr<String> {
    private final /* synthetic */ zzbmh zzfnh;

    zzbmk(zzbmh zzbmh) {
        this.zzfnh = zzbmh;
    }

    public final void zzb(Throwable th) {
        int i;
        zzdor zze = this.zzfnh.zzfmz;
        List<String> zza = this.zzfnh.zzfmy.zza(this.zzfnh.zzfmw, this.zzfnh.zzfmx, false, "", "failure_click_attok", this.zzfnh.zzfmx.zzdkj);
        zzp.zzkr();
        if (zzayu.zzbf(this.zzfnh.zzvr)) {
            i = zzcsq.zzgne;
        } else {
            i = zzcsq.zzgnd;
        }
        zze.zza(zza, i);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        int i;
        zzdor zze = this.zzfnh.zzfmz;
        List<String> zza = this.zzfnh.zzfmy.zza(this.zzfnh.zzfmw, this.zzfnh.zzfmx, false, "", (String) obj, this.zzfnh.zzfmx.zzdkj);
        zzp.zzkr();
        if (zzayu.zzbf(this.zzfnh.zzvr)) {
            i = zzcsq.zzgne;
        } else {
            i = zzcsq.zzgnd;
        }
        zze.zza(zza, i);
    }
}
