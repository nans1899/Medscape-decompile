package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterablePrecedingSiblingAxis extends IterableAxis {
    private static final long serialVersionUID = -3140080721715120745L;

    public IterablePrecedingSiblingAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getPrecedingSiblingAxisIterator(obj);
    }
}
