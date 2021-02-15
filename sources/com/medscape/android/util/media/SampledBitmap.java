package com.medscape.android.util.media;

import android.graphics.Bitmap;

public class SampledBitmap {
    private Bitmap mBitmap;
    private int mSampleSize;

    public SampledBitmap(Bitmap bitmap, int i) {
        this.mBitmap = bitmap;
        this.mSampleSize = i;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void setSampleSize(int i) {
        this.mSampleSize = i;
    }

    public int getSampleSize() {
        return this.mSampleSize;
    }
}
