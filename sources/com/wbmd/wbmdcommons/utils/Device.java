package com.wbmd.wbmdcommons.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Device {
    public static final float MULTIPLIER1_2X = 1.2f;

    public static int getDeviceWidthInDp(Context context, float f) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int round = Math.round(((float) displayMetrics.widthPixels) / context.getResources().getDisplayMetrics().density);
        return f > 0.0f ? (int) (((float) round) * f) : round;
    }

    public static int getDeviceHeightInDp(Context context, float f) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int round = Math.round(((float) displayMetrics.heightPixels) / context.getResources().getDisplayMetrics().density);
        return f > 0.0f ? (int) (((float) round) * f) : round;
    }

    public static int getDeviceWidthInPx(Context context) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static float dpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }
}
