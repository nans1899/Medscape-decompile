package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdxv<I, O> extends zzdxt<I, O, zzdvu<? super I, ? extends O>, O> {
    zzdxv(zzdzc<? extends I> zzdzc, zzdvu<? super I, ? extends O> zzdvu) {
        super(zzdzc, zzdvu);
    }

    /* access modifiers changed from: package-private */
    public final void setResult(@NullableDecl O o) {
        set(o);
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public final /* synthetic */ Object zzd(Object obj, @NullableDecl Object obj2) throws Exception {
        return ((zzdvu) obj).apply(obj2);
    }
}
