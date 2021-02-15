package com.medscape.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.view.ViewCompat;
import com.google.android.material.textfield.TextInputLayout;
import com.medscape.android.R;

public class CustomTextInputLayout extends TextInputLayout {
    private CharSequence mHint;
    private boolean mIsHintSet;

    public CustomTextInputLayout(Context context) {
        super(context);
    }

    public CustomTextInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            this.mHint = ((EditText) view).getHint();
        }
        super.addView(view, i, layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mIsHintSet && ViewCompat.isLaidOut(this)) {
            setHint((CharSequence) null);
            CharSequence hint = getEditText().getHint();
            if (hint != null && hint.length() > 0) {
                this.mHint = hint;
            }
            setHint(this.mHint);
            this.mIsHintSet = true;
        }
    }

    public void setError(CharSequence charSequence) {
        super.setError(charSequence);
        EditText editText = getEditText();
        if (editText != null && Build.VERSION.SDK_INT >= 16) {
            editText.setBackground(getResources().getDrawable(R.drawable.white_rectangular_background));
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        EditText editText = getEditText();
        if (editText != null && Build.VERSION.SDK_INT >= 16) {
            editText.setBackground(getResources().getDrawable(R.drawable.white_rectangular_background));
        }
    }
}
