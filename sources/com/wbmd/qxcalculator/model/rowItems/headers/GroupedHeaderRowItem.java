package com.wbmd.qxcalculator.model.rowItems.headers;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewHeaderItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class GroupedHeaderRowItem extends QxRecyclerViewHeaderItem {
    String title;

    public GroupedHeaderRowItem(String str) {
        this.title = str;
    }

    public int getResourceId() {
        return R.layout.header_row_item_grouped;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return GroupedHeaderViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ((GroupedHeaderViewHolder) viewHolder).textView.setText(this.title);
    }

    public static final class GroupedHeaderViewHolder extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public GroupedHeaderViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.grouped_header_text_view);
        }
    }
}
