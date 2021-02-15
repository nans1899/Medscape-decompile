package com.google.firebase.inappmessaging.display.internal.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.firebase.inappmessaging.display.R;
import com.google.firebase.inappmessaging.display.internal.Logging;
import com.google.firebase.inappmessaging.display.internal.layout.util.MeasureUtils;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class ModalLayoutLandscape extends BaseModalLayout {
    private static final int ITEM_SPACING_DP = 24;
    private static final float MAX_IMG_WIDTH_PCT = 0.4f;
    private int barrierWidth;
    private View buttonChild;
    private View imageChild;
    private int leftContentHeight;
    private int rightContentHeight;
    private View scrollChild;
    private View titleChild;
    private int vertItemSpacing;

    public ModalLayoutLandscape(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.imageChild = findChildById(R.id.image_view);
        this.titleChild = findChildById(R.id.message_title);
        this.scrollChild = findChildById(R.id.body_scroll);
        this.buttonChild = findChildById(R.id.button);
        int i3 = 0;
        this.barrierWidth = this.imageChild.getVisibility() == 8 ? 0 : dpToPixels(24);
        this.vertItemSpacing = dpToPixels(24);
        List<View> asList = Arrays.asList(new View[]{this.titleChild, this.scrollChild, this.buttonChild});
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int calculateBaseWidth = calculateBaseWidth(i);
        int calculateBaseHeight = calculateBaseHeight(i2) - paddingBottom;
        int i4 = calculateBaseWidth - paddingLeft;
        Logging.logd("Measuring image");
        MeasureUtils.measureAtMost(this.imageChild, (int) (((float) i4) * MAX_IMG_WIDTH_PCT), calculateBaseHeight);
        int desiredWidth = getDesiredWidth(this.imageChild);
        int i5 = i4 - (this.barrierWidth + desiredWidth);
        float f = (float) desiredWidth;
        Logging.logdPair("Max col widths (l, r)", f, (float) i5);
        int i6 = 0;
        for (View visibility : asList) {
            if (visibility.getVisibility() != 8) {
                i6++;
            }
        }
        int max = Math.max(0, (i6 - 1) * this.vertItemSpacing);
        int i7 = calculateBaseHeight - max;
        Logging.logd("Measuring getTitle");
        MeasureUtils.measureAtMost(this.titleChild, i5, i7);
        Logging.logd("Measuring button");
        MeasureUtils.measureAtMost(this.buttonChild, i5, i7);
        Logging.logd("Measuring scroll view");
        MeasureUtils.measureAtMost(this.scrollChild, i5, (i7 - getDesiredHeight(this.titleChild)) - getDesiredHeight(this.buttonChild));
        this.leftContentHeight = getDesiredHeight(this.imageChild);
        this.rightContentHeight = max;
        for (View desiredHeight : asList) {
            this.rightContentHeight += getDesiredHeight(desiredHeight);
        }
        int max2 = Math.max(this.leftContentHeight + paddingBottom, this.rightContentHeight + paddingBottom);
        for (View desiredWidth2 : asList) {
            i3 = Math.max(getDesiredWidth(desiredWidth2), i3);
        }
        Logging.logdPair("Measured columns (l, r)", f, (float) i3);
        int i8 = desiredWidth + i3 + this.barrierWidth + paddingLeft;
        Logging.logdPair("Measured dims", (float) i8, (float) max2);
        setMeasuredDimension(i8, max2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        super.onLayout(z, i, i2, i3, i4);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int measuredWidth = getMeasuredWidth() - getPaddingRight();
        int i7 = this.leftContentHeight;
        int i8 = this.rightContentHeight;
        int i9 = 0;
        if (i7 < i8) {
            i5 = (i8 - i7) / 2;
            i6 = 0;
        } else {
            i6 = (i7 - i8) / 2;
            i5 = 0;
        }
        Logging.logd("Layout image");
        int i10 = paddingTop + i5;
        int desiredWidth = getDesiredWidth(this.imageChild) + paddingLeft;
        layoutChild(this.imageChild, paddingLeft, i10, desiredWidth, i10 + getDesiredHeight(this.imageChild));
        int i11 = desiredWidth + this.barrierWidth;
        Logging.logd("Layout getTitle");
        int i12 = paddingTop + i6;
        int desiredHeight = getDesiredHeight(this.titleChild) + i12;
        layoutChild(this.titleChild, i11, i12, measuredWidth, desiredHeight);
        Logging.logd("Layout getBody");
        int i13 = desiredHeight + (this.titleChild.getVisibility() == 8 ? 0 : this.vertItemSpacing);
        int desiredHeight2 = getDesiredHeight(this.scrollChild) + i13;
        layoutChild(this.scrollChild, i11, i13, measuredWidth, desiredHeight2);
        Logging.logd("Layout button");
        if (this.scrollChild.getVisibility() != 8) {
            i9 = this.vertItemSpacing;
        }
        layoutChild(this.buttonChild, i11, desiredHeight2 + i9);
    }

    /* access modifiers changed from: protected */
    public void layoutCenterHorizontal(View view, int i, int i2, int i3, int i4) {
        int measuredWidth = view.getMeasuredWidth() / 2;
        int i5 = i + ((i3 - i) / 2);
        layoutChild(view, i5 - measuredWidth, i2, i5 + measuredWidth, i4);
    }
}
