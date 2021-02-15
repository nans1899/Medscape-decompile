package com.medscape.android.homescreen.views;

import com.google.android.material.appbar.AppBarLayout;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenFragment.kt */
final class HomeScreenFragment$initializeViews$3 extends Lambda implements Function0<Unit> {
    final /* synthetic */ HomeScreenFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HomeScreenFragment$initializeViews$3(HomeScreenFragment homeScreenFragment) {
        super(0);
        this.this$0 = homeScreenFragment;
    }

    public final void invoke() {
        ((AppBarLayout) this.this$0._$_findCachedViewById(R.id.app_bar_layout)).setExpanded(!HomeScreenFragment.access$getHomeViewModel$p(this.this$0).isExpanded(), true);
        OmnitureManager.get().trackModule(this.this$0.getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
    }
}
