package com.wbmd.qxcalculator.model.rowItems;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.db.DBCategory;
import com.wbmd.qxcalculator.views.ArrowView;

public class SubGroupRowItem extends CategoryRowItem {
    public static int defaultPaddingWidth;
    public static int indentWidth;

    public SubGroupRowItem(DBCategory dBCategory, Context context) {
        super(dBCategory);
        if (indentWidth == 0) {
            indentWidth = Math.round(context.getResources().getDimension(R.dimen.indent_width));
        }
        if (defaultPaddingWidth == 0) {
            defaultPaddingWidth = Math.round(context.getResources().getDimension(R.dimen.listview_margin_left));
        }
    }

    public String getTitle() {
        return this.category.getName();
    }

    public int getResourceId() {
        return R.layout.row_item_sub_group;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return SubGroupViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        SubGroupViewHolder subGroupViewHolder = (SubGroupViewHolder) viewHolder;
        if (subGroupViewHolder.indentLevel != this.indentLevel) {
            subGroupViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(subGroupViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), subGroupViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(subGroupViewHolder.container), subGroupViewHolder.container.getPaddingBottom());
        }
        subGroupViewHolder.titleTextView.setText(this.category.getName());
        if (this.isExpanded) {
            subGroupViewHolder.arrowView.setRotation(90.0f);
        } else {
            subGroupViewHolder.arrowView.setRotation(0.0f);
        }
    }

    public static final class SubGroupViewHolder extends QxRecyclerRowItemViewHolder {
        View arrowView;
        ViewGroup container;
        int indentLevel = 0;
        TextView titleTextView;

        public SubGroupViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
            this.titleTextView = (TextView) view.findViewById(R.id.result_title_text_view);
        }
    }
}
