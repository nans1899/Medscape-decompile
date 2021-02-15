package org.objenesis.instantiator.util;

import java.lang.reflect.Field;
import org.objenesis.ObjenesisException;
import sun.misc.Unsafe;

public final class UnsafeUtils {
    private static final Unsafe unsafe;

    static {
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            try {
                unsafe = (Unsafe) declaredField.get((Object) null);
            } catch (IllegalAccessException e) {
                throw new ObjenesisException((Throwable) e);
            }
        } catch (NoSuchFieldException e2) {
            throw new ObjenesisException((Throwable) e2);
        }
    }

    private UnsafeUtils() {
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
