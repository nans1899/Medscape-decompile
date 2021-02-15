package org.dom4j.tree;

import java.util.Iterator;

public class SingleIterator implements Iterator {
    private boolean first = true;
    private Object object;

    public SingleIterator(Object obj) {
        this.object = obj;
    }

    public boolean hasNext() {
        return this.first;
    }

    public Object next() {
        Object obj = this.object;
        this.object = null;
        this.first = false;
        return obj;
    }

    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported by this iterator");
    }
}
