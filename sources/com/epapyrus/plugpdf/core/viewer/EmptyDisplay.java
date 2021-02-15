package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: ReaderView */
class EmptyDisplay extends BasePlugPDFDisplay {
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

    public EmptyDisplay(Context context) {
        super(context, BasePlugPDFDisplay.PageDisplayMode.HORIZONTAL);
    }

    public void goToPage(int i) {
        this.mCurPageIdx = i;
    }
}
