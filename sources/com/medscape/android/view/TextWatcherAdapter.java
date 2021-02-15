package com.medscape.android.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextWatcherAdapter implements TextWatcher {
    private final TextWatcherListener listener;
    private final EditText view;

    public interface TextWatcherListener {
        void onTextChanged(EditText editText, String str);
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public TextWatcherAdapter(EditText editText, TextWatcherListener textWatcherListener) {
        this.view = editText;
        this.listener = textWatcherListener;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.listener.onTextChanged(this.view, charSequence.toString());
    }
}
