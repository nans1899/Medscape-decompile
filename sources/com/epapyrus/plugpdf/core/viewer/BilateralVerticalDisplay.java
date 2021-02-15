package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class BilateralVerticalDisplay extends BilateralPageDisplay implements Runnable {
    public BilateralVerticalDisplay(Context context, boolean z) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.BILATERAL_VERTICAL, z);
    }

    /* access modifiers changed from: protected */
    public boolean fling(Rect rect, float f, float f2) {
        BasePlugPDFDisplay.Direction direction = getDirection(f, f2);
        if (direction != BasePlugPDFDisplay.Direction.UP || rect.top < 0) {
            if (direction != BasePlugPDFDisplay.Direction.DOWN || rect.bottom > 0 || ((View) this.mPageViews.get(getStandaredPage(this.mCurPageIdx) - 1)) == null) {
                return false;
            }
            this.mCurPageIdx -= 2;
            slideViewOntoScreen();
            return true;
        } else if (((View) this.mPageViews.get(getStandaredPage(this.mCurPageIdx) + 2)) == null) {
            return false;
        } else {
            this.mCurPageIdx += 2;
            slideViewOntoScreen();
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void nearPageLayout(Point point, Rect rect, Rect rect2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        if (getStandaredPage(this.mCurPageIdx) > 0) {
            PageView pageView = (PageView) createPageView(this.mCurPageIdx - 2, true);
            PageView pageView2 = (PageView) createPageView(this.mCurPageIdx - 2, false);
            if (pageView2 == null || pageView == null) {
                i3 = 0;
            } else {
                if (pageView2.getMeasuredHeight() > pageView.getMeasuredHeight()) {
                    i5 = pageView2.getMeasuredHeight() / 2;
                    i4 = pageView.getMeasuredHeight() / 2;
                } else {
                    i5 = pageView.getMeasuredHeight() / 2;
                    i4 = pageView2.getMeasuredHeight() / 2;
                }
                i3 = i5 - i4;
            }
            int convertDipToPx = rect.height() > rect2.height() ? rect.top : (rect2.top - ((int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap()))) - i3;
            if (pageView2 != null) {
                pageView2.layout(rect.left, convertDipToPx - rect.height(), rect.right, convertDipToPx);
            }
            pageView.layout(rect2.left, convertDipToPx - rect2.height(), rect2.right, convertDipToPx);
        }
        if (getStandaredPage(this.mCurPageIdx) < this.mAdapter.getCount() - 2) {
            PageView pageView3 = (PageView) createPageView(this.mCurPageIdx + 2, true);
            PageView pageView4 = (PageView) createPageView(this.mCurPageIdx + 2, false);
            if (!(pageView3 == null || pageView4 == null)) {
                if (pageView4.getMeasuredHeight() > pageView3.getMeasuredHeight()) {
                    i2 = pageView4.getMeasuredHeight() / 2;
                    i = pageView3.getMeasuredHeight() / 2;
                } else {
                    i2 = pageView3.getMeasuredHeight() / 2;
                    i = pageView4.getMeasuredHeight() / 2;
                }
                i6 = i2 - i;
            }
            int convertDipToPx2 = rect.height() > rect2.height() ? rect.bottom : i6 + rect2.bottom + ((int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap()));
            pageView4.layout(rect.left, convertDipToPx2, rect.right, rect.height() + convertDipToPx2);
            if (pageView3 != null) {
                pageView3.layout(rect2.left, convertDipToPx2, rect2.right, rect2.height() + convertDipToPx2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, Rect rect, View view2, Rect rect2, Point point) {
        int i;
        if (view == null) {
            i = view2.getWidth();
        } else if (view2 == null) {
            i = view.getWidth();
        } else {
            i = view2.getRight() - view.getLeft();
        }
        if (!this.mUserInteracting && this.mScroller.isFinished()) {
            Point correctionPt = getCorrectionPt(getScrollBounds(rect, rect2));
            rect.right += correctionPt.x;
            rect.left += correctionPt.x;
            rect.top += correctionPt.y;
            rect.bottom += correctionPt.y;
            rect2.right += correctionPt.x;
            rect2.left += correctionPt.x;
            rect2.top += correctionPt.y;
            rect2.bottom += correctionPt.y;
        } else if (i <= getWidth() || (this.mIsMinScale && PropertyManager.isKeepMinimumZoomLevel())) {
            Point correctionPt2 = getCorrectionPt(getScrollBounds(rect, rect2));
            rect.left += correctionPt2.x;
            rect.right += correctionPt2.x;
            rect2.left += correctionPt2.x;
            rect2.right += correctionPt2.x;
        }
        if (view != null) {
            view.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
        if (view2 != null) {
            view2.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        }
        nearPageLayout(point, rect, rect2);
    }

    /* access modifiers changed from: protected */
    public int getCurrentPageIndexByScrollPosition() {
        return this.mCurPageIdx;
    }
}
