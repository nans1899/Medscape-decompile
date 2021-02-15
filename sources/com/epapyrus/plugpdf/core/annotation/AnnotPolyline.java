package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;

public class AnnotPolyline extends BaseAnnot {
    public AnnotPolyline(Context context) {
        super(context, "POLYLINE");
        setScale(1.0f);
    }
}
