package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
abstract class zzcp<E> extends AbstractList<E> implements zzee<E> {
    private boolean zzgb = true;

    zzcp() {
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
        zzaj();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzaj();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzaj();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzaj();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzaj();
        super.clear();
    }

    public boolean zzah() {
        return this.zzgb;
    }

    public final void zzai() {
        this.zzgb = false;
    }

    public E remove(int i) {
        zzaj();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzaj();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzaj();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzaj();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzaj();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzaj() {
        if (!this.zzgb) {
            throw new UnsupportedOperationException();
        }
    }
}
