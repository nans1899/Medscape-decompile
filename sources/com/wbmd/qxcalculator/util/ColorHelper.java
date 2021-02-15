package com.wbmd.qxcalculator.util;

public class ColorHelper {
    private static ColorHelper ourInstance = new ColorHelper();

    public int addColors(int i, int i2) {
        double d = ((double) ((i2 >> 24) & 255)) / 255.0d;
        double d2 = (((double) ((i >> 24) & 255)) / 255.0d) * (1.0d - d);
        double d3 = d + d2;
        double d4 = d2 / d3;
        double d5 = d4 / d3;
        return ((((int) ((((double) ((float) (i & 255))) * d4) + (((double) ((float) (i2 & 255))) * d5))) & 255) << 0) | ((((int) (d3 * 255.0d)) & 255) << 24) | ((((int) ((((double) ((float) ((i >> 16) & 255))) * d4) + (((double) ((float) ((i2 >> 16) & 255))) * d5))) & 255) << 16) | ((((int) ((((double) ((float) ((i >> 8) & 255))) * d4) + (((double) ((float) ((i2 >> 8) & 255))) * d5))) & 255) << 8);
    }

    public static ColorHelper getInstance() {
        return ourInstance;
    }

    private ColorHelper() {
    }
}
