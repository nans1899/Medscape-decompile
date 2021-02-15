package com.medscape.android.util.media;

import android.view.View;

public interface SampledBitmapLoadListener {
    void onLoadComplete(View view, SampledBitmap sampledBitmap);

    void onLoadStarted();
}
