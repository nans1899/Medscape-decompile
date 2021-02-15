package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.MobileAds;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcwm implements zzdyr<T> {
    private final /* synthetic */ zzdnw zzgeq;
    private final /* synthetic */ long zzgqa;
    private final /* synthetic */ String zzgqb;
    private final /* synthetic */ zzdnv zzgqc;
    private final /* synthetic */ zzcwj zzgqd;

    zzcwm(zzcwj zzcwj, long j, String str, zzdnv zzdnv, zzdnw zzdnw) {
        this.zzgqd = zzcwj;
        this.zzgqa = j;
        this.zzgqb = str;
        this.zzgqc = zzdnv;
        this.zzgeq = zzdnw;
    }

    public final void onSuccess(T t) {
        long elapsedRealtime = this.zzgqd.zzbqa.elapsedRealtime() - this.zzgqa;
        this.zzgqd.zza(this.zzgqb, 0, elapsedRealtime, this.zzgqc.zzheh);
        if (this.zzgqd.zzgpz) {
            this.zzgqd.zzgpx.zza(this.zzgeq, this.zzgqc, 0, (zzcte) null, elapsedRealtime);
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue()) {
            this.zzgqd.zzfst.zza(this.zzgqc, elapsedRealtime, (zzva) null);
        }
    }

    public final void zzb(Throwable th) {
        int i;
        long elapsedRealtime = this.zzgqd.zzbqa.elapsedRealtime() - this.zzgqa;
        if (th instanceof TimeoutException) {
            i = 2;
        } else if (th instanceof zzcvz) {
            i = 3;
        } else if (th instanceof CancellationException) {
            i = 4;
        } else if (th instanceof zzdos) {
            i = 5;
        } else {
            i = (!(th instanceof zzcof) || zzdpe.zzh(th).errorCode != 3) ? 6 : 1;
        }
        this.zzgqd.zza(this.zzgqb, i, elapsedRealtime, this.zzgqc.zzheh);
        if (this.zzgqd.zzgpz) {
            this.zzgqd.zzgpx.zza(this.zzgeq, this.zzgqc, i, th instanceof zzcte ? (zzcte) th : null, elapsedRealtime);
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyp)).booleanValue()) {
            zzva zzh = zzdpe.zzh(th);
            if ((zzh.errorCode == 3 || zzh.errorCode == 0) && zzh.zzcgy != null && !zzh.zzcgy.zzcgx.equals(MobileAds.ERROR_DOMAIN)) {
                zzh = zzdpe.zzh(new zzcte(zzdpg.MEDIATION_NO_FILL, zzh.zzcgy));
            }
            this.zzgqd.zzfst.zza(this.zzgqc, elapsedRealtime, zzh);
        }
    }
}
