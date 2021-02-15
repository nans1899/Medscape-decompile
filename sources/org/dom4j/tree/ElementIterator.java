package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;

public class ElementIterator extends FilterIterator {
    public ElementIterator(Iterator it) {
        super(it);
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        return obj instanceof Element;
    }
}
