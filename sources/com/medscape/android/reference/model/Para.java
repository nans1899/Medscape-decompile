package com.medscape.android.reference.model;

import android.text.SpannableStringBuilder;
import java.io.Serializable;

public class Para extends SpannableStringBuilder implements Serializable {
    public Object getLastSpan(Class<?> cls) {
        Object[] spans = getSpans(0, length(), cls);
        if (spans == null || spans.length <= 0) {
            return null;
        }
        return spans[spans.length - 1];
    }
}
