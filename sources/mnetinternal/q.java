package mnetinternal;

import java.lang.ref.WeakReference;
import java.util.List;
import net.media.android.bidder.base.common.ViewContextProvider;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.factory.AdLoaderFactory;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.internal.ActivityTrackerEvent;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.HostAppContext;

public final class q {
    private static q a;

    private q() {
        x.a().a(AdTrackerEvent.EVENT_ACTIVITY_RESUMED, new a(this));
    }

    public static void a() {
        if (a == null) {
            a = new q();
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, final String str2, final HostAppContext hostAppContext) {
        if (!AdUnitConfig.getInstance().getPublisherConfig().isBidPrefetchEnabled()) {
            Logger.debug("##BidPrefetcher##", "bid pre-fetching is disabled");
            return;
        }
        Logger.debug("##BidPrefetcher##", "pre-fetching bids for: " + str);
        aa.a((Runnable) new ac() {
            public void a() {
                AdLoaderFactory.getAdLoader().prefetchAd(new AdRequest.Builder().setHostAppContext(hostAppContext).setActivityName(str).setAdUnitId(str2).build(), new net.media.android.bidder.base.adloader.a() {
                    public void a(String str, List<BidResponse> list) {
                        Logger.debug("##BidPrefetcher##", "got " + list.size() + " bids");
                        s.a().a(str, list);
                    }

                    public void a(MNetError mNetError) {
                        Logger.error("##BidPrefetcher##", "bids pre-fetching error: " + mNetError.getMessage());
                    }
                });
            }
        });
    }

    public static void a(String str, HostAppContext hostAppContext) {
        a.a("", str, hostAppContext);
    }

    private static class a implements z<ActivityTrackerEvent> {
        WeakReference<q> a;

        a(q qVar) {
            this.a = new WeakReference<>(qVar);
        }

        public void a(ActivityTrackerEvent activityTrackerEvent) {
            q qVar = (q) this.a.get();
            if (qVar != null) {
                String eventType = activityTrackerEvent.getEventType();
                char c = 65535;
                if (eventType.hashCode() == 975015852 && eventType.equals(AdTrackerEvent.EVENT_ACTIVITY_RESUMED)) {
                    c = 0;
                }
                if (c == 0) {
                    qVar.a(activityTrackerEvent.getActivityName(), "", ViewContextProvider.getViewContext());
                }
            }
        }
    }
}
