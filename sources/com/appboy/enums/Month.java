package com.appboy.enums;

import com.appboy.support.AppboyLogger;

public enum Month {
    JANUARY(0),
    FEBRUARY(1),
    MARCH(2),
    APRIL(3),
    MAY(4),
    JUNE(5),
    JULY(6),
    AUGUST(7),
    SEPTEMBER(8),
    OCTOBER(9),
    NOVEMBER(10),
    DECEMBER(11);
    
    private static final String a = null;
    private final int b;

    static {
        a = AppboyLogger.getAppboyLogTag(Month.class);
    }

    private Month(int i) {
        this.b = i;
    }

    public int getValue() {
        return this.b;
    }

    public static Month getMonth(int i) {
        for (Month month : values()) {
            if (month.getValue() == i) {
                return month;
            }
        }
        AppboyLogger.e(a, "No month with value " + i + ", value must be in (0,11)");
        return null;
    }
}
