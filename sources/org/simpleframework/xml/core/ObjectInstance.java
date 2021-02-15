package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class ObjectInstance implements Instance {
    private final Context context;
    private final Class type;
    private final Value value;

    public ObjectInstance(Context context2, Value value2) {
        this.type = value2.getType();
        this.context = context2;
        this.value = value2;
    }

    public Object getInstance() throws Exception {
        if (this.value.isReference()) {
            return this.value.getValue();
        }
        Object instance = getInstance(this.type);
        Value value2 = this.value;
        if (value2 != null) {
            value2.setValue(instance);
        }
        return instance;
    }

    public Object getInstance(Class cls) throws Exception {
        return this.context.getInstance(cls).getInstance();
    }

    public Object setInstance(Object obj) {
        Value value2 = this.value;
        if (value2 != null) {
            value2.setValue(obj);
        }
        return obj;
    }

    public boolean isReference() {
        return this.value.isReference();
    }

    public Class getType() {
        return this.type;
    }
}
