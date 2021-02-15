package com.wbmd.adlibrary.callbacks;

public interface IAdStateListener {
    void onAdFailedToLoad();

    void onAdLoaded();

    void onAdRefresh();
}
