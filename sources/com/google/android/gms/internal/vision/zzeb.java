package com.google.android.gms.internal.vision;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzeb<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zzmu = new Object[0];

    zzeb() {
    }

    public abstract boolean contains(@NullableDecl Object obj);

    /* renamed from: zzcp */
    public abstract zzfa<E> iterator();

    /* access modifiers changed from: package-private */
    @NullableDecl
    public Object[] zzcq() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean zzcu();

    public final Object[] toArray() {
        return toArray(zzmu);
    }

    public final <T> T[] toArray(T[] tArr) {
        zzde.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] zzcq = zzcq();
            if (zzcq != null) {
                return Arrays.copyOfRange(zzcq, zzcr(), zzcs(), tArr.getClass());
            }
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        zza(tArr, 0);
        return tArr;
    }

    /* access modifiers changed from: package-private */
    public int zzcr() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int zzcs() {
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

    public zzee<E> zzct() {
        return isEmpty() ? zzee.zzcv() : zzee.zza(toArray());
    }

    /* access modifiers changed from: package-private */
    public int zza(Object[] objArr, int i) {
        zzfa zzfa = (zzfa) iterator();
        while (zzfa.hasNext()) {
            objArr[i] = zzfa.next();
            i++;
        }
        return i;
    }
}
