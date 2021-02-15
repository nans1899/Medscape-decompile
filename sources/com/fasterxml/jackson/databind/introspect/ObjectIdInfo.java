package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.fasterxml.jackson.databind.PropertyName;

public class ObjectIdInfo {
    protected final boolean _alwaysAsId;
    protected final Class<? extends ObjectIdGenerator<?>> _generator;
    protected final PropertyName _propertyName;
    protected final Class<? extends ObjectIdResolver> _resolver;
    protected final Class<?> _scope;

    public ObjectIdInfo(PropertyName propertyName, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2, Class<? extends ObjectIdResolver> cls3) {
        this(propertyName, cls, cls2, false, cls3);
    }

    @Deprecated
    public ObjectIdInfo(PropertyName propertyName, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2) {
        this(propertyName, cls, cls2, false);
    }

    @Deprecated
    public ObjectIdInfo(String str, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2) {
        this(new PropertyName(str), cls, cls2, false);
    }

    protected ObjectIdInfo(PropertyName propertyName, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2, boolean z) {
        this(propertyName, cls, cls2, z, SimpleObjectIdResolver.class);
    }

    protected ObjectIdInfo(PropertyName propertyName, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2, boolean z, Class<? extends ObjectIdResolver> cls3) {
        this._propertyName = propertyName;
        this._scope = cls;
        this._generator = cls2;
        this._alwaysAsId = z;
        this._resolver = cls3 == null ? SimpleObjectIdResolver.class : cls3;
    }

    public ObjectIdInfo withAlwaysAsId(boolean z) {
        if (this._alwaysAsId == z) {
            return this;
        }
        return new ObjectIdInfo(this._propertyName, this._scope, this._generator, z, this._resolver);
    }

    public PropertyName getPropertyName() {
        return this._propertyName;
    }

    public Class<?> getScope() {
        return this._scope;
    }

    public Class<? extends ObjectIdGenerator<?>> getGeneratorType() {
        return this._generator;
    }

    public Class<? extends ObjectIdResolver> getResolverType() {
        return this._resolver;
    }

    public boolean getAlwaysAsId() {
        return this._alwaysAsId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ObjectIdInfo: propName=");
        sb.append(this._propertyName);
        sb.append(", scope=");
        Class<?> cls = this._scope;
        String str = "null";
        sb.append(cls == null ? str : cls.getName());
        sb.append(", generatorType=");
        Class<? extends ObjectIdGenerator<?>> cls2 = this._generator;
        if (cls2 != null) {
            str = cls2.getName();
        }
        sb.append(str);
        sb.append(", alwaysAsId=");
        sb.append(this._alwaysAsId);
        return sb.toString();
    }
}
