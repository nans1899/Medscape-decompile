package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.PropertyManager;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;

public class ContinuosDisplay extends SinglePageDisplay implements Runnable {
    public ContinuosDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.CONTINUOS);
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
            pageView2.layout(i, i4 + convertDipToPx2, i3, pageView2.getMeasuredHeight() + i4 + convertDipToPx2);
        }
        if (this.mCurPageIdx > 1) {
            PageView pageView3 = (PageView) createPageView(this.mCurPageIdx - 2);
            int convertDipToPx3 = (int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap());
            pageView3.layout(i, (i2 - (pageView3.getMeasuredHeight() * 2)) - (convertDipToPx3 * 2), i3, (i2 - pageView3.getMeasuredHeight()) - convertDipToPx3);
        }
        if (this.mCurPageIdx + 2 < this.mAdapter.getCount()) {
            PageView pageView4 = (PageView) createPageView(this.mCurPageIdx + 2);
            int convertDipToPx4 = ((int) PlugPDFUtility.convertDipToPx(getContext(), (float) PropertyManager.getPageGap())) * 2;
            pageView4.layout(i, pageView4.getMeasuredHeight() + i4 + convertDipToPx4, i3, i4 + (pageView4.getMeasuredHeight() * 2) + convertDipToPx4);
        }
    }

    /* access modifiers changed from: protected */
    public void pageLayout(View view, int i, int i2, int i3, int i4, Point point) {
        Point correctionPt = getCorrectionPt(getScrollBounds(i, i2, i3, i4));
        int i5 = i + correctionPt.x;
        int i6 = i3 + correctionPt.x;
        view.layout(i5, i2, i6, i4);
        nearPageLayout(point, i5, i2, i6, i4);
    }

    /* access modifiers changed from: protected */
    public int getCurrentPageIndexByScrollPosition() {
        for (int i = this.mCurPageIdx - 3; i < this.mCurPageIdx + 3; i++) {
            View view = (View) this.mPageViews.get(i);
            if (view != null) {
                Rect rect = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                Point point = new Point(getWidth() / 2, getHeight() / 2);
                if (rect.contains(point.x, point.y)) {
                    return i;
                }
            }
        }
        return this.mCurPageIdx;
    }

    /* access modifiers changed from: protected */
    public Rect getScrollBounds(View view) {
        int top = view.getTop();
        int bottom = view.getBottom();
        View view2 = (View) this.mPageViews.get(this.mCurPageIdx - 1);
        if (view2 != null) {
            top = view2.getTop();
        }
        View view3 = (View) this.mPageViews.get(this.mCurPageIdx + 1);
        if (view3 != null) {
            bottom = view3.getBottom();
        }
        return getScrollBounds(view.getLeft() + this.mScrollPosX, top + this.mScrollPosY, view.getLeft() + view.getMeasuredWidth() + this.mScrollPosX, bottom + this.mScrollPosY);
    }
}
