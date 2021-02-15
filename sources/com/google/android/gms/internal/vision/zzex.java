package com.google.android.gms.internal.vision;

import java.util.Iterator;
import net.bytebuddy.pool.TypePool;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzex<E> extends zzej<E> {
    private final transient E zznu;
    private transient int zznv;

    zzex(E e) {
        this.zznu = zzde.checkNotNull(e);
    }

    public final int size() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcu() {
        return false;
    }

    zzex(E e, int i) {
        this.zznu = e;
        this.zznv = i;
    }

    public final boolean contains(Object obj) {
        return this.zznu.equals(obj);
    }

    public final zzfa<E> zzcp() {
        return new zzeo(this.zznu);
    }

    /* access modifiers changed from: package-private */
    public final zzee<E> zzda() {
        return zzee.zzg(this.zznu);
    }

    /* access modifiers changed from: package-private */
    public final int zza(Object[] objArr, int i) {
        objArr[i] = this.zznu;
        return i + 1;
    }

    public final int hashCode() {
        int i = this.zznv;
        if (i != 0) {
            return i;
        }
        int hashCode = this.zznu.hashCode();
        this.zznv = hashCode;
        return hashCode;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcz() {
        return this.zznv != 0;
    }

    public final String toString() {
        String obj = this.zznu.toString();
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
