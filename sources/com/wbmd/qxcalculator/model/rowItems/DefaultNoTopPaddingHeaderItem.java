package com.wbmd.qxcalculator.model.rowItems;

import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.rowItems.DefaultHeaderItem;

public class DefaultNoTopPaddingHeaderItem extends DefaultHeaderItem {
    public DefaultNoTopPaddingHeaderItem(String str) {
        super(str);
        this.title = str;
    }

    public int getResourceId() {
        return R.layout.header_item_default_not_top_padding;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        super.onBindData(viewHolder, i, qxIndexPath, rowPosition, qxRecyclerViewAdapter);
        DefaultHeaderItem.DefaultHeaderViewHolder defaultHeaderViewHolder = (DefaultHeaderItem.DefaultHeaderViewHolder) viewHolder;
        if (i == 0) {
            defaultHeaderViewHolder.separatorView.setVisibility(8);
        } else {
            defaultHeaderViewHolder.separatorView.setVisibility(0);
        }
    }
}
