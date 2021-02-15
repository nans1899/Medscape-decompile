package org.jaxen.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class AncestorOrSelfAxisIterator implements Iterator {
    private Object contextNode;
    private Navigator navigator;

    public AncestorOrSelfAxisIterator(Object obj, Navigator navigator2) {
        this.contextNode = obj;
        this.navigator = navigator2;
    }

    public boolean hasNext() {
        return this.contextNode != null;
    }

    public Object next() {
        try {
            if (hasNext()) {
                Object obj = this.contextNode;
                this.contextNode = this.navigator.getParentNode(this.contextNode);
                return obj;
            }
            throw new NoSuchElementException("Exhausted ancestor-or-self axis");
        } catch (UnsupportedAxisException e) {
            throw new JaxenRuntimeException((Throwable) e);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
