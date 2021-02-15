package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterableAncestorOrSelfAxis extends IterableAxis {
    private static final long serialVersionUID = 1;

    public IterableAncestorOrSelfAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getAncestorOrSelfAxisIterator(obj);
    }
}
