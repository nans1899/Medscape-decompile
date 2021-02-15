package org.mockito.internal.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;

public class Primitives {
    private static final Map<Class<?>, Object> PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES = new HashMap();
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPES = new HashMap();

    static {
        PRIMITIVE_TYPES.put(Boolean.class, Boolean.TYPE);
        PRIMITIVE_TYPES.put(Character.class, Character.TYPE);
        PRIMITIVE_TYPES.put(Byte.class, Byte.TYPE);
        PRIMITIVE_TYPES.put(Short.class, Short.TYPE);
        PRIMITIVE_TYPES.put(Integer.class, Integer.TYPE);
        PRIMITIVE_TYPES.put(Long.class, Long.TYPE);
        PRIMITIVE_TYPES.put(Float.class, Float.TYPE);
        PRIMITIVE_TYPES.put(Double.class, Double.TYPE);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Boolean.class, false);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Character.class, 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Byte.class, (byte) 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Short.class, (short) 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Integer.class, 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Long.class, 0L);
        Float valueOf = Float.valueOf(0.0f);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Float.class, valueOf);
        Double valueOf2 = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Double.class, valueOf2);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Boolean.TYPE, false);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Character.TYPE, 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Byte.TYPE, (byte) 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Short.TYPE, (short) 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Integer.TYPE, 0);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Long.TYPE, 0L);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Float.TYPE, valueOf);
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.put(Double.TYPE, valueOf2);
    }

    public static <T> Class<T> primitiveTypeOf(Class<T> cls) {
        if (cls.isPrimitive()) {
            return cls;
        }
        return PRIMITIVE_TYPES.get(cls);
    }

    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        return PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.containsKey(cls);
    }

    public static boolean isAssignableFromWrapper(Class<?> cls, Class<?> cls2) {
        if (!isPrimitiveOrWrapper(cls) || !isPrimitiveOrWrapper(cls2)) {
            return false;
        }
        return primitiveTypeOf(cls).isAssignableFrom(primitiveTypeOf(cls2));
    }

    public static <T> T defaultValue(Class<T> cls) {
        return PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.get(cls);
    }
}
