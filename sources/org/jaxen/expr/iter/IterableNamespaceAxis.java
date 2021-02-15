package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterableNamespaceAxis extends IterableAxis {
    private static final long serialVersionUID = -8022585664651357087L;

    public IterableNamespaceAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getNamespaceAxisIterator(obj);
    }
}
