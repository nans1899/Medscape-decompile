package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class VerticalDisplay extends SinglePageDisplay implements Runnable {
    public VerticalDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.VERTICAL);
    }

    /* access modifiers changed from: protected */
    public boolean fling(View view, float f, float f2) {
        View view2;
        Rect scrollBounds = getScrollBounds(view);
        BasePlugPDFDisplay.Direction direction = getDirection(f, f2);
        if (direction == BasePlugPDFDisplay.Direction.UP && scrollBounds.top >= 0) {
            View view3 = (View) this.mPageViews.get(this.mCurPageIdx + 1);
            if (view3 == null) {
                return false;
            }
            this.mCurPageIdx++;
            slideViewOntoScreen(view3);
            return true;
        } else if (direction != BasePlugPDFDisplay.Direction.DOWN || scrollBounds.bottom > 0 || (view2 = (View) this.mPageViews.get(this.mCurPageIdx - 1)) == null) {
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
            int convertDipToPx = (int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap());
            pageView.layout(i, (i2 - pageView.getMeasuredHeight()) - convertDipToPx, i3, i2 - convertDipToPx);
        }
        if (this.mCurPageIdx + 1 < this.mAdapter.getCount()) {
            PageView pageView2 = (PageView) createPageView(this.mCurPageIdx + 1);
            int convertDipToPx2 = (int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap());
            pageView2.layout(i, i4 + convertDipToPx2, i3, i4 + pageView2.getMeasuredHeight() + convertDipToPx2);
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, int i, int i2, int i3, int i4, Point point) {
        if (!this.mUserInteracting && this.mScroller.isFinished()) {
            Point correctionPt = getCorrectionPt(getScrollBounds(i, i2, i3, i4));
            i3 += correctionPt.x;
            i += correctionPt.x;
            i2 += correctionPt.y;
            i4 += correctionPt.y;
        } else if (view.getMeasuredWidth() <= getWidth()) {
            Point correctionPt2 = getCorrectionPt(getScrollBounds(i, i2, i3, i4));
            i += correctionPt2.x;
            i3 += correctionPt2.x;
        }
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        view.layout(i5, i6, i7, i8);
        nearPageLayout(point, i5, i6, i7, i8);
    }

    /* access modifiers changed from: protected */
    public PointF returnValidScrollPoint(float f, float f2) {
        PointF returnValidScrollPoint = super.returnValidScrollPoint(f, f2);
        float f3 = returnValidScrollPoint.x;
        float f4 = returnValidScrollPoint.y;
        View view = (View) this.mPageViews.get(this.mCurPageIdx);
        float f5 = 0.0f;
        if (f3 > 0.0f && view.getLeft() >= 0) {
            f3 = 0.0f;
        }
        if (f3 >= 0.0f || view.getRight() > getWidth()) {
            f5 = f3;
        }
        return new PointF(f5, f4);
    }

    /* access modifiers changed from: protected */
    public int getCurrentPageIndexByScrollPosition() {
        return this.mCurPageIdx;
    }
}
