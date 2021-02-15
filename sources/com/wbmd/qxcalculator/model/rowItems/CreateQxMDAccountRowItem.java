package com.wbmd.qxcalculator.model.rowItems;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class CreateQxMDAccountRowItem extends QxRecyclerViewRowItem {
    public String getTitle() {
        return "";
    }

    public CreateQxMDAccountRowItem(String str) {
        this.tag = str;
    }

    public int getResourceId() {
        return R.layout.row_item_create_qxmd_account;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return CreateQxMDAccountViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        CreateQxMDAccountViewHolder createQxMDAccountViewHolder = (CreateQxMDAccountViewHolder) viewHolder;
    }

    public static int getRadioButtonResourceId() {
        return R.id.radio_button;
    }

    public static final class CreateQxMDAccountViewHolder extends QxRecyclerRowItemViewHolder {
        View separatorView;

        public CreateQxMDAccountViewHolder(View view) {
            super(view);
        }
    }
}
