package org.jaxen.expr.iter;

import java.util.Iterator;
import org.jaxen.ContextSupport;
import org.jaxen.UnsupportedAxisException;

public class IterableFollowingSiblingAxis extends IterableAxis {
    private static final long serialVersionUID = 4412705219546610009L;

    public IterableFollowingSiblingAxis(int i) {
        super(i);
    }

    public Iterator iterator(Object obj, ContextSupport contextSupport) throws UnsupportedAxisException {
        return contextSupport.getNavigator().getFollowingSiblingAxisIterator(obj);
    }
}
