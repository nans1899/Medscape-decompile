package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterableParentAxis extends IterableAxis {
    private static final long serialVersionUID = -7521574185875636490L;

    public IterableParentAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getParentAxisIterator(obj);
    }
}
