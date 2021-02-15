package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class ConversionInstance implements Instance {
    private final Context context;
    private final Class convert;
    private final Value value;

    public ConversionInstance(Context context2, Value value2, Class cls) throws Exception {
        this.context = context2;
        this.convert = cls;
        this.value = value2;
    }

    public Object getInstance() throws Exception {
        if (this.value.isReference()) {
            return this.value.getValue();
        }
        Object instance = getInstance(this.convert);
        if (instance != null) {
            setInstance(instance);
        }
        return instance;
    }

    public Object getInstance(Class cls) throws Exception {
        return this.context.getInstance(cls).getInstance();
    }

    public Object setInstance(Object obj) throws Exception {
        Value value2 = this.value;
        if (value2 != null) {
            value2.setValue(obj);
        }
        return obj;
    }

    public Class getType() {
        return this.convert;
    }

    public boolean isReference() {
        return this.value.isReference();
    }
}
