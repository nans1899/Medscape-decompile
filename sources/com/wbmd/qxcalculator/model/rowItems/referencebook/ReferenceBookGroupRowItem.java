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

public class ReferenceBookGroupRowItem extends QxRecyclerViewRowItem {
    public static int defaultPaddingWidth = 0;
    public static int indentWidth = 0;
    private static boolean isRtl = false;
    private ReferenceBookSectionItem sectionItem;
    private Spanned subTitleSpanned;
    private Spanned titleSpanned;

    public ReferenceBookGroupRowItem(ReferenceBookSectionItem referenceBookSectionItem, Context context) {
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
        return R.layout.row_item_reference_book_group;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ReferenceBookGroupViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ReferenceBookGroupViewHolder referenceBookGroupViewHolder = (ReferenceBookGroupViewHolder) viewHolder;
        if (referenceBookGroupViewHolder.indentLevel != this.indentLevel) {
            referenceBookGroupViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(referenceBookGroupViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), referenceBookGroupViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(referenceBookGroupViewHolder.container), referenceBookGroupViewHolder.container.getPaddingBottom());
        }
        if (this.titleSpanned != null) {
            referenceBookGroupViewHolder.titleTextView.setText(this.titleSpanned);
            referenceBookGroupViewHolder.titleTextView.setVisibility(0);
        } else {
            referenceBookGroupViewHolder.titleTextView.setVisibility(8);
        }
        if (this.subTitleSpanned != null) {
            referenceBookGroupViewHolder.subTitleTextView.setText(this.subTitleSpanned);
            referenceBookGroupViewHolder.subTitleTextView.setVisibility(0);
        } else {
            referenceBookGroupViewHolder.subTitleTextView.setVisibility(8);
        }
        if (this.isExpanded) {
            referenceBookGroupViewHolder.arrowView.setRotation(90.0f);
        } else if (!isRtl) {
            referenceBookGroupViewHolder.arrowView.setRotation(0.0f);
        } else {
            referenceBookGroupViewHolder.arrowView.setRotation(180.0f);
        }
        if (rowPosition.equals(QxRecyclerViewRowItem.RowPosition.TOP) || rowPosition.equals(QxRecyclerViewRowItem.RowPosition.SINGLE)) {
            referenceBookGroupViewHolder.separatorView.setVisibility(4);
        } else {
            referenceBookGroupViewHolder.separatorView.setVisibility(0);
        }
    }

    public static final class ReferenceBookGroupViewHolder extends QxRecyclerRowItemViewHolder {
        View arrowView;
        ViewGroup container;
        int indentLevel = 0;
        View separatorView;
        TextView subTitleTextView;
        TextView titleTextView;

        public ReferenceBookGroupViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.separatorView = view.findViewById(R.id.separator_view);
            this.arrowView = view.findViewById(R.id.arrow_view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            this.subTitleTextView = (TextView) view.findViewById(R.id.sub_title_text_view);
        }
    }
}
