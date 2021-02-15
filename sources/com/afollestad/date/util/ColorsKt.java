package com.afollestad.date.util;

import android.graphics.Color;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¨\u0006\b"}, d2 = {"isColorDark", "", "", "threshold", "", "withAlpha", "alpha", "", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: Colors.kt */
public final class ColorsKt {
    public static /* synthetic */ boolean isColorDark$default(int i, double d, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d = 0.5d;
        }
        return isColorDark(i, d);
    }

    public static final boolean isColorDark(int i, double d) {
        return i != 0 && ((double) 1) - ((((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d)) / ((double) 255)) >= d;
    }

    public static final int withAlpha(int i, float f) {
        return Color.argb((int) (((float) 255) * f), Color.red(i), Color.green(i), Color.blue(i));
    }
}
