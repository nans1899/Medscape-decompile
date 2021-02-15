package com.epapyrus.plugpdf.core.annotation;

public interface AnnotEventListener {
    boolean onDoEditMenu(BaseAnnot baseAnnot, int i, int i2);

    boolean onLongPressDown(BaseAnnot baseAnnot);

    boolean onTapUp(BaseAnnot baseAnnot);
}
