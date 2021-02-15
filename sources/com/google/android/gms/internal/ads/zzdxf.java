package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdxf<E> extends zzdws<E> {
    static final zzdxf<Object> zzhrf = new zzdxf(new Object[0], 0, (Object[]) null, 0, 0);
    private final transient int mask;
    private final transient int size;
    private final transient int zzahv;
    private final transient Object[] zzhrg;
    private final transient Object[] zzhrh;

    zzdxf(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zzhrg = objArr;
        this.zzhrh = objArr2;
        this.mask = i2;
        this.zzahv = i;
        this.size = i3;
    }

    /* access modifiers changed from: package-private */
    public final int zzaxk() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxn() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaxp() {
        return true;
    }

    public final boolean contains(@NullableDecl Object obj) {
        int i;
        Object[] objArr = this.zzhrh;
        if (obj == null || objArr == null) {
            return false;
        }
        if (obj == null) {
            i = 0;
        } else {
            i = obj.hashCode();
        }
        int zzel = zzdwl.zzel(i);
        while (true) {
            int i2 = zzel & this.mask;
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            zzel = i2 + 1;
        }
    }

    public final int size() {
        return this.size;
    }

    public final zzdxg<E> zzaxi() {
        return (zzdxg) zzaxm().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzaxj() {
        return this.zzhrg;
    }

    /* access modifiers changed from: package-private */
    public final int zzaxl() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzhrg, 0, objArr, i, this.size);
        return i + this.size;
    }

    /* access modifiers changed from: package-private */
    public final zzdwp<E> zzaxq() {
        return zzdwp.zzb(this.zzhrg, this.size);
    }

    public final int hashCode() {
        return this.zzahv;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
