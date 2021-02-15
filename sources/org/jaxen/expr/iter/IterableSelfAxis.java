package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterableSelfAxis extends IterableAxis {
    private static final long serialVersionUID = 8292222516706760134L;

    public IterableSelfAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getSelfAxisIterator(obj);
    }
}
