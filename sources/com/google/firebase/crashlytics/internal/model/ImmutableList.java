package com.google.firebase.crashlytics.internal.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class ImmutableList<E> implements List<E>, RandomAccess {
    private final List<E> immutableList;

    public static <E> ImmutableList<E> from(E... eArr) {
        return new ImmutableList<>(Arrays.asList(eArr));
    }

    public static <E> ImmutableList<E> from(List<E> list) {
        return new ImmutableList<>(list);
    }

    private ImmutableList(List<E> list) {
        this.immutableList = Collections.unmodifiableList(list);
    }

    public int size() {
        return this.immutableList.size();
    }

    public boolean isEmpty() {
        return this.immutableList.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.immutableList.contains(obj);
    }

    public Iterator<E> iterator() {
        return this.immutableList.iterator();
    }

    public Object[] toArray() {
        return this.immutableList.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.immutableList.toArray(tArr);
    }

    public boolean add(E e) {
        return this.immutableList.add(e);
    }

    public boolean remove(Object obj) {
        return this.immutableList.remove(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.immutableList.containsAll(collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        return this.immutableList.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        return this.immutableList.addAll(i, collection);
    }

    public boolean removeAll(Collection<?> collection) {
        return this.immutableList.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.immutableList.retainAll(collection);
    }

    public void clear() {
        this.immutableList.clear();
    }

    public boolean equals(Object obj) {
        return this.immutableList.equals(obj);
    }

    public int hashCode() {
        return this.immutableList.hashCode();
    }

    public E get(int i) {
        return this.immutableList.get(i);
    }

    public E set(int i, E e) {
        return this.immutableList.set(i, e);
    }

    public void add(int i, E e) {
        this.immutableList.add(i, e);
    }

    public E remove(int i) {
        return this.immutableList.remove(i);
    }

    public int indexOf(Object obj) {
        return this.immutableList.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.immutableList.lastIndexOf(obj);
    }

    public ListIterator<E> listIterator() {
        return this.immutableList.listIterator();
    }

    public ListIterator<E> listIterator(int i) {
        return this.immutableList.listIterator(i);
    }

    public List<E> subList(int i, int i2) {
        return this.immutableList.subList(i, i2);
    }
}
