package com.wbmd.wbmdcommons.utils;

import android.database.Cursor;
import android.text.TextUtils;
import android.widget.AlphabetIndexer;

public class AlphabetNumberIndexer extends AlphabetIndexer {
    public AlphabetNumberIndexer(Cursor cursor, int i, CharSequence charSequence) {
        super(cursor, i, charSequence);
    }

    /* access modifiers changed from: protected */
    public int compare(String str, String str2) {
        if (TextUtils.isEmpty(str) || !"#".equals(str2)) {
            return super.compare(str, str2);
        }
        if (Character.isDigit(str.charAt(0))) {
            return 0;
        }
        return -1;
    }
}
