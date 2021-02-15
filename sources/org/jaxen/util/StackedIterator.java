package org.jaxen.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Set;
import org.jaxen.Navigator;

public abstract class StackedIterator implements Iterator {
    private Set created = new HashSet();
    private LinkedList iteratorStack = new LinkedList();
    private Navigator navigator;

    /* access modifiers changed from: protected */
    public abstract Iterator createIterator(Object obj);

    public StackedIterator(Object obj, Navigator navigator2) {
        init(obj, navigator2);
    }

    protected StackedIterator() {
    }

    /* access modifiers changed from: protected */
    public void init(Object obj, Navigator navigator2) {
        this.navigator = navigator2;
    }

    /* access modifiers changed from: protected */
    public Iterator internalCreateIterator(Object obj) {
        if (this.created.contains(obj)) {
            return null;
        }
        this.created.add(obj);
        return createIterator(obj);
    }

    public boolean hasNext() {
        Iterator currentIterator = currentIterator();
        if (currentIterator == null) {
            return false;
        }
        return currentIterator.hasNext();
    }

    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            Object next = currentIterator().next();
            pushIterator(internalCreateIterator(next));
            return next;
        }
        throw new NoSuchElementException();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public void pushIterator(Iterator it) {
        if (it != null) {
            this.iteratorStack.addFirst(it);
        }
    }

    private Iterator currentIterator() {
        while (this.iteratorStack.size() > 0) {
            Iterator it = (Iterator) this.iteratorStack.getFirst();
            if (it.hasNext()) {
                return it;
            }
            this.iteratorStack.removeFirst();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }
}
