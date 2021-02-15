package mnetinternal;

import android.text.TextUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.analytics.a;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.logging.b;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.Event;
import net.media.android.bidder.base.models.internal.Logger;

public final class w implements a {
    /* access modifiers changed from: private */
    public Map<String, Object> a = new ConcurrentHashMap();

    private String a(int i) {
        switch (i) {
            case 2:
                return "device";
            case 4:
                return Logger.NETWORK;
            case 5:
                return "location";
            case 6:
                return Logger.USER_AGENT;
            case 7:
                return Logger.TIMEZONE;
            case 8:
                return "address";
            case 9:
                return Logger.PUBLISHER;
            default:
                return "event";
        }
    }

    public void a(String str, Object obj) {
        if (!TextUtils.isEmpty(str)) {
            this.a.put(str, obj);
        }
    }

    public void a(final AnalyticsEvent analyticsEvent) {
        if (analyticsEvent != null && !d(analyticsEvent) && !e(analyticsEvent)) {
            aa.a((Runnable) new ac() {
                public void a() {
                    b.a().a(new Logger(w.this.c(analyticsEvent), new Event.Builder().setAdvertisingId(cv.d(MNet.getContext())).setType(analyticsEvent.getName()).setParams(analyticsEvent.getProperties()).setUserProperties(w.this.a).build()));
                }
            });
        }
    }

    public void b(final AnalyticsEvent analyticsEvent) {
        if (analyticsEvent != null && !d(analyticsEvent) && !e(analyticsEvent)) {
            aa.a((Runnable) new ac() {
                public void a() {
                    b.a().a(new Logger(w.this.c(analyticsEvent), new Event.Builder().setAdvertisingId(cv.d(MNet.getContext())).setType(analyticsEvent.getName()).setParams(analyticsEvent.getProperties()).setUserProperties(w.this.a).build()));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public String c(AnalyticsEvent analyticsEvent) {
        if (analyticsEvent.getType() == 1) {
            return analyticsEvent.getName();
        }
        return a(analyticsEvent.getType());
    }

    private boolean d(AnalyticsEvent analyticsEvent) {
        int type = analyticsEvent.getType();
        return net.media.android.bidder.base.common.b.a().e() && (type == 2 || type == 4 || type == 5 || type == 6 || type == 7 || type == 8 || type == 9);
    }

    private boolean e(AnalyticsEvent analyticsEvent) {
        List<String> pulseEventWhiteList;
        if (!AdUnitConfig.getInstance().getPublisherConfig().isPulseEnabled() || (pulseEventWhiteList = AdUnitConfig.getInstance().getPublisherConfig().pulseEventWhiteList()) == null) {
            return true;
        }
        return !pulseEventWhiteList.contains(analyticsEvent.getName());
    }
}
