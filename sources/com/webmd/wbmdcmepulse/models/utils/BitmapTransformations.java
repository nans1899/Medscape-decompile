package com.webmd.wbmdcmepulse.models.utils;

import android.graphics.Bitmap;
import com.squareup.picasso.Transformation;

public class BitmapTransformations implements Transformation {
    int mTargetHeight = -1;
    int mTargetWidth = -1;

    public String key() {
        return "transformation desiredWidth";
    }

    public Bitmap transform(Bitmap bitmap) {
        int i;
        if (this.mTargetWidth != -1 && this.mTargetHeight == -1) {
            this.mTargetHeight = (int) (((double) this.mTargetWidth) * (((double) bitmap.getHeight()) / ((double) bitmap.getWidth())));
        }
        int i2 = this.mTargetWidth;
        if (i2 == -1 || (i = this.mTargetHeight) == -1) {
            return bitmap;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i2, i, false);
        if (createScaledBitmap != bitmap) {
            bitmap.recycle();
        }
        return createScaledBitmap;
    }

    public void setTargetWidth(int i) {
        this.mTargetWidth = i;
    }

    public void setTargetHeight(int i) {
        this.mTargetHeight = i;
    }
}
