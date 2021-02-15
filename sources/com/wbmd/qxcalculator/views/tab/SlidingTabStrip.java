package com.wbmd.qxcalculator.views.tab;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.views.tab.SlidingTabLayout;

class SlidingTabStrip extends LinearLayout {
    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = Byte.MAX_VALUE;
    private static final float DEFAULT_DIVIDER_HEIGHT = 0.5f;
    private static final int DEFAULT_DIVIDER_THICKNESS_DIPS = 1;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = -1;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 4;
    private SlidingTabLayout.TabColorizer mCustomTabColorizer;
    private final int mDefaultBottomBorderColor;
    private final SimpleTabColorizer mDefaultTabColorizer;
    private final float mDividerHeight;
    private final Paint mDividerPaint;
    private final Paint mSelectedIndicatorPaint;
    private final int mSelectedIndicatorThickness;
    private int mSelectedPosition;
    private float mSelectionOffset;
    View oldSelection;
    private float unselectedAlpha;

    SlidingTabStrip(Context context) {
        this(context, (AttributeSet) null);
    }

    SlidingTabStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.oldSelection = null;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(R.dimen.tab_unselected_alpha, typedValue, true);
        this.unselectedAlpha = typedValue.getFloat();
        setWillNotDraw(false);
        float f = getResources().getDisplayMetrics().density;
        this.mDefaultBottomBorderColor = getResources().getColor(R.color.tab_border_bottom);
        SimpleTabColorizer simpleTabColorizer = new SimpleTabColorizer();
        this.mDefaultTabColorizer = simpleTabColorizer;
        simpleTabColorizer.setIndicatorColors(-1);
        this.mDefaultTabColorizer.setDividerColors(context.getResources().getColor(R.color.tab_divider));
        this.mSelectedIndicatorThickness = (int) (4.0f * f);
        this.mSelectedIndicatorPaint = new Paint();
        this.mDividerHeight = DEFAULT_DIVIDER_HEIGHT;
        Paint paint = new Paint();
        this.mDividerPaint = paint;
        paint.setStrokeWidth((float) ((int) (f * 1.0f)));
    }

    /* access modifiers changed from: package-private */
    public void setCustomTabColorizer(SlidingTabLayout.TabColorizer tabColorizer) {
        this.mCustomTabColorizer = tabColorizer;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setSelectedIndicatorColors(int... iArr) {
        this.mCustomTabColorizer = null;
        this.mDefaultTabColorizer.setIndicatorColors(iArr);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setDividerColors(int... iArr) {
        this.mCustomTabColorizer = null;
        this.mDefaultTabColorizer.setDividerColors(iArr);
        invalidate();
    }

    private void removeOldSelection() {
        View view = this.oldSelection;
        if (view != null) {
            view.setSelected(false);
            ImageView imageView = (ImageView) this.oldSelection.findViewById(R.id.tab_image_view);
            if (imageView != null) {
                imageView.setAlpha(this.unselectedAlpha);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onViewPagerPageChanged(int i, float f) {
        this.mSelectedPosition = i;
        this.mSelectionOffset = f;
        if (f == 0.0f) {
            updateTabSelected(i);
        }
        invalidate();
    }

    public void updateTabSelected(int i) {
        View childAt = getChildAt(i);
        if (childAt != null && childAt != this.oldSelection) {
            childAt.setSelected(true);
            ImageView imageView = (ImageView) childAt.findViewById(R.id.tab_image_view);
            if (imageView != null) {
                imageView.setAlpha(1.0f);
            }
            removeOldSelection();
            this.oldSelection = childAt;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        float f = (float) height;
        int min = (int) (Math.min(Math.max(0.0f, this.mDividerHeight), 1.0f) * f);
        SlidingTabLayout.TabColorizer tabColorizer = this.mCustomTabColorizer;
        if (tabColorizer == null) {
            tabColorizer = this.mDefaultTabColorizer;
        }
        SlidingTabLayout.TabColorizer tabColorizer2 = tabColorizer;
        getResources().getBoolean(R.bool.is_rtl);
        if (childCount > 0) {
            View childAt = getChildAt(this.mSelectedPosition);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int indicatorColor = tabColorizer2.getIndicatorColor(this.mSelectedPosition);
            if (this.mSelectionOffset > 0.0f && this.mSelectedPosition < getChildCount() - 1) {
                int indicatorColor2 = tabColorizer2.getIndicatorColor(this.mSelectedPosition + 1);
                if (indicatorColor != indicatorColor2) {
                    indicatorColor = blendColors(indicatorColor2, indicatorColor, this.mSelectionOffset);
                }
                View childAt2 = getChildAt(this.mSelectedPosition + 1);
                float left2 = this.mSelectionOffset * ((float) childAt2.getLeft());
                float f2 = this.mSelectionOffset;
                left = (int) (left2 + ((1.0f - f2) * ((float) left)));
                right = (int) ((f2 * ((float) childAt2.getRight())) + ((1.0f - this.mSelectionOffset) * ((float) right)));
            }
            this.mSelectedIndicatorPaint.setColor(indicatorColor);
            canvas.drawRect((float) left, (float) (height - this.mSelectedIndicatorThickness), (float) right, f, this.mSelectedIndicatorPaint);
        }
        int i = (height - min) / 2;
        for (int i2 = 0; i2 < childCount - 1; i2++) {
            View childAt3 = getChildAt(i2);
            this.mDividerPaint.setColor(tabColorizer2.getDividerColor(i2));
            canvas.drawLine((float) childAt3.getRight(), (float) i, (float) childAt3.getRight(), (float) (i + min), this.mDividerPaint);
        }
    }

    private static int setColorAlpha(int i, byte b) {
        return Color.argb(b, Color.red(i), Color.green(i), Color.blue(i));
    }

    private static int blendColors(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((((float) Color.red(i)) * f) + (((float) Color.red(i2)) * f2)), (int) ((((float) Color.green(i)) * f) + (((float) Color.green(i2)) * f2)), (int) ((((float) Color.blue(i)) * f) + (((float) Color.blue(i2)) * f2)));
    }

    private static class SimpleTabColorizer implements SlidingTabLayout.TabColorizer {
        private int[] mDividerColors;
        private int[] mIndicatorColors;

        private SimpleTabColorizer() {
        }

        public final int getIndicatorColor(int i) {
            int[] iArr = this.mIndicatorColors;
            return iArr[i % iArr.length];
        }

        public final int getDividerColor(int i) {
            int[] iArr = this.mDividerColors;
            return iArr[i % iArr.length];
        }

        /* access modifiers changed from: package-private */
        public void setIndicatorColors(int... iArr) {
            this.mIndicatorColors = iArr;
        }

        /* access modifiers changed from: package-private */
        public void setDividerColors(int... iArr) {
            this.mDividerColors = iArr;
        }
    }
}
