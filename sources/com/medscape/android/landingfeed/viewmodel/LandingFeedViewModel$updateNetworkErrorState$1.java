package com.medscape.android.landingfeed.viewmodel;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.util.MedscapeException;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
final class LandingFeedViewModel$updateNetworkErrorState$1 implements View.OnClickListener {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ LandingFeedViewModel this$0;

    LandingFeedViewModel$updateNetworkErrorState$1(LandingFeedViewModel landingFeedViewModel, FragmentActivity fragmentActivity) {
        this.this$0 = landingFeedViewModel;
        this.$activity = fragmentActivity;
    }

    public final void onClick(View view) {
        FragmentActivity fragmentActivity = this.$activity;
        if (fragmentActivity != null) {
            this.this$0.retryWAuth(fragmentActivity);
            if (this.this$0.getFeedType() == 3) {
                this.this$0.loadLiveEvents(this.$activity);
            }
        } else {
            this.this$0.retry();
        }
        MedscapeException access$getMNoNetworkException$p = this.this$0.mNoNetworkException;
        if (access$getMNoNetworkException$p != null) {
            access$getMNoNetworkException$p.dismissSnackBar();
        }
    }
}
