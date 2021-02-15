package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class NoResultRowItem extends QxRecyclerViewRowItem {
    public int getResourceId() {
        return R.layout.row_item_no_result;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CalculatorNoResultViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CalculatorNoResultViewHolder calculatorNoResultViewHolder = (CalculatorNoResultViewHolder) viewHolder;
        calculatorNoResultViewHolder.textView.setText(calculatorNoResultViewHolder.textView.getContext().getString(R.string.all_questions_not_answered));
    }

    public static final class CalculatorNoResultViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public CalculatorNoResultViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
