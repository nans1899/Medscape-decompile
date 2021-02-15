package com.wbmd.wbmdcommons.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class OffSetScrollView extends ScrollView {
    public OffSetScrollView(Context context) {
        super(context);
    }

    public OffSetScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getComputedVerticleScrollOffset() {
        return computeVerticalScrollOffset();
    }
}
