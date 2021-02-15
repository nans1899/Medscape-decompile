package com.epapyrus.plugpdf.core;

import android.graphics.Bitmap;
import android.graphics.PointF;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import com.epapyrus.plugpdf.core.gesture.BaseGestureProcessor;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import java.util.List;

public interface BaseReaderControl {
    boolean canCopyContent();

    boolean canExtract();

    boolean canFillField();

    boolean canModifyAnnot();

    boolean canModifyContent();

    boolean canPrint();

    void changeGestureType(BaseGestureProcessor.GestureType gestureType);

    void changeScale(double d, int i, int i2);

    void drawPage(Bitmap bitmap, int i, int i2, int i3, int i4, int i5, int i6, int i7);

    boolean fieldCenterOn(BaseField baseField);

    List<OutlineItem> getOutlineItem();

    PointF getPageSize(int i);

    void goToPage(int i);

    void resetSearchInfo();

    void search(String str, int i);

    void setAnnotationTool(BaseAnnotTool.AnnotToolType annotToolType);

    void setPageDisplayMode(BasePlugPDFDisplay.PageDisplayMode pageDisplayMode);

    void updateOutline(List<OutlineItem> list);
}
