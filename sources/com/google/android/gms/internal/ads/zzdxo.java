package com.google.android.gms.internal.ads;

import java.lang.Throwable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdxo<V, X extends Throwable> extends zzdxm<V, X, zzdvu<? super X, ? extends V>, V> {
    zzdxo(zzdzc<? extends V> zzdzc, Class<X> cls, zzdvu<? super X, ? extends V> zzdvu) {
        super(zzdzc, cls, zzdvu);
    }

    /* access modifiers changed from: package-private */
    public final void setResult(@NullableDecl V v) {
        set(v);
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public final /* synthetic */ Object zza(Object obj, Throwable th) throws Exception {
        return ((zzdvu) obj).apply(th);
    }
}
