package com.wbmd.qxcalculator.model.rowItems;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class LabelRowItem extends QxRecyclerViewRowItem {
    private QxRecyclerViewAdapter adapter;
    public String title;

    public LabelRowItem(String str, Object obj) {
        this.title = str;
        if (obj instanceof String) {
            this.tag = (String) obj;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public int getResourceId() {
        return R.layout.row_item_label;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return LabelViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        this.adapter = qxRecyclerViewAdapter;
        ((LabelViewHolder) viewHolder).labelTextView.setText(this.title);
    }

    private void onDeleteButtonPressed(View view) {
        QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
        qxRecyclerViewAdapter.onAccessoryViewClicked(view, qxRecyclerViewAdapter.getPositionForRowItem(this));
    }

    public static final class LabelViewHolder extends QxRecyclerRowItemViewHolder {
        TextView labelTextView;

        public LabelViewHolder(View view) {
            super(view);
            this.labelTextView = (TextView) view.findViewById(R.id.title_text_view);
        }
    }
}
