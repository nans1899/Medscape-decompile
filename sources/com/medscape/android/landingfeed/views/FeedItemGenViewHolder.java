package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.R;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.activity.rss.views.NewsLandingActivity;
import com.medscape.android.homescreen.util.DateUtils;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.GlideRequest;
import com.medscape.android.view.CustomFontTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0018\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemGenViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "clickListener", "Landroid/view/View$OnClickListener;", "mContext", "Landroidx/fragment/app/FragmentActivity;", "getMContext", "()Landroidx/fragment/app/FragmentActivity;", "setMContext", "(Landroidx/fragment/app/FragmentActivity;)V", "selectedData", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "applyText", "", "textView", "Landroid/widget/TextView;", "text", "", "checkForSavedArticles", "data", "setData", "activity", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemGenViewHolder.kt */
public final class FeedItemGenViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final View.OnClickListener clickListener = new FeedItemGenViewHolder$clickListener$1(this);
    private FragmentActivity mContext;
    /* access modifiers changed from: private */
    public FeedDataItem selectedData;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedItemGenViewHolder(View view) {
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
        String str;
        String str2;
        int hashCode;
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        if (feedDataItem != null) {
            this.selectedData = feedDataItem;
            this.mContext = fragmentActivity;
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.root_layout);
            Intrinsics.checkNotNullExpressionValue(frameLayout, "itemView.root_layout");
            boolean z = false;
            frameLayout.setVisibility(0);
            View view2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            ((CustomFontTextView) view2.findViewById(R.id.type_label)).setBackgroundColor(0);
            FragmentActivity fragmentActivity2 = this.mContext;
            if (fragmentActivity2 instanceof HomeScreenActivity) {
                View view3 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view3, "itemView");
                CustomFontTextView customFontTextView = (CustomFontTextView) view3.findViewById(R.id.type_label);
                Intrinsics.checkNotNullExpressionValue(customFontTextView, "itemView.type_label");
                customFontTextView.setVisibility(0);
                View view4 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view4, "itemView");
                CustomFontTextView customFontTextView2 = (CustomFontTextView) view4.findViewById(R.id.type_label);
                Intrinsics.checkNotNullExpressionValue(customFontTextView2, "itemView.type_label");
                applyText(customFontTextView2, feedDataItem.getCategory());
            } else if (fragmentActivity2 instanceof CMELandingActivity) {
                View view5 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view5, "itemView");
                CustomFontTextView customFontTextView3 = (CustomFontTextView) view5.findViewById(R.id.type_label);
                Intrinsics.checkNotNullExpressionValue(customFontTextView3, "itemView.type_label");
                customFontTextView3.setVisibility(8);
            } else if (fragmentActivity2 instanceof NewsLandingActivity) {
                View view6 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view6, "itemView");
                CustomFontTextView customFontTextView4 = (CustomFontTextView) view6.findViewById(R.id.type_label);
                Intrinsics.checkNotNullExpressionValue(customFontTextView4, "itemView.type_label");
                customFontTextView4.setVisibility(0);
                View view7 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view7, "itemView");
                CustomFontTextView customFontTextView5 = (CustomFontTextView) view7.findViewById(R.id.type_label);
                Intrinsics.checkNotNullExpressionValue(customFontTextView5, "itemView.type_label");
                applyText(customFontTextView5, feedDataItem.getCategory());
            }
            CharSequence imageUrl = feedDataItem.getImageUrl();
            if (imageUrl == null || StringsKt.isBlank(imageUrl)) {
                View view8 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view8, "itemView");
                ConstraintLayout constraintLayout = (ConstraintLayout) view8.findViewById(R.id.content_image_body);
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "itemView.content_image_body");
                constraintLayout.setVisibility(8);
                View view9 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view9, "itemView");
                ImageView imageView = (ImageView) view9.findViewById(R.id.content_image);
                Intrinsics.checkNotNullExpressionValue(imageView, "itemView.content_image");
                imageView.setVisibility(8);
            } else {
                View view10 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view10, "itemView");
                ConstraintLayout constraintLayout2 = (ConstraintLayout) view10.findViewById(R.id.content_image_body);
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "itemView.content_image_body");
                constraintLayout2.setVisibility(0);
                View view11 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view11, "itemView");
                ImageView imageView2 = (ImageView) view11.findViewById(R.id.content_image);
                Intrinsics.checkNotNullExpressionValue(imageView2, "itemView.content_image");
                imageView2.setVisibility(0);
                if (feedDataItem.getImageUrl() != null) {
                    GlideRequest placeholder = GlideApp.with(fragmentActivity).load(feedDataItem.getImageUrl()).dontAnimate().placeholder((int) R.drawable.placeholder_image);
                    View view12 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view12, "itemView");
                    placeholder.into((ImageView) view12.findViewById(R.id.content_image));
                }
            }
            String str3 = null;
            if (feedDataItem.getType() == null) {
                View view13 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view13, "itemView");
                LinearLayout linearLayout = (LinearLayout) view13.findViewById(R.id.content_save);
                Intrinsics.checkNotNullExpressionValue(linearLayout, "itemView.content_save");
                linearLayout.setVisibility(8);
            } else {
                String type = feedDataItem.getType();
                if (type == null) {
                    str = null;
                } else if (type != null) {
                    str = type.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.String).toLowerCase()");
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                if (Intrinsics.areEqual((Object) str, (Object) "reference")) {
                    String category = feedDataItem.getCategory();
                    if (category != null) {
                        if ((hashCode = category.hashCode()) == 800551195 ? !category.equals("Anatomy") : hashCode == 908763827 ? !category.equals("Procedure") : hashCode != 1142656251 || !category.equals("Condition")) {
                            View view14 = this.itemView;
                            Intrinsics.checkNotNullExpressionValue(view14, "itemView");
                            LinearLayout linearLayout2 = (LinearLayout) view14.findViewById(R.id.content_save);
                            Intrinsics.checkNotNullExpressionValue(linearLayout2, "itemView.content_save");
                            linearLayout2.setVisibility(8);
                        } else {
                            View view15 = this.itemView;
                            Intrinsics.checkNotNullExpressionValue(view15, "itemView");
                            LinearLayout linearLayout3 = (LinearLayout) view15.findViewById(R.id.content_save);
                            Intrinsics.checkNotNullExpressionValue(linearLayout3, "itemView.content_save");
                            linearLayout3.setVisibility(0);
                        }
                    }
                } else if (StringsKt.equals(feedDataItem.getType(), FeedConstants.SPECIAL_COVERAGE_ITEM, true)) {
                    View view16 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view16, "itemView");
                    LinearLayout linearLayout4 = (LinearLayout) view16.findViewById(R.id.content_save);
                    Intrinsics.checkNotNullExpressionValue(linearLayout4, "itemView.content_save");
                    linearLayout4.setVisibility(8);
                    View view17 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view17, "itemView");
                    CustomFontTextView customFontTextView6 = (CustomFontTextView) view17.findViewById(R.id.type_label);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView6, "itemView.type_label");
                    applyText(customFontTextView6, feedDataItem.getCategory());
                    FragmentActivity fragmentActivity3 = this.mContext;
                    if (fragmentActivity3 != null && Intrinsics.areEqual((Object) feedDataItem.getCategoryHighlighted(), (Object) true)) {
                        View view18 = this.itemView;
                        Intrinsics.checkNotNullExpressionValue(view18, "itemView");
                        ((CustomFontTextView) view18.findViewById(R.id.type_label)).setBackgroundColor(ContextCompat.getColor(fragmentActivity3, R.color.special_coverage_background));
                    }
                } else {
                    String type2 = feedDataItem.getType();
                    if (type2 == null) {
                        str2 = null;
                    } else if (type2 != null) {
                        str2 = type2.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(str2, "(this as java.lang.String).toLowerCase()");
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    if (!Intrinsics.areEqual((Object) str2, (Object) "promo")) {
                        View view19 = this.itemView;
                        Intrinsics.checkNotNullExpressionValue(view19, "itemView");
                        LinearLayout linearLayout5 = (LinearLayout) view19.findViewById(R.id.content_save);
                        Intrinsics.checkNotNullExpressionValue(linearLayout5, "itemView.content_save");
                        linearLayout5.setVisibility(0);
                    } else {
                        View view20 = this.itemView;
                        Intrinsics.checkNotNullExpressionValue(view20, "itemView");
                        LinearLayout linearLayout6 = (LinearLayout) view20.findViewById(R.id.content_save);
                        Intrinsics.checkNotNullExpressionValue(linearLayout6, "itemView.content_save");
                        linearLayout6.setVisibility(8);
                    }
                }
            }
            CharSequence title = feedDataItem.getTitle();
            if (!(title == null || StringsKt.isBlank(title))) {
                View view21 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view21, "itemView");
                CustomFontTextView customFontTextView7 = (CustomFontTextView) view21.findViewById(R.id.content_title);
                Intrinsics.checkNotNullExpressionValue(customFontTextView7, "itemView.content_title");
                applyText(customFontTextView7, feedDataItem.getTitle());
            } else {
                View view22 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view22, "itemView");
                CustomFontTextView customFontTextView8 = (CustomFontTextView) view22.findViewById(R.id.content_title);
                Intrinsics.checkNotNullExpressionValue(customFontTextView8, "itemView.content_title");
                applyText(customFontTextView8, feedDataItem.getHeadline());
            }
            View view23 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view23, "itemView");
            CustomFontTextView customFontTextView9 = (CustomFontTextView) view23.findViewById(R.id.update_date);
            Intrinsics.checkNotNullExpressionValue(customFontTextView9, "itemView.update_date");
            customFontTextView9.setVisibility(8);
            String type3 = feedDataItem.getType();
            if (type3 != null) {
                if (type3 != null) {
                    str3 = type3.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str3, "(this as java.lang.String).toLowerCase()");
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            }
            if (Intrinsics.areEqual((Object) str3, (Object) "reference")) {
                CharSequence updatedDate = feedDataItem.getUpdatedDate();
                if (updatedDate == null || StringsKt.isBlank(updatedDate)) {
                    z = true;
                }
                if (!z) {
                    View view24 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view24, "itemView");
                    CustomFontTextView customFontTextView10 = (CustomFontTextView) view24.findViewById(R.id.update_date);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView10, "itemView.update_date");
                    StringBuilder sb = new StringBuilder();
                    FragmentActivity fragmentActivity4 = this.mContext;
                    Intrinsics.checkNotNull(fragmentActivity4);
                    sb.append(fragmentActivity4.getResources().getString(R.string.update_label));
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    sb.append(DateUtils.Companion.getDate(feedDataItem.getUpdatedDate()));
                    applyText(customFontTextView10, sb.toString());
                } else {
                    View view25 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view25, "itemView");
                    CustomFontTextView customFontTextView11 = (CustomFontTextView) view25.findViewById(R.id.update_date);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView11, "itemView.update_date");
                    customFontTextView11.setVisibility(8);
                }
                View view26 = this.itemView;
                Intrinsics.checkNotNullExpressionValue(view26, "itemView");
                CustomFontTextView customFontTextView12 = (CustomFontTextView) view26.findViewById(R.id.content_body);
                Intrinsics.checkNotNullExpressionValue(customFontTextView12, "itemView.content_body");
                applyText(customFontTextView12, feedDataItem.getByLine());
            } else {
                CharSequence body = feedDataItem.getBody();
                if (body == null || StringsKt.isBlank(body)) {
                    z = true;
                }
                if (!z) {
                    View view27 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view27, "itemView");
                    CustomFontTextView customFontTextView13 = (CustomFontTextView) view27.findViewById(R.id.content_body);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView13, "itemView.content_body");
                    applyText(customFontTextView13, feedDataItem.getBody());
                } else {
                    View view28 = this.itemView;
                    Intrinsics.checkNotNullExpressionValue(view28, "itemView");
                    CustomFontTextView customFontTextView14 = (CustomFontTextView) view28.findViewById(R.id.content_body);
                    Intrinsics.checkNotNullExpressionValue(customFontTextView14, "itemView.content_body");
                    applyText(customFontTextView14, feedDataItem.getPublicationName());
                }
            }
            checkForSavedArticles(feedDataItem);
            View view29 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view29, "itemView");
            ((LinearLayout) view29.findViewById(R.id.content_save)).setOnClickListener(this.clickListener);
            View view30 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view30, "itemView");
            ((FrameLayout) view30.findViewById(R.id.root_layout)).setOnClickListener(this.clickListener);
            View view31 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view31, "itemView");
            ((LinearLayout) view31.findViewById(R.id.content_share)).setOnClickListener(this.clickListener);
            return;
        }
        View view32 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view32, "itemView");
        FrameLayout frameLayout2 = (FrameLayout) view32.findViewById(R.id.root_layout);
        Intrinsics.checkNotNullExpressionValue(frameLayout2, "itemView.root_layout");
        frameLayout2.setVisibility(8);
    }

    private final void checkForSavedArticles(FeedDataItem feedDataItem) {
        Boolean isSaved = feedDataItem.isSaved();
        Intrinsics.checkNotNull(isSaved);
        if (isSaved.booleanValue()) {
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            ((ImageView) view.findViewById(R.id.save_icon)).setImageResource(R.drawable.ic_save_fill);
            return;
        }
        View view2 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view2, "itemView");
        ((ImageView) view2.findViewById(R.id.save_icon)).setImageResource(R.drawable.ic_save_empty);
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemGenViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/landingfeed/views/FeedItemGenViewHolder;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedItemGenViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeedItemGenViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_card_gen, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new FeedItemGenViewHolder(inflate);
        }
    }
}
