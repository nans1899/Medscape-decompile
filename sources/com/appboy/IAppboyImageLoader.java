package com.appboy;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.appboy.enums.AppboyViewBounds;

public interface IAppboyImageLoader {
    Bitmap getBitmapFromUrl(Context context, String str, AppboyViewBounds appboyViewBounds);

    void renderUrlIntoView(Context context, String str, ImageView imageView, AppboyViewBounds appboyViewBounds);

    void setOffline(boolean z);
}
