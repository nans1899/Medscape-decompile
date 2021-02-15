package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
abstract class zzeij<E> extends AbstractList<E> implements zzekk<E> {
    private boolean zzieg = true;

    zzeij() {
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
        zzbed();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzbed();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzbed();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzbed();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzbed();
        super.clear();
    }

    public boolean zzbeb() {
        return this.zzieg;
    }

    public final void zzbec() {
        this.zzieg = false;
    }

    public E remove(int i) {
        zzbed();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzbed();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzbed();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzbed();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzbed();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzbed() {
        if (!this.zzieg) {
            throw new UnsupportedOperationException();
        }
    }
}
