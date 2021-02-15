package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.reflect.Type;

public abstract class StdConverter<IN, OUT> implements Converter<IN, OUT> {
    public abstract OUT convert(IN in);

    public JavaType getInputType(TypeFactory typeFactory) {
        return _findConverterType(typeFactory).containedType(0);
    }

    public JavaType getOutputType(TypeFactory typeFactory) {
        return _findConverterType(typeFactory).containedType(1);
    }

    /* access modifiers changed from: protected */
    public JavaType _findConverterType(TypeFactory typeFactory) {
        JavaType findSuperType = typeFactory.constructType((Type) getClass()).findSuperType(Converter.class);
        if (findSuperType != null && findSuperType.containedTypeCount() >= 2) {
            return findSuperType;
        }
        throw new IllegalStateException("Can not find OUT type parameter for Converter of type " + getClass().getName());
    }
}
