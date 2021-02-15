package com.medscape.android.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import com.medscape.android.view.TextWatcherAdapter;

public class ClearableEditText extends EditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {
    private View.OnFocusChangeListener f;
    private View.OnTouchListener l;
    private Listener listener;
    private Drawable xD;

    public interface Listener {
        void didClearText();
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    public ClearableEditText(Context context) {
        super(context);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public ClearableEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.l = onTouchListener;
    }

    public void setOnFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        this.f = onFocusChangeListener;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (getCompoundDrawables()[2] != null) {
            if (motionEvent.getX() > ((float) ((getWidth() - getPaddingRight()) - this.xD.getIntrinsicWidth()))) {
                if (motionEvent.getAction() == 1) {
                    setText("");
                    Listener listener2 = this.listener;
                    if (listener2 != null) {
                        listener2.didClearText();
                    }
                }
                return true;
            }
        }
        View.OnTouchListener onTouchListener = this.l;
        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, motionEvent);
        }
        return false;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            setClearIconVisible(!TextUtils.isEmpty(getText()));
        } else {
            setClearIconVisible(false);
        }
        View.OnFocusChangeListener onFocusChangeListener = this.f;
        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(view, z);
        }
    }

    public void onTextChanged(EditText editText, String str) {
        if (isFocused()) {
            setClearIconVisible(!TextUtils.isEmpty(str));
        }
    }

    private void init() {
        Drawable drawable = getCompoundDrawables()[2];
        this.xD = drawable;
        if (drawable == null) {
            this.xD = getResources().getDrawable(17301610);
        }
        Drawable drawable2 = this.xD;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.xD.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
    }

    /* access modifiers changed from: protected */
    public void setClearIconVisible(boolean z) {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], z ? this.xD : null, getCompoundDrawables()[3]);
    }
}
