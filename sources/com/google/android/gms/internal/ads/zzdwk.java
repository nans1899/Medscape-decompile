package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public abstract class zzdwk<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zzhqj = new Object[0];

    zzdwk() {
    }

    public abstract boolean contains(@NullableDecl Object obj);

    /* renamed from: zzaxi */
    public abstract zzdxg<E> iterator();

    /* access modifiers changed from: package-private */
    @NullableDecl
    public Object[] zzaxj() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean zzaxn();

    public final Object[] toArray() {
        return toArray(zzhqj);
    }

    public final <T> T[] toArray(T[] tArr) {
        zzdwd.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] zzaxj = zzaxj();
            if (zzaxj != null) {
                return Arrays.copyOfRange(zzaxj, zzaxk(), zzaxl(), tArr.getClass());
            }
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zza(tArr, 0);
        return tArr;
    }

    /* access modifiers changed from: package-private */
    public int zzaxk() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zzaxl() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public zzdwp<E> zzaxm() {
        return isEmpty() ? zzdwp.zzaxo() : zzdwp.zzc(toArray());
    }

    /* access modifiers changed from: package-private */
    public int zza(Object[] objArr, int i) {
        zzdxg zzdxg = (zzdxg) iterator();
        while (zzdxg.hasNext()) {
            objArr[i] = zzdxg.next();
            i++;
        }
        return i;
    }
}
