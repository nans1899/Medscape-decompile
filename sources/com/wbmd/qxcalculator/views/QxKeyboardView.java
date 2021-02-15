package com.wbmd.qxcalculator.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.util.AttributeSet;
import com.wbmd.qxcalculator.R;
import java.util.List;

public class QxKeyboardView extends KeyboardView {
    private Bitmap mBuffer;
    private Canvas mCanvas;
    private Rect mClipRegion = new Rect(0, 0, 0, 0);
    private Rect mDirtyRect = new Rect();
    private boolean mDrawPending;
    private Keyboard.Key mInvalidatedKey;
    private Drawable mKeyBackground;
    private Drawable mKeyDeleteBackground;
    private Drawable mKeySaveBackground;
    private int mKeyTextColor;
    private int mKeyTextSize;
    private Keyboard mKeyboard;
    private boolean mKeyboardChanged;
    private Keyboard.Key[] mKeys;
    private int mLabelTextSize;
    private Rect mPadding = new Rect(0, 0, 0, 0);
    private Paint mPaint;
    private int mShadowColor;
    private float mShadowRadius;

    public QxKeyboardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initUi(context, attributeSet);
    }

    private void initUi(Context context, AttributeSet attributeSet) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setAlpha(255);
        this.mPadding = new Rect(0, 0, 0, 0);
        this.mKeyBackground = context.getResources().getDrawable(R.drawable.selector_keyboard_key_bkg);
        this.mKeyDeleteBackground = context.getResources().getDrawable(R.drawable.selector_keyboard_delete_bkg);
        this.mKeySaveBackground = context.getResources().getDrawable(R.drawable.selector_keyboard_key_save_bkg);
        this.mKeyTextSize = context.getResources().getDimensionPixelSize(R.dimen.keyboard_text_size);
        this.mLabelTextSize = 24;
        this.mKeyTextColor = context.getResources().getColor(R.color.keyboard_text_color);
        this.mShadowRadius = 0.0f;
        this.mShadowColor = 0;
    }

    public void setKeyboard(Keyboard keyboard) {
        super.setKeyboard(keyboard);
        this.mKeyboard = keyboard;
        List<Keyboard.Key> keys = keyboard.getKeys();
        this.mKeys = (Keyboard.Key[]) keys.toArray(new Keyboard.Key[keys.size()]);
        requestLayout();
        this.mKeyboardChanged = true;
        invalidateAllKeys();
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mBuffer = null;
    }

    public void closing() {
        super.closing();
        this.mBuffer = null;
        this.mCanvas = null;
    }

    public void invalidateAllKeys() {
        super.invalidateAllKeys();
        this.mDirtyRect.union(0, 0, getWidth(), getHeight());
        this.mDrawPending = true;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        if (this.mDrawPending || this.mBuffer == null || this.mKeyboardChanged) {
            onBufferDraw();
        }
        canvas.drawBitmap(this.mBuffer, 0.0f, 0.0f, (Paint) null);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r3v22 */
    private void onBufferDraw() {
        boolean z;
        Drawable drawable;
        ? r3 = 0;
        if (this.mBuffer == null || this.mKeyboardChanged) {
            Bitmap bitmap = this.mBuffer;
            if (bitmap == null || (this.mKeyboardChanged && !(bitmap.getWidth() == getWidth() && this.mBuffer.getHeight() == getHeight()))) {
                this.mBuffer = Bitmap.createBitmap(Math.max(1, getWidth()), Math.max(1, getHeight()), Bitmap.Config.ARGB_8888);
                this.mCanvas = new Canvas(this.mBuffer);
            }
            invalidateAllKeys();
            this.mKeyboardChanged = false;
        }
        Canvas canvas = this.mCanvas;
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.save();
            canvas.clipRect(this.mDirtyRect);
        } else {
            canvas.clipRect(this.mDirtyRect, Region.Op.REPLACE);
        }
        Paint paint = this.mPaint;
        Rect rect = this.mClipRegion;
        Rect rect2 = this.mPadding;
        Keyboard.Key[] keyArr = this.mKeys;
        Keyboard.Key key = this.mInvalidatedKey;
        paint.setColor(this.mKeyTextColor);
        boolean z2 = key != null && canvas.getClipBounds(rect) && (key.x + 0) - 1 <= rect.left && (key.y + 0) - 1 <= rect.top && ((key.x + key.width) + 0) + 1 >= rect.right && ((key.y + key.height) + 0) + 1 >= rect.bottom;
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        int length = keyArr.length;
        int i = 0;
        while (true) {
            String str = null;
            if (i >= length) {
                break;
            }
            Keyboard.Key key2 = keyArr[i];
            if (!z2 || key == key2) {
                int[] currentDrawableState = key2.getCurrentDrawableState();
                if (key2.codes.length <= 0 || key2.codes[r3] != -4) {
                    drawable = this.mKeyBackground;
                } else {
                    drawable = this.mKeySaveBackground;
                }
                drawable.setState(currentDrawableState);
                if (key2.label != null) {
                    str = key2.label.toString();
                }
                Rect bounds = drawable.getBounds();
                if (!(key2.width == bounds.right && key2.height == bounds.bottom)) {
                    drawable.setBounds(r3, r3, key2.width, key2.height);
                }
                canvas.translate((float) (key2.x + r3), (float) (key2.y + r3));
                drawable.draw(canvas);
                if (str != null) {
                    if (str.length() <= 1 || key2.codes.length >= 2) {
                        paint.setTextSize((float) this.mKeyTextSize);
                        paint.setTypeface(Typeface.DEFAULT);
                    } else {
                        paint.setTextSize((float) this.mLabelTextSize);
                        paint.setTypeface(Typeface.DEFAULT_BOLD);
                    }
                    paint.setShadowLayer(this.mShadowRadius, 0.0f, 0.0f, this.mShadowColor);
                    canvas.drawText(str, (float) ((((key2.width - rect2.left) - rect2.right) / 2) + rect2.left), ((float) (((key2.height - rect2.top) - rect2.bottom) / 2)) + ((paint.getTextSize() - paint.descent()) / 2.0f) + ((float) rect2.top), paint);
                    paint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                } else if (key2.icon != null) {
                    int intrinsicWidth = ((((key2.width - rect2.left) - rect2.right) - key2.icon.getIntrinsicWidth()) / 2) + rect2.left;
                    int intrinsicHeight = ((((key2.height - rect2.top) - rect2.bottom) - key2.icon.getIntrinsicHeight()) / 2) + rect2.top;
                    canvas.translate((float) intrinsicWidth, (float) intrinsicHeight);
                    key2.icon.setBounds(0, 0, key2.icon.getIntrinsicWidth(), key2.icon.getIntrinsicHeight());
                    key2.icon.draw(canvas);
                    canvas.translate((float) (-intrinsicWidth), (float) (-intrinsicHeight));
                }
                z = false;
                canvas.translate((float) ((-key2.x) - 0), (float) ((-key2.y) - 0));
            } else {
                z = r3;
            }
            i++;
            r3 = z;
        }
        this.mInvalidatedKey = null;
        this.mDrawPending = r3;
        this.mDirtyRect.setEmpty();
        if (Build.VERSION.SDK_INT >= 26) {
            canvas.restore();
        }
    }

    public void invalidateKey(int i) {
        Keyboard.Key[] keyArr = this.mKeys;
        if (keyArr != null && i >= 0 && i < keyArr.length) {
            Keyboard.Key key = keyArr[i];
            this.mInvalidatedKey = key;
            this.mDirtyRect.union(key.x, key.y, key.x + key.width, key.y + key.height);
            onBufferDraw();
            invalidate(key.x, key.y, key.x + key.width, key.y + key.height);
        }
    }
}
