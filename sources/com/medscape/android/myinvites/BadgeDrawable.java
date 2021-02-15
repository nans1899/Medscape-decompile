package com.medscape.android.myinvites;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.R;

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
        this.mTextSize = context.getResources().getDimension(R.dimen.invitations_text_size);
        Paint paint = new Paint();
        this.mBadgePaint = paint;
        paint.setColor(-65536);
        this.mBadgePaint.setAntiAlias(true);
        this.mBadgePaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mTextPaint = paint2;
        paint2.setColor(-1);
        this.mTextPaint.setTypeface(Typeface.DEFAULT);
        this.mTextPaint.setTextSize(this.mTextSize);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void draw(Canvas canvas) {
        if (this.mWillDraw) {
            Rect bounds = getBounds();
            float f = (float) (bounds.right - bounds.left);
            float max = (Math.max(f, (float) (bounds.bottom - bounds.top)) / 2.0f) / 2.0f;
            float f2 = ((f - max) - 1.0f) + 10.0f;
            float f3 = max - 5.0f;
            if (this.mCount.length() < 2) {
                canvas.drawCircle(f2, f3, max + 1.0f, this.mBadgePaint);
            } else {
                canvas.drawCircle(f2, f3, max + 2.0f, this.mBadgePaint);
            }
            Paint paint = this.mTextPaint;
            String str = this.mCount;
            paint.getTextBounds(str, 0, str.length(), this.mTxtRect);
            float f4 = f3 + (((float) (this.mTxtRect.bottom - this.mTxtRect.top)) / 2.0f);
            if (this.mCount.length() > 2) {
                canvas.drawText("99+", f2, f4, this.mTextPaint);
            } else {
                canvas.drawText(this.mCount, f2, f4, this.mTextPaint);
            }
        }
    }

    public void setCount(String str) {
        this.mCount = str;
        this.mWillDraw = !str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        invalidateSelf();
    }
}
