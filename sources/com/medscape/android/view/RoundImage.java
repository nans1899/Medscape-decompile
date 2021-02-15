package com.medscape.android.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class RoundImage extends Drawable {
    private final Bitmap mBitmap;
    private final int mBitmapHeight;
    private final int mBitmapWidth;
    private final Paint mPaint;
    private final RectF mRectF = new RectF();

    public int getOpacity() {
        return -3;
    }

    public RoundImage(Bitmap bitmap, int i, int i2) {
        this.mBitmap = bitmap;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        if (i <= 0) {
            this.mBitmapHeight = this.mBitmap.getHeight();
        } else {
            this.mBitmapHeight = i;
        }
        if (i2 <= 0) {
            this.mBitmapWidth = this.mBitmap.getHeight();
        } else {
            this.mBitmapWidth = i2;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawOval(this.mRectF, this.mPaint);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mRectF.set(rect);
    }

    public void setAlpha(int i) {
        if (this.mPaint.getAlpha() != i) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public void setFilterBitmap(boolean z) {
        this.mPaint.setFilterBitmap(z);
        invalidateSelf();
    }

    public void setDither(boolean z) {
        this.mPaint.setDither(z);
        invalidateSelf();
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }
}
