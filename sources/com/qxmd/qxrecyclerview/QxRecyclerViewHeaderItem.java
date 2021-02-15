package com.qxmd.qxrecyclerview;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;

public class QxRecyclerViewHeaderItem extends QxRecyclerViewRowItem {
    public String title;

    public QxRecyclerViewHeaderItem() {
    }

    public QxRecyclerViewHeaderItem(String str) {
        this.title = str;
    }

    public int getResourceId() {
        return R.layout.header_item_default;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return QxRecyclerHeaderItemViewHolderDefault.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        QxRecyclerHeaderItemViewHolderDefault qxRecyclerHeaderItemViewHolderDefault = (QxRecyclerHeaderItemViewHolderDefault) viewHolder;
        if (qxRecyclerHeaderItemViewHolderDefault.textView == null) {
            return;
        }
        if (this.title != null) {
            qxRecyclerHeaderItemViewHolderDefault.textView.setText(this.title);
        } else {
            qxRecyclerHeaderItemViewHolderDefault.textView.setText("");
        }
    }

    public static final class QxRecyclerHeaderItemViewHolderDefault extends QxRecyclerRowItemViewHolder {
        TextView textView;

        public QxRecyclerHeaderItemViewHolderDefault(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
        }
    }
}
