package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class HorizontalDisplay extends SinglePageDisplay implements Runnable {
    public HorizontalDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
    }

    /* access modifiers changed from: protected */
    public boolean fling(View view, float f, float f2) {
        View view2;
        Rect scrollBounds = getScrollBounds(view);
        BasePlugPDFDisplay.Direction direction = getDirection(f, f2);
        if (direction == BasePlugPDFDisplay.Direction.LEFT && scrollBounds.left >= 0) {
            View view3 = (View) this.mPageViews.get(this.mCurPageIdx + 1);
            if (view3 == null) {
                return false;
            }
            this.mCurPageIdx++;
            slideViewOntoScreen(view3);
            return true;
        } else if (direction != BasePlugPDFDisplay.Direction.RIGHT || scrollBounds.right > 0 || (view2 = (View) this.mPageViews.get(this.mCurPageIdx - 1)) == null) {
            return false;
        } else {
            this.mCurPageIdx--;
            slideViewOntoScreen(view2);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void nearPageLayout(Point point, int i, int i2, int i3, int i4) {
        if (this.mCurPageIdx > 0) {
            PageView pageView = (PageView) createPageView(this.mCurPageIdx - 1);
            int offset = getSubScreenSizeOffset(pageView).x + getOffset() + point.x;
            int i5 = i4 + i2;
            pageView.layout((i - pageView.getMeasuredWidth()) - offset, (i5 - pageView.getMeasuredHeight()) / 2, i - offset, (i5 + pageView.getMeasuredHeight()) / 2);
        }
        if (this.mCurPageIdx + 1 < this.mAdapter.getCount()) {
            PageView pageView2 = (PageView) createPageView(this.mCurPageIdx + 1);
            int offset2 = point.x + getOffset() + getSubScreenSizeOffset(pageView2).x;
            int i6 = i4 + i2;
            pageView2.layout(i3 + offset2, (i6 - pageView2.getMeasuredHeight()) / 2, i3 + pageView2.getMeasuredWidth() + offset2, (i6 + pageView2.getMeasuredHeight()) / 2);
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, int i, int i2, int i3, int i4, Point point) {
        int i5;
        if (this.mUserInteracting || !this.mScroller.isFinished()) {
            if (view.getMeasuredHeight() <= getHeight()) {
                Point correctionPt = getCorrectionPt(getScrollBounds(i, i2, i3, i4));
                i2 += correctionPt.y;
                i5 = correctionPt.y;
            }
            int i6 = i;
            int i7 = i2;
            int i8 = i3;
            int i9 = i4;
            view.layout(i6, i7, i8, i9);
            nearPageLayout(point, i6, i7, i8, i9);
        }
        Point correctionPt2 = getCorrectionPt(getScrollBounds(i, i2, i3, i4));
        i3 += correctionPt2.x;
        i += correctionPt2.x;
        i2 += correctionPt2.y;
        i5 = correctionPt2.y;
        i4 += i5;
        int i62 = i;
        int i72 = i2;
        int i82 = i3;
        int i92 = i4;
        view.layout(i62, i72, i82, i92);
        nearPageLayout(point, i62, i72, i82, i92);
    }

    /* access modifiers changed from: protected */
    public PointF returnValidScrollPoint(float f, float f2) {
        PointF returnValidScrollPoint = super.returnValidScrollPoint(f, f2);
        float f3 = returnValidScrollPoint.x;
        float f4 = returnValidScrollPoint.y;
        View view = (View) this.mPageViews.get(this.mCurPageIdx);
        float f5 = 0.0f;
        if (f4 > 0.0f && view.getTop() >= 0) {
            f4 = 0.0f;
        }
        if (f4 >= 0.0f || view.getBottom() > getHeight()) {
            f5 = f4;
        }
        return new PointF(f3, f5);
    }

    /* access modifiers changed from: protected */
    public int getCurrentPageIndexByScrollPosition() {
        return this.mCurPageIdx;
    }
}
