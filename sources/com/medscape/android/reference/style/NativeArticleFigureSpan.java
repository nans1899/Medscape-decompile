package com.medscape.android.reference.style;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import com.medscape.android.reference.interfaces.EmbeddableNewlineSpan;

public class NativeArticleFigureSpan extends CharacterStyle implements EmbeddableNewlineSpan {
    public String figureId;

    public void updateDrawState(TextPaint textPaint) {
    }

    public NativeArticleFigureSpan(String str) {
        this.figureId = str;
    }
}
