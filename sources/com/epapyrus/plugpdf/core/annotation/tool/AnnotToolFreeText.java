package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.epapyrus.plugpdf.core.PDFDocument;
import com.epapyrus.plugpdf.core.annotation.AnnotFactory;
import com.epapyrus.plugpdf.core.annotation.AnnotFreeText;
import com.epapyrus.plugpdf.core.annotation.AnnotToolEventListener;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;
import org.apache.commons.io.IOUtils;

public class AnnotToolFreeText extends BaseAnnotTool {
    /* access modifiers changed from: private */
    public PointF currPoint;
    /* access modifiers changed from: private */
    public EditText editText;
    /* access modifiers changed from: private */
    public AnnotFreeText mAnnot;
    private Context mContext;

    public void enter() {
    }

    public BaseAnnot singleTapUp(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        return null;
    }

    /* access modifiers changed from: protected */
    public void touchMove(int i, int i2, AnnotToolEventListener annotToolEventListener) {
    }

    public AnnotToolFreeText(Context context) {
        super(context);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void touchBegin(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        Rect rect = new Rect();
        this.currPoint = getCorrectPos(i, i2);
        EditText editText2 = this.editText;
        if (editText2 != null) {
            editText2.getFocusedRect(rect);
            if (!rect.contains((int) this.currPoint.x, (int) this.currPoint.y)) {
                this.editText.setFocusable(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void touchEnd(int i, int i2, AnnotToolEventListener annotToolEventListener) {
        int freeTextColor = AnnotSetting.instance().getFreeTextColor();
        int freeTextOpacity = AnnotSetting.instance().getFreeTextOpacity();
        int freeTextSize = AnnotSetting.instance().getFreeTextSize();
        String freeTextFont = AnnotSetting.instance().getFreeTextFont();
        this.mAnnot = (AnnotFreeText) AnnotFactory.instance().createAnnot(this.mContext, "FREE_TEXT");
        this.mAnnotScale = this.mPageView.getAnnotScale();
        this.mAnnot.setScale(this.mAnnotScale);
        this.mAnnot.setPageIdx(this.mPageView.getPageIdx());
        this.mAnnot.setTextSize(freeTextSize);
        this.mAnnot.setScale(this.mAnnotScale);
        this.mAnnot.setARGB(freeTextOpacity, Color.red(freeTextColor), Color.green(freeTextColor), Color.blue(freeTextColor));
        PointF correctPos = getCorrectPos(i, i2);
        this.currPoint = correctPos;
        this.mAnnot.setPosition(correctPos);
        this.mAnnot.setFont(freeTextFont);
        this.mAnnot.setBBox(this.currPoint.x, this.currPoint.y, this.currPoint.x + 100.0f, this.currPoint.y + 100.0f);
        this.mPageView.addAnnot(this.mAnnot);
        this.mAnnot.invalidate();
        this.currPoint = new PointF((float) (i - this.mPageView.getLeft()), (float) (i2 - this.mPageView.getTop()));
        if (this.editText != null) {
            this.mPageView.removeView(this.editText);
        }
        EditText editText2 = new EditText(this.mContext);
        this.editText = editText2;
        editText2.requestFocus();
        this.editText.setFocusable(true);
        this.editText.setBackgroundColor(0);
        this.editText.setSingleLine(true);
        this.editText.setTextColor(this.mAnnot.getARGB());
        this.editText.setTextSize(0, ((float) this.mAnnot.getTextSize()) * this.mAnnotScale);
        this.editText.layout((int) this.currPoint.x, (int) this.currPoint.y, ((int) this.currPoint.x) + 100, ((int) this.currPoint.y) + this.editText.getLineHeight());
        this.editText.setPadding(0, 0, 0, 0);
        String substitueFont = PDFDocument.getSubstitueFont();
        if (isSystemFont(substitueFont)) {
            this.editText.setTypeface(Typeface.createFromFile("/system/fonts/" + substitueFont));
        } else if (isInAsset(substitueFont)) {
            AssetManager assets = this.mContext.getAssets();
            this.editText.setTypeface(Typeface.createFromAsset(assets, "Fonts/" + substitueFont));
        }
        this.editText.setGravity(48);
        this.editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                int width = AnnotToolFreeText.this.editText.getWidth();
                int measureText = (int) AnnotToolFreeText.this.editText.getPaint().measureText(String.valueOf(AnnotToolFreeText.this.editText.getText()));
                AnnotToolFreeText.this.currPoint.x = (float) AnnotToolFreeText.this.editText.getLeft();
                AnnotToolFreeText.this.currPoint.y = (float) AnnotToolFreeText.this.editText.getTop();
                if (((float) (((int) AnnotToolFreeText.this.currPoint.x) + measureText)) + AnnotToolFreeText.this.editText.getTextSize() >= ((float) AnnotToolFreeText.this.mPageView.getWidth())) {
                    if (AnnotToolFreeText.this.editText.getLineCount() * AnnotToolFreeText.this.editText.getLineHeight() >= AnnotToolFreeText.this.editText.getHeight()) {
                        AnnotToolFreeText.this.editText.layout((int) AnnotToolFreeText.this.currPoint.x, (int) AnnotToolFreeText.this.currPoint.y, AnnotToolFreeText.this.mPageView.getWidth(), ((int) AnnotToolFreeText.this.currPoint.y) + (AnnotToolFreeText.this.editText.getLineCount() * AnnotToolFreeText.this.editText.getLineHeight()));
                    } else if (AnnotToolFreeText.this.editText.getLineCount() == 1) {
                        AnnotToolFreeText.this.editText.layout((int) AnnotToolFreeText.this.currPoint.x, (int) AnnotToolFreeText.this.currPoint.y, AnnotToolFreeText.this.mPageView.getWidth(), ((int) AnnotToolFreeText.this.currPoint.y) + AnnotToolFreeText.this.editText.getLineHeight());
                    }
                    int selectionStart = AnnotToolFreeText.this.editText.getSelectionStart();
                    AnnotToolFreeText.this.editText.setSingleLine(false);
                    AnnotToolFreeText.this.editText.setSelection(selectionStart);
                } else if (width < measureText) {
                    AnnotToolFreeText.this.editText.layout((int) AnnotToolFreeText.this.currPoint.x, (int) AnnotToolFreeText.this.currPoint.y, ((int) AnnotToolFreeText.this.currPoint.x) + measureText, ((int) AnnotToolFreeText.this.currPoint.y) + AnnotToolFreeText.this.editText.getLineHeight());
                }
            }
        });
        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    int left = AnnotToolFreeText.this.editText.getLeft();
                    int top = AnnotToolFreeText.this.editText.getTop() + AnnotToolFreeText.this.mPageView.getTop();
                    AnnotToolFreeText annotToolFreeText = AnnotToolFreeText.this;
                    PointF unused = annotToolFreeText.currPoint = annotToolFreeText.getCorrectPos(left, top);
                    AnnotToolFreeText.this.mAnnot.setText(AnnotToolFreeText.this.editText.getText().toString());
                    AnnotToolFreeText.this.mAnnot.setTextSize(AnnotToolFreeText.this.mAnnot.getTextSize());
                    if (!AnnotToolFreeText.this.editText.getText().toString().equals("")) {
                        int indexOf = AnnotToolFreeText.this.editText.getText().toString().indexOf(IOUtils.LINE_SEPARATOR_UNIX);
                        int width = (int) (((float) AnnotToolFreeText.this.editText.getWidth()) * AnnotToolFreeText.this.mAnnotScale);
                        if (indexOf != -1) {
                            String substring = AnnotToolFreeText.this.editText.getText().toString().substring(0, indexOf);
                            String substring2 = AnnotToolFreeText.this.editText.getText().toString().substring(indexOf + 1);
                            width = (int) AnnotToolFreeText.this.editText.getPaint().measureText(String.valueOf(substring));
                            while (substring2.indexOf(IOUtils.LINE_SEPARATOR_UNIX) != -1) {
                                int indexOf2 = substring2.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
                                String substring3 = substring2.substring(0, indexOf2);
                                substring2 = substring2.substring(indexOf2);
                                width = Math.max(width, (int) AnnotToolFreeText.this.editText.getPaint().measureText(String.valueOf(substring3)));
                            }
                        }
                        AnnotToolFreeText.this.mAnnot.setBBox((float) ((int) AnnotToolFreeText.this.currPoint.x), (float) ((int) AnnotToolFreeText.this.currPoint.y), (float) (((int) AnnotToolFreeText.this.currPoint.x) + ((int) (((double) (((float) Math.min((int) (((float) width) / AnnotToolFreeText.this.mAnnotScale), AnnotToolFreeText.this.editText.getWidth())) / AnnotToolFreeText.this.mAnnotScale)) * 1.1d))), ((float) ((int) AnnotToolFreeText.this.currPoint.y)) + (((float) AnnotToolFreeText.this.editText.getHeight()) / AnnotToolFreeText.this.mAnnotScale) + (((float) AnnotToolFreeText.this.editText.getLineHeight()) - AnnotToolFreeText.this.editText.getTextSize()));
                        AnnotToolFreeText.this.mPageView.addFreeTextAnnot(AnnotToolFreeText.this.currPoint, AnnotToolFreeText.this.editText.getText().toString(), AnnotToolFreeText.this.mAnnot.getFont(), (double) AnnotToolFreeText.this.mAnnot.getTextSize(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, ((double) AnnotToolFreeText.this.mAnnot.mAlpha) / 255.0d, AnnotToolFreeText.this.mAnnot.mR, AnnotToolFreeText.this.mAnnot.mG, AnnotToolFreeText.this.mAnnot.mB, AnnotToolFreeText.this.mAnnot.getBBox());
                    }
                    if (AnnotToolFreeText.this.editText != null) {
                        AnnotToolFreeText.this.mPageView.removeView(AnnotToolFreeText.this.editText);
                    }
                }
            }
        });
        this.editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66 && keyEvent.getAction() == 0) {
                    int selectionStart = AnnotToolFreeText.this.editText.getSelectionStart();
                    AnnotToolFreeText.this.editText.setSingleLine(false);
                    AnnotToolFreeText.this.editText.layout((int) AnnotToolFreeText.this.currPoint.x, (int) AnnotToolFreeText.this.currPoint.y, AnnotToolFreeText.this.mPageView.getWidth(), ((int) AnnotToolFreeText.this.currPoint.y) + AnnotToolFreeText.this.editText.getHeight() + AnnotToolFreeText.this.editText.getLineHeight());
                    AnnotToolFreeText.this.editText.setSelection(selectionStart);
                }
                return false;
            }
        });
        this.mPageView.addView(this.editText);
        ((InputMethodManager) this.mContext.getSystemService("input_method")).showSoftInput(this.editText, 2);
    }

    public void exit() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            editText2.setFocusable(false);
            InputMethodManager inputMethodManager = (InputMethodManager) this.mContext.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(this.editText.getWindowToken(), 0);
            }
        }
    }

    private boolean isSystemFont(String str) {
        String[] fileList = getFileList("/system/fonts/");
        for (String equals : fileList) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInAsset(String str) {
        String[] fileList = getFileList(PDFDocument.getFontPath());
        for (String equals : fileList) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String[] getFileList(String str) {
        File file = new File(str);
        if (!file.isDirectory()) {
            return null;
        }
        return file.list();
    }
}
