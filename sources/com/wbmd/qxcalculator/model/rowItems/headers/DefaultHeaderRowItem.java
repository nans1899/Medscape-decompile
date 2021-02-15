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

public class DefaultHeaderRowItem extends QxRecyclerViewHeaderItem {
    protected int backgroundColor = 0;
    public boolean hideSeparator = false;
    protected boolean keepSeparatorSpacer = false;
    protected String title;

    public DefaultHeaderRowItem(String str) {
        this.title = str;
    }

    public DefaultHeaderRowItem(String str, int i) {
        this.title = str;
        this.backgroundColor = i;
    }

    public DefaultHeaderRowItem(String str, boolean z) {
        this.title = str;
        this.hideSeparator = z;
        this.keepSeparatorSpacer = false;
    }

    public DefaultHeaderRowItem(String str, boolean z, boolean z2) {
        this.title = str;
        this.hideSeparator = z;
        this.keepSeparatorSpacer = z2;
    }

    public int getResourceId() {
        return R.layout.header_default_row_item;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return DefaultHeaderViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        DefaultHeaderViewHolder defaultHeaderViewHolder = (DefaultHeaderViewHolder) viewHolder;
        defaultHeaderViewHolder.textView.setText(this.title);
        defaultHeaderViewHolder.separatorView.setVisibility(this.hideSeparator ? this.keepSeparatorSpacer ? 4 : 8 : 0);
        defaultHeaderViewHolder.textView.setBackgroundColor(this.backgroundColor);
    }

    public static final class DefaultHeaderViewHolder extends QxRecyclerRowItemViewHolder {
        View separatorView;
        TextView textView;

        public DefaultHeaderViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.text_view);
            this.separatorView = view.findViewById(R.id.separator);
        }
    }
}
