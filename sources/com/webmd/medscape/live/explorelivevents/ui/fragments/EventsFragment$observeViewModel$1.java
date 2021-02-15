package com.webmd.medscape.live.explorelivevents.ui.fragments;

import androidx.lifecycle.Observer;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.ui.fragments.EventsFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "resource", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: EventsFragment.kt */
final class EventsFragment$observeViewModel$1<T> implements Observer<Resource<List<? extends LiveEvent>>> {
    final /* synthetic */ EventsFragment this$0;

    EventsFragment$observeViewModel$1(EventsFragment eventsFragment) {
        this.this$0 = eventsFragment;
    }

    public final void onChanged(Resource<List<LiveEvent>> resource) {
        int i = EventsFragment.WhenMappings.$EnumSwitchMapping$0[resource.getStatus().ordinal()];
        if (i == 1) {
            this.this$0.handleLoadingResponse(resource.getMessage());
        } else if (i == 2) {
            EventsFragment eventsFragment = this.this$0;
            Intrinsics.checkNotNullExpressionValue(resource, ContentParser.RESOURCE);
            eventsFragment.handleSuccessResponse(resource);
        } else if (i == 3) {
            this.this$0.handleErrorResponse(resource.getMessage());
        }
    }
}
