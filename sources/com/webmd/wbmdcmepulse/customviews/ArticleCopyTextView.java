package com.webmd.wbmdcmepulse.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;

public class ArticleCopyTextView extends CustomFontTextView {
    private final float DEFAULT_LINE_SPACING = 12.0f;
    private final float DEFAULT_TEXT_SIZE = 15.0f;

    public ArticleCopyTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setLineSpacing(12.0f, 1.0f);
        super.setTextSize(15.0f);
        init(attributeSet);
    }

    public ArticleCopyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
        super.setLineSpacing(12.0f, 1.0f);
        super.setTextSize(15.0f);
    }

    public ArticleCopyTextView(Context context) {
        super(context);
        init((AttributeSet) null);
        super.setLineSpacing(12.0f, 1.0f);
        super.setTextSize(15.0f);
    }
}
