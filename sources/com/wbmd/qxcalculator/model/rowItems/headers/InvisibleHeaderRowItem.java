package com.wbmd.qxcalculator.model.rowItems.headers;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewHeaderItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class InvisibleHeaderRowItem extends QxRecyclerViewHeaderItem {
    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
    }

    public int getResourceId() {
        return R.layout.header_row_item_invisible;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return InvisibleHeaderViewHolder.class;
    }

    public static final class InvisibleHeaderViewHolder extends QxRecyclerRowItemViewHolder {
        public InvisibleHeaderViewHolder(View view) {
            super(view);
        }
    }
}
