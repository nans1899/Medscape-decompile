package com.webmd.wbmdcmepulse.models.articles;

import android.view.View;
import android.widget.RadioButton;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;

public class SingleAnswerViewHolder {
    public int answerId;
    public View background;
    public RadioButton radioButton;
    public ArticleCopyTextView textView;

    public SingleAnswerViewHolder() {
    }

    public SingleAnswerViewHolder(int i, View view, RadioButton radioButton2, ArticleCopyTextView articleCopyTextView) {
        this.answerId = i;
        this.background = view;
        this.radioButton = radioButton2;
        this.textView = articleCopyTextView;
    }
}
