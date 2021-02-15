package com.google.firebase.inappmessaging.display.internal.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.google.firebase.inappmessaging.display.R;
import com.google.firebase.inappmessaging.display.internal.Logging;
import com.google.firebase.inappmessaging.display.internal.layout.util.MeasureUtils;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class CardLayoutPortrait extends BaseModalLayout {
    private static double IMAGE_MAX_HEIGHT_PCT = 0.8d;
    private View actionBarChild;
    private View imageChild;
    private View scrollChild;
    private View titleChild;

    public CardLayoutPortrait(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.imageChild = findChildById(R.id.image_view);
        this.titleChild = findChildById(R.id.message_title);
        this.scrollChild = findChildById(R.id.body_scroll);
        this.actionBarChild = findChildById(R.id.action_bar);
        int calculateBaseWidth = calculateBaseWidth(i);
        int calculateBaseHeight = calculateBaseHeight(i2);
        int roundToNearest = roundToNearest((int) (IMAGE_MAX_HEIGHT_PCT * ((double) calculateBaseHeight)), 4);
        Logging.logd("Measuring image");
        MeasureUtils.measureFullWidth(this.imageChild, calculateBaseWidth, calculateBaseHeight);
        if (getDesiredHeight(this.imageChild) > roundToNearest) {
            Logging.logd("Image exceeded maximum height, remeasuring image");
            MeasureUtils.measureFullHeight(this.imageChild, calculateBaseWidth, roundToNearest);
        }
        int desiredWidth = getDesiredWidth(this.imageChild);
        Logging.logd("Measuring title");
        MeasureUtils.measureFullWidth(this.titleChild, desiredWidth, calculateBaseHeight);
        Logging.logd("Measuring action bar");
        MeasureUtils.measureFullWidth(this.actionBarChild, desiredWidth, calculateBaseHeight);
        Logging.logd("Measuring scroll view");
        MeasureUtils.measureFullWidth(this.scrollChild, desiredWidth, ((calculateBaseHeight - getDesiredHeight(this.imageChild)) - getDesiredHeight(this.titleChild)) - getDesiredHeight(this.actionBarChild));
        int size = getVisibleChildren().size();
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            i3 += getDesiredHeight(getVisibleChildren().get(i4));
        }
        setMeasuredDimension(desiredWidth, i3);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int size = getVisibleChildren().size();
        int i5 = 0;
        for (int i6 = 0; i6 < size; i6++) {
            View view = getVisibleChildren().get(i6);
            int measuredHeight = view.getMeasuredHeight() + i5;
            int measuredWidth = view.getMeasuredWidth() + 0;
            Logging.logd("Layout child " + i6);
            Logging.logdPair("\t(top, bottom)", (float) i5, (float) measuredHeight);
            Logging.logdPair("\t(left, right)", (float) 0, (float) measuredWidth);
            view.layout(0, i5, measuredWidth, measuredHeight);
            Logging.logdPair("Child " + i6 + " wants to be ", (float) view.getMeasuredWidth(), (float) view.getMeasuredHeight());
            i5 += view.getMeasuredHeight();
        }
    }
}
