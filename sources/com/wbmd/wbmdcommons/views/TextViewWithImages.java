package com.wbmd.wbmdcommons.views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextViewWithImages extends TextView {
    private static final String DRAWABLE = "drawable";
    public static final String PATTERN = "\\Q[img src=\\E([a-zA-Z0-9_]+?)\\Q/]\\E";

    public TextViewWithImages(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public TextViewWithImages(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TextViewWithImages(Context context) {
        super(context);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(getTextWithImages(getContext(), charSequence, getLineHeight(), getCurrentTextColor()), TextView.BufferType.SPANNABLE);
    }

    private static Spannable getTextWithImages(Context context, CharSequence charSequence, int i, int i2) {
        Spannable newSpannable = Spannable.Factory.getInstance().newSpannable(charSequence);
        addImages(context, newSpannable, i, i2);
        return newSpannable;
    }

    private static boolean addImages(Context context, Spannable spannable, int i, int i2) {
        boolean z;
        Matcher matcher = Pattern.compile(PATTERN).matcher(spannable);
        boolean z2 = false;
        while (matcher.find()) {
            ImageSpan[] imageSpanArr = (ImageSpan[]) spannable.getSpans(matcher.start(), matcher.end(), ImageSpan.class);
            int length = imageSpanArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    z = true;
                    break;
                }
                ImageSpan imageSpan = imageSpanArr[i3];
                if (spannable.getSpanStart(imageSpan) < matcher.start() || spannable.getSpanEnd(imageSpan) > matcher.end()) {
                    z = false;
                } else {
                    spannable.removeSpan(imageSpan);
                    i3++;
                }
            }
            z = false;
            int identifier = context.getResources().getIdentifier(spannable.subSequence(matcher.start(1), matcher.end(1)).toString().trim(), DRAWABLE, context.getPackageName());
            if (z) {
                spannable.setSpan(makeImageSpan(context, identifier, i, i2), matcher.start(), matcher.end(), 33);
                z2 = true;
            }
        }
        return z2;
    }

    private static ImageSpan makeImageSpan(Context context, int i, int i2, int i3) {
        Drawable drawable = context.getResources().getDrawable(i);
        drawable.mutate();
        drawable.setColorFilter(i3, PorterDuff.Mode.MULTIPLY);
        drawable.setBounds(0, 0, i2, i2);
        return new ImageSpan(drawable, 0);
    }
}
