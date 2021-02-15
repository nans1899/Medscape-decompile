package com.appboy.ui.support;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import com.appboy.support.AppboyLogger;

public class ViewUtils {
    private static final int TABLET_SMALLEST_WIDTH_DP = 600;
    private static final String TAG = AppboyLogger.getAppboyLogTag(ViewUtils.class);
    private static int sDisplayHeight;

    public static void removeViewFromParent(View view) {
        if (view != null && (view.getParent() instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            setFocusableInTouchModeAndRequestFocus(viewGroup);
            viewGroup.removeView(view);
        }
    }

    public static void setFocusableInTouchModeAndRequestFocus(View view) {
        try {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        } catch (Exception e) {
            AppboyLogger.e(TAG, "Caught exception while setting view to focusable in touch mode and requesting focus.", e);
        }
    }

    public static int getTopVisibleCoordinate(View view) {
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

    public static int getDisplayHeight(Activity activity) {
        int i = sDisplayHeight;
        if (i > 0) {
            return i;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i2 = point.y;
        sDisplayHeight = i2;
        return i2;
    }

    public static double convertDpToPixels(Activity activity, double d) {
        return d * ((double) activity.getResources().getDisplayMetrics().density);
    }

    public static boolean isRunningOnTablet(Activity activity) {
        return activity.getResources().getConfiguration().smallestScreenWidthDp >= 600;
    }

    public static void setActivityRequestedOrientation(Activity activity, int i) {
        try {
            activity.setRequestedOrientation(i);
        } catch (Exception e) {
            String str = TAG;
            AppboyLogger.e(str, "Failed to set requested orientation " + i + " for activity class: " + activity.getLocalClassName(), e);
        }
    }
}
