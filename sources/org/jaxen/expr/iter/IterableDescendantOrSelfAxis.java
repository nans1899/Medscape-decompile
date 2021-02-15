package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterableDescendantOrSelfAxis extends IterableAxis {
    private static final long serialVersionUID = 2956703237251023850L;

    public IterableDescendantOrSelfAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getDescendantOrSelfAxisIterator(obj);
    }
}
