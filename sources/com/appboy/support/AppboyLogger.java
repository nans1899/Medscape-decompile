package com.appboy.support;

import android.util.Log;
import bo.app.bw;
import bo.app.eh;
import com.appboy.Constants;

public class AppboyLogger {
    public static final int SUPPRESS = Integer.MAX_VALUE;
    private static bw a;
    private static final String b = getAppboyLogTag(AppboyLogger.class);
    private static boolean c;
    private static int d = 4;
    private static final int e = 15;
    private static final int f = (80 - 15);

    public static synchronized void checkForSystemLogLevelProperty() {
        synchronized (AppboyLogger.class) {
            String a2 = eh.a("log.tag.APPBOY", "");
            if (!StringUtils.isNullOrBlank(a2) && a2.trim().equalsIgnoreCase("verbose")) {
                c = true;
                d = 2;
                String str = b;
                i(str, "AppboyLogger log level set to " + a2 + " via device system property. Note that subsequent calls to AppboyLogger.setLogLevel() will have no effect.");
            }
        }
    }

    public static synchronized void setLogLevel(int i) {
        synchronized (AppboyLogger.class) {
            if (!c) {
                d = i;
            } else {
                String str = b;
                w(str, "Log level already set via system property. AppboyLogger.setLogLevel() ignored for level: " + i);
            }
        }
    }

    public static int getLogLevel() {
        return d;
    }

    public static int v(String str, String str2) {
        if (d <= 2) {
            return Log.v(str, str2);
        }
        return 0;
    }

    public static int v(String str, String str2, Throwable th) {
        if (d <= 2) {
            return Log.v(str, str2, th);
        }
        return 0;
    }

    public static int d(String str, String str2) {
        return d(str, str2, true);
    }

    public static int d(String str, String str2, Throwable th) {
        return d(str, str2, th, true);
    }

    public static int d(String str, String str2, boolean z) {
        return d(str, str2, (Throwable) null, z);
    }

    public static int d(String str, String str2, Throwable th, boolean z) {
        if (z) {
            a(str, str2, (Throwable) null);
        }
        if (d > 3) {
            return 0;
        }
        if (th != null) {
            return Log.d(str, str2, th);
        }
        return Log.d(str, str2);
    }

    public static int i(String str, String str2) {
        return i(str, str2, true);
    }

    public static int i(String str, String str2, Throwable th) {
        return i(str, str2, th, true);
    }

    public static int i(String str, String str2, boolean z) {
        return d(str, str2, (Throwable) null, z);
    }

    public static int i(String str, String str2, Throwable th, boolean z) {
        if (z) {
            a(str, str2, (Throwable) null);
        }
        if (d > 4) {
            return 0;
        }
        if (th != null) {
            return Log.i(str, str2, th);
        }
        return Log.i(str, str2);
    }

    public static int w(String str, String str2) {
        a(str, str2, (Throwable) null);
        if (d <= 5) {
            return Log.w(str, str2);
        }
        return 0;
    }

    public static int w(String str, String str2, Throwable th) {
        a(str, str2, th);
        if (d <= 5) {
            return Log.w(str, str2, th);
        }
        return 0;
    }

    public static int w(String str, Throwable th) {
        a(str, (String) null, th);
        if (d <= 5) {
            return Log.w(str, th);
        }
        return 0;
    }

    public static int e(String str, String str2) {
        a(str, str2, (Throwable) null);
        if (d <= 6) {
            return Log.e(str, str2);
        }
        return 0;
    }

    public static int e(String str, String str2, Throwable th) {
        a(str, str2, (Throwable) null);
        if (d <= 6) {
            return Log.e(str, str2, th);
        }
        return 0;
    }

    public static String getAppboyLogTag(Class cls) {
        String name = cls.getName();
        int length = name.length();
        int i = f;
        if (length > i) {
            name = name.substring(length - i);
        }
        return Constants.APPBOY_LOG_TAG_PREFIX + name;
    }

    public static void setTestUserDeviceLoggingManager(bw bwVar) {
        a = bwVar;
    }

    private static void a(String str, String str2, Throwable th) {
        bw bwVar = a;
        if (bwVar != null && bwVar.a() && str != null) {
            a.a(str, str2, th);
        }
    }
}
