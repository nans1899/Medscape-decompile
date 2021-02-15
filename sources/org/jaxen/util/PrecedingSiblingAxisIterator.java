package org.jaxen.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.jaxen.JaxenConstants;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class PrecedingSiblingAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;
    private Object nextObj;
    private Iterator siblingIter;

    public PrecedingSiblingAxisIterator(Object obj, Navigator navigator2) throws UnsupportedAxisException {
        this.contextNode = obj;
        this.navigator = navigator2;
        init();
        if (this.siblingIter.hasNext()) {
            this.nextObj = this.siblingIter.next();
        }
    }

    private void init() throws UnsupportedAxisException {
        Object parentNode = this.navigator.getParentNode(this.contextNode);
        if (parentNode != null) {
            Iterator childAxisIterator = this.navigator.getChildAxisIterator(parentNode);
            LinkedList linkedList = new LinkedList();
            while (childAxisIterator.hasNext()) {
                Object next = childAxisIterator.next();
                if (next.equals(this.contextNode)) {
                    break;
                }
                linkedList.addFirst(next);
            }
            this.siblingIter = linkedList.iterator();
            return;
        }
        this.siblingIter = JaxenConstants.EMPTY_ITERATOR;
    }

    public boolean hasNext() {
        return this.nextObj != null;
    }

    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            Object obj = this.nextObj;
            if (this.siblingIter.hasNext()) {
                this.nextObj = this.siblingIter.next();
            } else {
                this.nextObj = null;
            }
            return obj;
        }
        throw new NoSuchElementException();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
