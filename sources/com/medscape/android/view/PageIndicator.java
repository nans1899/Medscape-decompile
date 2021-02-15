package com.medscape.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.util.Util;

public class PageIndicator extends LinearLayout {
    private static final int DEFAULT_DRAWABLE = 2131231418;
    private static final int DEFAULT_HORIZONTAL_SPACING = ((int) Util.dpToPixel(MedscapeApplication.get(), 7));
    private static final int DEFAULT_INDICATOR_DIAMETER = ((int) Util.dpToPixel(MedscapeApplication.get(), 15));
    public static final int MAX_INDICATORS = 10;
    int mDesiredPages;
    int mHorizontalSpacing;
    int mIndicatorDiameter;
    int mIndicatorDrawable;
    LinearLayout.LayoutParams mLayoutParams;
    int mSelected;

    public PageIndicator(Context context) {
        super(context);
    }

    public PageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Medscape);
        this.mHorizontalSpacing = obtainStyledAttributes.getDimensionPixelOffset(6, DEFAULT_HORIZONTAL_SPACING);
        this.mIndicatorDiameter = obtainStyledAttributes.getDimensionPixelSize(1, DEFAULT_INDICATOR_DIAMETER);
        this.mIndicatorDrawable = obtainStyledAttributes.getResourceId(0, R.drawable.page_indicator);
        this.mSelected = obtainStyledAttributes.getInt(13, -1);
        int i = obtainStyledAttributes.getInt(11, 0);
        obtainStyledAttributes.recycle();
        int i2 = this.mIndicatorDiameter;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        this.mLayoutParams = layoutParams;
        layoutParams.rightMargin = this.mHorizontalSpacing;
        setOrientation(0);
        setGravity(1);
        setTotalPages(i);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = this.mSelected;
        if (-1 < i5 && i5 < getChildCount()) {
            getChildAt(this.mSelected).setSelected(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (this.mIndicatorDiameter > (size - getPaddingTop()) - getPaddingBottom()) {
            LinearLayout.LayoutParams layoutParams = this.mLayoutParams;
            int paddingTop = (size - getPaddingTop()) - getPaddingBottom();
            this.mIndicatorDiameter = paddingTop;
            layoutParams.width = paddingTop;
            layoutParams.height = paddingTop;
            int childCount = getChildCount();
            for (int i3 = 0; i3 < childCount; i3++) {
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) getChildAt(i3).getLayoutParams();
                layoutParams2.width = this.mIndicatorDiameter;
                layoutParams2.height = this.mIndicatorDiameter;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setTotalPages(int i) {
        if (i > 10) {
            i = 10;
        }
        this.mDesiredPages = i;
        Context context = getContext();
        if (getChildCount() < this.mDesiredPages) {
            for (int childCount = getChildCount(); childCount < this.mDesiredPages; childCount++) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setImageResource(this.mIndicatorDrawable);
                addView(imageView, this.mLayoutParams);
            }
        } else if (getChildCount() > this.mDesiredPages) {
            for (int childCount2 = getChildCount(); childCount2 > this.mDesiredPages; childCount2--) {
                removeViewAt(childCount2 - 1);
            }
        }
    }

    public int getTotalPages() {
        return getChildCount();
    }

    public void setSelectedPage(int i) {
        int i2 = this.mSelected;
        if (i2 != i) {
            if (-1 < i2 && i2 < getChildCount()) {
                getChildAt(this.mSelected).setSelected(false);
            }
            int i3 = i % 10;
            this.mSelected = i3;
            if (getChildAt(i3) != null) {
                getChildAt(this.mSelected).setSelected(true);
            }
        }
    }

    public int getSelectedPage() {
        return this.mSelected;
    }

    public final void setOrientation(int i) {
        super.setOrientation(0);
    }
}
