package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.NamedAccessNavigator;
import org.jaxen.UnsupportedAxisException;

public class IterableChildAxis extends IterableAxis {
    private static final long serialVersionUID = 1;

    public IterableChildAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getChildAxisIterator(obj);
    }

    public Iterator namedAccessIterator(Object obj, ContextSupport contextSupport, String str, String str2, String str3) throws UnsupportedAxisException {
        return ((NamedAccessNavigator) contextSupport.getNavigator()).getChildAxisIterator(obj, str, str2, str3);
    }

    public boolean supportsNamedAccess(ContextSupport contextSupport) {
        return contextSupport.getNavigator() instanceof NamedAccessNavigator;
    }
}
