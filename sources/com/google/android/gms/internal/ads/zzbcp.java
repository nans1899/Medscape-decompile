package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbcp implements zzdyr<T> {
    private final /* synthetic */ zzbck zzefe;
    private final /* synthetic */ zzbci zzeff;

    zzbcp(zzbcn zzbcn, zzbck zzbck, zzbci zzbci) {
        this.zzefe = zzbck;
        this.zzeff = zzbci;
    }

    public final void onSuccess(T t) {
        this.zzefe.zzh(t);
    }

    public final void zzb(Throwable th) {
        this.zzeff.run();
    }
}
