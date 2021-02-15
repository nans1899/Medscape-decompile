package com.wbmd.qxcalculator.model.rowItems.definition;

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
import com.wbmd.qxcalculator.model.contentItems.definition.DefinitionSection;

public class DefinitionSectionRowItem extends QxRecyclerViewRowItem {
    private Spanned body;
    public DefinitionSection definitionSection;
    private Spanned title;

    public DefinitionSectionRowItem(DefinitionSection definitionSection2) {
        this.definitionSection = definitionSection2;
        this.title = Html.fromHtml(definitionSection2.title);
        this.body = Html.fromHtml(definitionSection2.body);
    }

    public int getResourceId() {
        return R.layout.row_item_definition_section;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return DefinitionSectionViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        DefinitionSectionViewHolder definitionSectionViewHolder = (DefinitionSectionViewHolder) viewHolder;
        definitionSectionViewHolder.titleTextView.setText(this.title);
        definitionSectionViewHolder.bodyTextView.setText(this.body);
    }

    public static final class DefinitionSectionViewHolder extends QxRecyclerRowItemViewHolder {
        TextView bodyTextView;
        TextView titleTextView;

        public DefinitionSectionViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.definition_section_title_text_view);
            this.bodyTextView = (TextView) view.findViewById(R.id.definition_section_body_text_view);
        }
    }
}
