package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbrc implements zzdyr<zzbqu> {
    private final /* synthetic */ zzdyr zzfsd;
    private final /* synthetic */ zzbqv zzfse;

    zzbrc(zzbqv zzbqv, zzdyr zzdyr) {
        this.zzfse = zzbqv;
        this.zzfsd = zzdyr;
    }

    public final void zzb(Throwable th) {
        this.zzfsd.zzb(th);
        this.zzfse.zzaja();
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zzfse.zza(((zzbqu) obj).zzfrx, this.zzfsd);
    }
}
