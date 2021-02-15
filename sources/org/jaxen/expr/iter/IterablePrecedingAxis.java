package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterablePrecedingAxis extends IterableAxis {
    private static final long serialVersionUID = 587333938258540052L;

    public IterablePrecedingAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getPrecedingAxisIterator(obj);
    }
}
