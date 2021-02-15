package com.appboy.support;

import java.util.Set;

public class CustomAttributeValidationUtils {
    private static final String a = AppboyLogger.getAppboyLogTag(CustomAttributeValidationUtils.class);

    public static boolean isValidCustomAttributeKey(String str, Set<String> set) {
        if (str == null) {
            AppboyLogger.w(a, "Custom attribute key cannot be null.");
            return false;
        } else if (!set.contains(str)) {
            return true;
        } else {
            String str2 = a;
            AppboyLogger.w(str2, "Custom attribute key cannot be blacklisted attribute: " + str + ".");
            return false;
        }
    }

    public static boolean isValidCustomAttributeValue(String str) {
        if (str != null) {
            return true;
        }
        AppboyLogger.w(a, "Custom attribute value cannot be null.");
        return false;
    }

    public static String[] ensureCustomAttributeArrayValues(String[] strArr) {
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < strArr.length; i++) {
                strArr[i] = ValidationUtils.ensureAppboyFieldLength(strArr[i]);
            }
        }
        return strArr;
    }
}
