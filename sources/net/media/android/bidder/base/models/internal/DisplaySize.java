package net.media.android.bidder.base.models.internal;

public final class DisplaySize {
    private int mHeight;
    private int mWidth;

    public DisplaySize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }
}
