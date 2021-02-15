package com.webmd.wbmdcmepulse.models.utils;

public class ExtensionsAspectRatio {
    public static int getAdjustedHeight(int i, int i2, int i3) {
        return (i3 * i2) / i;
    }

    public static int getAdjustedWidth(int i, int i2, int i3) {
        return (i3 * i) / i2;
    }
}
