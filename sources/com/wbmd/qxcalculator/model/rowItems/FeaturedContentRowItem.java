package com.wbmd.qxcalculator.model.rowItems;

import android.graphics.drawable.Drawable;
import android.text.Spanned;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxIndexPath;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAd;
import com.wbmd.qxcalculator.views.BetterLinkTextView;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class FeaturedContentRowItem extends QxRecyclerViewRowItem {
    private Drawable bkgDrawable;
    private String body;
    private Spanned bodySpanned;
    public DBContentItem dbContentItem;
    private String disclaimer;
    private Spanned disclaimerSpanned;
    public boolean errorRetrievingDrawable;
    public DBFeaturedContentAd featuredContentAd;
    private String footer;
    private Spanned footerSpanned;
    private Drawable imageDrawable;
    private String title;
    private Spanned titleSpanned;

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0169  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FeaturedContentRowItem(com.wbmd.qxcalculator.model.db.DBFeaturedContentAd r7, android.content.Context r8) {
        /*
            r6 = this;
            r6.<init>()
            r6.featuredContentAd = r7
            com.wbmd.qxcalculator.model.db.DBContentItem r0 = r7.getContentItem()
            r6.dbContentItem = r0
            java.lang.String r0 = r7.getName()
            r1 = 0
            if (r0 == 0) goto L_0x0044
            java.lang.String r0 = r7.getName()
            java.lang.String r2 = "$name"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x002f
            com.wbmd.qxcalculator.model.db.DBContentItem r0 = r6.dbContentItem
            java.lang.String r0 = r0.getName()
            if (r0 == 0) goto L_0x002d
            com.wbmd.qxcalculator.model.db.DBContentItem r0 = r6.dbContentItem
            java.lang.String r0 = r0.getName()
            goto L_0x0033
        L_0x002d:
            r0 = r1
            goto L_0x0033
        L_0x002f:
            java.lang.String r0 = r7.getName()
        L_0x0033:
            if (r0 == 0) goto L_0x0044
            boolean r2 = com.wbmd.qxcalculator.util.DetectHtml.isHtml(r0)
            if (r2 == 0) goto L_0x0042
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            r6.titleSpanned = r0
            goto L_0x0044
        L_0x0042:
            r6.title = r0
        L_0x0044:
            java.lang.String r0 = r7.getDescription()
            if (r0 == 0) goto L_0x007a
            java.lang.String r0 = r7.getDescription()
            java.lang.String r2 = "$description"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0065
            com.wbmd.qxcalculator.model.db.DBContentItem r0 = r6.dbContentItem
            java.lang.String r0 = r0.getDescription()
            if (r0 == 0) goto L_0x0069
            com.wbmd.qxcalculator.model.db.DBContentItem r0 = r6.dbContentItem
            java.lang.String r1 = r0.getDescription()
            goto L_0x0069
        L_0x0065:
            java.lang.String r1 = r7.getDescription()
        L_0x0069:
            if (r1 == 0) goto L_0x007a
            boolean r0 = com.wbmd.qxcalculator.util.DetectHtml.isHtml(r1)
            if (r0 == 0) goto L_0x0078
            android.text.Spanned r0 = android.text.Html.fromHtml(r1)
            r6.bodySpanned = r0
            goto L_0x007a
        L_0x0078:
            r6.body = r1
        L_0x007a:
            java.lang.String r0 = r7.getDisclaimer()
            if (r0 == 0) goto L_0x0095
            java.lang.String r0 = r7.getDisclaimer()
            if (r0 == 0) goto L_0x0095
            boolean r1 = com.wbmd.qxcalculator.util.DetectHtml.isHtml(r0)
            if (r1 == 0) goto L_0x0093
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            r6.disclaimerSpanned = r0
            goto L_0x0095
        L_0x0093:
            r6.disclaimer = r0
        L_0x0095:
            java.lang.String r0 = r7.getFooter()
            if (r0 == 0) goto L_0x00b7
            java.lang.String r0 = r7.getFooter()
            boolean r0 = com.wbmd.qxcalculator.util.DetectHtml.isHtml(r0)
            if (r0 == 0) goto L_0x00b0
            java.lang.String r0 = r7.getFooter()
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            r6.footerSpanned = r0
            goto L_0x00dd
        L_0x00b0:
            java.lang.String r0 = r7.getFooter()
            r6.footer = r0
            goto L_0x00dd
        L_0x00b7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "<i>"
            r0.append(r1)
            android.content.res.Resources r1 = r8.getResources()
            int r2 = com.wbmd.qxcalculator.R.string.featured_content_footer
            java.lang.String r1 = r1.getString(r2)
            r0.append(r1)
            java.lang.String r1 = "</i>"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.text.Spanned r0 = android.text.Html.fromHtml(r0)
            r6.footerSpanned = r0
        L_0x00dd:
            java.lang.String r0 = r7.getImageSource()
            r1 = 1
            if (r0 == 0) goto L_0x00fa
            com.wbmd.qxcalculator.managers.FileHelper r0 = com.wbmd.qxcalculator.managers.FileHelper.getInstance()
            com.wbmd.qxcalculator.model.db.DBContentItem r2 = r6.dbContentItem
            java.lang.String r3 = r7.getImageSource()
            r4 = 0
            android.graphics.drawable.BitmapDrawable r0 = r0.getScaledDrawable((com.wbmd.qxcalculator.model.db.DBContentItem) r2, (java.lang.String) r3, (double) r4)
            r6.imageDrawable = r0
            if (r0 != 0) goto L_0x00fa
            r6.errorRetrievingDrawable = r1
        L_0x00fa:
            java.lang.String r0 = r7.getBackgroundColor()
            r2 = 0
            if (r0 == 0) goto L_0x010e
            java.lang.String r7 = r7.getBackgroundColor()     // Catch:{ IllegalArgumentException -> 0x010a }
            int r7 = android.graphics.Color.parseColor(r7)     // Catch:{ IllegalArgumentException -> 0x010a }
            goto L_0x010f
        L_0x010a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x010e:
            r7 = 0
        L_0x010f:
            if (r7 != 0) goto L_0x011b
            android.content.res.Resources r7 = r8.getResources()
            int r0 = com.wbmd.qxcalculator.R.color.featured_content_bkg
            int r7 = r7.getColor(r0)
        L_0x011b:
            android.graphics.drawable.Drawable r0 = r6.imageDrawable
            if (r0 != 0) goto L_0x0169
            int r0 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            if (r0 < r3) goto L_0x013e
            android.content.res.Resources r8 = r8.getResources()
            int r0 = com.wbmd.qxcalculator.R.drawable.selector_white_background
            android.graphics.drawable.Drawable r8 = r8.getDrawable(r0)
            android.graphics.drawable.RippleDrawable r8 = (android.graphics.drawable.RippleDrawable) r8
            android.graphics.PorterDuffColorFilter r0 = new android.graphics.PorterDuffColorFilter
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_IN
            r0.<init>(r7, r1)
            r8.setColorFilter(r0)
            r6.bkgDrawable = r8
            goto L_0x0170
        L_0x013e:
            android.graphics.drawable.StateListDrawable r0 = new android.graphics.drawable.StateListDrawable
            r0.<init>()
            int[] r1 = new int[r1]
            r3 = 16842919(0x10100a7, float:2.3694026E-38)
            r1[r2] = r3
            android.graphics.drawable.ColorDrawable r3 = new android.graphics.drawable.ColorDrawable
            android.content.res.Resources r8 = r8.getResources()
            int r4 = com.wbmd.qxcalculator.R.color.list_selection_color
            int r8 = r8.getColor(r4)
            r3.<init>(r8)
            r0.addState(r1, r3)
            int[] r8 = new int[r2]
            android.graphics.drawable.ColorDrawable r1 = new android.graphics.drawable.ColorDrawable
            r1.<init>(r7)
            r0.addState(r8, r1)
            r6.bkgDrawable = r0
            goto L_0x0170
        L_0x0169:
            android.graphics.drawable.ColorDrawable r8 = new android.graphics.drawable.ColorDrawable
            r8.<init>(r7)
            r6.bkgDrawable = r8
        L_0x0170:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.rowItems.FeaturedContentRowItem.<init>(com.wbmd.qxcalculator.model.db.DBFeaturedContentAd, android.content.Context):void");
    }

    public int getResourceId() {
        return R.layout.row_item_featured_content_ad;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return FeaturedContentAdItemViewHolder.class;
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, QxRecyclerViewRowItem.RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        FeaturedContentAdItemViewHolder featuredContentAdItemViewHolder = (FeaturedContentAdItemViewHolder) viewHolder;
        featuredContentAdItemViewHolder.featuredContentAd = this.featuredContentAd;
        featuredContentAdItemViewHolder.contentItem = this.dbContentItem;
        if (this.imageDrawable != null) {
            featuredContentAdItemViewHolder.textViewContainer.setVisibility(8);
            featuredContentAdItemViewHolder.imageView.setVisibility(0);
            featuredContentAdItemViewHolder.imageView.setImageDrawable(this.imageDrawable);
        } else {
            featuredContentAdItemViewHolder.textViewContainer.setVisibility(0);
            featuredContentAdItemViewHolder.imageView.setVisibility(8);
            if (this.titleSpanned == null && this.title == null) {
                featuredContentAdItemViewHolder.titleTextView.setVisibility(8);
            } else {
                BetterLinkTextView betterLinkTextView = featuredContentAdItemViewHolder.titleTextView;
                CharSequence charSequence = this.titleSpanned;
                if (charSequence == null) {
                    charSequence = this.title;
                }
                betterLinkTextView.setText(charSequence);
                featuredContentAdItemViewHolder.titleTextView.setVisibility(0);
            }
            if (this.bodySpanned == null && this.body == null) {
                featuredContentAdItemViewHolder.bodyTextView.setVisibility(8);
            } else {
                BetterLinkTextView betterLinkTextView2 = featuredContentAdItemViewHolder.bodyTextView;
                CharSequence charSequence2 = this.bodySpanned;
                if (charSequence2 == null) {
                    charSequence2 = this.body;
                }
                betterLinkTextView2.setText(charSequence2);
                featuredContentAdItemViewHolder.bodyTextView.setVisibility(0);
            }
            if (this.disclaimerSpanned == null && this.disclaimer == null) {
                featuredContentAdItemViewHolder.disclaimerTextView.setVisibility(8);
            } else {
                BetterLinkTextView betterLinkTextView3 = featuredContentAdItemViewHolder.disclaimerTextView;
                CharSequence charSequence3 = this.disclaimerSpanned;
                if (charSequence3 == null) {
                    charSequence3 = this.disclaimer;
                }
                betterLinkTextView3.setText(charSequence3);
                featuredContentAdItemViewHolder.disclaimerTextView.setVisibility(0);
            }
            if (this.footerSpanned == null && this.footer == null) {
                featuredContentAdItemViewHolder.footerTextView.setVisibility(8);
            } else {
                BetterLinkTextView betterLinkTextView4 = featuredContentAdItemViewHolder.footerTextView;
                CharSequence charSequence4 = this.footerSpanned;
                if (charSequence4 == null) {
                    charSequence4 = this.footer;
                }
                betterLinkTextView4.setText(charSequence4);
                featuredContentAdItemViewHolder.footerTextView.setVisibility(0);
            }
        }
        if (featuredContentAdItemViewHolder.containerView.getBackground() != this.bkgDrawable) {
            featuredContentAdItemViewHolder.containerView.setBackground(this.bkgDrawable);
        }
    }

    public static final class FeaturedContentAdItemViewHolder extends QxRecyclerRowItemViewHolder {
        BetterLinkTextView bodyTextView;
        ViewGroup containerView;
        DBContentItem contentItem;
        BetterLinkTextView disclaimerTextView;
        DBFeaturedContentAd featuredContentAd;
        BetterLinkTextView footerTextView;
        ImageView imageView;
        ViewGroup textViewContainer;
        BetterLinkTextView titleTextView;

        public void onViewAttachedToWindow() {
        }

        public FeaturedContentAdItemViewHolder(View view) {
            super(view);
            this.containerView = (ViewGroup) view.findViewById(R.id.container_view);
            this.textViewContainer = (ViewGroup) view.findViewById(R.id.text_container_view);
            BetterLinkTextView betterLinkTextView = (BetterLinkTextView) view.findViewById(R.id.title_text_view);
            this.titleTextView = betterLinkTextView;
            betterLinkTextView.setMovementMethod(BetterLinkMovementMethod.getInstance());
            BetterLinkTextView betterLinkTextView2 = (BetterLinkTextView) view.findViewById(R.id.body_text_view);
            this.bodyTextView = betterLinkTextView2;
            betterLinkTextView2.setMovementMethod(BetterLinkMovementMethod.getInstance());
            BetterLinkTextView betterLinkTextView3 = (BetterLinkTextView) view.findViewById(R.id.disclaimer_text_view);
            this.disclaimerTextView = betterLinkTextView3;
            betterLinkTextView3.setMovementMethod(BetterLinkMovementMethod.getInstance());
            BetterLinkTextView betterLinkTextView4 = (BetterLinkTextView) view.findViewById(R.id.footer_text_view);
            this.footerTextView = betterLinkTextView4;
            betterLinkTextView4.setMovementMethod(BetterLinkMovementMethod.getInstance());
            this.imageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }
}
