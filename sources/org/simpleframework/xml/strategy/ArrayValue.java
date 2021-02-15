package org.simpleframework.xml.strategy;

class ArrayValue implements Value {
    private int size;
    private Class type;
    private Object value;

    public boolean isReference() {
        return false;
    }

    public ArrayValue(Class cls, int i) {
        this.type = cls;
        this.size = i;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public Class getType() {
        return this.type;
    }

    public int getLength() {
        return this.size;
    }
}
