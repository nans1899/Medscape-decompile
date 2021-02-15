package com.epapyrus.plugpdf.core.annotation.acroform;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public interface CustomCheckBoxPainter {
    Drawable draw(CheckBoxField checkBoxField, Canvas canvas);
}
