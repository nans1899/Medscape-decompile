package com.wbmd.qxcalculator.model.rowItems;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.DetectHtml;

public class LeafItemRowItem extends QxRecyclerViewRowItem {
    public static int defaultPaddingWidth = 0;
    private static final String htmlTagSearch = "\\<.*\\>.*\\<.*\\>";
    public static int indentWidth;
    public final DBContentItem contentItem;
    private String description;
    private Spanned descriptionSpanned;
    private String footer;
    private Spanned footerSpanned;
    private String title;
    private Spanned titleSpanned;

    public LeafItemRowItem(DBContentItem dBContentItem, Context context) {
        this.contentItem = dBContentItem;
        if (indentWidth == 0) {
            indentWidth = Math.round(context.getResources().getDimension(R.dimen.indent_width));
        }
        if (defaultPaddingWidth == 0) {
            defaultPaddingWidth = Math.round(context.getResources().getDimension(R.dimen.listview_margin_left));
        }
        resetTitle();
        if (dBContentItem.getDescription() != null && !dBContentItem.getDescription().isEmpty()) {
            if (DetectHtml.isHtml(dBContentItem.getDescription())) {
                this.descriptionSpanned = Html.fromHtml(dBContentItem.getDescription());
            } else {
                this.description = dBContentItem.getDescription();
            }
        }
        resetFooter();
    }

    public void resetItem() {
        resetTitle();
        if (this.contentItem.getDescription() != null && !this.contentItem.getDescription().isEmpty()) {
            if (DetectHtml.isHtml(this.contentItem.getDescription())) {
                this.descriptionSpanned = Html.fromHtml(this.contentItem.getDescription());
                this.description = null;
            } else {
                this.description = this.contentItem.getDescription();
                this.descriptionSpanned = null;
            }
        }
        resetFooter();
    }

    private void resetTitle() {
        this.title = null;
        this.titleSpanned = null;
        if (this.contentItem.promotionToUse == null || this.contentItem.promotionToUse.getTitle() == null || this.contentItem.promotionToUse.getTitle().isEmpty()) {
            this.title = this.contentItem.overrideName != null ? this.contentItem.overrideName : this.contentItem.getName();
        } else {
            this.title = this.contentItem.promotionToUse.getTitle();
        }
        if (DetectHtml.isHtml(this.title)) {
            this.titleSpanned = Html.fromHtml(this.title);
            this.title = null;
        }
    }

    private void resetFooter() {
        this.footer = null;
        this.footerSpanned = null;
        if (this.contentItem.promotionToUse == null || this.contentItem.promotionToUse.getFooter() == null || this.contentItem.promotionToUse.getFooter().isEmpty()) {
            if (this.contentItem.getFooter() != null && !this.contentItem.getFooter().isEmpty()) {
                if (DetectHtml.isHtml(this.contentItem.getFooter())) {
                    this.footerSpanned = Html.fromHtml(this.contentItem.getFooter());
                } else {
                    this.footer = this.contentItem.getFooter();
                }
            }
        } else if (DetectHtml.isHtml(this.contentItem.promotionToUse.getFooter())) {
            this.footerSpanned = Html.fromHtml(this.contentItem.promotionToUse.getFooter());
        } else {
            this.footer = this.contentItem.promotionToUse.getFooter();
        }
    }

    public String getTitle() {
        String str = this.title;
        return str != null ? str : this.titleSpanned.toString();
    }

    public int getResourceId() {
        return R.layout.row_item_content_item;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ContentItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ContentItemViewHolder contentItemViewHolder = (ContentItemViewHolder) viewHolder;
        if (contentItemViewHolder.rowItem != null) {
            contentItemViewHolder.rowItem.contentItem.removeIsUpdatingWeakListener(contentItemViewHolder);
        }
        contentItemViewHolder.rowItem = this;
        this.contentItem.addIsUpdatingWeakListener(contentItemViewHolder);
        if (contentItemViewHolder.indentLevel != this.indentLevel) {
            contentItemViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(contentItemViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), contentItemViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(contentItemViewHolder.container), contentItemViewHolder.container.getPaddingBottom());
        }
        TextView textView = contentItemViewHolder.titleTextView;
        CharSequence charSequence = this.titleSpanned;
        if (charSequence == null) {
            charSequence = this.title;
        }
        textView.setText(charSequence);
        if (UserManager.getInstance().getDbUser().getShowItemDescription() == null || !UserManager.getInstance().getDbUser().getShowItemDescription().booleanValue() || (this.descriptionSpanned == null && this.description == null)) {
            contentItemViewHolder.descriptionTextView.setVisibility(8);
        } else {
            contentItemViewHolder.descriptionTextView.setVisibility(0);
            TextView textView2 = contentItemViewHolder.descriptionTextView;
            CharSequence charSequence2 = this.descriptionSpanned;
            if (charSequence2 == null) {
                charSequence2 = this.description;
            }
            textView2.setText(charSequence2);
        }
        if (this.footerSpanned == null && this.footer == null) {
            contentItemViewHolder.footerTextView.setVisibility(8);
        } else {
            contentItemViewHolder.footerTextView.setVisibility(0);
            TextView textView3 = contentItemViewHolder.footerTextView;
            CharSequence charSequence3 = this.footerSpanned;
            if (charSequence3 == null) {
                charSequence3 = this.footer;
            }
            textView3.setText(charSequence3);
        }
        updateUpdatingView(contentItemViewHolder);
    }

    public void updateUpdatingView(ContentItemViewHolder contentItemViewHolder) {
        if ((this.contentItem.getResourcesRequireUpdate() == null || !this.contentItem.getResourcesRequireUpdate().booleanValue()) && (this.contentItem.getRequiresUpdate() == null || !this.contentItem.getRequiresUpdate().booleanValue())) {
            contentItemViewHolder.updatingOverlay.setVisibility(8);
            contentItemViewHolder.progressBar.setVisibility(8);
            return;
        }
        contentItemViewHolder.updatingOverlay.setVisibility(0);
        if (this.contentItem.getIsUpdating()) {
            contentItemViewHolder.progressBar.setVisibility(0);
        } else {
            contentItemViewHolder.progressBar.setVisibility(8);
        }
    }

    public static final class ContentItemViewHolder extends QxRecyclerRowItemViewHolder implements DBContentItem.OnUpdateListener {
        ViewGroup container;
        TextView descriptionTextView;
        TextView footerTextView;
        int indentLevel = 0;
        ProgressBar progressBar;
        LeafItemRowItem rowItem;
        TextView titleTextView;
        View updatingOverlay;

        public ContentItemViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.titleTextView = (TextView) view.findViewById(R.id.result_title_text_view);
            this.descriptionTextView = (TextView) view.findViewById(R.id.description_text_view);
            this.footerTextView = (TextView) view.findViewById(R.id.footer_text_view);
            this.updatingOverlay = view.findViewById(R.id.updating_overlay);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }

        public void isUpdatingStatusChanged(DBContentItem dBContentItem) {
            if (dBContentItem.equals(this.rowItem.contentItem)) {
                this.rowItem.updateUpdatingView(this);
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            try {
                super.finalize();
                if (this.rowItem != null) {
                    this.rowItem.contentItem.removeIsUpdatingWeakListener(this);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
