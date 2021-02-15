package com.medscape.android.homescreen.views;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenActivity.kt */
final class HomeScreenActivity$landingViewModel$2 extends Lambda implements Function0<LandingFeedViewModel> {
    final /* synthetic */ HomeScreenActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HomeScreenActivity$landingViewModel$2(HomeScreenActivity homeScreenActivity) {
        super(0);
        this.this$0 = homeScreenActivity;
    }

    public final LandingFeedViewModel invoke() {
        return (LandingFeedViewModel) ViewModelProviders.of((FragmentActivity) this.this$0).get(LandingFeedViewModel.class);
    }
}
