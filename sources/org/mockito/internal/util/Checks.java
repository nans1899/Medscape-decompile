package org.mockito.internal.util;

public class Checks {
    public static <T> T checkNotNull(T t, String str) {
        return checkNotNull(t, str, (String) null);
    }

    public static <T> T checkNotNull(T t, String str, String str2) {
        if (t != null) {
            return t;
        }
        String str3 = str + " should not be null";
        if (str2 != null) {
            str3 = str3 + ". " + str2;
        }
        throw new IllegalArgumentException(str3);
    }

    public static <T extends Iterable<?>> T checkItemsNotNull(T t, String str) {
        checkNotNull(t, str);
        for (Object next : t) {
            checkNotNull(next, "item in " + str);
        }
        return t;
    }
}
