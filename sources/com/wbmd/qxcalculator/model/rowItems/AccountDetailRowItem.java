package com.wbmd.qxcalculator.model.rowItems;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;

public class AccountDetailRowItem extends QxRecyclerViewRowItem {
    public String detail;
    public String id;
    public String title;
    public AccountDetailType type;

    public enum AccountDetailType {
        PROFESSION,
        SPECIALTY,
        LOCATION,
        ZIP,
        NPI,
        EMAIL
    }

    public AccountDetailRowItem(String str, String str2, AccountDetailType accountDetailType) {
        this.title = str;
        this.detail = str2;
        this.type = accountDetailType;
    }

    public int getResourceId() {
        return R.layout.row_item_account_detail;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return AccountDetailViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        AccountDetailViewHolder accountDetailViewHolder = (AccountDetailViewHolder) viewHolder;
        accountDetailViewHolder.titleTextView.setText(this.title);
        String str = this.detail;
        if (str == null || str.isEmpty()) {
            accountDetailViewHolder.detailTextView.setVisibility(8);
            return;
        }
        accountDetailViewHolder.detailTextView.setVisibility(0);
        accountDetailViewHolder.detailTextView.setText(this.detail);
    }

    public static final class AccountDetailViewHolder extends QxRecyclerRowItemViewHolder {
        TextView detailTextView;
        TextView titleTextView;

        public AccountDetailViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            this.detailTextView = (TextView) view.findViewById(R.id.detail_text_view);
        }
    }
}
