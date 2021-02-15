package org.mockito.internal.matchers;

import java.lang.reflect.Array;

public class Equality {
    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (!isArray(obj)) {
            return obj.equals(obj2);
        }
        if (!isArray(obj2) || !areArraysEqual(obj, obj2)) {
            return false;
        }
        return true;
    }

    static boolean areArraysEqual(Object obj, Object obj2) {
        return areArrayLengthsEqual(obj, obj2) && areArrayElementsEqual(obj, obj2);
    }

    static boolean areArrayLengthsEqual(Object obj, Object obj2) {
        return Array.getLength(obj) == Array.getLength(obj2);
    }

    static boolean areArrayElementsEqual(Object obj, Object obj2) {
        for (int i = 0; i < Array.getLength(obj); i++) {
            if (!areEqual(Array.get(obj, i), Array.get(obj2, i))) {
                return false;
            }
        }
        return true;
    }

    static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }
}
