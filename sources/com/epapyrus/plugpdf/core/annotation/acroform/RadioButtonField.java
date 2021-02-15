package com.epapyrus.plugpdf.core.annotation.acroform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.epapyrus.plugpdf.core.PDFDocument;

public class RadioButtonField extends CheckBoxField {
    protected static final String OFF = "Off";
    protected static final String ON = "On";

    public String getType() {
        return "RADIO_BUTTON";
    }

    public RadioButtonField(Context context, PDFDocument pDFDocument, FieldProperty fieldProperty) {
        super(context, pDFDocument, fieldProperty);
        setFieldState(this.mFieldState);
    }

    public String getUID() {
        return this.mProperty.getValue("AP_N");
    }

    public int getObjID() {
        return this.mProperty.getObjID();
    }

    public String getGroupID() {
        return this.mProperty.getValue("Group");
    }

    /* access modifiers changed from: protected */
    public boolean onTapUp(MotionEvent motionEvent) {
        if (this.mListener != null && this.mListener.onClick(this)) {
            return true;
        }
        if (this.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED).equals(getUID())) {
            setValue("");
        } else {
            setValue(getUID());
        }
        resetSibling();
        runBasicAction();
        return super.onTouchEvent(motionEvent);
    }

    public void setValue(String str) {
        boolean z = false;
        if (str == null || str.isEmpty()) {
            this.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "");
            setChecked(false);
        }
        if (str.equals(OFF) || str.equals("")) {
            this.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "");
            setChecked(false);
        } else if (str.equals(ON) || str.equals(getUID())) {
            this.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, getUID());
            setChecked(true);
        }
        for (BaseField baseField : this.mSiblings) {
            if (baseField instanceof RadioButtonField) {
                RadioButtonField radioButtonField = (RadioButtonField) baseField;
                if (str.equals(baseField.getUID())) {
                    radioButtonField.setChecked(true);
                    radioButtonField.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, getValue());
                    radioButtonField.invalidate();
                    this.mDoc.setFieldValue(radioButtonField.mProperty.getPageIdx(), radioButtonField.mProperty.getObjID(), radioButtonField.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED), this.mPainter != null);
                    setChecked(false);
                    this.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "");
                } else {
                    radioButtonField.setChecked(false);
                    radioButtonField.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "");
                    this.mDoc.setFieldValue(radioButtonField.mProperty.getPageIdx(), radioButtonField.mProperty.getObjID(), radioButtonField.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED), this.mPainter != null);
                }
            }
        }
        if (str.equals(getUID())) {
            setChecked(true);
            this.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, getUID());
        }
        PDFDocument pDFDocument = this.mDoc;
        int pageIdx = this.mProperty.getPageIdx();
        int objID = this.mProperty.getObjID();
        String value = this.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        if (this.mPainter != null) {
            z = true;
        }
        pDFDocument.setFieldValue(pageIdx, objID, value, z);
        invalidate();
        if (this.mListener != null) {
            this.mListener.onChangedValue(this);
        }
    }

    public String getValue() {
        if (isChecked()) {
            return getUID();
        }
        for (BaseField baseField : this.mSiblings) {
            RadioButtonField radioButtonField = (RadioButtonField) baseField;
            if (radioButtonField.isChecked()) {
                return radioButtonField.getUID();
            }
        }
        return "";
    }

    /* access modifiers changed from: protected */
    public void drawSelectFrame(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAntiAlias(true);
        if (isChecked()) {
            RectF rectF = new RectF(INSET, INSET, (this.mTouchFrame.width() - (INSET * 2.0f)) + 1.0f, (this.mTouchFrame.height() - (INSET * 2.0f)) + 1.0f);
            canvas.drawCircle((rectF.width() / 2.0f) + INSET, (rectF.height() / 2.0f) + INSET, rectF.width() / 2.0f, paint);
        }
    }

    /* access modifiers changed from: protected */
    public void resetSibling() {
        for (BaseField value : this.mSiblings) {
            value.setValue(getValue());
        }
    }
}
