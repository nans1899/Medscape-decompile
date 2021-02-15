package org.jsoup.helper;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ChangeNotifyingArrayList<E> extends ArrayList<E> {
    public abstract void onContentsChanged();

    public ChangeNotifyingArrayList(int i) {
        super(i);
    }

    public E set(int i, E e) {
        onContentsChanged();
        return super.set(i, e);
    }

    public boolean add(E e) {
        onContentsChanged();
        return super.add(e);
    }

    public void add(int i, E e) {
        onContentsChanged();
        super.add(i, e);
    }

    public E remove(int i) {
        onContentsChanged();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        onContentsChanged();
        return super.remove(obj);
    }

    public void clear() {
        onContentsChanged();
        super.clear();
    }

    public boolean addAll(Collection<? extends E> collection) {
        onContentsChanged();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        onContentsChanged();
        return super.addAll(i, collection);
    }

    /* access modifiers changed from: protected */
    public void removeRange(int i, int i2) {
        onContentsChanged();
        super.removeRange(i, i2);
    }

    public boolean removeAll(Collection<?> collection) {
        onContentsChanged();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        onContentsChanged();
        return super.retainAll(collection);
    }
}
