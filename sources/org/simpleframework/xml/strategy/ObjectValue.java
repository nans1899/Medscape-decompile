package org.simpleframework.xml.strategy;

class ObjectValue implements Value {
    private Class type;
    private Object value;

    public int getLength() {
        return 0;
    }

    public boolean isReference() {
        return false;
    }

    public ObjectValue(Class cls) {
        this.type = cls;
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
}
