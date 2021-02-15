package org.simpleframework.xml.strategy;

class Reference implements Value {
    private Class type;
    private Object value;

    public int getLength() {
        return 0;
    }

    public boolean isReference() {
        return true;
    }

    public Reference(Object obj, Class cls) {
        this.value = obj;
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
