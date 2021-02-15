package com.medscape.android.util;

public class StringUtil {
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String trimAllWhiteSpaces(String str) {
        int length = str.length() - 1;
        int i = 0;
        while (i <= length && (str.charAt(i) <= ' ' || str.charAt(i) == 10 || str.charAt(i) == 9)) {
            i++;
        }
        int i2 = length;
        while (i2 >= i && (str.charAt(i2) <= ' ' || str.charAt(i) == 10 || str.charAt(i) == 9)) {
            i2--;
        }
        if (i == 0 && i2 == length) {
            return str;
        }
        if (i > length) {
            return "";
        }
        return str.substring(i, i2 + 1);
    }

    public static String optString(String str) {
        return isNotEmpty(str) ? str : "";
    }

    public static String toLowerCase(String str) {
        return isNotEmpty(str) ? str.toLowerCase() : str;
    }

    public static boolean isSubstringOpSafe(String str, int i, int i2) {
        return str != null && i >= 0 && i <= i2 && i2 <= str.length();
    }

    public static boolean onlyNumbers(String str) {
        return isNotEmpty(str) && str.matches("[0-9]+");
    }
}
