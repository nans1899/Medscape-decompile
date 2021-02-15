package net.media.android.bidder.base.analytics;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mnetinternal.aa;
import mnetinternal.ac;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.TimeEvent;

public final class TimeEventTracker {
    public static final String EVENT_DFP_RESPONSE = "dfp_response";
    public static final String EVENT_DP_DELAY = "dp_processing";
    public static final String EVENT_DP_LATENCY = "dp_latency";
    public static final String EVENT_DP_RESPONSE = "dp_response";
    public static final String EVENT_RTB_DELAY = "rtb_exchange";
    private static final String PROPERTY_KEY = "timings";
    private static final String TAG = "##TimeEventTracker##";
    private static final String TIME_EVENT_STRING = "ad_load_net_end";
    /* access modifiers changed from: private */
    public static Map<String, Map<String, TimeEvent>> mEvents = new ConcurrentHashMap();

    private TimeEventTracker() {
    }

    private static void putEvent(final String str, final TimeEvent timeEvent) {
        if (!TextUtils.isEmpty(str) && timeEvent != null) {
            aa.a((Runnable) new ac() {
                public void a() {
                    Map map = (Map) TimeEventTracker.mEvents.get(str);
                    if (map == null) {
                        map = new ConcurrentHashMap();
                        TimeEventTracker.mEvents.put(str, map);
                    }
                    map.put(timeEvent.getName(), timeEvent);
                }
            });
        }
    }

    public static void timeEvent(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            putEvent(str, new TimeEvent(str2));
        }
    }

    public static void addEvent(String str, TimeEvent timeEvent) {
        if (!TextUtils.isEmpty(str) && timeEvent != null) {
            putEvent(str, timeEvent);
        }
    }

    public static void endTimeEvent(String str, String str2) {
        Map map;
        TimeEvent timeEvent;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && (map = mEvents.get(str)) != null && (timeEvent = (TimeEvent) map.get(str2)) != null) {
            timeEvent.endTimeEvent();
        }
    }

    public static void pushTimeEvent(final String str) {
        if (!TextUtils.isEmpty(str)) {
            aa.a((Runnable) new ac() {
                public void a() {
                    Map map = (Map) TimeEventTracker.mEvents.get(str);
                    if (map == null) {
                        Logger.debug(TimeEventTracker.TAG, "no time events for id: " + str);
                        return;
                    }
                    Logger.debug(TimeEventTracker.TAG, "pushing time events for id: " + str);
                    HashMap hashMap = new HashMap();
                    for (Map.Entry entry : map.entrySet()) {
                        hashMap.put(entry.getKey(), Long.valueOf(((TimeEvent) entry.getValue()).getTimeTaken()));
                    }
                    b.a().a(AnalyticsEvent.Events.newEvent(TimeEventTracker.TIME_EVENT_STRING).addProperty(TimeEventTracker.PROPERTY_KEY, hashMap));
                    TimeEventTracker.mEvents.remove(str);
                }
            });
        }
    }
}
