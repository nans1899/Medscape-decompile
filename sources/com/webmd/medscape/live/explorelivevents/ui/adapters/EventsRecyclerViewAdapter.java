package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.medscape.live.explorelivevents.common.OnLiveEventSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.databinding.EventItemBinding;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.LiveEventViewHolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0014\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u0017J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/adapters/EventsRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/webmd/medscape/live/explorelivevents/ui/viewholders/LiveEventViewHolder;", "eventSelectedListener", "Lcom/webmd/medscape/live/explorelivevents/common/OnLiveEventSelectedListener;", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "(Lcom/webmd/medscape/live/explorelivevents/common/OnLiveEventSelectedListener;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;)V", "liveEvents", "", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "refresh", "liveEventsSet", "", "setStyle", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EventsRecyclerViewAdapter.kt */
public final class EventsRecyclerViewAdapter extends RecyclerView.Adapter<LiveEventViewHolder> {
    /* access modifiers changed from: private */
    public OnLiveEventSelectedListener eventSelectedListener;
    /* access modifiers changed from: private */
    public List<LiveEvent> liveEvents;
    private StyleManager styleManager;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EventsRecyclerViewAdapter(OnLiveEventSelectedListener onLiveEventSelectedListener, StyleManager styleManager2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(onLiveEventSelectedListener, (i & 2) != 0 ? new StyleManager(false, 1, (DefaultConstructorMarker) null) : styleManager2);
    }

    public EventsRecyclerViewAdapter(OnLiveEventSelectedListener onLiveEventSelectedListener, StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(onLiveEventSelectedListener, "eventSelectedListener");
        Intrinsics.checkNotNullParameter(styleManager2, "styleManager");
        this.eventSelectedListener = onLiveEventSelectedListener;
        this.styleManager = styleManager2;
        this.liveEvents = new ArrayList();
    }

    public LiveEventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        EventItemBinding inflate = EventItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "EventItemBinding.inflate…tInflater, parent, false)");
        return new LiveEventViewHolder(inflate);
    }

    public int getItemCount() {
        return this.liveEvents.size();
    }

    public final void refresh(List<LiveEvent> list) {
        Intrinsics.checkNotNullParameter(list, "liveEventsSet");
        this.liveEvents.clear();
        this.liveEvents.addAll(list);
        notifyDataSetChanged();
    }

    public final void setStyle(StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(styleManager2, "styleManager");
        this.styleManager = styleManager2;
    }

    public void onBindViewHolder(LiveEventViewHolder liveEventViewHolder, int i) {
        Intrinsics.checkNotNullParameter(liveEventViewHolder, "holder");
        liveEventViewHolder.getBinding().setLiveEvent(this.liveEvents.get(i));
        liveEventViewHolder.getBinding().setStyleManager(this.styleManager);
        liveEventViewHolder.getBinding().getRoot().setOnClickListener(new EventsRecyclerViewAdapter$onBindViewHolder$$inlined$with$lambda$1(this, liveEventViewHolder, i));
    }
}
