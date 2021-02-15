package com.epapyrus.plugpdf.core.viewer;

import android.view.MotionEvent;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.epapyrus.plugpdf.core.viewer.DocumentState;

public interface ReaderListener {
    void onChangeDisplayMode(BasePlugPDFDisplay.PageDisplayMode pageDisplayMode, int i);

    void onChangeZoom(double d);

    void onDoubleTapUp(MotionEvent motionEvent);

    void onGoToPage(int i, int i2);

    void onLoadFinish(DocumentState.OPEN open);

    void onLongPress(MotionEvent motionEvent);

    void onScroll(int i, int i2);

    void onSearchFinish(boolean z);

    void onSingleTapUp(MotionEvent motionEvent);
}
