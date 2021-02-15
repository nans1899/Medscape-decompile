package com.epapyrus.plugpdf.core.annotation.acroform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.dd.plist.ASCIIPropertyListParser;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;

public class TextField extends BaseField implements TextView.OnEditorActionListener, View.OnFocusChangeListener, Runnable {
    private RectF mAvailableSpaceRect = new RectF();
    private String mDefaultValue = null;
    private float mDispDensity;
    /* access modifiers changed from: private */
    public EditText mEditText;
    /* access modifiers changed from: private */
    public float mFontSize;
    private boolean mIsMultiLine;
    private int mMaxTextSize = Integer.MAX_VALUE;
    private int mMinTextSize = 10;
    /* access modifiers changed from: private */
    public Paint mPaint = new TextPaint();
    private final SizeTester mSizeTester = new SizeTester() {
        public int onTestSize(int i, RectF rectF) {
            if (Build.VERSION.SDK_INT < 16) {
                return 0;
            }
            TextField.this.mPaint.setTextSize((float) i);
            String obj = TextField.this.mEditText.getText().toString();
            TextField.this.mTextRect.bottom = TextField.this.mPaint.getFontSpacing();
            TextField.this.mTextRect.right = TextField.this.mPaint.measureText(obj);
            TextField.this.mTextRect.offsetTo(0.0f, 0.0f);
            return rectF.contains(TextField.this.mTextRect) ? -1 : 1;
        }
    };
    /* access modifiers changed from: private */
    public RectF mTextRect = new RectF();
    private TextWatcher mTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextField.this.mProperty != null && editable != null) {
                String replace = editable.toString().replace(4510, '.').replace(4514, ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                if (!TextField.this.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED).equals(replace)) {
                    TextField.this.mProperty.setValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, replace);
                    TextField.this.mDoc.setFieldValue(TextField.this.mProperty.getPageIdx(), TextField.this.mProperty.getObjID(), replace);
                    if (TextField.this.mListener != null) {
                        TextField.this.mListener.onChangedValue(TextField.this);
                    }
                    if (TextField.this.mFontSize == 0.0f) {
                        TextField textField = TextField.this;
                        textField.adjustTextSize(textField.getValue());
                    }
                    for (BaseField baseField : TextField.this.mSiblings) {
                        if (!TextField.this.getValue().equals(baseField.getValue())) {
                            baseField.setValue(TextField.this.getValue());
                        }
                    }
                }
            }
        }
    };
    private int mWidthLimit;

    public enum InputType {
        DEFAULT,
        NUMBER,
        EMAIL,
        MULTI_LINE,
        ENGLISH,
        PASSWORD
    }

    private interface SizeTester {
        int onTestSize(int i, RectF rectF);
    }

    public void beforeFlatten() {
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

    public TextField(Context context, PDFDocument pDFDocument, FieldProperty fieldProperty) {
        super(context, pDFDocument, "TEXT_FIELD", fieldProperty);
        this.mTouchFrame = new RectF();
        EditText editText = new EditText(context);
        this.mEditText = editText;
        editText.setOnTouchListener(this);
        this.mEditText.setOnEditorActionListener(this);
        this.mEditText.setOnFocusChangeListener(this);
        this.mEditText.addTextChangedListener(this.mTextWatcher);
        this.mEditText.setText(this.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED));
        int parseInt = Integer.parseInt(this.mProperty.getValue("FontColor"));
        this.mEditText.setTextColor(Color.argb(255, (parseInt >> 16) & 255, (parseInt >> 8) & 255, parseInt & 255));
        this.mEditText.setBackgroundColor(0);
        this.mEditText.setPadding(0, 0, 0, 0);
        int parseInt2 = Integer.parseInt(this.mProperty.getValue("Q"));
        if (parseInt2 == 2) {
            this.mEditText.setGravity(85);
        } else if (parseInt2 != 4) {
            this.mEditText.setGravity(83);
        } else {
            this.mEditText.setGravity(81);
        }
        addView(this.mEditText);
        this.mFontSize = Float.parseFloat(this.mProperty.getValue("FontSize"));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.mDispDensity = (float) ((3.0d / ((double) displayMetrics.density)) * 0.35d);
        if (Boolean.parseBoolean(this.mProperty.getValue("isPassword"))) {
            setInputType(InputType.PASSWORD);
        } else {
            boolean parseBoolean = Boolean.parseBoolean(this.mProperty.getValue("isMultiLine"));
            this.mIsMultiLine = parseBoolean;
            if (parseBoolean) {
                setInputType(InputType.MULTI_LINE);
            } else {
                setInputType(InputType.DEFAULT);
            }
        }
        setBackgroundColor(0);
        String value = this.mProperty.getValue("DV");
        this.mDefaultValue = value;
        if (value != null && this.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED).length() == 0) {
            this.mEditText.setText(this.mDefaultValue);
        }
        setFieldState(this.mFieldState);
    }

    public View getNativeWidget() {
        return this.mEditText;
    }

    public void setContents(String str) {
        this.mEditText.setText(str);
    }

    public void setFieldState(BaseField.FieldState fieldState) {
        if (fieldState == BaseField.FieldState.ENABLE) {
            this.mEditText.setEnabled(true);
        } else {
            this.mEditText.setEnabled(false);
        }
        super.setFieldState(fieldState);
    }

    /* access modifiers changed from: protected */
    public int getBorderColor() {
        if (getFieldState() != BaseField.FieldState.ENABLE || !this.mEditText.hasFocus()) {
            return super.getBorderColor();
        }
        return Color.parseColor("#394b65");
    }

    /* renamed from: com.epapyrus.plugpdf.core.annotation.acroform.TextField$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType[] r0 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType = r0
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType r1 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType r1 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType r1 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.EMAIL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType r1 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.MULTI_LINE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType r1 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.ENGLISH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.epapyrus.plugpdf.core.annotation.acroform.TextField$InputType r1 = com.epapyrus.plugpdf.core.annotation.acroform.TextField.InputType.PASSWORD     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.annotation.acroform.TextField.AnonymousClass3.<clinit>():void");
        }
    }

    public void setInputType(InputType inputType) {
        switch (AnonymousClass3.$SwitchMap$com$epapyrus$plugpdf$core$annotation$acroform$TextField$InputType[inputType.ordinal()]) {
            case 1:
                setInputType(1);
                this.mEditText.setPrivateImeOptions("defaultInputmode=korea;");
                return;
            case 2:
                setInputType(2);
                return;
            case 3:
                setInputType(33);
                return;
            case 4:
                setInputType(131073);
                return;
            case 5:
                setInputType(1);
                this.mEditText.setPrivateImeOptions("defaultInputmode=english;");
                return;
            case 6:
                setInputType(129);
                this.mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                return;
            default:
                return;
        }
    }

    public void setInputType(int i) {
        this.mEditText.setInputType(i);
    }

    public void setImeOptions(int i) {
        this.mEditText.setImeOptions(i);
    }

    /* access modifiers changed from: protected */
    public float getBorderWidth() {
        if (getFieldState() != BaseField.FieldState.ENABLE || !this.mEditText.hasFocus()) {
            return super.getBorderWidth();
        }
        return 8.0f;
    }

    public void setScale(float f) {
        super.setScale(f);
        this.mTouchFrame.set(this.mRect.left * this.mScale, this.mRect.top * this.mScale, this.mRect.right * this.mScale, this.mRect.bottom * this.mScale);
        layout((int) this.mTouchFrame.left, (int) this.mTouchFrame.top, (int) this.mTouchFrame.right, (int) this.mTouchFrame.bottom);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f = this.mFontSize * this.mDispDensity * this.mScale;
        post(this);
        this.mEditText.layout(0, 0, getWidth(), getHeight());
        if (this.mFontSize > 0.0f) {
            this.mEditText.setTextSize(f);
        } else {
            adjustTextSize(getValue());
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDefaultDrawingIsUsed) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(getBackgroundColor());
            Canvas canvas2 = canvas;
            canvas2.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(getBorderColor());
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStrokeWidth(getBorderWidth());
            canvas2.drawRect(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), this.mPaint);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onTapUp(MotionEvent motionEvent) {
        if (this.mListener != null && this.mListener.onClick(this)) {
            return true;
        }
        runBasicAction();
        return super.onTouchEvent(motionEvent);
    }

    public int getSelectionEnd() {
        return this.mEditText.getSelectionEnd();
    }

    public int getSelectionStart() {
        return this.mEditText.getSelectionStart();
    }

    public void setSelection(int i) {
        this.mEditText.setSelection(i);
    }

    public void setSelection(int i, int i2) {
        this.mEditText.setSelection(i, i2);
    }

    public void setMaxLength(int i) {
        this.mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
    }

    public void setInputFilter(InputFilter[] inputFilterArr) {
        this.mEditText.setFilters(inputFilterArr);
    }

    public void setValue(String str) {
        this.mEditText.setText(str);
    }

    public String getValue() {
        return this.mProperty.getValue(ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
    }

    public void clear() {
        setValue("");
    }

    public void onFocusChange(View view, boolean z) {
        String str;
        if (this.mListener == null || !this.mListener.onFocusChange(this, z)) {
            if (!z && this.mEditText.getText().length() == 0 && (str = this.mDefaultValue) != null) {
                this.mEditText.setText(str);
            }
            super.onFocusChange(view, z);
            invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void adjustTextSize(String str) {
        int i;
        int i2 = this.mMinTextSize;
        int height = (this.mEditText.getHeight() - this.mEditText.getCompoundPaddingBottom()) - this.mEditText.getCompoundPaddingTop();
        int width = (this.mEditText.getWidth() - this.mEditText.getCompoundPaddingLeft()) - this.mEditText.getCompoundPaddingRight();
        this.mWidthLimit = width;
        this.mAvailableSpaceRect.right = (float) width;
        this.mAvailableSpaceRect.bottom = (float) height;
        if (!this.mIsMultiLine || Math.min(getRect().width(), getRect().height()) <= 40.0f) {
            i = binarySearch(i2, this.mMaxTextSize, this.mSizeTester, this.mAvailableSpaceRect);
        } else {
            i = 40;
        }
        this.mEditText.setTextSize(0, (float) i);
    }

    private static int binarySearch(int i, int i2, SizeTester sizeTester, RectF rectF) {
        int i3 = i2 - 1;
        int i4 = i;
        while (i <= i3) {
            i4 = (i + i3) >>> 1;
            int onTestSize = sizeTester.onTestSize(i4, rectF);
            if (onTestSize >= 0) {
                if (onTestSize <= 0) {
                    break;
                }
                i4--;
                i3 = i4;
            } else {
                int i5 = i4 + 1;
                i4 = i;
                i = i5;
            }
        }
        return i4;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        EditText editText = this.mEditText;
        editText.setText(editText.getText());
    }

    public void run() {
        float f = this.mFontSize * this.mDispDensity * this.mScale;
        if (!this.mIsMultiLine && this.mEditText.getLineCount() > 0) {
            this.mEditText.setPadding(0, ((int) ((((float) getHeight()) / 2.0f) - ((f / this.mDispDensity) * 0.1f))) - ((int) ((((float) this.mEditText.getLineHeight()) / 2.0f) * ((float) this.mEditText.getLineCount()))), 0, 0);
        }
    }
}
