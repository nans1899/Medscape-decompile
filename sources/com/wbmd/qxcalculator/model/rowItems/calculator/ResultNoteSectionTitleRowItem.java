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

public class ResultNoteSectionTitleRowItem extends QxRecyclerViewRowItem {
    public Result result;
    private Spanned titleSpanned;

    public ResultNoteSectionTitleRowItem(Result result2) {
        this.result = result2;
        if (result2.titleFormulaResult != null) {
            this.titleSpanned = Html.fromHtml(result2.titleFormulaResult);
        } else if (result2.title != null) {
            this.titleSpanned = Html.fromHtml(result2.title);
        }
    }

    public int getResourceId() {
        return R.layout.row_item_result_note_section_title;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CalculatorResultNoteSectionViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CalculatorResultNoteSectionViewHolder calculatorResultNoteSectionViewHolder = (CalculatorResultNoteSectionViewHolder) viewHolder;
        if (this.titleSpanned != null) {
            calculatorResultNoteSectionViewHolder.textView.setText(this.titleSpanned);
            calculatorResultNoteSectionViewHolder.textView.setVisibility(0);
            return;
        }
        calculatorResultNoteSectionViewHolder.textView.setVisibility(8);
    }

    public static final class CalculatorResultNoteSectionViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public CalculatorResultNoteSectionViewHolder(View view) {
            super(view);
            TextView textView2 = (TextView) view.findViewById(R.id.note_title_text_view);
            this.textView = textView2;
            textView2.setMovementMethod(BetterLinkMovementMethod.getInstance());
        }
    }
}
