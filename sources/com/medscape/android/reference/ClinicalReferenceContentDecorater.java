package com.medscape.android.reference;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SuperscriptSpan;
import androidx.core.view.ViewCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.reference.model.Para;
import com.medscape.android.reference.style.NativeArticleBulletSpan;
import com.medscape.android.reference.style.NativeArticleFigureSpan;
import com.medscape.android.reference.style.NativeArticleListIndentationSpan;
import com.medscape.android.reference.style.NativeArticleParaSpan;
import com.medscape.android.reference.style.NativeArticleSideHeaderSpan;
import com.medscape.android.util.CustomTypefaceSpan;
import com.medscape.android.util.StringUtil;
import org.apache.commons.io.IOUtils;

public class ClinicalReferenceContentDecorater {
    private Context mContext;
    private int orderedListCount = 1;

    public ClinicalReferenceContentDecorater(Context context) {
        this.mContext = context;
    }

    public Para handleParagraph(Para para, int i, boolean z) {
        if (i != -1) {
            if (!z) {
                para.setSpan(new NativeArticleParaSpan(), i, para.length(), 17);
            } else {
                para.setSpan(new NativeArticleSideHeaderSpan(), i, para.length(), 17);
            }
        }
        return para;
    }

    public Para handleSimpleParaEnd(Para para, SpannableStringBuilder spannableStringBuilder) {
        para.append(IOUtils.LINE_SEPARATOR_UNIX);
        return para;
    }

    public Para handleParaStart(Para para, SpannableStringBuilder spannableStringBuilder) {
        para.append(IOUtils.LINE_SEPARATOR_UNIX);
        return para;
    }

    public Para handleTitleStart(Para para, SpannableStringBuilder spannableStringBuilder) {
        para.append(IOUtils.LINE_SEPARATOR_UNIX);
        return para;
    }

    public Para handleListItemStart(Para para, boolean z) {
        para.append(IOUtils.LINE_SEPARATOR_UNIX);
        if (!z) {
            para.append(IOUtils.LINE_SEPARATOR_UNIX);
        } else {
            this.orderedListCount = 1;
        }
        return para;
    }

    public Para handleListItemEnd(Para para, int i, boolean z) {
        int length = para.length();
        if (z) {
            StringBuilder sb = new StringBuilder();
            int i2 = this.orderedListCount;
            this.orderedListCount = i2 + 1;
            sb.append(i2);
            sb.append(". ");
            para.insert(i, sb.toString());
            para.setSpan(new NativeArticleListIndentationSpan(), i, length, 17);
        } else {
            para.setSpan(new NativeArticleBulletSpan(), i, length, 17);
        }
        return para;
    }

    public Para addLineBreak(Para para, SpannableStringBuilder spannableStringBuilder, int i) {
        para.append(IOUtils.LINE_SEPARATOR_UNIX);
        return para;
    }

    public Para handleFigure(Para para, SpannableStringBuilder spannableStringBuilder, NativeArticleFigureSpan nativeArticleFigureSpan) {
        para.setSpan(nativeArticleFigureSpan, para.length(), para.length(), 17);
        return para;
    }

    public Para handleSuperscript(Para para, int i) {
        int length = para.length();
        para.setSpan(new SuperscriptSpan(), i, length, 17);
        para.setSpan(new RelativeSizeSpan(0.75f), i, length, 17);
        return para;
    }

    public Para handle(Para para, int i, CharacterStyle characterStyle) {
        para.setSpan(characterStyle, i, para.length(), 17);
        return para;
    }

    public Para handleTitle(Para para, int i, CharacterStyle characterStyle) {
        para.append(IOUtils.LINE_SEPARATOR_UNIX);
        int length = para.length();
        para.setSpan(getRobotoMediumSpan(), i, length, 17);
        para.setSpan(new RelativeSizeSpan(1.5f), i, length, 17);
        if (characterStyle != null) {
            para.setSpan(characterStyle, 0, length, 17);
        }
        para.setSpan(new NativeArticleSideHeaderSpan(), i, para.length(), 17);
        return para;
    }

    public Para handleAside(Para para, int i) {
        int length = para.length();
        para.setSpan(new StyleSpan(3), i, length, 17);
        para.setSpan(new RelativeSizeSpan(1.5f), i, length, 17);
        para.setSpan(new ForegroundColorSpan(ViewCompat.MEASURED_STATE_MASK), i, length, 17);
        para.setSpan(new NativeArticleSideHeaderSpan(), i, para.length(), 17);
        return para;
    }

    public Para handleBlockQuote(Para para, int i) {
        int length = para.length();
        para.setSpan(new LeadingMarginSpan.Standard(30), i, length, 17);
        para.setSpan(new RelativeSizeSpan(0.9f), i, length, 17);
        para.setSpan(new NativeArticleSideHeaderSpan(), i, para.length(), 17);
        return para;
    }

    public SpannableStringBuilder handleText(String str) {
        if (str != null) {
            return removeWhiteSpaceExceptSpaces(str);
        }
        return new SpannableStringBuilder("");
    }

    public Para handleRItem(Para para, String str) {
        return (Para) para.append(IOUtils.LINE_SEPARATOR_UNIX).append(str.replaceFirst("refsrc", "")).append(". ");
    }

    private SpannableStringBuilder removeWhiteSpaceExceptSpaces(String str) {
        boolean startsWith = str.startsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        String[] split = StringUtil.trimAllWhiteSpaces(str).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        boolean endsWith = str.endsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (startsWith) {
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        for (int i = 0; i < split.length; i++) {
            String str2 = split[i];
            if (i != 0) {
                spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            spannableStringBuilder.append(str2.trim());
        }
        if (endsWith && spannableStringBuilder.length() > 0) {
            spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        return spannableStringBuilder;
    }

    private CustomTypefaceSpan getRobotoMediumSpan() {
        return new CustomTypefaceSpan("", Typeface.createFromAsset(this.mContext.getAssets(), "font/Roboto-Medium.ttf"));
    }
}
