package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;

public class AnnotSquiggly extends AnnotTextMarkup {
    public AnnotSquiggly(Context context) {
        super(context, "SQUIGGLY");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawTextMarkup(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawTextMarkup(Canvas canvas) {
        boolean z = false;
        if (this.mQtps.size() == 0) {
            this.mRect.left = this.mBbox.left * this.mScale;
            this.mRect.top = this.mBbox.top * this.mScale;
            this.mRect.right = this.mBbox.right * this.mScale;
            this.mRect.bottom = this.mBbox.bottom * this.mScale;
            Path path = new Path();
            float f = this.mRect.left;
            while (f < this.mRect.right + (this.mScale * 2.0f)) {
                if (z) {
                    path.lineTo(f, this.mRect.bottom - 2.0f);
                } else {
                    path.lineTo(f, this.mRect.bottom + 2.0f);
                }
                z = !z;
                f += this.mScale * 2.0f;
            }
            loadPaint();
            canvas.drawPath(path, this.mPaint);
            return;
        }
        for (int i = 0; i < this.mQtps.size(); i += 8) {
            this.mRect.left = ((Float) this.mQtps.get(i)).floatValue() * this.mScale;
            this.mRect.top = ((Float) this.mQtps.get(i + 1)).floatValue() * this.mScale;
            this.mRect.right = ((Float) this.mQtps.get(i + 6)).floatValue() * this.mScale;
            this.mRect.bottom = ((Float) this.mQtps.get(i + 7)).floatValue() * this.mScale;
            Path path2 = new Path();
            path2.moveTo(this.mRect.left, this.mRect.top);
            float f2 = this.mRect.left;
            boolean z2 = false;
            while (f2 < this.mRect.right + (this.mScale * 2.0f)) {
                if (z2) {
                    path2.lineTo(f2, this.mRect.top - 2.0f);
                } else {
                    path2.lineTo(f2, this.mRect.top + 2.0f);
                }
                z2 = !z2;
                f2 += this.mScale * 2.0f;
            }
            loadPaint();
            canvas.drawPath(path2, this.mPaint);
        }
    }
}
