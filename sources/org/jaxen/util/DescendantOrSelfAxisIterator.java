package org.jaxen.util;

import java.util.Iterator;
import org.jaxen.Navigator;

public class DescendantOrSelfAxisIterator extends DescendantAxisIterator {
    public DescendantOrSelfAxisIterator(Object obj, Navigator navigator) {
        super(navigator, (Iterator) new SingleObjectIterator(obj));
    }
}
