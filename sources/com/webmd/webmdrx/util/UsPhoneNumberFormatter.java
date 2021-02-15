package com.webmd.webmdrx.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.lang.ref.WeakReference;

public class UsPhoneNumberFormatter implements TextWatcher {
    private boolean clearFlag;
    private boolean mFormatting;
    private String mLastBeforeText;
    private int mLastStartLocation;
    private WeakReference<EditText> mWeakEditText;

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public UsPhoneNumberFormatter(WeakReference<EditText> weakReference) {
        this.mWeakEditText = weakReference;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (i3 == 0 && charSequence.toString().equals("1 ")) {
            this.clearFlag = true;
        }
        this.mLastStartLocation = i;
        this.mLastBeforeText = charSequence.toString();
    }

    public void afterTextChanged(Editable editable) {
        if (!this.mFormatting) {
            this.mFormatting = true;
            int i = this.mLastStartLocation;
            String str = this.mLastBeforeText;
            String obj = editable.toString();
            String formatUsNumber = formatUsNumber(editable);
            if (obj.length() > str.length()) {
                int length = formatUsNumber.length() - (str.length() - i);
                EditText editText = (EditText) this.mWeakEditText.get();
                if (length < 0) {
                    length = 0;
                }
                editText.setSelection(length);
            } else {
                int length2 = formatUsNumber.length() - (obj.length() - i);
                if (length2 > 0 && !Character.isDigit(formatUsNumber.charAt(length2 - 1))) {
                    length2--;
                }
                EditText editText2 = (EditText) this.mWeakEditText.get();
                if (length2 < 0) {
                    length2 = 0;
                }
                editText2.setSelection(length2);
            }
            this.mFormatting = false;
        }
    }

    private String formatUsNumber(Editable editable) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i2 < editable.length()) {
            if (!Character.isDigit(editable.charAt(i2))) {
                editable.delete(i2, i2 + 1);
            } else {
                i2++;
            }
        }
        String obj = editable.toString();
        int length = obj.length();
        if (length == 0 || length > 10) {
            editable.clear();
            editable.append(obj);
            return obj;
        } else if (this.clearFlag) {
            editable.clear();
            this.clearFlag = false;
            return "";
        } else {
            if (length + 0 > 3) {
                sb.append("(" + obj.substring(0, 3) + ") ");
                i = 3;
            }
            if (length - i > 3) {
                StringBuilder sb2 = new StringBuilder();
                int i3 = i + 3;
                sb2.append(obj.substring(i, i3));
                sb2.append("-");
                sb.append(sb2.toString());
                i = i3;
            }
            if (length > i) {
                sb.append(obj.substring(i));
            }
            editable.clear();
            editable.append(sb.toString());
            return sb.toString();
        }
    }
}
