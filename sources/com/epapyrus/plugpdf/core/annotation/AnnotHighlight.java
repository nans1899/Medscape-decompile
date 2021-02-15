package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;

public class AnnotHighlight extends AnnotTextMarkup {
    public AnnotHighlight(Context context) {
        super(context, "HIGHLIGHT");
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
            path.moveTo(this.mRect.left, this.mRect.top + (this.mRect.height() / 2.0f));
            path.lineTo(this.mRect.right, this.mRect.top + (this.mRect.height() / 2.0f));
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
            path2.moveTo(this.mRect.left, this.mRect.top + (this.mRect.height() / 2.0f));
            path2.lineTo(this.mRect.right, this.mRect.top + (this.mRect.height() / 2.0f));
            loadPaint();
            canvas.drawPath(path2, this.mPaint);
        }
    }

    /* access modifiers changed from: protected */
    public void loadPaint() {
        super.loadPaint();
        this.mPaint.setAlpha(this.mPaint.getAlpha() / 2);
        if (this.mRect.height() < 0.0f) {
            this.mPaint.setStrokeWidth(-this.mRect.height());
        } else {
            this.mPaint.setStrokeWidth(this.mRect.height());
        }
    }
}
