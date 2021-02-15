package com.wbmd.qxcalculator.model.rowItems;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
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
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;

public class MoreInfoSectionGroupRowItem extends QxRecyclerViewRowItem {
    public static int defaultPaddingWidth = 0;
    public static int indentWidth = 0;
    private static boolean isRtl = false;
    private Drawable bkgDrawable;
    private int color = 0;
    public ContentItem contentItem;
    private Spanned footerSpanned;
    public MoreInfoSectionItem moreInfoSectionItem;
    private Spanned subTitleSpanned;
    private Spanned titleSpanned;

    public MoreInfoSectionGroupRowItem(MoreInfoSectionItem moreInfoSectionItem2, ContentItem contentItem2, Context context) {
        int i;
        this.moreInfoSectionItem = moreInfoSectionItem2;
        this.contentItem = contentItem2;
        if (moreInfoSectionItem2.title != null && !moreInfoSectionItem2.title.isEmpty()) {
            this.titleSpanned = Html.fromHtml(moreInfoSectionItem2.title);
        }
        if (moreInfoSectionItem2.subTitle != null && !moreInfoSectionItem2.subTitle.isEmpty()) {
            this.subTitleSpanned = Html.fromHtml(moreInfoSectionItem2.subTitle);
        }
        if (moreInfoSectionItem2.footer != null && !moreInfoSectionItem2.footer.isEmpty()) {
            this.footerSpanned = Html.fromHtml(moreInfoSectionItem2.footer);
        }
        if (moreInfoSectionItem2.backgroundColor == null || moreInfoSectionItem2.backgroundColor.isEmpty()) {
            this.bkgDrawable = context.getResources().getDrawable(R.drawable.selector_white_background);
        } else {
            try {
                i = Color.parseColor(moreInfoSectionItem2.backgroundColor);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                i = 0;
            }
            if (i == 0) {
                this.bkgDrawable = context.getResources().getDrawable(R.drawable.selector_white_background);
            } else if (Build.VERSION.SDK_INT >= 21) {
                RippleDrawable rippleDrawable = (RippleDrawable) context.getResources().getDrawable(R.drawable.selector_white_background);
                rippleDrawable.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
                this.bkgDrawable = rippleDrawable;
            } else {
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(context.getResources().getColor(R.color.list_selection_color)));
                stateListDrawable.addState(new int[0], new ColorDrawable(i));
                this.bkgDrawable = stateListDrawable;
            }
        }
        if (moreInfoSectionItem2.color == null || moreInfoSectionItem2.color.isEmpty()) {
            this.color = ViewCompat.MEASURED_STATE_MASK;
        } else {
            this.color = Color.parseColor(moreInfoSectionItem2.color);
        }
        if (indentWidth == 0) {
            indentWidth = Math.round(context.getResources().getDimension(R.dimen.indent_width));
        }
        if (defaultPaddingWidth == 0) {
            defaultPaddingWidth = Math.round(context.getResources().getDimension(R.dimen.listview_margin_left));
        }
        isRtl = context.getResources().getBoolean(R.bool.is_rtl);
    }

    public int getResourceId() {
        return R.layout.row_item_more_info_section_group;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return MoreInfoSectionGroupViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        MoreInfoSectionGroupViewHolder moreInfoSectionGroupViewHolder = (MoreInfoSectionGroupViewHolder) viewHolder;
        if (moreInfoSectionGroupViewHolder.container.getBackground() != this.bkgDrawable) {
            moreInfoSectionGroupViewHolder.container.setBackground(this.bkgDrawable);
        }
        if (moreInfoSectionGroupViewHolder.indentLevel != this.indentLevel) {
            moreInfoSectionGroupViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(moreInfoSectionGroupViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), moreInfoSectionGroupViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(moreInfoSectionGroupViewHolder.container), moreInfoSectionGroupViewHolder.container.getPaddingBottom());
        }
        if (this.titleSpanned != null) {
            moreInfoSectionGroupViewHolder.titleTextView.setText(this.titleSpanned);
            moreInfoSectionGroupViewHolder.titleTextView.setVisibility(0);
            moreInfoSectionGroupViewHolder.titleTextView.setTextColor(this.color);
        } else {
            moreInfoSectionGroupViewHolder.titleTextView.setVisibility(8);
        }
        if (this.subTitleSpanned != null) {
            moreInfoSectionGroupViewHolder.subTitleTextView.setText(this.subTitleSpanned);
            moreInfoSectionGroupViewHolder.subTitleTextView.setVisibility(0);
        } else {
            moreInfoSectionGroupViewHolder.subTitleTextView.setVisibility(8);
        }
        if (this.footerSpanned != null) {
            moreInfoSectionGroupViewHolder.footerTextView.setText(this.footerSpanned);
            moreInfoSectionGroupViewHolder.footerTextView.setVisibility(0);
        } else {
            moreInfoSectionGroupViewHolder.footerTextView.setVisibility(8);
        }
        if (this.isExpanded) {
            moreInfoSectionGroupViewHolder.arrowView.setRotation(90.0f);
        } else if (!isRtl) {
            moreInfoSectionGroupViewHolder.arrowView.setRotation(0.0f);
        } else {
            moreInfoSectionGroupViewHolder.arrowView.setRotation(180.0f);
        }
        if (rowPosition.equals(QxRecyclerViewRowItem.RowPosition.TOP) || rowPosition.equals(QxRecyclerViewRowItem.RowPosition.SINGLE)) {
            moreInfoSectionGroupViewHolder.separatorView.setVisibility(4);
        } else {
            moreInfoSectionGroupViewHolder.separatorView.setVisibility(0);
        }
    }

    public static final class MoreInfoSectionGroupViewHolder extends QxRecyclerRowItemViewHolder {
        View arrowView;
        ViewGroup container;
        TextView footerTextView;
        int indentLevel = 0;
        View separatorView;
        TextView subTitleTextView;
        TextView titleTextView;

        public MoreInfoSectionGroupViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.separatorView = view.findViewById(R.id.separator_view);
            this.arrowView = view.findViewById(R.id.arrow_view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            this.subTitleTextView = (TextView) view.findViewById(R.id.sub_title_text_view);
            this.footerTextView = (TextView) view.findViewById(R.id.footer_text_view);
        }
    }
}
