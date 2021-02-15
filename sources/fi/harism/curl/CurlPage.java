package fi.harism.curl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class CurlPage {
    public static final int SIDE_BACK = 2;
    public static final int SIDE_BOTH = 3;
    public static final int SIDE_FRONT = 1;
    private int mColorBack;
    private int mColorFront;
    private Bitmap mTextureBack;
    private Bitmap mTextureFront;
    private boolean mTexturesChanged;

    private int getNextHighestPO2(int i) {
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        int i7 = i6 | (i6 >> 16);
        return (i7 | (i7 >> 32)) + 1;
    }

    public CurlPage() {
        reset();
    }

    public int getColor(int i) {
        if (i != 1) {
            return this.mColorBack;
        }
        return this.mColorFront;
    }

    private Bitmap getTexture(Bitmap bitmap, RectF rectF) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int nextHighestPO2 = getNextHighestPO2(width);
        int nextHighestPO22 = getNextHighestPO2(height);
        Bitmap createBitmap = Bitmap.createBitmap(nextHighestPO2, nextHighestPO22, bitmap.getConfig());
        new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        rectF.set(0.0f, 0.0f, ((float) width) / ((float) nextHighestPO2), ((float) height) / ((float) nextHighestPO22));
        return createBitmap;
    }

    public Bitmap getTexture(RectF rectF, int i) {
        if (i != 1) {
            return getTexture(this.mTextureBack, rectF);
        }
        return getTexture(this.mTextureFront, rectF);
    }

    public boolean getTexturesChanged() {
        return this.mTexturesChanged;
    }

    public boolean hasBackTexture() {
        return !this.mTextureFront.equals(this.mTextureBack);
    }

    public void recycle() {
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
        this.mTextureFront = createBitmap;
        createBitmap.eraseColor(this.mColorFront);
        Bitmap createBitmap2 = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
        this.mTextureBack = createBitmap2;
        createBitmap2.eraseColor(this.mColorBack);
        this.mTexturesChanged = false;
    }

    public void reset() {
        this.mColorBack = -1;
        this.mColorFront = -1;
        recycle();
    }

    public void setColor(int i, int i2) {
        if (i2 == 1) {
            this.mColorFront = i;
        } else if (i2 != 2) {
            this.mColorBack = i;
            this.mColorFront = i;
        } else {
            this.mColorBack = i;
        }
    }

    public void setTexture(Bitmap bitmap, int i) {
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
            if (i == 2) {
                bitmap.eraseColor(this.mColorBack);
            } else {
                bitmap.eraseColor(this.mColorFront);
            }
        }
        if (i == 1) {
            this.mTextureFront = bitmap;
        } else if (i == 2) {
            this.mTextureBack = bitmap;
        } else if (i == 3) {
            this.mTextureBack = bitmap;
            this.mTextureFront = bitmap;
        }
        this.mTexturesChanged = true;
    }
}
