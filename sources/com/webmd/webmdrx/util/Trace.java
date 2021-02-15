package com.webmd.webmdrx.util;

import android.util.Log;

public class Trace {
    private static final String DEFAULT_ERROR_MESSAGE = "An Unknown Error Occured";
    public static final int ERRORS_ONLY = 1;
    public static final int ERRORS_WARNINGS = 2;
    public static final int ERRORS_WARNINGS_INFO = 3;
    public static final int ERRORS_WARNINGS_INFO_DEBUG = 4;
    public static final int ERRORS_WARNINGS_INFO_VERBOSE = 5;
    private static final int LOGGING_LEVEL = 5;
    public static final int NONE = 0;

    public static void e(String str, String str2) {
        if (str2 == null) {
            e(str, str2, DEFAULT_ERROR_MESSAGE);
        } else {
            Log.e(str, str2);
        }
    }

    public static void e(String str, String str2, String str3) {
        if (str2 == null) {
            str2 = str3;
        }
        Log.e(str, str2);
    }

    public static void e(String str, String str2, Exception exc) {
        if (str2 == null) {
            e(str, str2, DEFAULT_ERROR_MESSAGE);
        } else {
            Log.e(str, str2, exc);
        }
    }

    public static void w(String str, String str2, String str3) {
        if (str2 == null) {
            str2 = str3;
        }
        Log.w(str, str2);
    }

    public static void w(String str, String str2) {
        if (str2 == null) {
            w(str, str2, DEFAULT_ERROR_MESSAGE);
        } else {
            Log.w(str, str2);
        }
    }

    public static void i(String str, String str2, String str3) {
        if (str2 == null) {
            str2 = str3;
        }
        Log.i(str, str2);
    }

    public static void i(String str, String str2) {
        if (str2 == null) {
            i(str, str2, DEFAULT_ERROR_MESSAGE);
        } else {
            Log.i(str, str2);
        }
    }

    public static void d(String str, String str2, String str3) {
        if (str2 == null) {
            str2 = str3;
        }
        Log.d(str, str2);
    }

    public static void d(String str, String str2) {
        if (str2 == null) {
            d(str, str2, DEFAULT_ERROR_MESSAGE);
        } else {
            Log.d(str, str2);
        }
    }

    public static void v(String str, String str2, String str3) {
        if (str2 == null) {
            str2 = str3;
        }
        Log.d(str, str2);
    }

    public static void v(String str, String str2) {
        if (str2 == null) {
            v(str, str2, DEFAULT_ERROR_MESSAGE);
        } else {
            Log.d(str, str2);
        }
    }
}
