package org.mockito.internal.util.collections;

import java.util.Iterator;
import java.util.LinkedList;

public class IdentitySet {
    private final LinkedList list = new LinkedList();

    public boolean contains(Object obj) {
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            if (it.next() == obj) {
                return true;
            }
        }
        return false;
    }

    public void add(Object obj) {
        this.list.add(obj);
    }
}
