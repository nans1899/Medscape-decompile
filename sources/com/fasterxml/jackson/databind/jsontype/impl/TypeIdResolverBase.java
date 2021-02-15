package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;

public abstract class TypeIdResolverBase implements TypeIdResolver {
    protected final JavaType _baseType;
    protected final TypeFactory _typeFactory;

    public String getDescForKnownTypeIds() {
        return null;
    }

    public void init(JavaType javaType) {
    }

    protected TypeIdResolverBase() {
        this((JavaType) null, (TypeFactory) null);
    }

    protected TypeIdResolverBase(JavaType javaType, TypeFactory typeFactory) {
        this._baseType = javaType;
        this._typeFactory = typeFactory;
    }

    public String idFromBaseType() {
        return idFromValueAndType((Object) null, this._baseType.getRawClass());
    }

    @Deprecated
    public JavaType typeFromId(String str) {
        return typeFromId((DatabindContext) null, str);
    }

    public JavaType typeFromId(DatabindContext databindContext, String str) {
        throw new IllegalStateException("Sub-class " + getClass().getName() + " MUST implement " + "`typeFromId(DatabindContext,String)");
    }
}
