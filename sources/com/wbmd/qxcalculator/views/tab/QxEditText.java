package com.wbmd.qxcalculator.views.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class QxEditText extends EditText {
    public QxEditText(Context context) {
        this(context, (AttributeSet) null);
    }

    public QxEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QxEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
