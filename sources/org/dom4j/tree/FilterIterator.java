package org.dom4j.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class FilterIterator implements Iterator {
    private boolean first = true;
    private Object next;
    protected Iterator proxy;

    /* access modifiers changed from: protected */
    public abstract boolean matches(Object obj);

    public FilterIterator(Iterator it) {
        this.proxy = it;
    }

    public boolean hasNext() {
        if (this.first) {
            this.next = findNext();
            this.first = false;
        }
        if (this.next != null) {
            return true;
        }
        return false;
    }

    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            Object obj = this.next;
            this.next = findNext();
            return obj;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public Object findNext() {
        if (this.proxy != null) {
            while (this.proxy.hasNext()) {
                Object next2 = this.proxy.next();
                if (next2 != null && matches(next2)) {
                    return next2;
                }
            }
            this.proxy = null;
        }
        return null;
    }
}
