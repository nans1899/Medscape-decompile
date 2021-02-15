package com.webmd.medscape.live.explorelivevents.ui.fragments;

import androidx.lifecycle.Observer;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.ui.fragments.LiveEventFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventFragment.kt */
final class LiveEventFragment$observeViewModel$1<T> implements Observer<Resource<String>> {
    final /* synthetic */ LiveEventFragment this$0;

    LiveEventFragment$observeViewModel$1(LiveEventFragment liveEventFragment) {
        this.this$0 = liveEventFragment;
    }

    public final void onChanged(Resource<String> resource) {
        int i = LiveEventFragment.WhenMappings.$EnumSwitchMapping$0[resource.getStatus().ordinal()];
        if (i == 1) {
            this.this$0.handleLoadingResponse(resource.getMessage());
        } else if (i == 2) {
            LiveEventFragment liveEventFragment = this.this$0;
            Intrinsics.checkNotNullExpressionValue(resource, "it");
            liveEventFragment.handleSuccessResponse(resource);
        } else if (i == 3) {
            this.this$0.handleErrorResponse(resource.getMessage());
        }
    }
}
