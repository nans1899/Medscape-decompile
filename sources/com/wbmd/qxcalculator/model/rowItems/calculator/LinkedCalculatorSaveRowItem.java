package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class LinkedCalculatorSaveRowItem extends QxRecyclerViewRowItem {
    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
    }

    public int getResourceId() {
        return R.layout.row_item_linked_calculator_save;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return LinkedCalculatorSaveViewHolder.class;
    }

    public static final class LinkedCalculatorSaveViewHolder extends QxRecyclerRowItemViewHolder {
        public LinkedCalculatorSaveViewHolder(View view) {
            super(view);
        }
    }
}
