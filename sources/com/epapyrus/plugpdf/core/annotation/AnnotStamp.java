package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;

public class AnnotStamp extends BaseAnnot {
    private String mStampName;

    public AnnotStamp(Context context) {
        super(context, "STAMP");
        setScale(1.0f);
    }

    public void setStampName(String str) {
        this.mStampName = str;
    }

    public String getStampName() {
        return this.mStampName;
    }
}
