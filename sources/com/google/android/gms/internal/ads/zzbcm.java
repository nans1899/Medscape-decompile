package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbcm implements zzdyr<T> {
    private final /* synthetic */ zzbcn zzefb;

    zzbcm(zzbcn zzbcn) {
        this.zzefb = zzbcn;
    }

    public final void onSuccess(T t) {
        this.zzefb.zzefd.set(1);
    }

    public final void zzb(Throwable th) {
        this.zzefb.zzefd.set(-1);
    }
}
