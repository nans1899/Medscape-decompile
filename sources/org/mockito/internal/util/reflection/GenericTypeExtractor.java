package org.mockito.internal.util.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericTypeExtractor {
    public static Class<?> genericTypeOf(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        while (true) {
            Class<? super Object> cls4 = cls;
            if (cls4 == Object.class) {
                return Object.class;
            }
            if (cls4.getSuperclass() == cls2) {
                return extractGeneric(cls4.getGenericSuperclass());
            }
            Type findGenericInterface = findGenericInterface(cls4, cls3);
            if (findGenericInterface != null) {
                return extractGeneric(findGenericInterface);
            }
            cls4 = cls4.getSuperclass();
        }
    }

    private static Type findGenericInterface(Class<?> cls, Class<?> cls2) {
        for (Class<?> cls3 : cls.getInterfaces()) {
            if (cls3 == cls2) {
                return cls.getGenericInterfaces()[0];
            }
            Type findGenericInterface = findGenericInterface(cls3, cls2);
            if (findGenericInterface != null) {
                return findGenericInterface;
            }
        }
        return null;
    }

    private static Class<?> extractGeneric(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length <= 0 || !(actualTypeArguments[0] instanceof Class)) {
            return Object.class;
        }
        return (Class) actualTypeArguments[0];
    }
}
