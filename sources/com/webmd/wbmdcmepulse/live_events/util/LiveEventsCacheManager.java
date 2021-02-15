package com.webmd.wbmdcmepulse.live_events.util;

import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006J\u001e\u0010\u000e\u001a\u00020\f2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsCacheManager;", "", "()V", "liveEventsList", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "getLiveEventsList", "()Ljava/util/ArrayList;", "setLiveEventsList", "(Ljava/util/ArrayList;)V", "clearLiveEvents", "", "getLiveEvents", "setLiveEvents", "list", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsCacheManager.kt */
public final class LiveEventsCacheManager {
    private ArrayList<LiveEventItem> liveEventsList = new ArrayList<>();

    public final ArrayList<LiveEventItem> getLiveEventsList() {
        return this.liveEventsList;
    }

    public final void setLiveEventsList(ArrayList<LiveEventItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.liveEventsList = arrayList;
    }

    public final void setLiveEvents(ArrayList<LiveEventItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, Constants.XML_LIST);
        this.liveEventsList.clear();
        this.liveEventsList = arrayList;
    }

    public final ArrayList<LiveEventItem> getLiveEvents() {
        return this.liveEventsList;
    }

    public final void clearLiveEvents() {
        this.liveEventsList.clear();
    }
}
