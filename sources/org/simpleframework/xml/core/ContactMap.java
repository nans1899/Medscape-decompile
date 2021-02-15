package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedHashMap;

class ContactMap extends LinkedHashMap<Object, Contact> implements Iterable<Contact> {
    ContactMap() {
    }

    public Iterator<Contact> iterator() {
        return values().iterator();
    }
}
