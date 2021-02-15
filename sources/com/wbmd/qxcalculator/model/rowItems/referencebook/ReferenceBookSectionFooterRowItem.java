package com.wbmd.qxcalculator.model.rowItems.referencebook;

import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSection;

public class ReferenceBookSectionFooterRowItem extends QxRecyclerViewRowItem {
    public ReferenceBookSection section;
    private Spanned titleSpanned;

    public ReferenceBookSectionFooterRowItem(ReferenceBookSection referenceBookSection) {
        this.section = referenceBookSection;
        this.titleSpanned = Html.fromHtml(referenceBookSection.footer);
    }

    public String getId() {
        return "footer_" + this.section.identifier;
    }

    public int getResourceId() {
        return R.layout.row_item_reference_book_section_footer;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ReferenceBookItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ReferenceBookItemViewHolder referenceBookItemViewHolder = (ReferenceBookItemViewHolder) viewHolder;
        referenceBookItemViewHolder.titleTextView.setText(this.titleSpanned);
        if (rowPosition.equals(QxRecyclerViewRowItem.RowPosition.TOP) || rowPosition.equals(QxRecyclerViewRowItem.RowPosition.SINGLE)) {
            referenceBookItemViewHolder.separatorView.setVisibility(4);
        } else {
            referenceBookItemViewHolder.separatorView.setVisibility(0);
        }
    }

    public static final class ReferenceBookItemViewHolder extends QxRecyclerRowItemViewHolder {
        View separatorView;
        TextView titleTextView;

        public ReferenceBookItemViewHolder(View view) {
            super(view);
            this.separatorView = view.findViewById(R.id.separator_view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
        }
    }
}
