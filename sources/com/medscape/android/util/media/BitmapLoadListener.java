package com.medscape.android.util.media;

import android.graphics.Bitmap;
import android.view.View;

public interface BitmapLoadListener {
    void onLoadComplete(View view, Bitmap bitmap);

    void onLoadStarted();
}
