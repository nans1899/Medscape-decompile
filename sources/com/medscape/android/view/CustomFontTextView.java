package com.medscape.android.view;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import com.medscape.android.R;

public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public CustomFontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public CustomFontTextView(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet) {
        if (!isInEditMode() && attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomFontTextView);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                AssetManager assets = getContext().getAssets();
                setTypeface(Typeface.createFromAsset(assets, "font/" + string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void onDetachedFromWindow() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        super.onDetachedFromWindow();
    }
}
