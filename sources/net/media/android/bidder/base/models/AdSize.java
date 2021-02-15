package net.media.android.bidder.base.models;

import androidx.recyclerview.widget.ItemTouchHelper;
import mnetinternal.c;
import org.jetbrains.anko.DimensionsKt;

public class AdSize {
    public static final AdSize BANNER = new AdSize(DimensionsKt.XHDPI, 50);
    public static final AdSize MEDIUM = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    @c(a = "h")
    private int mHeight;
    @c(a = "w")
    private int mWidth;

    public AdSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        if (this.mWidth == adSize.getWidth() && this.mHeight == adSize.getHeight()) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "AdSize{ width = " + this.mWidth + ", height = " + this.mHeight + " }";
    }
}
