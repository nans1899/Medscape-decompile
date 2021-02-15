package com.google.android.gms.internal.ads;

import java.util.Iterator;
import net.bytebuddy.pool.TypePool;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdxh<E> extends zzdws<E> {
    private final transient E zzhri;
    private transient int zzhrj;

    zzdxh(E e) {
        this.zzhri = zzdwd.checkNotNull(e);
    }

    public final int size() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return false;
    }

    zzdxh(E e, int i) {
        this.zzhri = e;
        this.zzhrj = i;
    }

    public final boolean contains(Object obj) {
        return this.zzhri.equals(obj);
    }

    public final zzdxg<E> zzaxi() {
        return new zzdwu(this.zzhri);
    }

    /* access modifiers changed from: package-private */
    public final zzdwp<E> zzaxq() {
        return zzdwp.zzac(this.zzhri);
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        objArr[i] = this.zzhri;
        return i + 1;
    }

    public final int hashCode() {
        int i = this.zzhrj;
        if (i != 0) {
            return i;
        }
        int hashCode = this.zzhri.hashCode();
        this.zzhrj = hashCode;
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxp() {
        return this.zzhrj != 0;
    }

    public final String toString() {
        String obj = this.zzhri.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 2);
        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        sb.append(obj);
        sb.append(']');
        return sb.toString();
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
