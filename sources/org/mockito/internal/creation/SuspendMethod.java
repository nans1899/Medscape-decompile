package org.mockito.internal.creation;

import java.util.Arrays;

public class SuspendMethod {
    private static final String KOTLIN_CONTINUATION = "kotlin.coroutines.Continuation";
    private static final String KOTLIN_EXPERIMENTAL_CONTINUATION = "kotlin.coroutines.experimental.Continuation";

    public static Class<?>[] trimSuspendParameterTypes(Class<?>[] clsArr) {
        int length = clsArr.length;
        if (length <= 0) {
            return clsArr;
        }
        int i = length - 1;
        return isContinuationType(clsArr[i]) ? (Class[]) Arrays.copyOf(clsArr, i) : clsArr;
    }

    private static boolean isContinuationType(Class<?> cls) {
        String name = cls.getName();
        return name.equals(KOTLIN_CONTINUATION) || name.equals(KOTLIN_EXPERIMENTAL_CONTINUATION);
    }
}
