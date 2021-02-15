package com.appboy.support;

import bo.app.dq;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Set;

public final class ValidationUtils {
    public static final int APPBOY_STRING_MAX_LENGTH = 255;
    private static final String a = AppboyLogger.getAppboyLogTag(ValidationUtils.class);

    public static boolean isValidLocation(double d, double d2) {
        return d < 90.0d && d > -90.0d && d2 < 180.0d && d2 > -180.0d;
    }

    public static boolean isValidEmailAddress(String str) {
        return str != null && str.toLowerCase(Locale.US).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    public static boolean isValidPhoneNumber(String str) {
        return str != null && str.matches("^[0-9 .\\(\\)\\+\\-]+$");
    }

    public static String ensureAppboyFieldLength(String str) {
        String trim = str.trim();
        if (trim.length() <= 255) {
            return trim;
        }
        String str2 = a;
        AppboyLogger.w(str2, "Provided string field is too long [" + trim.length() + "]. The max length is " + 255 + ", truncating provided field.");
        return trim.substring(0, 255);
    }

    public static boolean isValidLogPurchaseInput(String str, String str2, BigDecimal bigDecimal, int i, dq dqVar, Set<String> set) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.w(a, "The productId is empty, not logging in-app purchase to Appboy.");
            return false;
        } else if (dqVar.j().contains(str)) {
            String str3 = a;
            AppboyLogger.w(str3, "The productId is a blacklisted productId: " + str);
            return false;
        } else if (str2 == null) {
            String str4 = a;
            AppboyLogger.w(str4, "The currencyCode is null. Expected one of " + set);
            return false;
        } else if (!set.contains(str2)) {
            String str5 = a;
            AppboyLogger.w(str5, "The currencyCode " + str2 + " is invalid. Expected one of " + set);
            return false;
        } else if (bigDecimal == null) {
            AppboyLogger.w(a, "The price is null.");
            return false;
        } else if (i <= 0) {
            String str6 = a;
            AppboyLogger.w(str6, "The requested purchase quantity of " + i + " is less than zero.");
            return false;
        } else if (i <= 100) {
            return true;
        } else {
            String str7 = a;
            AppboyLogger.w(str7, "The requested purchase quantity of " + i + " is greater than the maximum of " + 100);
            return false;
        }
    }

    public static boolean isValidLogCustomEventInput(String str, dq dqVar) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.w(a, "The custom event name cannot be null or contain only whitespaces. Invalid custom event.");
            return false;
        } else if (!dqVar.h().contains(str)) {
            return true;
        } else {
            String str2 = a;
            AppboyLogger.w(str2, "The custom event is a blacklisted custom event: " + str + ". Invalid custom event.");
            return false;
        }
    }

    public static boolean isValidPushStoryClickInput(String str, String str2) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.w(a, "Campaign ID cannot be null or blank");
            return false;
        } else if (!StringUtils.isNullOrBlank(str2)) {
            return true;
        } else {
            AppboyLogger.w(a, "Push story page ID cannot be null or blank");
            return false;
        }
    }
}
