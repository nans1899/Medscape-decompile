package com.medscape.android.util.customtooltip;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;

public class ArrowDrawable extends ColorDrawable {
    public static final int AUTO = 4;
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int TOP = 1;
    private final int mBackgroundColor = 0;
    private final int mDirection;
    private final Paint mPaint;
    private Path mPath;

    ArrowDrawable(int i, int i2) {
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setColor(i);
        this.mDirection = i2;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updatePath(rect);
    }

    private synchronized void updatePath(Rect rect) {
        Path path = new Path();
        this.mPath = path;
        int i = this.mDirection;
        if (i == 0) {
            path.moveTo((float) rect.width(), (float) rect.height());
            this.mPath.lineTo(0.0f, (float) (rect.height() / 2));
            this.mPath.lineTo((float) rect.width(), 0.0f);
            this.mPath.lineTo((float) rect.width(), (float) rect.height());
        } else if (i == 1) {
            path.moveTo(0.0f, (float) rect.height());
            this.mPath.lineTo((float) (rect.width() / 2), 0.0f);
            this.mPath.lineTo((float) rect.width(), (float) rect.height());
            this.mPath.lineTo(0.0f, (float) rect.height());
        } else if (i == 2) {
            path.moveTo(0.0f, 0.0f);
            this.mPath.lineTo((float) rect.width(), (float) (rect.height() / 2));
            this.mPath.lineTo(0.0f, (float) rect.height());
            this.mPath.lineTo(0.0f, 0.0f);
        } else if (i == 3) {
            path.moveTo(0.0f, 0.0f);
            this.mPath.lineTo((float) (rect.width() / 2), (float) rect.height());
            this.mPath.lineTo((float) rect.width(), 0.0f);
            this.mPath.lineTo(0.0f, 0.0f);
        }
        this.mPath.close();
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(this.mBackgroundColor);
        if (this.mPath == null) {
            updatePath(getBounds());
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColor(int i) {
        this.mPaint.setColor(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        if (this.mPaint.getColorFilter() != null) {
            return -3;
        }
        int color = this.mPaint.getColor() >>> 24;
        if (color == 0) {
            return -2;
        }
        if (color != 255) {
            return -3;
        }
        return -1;
    }
}
