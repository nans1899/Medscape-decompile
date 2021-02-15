package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

class PrimitiveFactory extends Factory {
    public PrimitiveFactory(Context context, Type type) {
        super(context, type);
    }

    public PrimitiveFactory(Context context, Type type, Class cls) {
        super(context, type, cls);
    }

    public Instance getInstance(InputNode inputNode) throws Exception {
        Value override = getOverride(inputNode);
        Class type = getType();
        if (override == null) {
            return this.context.getInstance(type);
        }
        return new ObjectInstance(this.context, override);
    }

    public Object getInstance(String str, Class cls) throws Exception {
        return this.support.read(str, cls);
    }

    public String getText(Object obj) throws Exception {
        Class<?> cls = obj.getClass();
        if (cls.isEnum()) {
            return this.support.write(obj, cls);
        }
        return this.support.write(obj, cls);
    }
}
