package com.webmd.wbmdcmepulse.live_events.util;

import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import java.util.ArrayList;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007H&¨\u0006\b"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsLoadFinish;", "", "onLiveEventsLoaded", "", "liveEvents", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsLoadFinish.kt */
public interface LiveEventsLoadFinish {
    void onLiveEventsLoaded(ArrayList<LiveEventItem> arrayList);
}
