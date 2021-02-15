package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;

public class AnnotUnderline extends AnnotTextMarkup {
    public AnnotUnderline(Context context) {
        super(context, "UNDERLINE");
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawTextMarkup(canvas);
        super.onDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public void drawTextMarkup(Canvas canvas) {
        if (this.mQtps.size() == 0) {
            this.mRect.left = this.mBbox.left * this.mScale;
            this.mRect.top = this.mBbox.top * this.mScale;
            this.mRect.right = this.mBbox.right * this.mScale;
            this.mRect.bottom = this.mBbox.bottom * this.mScale;
            Path path = new Path();
            path.moveTo(this.mRect.left, this.mRect.top);
            path.lineTo(this.mRect.right, this.mRect.top);
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
            path2.lineTo(this.mRect.right, this.mRect.top);
            loadPaint();
            canvas.drawPath(path2, this.mPaint);
        }
    }
}
