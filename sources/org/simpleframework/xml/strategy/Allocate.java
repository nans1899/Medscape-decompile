package org.simpleframework.xml.strategy;

import java.util.Map;

class Allocate implements Value {
    private String key;
    private Map map;
    private Value value;

    public boolean isReference() {
        return false;
    }

    public Allocate(Value value2, Map map2, String str) {
        this.value = value2;
        this.map = map2;
        this.key = str;
    }

    public Object getValue() {
        return this.map.get(this.key);
    }

    public void setValue(Object obj) {
        String str = this.key;
        if (str != null) {
            this.map.put(str, obj);
        }
        this.value.setValue(obj);
    }

    public Class getType() {
        return this.value.getType();
    }

    public int getLength() {
        return this.value.getLength();
    }
}
