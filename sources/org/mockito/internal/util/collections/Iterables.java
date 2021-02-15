package org.mockito.internal.util.collections;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

public class Iterables {
    public static <T> Iterable<T> toIterable(Enumeration<T> enumeration) {
        LinkedList linkedList = new LinkedList();
        while (enumeration.hasMoreElements()) {
            linkedList.add(enumeration.nextElement());
        }
        return linkedList;
    }

    public static <T> T firstOf(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new IllegalArgumentException("Cannot provide 1st element from empty iterable: " + iterable);
    }
}
