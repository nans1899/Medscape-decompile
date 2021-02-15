package com.fasterxml.jackson.databind.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>, Iterable<T> {
    private final T[] _a;
    private int _index = 0;

    public Iterator<T> iterator() {
        return this;
    }

    public ArrayIterator(T[] tArr) {
        this._a = tArr;
    }

    public boolean hasNext() {
        return this._index < this._a.length;
    }

    public T next() {
        int i = this._index;
        T[] tArr = this._a;
        if (i < tArr.length) {
            this._index = i + 1;
            return tArr[i];
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
