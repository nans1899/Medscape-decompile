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
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class ResultDefaultRowItem extends QxRecyclerViewRowItem {
    private Spanned answer;
    public Result result;
    private Spanned title;

    public ResultDefaultRowItem(Result result2) {
        this.result = result2;
        if (result2.titleFormulaResult != null) {
            this.title = Html.fromHtml(result2.titleFormulaResult);
        } else if (result2.title != null) {
            this.title = Html.fromHtml(result2.title);
        }
        if (result2.answerResult != null) {
            this.answer = Html.fromHtml(result2.answerResult);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_result_default;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CalculatorResultViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CalculatorResultViewHolder calculatorResultViewHolder = (CalculatorResultViewHolder) viewHolder;
        if (this.title != null) {
            calculatorResultViewHolder.textView.setText(this.title);
            calculatorResultViewHolder.textView.setVisibility(0);
        } else {
            calculatorResultViewHolder.textView.setVisibility(8);
        }
        if (this.answer != null) {
            calculatorResultViewHolder.answerTextView.setText(this.answer);
            calculatorResultViewHolder.answerTextView.setVisibility(0);
            return;
        }
        calculatorResultViewHolder.answerTextView.setVisibility(8);
    }

    public static final class CalculatorResultViewHolder extends QxRecyclerRowItemViewHolder {
        TextView answerTextView;
        TextView textView;

        public CalculatorResultViewHolder(View view) {
            super(view);
            TextView textView2 = (TextView) view.findViewById(R.id.result_title_text_view);
            this.textView = textView2;
            textView2.setMovementMethod(BetterLinkMovementMethod.getInstance());
            TextView textView3 = (TextView) view.findViewById(R.id.result_value_text_view);
            this.answerTextView = textView3;
            textView3.setMovementMethod(BetterLinkMovementMethod.getInstance());
        }
    }
}
