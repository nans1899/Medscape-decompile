package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;

public class AnnotPolygon extends BaseAnnot {
    public AnnotPolygon(Context context) {
        super(context, "POLYGON");
        setScale(1.0f);
    }
}
