package com.medscape.android.landingfeed.views;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.landingfeed.model.FeedAdItem;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: FeedItemNativeADViewHolder.kt */
final class FeedItemNativeADViewHolder$bindAD$1 implements View.OnClickListener {
    final /* synthetic */ FeedAdItem $adItem;
    final /* synthetic */ LandingFeedViewModel $landingFeedViewModel;
    final /* synthetic */ FragmentActivity $mContext;
    final /* synthetic */ int $pgValue;
    final /* synthetic */ FeedItemNativeADViewHolder this$0;

    FeedItemNativeADViewHolder$bindAD$1(FeedItemNativeADViewHolder feedItemNativeADViewHolder, FeedAdItem feedAdItem, LandingFeedViewModel landingFeedViewModel, FragmentActivity fragmentActivity, int i) {
        this.this$0 = feedItemNativeADViewHolder;
        this.$adItem = feedAdItem;
        this.$landingFeedViewModel = landingFeedViewModel;
        this.$mContext = fragmentActivity;
        this.$pgValue = i;
    }

    public final void onClick(View view) {
        NativeDFPAD nativeDFPAD;
        NativeCustomTemplateAd nativeAD;
        String customTemplateId;
        FeedAdItem feedAdItem = this.$adItem;
        if (!(feedAdItem == null || (nativeDFPAD = feedAdItem.getNativeDFPAD()) == null || (nativeAD = nativeDFPAD.getNativeAD()) == null || (customTemplateId = nativeAD.getCustomTemplateId()) == null || !customTemplateId.equals("11848473"))) {
            this.$landingFeedViewModel.sendNativeAdClickedImpression(this.$mContext, this.$pgValue);
        }
        NativeCustomTemplateAd nativead = this.this$0.getBinding().getNativead();
        if (nativead != null) {
            nativead.performClick("Title");
        }
    }
}
