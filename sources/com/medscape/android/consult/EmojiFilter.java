package com.medscape.android.consult;

import android.text.InputFilter;
import android.text.Spanned;

public class EmojiFilter implements InputFilter {
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        while (i < i2) {
            int type = Character.getType(charSequence.charAt(i));
            if (type == 19 || type == 28) {
                return "";
            }
            i++;
        }
        return null;
    }
}
