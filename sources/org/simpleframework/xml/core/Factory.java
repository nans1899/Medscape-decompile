package org.simpleframework.xml.core;

import java.lang.reflect.Modifier;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

abstract class Factory {
    protected Context context;
    protected Class override;
    protected Support support;
    protected Type type;

    protected Factory(Context context2, Type type2) {
        this(context2, type2, (Class) null);
    }

    protected Factory(Context context2, Type type2, Class cls) {
        this.support = context2.getSupport();
        this.override = cls;
        this.context = context2;
        this.type = type2;
    }

    public Class getType() {
        Class cls = this.override;
        if (cls != null) {
            return cls;
        }
        return this.type.getType();
    }

    public Object getInstance() throws Exception {
        Class type2 = getType();
        if (isInstantiable(type2)) {
            return type2.newInstance();
        }
        throw new InstantiationException("Type %s can not be instantiated", type2);
    }

    /* access modifiers changed from: protected */
    public Value getOverride(InputNode inputNode) throws Exception {
        Value conversion = getConversion(inputNode);
        if (conversion != null) {
            Position position = inputNode.getPosition();
            Class type2 = conversion.getType();
            if (!isCompatible(getType(), type2)) {
                throw new InstantiationException("Incompatible %s for %s at %s", type2, this.type, position);
            }
        }
        return conversion;
    }

    public boolean setOverride(Type type2, Object obj, OutputNode outputNode) throws Exception {
        Class type3 = type2.getType();
        if (type3.isPrimitive()) {
            type2 = getPrimitive(type2, type3);
        }
        return this.context.setOverride(type2, obj, outputNode);
    }

    private Type getPrimitive(Type type2, Class cls) throws Exception {
        Class primitive = Support.getPrimitive(cls);
        return primitive != cls ? new OverrideType(type2, primitive) : type2;
    }

    public Value getConversion(InputNode inputNode) throws Exception {
        Value override2 = this.context.getOverride(this.type, inputNode);
        if (!(override2 == null || this.override == null)) {
            if (!isCompatible(this.override, override2.getType())) {
                return new OverrideValue(override2, this.override);
            }
        }
        return override2;
    }

    public static boolean isCompatible(Class<?> cls, Class cls2) {
        if (cls.isArray()) {
            cls = cls.getComponentType();
        }
        return cls.isAssignableFrom(cls2);
    }

    public static boolean isInstantiable(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isAbstract(modifiers)) {
            return false;
        }
        return !Modifier.isInterface(modifiers);
    }
}
