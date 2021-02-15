package com.wbmd.wbmdcommons.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import androidx.appcompat.widget.AppCompatEditText;
import com.wbmd.wbmdcommons.callbacks.EditTextImeBackListener;

public class EditTextBackEvent extends AppCompatEditText {
    private EditTextImeBackListener mOnImeBack;

    public EditTextBackEvent(Context context) {
        super(context);
    }

    public EditTextBackEvent(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditTextBackEvent(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        EditTextImeBackListener editTextImeBackListener;
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && (editTextImeBackListener = this.mOnImeBack) != null) {
            editTextImeBackListener.onImeBack();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener editTextImeBackListener) {
        this.mOnImeBack = editTextImeBackListener;
    }
}
