package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsd implements zzdso {
    private final zzcsc zzgml;

    zzcsd(zzcsc zzcsc) {
        this.zzgml = zzcsc;
    }

    public final void zza(zzdsf zzdsf, String str) {
    }

    public final void zzb(zzdsf zzdsf, String str) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue() && zzdsf.RENDERER == zzdsf) {
            this.zzgml.zzff(zzp.zzky().elapsedRealtime());
        }
    }

    public final void zza(zzdsf zzdsf, String str, Throwable th) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue() && zzdsf.RENDERER == zzdsf && this.zzgml.zzaqe() != 0) {
            this.zzgml.zzep(zzp.zzky().elapsedRealtime() - this.zzgml.zzaqe());
        }
    }

    public final void zzc(zzdsf zzdsf, String str) {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcwk)).booleanValue() && zzdsf.RENDERER == zzdsf && this.zzgml.zzaqe() != 0) {
            this.zzgml.zzep(zzp.zzky().elapsedRealtime() - this.zzgml.zzaqe());
        }
    }
}
