package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class OverrideValue implements Value {
    private final Class type;
    private final Value value;

    public OverrideValue(Value value2, Class cls) {
        this.value = value2;
        this.type = cls;
    }

    public Object getValue() {
        return this.value.getValue();
    }

    public void setValue(Object obj) {
        this.value.setValue(obj);
    }

    public Class getType() {
        return this.type;
    }

    public int getLength() {
        return this.value.getLength();
    }

    public boolean isReference() {
        return this.value.isReference();
    }
}
