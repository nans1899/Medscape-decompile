package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbrb implements zzdyr<zzbqo> {
    private final /* synthetic */ zzdyr zzfsd;
    private final /* synthetic */ zzbqv zzfse;

    zzbrb(zzbqv zzbqv, zzdyr zzdyr) {
        this.zzfse = zzbqv;
        this.zzfsd = zzdyr;
    }

    public final void zzb(Throwable th) {
        this.zzfse.zzaja();
        this.zzfsd.zzb(th);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zzfse.zzaja();
        this.zzfsd.onSuccess((zzbqo) obj);
    }
}
