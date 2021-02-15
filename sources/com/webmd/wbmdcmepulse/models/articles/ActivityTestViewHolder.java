package com.webmd.wbmdcmepulse.models.articles;

import android.widget.LinearLayout;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.webmd.wbmdcmepulse.customviews.AnswersTableLayout;

public class ActivityTestViewHolder {
    public AnswersTableLayout answersTableLayout;
    public LinearLayout backgroundLinearLayout;
    public CustomFontTextView questionTextView;

    public ActivityTestViewHolder() {
    }

    public ActivityTestViewHolder(AnswersTableLayout answersTableLayout2, LinearLayout linearLayout, CustomFontTextView customFontTextView) {
        this.answersTableLayout = answersTableLayout2;
        this.backgroundLinearLayout = linearLayout;
        this.questionTextView = customFontTextView;
    }
}
