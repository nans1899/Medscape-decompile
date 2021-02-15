package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
abstract class zzgi<E> extends AbstractList<E> implements zzik<E> {
    private boolean zztf = true;

    zzgi() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public boolean add(E e) {
        zzek();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzek();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzek();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzek();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzek();
        super.clear();
    }

    public boolean zzei() {
        return this.zztf;
    }

    public final void zzej() {
        this.zztf = false;
    }

    public E remove(int i) {
        zzek();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzek();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzek();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzek();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzek();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzek() {
        if (!this.zztf) {
            throw new UnsupportedOperationException();
        }
    }
}
