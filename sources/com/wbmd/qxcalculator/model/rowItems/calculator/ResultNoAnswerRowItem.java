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

public class ResultNoAnswerRowItem extends QxRecyclerViewRowItem {
    public Result result;
    private Spanned titleSpanned;

    public ResultNoAnswerRowItem(Result result2) {
        this.result = result2;
        if (result2.titleFormulaResult != null) {
            this.titleSpanned = Html.fromHtml(result2.titleFormulaResult);
        } else if (result2.title != null) {
            this.titleSpanned = Html.fromHtml(result2.title);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_result_no_answer;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CalculatorResultNoAnswerViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CalculatorResultNoAnswerViewHolder calculatorResultNoAnswerViewHolder = (CalculatorResultNoAnswerViewHolder) viewHolder;
        if (this.titleSpanned != null) {
            calculatorResultNoAnswerViewHolder.textView.setText(this.titleSpanned);
            calculatorResultNoAnswerViewHolder.textView.setVisibility(0);
            return;
        }
        calculatorResultNoAnswerViewHolder.textView.setVisibility(8);
    }

    public static final class CalculatorResultNoAnswerViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public CalculatorResultNoAnswerViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
