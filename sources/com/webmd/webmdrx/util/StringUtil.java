package com.webmd.webmdrx.util;

public class StringUtil {
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
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
}
