package com.appboy.support;

import android.content.Context;

public class PackageUtils {
    private static final String a = AppboyLogger.getAppboyLogTag(PackageUtils.class);
    private static String b;

    public static void setResourcePackageName(String str) {
        if (!StringUtils.isNullOrBlank(str)) {
            b = str;
        } else {
            AppboyLogger.e(a, "Package name may not be null or blank");
        }
    }

    public static String getResourcePackageName(Context context) {
        String str = b;
        if (str != null) {
            return str;
        }
        String packageName = context.getPackageName();
        b = packageName;
        return packageName;
    }
}
