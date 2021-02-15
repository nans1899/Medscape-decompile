package com.medscape.android.reference.style;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import com.medscape.android.reference.interfaces.EmbeddableNewlineSpan;

public class NativeArticleTableSpan extends CharacterStyle implements EmbeddableNewlineSpan {
    public String tableId;

    public void updateDrawState(TextPaint textPaint) {
    }

    public NativeArticleTableSpan(String str) {
        this.tableId = str;
    }
}
