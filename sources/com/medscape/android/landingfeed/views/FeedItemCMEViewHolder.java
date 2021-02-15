package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.R;
import com.medscape.android.homescreen.util.DateUtils;
import com.medscape.android.landingfeed.model.Eligibility;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.GlideRequest;
import com.medscape.android.view.CustomFontTextView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 )2\u00020\u0001:\u0001)B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003H\u0002J6\u0010\u001c\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0003H\u0002J\u001a\u0010 \u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0012H\u0002J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0017H\u0002J\u0018\u0010'\u001a\u00020\u00142\b\u0010#\u001a\u0004\u0018\u00010\u00122\u0006\u0010(\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemCMEViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "CREDIT_TYPE_ABIM_MOC", "", "CREDIT_TYPE_CE", "CREDIT_TYPE_CME", "clickListener", "Landroid/view/View$OnClickListener;", "mContext", "Landroidx/fragment/app/FragmentActivity;", "getMContext", "()Landroidx/fragment/app/FragmentActivity;", "setMContext", "(Landroidx/fragment/app/FragmentActivity;)V", "selectedData", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "applyCreditValue", "", "eligibilityMap", "", "Lcom/medscape/android/landingfeed/model/Eligibility;", "creditType", "textView", "Landroid/widget/TextView;", "wrapperView", "applyDividerVisibility", "mocCmeDivider", "cmeCeDivider", "ceSaveDivider", "applyText", "text", "checkForSavedArticles", "data", "hasCredit", "", "e", "setData", "context", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemCMEViewHolder.kt */
public final class FeedItemCMEViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String CREDIT_TYPE_ABIM_MOC = "ABIM MOC";
    private final String CREDIT_TYPE_CE = "CE";
    private final String CREDIT_TYPE_CME = "CME";
    private final View.OnClickListener clickListener = new FeedItemCMEViewHolder$clickListener$1(this);
    private FragmentActivity mContext;
    /* access modifiers changed from: private */
    public FeedDataItem selectedData;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedItemCMEViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
    }

    public final FragmentActivity getMContext() {
        return this.mContext;
    }

    public final void setMContext(FragmentActivity fragmentActivity) {
        this.mContext = fragmentActivity;
    }

    public final void setData(FeedDataItem feedDataItem, FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        if (feedDataItem != null) {
            this.selectedData = feedDataItem;
            this.mContext = fragmentActivity;
            if (feedDataItem.getCmeCredits() != null) {
                List<Eligibility> cmeCredits = feedDataItem.getCmeCredits();
                String str = this.CREDIT_TYPE_ABIM_MOC;
                View view = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view, "itemView");
                TextView textView = (TextView) view.findViewById(R.id.abim_value);
                Intrinsics.checkNotNullExpressionValue(textView, "itemView.abim_value");
                View view2 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view2, "itemView");
                LinearLayout linearLayout = (LinearLayout) view2.findViewById(R.id.content_abim);
                Intrinsics.checkNotNullExpressionValue(linearLayout, "itemView.content_abim");
                applyCreditValue(cmeCredits, str, textView, linearLayout);
                List<Eligibility> cmeCredits2 = feedDataItem.getCmeCredits();
                String str2 = this.CREDIT_TYPE_CME;
                View view3 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view3, "itemView");
                TextView textView2 = (TextView) view3.findViewById(R.id.cme_value);
                Intrinsics.checkNotNullExpressionValue(textView2, "itemView.cme_value");
                View view4 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view4, "itemView");
                LinearLayout linearLayout2 = (LinearLayout) view4.findViewById(R.id.content_cme);
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "itemView.content_cme");
                applyCreditValue(cmeCredits2, str2, textView2, linearLayout2);
                List<Eligibility> cmeCredits3 = feedDataItem.getCmeCredits();
                String str3 = this.CREDIT_TYPE_CE;
                View view5 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view5, "itemView");
                TextView textView3 = (TextView) view5.findViewById(R.id.min_value);
                Intrinsics.checkNotNullExpressionValue(textView3, "itemView.min_value");
                View view6 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view6, "itemView");
                LinearLayout linearLayout3 = (LinearLayout) view6.findViewById(R.id.content_min);
                Intrinsics.checkNotNullExpressionValue(linearLayout3, "itemView.content_min");
                applyCreditValue(cmeCredits3, str3, textView3, linearLayout3);
                List<Eligibility> cmeCredits4 = feedDataItem.getCmeCredits();
                View view7 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view7, "itemView");
                View findViewById = view7.findViewById(R.id.view_moc_cme_divider);
                Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.view_moc_cme_divider");
                View view8 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view8, "itemView");
                View findViewById2 = view8.findViewById(R.id.view_cme_min_divider);
                Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.view_cme_min_divider");
                View view9 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view9, "itemView");
                View findViewById3 = view9.findViewById(R.id.view_ce_save_divider);
                Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.view_ce_save_divider");
                View view10 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view10, "itemView");
                LinearLayout linearLayout4 = (LinearLayout) view10.findViewById(R.id.bottom_row);
                Intrinsics.checkNotNullExpressionValue(linearLayout4, "itemView.bottom_row");
                applyDividerVisibility(cmeCredits4, findViewById, findViewById2, findViewById3, linearLayout4);
            } else {
                View view11 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view11, "itemView");
                LinearLayout linearLayout5 = (LinearLayout) view11.findViewById(R.id.bottom_row);
                Intrinsics.checkNotNullExpressionValue(linearLayout5, "itemView.bottom_row");
                linearLayout5.setVisibility(8);
            }
            View view12 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view12, "itemView");
            FrameLayout frameLayout = (FrameLayout) view12.findViewById(R.id.root_container);
            Intrinsics.checkNotNullExpressionValue(frameLayout, "itemView.root_container");
            frameLayout.setVisibility(0);
            if (StringsKt.equals$default(feedDataItem.getSource(), "Featured", false, 2, (Object) null)) {
                View view13 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view13, "itemView");
                View findViewById4 = view13.findViewById(R.id.featured_indicator);
                Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.featured_indicator");
                findViewById4.setVisibility(0);
            } else {
                View view14 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view14, "itemView");
                View findViewById5 = view14.findViewById(R.id.featured_indicator);
                Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.featured_indicator");
                findViewById5.setVisibility(8);
            }
            CharSequence imageUrl = feedDataItem.getImageUrl();
            boolean z = true;
            if (imageUrl == null || StringsKt.isBlank(imageUrl)) {
                View view15 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view15, "itemView");
                ConstraintLayout constraintLayout = (ConstraintLayout) view15.findViewById(R.id.feed_image_body);
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.feed_image_body");
                constraintLayout.setVisibility(8);
                View view16 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view16, "itemView");
                ImageView imageView = (ImageView) view16.findViewById(R.id.feed_image);
                Intrinsics.checkNotNullExpressionValue(imageView, "itemView.feed_image");
                imageView.setVisibility(8);
            } else {
                View view17 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view17, "itemView");
                ConstraintLayout constraintLayout2 = (ConstraintLayout) view17.findViewById(R.id.feed_image_body);
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "itemView.feed_image_body");
                constraintLayout2.setVisibility(0);
                View view18 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view18, "itemView");
                ImageView imageView2 = (ImageView) view18.findViewById(R.id.feed_image);
                Intrinsics.checkNotNullExpressionValue(imageView2, "itemView.feed_image");
                imageView2.setVisibility(0);
                View view19 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view19, "itemView");
                GlideRequest placeholder = GlideApp.with(view19.getContext()).load(feedDataItem.getImageUrl()).placeholder((int) R.drawable.placeholder_image);
                View view20 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view20, "itemView");
                Intrinsics.checkNotNullExpressionValue(placeholder.into((ImageView) view20.findViewById(R.id.feed_image)), "GlideApp.with(itemView.c…into(itemView.feed_image)");
            }
            CharSequence title = feedDataItem.getTitle();
            if (!(title == null || StringsKt.isBlank(title))) {
                View view21 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view21, "itemView");
                CustomFontTextView customFontTextView = (CustomFontTextView) view21.findViewById(R.id.feed_title);
                Intrinsics.checkNotNullExpressionValue(customFontTextView, "itemView.feed_title");
                applyText(customFontTextView, feedDataItem.getTitle());
            } else {
                View view22 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view22, "itemView");
                CustomFontTextView customFontTextView2 = (CustomFontTextView) view22.findViewById(R.id.feed_title);
                Intrinsics.checkNotNullExpressionValue(customFontTextView2, "itemView.feed_title");
                applyText(customFontTextView2, feedDataItem.getHeadline());
            }
            View view23 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view23, "itemView");
            CustomFontTextView customFontTextView3 = (CustomFontTextView) view23.findViewById(R.id.feed_body);
            Intrinsics.checkNotNullExpressionValue(customFontTextView3, "itemView.feed_body");
            applyText(customFontTextView3, DateUtils.Companion.getDateWithMonth(feedDataItem.getPublishDate()));
            CharSequence body = feedDataItem.getBody();
            if (!(body == null || StringsKt.isBlank(body))) {
                View view24 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view24, "itemView");
                CustomFontTextView customFontTextView4 = (CustomFontTextView) view24.findViewById(R.id.feed_description);
                Intrinsics.checkNotNullExpressionValue(customFontTextView4, "itemView.feed_description");
                applyText(customFontTextView4, feedDataItem.getBody());
                View view25 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view25, "itemView");
                CustomFontTextView customFontTextView5 = (CustomFontTextView) view25.findViewById(R.id.feed_description);
                Intrinsics.checkNotNullExpressionValue(customFontTextView5, "itemView.feed_description");
                customFontTextView5.setVisibility(0);
            } else {
                CharSequence description = feedDataItem.getDescription();
                if (!(description == null || description.length() == 0)) {
                    z = false;
                }
                if (!z) {
                    View view26 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view26, "itemView");
                    CustomFontTextView customFontTextView6 = (CustomFontTextView) view26.findViewById(R.id.feed_description);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView6, "itemView.feed_description");
                    applyText(customFontTextView6, feedDataItem.getDescription());
                    View view27 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view27, "itemView");
                    CustomFontTextView customFontTextView7 = (CustomFontTextView) view27.findViewById(R.id.feed_description);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView7, "itemView.feed_description");
                    customFontTextView7.setVisibility(0);
                } else {
                    View view28 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view28, "itemView");
                    CustomFontTextView customFontTextView8 = (CustomFontTextView) view28.findViewById(R.id.feed_description);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView8, "itemView.feed_description");
                    customFontTextView8.setVisibility(8);
                }
            }
            checkForSavedArticles(feedDataItem);
            View view29 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view29, "itemView");
            ((FrameLayout) view29.findViewById(R.id.root_container)).setOnClickListener(this.clickListener);
            View view30 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view30, "itemView");
            ((LinearLayout) view30.findViewById(R.id.feed_save)).setOnClickListener(this.clickListener);
            return;
        }
        View view31 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view31, "itemView");
        FrameLayout frameLayout2 = (FrameLayout) view31.findViewById(R.id.root_container);
        Intrinsics.checkNotNullExpressionValue(frameLayout2, "itemView.root_container");
        frameLayout2.setVisibility(8);
    }

    private final void applyDividerVisibility(List<? extends Eligibility> list, View view, View view2, View view3, View view4) {
        View view5 = view;
        View view6 = view2;
        View view7 = view3;
        View view8 = view4;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (Eligibility eligibility : list) {
            if (StringsKt.equals$default(eligibility.getType(), this.CREDIT_TYPE_ABIM_MOC, false, 2, (Object) null)) {
                z = hasCredit(eligibility);
                if (z) {
                }
            } else if (StringsKt.equals$default(eligibility.getType(), this.CREDIT_TYPE_CME, false, 2, (Object) null)) {
                z2 = hasCredit(eligibility);
                if (z2) {
                }
            } else if (StringsKt.equals$default(eligibility.getType(), this.CREDIT_TYPE_CE, false, 2, (Object) null)) {
                z3 = hasCredit(eligibility);
                if (z3) {
                }
            }
            i++;
        }
        if (list.isEmpty()) {
            view8.setVisibility(8);
        } else if (i == 3 || (!z && !z2 && !z3)) {
            view8.setVisibility(8);
        } else {
            view8.setVisibility(0);
            if (!z) {
                view5.setVisibility(8);
            } else {
                view5.setVisibility(0);
            }
            if (!z2) {
                view6.setVisibility(8);
            } else {
                view6.setVisibility(0);
            }
            if (!z3) {
                view7.setVisibility(8);
            } else {
                view7.setVisibility(0);
            }
        }
    }

    private final boolean hasCredit(Eligibility eligibility) {
        if (eligibility == null || eligibility.getCredits() == null || ((double) eligibility.getCredits().floatValue()) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return false;
        }
        return true;
    }

    private final void applyCreditValue(List<? extends Eligibility> list, String str, TextView textView, View view) {
        boolean z = false;
        for (Eligibility eligibility : list) {
            Float f = null;
            if (StringsKt.equals$default(eligibility.getType(), str, false, 2, (Object) null)) {
                if (eligibility != null) {
                    f = eligibility.getCredits();
                }
                if (f != null) {
                    applyText(textView, String.valueOf(eligibility.getCredits().floatValue()));
                    z = true;
                }
            }
        }
        if (!z) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
        }
    }

    private final void applyText(TextView textView, String str) {
        CharSequence charSequence = str;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(charSequence);
    }

    private final void checkForSavedArticles(FeedDataItem feedDataItem) {
        Boolean isSaved = feedDataItem.isSaved();
        Intrinsics.checkNotNull(isSaved);
        if (isSaved.booleanValue()) {
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            ((ImageView) view.findViewById(R.id.feed_save_icon)).setImageResource(R.drawable.ic_save_fill);
            return;
        }
        View view2 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view2, "itemView");
        ((ImageView) view2.findViewById(R.id.feed_save_icon)).setImageResource(R.drawable.ic_save_empty);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemCMEViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/landingfeed/views/FeedItemCMEViewHolder;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedItemCMEViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeedItemCMEViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cme_feed, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new FeedItemCMEViewHolder(inflate);
        }
    }
}
