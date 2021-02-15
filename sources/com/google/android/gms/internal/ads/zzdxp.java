package com.google.android.gms.internal.ads;

import java.lang.Throwable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdxp<V, X extends Throwable> extends zzdxm<V, X, zzdya<? super X, ? extends V>, zzdzc<? extends V>> {
    zzdxp(zzdzc<? extends V> zzdzc, Class<X> cls, zzdya<? super X, ? extends V> zzdya) {
        super(zzdzc, cls, zzdya);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void setResult(Object obj) {
        setFuture((zzdzc) obj);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj, Throwable th) throws Exception {
        zzdya zzdya = (zzdya) obj;
        zzdzc zzf = zzdya.zzf(th);
        zzdwd.zza(zzf, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzdya);
        return zzf;
    }
}
