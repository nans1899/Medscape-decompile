package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.graphics.Point;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.epapyrus.plugpdf.core.PlugPDFUtility;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.jetbrains.anko.DimensionsKt;

public class ThumbnailDisplay extends BasePlugPDFDisplay {
    private Point mChildViewSize = new Point(DimensionsKt.HDPI, 410);
    private GridView mGridView;
    private boolean mInitGridView = false;

    public double calculatePageViewScaleInReaderView() {
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public void changeScale(double d, int i, int i2) {
    }

    public SparseArray<PageView> getChildViewList() {
        return null;
    }

    public PageView getPageView() {
        return null;
    }

    public PageView getPageView(int i) {
        return null;
    }

    /* access modifiers changed from: protected */
    public double getPageViewScale(int i) {
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public void refreshLayout() {
    }

    public void scrollToView(int i, View view) {
    }

    public void setScrollPosX(int i) {
    }

    public void setScrollPosY(int i) {
    }

    public void setupPageViews() {
    }

    public ThumbnailDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.THUMBNAIL);
    }

    private void measureGridView() {
        ((ThumbnailPageAdapter) this.mAdapter).setParentSize(this.mChildViewSize.x, this.mChildViewSize.y);
        this.mGridView.measure(getWidth() | 1073741824, 1073741824 | getHeight());
    }

    private void initGridView() {
        int convertDipToPx = (int) PlugPDFUtility.convertDipToPx(getContext(), 10.0f);
        int convertDipToPx2 = (int) PlugPDFUtility.convertDipToPx(getContext(), 10.0f);
        GridView gridView = new GridView(getContext());
        this.mGridView = gridView;
        gridView.setGravity(17);
        this.mGridView.setPadding(convertDipToPx, convertDipToPx, convertDipToPx, convertDipToPx);
        this.mGridView.setHorizontalSpacing(convertDipToPx2);
        this.mGridView.setVerticalSpacing(convertDipToPx2);
        this.mGridView.setStretchMode(2);
        ViewGroup.LayoutParams layoutParams = this.mGridView.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -1);
        }
        addViewInLayout(this.mGridView, 0, layoutParams, false);
        this.mGridView.setAdapter((ThumbnailPageAdapter) this.mAdapter);
        this.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ThumbnailDisplay.this.mCurPageIdx = i;
                ThumbnailDisplay.this.mDisplayListener.onPageSelect(i);
            }
        });
        this.mInitGridView = true;
    }

    public void goToPage(int i) {
        if (i >= 0 && i < this.mAdapter.getCount() && this.mCurPageIdx != i) {
            this.mCurPageIdx = i;
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mAdapter != null) {
            if (!this.mInitGridView) {
                initGridView();
            }
            this.mGridView.setNumColumns(getWidth() / this.mChildViewSize.x);
            measureGridView();
            this.mGridView.layout(i, i2 + ((int) PlugPDFUtility.convertDipToPx(getContext(), 50.0f)), i3, i4);
        }
    }
}
