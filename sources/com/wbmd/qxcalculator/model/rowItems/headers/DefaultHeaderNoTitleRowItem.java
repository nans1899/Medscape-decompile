package com.wbmd.qxcalculator.model.rowItems.headers;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewHeaderItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class DefaultHeaderNoTitleRowItem extends QxRecyclerViewHeaderItem {
    public boolean hideSeparator = false;

    public DefaultHeaderNoTitleRowItem() {
    }

    public DefaultHeaderNoTitleRowItem(int i) {
    }

    public DefaultHeaderNoTitleRowItem(boolean z) {
        this.hideSeparator = z;
    }

    public int getResourceId() {
        return R.layout.header_default_no_title_row_item;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return DefaultHeaderViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ((DefaultHeaderViewHolder) viewHolder).separatorView.setVisibility(this.hideSeparator ? 8 : 0);
    }

    public static final class DefaultHeaderViewHolder extends QxRecyclerRowItemViewHolder {
        View separatorView;

        public DefaultHeaderViewHolder(View view) {
            super(view);
            this.separatorView = view.findViewById(R.id.separator);
        }
    }
}
