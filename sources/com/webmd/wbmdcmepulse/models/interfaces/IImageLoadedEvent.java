package com.webmd.wbmdcmepulse.models.interfaces;

import android.graphics.Bitmap;
import android.widget.ImageView;

public interface IImageLoadedEvent {
    void loadImage(String str, ImageView imageView);

    void loadImage(String str, ImageView imageView, int i, int i2);

    void onImageLoaded(ImageView imageView, Bitmap bitmap);
}
