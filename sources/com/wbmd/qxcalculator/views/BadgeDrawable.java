package com.wbmd.qxcalculator.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.wbmd.qxcalculator.R;

public class BadgeDrawable extends Drawable {
    private Paint mBadgePaint;
    private String mCount = "";
    private Paint mTextPaint;
    private float mTextSize;
    private Rect mTxtRect = new Rect();
    private boolean mWillDraw = false;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public BadgeDrawable(Context context) {
        this.mTextSize = context.getResources().getDimension(R.dimen.badge_text_size);
        Paint paint = new Paint();
        this.mBadgePaint = paint;
        paint.setColor(-65536);
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas canvas) {
        if (this.mWillDraw) {
            Rect bounds = getBounds();
            float f = (float) (bounds.right - bounds.left);
            float f2 = (float) (bounds.bottom - bounds.top);
            float f3 = f * 2.0f;
            float f4 = (-f2) / 2.0f;
            canvas.drawCircle(f3, f4, ((Math.min(f, f2) / 2.0f) - 1.0f) / 2.0f, this.mBadgePaint);
            Paint paint = this.mTextPaint;
            String str = this.mCount;
            paint.getTextBounds(str, 0, str.length(), this.mTxtRect);
            canvas.drawText(this.mCount, f3, f4 + (((float) (this.mTxtRect.bottom - this.mTxtRect.top)) / 2.0f), this.mTextPaint);
        }
    }

    public void setCount(int i) {
        this.mCount = Integer.toString(i);
        this.mWillDraw = i > 0;
        invalidateSelf();
    }
}
