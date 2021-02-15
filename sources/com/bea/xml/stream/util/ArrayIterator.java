package com.bea.xml.stream.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class ArrayIterator implements Iterator {
    private final Object[] array;
    private int index;
    private final int maxIndex;

    public ArrayIterator(Object[] objArr) {
        this(objArr, 0, objArr.length);
    }

    public ArrayIterator(Object[] objArr, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i > objArr.length) {
            throw new IllegalArgumentException();
        } else if (i2 <= objArr.length - i) {
            this.array = objArr;
            this.index = i;
            this.maxIndex = i2 + i;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean hasNext() {
        return this.index < this.maxIndex;
    }

    public Object next() {
        int i = this.index;
        if (i < this.maxIndex) {
            Object[] objArr = this.array;
            this.index = i + 1;
            return objArr[i];
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
