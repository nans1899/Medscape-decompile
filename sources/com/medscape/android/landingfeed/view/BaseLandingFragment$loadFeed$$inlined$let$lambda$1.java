package com.medscape.android.landingfeed.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.landingfeed.repository.NetworkState;
import com.medscape.android.landingfeed.repository.Status;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "kotlin.jvm.PlatformType", "onChanged", "com/medscape/android/landingfeed/view/BaseLandingFragment$loadFeed$2$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: BaseLandingFragment.kt */
final class BaseLandingFragment$loadFeed$$inlined$let$lambda$1<T> implements Observer<NetworkState> {
    final /* synthetic */ FragmentActivity $activity$inlined;
    final /* synthetic */ LandingFeedViewModel $viewModel;
    final /* synthetic */ BaseLandingFragment this$0;

    BaseLandingFragment$loadFeed$$inlined$let$lambda$1(LandingFeedViewModel landingFeedViewModel, BaseLandingFragment baseLandingFragment, FragmentActivity fragmentActivity) {
        this.$viewModel = landingFeedViewModel;
        this.this$0 = baseLandingFragment;
        this.$activity$inlined = fragmentActivity;
    }

    public final void onChanged(NetworkState networkState) {
        if (this.$viewModel.getIgnoreFirstError()) {
            this.$viewModel.setIgnoreFirstError(networkState.getStatus() != Status.SUCCESS);
        }
        if (!this.$viewModel.getIgnoreFirstError() || networkState.getStatus() != Status.FAILED) {
            this.this$0.setShimmerEffect(networkState);
            this.$viewModel.updateNetworkErrorState(networkState, this.$activity$inlined, this.this$0.getRootView());
        } else {
            this.$viewModel.setIgnoreFirstError(false);
            this.$viewModel.refreshFailedFeed(false);
        }
        if (this.this$0.isScrollTop && networkState.getStatus() == Status.SUCCESS) {
            RecyclerView feedView = this.this$0.getFeedView();
            if (feedView != null) {
                feedView.scrollToPosition(0);
            }
            this.this$0.isScrollTop = false;
        }
    }
}
