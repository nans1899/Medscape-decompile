package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorResultSection;

public class QuestionLinkedCalculatorResultSectionRowItem extends QxRecyclerViewRowItem {
    private Spanned answer;
    public LinkedCalculatorResultSection result;
    private Spanned title;

    public QuestionLinkedCalculatorResultSectionRowItem(LinkedCalculatorResultSection linkedCalculatorResultSection) {
        this.result = linkedCalculatorResultSection;
        if (linkedCalculatorResultSection.title != null) {
            this.title = Html.fromHtml(linkedCalculatorResultSection.title);
        }
        if (linkedCalculatorResultSection.answer != null) {
            this.answer = Html.fromHtml(linkedCalculatorResultSection.answer);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_question_linked_calculator_result_section;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return QuestionLinkedCalculatorResultSectionViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        QuestionLinkedCalculatorResultSectionViewHolder questionLinkedCalculatorResultSectionViewHolder = (QuestionLinkedCalculatorResultSectionViewHolder) viewHolder;
        if (this.title != null) {
            questionLinkedCalculatorResultSectionViewHolder.textView.setText(this.result.title);
            questionLinkedCalculatorResultSectionViewHolder.textView.setVisibility(0);
        } else {
            questionLinkedCalculatorResultSectionViewHolder.textView.setVisibility(8);
        }
        if (this.answer != null) {
            questionLinkedCalculatorResultSectionViewHolder.answerTextView.setText(this.result.answer);
            questionLinkedCalculatorResultSectionViewHolder.answerTextView.setVisibility(0);
            return;
        }
        questionLinkedCalculatorResultSectionViewHolder.answerTextView.setVisibility(8);
    }

    public static final class QuestionLinkedCalculatorResultSectionViewHolder extends QxRecyclerRowItemViewHolder {
        TextView answerTextView;
        TextView textView;

        public QuestionLinkedCalculatorResultSectionViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.result_title_text_view);
            this.answerTextView = (TextView) view.findViewById(R.id.result_value_text_view);
        }
    }
}
