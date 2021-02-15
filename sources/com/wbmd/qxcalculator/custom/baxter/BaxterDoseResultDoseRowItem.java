package com.wbmd.qxcalculator.custom.baxter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;

public class BaxterDoseResultDoseRowItem extends QxRecyclerViewRowItem {
    private static final String[] optionsRomanNumeral = {"i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix", "x", "xi", "xii", "xiii", "xiv", "xv"};
    private int backgroundColor;
    public BaxterDose baxterDose;
    public ContentItem contentItem;
    private Drawable dayDrawable;
    private Spanned dayTitleSpanned;
    public int index;
    private Drawable nightDrawable;
    private Spanned nightTitleSpanned;
    private boolean showBottomSeparator = false;

    public BaxterDoseResultDoseRowItem(BaxterDose baxterDose2, int i, boolean z, ContentItem contentItem2) {
        String str;
        this.baxterDose = baxterDose2;
        this.index = i;
        this.contentItem = contentItem2;
        this.showBottomSeparator = z;
        this.nightTitleSpanned = Html.fromHtml(baxterDose2.nightTitle);
        this.dayTitleSpanned = Html.fromHtml(baxterDose2.dayTitle);
        String str2 = "icodextrin.png";
        if (baxterDose2.nightType.equalsIgnoreCase("dextrose15")) {
            str = "dextrose15.png";
        } else if (baxterDose2.nightType.equalsIgnoreCase("dextrose25")) {
            str = "dextrose25.png";
        } else if (baxterDose2.nightType.equalsIgnoreCase("Icodextrin")) {
            str = str2;
        } else {
            boolean equalsIgnoreCase = baxterDose2.nightType.equalsIgnoreCase("none");
            str = null;
        }
        if (str != null) {
            this.nightDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, str, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        if (baxterDose2.dayType.equalsIgnoreCase("dextrose15")) {
            str2 = "dextrose15.png";
        } else if (baxterDose2.dayType.equalsIgnoreCase("dextrose25")) {
            str2 = "dextrose25.png";
        } else if (!baxterDose2.dayType.equalsIgnoreCase("Icodextrin")) {
            boolean equalsIgnoreCase2 = baxterDose2.dayType.equalsIgnoreCase("none");
            str2 = null;
        }
        if (str2 != null) {
            this.dayDrawable = FileHelper.getInstance().getScaledDrawable(contentItem2, str2, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        if (i % 2 == 0) {
            this.backgroundColor = -1;
        } else {
            this.backgroundColor = Color.parseColor("#e8ebed");
        }
    }

    public int getResourceId() {
        return R.layout.row_item_baxter_dose_result_dose;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return BaxterDoseResultDoseViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        BaxterDoseResultDoseViewHolder baxterDoseResultDoseViewHolder = (BaxterDoseResultDoseViewHolder) viewHolder;
        baxterDoseResultDoseViewHolder.optionTextView.setText(optionsRomanNumeral[this.index]);
        baxterDoseResultDoseViewHolder.nightTextView.setText(this.nightTitleSpanned);
        baxterDoseResultDoseViewHolder.dayTextView.setText(this.dayTitleSpanned);
        if (this.nightDrawable != null) {
            baxterDoseResultDoseViewHolder.nightImageView.setVisibility(0);
            baxterDoseResultDoseViewHolder.nightImageView.setImageDrawable(this.nightDrawable);
        } else {
            baxterDoseResultDoseViewHolder.nightImageView.setVisibility(8);
        }
        if (this.dayDrawable != null) {
            baxterDoseResultDoseViewHolder.dayImageView.setVisibility(0);
            baxterDoseResultDoseViewHolder.dayImageView.setImageDrawable(this.dayDrawable);
        } else {
            baxterDoseResultDoseViewHolder.dayImageView.setVisibility(8);
        }
        if (this.showBottomSeparator) {
            baxterDoseResultDoseViewHolder.bottomSeparator.setVisibility(0);
        } else {
            baxterDoseResultDoseViewHolder.bottomSeparator.setVisibility(8);
        }
        baxterDoseResultDoseViewHolder.containerView.setBackgroundColor(this.backgroundColor);
    }

    public static final class BaxterDoseResultDoseViewHolder extends QxRecyclerRowItemViewHolder {
        View bottomSeparator;
        View containerView;
        ImageView dayImageView;
        TextView dayTextView;
        ImageView nightImageView;
        TextView nightTextView;
        TextView optionTextView;

        public BaxterDoseResultDoseViewHolder(View view) {
            super(view);
            this.containerView = view.findViewById(R.id.container_view);
            this.optionTextView = (TextView) view.findViewById(R.id.option_number_text_view);
            this.dayTextView = (TextView) view.findViewById(R.id.day_text_view);
            this.nightTextView = (TextView) view.findViewById(R.id.night_text_view);
            this.nightImageView = (ImageView) view.findViewById(R.id.night_image_view);
            this.dayImageView = (ImageView) view.findViewById(R.id.day_image_view);
            this.bottomSeparator = view.findViewById(R.id.bottom_separator);
        }
    }
}
