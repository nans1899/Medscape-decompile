package com.wbmd.qxcalculator.model.rowItems;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.db.DBCategory;
import com.wbmd.qxcalculator.views.ArrowView;

public class GroupRowItem extends CategoryRowItem {
    public boolean highlightGroup = false;

    public GroupRowItem(DBCategory dBCategory, Context context) {
        super(dBCategory);
    }

    public String getTitle() {
        return this.category.overrideName != null ? this.category.overrideName : this.category.getName();
    }

    public int getResourceId() {
        return R.layout.row_item_group;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return GroupItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        GroupItemViewHolder groupItemViewHolder = (GroupItemViewHolder) viewHolder;
        groupItemViewHolder.titleTextView.setText(this.category.overrideName != null ? this.category.overrideName : this.category.getName());
        if (this.isExpanded) {
            groupItemViewHolder.arrowView.setRotation(90.0f);
        } else {
            groupItemViewHolder.arrowView.setRotation(0.0f);
        }
        if (this.highlightGroup) {
            groupItemViewHolder.titleTextView.setTypeface(Typeface.DEFAULT_BOLD);
            groupItemViewHolder.titleTextView.setTextColor(groupItemViewHolder.titleTextView.getResources().getColor(R.color.group_highlight_color));
            groupItemViewHolder.arrowView.setColor(groupItemViewHolder.titleTextView.getResources().getColor(R.color.group_highlight_color_arrow));
            return;
        }
        groupItemViewHolder.titleTextView.setTypeface(Typeface.DEFAULT);
        groupItemViewHolder.titleTextView.setTextColor(groupItemViewHolder.titleTextView.getResources().getColor(R.color.text_main_group));
        groupItemViewHolder.arrowView.setColor(groupItemViewHolder.titleTextView.getResources().getColor(R.color.group_arrow_color));
    }

    public static final class GroupItemViewHolder extends QxRecyclerRowItemViewHolder {
        ArrowView arrowView;
        TextView titleTextView;

        public GroupItemViewHolder(View view) {
            super(view);
            this.arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
            this.titleTextView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
