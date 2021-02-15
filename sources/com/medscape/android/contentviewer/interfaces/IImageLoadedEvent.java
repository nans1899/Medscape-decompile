package com.medscape.android.contentviewer.interfaces;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface IImageLoadedEvent {
    void loadImage(String str, ImageView imageView);

    void onImageLoaded(ImageView imageView, Bitmap bitmap);
}
