package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import org.simpleframework.xml.util.Entry;

public class Dictionary<T extends Entry> extends AbstractSet<T> {
    protected final Table<T> map = new Table<>();

    private static class Table<T> extends HashMap<String, T> {
    }

    public boolean add(T t) {
        return this.map.put(t.getName(), t) != null;
    }

    public int size() {
        return this.map.size();
    }

    public Iterator<T> iterator() {
        return this.map.values().iterator();
    }

    public T get(String str) {
        return (Entry) this.map.get(str);
    }

    public T remove(String str) {
        return (Entry) this.map.remove(str);
    }
}
