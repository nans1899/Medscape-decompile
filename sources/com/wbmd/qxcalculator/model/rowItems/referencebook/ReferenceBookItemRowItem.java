package com.wbmd.qxcalculator.model.rowItems.referencebook;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
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
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;

public class ReferenceBookItemRowItem extends QxRecyclerViewRowItem {
    public static int defaultPaddingWidth = 0;
    public static int indentWidth = 0;
    private static boolean isRtl = false;
    public ReferenceBookSectionItem sectionItem;
    private Spanned subTitleSpanned;
    private Spanned titleSpanned;

    public ReferenceBookItemRowItem(ReferenceBookSectionItem referenceBookSectionItem, Context context) {
        this.sectionItem = referenceBookSectionItem;
        if (referenceBookSectionItem.title != null && !referenceBookSectionItem.title.isEmpty()) {
            this.titleSpanned = Html.fromHtml(referenceBookSectionItem.title);
        }
        if (referenceBookSectionItem.subTitle != null && !referenceBookSectionItem.subTitle.isEmpty()) {
            this.subTitleSpanned = Html.fromHtml(referenceBookSectionItem.subTitle);
        }
        if (indentWidth == 0) {
            indentWidth = Math.round(context.getResources().getDimension(R.dimen.indent_width));
        }
        if (defaultPaddingWidth == 0) {
            defaultPaddingWidth = Math.round(context.getResources().getDimension(R.dimen.listview_margin_left));
        }
        isRtl = context.getResources().getBoolean(R.bool.is_rtl);
    }

    public String getId() {
        return this.sectionItem.identifier;
    }

    public int getResourceId() {
        return R.layout.row_item_reference_book_item;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ReferenceBookItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ReferenceBookItemViewHolder referenceBookItemViewHolder = (ReferenceBookItemViewHolder) viewHolder;
        if (referenceBookItemViewHolder.indentLevel != this.indentLevel) {
            referenceBookItemViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(referenceBookItemViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), referenceBookItemViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(referenceBookItemViewHolder.container), referenceBookItemViewHolder.container.getPaddingBottom());
        }
        if (this.titleSpanned != null) {
            referenceBookItemViewHolder.titleTextView.setText(this.titleSpanned);
            referenceBookItemViewHolder.titleTextView.setVisibility(0);
        } else {
            referenceBookItemViewHolder.titleTextView.setVisibility(8);
        }
        if (this.subTitleSpanned != null) {
            referenceBookItemViewHolder.subTitleTextView.setText(this.subTitleSpanned);
            referenceBookItemViewHolder.subTitleTextView.setVisibility(0);
        } else {
            referenceBookItemViewHolder.subTitleTextView.setVisibility(8);
        }
        if (rowPosition.equals(QxRecyclerViewRowItem.RowPosition.TOP) || rowPosition.equals(QxRecyclerViewRowItem.RowPosition.SINGLE)) {
            referenceBookItemViewHolder.separatorView.setVisibility(4);
        } else {
            referenceBookItemViewHolder.separatorView.setVisibility(0);
        }
    }

    public static final class ReferenceBookItemViewHolder extends QxRecyclerRowItemViewHolder {
        ViewGroup container;
        int indentLevel = 0;
        View separatorView;
        TextView subTitleTextView;
        TextView titleTextView;

        public ReferenceBookItemViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.separatorView = view.findViewById(R.id.separator_view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            this.subTitleTextView = (TextView) view.findViewById(R.id.sub_title_text_view);
        }
    }
}
