package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class Session implements Map {
    private final Map map;
    private final boolean strict;

    public Session() {
        this(true);
    }

    public Session(boolean z) {
        this.map = new HashMap();
        this.strict = z;
    }

    public boolean isStrict() {
        return this.strict;
    }

    public Map getMap() {
        return this.map;
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public Object get(Object obj) {
        return this.map.get(obj);
    }

    public Object put(Object obj, Object obj2) {
        return this.map.put(obj, obj2);
    }

    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    public void putAll(Map map2) {
        this.map.putAll(map2);
    }

    public Set keySet() {
        return this.map.keySet();
    }

    public Collection values() {
        return this.map.values();
    }

    public Set entrySet() {
        return this.map.entrySet();
    }

    public void clear() {
        this.map.clear();
    }
}
