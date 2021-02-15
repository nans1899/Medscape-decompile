package com.appboy.support;

import android.content.Context;

public class PermissionUtils {
    private static final String a = AppboyLogger.getAppboyLogTag(PermissionUtils.class);

    public static boolean hasPermission(Context context, String str) {
        try {
            return context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable th) {
            String str2 = a;
            AppboyLogger.e(str2, "Failure checking permission " + str, th);
            return false;
        }
    }
}
