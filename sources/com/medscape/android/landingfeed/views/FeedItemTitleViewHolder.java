package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomFontTextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0011"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemTitleViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "applyText", "", "textView", "Landroid/widget/TextView;", "text", "", "setData", "data", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "activity", "Landroidx/fragment/app/FragmentActivity;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemTitleViewHolder.kt */
public final class FeedItemTitleViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedItemTitleViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
    }

    public final void setData(FeedDataItem feedDataItem, FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        String str = null;
        if (Util.isDataUpdatesItem(feedDataItem)) {
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            CustomFontTextView customFontTextView = (CustomFontTextView) view.findViewById(R.id.type_label);
            Intrinsics.checkNotNullExpressionValue(customFontTextView, "itemView.type_label");
            customFontTextView.setText(feedDataItem != null ? feedDataItem.getType() : null);
        } else if (Util.isClinicalAdvancesItem(feedDataItem) || Util.isLiveEventsItem(feedDataItem)) {
            View view2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            CustomFontTextView customFontTextView2 = (CustomFontTextView) view2.findViewById(R.id.type_label);
            Intrinsics.checkNotNullExpressionValue(customFontTextView2, "itemView.type_label");
            customFontTextView2.setVisibility(8);
        }
        View view3 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view3, "itemView");
        CustomFontTextView customFontTextView3 = (CustomFontTextView) view3.findViewById(R.id.content_title);
        Intrinsics.checkNotNullExpressionValue(customFontTextView3, "itemView.content_title");
        applyText(customFontTextView3, feedDataItem != null ? feedDataItem.getTitle() : null);
        View view4 = this.itemView;
        Intrinsics.checkNotNullExpressionValue(view4, "itemView");
        CustomFontTextView customFontTextView4 = (CustomFontTextView) view4.findViewById(R.id.content_body);
        Intrinsics.checkNotNullExpressionValue(customFontTextView4, "itemView.content_body");
        TextView textView = customFontTextView4;
        if (feedDataItem != null) {
            str = feedDataItem.getBody();
        }
        applyText(textView, str);
        this.itemView.setOnClickListener(new FeedItemTitleViewHolder$setData$1(feedDataItem, fragmentActivity));
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemTitleViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/landingfeed/views/FeedItemTitleViewHolder;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedItemTitleViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeedItemTitleViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_card_title, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new FeedItemTitleViewHolder(inflate);
        }
    }
}
