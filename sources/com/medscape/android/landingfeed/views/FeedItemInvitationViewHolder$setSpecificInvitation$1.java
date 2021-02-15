package com.medscape.android.landingfeed.views;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.landingfeed.model.FeedDataItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: FeedItemInvitationViewHolder.kt */
final class FeedItemInvitationViewHolder$setSpecificInvitation$1 implements View.OnClickListener {
    final /* synthetic */ FragmentActivity $context;
    final /* synthetic */ int $feedType;
    final /* synthetic */ FeedDataItem $item;
    final /* synthetic */ FeedItemInvitationViewHolder this$0;

    FeedItemInvitationViewHolder$setSpecificInvitation$1(FeedItemInvitationViewHolder feedItemInvitationViewHolder, FeedDataItem feedDataItem, FragmentActivity fragmentActivity, int i) {
        this.this$0 = feedItemInvitationViewHolder;
        this.$item = feedDataItem;
        this.$context = fragmentActivity;
        this.$feedType = i;
    }

    public final void onClick(View view) {
        this.this$0.openDestinationUrl(this.$item, this.$context, this.$feedType);
    }
}
