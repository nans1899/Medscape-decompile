package com.medscape.android.util.customtooltip;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public final class CustomToolTipUtil {
    private CustomToolTipUtil() {
    }

    public static RectF calculeRectOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new RectF((float) iArr[0], (float) iArr[1], (float) (iArr[0] + view.getMeasuredWidth()), (float) (iArr[1] + view.getMeasuredHeight()));
    }

    public static RectF calculeRectInWindow(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return new RectF((float) iArr[0], (float) iArr[1], (float) (iArr[0] + view.getMeasuredWidth()), (float) (iArr[1] + view.getMeasuredHeight()));
    }

    public static float dpFromPx(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public static float pxFromDp(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    public static void setWidth(View view, float f) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams((int) f, view.getHeight());
        } else {
            layoutParams.width = (int) f;
        }
        view.setLayoutParams(layoutParams);
    }

    public static int tooltipGravityToArrowDirection(int i) {
        if (i == 17) {
            return 1;
        }
        if (i == 48) {
            return 3;
        }
        if (i == 80) {
            return 1;
        }
        if (i == 8388611) {
            return 2;
        }
        if (i == 8388613) {
            return 0;
        }
        throw new IllegalArgumentException("Gravity must have be CENTER, START, END, TOP or BOTTOM.");
    }

    public static void setX(View view, int i) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setX((float) i);
            return;
        }
        ViewGroup.MarginLayoutParams orCreateMarginLayoutParams = getOrCreateMarginLayoutParams(view);
        orCreateMarginLayoutParams.leftMargin = i - view.getLeft();
        view.setLayoutParams(orCreateMarginLayoutParams);
    }

    public static void setY(View view, int i) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setY((float) i);
            return;
        }
        ViewGroup.MarginLayoutParams orCreateMarginLayoutParams = getOrCreateMarginLayoutParams(view);
        orCreateMarginLayoutParams.topMargin = i - view.getTop();
        view.setLayoutParams(orCreateMarginLayoutParams);
    }

    private static ViewGroup.MarginLayoutParams getOrCreateMarginLayoutParams(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return new ViewGroup.MarginLayoutParams(view.getWidth(), view.getHeight());
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return (ViewGroup.MarginLayoutParams) layoutParams;
        }
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void setTextAppearance(TextView textView, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }

    public static int getColor(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(i);
        }
        return context.getResources().getColor(i);
    }

    public static Drawable getDrawable(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }
}
