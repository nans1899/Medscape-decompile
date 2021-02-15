package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class BilateralHorizontalDisplay extends BilateralPageDisplay implements Runnable {
    public BilateralHorizontalDisplay(Context context, boolean z) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.BILATERAL_HORIZONTAL, z);
    }

    /* access modifiers changed from: protected */
    public boolean fling(Rect rect, float f, float f2) {
        BasePlugPDFDisplay.Direction direction = getDirection(f, f2);
        if (direction != BasePlugPDFDisplay.Direction.LEFT || rect.left < 0) {
            if (direction != BasePlugPDFDisplay.Direction.RIGHT || rect.right > 0 || ((View) this.mPageViews.get(getStandaredPage(this.mCurPageIdx) - 1)) == null) {
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
        if (getStandaredPage(this.mCurPageIdx) > 0) {
            PageView pageView = (PageView) createPageView(this.mCurPageIdx - 2, true);
            PageView pageView2 = (PageView) createPageView(this.mCurPageIdx - 2, false);
            int convertDipToPx = rect.left - (((int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap())) - (-(getSubScreenSizeOffset((View) this.mPageViews.get(getStandaredPage(this.mCurPageIdx))).x + getSubScreenSizeOffset(pageView).x)));
            pageView.layout(convertDipToPx - (pageView.getMeasuredWidth() / 2), (rect.top + (rect.height() / 2)) - ((pageView.getMeasuredHeight() / 2) / 2), convertDipToPx, rect.top + (rect.height() / 2) + ((pageView.getMeasuredHeight() / 2) / 2));
            if (pageView2 != null) {
                int measuredWidth = (int) (((double) convertDipToPx) - (((double) (pageView.getMeasuredWidth() / 2)) + (((double) getOffset()) * getScale())));
                pageView2.layout(measuredWidth - (pageView2.getMeasuredWidth() / 2), (rect.top + (rect.height() / 2)) - ((pageView2.getMeasuredHeight() / 2) / 2), measuredWidth, rect.top + (rect.height() / 2) + ((pageView2.getMeasuredHeight() / 2) / 2));
            }
        }
        if (getStandaredPage(this.mCurPageIdx) < this.mAdapter.getCount() - 2) {
            PageView pageView3 = (PageView) createPageView(this.mCurPageIdx + 2, true);
            PageView pageView4 = (PageView) createPageView(this.mCurPageIdx + 2, false);
            int convertDipToPx2 = rect2.right + ((int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap())) + getSubScreenSizeOffset((View) this.mPageViews.get(getStandaredPage(this.mCurPageIdx) + 1)).x + getSubScreenSizeOffset(pageView4).x;
            pageView4.layout(convertDipToPx2, (rect.top + (rect.height() / 2)) - ((pageView4.getMeasuredHeight() / 2) / 2), (pageView4.getMeasuredWidth() / 2) + convertDipToPx2, rect.top + (rect.height() / 2) + ((pageView4.getMeasuredHeight() / 2) / 2));
            if (pageView3 != null) {
                int measuredWidth2 = (int) (((double) convertDipToPx2) + ((double) (pageView3.getMeasuredWidth() / 2)) + (((double) getOffset()) * getScale()));
                pageView3.layout(measuredWidth2, (rect.top + (rect.height() / 2)) - ((pageView3.getMeasuredHeight() / 2) / 2), (pageView3.getMeasuredWidth() / 2) + measuredWidth2, rect.top + (rect.height() / 2) + ((pageView3.getMeasuredHeight() / 2) / 2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, Rect rect, View view2, Rect rect2, Point point) {
        int i;
        if (view == null) {
            i = view2.getHeight();
        } else if (view2 == null) {
            i = view.getHeight();
        } else {
            i = leftIsGreater(view, view2) ? view.getHeight() : view2.getHeight();
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
        } else if (i <= getHeight() || (this.mIsMinScale && PropertyManager.isKeepMinimumZoomLevel())) {
            Point correctionPt2 = getCorrectionPt(getScrollBounds(rect, rect2));
            rect.top += correctionPt2.y;
            rect.bottom += correctionPt2.y;
            rect2.top += correctionPt2.y;
            rect2.bottom += correctionPt2.y;
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
