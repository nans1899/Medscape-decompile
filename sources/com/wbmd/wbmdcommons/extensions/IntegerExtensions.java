package com.wbmd.wbmdcommons.extensions;

public class IntegerExtensions {
    public static boolean tryParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
