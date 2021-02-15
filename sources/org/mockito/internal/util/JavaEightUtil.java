package org.mockito.internal.util;

import org.mockito.internal.creation.instance.InstantiationException;

public final class JavaEightUtil {
    private static Object emptyOptional;
    private static Object emptyOptionalDouble;
    private static Object emptyOptionalInt;
    private static Object emptyOptionalLong;

    private JavaEightUtil() {
    }

    public static Object emptyOptional() {
        Object obj = emptyOptional;
        if (obj != null) {
            return obj;
        }
        Object invokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.Optional", "empty");
        emptyOptional = invokeNullaryFactoryMethod;
        return invokeNullaryFactoryMethod;
    }

    public static Object emptyOptionalDouble() {
        Object obj = emptyOptionalDouble;
        if (obj != null) {
            return obj;
        }
        Object invokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.OptionalDouble", "empty");
        emptyOptionalDouble = invokeNullaryFactoryMethod;
        return invokeNullaryFactoryMethod;
    }

    public static Object emptyOptionalInt() {
        Object obj = emptyOptionalInt;
        if (obj != null) {
            return obj;
        }
        Object invokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.OptionalInt", "empty");
        emptyOptionalInt = invokeNullaryFactoryMethod;
        return invokeNullaryFactoryMethod;
    }

    public static Object emptyOptionalLong() {
        Object obj = emptyOptionalLong;
        if (obj != null) {
            return obj;
        }
        Object invokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.OptionalLong", "empty");
        emptyOptionalLong = invokeNullaryFactoryMethod;
        return invokeNullaryFactoryMethod;
    }

    public static Object emptyStream() {
        return invokeNullaryFactoryMethod("java.util.stream.Stream", "empty");
    }

    public static Object emptyDoubleStream() {
        return invokeNullaryFactoryMethod("java.util.stream.DoubleStream", "empty");
    }

    public static Object emptyIntStream() {
        return invokeNullaryFactoryMethod("java.util.stream.IntStream", "empty");
    }

    public static Object emptyLongStream() {
        return invokeNullaryFactoryMethod("java.util.stream.LongStream", "empty");
    }

    private static Object invokeNullaryFactoryMethod(String str, String str2) {
        try {
            return Class.forName(str).getMethod(str2, new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            throw new InstantiationException(String.format("Could not create %s#%s(): %s", new Object[]{str, str2, e}), e);
        }
    }
}
