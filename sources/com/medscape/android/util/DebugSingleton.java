package com.medscape.android.util;

public class DebugSingleton {
    static DebugSingleton mInstance;
    public boolean isForceLongSectionName = false;
    public boolean isShowWebViewError = false;

    public static DebugSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new DebugSingleton();
        }
        return mInstance;
    }
}
