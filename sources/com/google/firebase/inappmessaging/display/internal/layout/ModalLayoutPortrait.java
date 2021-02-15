package com.google.firebase.inappmessaging.display.internal.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.firebase.inappmessaging.display.R;
import com.google.firebase.inappmessaging.display.internal.Logging;
import com.google.firebase.inappmessaging.display.internal.layout.util.MeasureUtils;
import com.google.firebase.inappmessaging.display.internal.layout.util.VerticalViewGroupMeasure;
import com.google.firebase.inappmessaging.display.internal.layout.util.ViewMeasure;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class ModalLayoutPortrait extends BaseModalLayout {
    private static final int ITEM_SPACING_DP = 24;
    private int vertItemSpacing;
    private VerticalViewGroupMeasure vgm = new VerticalViewGroupMeasure();

    public ModalLayoutPortrait(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.vertItemSpacing = dpToPixels(24);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int calculateBaseWidth = calculateBaseWidth(i);
        int calculateBaseHeight = calculateBaseHeight(i2);
        boolean z = true;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + ((getVisibleChildren().size() - 1) * this.vertItemSpacing);
        this.vgm.reset(calculateBaseWidth, calculateBaseHeight);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            this.vgm.add(childAt, isFlex(childAt));
        }
        Logging.logd("Screen dimens: " + getDisplayMetrics());
        Logging.logdPair("Max pct", getMaxWidthPct(), getMaxHeightPct());
        float f = (float) calculateBaseWidth;
        Logging.logdPair("Base dimens", f, (float) calculateBaseHeight);
        for (ViewMeasure preMeasure : this.vgm.getViews()) {
            Logging.logd("Pre-measure child");
            preMeasure.preMeasure(calculateBaseWidth, calculateBaseHeight);
        }
        int totalHeight = this.vgm.getTotalHeight() + paddingBottom;
        Logging.logdNumber("Total reserved height", (float) paddingBottom);
        Logging.logdNumber("Total desired height", (float) totalHeight);
        if (totalHeight <= calculateBaseHeight) {
            z = false;
        }
        Logging.logd("Total height constrained: " + z);
        if (z) {
            this.vgm.allocateSpace((calculateBaseHeight - paddingBottom) - this.vgm.getTotalFixedHeight());
        }
        int i4 = calculateBaseWidth - paddingRight;
        for (ViewMeasure next : this.vgm.getViews()) {
            Logging.logd("Measuring child");
            MeasureUtils.measureAtMost(next.getView(), i4, next.getMaxHeight());
            paddingBottom += getDesiredHeight(next.getView());
        }
        Logging.logdPair("Measured dims", f, (float) paddingBottom);
        setMeasuredDimension(calculateBaseWidth, paddingBottom);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        super.onLayout(z, i, i2, i3, i4);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int size = getVisibleChildren().size();
        for (int i7 = 0; i7 < size; i7++) {
            View view = getVisibleChildren().get(i7);
            FrameLayout.LayoutParams layoutParams = getLayoutParams(view);
            int measuredHeight = view.getMeasuredHeight();
            int measuredWidth = view.getMeasuredWidth();
            int i8 = measuredHeight + paddingTop;
            if ((layoutParams.gravity & 1) == 1) {
                int i9 = (i3 - i) / 2;
                int i10 = measuredWidth / 2;
                i5 = i9 - i10;
                i6 = i9 + i10;
            } else {
                i6 = paddingLeft + measuredWidth;
                i5 = paddingLeft;
            }
            Logging.logd("Layout child " + i7);
            Logging.logdPair("\t(top, bottom)", (float) paddingTop, (float) i8);
            Logging.logdPair("\t(left, right)", (float) i5, (float) i6);
            view.layout(i5, paddingTop, i6, i8);
            paddingTop += view.getMeasuredHeight();
            if (i7 < size - 1) {
                paddingTop += this.vertItemSpacing;
            }
        }
    }

    private boolean isFlex(View view) {
        return view.getId() == R.id.body_scroll || view.getId() == R.id.image_view;
    }
}
