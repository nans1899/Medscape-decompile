package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class ResultNoteWTitleSubTitleRowItem extends QxRecyclerViewRowItem {
    private Spanned answerSpanned;
    public Result result;
    private boolean shouldHideTopPadding;
    private boolean shouldLeftJustify;
    private Spanned subTitleSpanned;
    private Spanned titleSpanned;

    public ResultNoteWTitleSubTitleRowItem(Result result2, boolean z) {
        this(result2, z, false);
    }

    public ResultNoteWTitleSubTitleRowItem(Result result2, boolean z, boolean z2) {
        this.result = result2;
        this.shouldHideTopPadding = z;
        this.shouldLeftJustify = z2;
        if (result2.titleFormulaResult != null) {
            this.titleSpanned = Html.fromHtml(result2.titleFormulaResult);
        } else if (result2.title != null) {
            this.titleSpanned = Html.fromHtml(result2.title);
        }
        if (result2.subTitleFormulaResult != null) {
            this.subTitleSpanned = Html.fromHtml(result2.subTitleFormulaResult);
        } else if (result2.subTitle != null) {
            this.subTitleSpanned = Html.fromHtml(result2.subTitle);
        }
        if (result2.answerResult != null) {
            this.answerSpanned = Html.fromHtml(result2.answerResult);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_result_note_w_title_subtitle;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CalculatorResultNoteWTitleSubTitleViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CalculatorResultNoteWTitleSubTitleViewHolder calculatorResultNoteWTitleSubTitleViewHolder = (CalculatorResultNoteWTitleSubTitleViewHolder) viewHolder;
        calculatorResultNoteWTitleSubTitleViewHolder.paddingView.setVisibility(this.shouldHideTopPadding ? 8 : 0);
        if (this.titleSpanned != null) {
            calculatorResultNoteWTitleSubTitleViewHolder.titleTextView.setText(this.titleSpanned);
            calculatorResultNoteWTitleSubTitleViewHolder.titleTextView.setVisibility(0);
        } else {
            calculatorResultNoteWTitleSubTitleViewHolder.titleTextView.setVisibility(8);
        }
        if (this.subTitleSpanned != null) {
            calculatorResultNoteWTitleSubTitleViewHolder.subTitleTextView.setText(this.subTitleSpanned);
            calculatorResultNoteWTitleSubTitleViewHolder.subTitleTextView.setVisibility(0);
        } else {
            calculatorResultNoteWTitleSubTitleViewHolder.subTitleTextView.setVisibility(8);
        }
        if (this.answerSpanned != null) {
            calculatorResultNoteWTitleSubTitleViewHolder.answerTextView.setText(this.answerSpanned);
            calculatorResultNoteWTitleSubTitleViewHolder.answerTextView.setVisibility(0);
            if (this.shouldLeftJustify) {
                calculatorResultNoteWTitleSubTitleViewHolder.answerTextView.setGravity(GravityCompat.START);
            } else {
                calculatorResultNoteWTitleSubTitleViewHolder.answerTextView.setGravity(GravityCompat.END);
            }
        } else {
            calculatorResultNoteWTitleSubTitleViewHolder.answerTextView.setVisibility(8);
        }
    }

    public static final class CalculatorResultNoteWTitleSubTitleViewHolder extends QxRecyclerRowItemViewHolder {
        TextView answerTextView;
        View paddingView;
        TextView subTitleTextView;
        TextView titleTextView;

        public CalculatorResultNoteWTitleSubTitleViewHolder(View view) {
            super(view);
            this.paddingView = view.findViewById(R.id.padding_view);
            TextView textView = (TextView) view.findViewById(R.id.title_text_view);
            this.titleTextView = textView;
            textView.setMovementMethod(BetterLinkMovementMethod.getInstance());
            TextView textView2 = (TextView) view.findViewById(R.id.sub_title_text_view);
            this.subTitleTextView = textView2;
            textView2.setMovementMethod(BetterLinkMovementMethod.getInstance());
            TextView textView3 = (TextView) view.findViewById(R.id.answer_text_view);
            this.answerTextView = textView3;
            textView3.setMovementMethod(BetterLinkMovementMethod.getInstance());
        }
    }
}
