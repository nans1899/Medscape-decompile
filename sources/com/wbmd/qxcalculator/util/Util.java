package com.wbmd.qxcalculator.util;

public class Util {
    public static String truncateFirebaseValue(String str) {
        return (str == null || str.isEmpty() || str.length() < 99) ? str : str.substring(0, 98);
    }

    public static String truncateCalculatorIdForFirebase(String str) {
        return (str == null || str.isEmpty() || !str.contains("calculator_")) ? "" : str.substring(str.indexOf("_") + 1);
    }
}
