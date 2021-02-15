package org.simpleframework.xml.core;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;

class MapFactory extends Factory {
    public MapFactory(Context context, Type type) {
        super(context, type);
    }

    public Object getInstance() throws Exception {
        Class type = getType();
        Class conversion = !isInstantiable(type) ? getConversion(type) : type;
        if (isMap(conversion)) {
            return conversion.newInstance();
        }
        throw new InstantiationException("Invalid map %s for %s", type, this.type);
    }

    public Instance getInstance(InputNode inputNode) throws Exception {
        Value override = getOverride(inputNode);
        Class type = getType();
        if (override != null) {
            return getInstance(override);
        }
        if (!isInstantiable(type)) {
            type = getConversion(type);
        }
        if (isMap(type)) {
            return this.context.getInstance(type);
        }
        throw new InstantiationException("Invalid map %s for %s", type, this.type);
    }

    public Instance getInstance(Value value) throws Exception {
        Class type = value.getType();
        if (!isInstantiable(type)) {
            type = getConversion(type);
        }
        if (isMap(type)) {
            return new ConversionInstance(this.context, value, type);
        }
        throw new InstantiationException("Invalid map %s for %s", type, this.type);
    }

    public Class getConversion(Class cls) throws Exception {
        if (cls.isAssignableFrom(HashMap.class)) {
            return HashMap.class;
        }
        if (cls.isAssignableFrom(TreeMap.class)) {
            return TreeMap.class;
        }
        throw new InstantiationException("Cannot instantiate %s for %s", cls, this.type);
    }

    private boolean isMap(Class cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
