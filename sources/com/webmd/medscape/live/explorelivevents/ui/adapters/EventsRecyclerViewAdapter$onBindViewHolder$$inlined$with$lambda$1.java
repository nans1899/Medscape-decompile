package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.View;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.LiveEventViewHolder;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/webmd/medscape/live/explorelivevents/ui/adapters/EventsRecyclerViewAdapter$onBindViewHolder$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: EventsRecyclerViewAdapter.kt */
final class EventsRecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ LiveEventViewHolder $holder$inlined;
    final /* synthetic */ int $position$inlined;
    final /* synthetic */ EventsRecyclerViewAdapter this$0;

    EventsRecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1(EventsRecyclerViewAdapter eventsRecyclerViewAdapter, LiveEventViewHolder liveEventViewHolder, int i) {
        this.this$0 = eventsRecyclerViewAdapter;
        this.$holder$inlined = liveEventViewHolder;
        this.$position$inlined = i;
    }

    public final void onClick(View view) {
        this.this$0.eventSelectedListener.onClick((LiveEvent) this.this$0.liveEvents.get(this.$position$inlined));
    }
}
