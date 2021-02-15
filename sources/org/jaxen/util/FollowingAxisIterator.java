package org.jaxen.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jaxen.JaxenConstants;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class FollowingAxisIterator implements Iterator {
    private Object contextNode;
    private Iterator currentSibling = JaxenConstants.EMPTY_ITERATOR;
    private Navigator navigator;
    private Iterator siblings;

    public FollowingAxisIterator(Object obj, Navigator navigator2) throws UnsupportedAxisException {
        this.contextNode = obj;
        this.navigator = navigator2;
        this.siblings = navigator2.getFollowingSiblingAxisIterator(obj);
    }

    private boolean goForward() {
        while (!this.siblings.hasNext()) {
            if (!goUp()) {
                return false;
            }
        }
        this.currentSibling = new DescendantOrSelfAxisIterator(this.siblings.next(), this.navigator);
        return true;
    }

    private boolean goUp() {
        Object obj = this.contextNode;
        if (obj == null || this.navigator.isDocument(obj)) {
            return false;
        }
        try {
            Object parentNode = this.navigator.getParentNode(this.contextNode);
            this.contextNode = parentNode;
            if (parentNode == null || this.navigator.isDocument(parentNode)) {
                return false;
            }
            this.siblings = this.navigator.getFollowingSiblingAxisIterator(this.contextNode);
            return true;
        } catch (UnsupportedAxisException e) {
            throw new JaxenRuntimeException((Throwable) e);
        }
    }

    public boolean hasNext() {
        while (!this.currentSibling.hasNext()) {
            if (!goForward()) {
                return false;
            }
        }
        return true;
    }

    public Object next() throws NoSuchElementException {
        if (hasNext()) {
            return this.currentSibling.next();
        }
        throw new NoSuchElementException();
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
