package com.epapyrus.plugpdf.core.annotation.acroform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.core.view.ViewCompat;
import com.epapyrus.plugpdf.core.PDFDocument;
import java.io.ByteArrayOutputStream;

public class CheckBoxField extends BaseField {
    protected static float INSET = 2.0f;
    protected static final String KEY = "V";
    protected static final String OFF = "0";
    protected static final String ON = "1";
    private static CustomCheckBoxPainter mGlobalPainter;
    private Button mButton;
    private boolean mChecked;
    protected CustomCheckBoxPainter mPainter;

    public CheckBoxField(Context context, PDFDocument pDFDocument, FieldProperty fieldProperty) {
        super(context, pDFDocument, "CHECK_BOX", fieldProperty);
        this.mPainter = null;
        this.mPainter = mGlobalPainter;
        this.mTouchFrame = new RectF();
        setValue(this.mProperty.getValue("V"));
        Button button = new Button(context);
        this.mButton = button;
        button.setOnTouchListener(this);
        addView(this.mButton);
        setFieldState(this.mFieldState);
    }

    public View getNativeWidget() {
        return this.mButton;
    }

    @Deprecated
    public void setCustomPainter(CustomCheckBoxPainter customCheckBoxPainter) {
        this.mPainter = customCheckBoxPainter;
    }

    public static void setGlobalCustomPainter(CustomCheckBoxPainter customCheckBoxPainter) {
        mGlobalPainter = customCheckBoxPainter;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        CustomCheckBoxPainter customCheckBoxPainter = this.mPainter;
        if (customCheckBoxPainter != null) {
            customCheckBoxPainter.draw(this, canvas);
        } else if (this.mDefaultDrawingIsUsed) {
            drawSelectFrame(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public void drawSelectFrame(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAntiAlias(true);
        if (isChecked()) {
            float f = INSET;
            canvas.drawRect(f, f, (this.mTouchFrame.width() - (INSET * 2.0f)) + 1.0f, (this.mTouchFrame.height() - (INSET * 2.0f)) + 1.0f, paint);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onTapUp(MotionEvent motionEvent) {
        if (this.mListener != null && this.mListener.onClick(this)) {
            return true;
        }
        setChecked(!isChecked());
        setValue(new String(isChecked() ? "1" : "0"));
        runBasicAction();
        return super.onTouchEvent(motionEvent);
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void setScale(float f) {
        super.setScale(f);
        this.mTouchFrame.set(this.mRect.left * this.mScale, this.mRect.top * this.mScale, this.mRect.right * this.mScale, this.mRect.bottom * this.mScale);
        RectF rectF = this.mTouchFrame;
        float f2 = INSET;
        rectF.inset(-f2, -f2);
        layout((int) this.mTouchFrame.left, (int) this.mTouchFrame.top, (int) this.mTouchFrame.right, (int) this.mTouchFrame.bottom);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        setBackgroundColor(0);
        this.mButton.layout(0, 0, getWidth(), getHeight());
        this.mButton.setBackgroundColor(0);
    }

    public void setValue(String str) {
        boolean z = true;
        if (str == null || str.isEmpty() || str.equals("0") || str.equals("Off")) {
            this.mProperty.setValue("V", "0");
            setChecked(false);
        } else {
            this.mProperty.setValue("V", "1");
            setChecked(true);
        }
        PDFDocument pDFDocument = this.mDoc;
        int pageIdx = this.mProperty.getPageIdx();
        int objID = this.mProperty.getObjID();
        String value = this.mProperty.getValue("V");
        if (this.mPainter == null) {
            z = false;
        }
        pDFDocument.setFieldValue(pageIdx, objID, value, z);
        invalidate();
        if (this.mListener != null) {
            this.mListener.onChangedValue(this);
        }
        resetSibling();
    }

    public String getValue() {
        return this.mProperty.getValue("V").equals("0") ? "0" : "1";
    }

    public void clear() {
        setValue("0");
    }

    /* access modifiers changed from: protected */
    public void resetSibling() {
        for (BaseField baseField : this.mSiblings) {
            if (!getValue().equals(baseField.getValue())) {
                baseField.setValue(getValue());
            }
        }
    }

    public void beforeFlatten() {
        Bitmap createBitmap = Bitmap.createBitmap((int) this.mRect.width(), (int) this.mRect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        CustomCheckBoxPainter customCheckBoxPainter = this.mPainter;
        if (customCheckBoxPainter != null) {
            Drawable draw = customCheckBoxPainter.draw(this, canvas);
            draw.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            draw.draw(canvas);
            if (draw != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                this.mDoc.insertImageToWidget(getPageIdx(), getObjID(), createBitmap.getWidth(), createBitmap.getHeight(), byteArrayOutputStream.toByteArray());
            }
        }
    }
}
