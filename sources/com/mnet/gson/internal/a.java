package com.mnet.gson.internal;

public final class a {
    public static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }
}
