package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdxd<K> extends zzdws<K> {
    private final transient zzdwp<K> zzhqo;
    private final transient zzdwt<K, ?> zzhrd;

    zzdxd(zzdwt<K, ?> zzdwt, zzdwp<K> zzdwp) {
        this.zzhrd = zzdwt;
        this.zzhqo = zzdwp;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return true;
    }

    public final zzdxg<K> zzaxi() {
        return (zzdxg) zzaxm().iterator();
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        return zzaxm().zza(objArr, i);
    }

    public final zzdwp<K> zzaxm() {
        return this.zzhqo;
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.zzhrd.get(obj) != null;
    }

    public final int size() {
        return this.zzhrd.size();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
