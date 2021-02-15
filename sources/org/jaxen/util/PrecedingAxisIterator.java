package org.jaxen.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.jaxen.JaxenConstants;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class PrecedingAxisIterator implements Iterator {
    private Iterator ancestorOrSelf;
    private ListIterator childrenOrSelf = JaxenConstants.EMPTY_LIST_ITERATOR;
    private Navigator navigator;
    private Iterator precedingSibling = JaxenConstants.EMPTY_ITERATOR;
    private ArrayList stack = new ArrayList();

    public PrecedingAxisIterator(Object obj, Navigator navigator2) throws UnsupportedAxisException {
        this.navigator = navigator2;
        this.ancestorOrSelf = navigator2.getAncestorOrSelfAxisIterator(obj);
    }

    public boolean hasNext() {
        while (!this.childrenOrSelf.hasPrevious()) {
            try {
                if (this.stack.isEmpty()) {
                    while (!this.precedingSibling.hasNext()) {
                        if (!this.ancestorOrSelf.hasNext()) {
                            return false;
                        }
                        this.precedingSibling = new PrecedingSiblingAxisIterator(this.ancestorOrSelf.next(), this.navigator);
                    }
                    this.childrenOrSelf = childrenOrSelf(this.precedingSibling.next());
                } else {
                    this.childrenOrSelf = (ListIterator) this.stack.remove(this.stack.size() - 1);
                }
            } catch (UnsupportedAxisException e) {
                throw new JaxenRuntimeException((Throwable) e);
            }
        }
        return true;
    }

    private ListIterator childrenOrSelf(Object obj) {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(obj);
            Iterator childAxisIterator = this.navigator.getChildAxisIterator(obj);
            if (childAxisIterator != null) {
                while (childAxisIterator.hasNext()) {
                    arrayList.add(childAxisIterator.next());
                }
            }
            return arrayList.listIterator(arrayList.size());
        } catch (UnsupportedAxisException e) {
            throw new JaxenRuntimeException((Throwable) e);
        }
    }

    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            while (true) {
                Object previous = this.childrenOrSelf.previous();
                if (!this.childrenOrSelf.hasPrevious()) {
                    return previous;
                }
                this.stack.add(this.childrenOrSelf);
                this.childrenOrSelf = childrenOrSelf(previous);
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
