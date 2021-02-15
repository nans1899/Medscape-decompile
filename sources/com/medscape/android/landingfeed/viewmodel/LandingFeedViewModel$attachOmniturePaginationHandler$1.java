package com.medscape.android.landingfeed.viewmodel;

import android.app.Activity;
import android.widget.Toast;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.webmd.wbmdomnituremanager.WBMDPaginationListener;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"com/medscape/android/landingfeed/viewmodel/LandingFeedViewModel$attachOmniturePaginationHandler$1", "Lcom/webmd/wbmdomnituremanager/WBMDPaginationListener;", "onDebugOptions", "", "flag", "", "sendOmniture", "pageCount", "pageNumber", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
public final class LandingFeedViewModel$attachOmniturePaginationHandler$1 implements WBMDPaginationListener {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ LandingFeedViewModel this$0;

    LandingFeedViewModel$attachOmniturePaginationHandler$1(LandingFeedViewModel landingFeedViewModel, Activity activity) {
        this.this$0 = landingFeedViewModel;
        this.$activity = activity;
    }

    public void sendOmniture(int i, int i2) {
        Map hashMap = new HashMap();
        hashMap.put("wapp.pagination", String.valueOf(i2));
        OmnitureManager omnitureManager = OmnitureManager.get();
        omnitureManager.markModule("app-swipe", String.valueOf(i) + "", hashMap);
        this.this$0.sendScreenViewPing(this.$activity);
    }

    public void onDebugOptions(int i) {
        if (SharedPreferenceProvider.get().get(Constants.DEBUG_VIRTUAL_PAGEVIEW, 10) == 11) {
            Toast.makeText(this.$activity, "1.5 page passed since last call", 0).show();
        }
    }
}
