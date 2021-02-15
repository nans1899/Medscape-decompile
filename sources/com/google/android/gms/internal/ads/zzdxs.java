package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdxs<I, O> extends zzdxt<I, O, zzdya<? super I, ? extends O>, zzdzc<? extends O>> {
    zzdxs(zzdzc<? extends I> zzdzc, zzdya<? super I, ? extends O> zzdya) {
        super(zzdzc, zzdya);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void setResult(Object obj) {
        setFuture((zzdzc) obj);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzd(Object obj, @NullableDecl Object obj2) throws Exception {
        zzdya zzdya = (zzdya) obj;
        zzdzc zzf = zzdya.zzf(obj2);
        zzdwd.zza(zzf, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzdya);
        return zzf;
    }
}
