package com.google.firebase.inappmessaging.display.internal.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import com.google.firebase.inappmessaging.display.R;
import com.google.firebase.inappmessaging.display.internal.Logging;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public abstract class BaseModalLayout extends FrameLayout {
    private static final float DEFAULT_MAX_HEIGHT_PCT = -1.0f;
    private static final float DEFAULT_MAX_WIDTH_PCT = -1.0f;
    private DisplayMetrics mDisplay;
    private float mMaxHeightPct;
    private float mMaxWidthPct;
    private List<View> mVisibleChildren = new ArrayList();

    /* JADX INFO: finally extract failed */
    public BaseModalLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ModalLayout, 0, 0);
        try {
            this.mMaxWidthPct = obtainStyledAttributes.getFloat(R.styleable.ModalLayout_maxWidthPct, -1.0f);
            this.mMaxHeightPct = obtainStyledAttributes.getFloat(R.styleable.ModalLayout_maxHeightPct, -1.0f);
            obtainStyledAttributes.recycle();
            this.mDisplay = context.getResources().getDisplayMetrics();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Logging.logdHeader("BEGIN LAYOUT");
        Logging.logd("onLayout: l: " + i + ", t: " + i2 + ", r: " + i3 + ", b: " + i4);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Logging.logdHeader("BEGIN MEASURE");
        Logging.logdPair("Display", (float) getDisplayMetrics().widthPixels, (float) getDisplayMetrics().heightPixels);
        this.mVisibleChildren.clear();
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                this.mVisibleChildren.add(childAt);
            } else {
                Logging.logdNumber("Skipping GONE child", (float) i3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public float getMaxWidthPct() {
        return this.mMaxWidthPct;
    }

    /* access modifiers changed from: protected */
    public float getMaxHeightPct() {
        return this.mMaxHeightPct;
    }

    /* access modifiers changed from: protected */
    public DisplayMetrics getDisplayMetrics() {
        return this.mDisplay;
    }

    /* access modifiers changed from: protected */
    public List<View> getVisibleChildren() {
        return this.mVisibleChildren;
    }

    /* access modifiers changed from: protected */
    public int calculateBaseWidth(int i) {
        if (getMaxWidthPct() > 0.0f) {
            Logging.logd("Width: restrict by pct");
            return roundToNearest((int) (((float) getDisplayMetrics().widthPixels) * getMaxWidthPct()), 4);
        }
        Logging.logd("Width: restrict by spec");
        return View.MeasureSpec.getSize(i);
    }

    /* access modifiers changed from: protected */
    public int calculateBaseHeight(int i) {
        if (getMaxHeightPct() > 0.0f) {
            Logging.logd("Height: restrict by pct");
            return roundToNearest((int) (((float) getDisplayMetrics().heightPixels) * getMaxHeightPct()), 4);
        }
        Logging.logd("Height: restrict by spec");
        return View.MeasureSpec.getSize(i);
    }

    /* access modifiers changed from: protected */
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        Logging.logdPair("\tdesired (w,h)", (float) view.getMeasuredWidth(), (float) view.getMeasuredHeight());
        super.measureChildWithMargins(view, i, i2, i3, i4);
        Logging.logdPair("\tactual  (w,h)", (float) view.getMeasuredWidth(), (float) view.getMeasuredHeight());
    }

    /* access modifiers changed from: protected */
    public void layoutChild(View view, int i, int i2) {
        layoutChild(view, i, i2, i + getDesiredWidth(view), i2 + getDesiredHeight(view));
    }

    /* access modifiers changed from: protected */
    public void layoutChild(View view, int i, int i2, int i3, int i4) {
        Logging.logdPair("\tleft, right", (float) i, (float) i3);
        Logging.logdPair("\ttop, bottom", (float) i2, (float) i4);
        view.layout(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public View findChildById(int i) {
        View findViewById = findViewById(i);
        if (findViewById != null) {
            return findViewById;
        }
        throw new IllegalStateException("No such child: " + i);
    }

    /* access modifiers changed from: protected */
    public int getHeightWithMargins(View view) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        FrameLayout.LayoutParams layoutParams = getLayoutParams(view);
        return getDesiredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    /* access modifiers changed from: protected */
    public int getMarginBottom(View view) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        return getLayoutParams(view).bottomMargin;
    }

    /* access modifiers changed from: protected */
    public int getMarginTop(View view) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        return getLayoutParams(view).topMargin;
    }

    /* access modifiers changed from: protected */
    public int getWidthWithMargins(View view) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        FrameLayout.LayoutParams layoutParams = getLayoutParams(view);
        return getDesiredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
    }

    /* access modifiers changed from: protected */
    public int getDesiredWidth(View view) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        return view.getMeasuredWidth();
    }

    /* access modifiers changed from: protected */
    public int getDesiredHeight(View view) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        return view.getMeasuredHeight();
    }

    /* access modifiers changed from: protected */
    public FrameLayout.LayoutParams getLayoutParams(View view) {
        return (FrameLayout.LayoutParams) view.getLayoutParams();
    }

    /* access modifiers changed from: protected */
    public int roundToNearest(int i, int i2) {
        return i2 * Math.round(((float) i) / ((float) i2));
    }

    /* access modifiers changed from: protected */
    public int dpToPixels(int i) {
        return (int) Math.floor((double) TypedValue.applyDimension(1, (float) i, this.mDisplay));
    }
}
