package com.appboy.ui.feed;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageSwitcher;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;

public class AppboyImageSwitcher extends ImageSwitcher {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyImageSwitcher.class);
    private Drawable mReadIcon;
    private Drawable mUnReadIcon;

    public AppboyImageSwitcher(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Drawable drawable;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.com_appboy_ui_feed_AppboyImageSwitcher);
            for (int i = 0; i < obtainStyledAttributes.getIndexCount(); i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.com_appboy_ui_feed_AppboyImageSwitcher_appboyFeedCustomReadIcon) {
                    Drawable drawable2 = obtainStyledAttributes.getDrawable(index);
                    if (drawable2 != null) {
                        this.mReadIcon = drawable2;
                    }
                } else if (obtainStyledAttributes.getIndex(i) == R.styleable.com_appboy_ui_feed_AppboyImageSwitcher_appboyFeedCustomUnReadIcon && (drawable = obtainStyledAttributes.getDrawable(index)) != null) {
                    this.mUnReadIcon = drawable;
                }
            }
            obtainStyledAttributes.recycle();
        } catch (Exception e) {
            AppboyLogger.w(TAG, "Error while checking for custom drawable.", e);
        }
    }

    public Drawable getUnReadIcon() {
        return this.mUnReadIcon;
    }

    public Drawable getReadIcon() {
        return this.mReadIcon;
    }

    public void setUnReadIcon(Drawable drawable) {
        this.mUnReadIcon = drawable;
    }

    public void setReadIcon(Drawable drawable) {
        this.mReadIcon = drawable;
    }
}
