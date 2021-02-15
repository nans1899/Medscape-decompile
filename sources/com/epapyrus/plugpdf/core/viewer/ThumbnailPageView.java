package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.View;
import com.epapyrus.plugpdf.core.PDFDocument;

public class ThumbnailPageView extends PageView {
    public void adjustPatch() {
    }

    public ThumbnailPageView(Context context, PDFDocument pDFDocument, Point point) {
        super(context, pDFDocument, point);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (View.MeasureSpec.getMode(i) != 0) {
            i3 = View.MeasureSpec.getSize(i);
        } else {
            i3 = this.mSize.x;
        }
        if (View.MeasureSpec.getMode(i2) != 0) {
            i4 = View.MeasureSpec.getSize(i2);
        } else {
            i4 = this.mSize.y;
        }
        setMeasuredDimension(i3, (int) (((float) i4) * (((float) i3) / ((float) this.mParentSize.x))));
        if (this.mBusyIndicator != null) {
            int min = (Math.min(this.mParentSize.x, this.mParentSize.y) / 6) | Integer.MIN_VALUE;
            this.mBusyIndicator.measure(min, min);
        }
    }

    /* access modifiers changed from: protected */
    public void clearEntire() {
        if (this.mEntire != null) {
            this.mEntire.setImageBitmap((Bitmap) null);
        }
    }

    public void setPage(int i, PointF pointF) {
        this.mPageIdx = i;
        calculateSize(pointF);
        drawEntire();
    }
}
