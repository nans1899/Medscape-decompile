package mnetinternal;

import android.util.Log;

public final class bi {
    private static boolean a;

    public static void a(String str, String str2) {
        if (a) {
            Log.d(str, str2);
        }
    }

    public static void a(String str, Throwable th) {
        if (a) {
            Log.e(str, th.getMessage(), th);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            Log.e(str, str2, th);
        }
    }

    public static void a(boolean z) {
        a = z;
    }
}
