package com.epapyrus.plugpdf.core.viewer;

import android.graphics.Bitmap;
import com.epapyrus.plugpdf.core.annotation.BaseAnnot;
import com.epapyrus.plugpdf.core.annotation.acroform.BaseField;
import java.util.List;

public interface PageViewListener {
    void cachePageBitmap(int i, Bitmap bitmap);

    void onAnnotationEdited(int i, List<BaseAnnot> list, int i2);

    void onAnnotationList(int i, List<BaseAnnot> list);

    void onFieldList(int i, List<BaseField> list);

    void onPageLoadFinish(int i);
}
