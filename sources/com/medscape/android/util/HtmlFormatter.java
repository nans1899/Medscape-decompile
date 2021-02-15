package com.medscape.android.util;

import android.text.SpannableStringBuilder;
import com.wbmd.wbmdcommons.logging.Trace;

public class HtmlFormatter {
    private static String TAG = HtmlFormatter.class.getSimpleName();

    public static SpannableStringBuilder removeTrailingLineBreaks(SpannableStringBuilder spannableStringBuilder) {
        if (spannableStringBuilder != null) {
            try {
                if (spannableStringBuilder.length() > 0) {
                    while (spannableStringBuilder.charAt(spannableStringBuilder.length() - 1) == 10) {
                        spannableStringBuilder = new SpannableStringBuilder(spannableStringBuilder, 0, spannableStringBuilder.length() - 1);
                    }
                }
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
            }
        }
        return spannableStringBuilder;
    }
}
