package net.media.android.bidder.base.analytics;

import net.media.android.bidder.base.models.internal.AnalyticsEvent;

public interface a {
    void a(String str, Object obj);

    void a(AnalyticsEvent analyticsEvent);

    void b(AnalyticsEvent analyticsEvent);
}
