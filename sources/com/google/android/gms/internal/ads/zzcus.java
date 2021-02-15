package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcus implements zzdyr<zzboq> {
    private final /* synthetic */ zzcun zzgpa;

    zzcus(zzcun zzcun) {
        this.zzgpa = zzcun;
    }

    public final void zzb(Throwable th) {
        zzva zze = this.zzgpa.zzgov.zzaev().zze(th);
        this.zzgpa.zzfwd.zzk(zze);
        zzdox.zza(zze.errorCode, th, "DelayedBannerAd.onFailure");
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        ((zzboq) obj).zzaih();
    }
}
