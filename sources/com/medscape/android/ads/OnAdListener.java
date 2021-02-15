package com.medscape.android.ads;

public interface OnAdListener {
    String getCurrentPvid();

    void isAdExpandedByUser(boolean z);

    void onAdAvailable();

    void onAdNotAvilable();
}
