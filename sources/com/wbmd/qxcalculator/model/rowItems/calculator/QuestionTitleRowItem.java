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
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;

public class QuestionTitleRowItem extends QxRecyclerViewRowItem {
    public Question question;
    private Spanned sectionTitleSpanned;
    private Spanned titleSpanned;

    public QuestionTitleRowItem(Question question2) {
        this.question = question2;
        if (question2.sectionName != null && !question2.sectionName.isEmpty()) {
            this.sectionTitleSpanned = Html.fromHtml(question2.sectionName);
        }
        this.titleSpanned = Html.fromHtml(question2.title);
    }

    public int getResourceId() {
        return R.layout.row_item_question_title;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return QuestionTitleViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        QuestionTitleViewHolder questionTitleViewHolder = (QuestionTitleViewHolder) viewHolder;
        if (this.sectionTitleSpanned != null) {
            questionTitleViewHolder.sectionNameTextView.setVisibility(0);
            questionTitleViewHolder.sectionNameTextView.setText(this.sectionTitleSpanned);
        } else {
            questionTitleViewHolder.sectionNameTextView.setVisibility(8);
        }
        questionTitleViewHolder.titleTextView.setText(this.titleSpanned);
    }

    public static final class QuestionTitleViewHolder extends QxRecyclerRowItemViewHolder {
        TextView sectionNameTextView;
        TextView titleTextView;

        public QuestionTitleViewHolder(View view) {
            super(view);
            this.sectionNameTextView = (TextView) view.findViewById(R.id.section_name);
            this.titleTextView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
