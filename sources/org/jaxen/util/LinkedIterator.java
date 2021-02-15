package org.jaxen.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedIterator implements Iterator {
    private int cur = 0;
    private List iterators = new ArrayList();

    public void addIterator(Iterator it) {
        this.iterators.add(it);
    }

    public boolean hasNext() {
        if (this.cur >= this.iterators.size()) {
            return false;
        }
        boolean hasNext = ((Iterator) this.iterators.get(this.cur)).hasNext();
        if (hasNext || this.cur >= this.iterators.size()) {
            return hasNext;
        }
        this.cur++;
        return hasNext();
    }

    public Object next() {
        if (hasNext()) {
            return ((Iterator) this.iterators.get(this.cur)).next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
