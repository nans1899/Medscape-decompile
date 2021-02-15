package com.google.firebase.inappmessaging.display.internal.layout.util;

import android.view.View;
import com.google.firebase.inappmessaging.display.internal.Logging;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class MeasureUtils {
    public static void measureAtMost(View view, int i, int i2) {
        measure(view, i, i2, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public static void measureExactly(View view, int i, int i2) {
        measure(view, i, i2, 1073741824, 1073741824);
    }

    public static void measureFullWidth(View view, int i, int i2) {
        measure(view, i, i2, 1073741824, Integer.MIN_VALUE);
    }

    public static void measureFullHeight(View view, int i, int i2) {
        measure(view, i, i2, Integer.MIN_VALUE, 1073741824);
    }

    private static void measure(View view, int i, int i2, int i3, int i4) {
        Logging.logdPair("\tdesired (w,h)", (float) view.getMeasuredWidth(), (float) view.getMeasuredHeight());
        if (view.getVisibility() == 8) {
            i = 0;
            i2 = 0;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec(i, i3), View.MeasureSpec.makeMeasureSpec(i2, i4));
        Logging.logdPair("\tactual (w,h)", (float) view.getMeasuredWidth(), (float) view.getMeasuredHeight());
    }
}
