package com.wbmd.adlibrary.callbacks;

import android.view.View;

public class SimpleAdStateListener implements IAdStateListener {
    private View mAdView;

    public void onAdRefresh() {
    }

    public SimpleAdStateListener(View view) {
        this.mAdView = view;
    }

    public void onAdLoaded() {
        View view = this.mAdView;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void onAdFailedToLoad() {
        View view = this.mAdView;
        if (view != null) {
            view.setVisibility(8);
        }
    }
}
