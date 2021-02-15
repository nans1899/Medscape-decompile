package com.qxmd.eventssdkandroid.util;

public class Log {
    static final boolean LOG = false;

    public static void d(Object obj, String str) {
    }

    public static void d(String str, String str2) {
    }

    public static void e(String str, String str2) {
    }

    public static void i(String str, String str2) {
    }

    public static void v(String str, String str2) {
    }

    public static void w(String str, String str2) {
    }

    public static String getLogTagForClass(Class cls) {
        return cls.getCanonicalName();
    }

    public static void v(Object obj, String str) {
        v(obj.getClass().getSimpleName(), str);
    }
}
