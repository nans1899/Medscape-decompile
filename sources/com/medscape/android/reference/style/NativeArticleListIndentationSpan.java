package com.medscape.android.reference.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

public class NativeArticleListIndentationSpan implements LeadingMarginSpan {
    private static final int NUMBER_SPAN = 9;
    private final int indent = 30;

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
    }

    public int getLeadingMargin(boolean z) {
        return z ? this.indent - 5 : this.indent * 2;
    }
}
