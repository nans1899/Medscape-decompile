package com.epapyrus.plugpdf.core.annotation.acroform;

import android.graphics.RectF;
import java.util.HashMap;

public class FieldProperty {
    private int mObjID;
    private int mPageIdx;
    private HashMap<String, String> mProperties = new HashMap<>();
    private RectF mRect;
    private int mType;

    public FieldProperty(int i, int i2, int i3) {
        this.mObjID = i3;
        this.mType = i;
        this.mPageIdx = i2;
    }

    public void setRect(float f, float f2, float f3, float f4) {
        this.mRect = new RectF(f, f2, f3, f4);
    }

    public String getValue(String str) {
        return this.mProperties.get(str);
    }

    public void setValue(String str, String str2) {
        this.mProperties.put(str, str2);
    }

    public int getType() {
        return this.mType;
    }

    public RectF getRect() {
        return this.mRect;
    }

    public String getTitle() {
        return this.mProperties.get("T");
    }

    public int getPageIdx() {
        return this.mPageIdx;
    }

    public int getObjID() {
        return this.mObjID;
    }
}
