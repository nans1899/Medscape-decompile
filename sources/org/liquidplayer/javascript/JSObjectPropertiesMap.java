package org.liquidplayer.javascript;

import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class JSObjectPropertiesMap<V> extends JSObjectWrapper implements Map<String, V> {
    private final Class<V> mType;

    public /* bridge */ /* synthetic */ JSObject getJSObject() {
        return super.getJSObject();
    }

    public JSObjectPropertiesMap(JSObject jSObject, Class<V> cls) {
        super(jSObject);
        this.mType = cls;
    }

    public JSObjectPropertiesMap(JSContext jSContext, Map map, Class<V> cls) {
        super(new JSObject(jSContext, map));
        this.mType = cls;
    }

    public JSObjectPropertiesMap(JSContext jSContext, Class<V> cls) {
        super(new JSObject(jSContext));
        this.mType = cls;
    }

    public int size() {
        return propertyNames().length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(Object obj) {
        return hasProperty(obj.toString());
    }

    public boolean containsValue(Object obj) {
        for (String property : propertyNames()) {
            if (property(property).equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public V get(Object obj) {
        JSValue property = property(obj.toString());
        if (property.isUndefined().booleanValue()) {
            return null;
        }
        return property.toJavaObject(this.mType);
    }

    public V put(String str, V v) {
        V v2 = get(str);
        property(str, v);
        return v2;
    }

    public V remove(Object obj) {
        V v = get(obj);
        deleteProperty(obj.toString());
        return v;
    }

    public void putAll(Map<? extends String, ? extends V> map) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj != null) {
                put(str, obj);
            }
        }
    }

    public void clear() {
        for (String deleteProperty : propertyNames()) {
            deleteProperty(deleteProperty);
        }
    }

    public Set keySet() {
        return new HashSet(Arrays.asList(propertyNames()));
    }

    public Collection<V> values() {
        return new AbstractList<V>() {
            public V get(int i) {
                String[] propertyNames = JSObjectPropertiesMap.this.propertyNames();
                if (i <= propertyNames.length) {
                    return JSObjectPropertiesMap.this.get(propertyNames[i]);
                }
                throw new IndexOutOfBoundsException();
            }

            public int size() {
                return JSObjectPropertiesMap.this.propertyNames().length;
            }

            public boolean contains(Object obj) {
                return JSObjectPropertiesMap.this.containsValue(obj);
            }
        };
    }

    private class SetIterator implements Iterator<Map.Entry<String, V>> {
        private String current = null;
        private String removal = null;

        SetIterator() {
            String[] propertyNames = JSObjectPropertiesMap.this.propertyNames();
            if (propertyNames.length > 0) {
                this.current = propertyNames[0];
            }
        }

        public boolean hasNext() {
            if (this.current == null) {
                return false;
            }
            for (String equals : JSObjectPropertiesMap.this.propertyNames()) {
                if (this.current.equals(equals)) {
                    return true;
                }
            }
            return false;
        }

        public Map.Entry<String, V> next() {
            AnonymousClass1 r4;
            if (this.current != null) {
                String[] propertyNames = JSObjectPropertiesMap.this.propertyNames();
                int i = 0;
                while (true) {
                    if (i >= propertyNames.length) {
                        r4 = null;
                        break;
                    } else if (this.current.equals(propertyNames[i])) {
                        final String str = propertyNames[i];
                        r4 = new Map.Entry<String, V>() {
                            public String getKey() {
                                return str;
                            }

                            public V getValue() {
                                return JSObjectPropertiesMap.this.get(str);
                            }

                            public V setValue(V v) {
                                return JSObjectPropertiesMap.this.put(str, v);
                            }
                        };
                        break;
                    } else {
                        i++;
                    }
                }
                this.removal = this.current;
                int i2 = i + 1;
                if (i2 < propertyNames.length) {
                    this.current = propertyNames[i2];
                } else {
                    this.current = null;
                }
                if (r4 != null) {
                    return r4;
                }
                throw new NoSuchElementException();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            String str = this.removal;
            if (str != null) {
                JSObjectPropertiesMap.this.deleteProperty(str);
                this.removal = null;
                return;
            }
            throw new NoSuchElementException();
        }
    }

    public Set<Map.Entry<String, V>> entrySet() {
        return new AbstractSet<Map.Entry<String, V>>() {
            public Iterator<Map.Entry<String, V>> iterator() {
                return new SetIterator();
            }

            public int size() {
                return JSObjectPropertiesMap.this.propertyNames().length;
            }
        };
    }
}
