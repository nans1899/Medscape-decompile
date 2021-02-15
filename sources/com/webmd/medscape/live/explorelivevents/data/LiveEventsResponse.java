package com.webmd.medscape.live.explorelivevents.data;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/LiveEventsResponse;", "", "liveEvents", "", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "(Ljava/util/List;)V", "getLiveEvents", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsResponse.kt */
public final class LiveEventsResponse {
    @Expose
    private final List<LiveEvent> liveEvents;

    public LiveEventsResponse() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ LiveEventsResponse copy$default(LiveEventsResponse liveEventsResponse, List<LiveEvent> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = liveEventsResponse.liveEvents;
        }
        return liveEventsResponse.copy(list);
    }

    public final List<LiveEvent> component1() {
        return this.liveEvents;
    }

    public final LiveEventsResponse copy(List<LiveEvent> list) {
        Intrinsics.checkNotNullParameter(list, "liveEvents");
        return new LiveEventsResponse(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof LiveEventsResponse) && Intrinsics.areEqual((Object) this.liveEvents, (Object) ((LiveEventsResponse) obj).liveEvents);
        }
        return true;
    }

    public int hashCode() {
        List<LiveEvent> list = this.liveEvents;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "LiveEventsResponse(liveEvents=" + this.liveEvents + ")";
    }

    public LiveEventsResponse(List<LiveEvent> list) {
        Intrinsics.checkNotNullParameter(list, "liveEvents");
        this.liveEvents = list;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LiveEventsResponse(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list);
    }

    public final List<LiveEvent> getLiveEvents() {
        return this.liveEvents;
    }
}
