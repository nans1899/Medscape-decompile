package org.jaxen.util;

import org.jaxen.Navigator;

public class AncestorAxisIterator extends AncestorOrSelfAxisIterator {
    public AncestorAxisIterator(Object obj, Navigator navigator) {
        super(obj, navigator);
        next();
    }
}
