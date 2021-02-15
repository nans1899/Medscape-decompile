package net.media.android.bidder.base.analytics;

import mnetinternal.w;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;

public final class b {
    private static b a;
    private a b = new w();

    private b() {
        Logger.debug("##EventTracker##", "Initializing tracker in => production env");
    }

    public static b a() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    public void a(AnalyticsEvent analyticsEvent) {
        this.b.a(analyticsEvent);
    }

    public void a(String str, Object obj) {
        this.b.a(str, obj);
    }

    public void b(AnalyticsEvent analyticsEvent) {
        this.b.b(analyticsEvent);
    }
}
