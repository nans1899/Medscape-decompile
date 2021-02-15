package com.webmd.wbmdcmepulse.models.utils;

import android.widget.Spinner;
import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extensions {
    public static String getSubstring(String str, String str2, String str3) {
        Matcher matcher = Pattern.compile(Pattern.quote(str2.toLowerCase()) + "(.*?)" + Pattern.quote(str3.toLowerCase())).matcher(str);
        String group = matcher.find() ? matcher.group(1) : "";
        return str2 + group + str3;
    }

    public static int getSpinnerPositionByValue(Spinner spinner, String str) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(str)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean shouldRefreshListData(List<String> list, List<String> list2) {
        if (list2 == null) {
            return false;
        }
        if (list == null || list.size() != list2.size()) {
            return true;
        }
        for (String next : list) {
            boolean z = false;
            for (String equals : list2) {
                if (equals.equals(next)) {
                    z = true;
                }
            }
            if (!z) {
                break;
            }
        }
        return true;
    }

    public static <V> Map.Entry<String, V> getSafeContainsPosition(Map<String, V> map, int i) {
        Map.Entry<String, V>[] entryArr;
        if (i < 0 || map == null || map.size() <= i || (entryArr = (Map.Entry[]) map.entrySet().toArray(new Map.Entry[map.size()])) == null) {
            return null;
        }
        return entryArr[i];
    }

    public static boolean tryParseFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean tryParseBoolean(String str) {
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
    }

    public static boolean isStringNullOrEmpty(String str) {
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
