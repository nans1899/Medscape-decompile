package com.wbmd.qxcalculator.model.rowItems.calculator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;

public class ExtraSectionHeaderRowItem extends QxRecyclerViewRowItem {
    public static int defaultPaddingWidth = 0;
    public static int indentWidth = 0;
    private static boolean isRtl = false;
    private static int minRowHeight;
    private int bkgColor = -1;
    private int color = ViewCompat.MEASURED_STATE_MASK;
    public ContentItem contentItem;
    public ExtraSectionItem extraSectionItem;
    private Spanned footerSpanned;
    private Drawable leftDrawable;
    private Drawable rightDrawable;
    private int sepColor;
    private Spanned subTitleSpanned;
    private Spanned titleSpanned;

    public ExtraSectionHeaderRowItem(ExtraSectionItem extraSectionItem2, ContentItem contentItem2, Context context) {
        this.extraSectionItem = extraSectionItem2;
        this.contentItem = contentItem2;
        if (extraSectionItem2.title != null && !extraSectionItem2.title.isEmpty()) {
            this.titleSpanned = Html.fromHtml(extraSectionItem2.title);
        }
        if (extraSectionItem2.subTitle != null && !extraSectionItem2.subTitle.isEmpty()) {
            this.subTitleSpanned = Html.fromHtml(extraSectionItem2.subTitle);
        }
        if (extraSectionItem2.footer != null && !extraSectionItem2.footer.isEmpty()) {
            this.footerSpanned = Html.fromHtml(extraSectionItem2.footer);
        }
        if (extraSectionItem2.leftImage != null) {
            this.leftDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, extraSectionItem2.leftImage, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        if (extraSectionItem2.rightImage != null) {
            this.rightDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, extraSectionItem2.rightImage, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        if (extraSectionItem2.backgroundColor != null && !extraSectionItem2.backgroundColor.isEmpty()) {
            try {
                this.bkgColor = Color.parseColor(extraSectionItem2.backgroundColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = this.bkgColor;
        if (i == -1) {
            this.sepColor = context.getResources().getColor(R.color.list_view_divider_color);
        } else {
            this.sepColor = i;
        }
        if (extraSectionItem2.color != null && !extraSectionItem2.color.isEmpty()) {
            this.color = Color.parseColor(extraSectionItem2.color);
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
        return R.layout.row_item_extra_section_header;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return ExtraSectionItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ExtraSectionItemViewHolder extraSectionItemViewHolder = (ExtraSectionItemViewHolder) viewHolder;
        extraSectionItemViewHolder.bkgContainer.setBackgroundColor(this.bkgColor);
        if (extraSectionItemViewHolder.indentLevel != this.indentLevel) {
            extraSectionItemViewHolder.indentLevel = this.indentLevel;
            ViewCompat.setPaddingRelative(extraSectionItemViewHolder.container, defaultPaddingWidth + (this.indentLevel * indentWidth), extraSectionItemViewHolder.container.getPaddingTop(), ViewCompat.getPaddingEnd(extraSectionItemViewHolder.container), extraSectionItemViewHolder.container.getPaddingBottom());
        }
        if (this.titleSpanned != null) {
            extraSectionItemViewHolder.titleTextView.setText(this.titleSpanned);
            extraSectionItemViewHolder.titleTextView.setVisibility(0);
            extraSectionItemViewHolder.titleTextView.setTextColor(this.color);
        } else {
            extraSectionItemViewHolder.titleTextView.setVisibility(8);
        }
        if (this.subTitleSpanned != null) {
            extraSectionItemViewHolder.subTitleTextView.setText(this.subTitleSpanned);
            extraSectionItemViewHolder.subTitleTextView.setVisibility(0);
        } else {
            extraSectionItemViewHolder.subTitleTextView.setVisibility(8);
        }
        if (this.footerSpanned != null) {
            extraSectionItemViewHolder.footerTextView.setText(this.footerSpanned);
            extraSectionItemViewHolder.footerTextView.setVisibility(0);
        } else {
            extraSectionItemViewHolder.footerTextView.setVisibility(8);
        }
        if (this.leftDrawable != null) {
            extraSectionItemViewHolder.leftImageView.setImageDrawable(this.leftDrawable);
            extraSectionItemViewHolder.leftImageView.setVisibility(0);
        } else {
            extraSectionItemViewHolder.leftImageView.setVisibility(8);
        }
        if (this.rightDrawable != null) {
            extraSectionItemViewHolder.rightImageView.setImageDrawable(this.rightDrawable);
            extraSectionItemViewHolder.rightImageView.setVisibility(0);
        } else {
            extraSectionItemViewHolder.rightImageView.setVisibility(8);
        }
        if (rowPosition.equals(QxRecyclerViewRowItem.RowPosition.TOP) || rowPosition.equals(QxRecyclerViewRowItem.RowPosition.SINGLE) || this.bkgColor != -1) {
            extraSectionItemViewHolder.separatorView.setVisibility(4);
        } else {
            extraSectionItemViewHolder.separatorView.setVisibility(0);
        }
    }

    public static final class ExtraSectionItemViewHolder extends QxRecyclerRowItemViewHolder {
        ViewGroup bkgContainer;
        ViewGroup container;
        TextView footerTextView;
        int indentLevel = 0;
        ImageView leftImageView;
        ImageView rightImageView;
        View separatorView;
        TextView subTitleTextView;
        TextView titleTextView;

        public ExtraSectionItemViewHolder(View view) {
            super(view);
            this.container = (ViewGroup) view.findViewById(R.id.container_view);
            this.bkgContainer = (ViewGroup) view.findViewById(R.id.bkg_container_view);
            this.separatorView = view.findViewById(R.id.separator_view);
            this.titleTextView = (TextView) view.findViewById(R.id.title_text_view);
            this.subTitleTextView = (TextView) view.findViewById(R.id.sub_title_text_view);
            this.footerTextView = (TextView) view.findViewById(R.id.footer_text_view);
            this.leftImageView = (ImageView) view.findViewById(R.id.left_image_view);
            this.rightImageView = (ImageView) view.findViewById(R.id.right_image_view);
        }
    }
}
