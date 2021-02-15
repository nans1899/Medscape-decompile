package com.google.firebase.inappmessaging.model;

import android.graphics.Bitmap;
import android.text.TextUtils;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ImageData {
    private final Bitmap bitmapData;
    private final String imageUrl;

    public int hashCode() {
        Bitmap bitmap = this.bitmapData;
        return this.imageUrl.hashCode() + (bitmap != null ? bitmap.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageData)) {
            return false;
        }
        ImageData imageData = (ImageData) obj;
        return hashCode() == imageData.hashCode() && this.imageUrl.equals(imageData.imageUrl);
    }

    public ImageData(String str, Bitmap bitmap) {
        this.imageUrl = str;
        this.bitmapData = bitmap;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Bitmap getBitmapData() {
        return this.bitmapData;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static class Builder {
        private Bitmap bitmapData;
        private String imageUrl;

        public Builder setImageUrl(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.imageUrl = str;
            }
            return this;
        }

        public Builder setBitmapData(Bitmap bitmap) {
            this.bitmapData = bitmap;
            return this;
        }

        public ImageData build() {
            if (!TextUtils.isEmpty(this.imageUrl)) {
                return new ImageData(this.imageUrl, this.bitmapData);
            }
            throw new IllegalArgumentException("ImageData model must have an imageUrl");
        }
    }
}
