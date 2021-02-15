package org.jaxen.util;

import java.util.AbstractList;

public class SingletonList extends AbstractList {
    private final Object element;

    public int size() {
        return 1;
    }

    public SingletonList(Object obj) {
        this.element = obj;
    }

    public Object get(int i) {
        if (i == 0) {
            return this.element;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i);
        stringBuffer.append(" != 0");
        throw new IndexOutOfBoundsException(stringBuffer.toString());
    }
}
