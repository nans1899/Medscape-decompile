package org.jaxen.expr.iter;

import java.io.Serializable;
import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public abstract class IterableAxis implements Serializable {
    private int value;

    public abstract Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException;

    public boolean supportsNamedAccess(ContextSupport contextSupport) {
        return false;
    }

    public IterableAxis(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public Iterator namedAccessIterator(Object obj, ContextSupport contextSupport, String str, String str2, String str3) throws UnsupportedAxisException {
        throw new UnsupportedOperationException("Named access unsupported");
    }
}
