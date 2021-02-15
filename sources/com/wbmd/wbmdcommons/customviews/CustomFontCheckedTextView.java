package com.wbmd.wbmdcommons.customviews;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import com.wbmd.wbmdcommons.R;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;

public class CustomFontCheckedTextView extends AppCompatCheckedTextView {
    private static final String TAG = CustomFontCheckedTextView.class.getSimpleName();
    private String fontName;

    public CustomFontCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public CustomFontCheckedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public CustomFontCheckedTextView(Context context) {
        super(context);
        init((AttributeSet) null);
    }

    public CustomFontCheckedTextView(Context context, String str) {
        super(context);
        this.fontName = str;
        init((AttributeSet) null);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attributeSet) {
        if (isInEditMode()) {
            return;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomFontTextView);
            String string = obtainStyledAttributes.getString(R.styleable.CustomFontTextView_fontName);
            this.fontName = string;
            if (string != null) {
                setFont(string);
            }
            obtainStyledAttributes.recycle();
        } else if (!StringExtensions.isNullOrEmpty(this.fontName)) {
            setFont(this.fontName);
        }
    }

    public void onDetachedFromWindow() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        super.onDetachedFromWindow();
    }

    public void setFont(String str) {
        try {
            AssetManager assets = getContext().getAssets();
            setTypeface(Typeface.createFromAsset(assets, "font/" + str));
        } catch (RuntimeException e) {
            String str2 = TAG;
            Trace.e(str2, e.getMessage() + "  Fonts directory must be added to the apps res folder");
        } catch (Exception e2) {
            Trace.e(TAG, e2.getMessage());
        }
    }
}
