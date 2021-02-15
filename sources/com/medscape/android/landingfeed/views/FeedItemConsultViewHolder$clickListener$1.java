package com.medscape.android.landingfeed.views;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "view", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: FeedItemConsultViewHolder.kt */
final class FeedItemConsultViewHolder$clickListener$1 implements View.OnClickListener {
    final /* synthetic */ FeedItemConsultViewHolder this$0;

    FeedItemConsultViewHolder$clickListener$1(FeedItemConsultViewHolder feedItemConsultViewHolder) {
        this.this$0 = feedItemConsultViewHolder;
    }

    public final void onClick(View view) {
        if (this.this$0.selectedData != null && this.this$0.getMContext() != null) {
            FragmentActivity mContext = this.this$0.getMContext();
            Intrinsics.checkNotNull(mContext);
            ViewModel viewModel = ViewModelProviders.of(mContext).get(LandingFeedViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(mC…eedViewModel::class.java)");
            FeedDataItem access$getSelectedData$p = this.this$0.selectedData;
            Intrinsics.checkNotNull(access$getSelectedData$p);
            FragmentActivity mContext2 = this.this$0.getMContext();
            Intrinsics.checkNotNull(mContext2);
            ((LandingFeedViewModel) viewModel).launchFeedItem(access$getSelectedData$p, mContext2, this.this$0.getAdapterPosition());
        }
    }
}
