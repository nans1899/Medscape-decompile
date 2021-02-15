package com.medscape.android.reference.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;

public class NativeArticleBulletSpan implements LeadingMarginSpan {
    private static final int BULLET_RADIUS = 5;
    private static final int BULLET_SPAN = 8;
    public static final int STANDARD_GAP_WIDTH = 25;
    private static final int STANDARD_INDENT = 30;
    private static Path sBulletPath;
    private final int mColor = 0;
    private final int mGapWidth = 25;
    private boolean mIndent = true;
    private final boolean mWantColor = false;

    private int getBulletMargin() {
        return this.mIndent ? 30 : 0;
    }

    public int getLeadingMargin(boolean z) {
        return getBulletMargin() + 10 + this.mGapWidth;
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        if (((Spanned) charSequence).getSpanStart(this) == i6) {
            Paint.Style style = paint.getStyle();
            int i8 = 0;
            if (this.mWantColor) {
                i8 = paint.getColor();
                paint.setColor(this.mColor);
            }
            paint.setStyle(Paint.Style.FILL);
            if (canvas.isHardwareAccelerated()) {
                if (sBulletPath == null) {
                    Path path = new Path();
                    sBulletPath = path;
                    path.addCircle(0.0f, 0.0f, 6.0f, Path.Direction.CW);
                }
                canvas.save();
                canvas.translate((float) (getBulletMargin() + (i2 * 5)), ((float) (i3 + i5)) / 2.0f);
                canvas.drawPath(sBulletPath, paint);
                canvas.restore();
            } else {
                canvas.drawCircle((float) (i + (i2 * 5)), ((float) (i3 + i5)) / 2.0f, 5.0f, paint);
            }
            if (this.mWantColor) {
                paint.setColor(i8);
            }
            paint.setStyle(style);
        }
    }
}
