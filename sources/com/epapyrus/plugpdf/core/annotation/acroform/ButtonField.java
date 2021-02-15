package com.epapyrus.plugpdf.core.annotation.acroform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import com.epapyrus.plugpdf.core.PDFDocument;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class ButtonField extends BaseField {
    BitmapDrawable mBackgroundImage = null;
    private Bitmap mBitmap;
    private ImageButton mButton;

    public void addSibling(BaseField baseField) {
    }

    public void beforeFlatten() {
    }

    public String getValue() {
        return null;
    }

    public void setValue(String str) {
    }

    public ButtonField(Context context, PDFDocument pDFDocument, FieldProperty fieldProperty) {
        super(context, pDFDocument, "BUTTON", fieldProperty);
        this.mTouchFrame = new RectF();
        ImageButton imageButton = new ImageButton(context);
        this.mButton = imageButton;
        imageButton.setOnTouchListener(this);
        addView(this.mButton);
        Bitmap drawAnnotAP = this.mDoc.drawAnnotAP(this.mProperty.getPageIdx(), this.mProperty.getObjID(), 0, 5.0d);
        this.mBitmap = drawAnnotAP;
        if (drawAnnotAP != null) {
            this.mButton.setBackgroundColor(0);
            this.mBackgroundImage = new BitmapDrawable(getContext().getResources(), this.mBitmap);
        }
        setBackgroundColor(0);
        this.mButton.setBackgroundColor(0);
        setFieldState(this.mFieldState);
    }

    public View getNativeWidget() {
        return this.mButton;
    }

    /* access modifiers changed from: protected */
    public boolean onTapUp(MotionEvent motionEvent) {
        if (this.mListener != null && this.mListener.onClick(this)) {
            return true;
        }
        runBasicAction();
        return super.onTouchEvent(motionEvent);
    }

    public void setScale(float f) {
        super.setScale(f);
        this.mTouchFrame.set(this.mRect.left * this.mScale, this.mRect.top * this.mScale, this.mRect.right * this.mScale, this.mRect.bottom * this.mScale);
        this.mTouchFrame.inset(-5.0f, -5.0f);
        layout((int) this.mTouchFrame.left, (int) this.mTouchFrame.top, (int) this.mTouchFrame.right, (int) this.mTouchFrame.bottom);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.mButton.layout(0, 0, getWidth(), getHeight());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        BitmapDrawable bitmapDrawable = this.mBackgroundImage;
        if (bitmapDrawable != null) {
            bitmapDrawable.setBounds(0, 0, getWidth(), getHeight());
            this.mBackgroundImage.draw(canvas);
        }
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            clear();
        } else if (!bitmap.isRecycled()) {
            Bitmap bitmap2 = this.mBitmap;
            if (!(bitmap2 == null || bitmap == bitmap2)) {
                bitmap2.recycle();
            }
            this.mBitmap = bitmap;
            this.mButton.setBackgroundColor(0);
            this.mBackgroundImage = new BitmapDrawable(getContext().getResources(), this.mBitmap);
            invalidate();
            Log.i("PlugPDF", "Bitmap size bitmap width :" + this.mBitmap.getWidth() + "bitmap hegiht :" + this.mBitmap.getHeight());
            Log.i("PlugPDF", "Bitmap size Field width :" + this.mBitmap.getWidth() + "Field hegiht :" + this.mBitmap.getHeight());
            Rect rect = new Rect(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
            Bitmap createBitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            createBitmap.eraseColor(0);
            new Canvas(createBitmap).drawBitmap(this.mBitmap, rect, rect, (Paint) null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            this.mDoc.insertImageToWidget(this.mProperty.getPageIdx(), this.mProperty.getObjID(), createBitmap.getWidth(), createBitmap.getHeight(), byteArrayOutputStream.toByteArray());
            createBitmap.recycle();
        } else {
            throw new RuntimeException("Sign Bitmap is recycled");
        }
    }

    public void setAlphaBitmap(Bitmap bitmap, boolean z) {
        Bitmap bitmap2 = this.mBitmap;
        if (!(bitmap2 == null || bitmap == bitmap2)) {
            bitmap2.recycle();
        }
        this.mBitmap = bitmap;
        this.mButton.setBackgroundColor(0);
        this.mBackgroundImage = new BitmapDrawable(getContext().getResources(), this.mBitmap);
        invalidate();
        int width = this.mBitmap.getWidth();
        int height = this.mBitmap.getHeight();
        Rect rect = new Rect(0, 0, width, height);
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(this.mBitmap, rect, rect, (Paint) null);
        int i = width * height;
        byte[] bArr = new byte[(i * 4)];
        createBitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
        if (z) {
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = i2 * 4;
                bArr[i3 + 0] = 0;
                bArr[i3 + 1] = 0;
                bArr[i3 + 2] = 0;
            }
        }
        this.mDoc.insertAlphaImageToWidget(this.mProperty.getPageIdx(), this.mProperty.getObjID(), width, height, 4, bArr);
        createBitmap.recycle();
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void clear() {
        this.mDoc.removeAnnotAp(this.mProperty.getPageIdx(), this.mProperty.getObjID(), 0);
        this.mBackgroundImage = null;
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mBitmap = null;
        invalidate();
    }

    public void redrawAP() {
        Bitmap drawAnnotAP = this.mDoc.drawAnnotAP(getPageIdx(), getObjID(), 0, (double) this.mScale);
        this.mBitmap = drawAnnotAP;
        if (drawAnnotAP != null) {
            this.mButton.setBackgroundColor(0);
            this.mBackgroundImage = new BitmapDrawable(getContext().getResources(), this.mBitmap);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBitmap.recycle();
        }
    }
}
