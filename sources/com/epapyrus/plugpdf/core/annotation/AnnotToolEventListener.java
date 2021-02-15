package com.epapyrus.plugpdf.core.annotation;

import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;

public interface AnnotToolEventListener {
    boolean onShowContentsBox(AnnotNote annotNote);

    void onTouchBegin(int i, int i2, BaseAnnotTool baseAnnotTool);

    void onTouchEnd(int i, int i2, BaseAnnotTool baseAnnotTool);

    void onTouchMove(int i, int i2, BaseAnnotTool baseAnnotTool);
}
