package com.google.firebase.inappmessaging.display.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class ResizableImageView extends AppCompatImageView {
    private int mDensityDpi;

    public ResizableImageView(Context context) {
        super(context);
        init(context);
    }

    public ResizableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ResizableImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mDensityDpi = (int) (context.getResources().getDisplayMetrics().density * 160.0f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (Build.VERSION.SDK_INT > 16) {
            Drawable drawable = getDrawable();
            boolean adjustViewBounds = getAdjustViewBounds();
            if (drawable != null && adjustViewBounds) {
                scalePxToDp(drawable);
                checkMinDim();
            }
        }
    }

    private void checkMinDim() {
        int max = Math.max(getMinimumWidth(), getSuggestedMinimumWidth());
        int max2 = Math.max(getMinimumHeight(), getSuggestedMinimumHeight());
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = (float) max;
        float f2 = (float) max2;
        Logging.logdPair("Image: min width, height", f, f2);
        float f3 = (float) measuredWidth;
        float f4 = (float) measuredHeight;
        Logging.logdPair("Image: actual width, height", f3, f4);
        float f5 = 1.0f;
        float f6 = measuredWidth < max ? f / f3 : 1.0f;
        if (measuredHeight < max2) {
            f5 = f2 / f4;
        }
        if (f6 <= f5) {
            f6 = f5;
        }
        if (((double) f6) > 1.0d) {
            int ceil = (int) Math.ceil((double) (f3 * f6));
            int ceil2 = (int) Math.ceil((double) (f4 * f6));
            Logging.logd("Measured dimension (" + measuredWidth + "x" + measuredHeight + ") too small.  Resizing to " + ceil + "x" + ceil2);
            Dimensions bound = bound(ceil, ceil2);
            setMeasuredDimension(bound.w, bound.h);
        }
    }

    private void scalePxToDp(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Logging.logdPair("Image: intrinsic width, height", (float) intrinsicWidth, (float) intrinsicHeight);
        Dimensions bound = bound((int) Math.ceil((double) ((intrinsicWidth * this.mDensityDpi) / 160)), (int) Math.ceil((double) ((intrinsicHeight * this.mDensityDpi) / 160)));
        Logging.logdPair("Image: new target dimensions", (float) bound.w, (float) bound.h);
        setMeasuredDimension(bound.w, bound.h);
    }

    private Dimensions bound(int i, int i2) {
        int maxWidth = getMaxWidth();
        int maxHeight = getMaxHeight();
        if (i > maxWidth) {
            Logging.logdNumber("Image: capping width", (float) maxWidth);
            i2 = (i2 * maxWidth) / i;
            i = maxWidth;
        }
        if (i2 > maxHeight) {
            Logging.logdNumber("Image: capping height", (float) maxHeight);
            i = (i * maxHeight) / i2;
        } else {
            maxHeight = i2;
        }
        return new Dimensions(i, maxHeight);
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    private static class Dimensions {
        final int h;
        final int w;

        private Dimensions(int i, int i2) {
            this.w = i;
            this.h = i2;
        }
    }
}
