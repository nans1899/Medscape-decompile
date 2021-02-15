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
public class CardLayoutLandscape extends BaseModalLayout {
    private static double IMAGE_MAX_WIDTH_PCT = 0.6d;
    private View actionBarChild;
    private View imageChild;
    private View scrollChild;
    private View titleChild;

    public CardLayoutLandscape(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.imageChild = findChildById(R.id.image_view);
        this.titleChild = findChildById(R.id.message_title);
        this.scrollChild = findChildById(R.id.body_scroll);
        View findChildById = findChildById(R.id.action_bar);
        this.actionBarChild = findChildById;
        int i3 = 0;
        List<View> asList = Arrays.asList(new View[]{this.titleChild, this.scrollChild, findChildById});
        int calculateBaseWidth = calculateBaseWidth(i);
        int calculateBaseHeight = calculateBaseHeight(i2);
        int roundToNearest = roundToNearest((int) (IMAGE_MAX_WIDTH_PCT * ((double) calculateBaseWidth)), 4);
        Logging.logd("Measuring image");
        MeasureUtils.measureFullHeight(this.imageChild, calculateBaseWidth, calculateBaseHeight);
        if (getDesiredWidth(this.imageChild) > roundToNearest) {
            Logging.logd("Image exceeded maximum width, remeasuring image");
            MeasureUtils.measureFullWidth(this.imageChild, roundToNearest, calculateBaseHeight);
        }
        int desiredHeight = getDesiredHeight(this.imageChild);
        int desiredWidth = getDesiredWidth(this.imageChild);
        int i4 = calculateBaseWidth - desiredWidth;
        float f = (float) desiredWidth;
        Logging.logdPair("Max col widths (l, r)", f, (float) i4);
        Logging.logd("Measuring title");
        MeasureUtils.measureAtMost(this.titleChild, i4, desiredHeight);
        Logging.logd("Measuring action bar");
        MeasureUtils.measureAtMost(this.actionBarChild, i4, desiredHeight);
        Logging.logd("Measuring scroll view");
        MeasureUtils.measureFullHeight(this.scrollChild, i4, (desiredHeight - getDesiredHeight(this.titleChild)) - getDesiredHeight(this.actionBarChild));
        for (View desiredWidth2 : asList) {
            i3 = Math.max(getDesiredWidth(desiredWidth2), i3);
        }
        Logging.logdPair("Measured columns (l, r)", f, (float) i3);
        int i5 = desiredWidth + i3;
        Logging.logdPair("Measured dims", (float) i5, (float) desiredHeight);
        setMeasuredDimension(i5, desiredHeight);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        Logging.logd("Layout image");
        int desiredWidth = getDesiredWidth(this.imageChild);
        layoutChild(this.imageChild, 0, 0, desiredWidth, getDesiredHeight(this.imageChild));
        Logging.logd("Layout title");
        int desiredHeight = getDesiredHeight(this.titleChild);
        int i5 = desiredWidth;
        int i6 = measuredWidth;
        layoutChild(this.titleChild, i5, 0, i6, desiredHeight);
        Logging.logd("Layout scroll");
        layoutChild(this.scrollChild, i5, desiredHeight, i6, desiredHeight + getDesiredHeight(this.scrollChild));
        Logging.logd("Layout action bar");
        layoutChild(this.actionBarChild, i5, measuredHeight - getDesiredHeight(this.actionBarChild), i6, measuredHeight);
    }
}
