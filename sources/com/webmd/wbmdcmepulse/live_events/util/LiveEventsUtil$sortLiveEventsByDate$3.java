package com.webmd.wbmdcmepulse.live_events.util;

import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "p0", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "kotlin.jvm.PlatformType", "p1", "compare"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventsUtil.kt */
final class LiveEventsUtil$sortLiveEventsByDate$3<T> implements Comparator<LiveEventItem> {
    final /* synthetic */ String $correctPattern;

    LiveEventsUtil$sortLiveEventsByDate$3(String str) {
        this.$correctPattern = str;
    }

    public final int compare(LiveEventItem liveEventItem, LiveEventItem liveEventItem2) {
        String str = null;
        Date parse = new SimpleDateFormat(this.$correctPattern).parse(liveEventItem != null ? liveEventItem.getEventDate() : null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.$correctPattern);
        if (liveEventItem2 != null) {
            str = liveEventItem2.getEventDate();
        }
        return parse.compareTo(simpleDateFormat.parse(str));
    }
}
