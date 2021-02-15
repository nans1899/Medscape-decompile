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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;

public class MoreInfoSectionItemRowItem extends QxRecyclerViewRowItem {
    public static int defaultPaddingWidth = 0;
    public static int indentWidth = 0;
    private static boolean isRtl = false;
    private static int minRowHeight;
    private Drawable bkgDrawable;
    private int color = 0;
    public ContentItem contentItem;
    private Spanned footerSpanned;
    private Drawable leftDrawable;
    public MoreInfoSectionItem moreInfoSectionItem;
    private Drawable rightDrawable;
    private Spanned subTitleSpanned;
    private Spanned titleSpanned;

    public MoreInfoSectionItemRowItem(MoreInfoSectionItem moreInfoSectionItem2, ContentItem contentItem2, Context context) {
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
        if (moreInfoSectionItem2.leftImage != null) {
            this.leftDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, moreInfoSectionItem2.leftImage, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        if (moreInfoSectionItem2.rightImage != null) {
            this.rightDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, moreInfoSectionItem2.rightImage, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
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
        if (minRowHeight == 0) {
            minRowHeight = Math.round(context.getResources().getDimension(R.dimen.min_row_height));
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
        return R.layout.row_item_more_info_section_item;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return MoreInfoSectionItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        MoreInfoSectionItemViewHolder moreInfoSectionItemViewHolder = (MoreInfoSectionItemViewHolder) viewHolder;
        if (moreInfoSectionItemViewHolder.container.getBackground() != this.bkgDrawable) {
            moreInfoSectionItemViewHolder.container.setBackground(this.bkgDrawable);
        }
        if (moreInfoSectionItemViewHolder.indentLevel != this.indentLevel) {
            moreInfoSectionItemViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(moreInfoSectionItemViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), moreInfoSectionItemViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(moreInfoSectionItemViewHolder.container), moreInfoSectionItemViewHolder.container.getPaddingBottom());
        }
        if (this.titleSpanned != null) {
            moreInfoSectionItemViewHolder.titleTextView.setText(this.titleSpanned);
            moreInfoSectionItemViewHolder.titleTextView.setVisibility(0);
            moreInfoSectionItemViewHolder.titleTextView.setTextColor(this.color);
        } else {
            moreInfoSectionItemViewHolder.titleTextView.setVisibility(8);
        }
        if (this.subTitleSpanned != null) {
            moreInfoSectionItemViewHolder.subTitleTextView.setText(this.subTitleSpanned);
            moreInfoSectionItemViewHolder.subTitleTextView.setVisibility(0);
        } else {
            moreInfoSectionItemViewHolder.subTitleTextView.setVisibility(8);
        }
        if (this.footerSpanned != null) {
            moreInfoSectionItemViewHolder.footerTextView.setText(this.footerSpanned);
            moreInfoSectionItemViewHolder.footerTextView.setVisibility(0);
        } else {
            moreInfoSectionItemViewHolder.footerTextView.setVisibility(8);
        }
        if (this.leftDrawable != null) {
            moreInfoSectionItemViewHolder.leftImageView.setImageDrawable(this.leftDrawable);
            moreInfoSectionItemViewHolder.leftImageView.setVisibility(0);
        } else {
            moreInfoSectionItemViewHolder.leftImageView.setVisibility(8);
        }
        if (this.rightDrawable != null) {
            moreInfoSectionItemViewHolder.rightImageView.setImageDrawable(this.rightDrawable);
            moreInfoSectionItemViewHolder.rightImageView.setVisibility(0);
        } else {
            moreInfoSectionItemViewHolder.rightImageView.setVisibility(8);
        }
        if (rowPosition.equals(QxRecyclerViewRowItem.RowPosition.TOP) || rowPosition.equals(QxRecyclerViewRowItem.RowPosition.SINGLE)) {
            moreInfoSectionItemViewHolder.separatorView.setVisibility(4);
        } else {
            moreInfoSectionItemViewHolder.separatorView.setVisibility(0);
        }
    }

    public static final class MoreInfoSectionItemViewHolder extends QxRecyclerRowItemViewHolder {
        ViewGroup container;
        TextView footerTextView;
        int indentLevel = 0;
        ImageView leftImageView;
        ImageView rightImageView;
        View separatorView;
        TextView subTitleTextView;
        TextView titleTextView;

        public MoreInfoSectionItemViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.separatorView = view.findViewById(R.id.separator_view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            this.subTitleTextView = (TextView) view.findViewById(R.id.sub_title_text_view);
            this.footerTextView = (TextView) view.findViewById(R.id.footer_text_view);
            this.leftImageView = (ImageView) view.findViewById(R.id.left_image_view);
            this.rightImageView = (ImageView) view.findViewById(R.id.right_image_view);
        }
    }
}
