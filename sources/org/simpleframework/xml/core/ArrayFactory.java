package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.Position;

class ArrayFactory extends Factory {
    public ArrayFactory(Context context, Type type) {
        super(context, type);
    }

    public Object getInstance() throws Exception {
        Class componentType = getComponentType();
        if (componentType != null) {
            return Array.newInstance(componentType, 0);
        }
        return null;
    }

    public Instance getInstance(InputNode inputNode) throws Exception {
        Position position = inputNode.getPosition();
        Value override = getOverride(inputNode);
        if (override != null) {
            return getInstance(override, override.getType());
        }
        throw new ElementException("Array length required for %s at %s", this.type, position);
    }

    private Instance getInstance(Value value, Class cls) throws Exception {
        Class componentType = getComponentType();
        if (componentType.isAssignableFrom(cls)) {
            return new ArrayInstance(value);
        }
        throw new InstantiationException("Array of type %s cannot hold %s for %s", componentType, cls, this.type);
    }

    private Class getComponentType() throws Exception {
        Class type = getType();
        if (type.isArray()) {
            return type.getComponentType();
        }
        throw new InstantiationException("The %s not an array for %s", type, this.type);
    }
}
