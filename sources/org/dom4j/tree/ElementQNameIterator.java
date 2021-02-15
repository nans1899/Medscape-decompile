package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.QName;

public class ElementQNameIterator extends FilterIterator {
    private QName qName;

    public ElementQNameIterator(Iterator it, QName qName2) {
        super(it);
        this.qName = qName2;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Object obj) {
        if (obj instanceof Element) {
            return this.qName.equals(((Element) obj).getQName());
        }
        return false;
    }
}
