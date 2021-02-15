package com.wbmd.wbmdcommons.utils;

import java.util.Arrays;
import java.util.Map;

public class Extensions {
    public static <V> Map.Entry<String, V> getSafeContainsPosition(Map<String, V> map, int i) {
        Map.Entry<String, V>[] entryArr;
        if (i < 0 || map == null || map.size() <= i || (entryArr = (Map.Entry[]) map.entrySet().toArray(new Map.Entry[map.size()])) == null) {
            return null;
        }
        return entryArr[i];
    }

    public static boolean IsNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean contains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T[] tArr, T t) {
        return Arrays.asList(tArr).contains(t);
    }
}
