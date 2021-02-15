package org.simpleframework.xml.convert;

import org.simpleframework.xml.strategy.Value;

class Reference implements Value {
    private Class actual;
    private Object data;
    private Value value;

    public int getLength() {
        return 0;
    }

    public boolean isReference() {
        return true;
    }

    public Reference(Value value2, Object obj, Class cls) {
        this.actual = cls;
        this.value = value2;
        this.data = obj;
    }

    public Class getType() {
        Object obj = this.data;
        if (obj != null) {
            return obj.getClass();
        }
        return this.actual;
    }

    public Object getValue() {
        return this.data;
    }

    public void setValue(Object obj) {
        Value value2 = this.value;
        if (value2 != null) {
            value2.setValue(obj);
        }
        this.data = obj;
    }
}
