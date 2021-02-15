package com.webmd.medscape.live.explorelivevents.common;

import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/OnLiveEventSelectedListener;", "", "onClick", "", "event", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OnLiveEventSelectedListener.kt */
public interface OnLiveEventSelectedListener {
    void onClick(LiveEvent liveEvent);
}
