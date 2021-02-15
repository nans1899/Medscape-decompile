package com.epapyrus.plugpdf.core.annotation.acroform;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.RectF;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.ReaderView;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseField extends ViewGroup implements View.OnTouchListener {
    private static String mEnableBackGroundColor;
    private static String mReadOnlyBackgroundColor;
    protected boolean mDefaultDrawingIsUsed = true;
    protected PDFDocument mDoc;
    protected FieldState mFieldState;
    protected FieldEventListener mListener;
    private View.OnTouchListener mOnTouchListener;
    protected FieldProperty mProperty;
    protected RectF mRect;
    protected float mScale;
    protected List<BaseField> mSiblings;
    protected RectF mTouchFrame;
    private String mType;

    public enum FieldState {
        ENABLE,
        DISABLE,
        READONLY
    }

    public abstract void beforeFlatten();

    public abstract void clear();

    public abstract View getNativeWidget();

    public abstract String getValue();

    public void onFocusChange(View view, boolean z) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean onTapUp(MotionEvent motionEvent);

    public abstract void setValue(String str);

    public BaseField(Context context, PDFDocument pDFDocument, String str, FieldProperty fieldProperty) {
        super(context);
        this.mType = str;
        this.mProperty = fieldProperty;
        this.mRect = fieldProperty.getRect();
        this.mDoc = pDFDocument;
        this.mSiblings = new ArrayList();
        if (!this.mDoc.canFillField() || Boolean.parseBoolean(this.mProperty.getValue("isReadOnly"))) {
            this.mFieldState = FieldState.READONLY;
        } else {
            this.mFieldState = FieldState.ENABLE;
        }
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.mOnTouchListener = onTouchListener;
    }

    public String getType() {
        return this.mType;
    }

    public int getPageIdx() {
        return this.mProperty.getPageIdx();
    }

    public String getTitle() {
        return this.mProperty.getTitle();
    }

    public String getUID() {
        return this.mProperty.getTitle();
    }

    public int getObjID() {
        return this.mProperty.getObjID();
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public void setFieldState(FieldState fieldState) {
        if (!this.mDoc.canFillField()) {
            fieldState = FieldState.READONLY;
        }
        if (getFieldState() != fieldState) {
            this.mFieldState = fieldState;
            FieldRule instance = FieldRule.instance();
            instance.registerFieldState(getPageIdx(), getTitle(), getUID(), fieldState);
            instance.applyRule(this);
        }
    }

    public FieldState getFieldState() {
        return this.mFieldState;
    }

    /* access modifiers changed from: protected */
    public int getBackgroundColor() {
        String str;
        if (getFieldState() == FieldState.DISABLE) {
            return Color.parseColor("#C0C0C0");
        }
        if (getFieldState() == FieldState.ENABLE && (str = mEnableBackGroundColor) != null) {
            return Color.parseColor(str);
        }
        String str2 = mReadOnlyBackgroundColor;
        if (str2 != null) {
            return Color.parseColor(str2);
        }
        return 0;
    }

    public static void setEnableBackgroundColor(String str) {
        mEnableBackGroundColor = str;
    }

    public static void setReadOnlyBackgroundColor(String str) {
        mReadOnlyBackgroundColor = str;
    }

    /* access modifiers changed from: protected */
    public int getBorderColor() {
        if (getFieldState() == FieldState.READONLY) {
            return 0;
        }
        return Color.parseColor("#9EA8B8");
    }

    /* access modifiers changed from: protected */
    public float getBorderWidth() {
        return getFieldState() == FieldState.READONLY ? 0.0f : 4.0f;
    }

    private View findReaderView(View view) {
        if (view instanceof ReaderView) {
            return view;
        }
        return findReaderView((View) view.getParent());
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.mOnTouchListener != null) {
            if (((ReaderView) findReaderView(this)).getPlugPDFDisplay().getGesture().getType().equals(BaseGestureProcessor.GestureType.EDIT)) {
                motionEvent.setLocation(((float) getRelativeLeft(view)) + motionEvent.getX(), ((float) getRelativeTop(view)) + motionEvent.getY());
            }
            this.mOnTouchListener.onTouch(view, motionEvent);
        }
        if (this.mFieldState != FieldState.ENABLE) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            return onTapUp(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    private int getRelativeLeft(View view) {
        if (view.getParent() == view.getRootView()) {
            return view.getLeft();
        }
        return view.getLeft() + getRelativeLeft((View) view.getParent());
    }

    private int getRelativeTop(View view) {
        if (view.getParent() == view.getRootView()) {
            return view.getTop();
        }
        return view.getTop() + getRelativeTop((View) view.getParent());
    }

    public void setListener(FieldEventListener fieldEventListener) {
        this.mListener = fieldEventListener;
    }

    public float getX() {
        RectF rectF = this.mTouchFrame;
        if (rectF == null) {
            return super.getX();
        }
        if (rectF.isEmpty()) {
            this.mTouchFrame.set(this.mRect.left * this.mScale, this.mRect.top * this.mScale, this.mRect.right * this.mScale, this.mRect.bottom * this.mScale);
            this.mTouchFrame.inset(-5.0f, -5.0f);
        }
        return this.mTouchFrame.left;
    }

    public float getY() {
        RectF rectF = this.mTouchFrame;
        if (rectF == null) {
            return super.getY();
        }
        if (rectF.isEmpty()) {
            this.mTouchFrame.set(this.mRect.left * this.mScale, this.mRect.top * this.mScale, this.mRect.right * this.mScale, this.mRect.bottom * this.mScale);
            this.mTouchFrame.inset(-5.0f, -5.0f);
        }
        return this.mTouchFrame.top;
    }

    public void setDefaultDrawing(boolean z) {
        this.mDefaultDrawingIsUsed = z;
    }

    /* access modifiers changed from: protected */
    public void runBasicAction() {
        String value = this.mProperty.getValue(ExifInterface.LATITUDE_SOUTH);
        if (!value.isEmpty()) {
            String[] split = value.split("\\|\\|");
            int length = split.length;
            int i = 0;
            while (i < length) {
                String str = split[i];
                if (!str.isEmpty() && str.contains(",")) {
                    String substring = str.substring(0, str.indexOf(","));
                    BasePlugPDFDisplay basePlugPDFDisplay = (BasePlugPDFDisplay) getParent().getParent();
                    if (substring.equals("GoTo")) {
                        basePlugPDFDisplay.goToPage(Integer.parseInt(str.substring(str.indexOf("D=") + 2)));
                    } else if (substring.equals("Show")) {
                        String substring2 = str.substring(str.indexOf("T=") + 2);
                        for (BaseField next : basePlugPDFDisplay.getPageView().getFieldList()) {
                            if (next.getTitle().equals(substring2)) {
                                next.setVisibility(0);
                            }
                        }
                    } else if (substring.equals("Hide")) {
                        String substring3 = str.substring(str.indexOf("T=") + 2);
                        for (BaseField next2 : basePlugPDFDisplay.getPageView().getFieldList()) {
                            if (next2.getTitle().equals(substring3)) {
                                next2.setVisibility(8);
                            }
                        }
                    } else if (substring.equals("URI")) {
                        getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str.substring(str.indexOf("URI=") + 4))));
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void addSibling(BaseField baseField) {
        if (getObjID() != baseField.getObjID()) {
            this.mSiblings.add(baseField);
        }
    }

    public RectF getRect() {
        return this.mRect;
    }
}
