package org.jaxen.javabean;

import java.util.Iterator;

public class ElementIterator implements Iterator {
    private Iterator iterator;
    private String name;
    private Element parent;

    public ElementIterator(Element element, String str, Iterator it) {
        this.parent = element;
        this.name = str;
        this.iterator = it;
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public Object next() {
        return new Element(this.parent, this.name, this.iterator.next());
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
