package com.medscape.android.util;

public class NumberUtil {
    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int log2(int i) {
        if (i > 0) {
            return 31 - Integer.numberOfLeadingZeros(i);
        }
        throw new IllegalArgumentException();
    }

    public static double distance(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d4 - d2, 2.0d) + Math.pow(d3 - d, 2.0d));
    }

    public static int intValue(Integer num) {
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }
}
