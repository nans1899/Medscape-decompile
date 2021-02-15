package com.google.android.gms.ads.formats;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class NativeAd {
    public static final String ASSET_ADCHOICES_CONTAINER_VIEW = "1098";

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static abstract class AdChoicesInfo {
        public abstract List<Image> getImages();

        public abstract CharSequence getText();
    }

    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static abstract class Image {
        public abstract Drawable getDrawable();

        public int getHeight() {
            return -1;
        }

        public abstract double getScale();

        public abstract Uri getUri();

        public int getWidth() {
            return -1;
        }
    }

    @Deprecated
    public abstract void performClick(Bundle bundle);

    @Deprecated
    public abstract boolean recordImpression(Bundle bundle);

    @Deprecated
    public abstract void reportTouchEvent(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract Object zzjs();
}
