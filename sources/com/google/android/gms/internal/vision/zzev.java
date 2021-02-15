package com.google.android.gms.internal.vision;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzev<E> extends zzej<E> {
    static final zzev<Object> zznq = new zzev(new Object[0], 0, (Object[]) null, 0, 0);
    private final transient int mask;
    private final transient int size;
    private final transient Object[] zznr;
    private final transient Object[] zzns;
    private final transient int zznt;

    zzev(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zznr = objArr;
        this.zzns = objArr2;
        this.mask = i2;
        this.zznt = i;
        this.size = i3;
    }

    /* access modifiers changed from: package-private */
    public final int zzcr() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcz() {
        return true;
    }

    public final boolean contains(@NullableDecl Object obj) {
        Object[] objArr = this.zzns;
        if (obj == null || objArr == null) {
            return false;
        }
        int zzf = zzec.zzf(obj);
        while (true) {
            int i = zzf & this.mask;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            zzf = i + 1;
        }
    }

    public final int size() {
        return this.size;
    }

    public final zzfa<E> zzcp() {
        return (zzfa) zzct().iterator();
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzcq() {
        return this.zznr;
    }

    /* access modifiers changed from: package-private */
    public final int zzcs() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zznr, 0, objArr, i, this.size);
        return i + this.size;
    }

    /* access modifiers changed from: package-private */
    public final zzee<E> zzda() {
        return zzee.zzb(this.zznr, this.size);
    }

    public final int hashCode() {
        return this.zznt;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
