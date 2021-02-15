package org.mockito.internal.runners.util;

import java.lang.reflect.Method;
import org.junit.Test;

public class TestMethodsFinder {
    private TestMethodsFinder() {
    }

    public static boolean hasTestMethods(Class<?> cls) {
        for (Method isAnnotationPresent : cls.getMethods()) {
            if (isAnnotationPresent.isAnnotationPresent(Test.class)) {
                return true;
            }
        }
        return false;
    }
}
