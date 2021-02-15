package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class WifiNode {
    @c(a = "capabilities")
    private String mCapabilities;
    @c(a = "signal_level")
    private int mSignalLevel;
    @c(a = "ssid")
    private String mSsid;

    public WifiNode(String str, String str2, int i) {
        this.mSsid = str;
        this.mCapabilities = str2;
        this.mSignalLevel = i;
    }
}
