package net.media.android.bidder.base.adloader;

import android.app.Activity;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.medscape.android.cache.Cache;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.af;
import mnetinternal.ai;
import mnetinternal.ak;
import mnetinternal.an;
import mnetinternal.cs;
import mnetinternal.cv;
import mnetinternal.cx;
import mnetinternal.da;
import mnetinternal.q;
import mnetinternal.s;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.analytics.TimeEventTracker;
import net.media.android.bidder.base.analytics.b;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.common.ViewContextProvider;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.error.ErrorMessage;
import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.AdDetails;
import net.media.android.bidder.base.models.internal.AdResponse;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.BidRequest;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.HostAppContext;
import net.media.android.bidder.base.models.internal.TimeEvent;

public final class c implements AdLoader {
    private List<cs> a;
    /* access modifiers changed from: private */
    public b b;
    /* access modifiers changed from: private */
    public d c = new d(this.a);

    public c(List<cs> list) {
        this.a = list;
        this.b = new b(list);
    }

    public void loadAd(AdRequest adRequest, AdLoaderListener adLoaderListener) {
        a(adRequest, adLoaderListener);
    }

    private void a(final AdRequest adRequest, AdLoaderListener adLoaderListener) {
        List<BidResponse> list;
        final long currentTimeMillis = System.currentTimeMillis();
        b.a().a(AnalyticsEvent.Events.newEvent("ad_load_net_start").addProperty("ad_unit", adRequest.getAdUnitId()).addProperty(Cache.Caches.TIME, Long.valueOf(currentTimeMillis)));
        TimeEventTracker.timeEvent(adRequest.getAdCycleId(), TimeEventTracker.EVENT_DP_RESPONSE);
        int timeout = adRequest.getTimeout();
        Logger.debug("##MNetAdLoader##", "request timeout " + timeout);
        if (adRequest.isInterstitial()) {
            list = s.a().a(adRequest.getAdUnitId());
        } else {
            list = s.a().a(adRequest.getAdUnitId(), Arrays.asList(adRequest.getAdSizes()), adRequest.getHostAppContext().getCrawlerLink());
        }
        List<BidResponse> list2 = list;
        if (timeout <= 1 || this.b.a(adRequest.getAdUnitId(), list2)) {
            if (list2 == null || list2.isEmpty()) {
                Logger.debug("##MNetAdLoader##", "No prefetched bids, sync request made. No bid.");
                a((Throwable) new Exception("No-Ad"), adRequest, adLoaderListener);
                aa.a((Runnable) new ac() {
                    public void a() {
                        q.a(adRequest.getAdUnitId(), adRequest.getHostAppContext());
                    }
                });
                return;
            }
            Logger.debug("##MNetAdLoader##", "we have bids for all bidders, going for client side auction");
            AdResponse a2 = this.b.a(adRequest, list2, currentTimeMillis);
            if (a2 != null) {
                adLoaderListener.onSuccess(a2);
                a(a2);
                return;
            }
        }
        final AdRequest adRequest2 = adRequest;
        final AdLoaderListener adLoaderListener2 = adLoaderListener;
        final List<BidResponse> list3 = list2;
        af.b(new ai.a(an.e()).a(timeout).a(BidRequest.createForAdRequest(adRequest, list2).toJson()).a(), new ak<a>() {
            public Class<a> a() {
                return a.class;
            }

            public void a(a aVar) {
                c.this.a(adRequest2, aVar, currentTimeMillis, adLoaderListener2);
                c.this.a(adRequest2.getAdUnitId(), (List<BidResponse>) list3, aVar);
            }

            public void a(Throwable th) {
                if (!cx.b(MNet.getContext())) {
                    c.this.a(new Throwable("no internet connection"), adRequest2, adLoaderListener2);
                    return;
                }
                List<BidResponse> list = list3;
                if (list == null || list.isEmpty()) {
                    Logger.debug("##MNetAdLoader##", "cachebids nil or empty");
                    if (adRequest2.isInterstitial()) {
                        list = s.a().a(adRequest2.getAdUnitId());
                    } else {
                        list = s.a().a(adRequest2.getAdUnitId(), Arrays.asList(adRequest2.getAdSizes()), adRequest2.getHostAppContext().getCrawlerLink());
                    }
                    if (list == null || list.isEmpty()) {
                        AdResponse a2 = c.this.c.a(adRequest2, currentTimeMillis);
                        if (a2 == null) {
                            c.this.a(th, adRequest2, adLoaderListener2);
                            aa.a((Runnable) new ac() {
                                public void a() {
                                    q.a(adRequest2.getAdUnitId(), adRequest2.getHostAppContext());
                                }
                            });
                            return;
                        }
                        adLoaderListener2.onSuccess(a2);
                        c.this.a(a2);
                        return;
                    }
                }
                Logger.debug("##MNetAdLoader##", "going for client side auction " + adRequest2.getAdUnitId());
                AdResponse a3 = c.this.b.a(adRequest2, list, currentTimeMillis);
                if (a3 == null) {
                    c.this.a(th, adRequest2, adLoaderListener2);
                    aa.a((Runnable) new ac() {
                        public void a() {
                            q.a(adRequest2.getAdUnitId(), adRequest2.getHostAppContext());
                        }
                    });
                    return;
                }
                adLoaderListener2.onSuccess(a3);
                c.this.a(a3);
            }
        });
        a(list2, adRequest.getHostAppContext());
    }

    /* access modifiers changed from: private */
    public void a(final AdRequest adRequest, a aVar, final long j, AdLoaderListener adLoaderListener) {
        try {
            AdDetails a2 = aVar.a(adRequest.getAdUnitId());
            if (a2 == null) {
                a((Throwable) new MNetError(ErrorMessage.NO_FILL.toString(), 3), adRequest, adLoaderListener);
                return;
            }
            AdResponse adResponse = new AdResponse(a(adRequest, adRequest.getAdUnitId(), aVar, a2.getBidResponses()), adRequest);
            adLoaderListener.onSuccess(adResponse);
            a(adRequest, aVar);
            a(adResponse);
            a(a2.getAuctionProcessLogs());
            TimeEventTracker.endTimeEvent(adRequest.getAdCycleId(), TimeEventTracker.EVENT_DP_RESPONSE);
            aa.a((Runnable) new ac() {
                public void a() {
                    q.a(adRequest.getAdUnitId(), adRequest.getHostAppContext());
                    b.a().a(AnalyticsEvent.Events.newEvent("ad_load_net_end").addProperty("error", false).addProperty("ad_unit", adRequest.getAdUnitId()).addProperty(Cache.Caches.TIME, Long.valueOf(System.currentTimeMillis())).addProperty("app_link", adRequest.getHostAppContext().getCrawlerLink()).addProperty("net_time", Long.valueOf(System.currentTimeMillis() - j)));
                }
            });
        } catch (Exception e) {
            Logger.notify("##MNetAdLoader##", "error at ad load success", e);
            if (0 == 0) {
                adLoaderListener.onError(new MNetError(ErrorMessage.INTERNAL_ERROR.toString()));
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Throwable th, final AdRequest adRequest, AdLoaderListener adLoaderListener) {
        Logger.debug("##MNetAdLoader##", "ad load error: " + th.getMessage());
        b.a().a(AnalyticsEvent.Events.newEvent("ad_load_net_end").addProperty("ad_unit", adRequest.getAdUnitId()).addProperty("error", true).addProperty(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, th.getMessage()).addProperty("app_link", adRequest.getHostAppContext().getCrawlerLink()).addProperty(Cache.Caches.TIME, Long.valueOf(System.currentTimeMillis())));
        aa.a((Runnable) new ac() {
            public void a() {
                q.a(adRequest.getAdUnitId(), adRequest.getHostAppContext());
            }
        });
        if (th instanceof ConnectException) {
            adLoaderListener.onError(new MNetError(ErrorMessage.NETWORK_ERROR.toString(), 2));
        } else if (th.getMessage() == null || !th.getMessage().contains("No-Ad")) {
            adLoaderListener.onError(new MNetError(ErrorMessage.AD_LOAD_ERROR.toString(), 5));
        } else {
            adLoaderListener.onError(new MNetError(ErrorMessage.NO_FILL.toString(), 3));
        }
    }

    public void prefetchAd(final AdRequest adRequest, final a aVar) {
        aa.a((Runnable) new Runnable() {
            public void run() {
                BidRequest createForBidPrefetch = BidRequest.createForBidPrefetch(adRequest.getActivityName(), adRequest.getAdUnitId(), adRequest.getHostAppContext());
                af.b(new ai.a(an.f()).a(createForBidPrefetch.toJson()).a(), new ak<a>() {
                    public Class<a> a() {
                        return a.class;
                    }

                    public void a(a aVar) {
                        if (aVar == null || aVar.a() == null) {
                            Logger.debug("##MNetAdLoader##", "no predicted bids, returning");
                            return;
                        }
                        for (Map.Entry next : aVar.a().entrySet()) {
                            aVar.a((String) next.getKey(), c.this.a(adRequest, (String) next.getKey(), aVar, ((AdDetails) next.getValue()).getBidResponses()));
                        }
                    }

                    public void a(Throwable th) {
                        if (th == null) {
                            aVar.a(new MNetError(ErrorMessage.INTERNAL_ERROR.toString(), 5));
                        } else if (th instanceof ConnectException) {
                            aVar.a(new MNetError(ErrorMessage.NETWORK_ERROR.toString(), 2));
                        } else if (th.getMessage() != null) {
                            aVar.a(new MNetError(th.getMessage(), 5));
                        } else {
                            aVar.a(new MNetError(ErrorMessage.INTERNAL_ERROR.toString(), 5));
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public List<BidResponse> a(AdRequest adRequest, String str, a aVar, List<BidResponse> list) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        for (cs a2 : this.a) {
            list = a2.a(list, str, adRequest, aVar);
        }
        return list;
    }

    /* access modifiers changed from: private */
    public void a(final String str, final List<BidResponse> list, final a aVar) {
        if (list != null && !list.isEmpty()) {
            aa.a((Runnable) new ac() {
                public void a() {
                    if (AdUnitConfig.getInstance().getPublisherConfig().shouldReuseBids()) {
                        AdDetails a2 = aVar.a(str);
                        if (a2 == null || a2.getBidResponses() == null || a2.getBidResponses().isEmpty()) {
                            s.a().a(str, list);
                            return;
                        }
                        BidResponse bidResponse = a2.getBidResponses().get(0);
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            BidResponse bidResponse2 = (BidResponse) it.next();
                            if (bidResponse2.isFirstPartyBid() && bidResponse2.getCreativeId().equals(bidResponse.getCreativeId())) {
                                it.remove();
                            }
                        }
                        s.a().a(str, list);
                    }
                }
            });
        }
    }

    private void a(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                Logger.debug("##MNetAdLoader##", "firing ap log: " + str);
                a(str, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, final int i) {
        if (str != null && !TextUtils.isEmpty(str)) {
            aa.a(new ac() {
                public void a() {
                    af.a(new ai.a(str).a(), new ak<String>() {
                        public Class<String> a() {
                            return String.class;
                        }

                        public void a(String str) {
                            Logger.debug("##MNetAdLoader##", "ap log fired");
                        }

                        public void a(Throwable th) {
                            Logger.warning("##MNetAdLoader##", "ap logs failed: ", th);
                            if (i < 5) {
                                c.this.a(str, i + 1);
                                Logger.debug("##MNetAdLoader##", "retrying count " + i);
                            }
                        }
                    });
                }
            }, (long) (i * 500), TimeUnit.MILLISECONDS);
        }
    }

    private void a(final List<BidResponse> list, final HostAppContext hostAppContext) {
        if (list != null && !list.isEmpty()) {
            Activity a2 = da.a();
            final String localClassName = a2 != null ? a2.getLocalClassName() : "";
            aa.a((Runnable) new ac() {
                public void a() {
                    String str;
                    for (BidResponse bidResponse : list) {
                        if (!TextUtils.isEmpty(bidResponse.getPredictionId())) {
                            b a2 = b.a();
                            AnalyticsEvent addProperty = AnalyticsEvent.Events.newEvent("predicted_bid_auction_participation").addProperty("prediction_id", bidResponse.getPredictionId()).addProperty("activity", localClassName);
                            HostAppContext hostAppContext = hostAppContext;
                            if (hostAppContext == null) {
                                str = "";
                            } else {
                                str = hostAppContext.getCrawlerLink();
                            }
                            a2.a(addProperty.addProperty(ShareConstants.STORY_DEEP_LINK_URL, str));
                        }
                    }
                }
            });
        }
    }

    private void a(final AdRequest adRequest, final a aVar) {
        aa.a((Runnable) new ac() {
            public void a() {
                HostAppContext hostAppContext = adRequest.getHostAppContext();
                if (aVar.e() != null) {
                    TimeEventTracker.addEvent(adRequest.getAdCycleId(), new TimeEvent(TimeEventTracker.EVENT_RTB_DELAY, aVar.e().getRtbDelay()));
                    TimeEventTracker.addEvent(adRequest.getAdCycleId(), new TimeEvent(TimeEventTracker.EVENT_DP_DELAY, aVar.e().getDpDelay()));
                }
                try {
                    TimeEventTracker.addEvent(adRequest.getAdCycleId(), new TimeEvent(TimeEventTracker.EVENT_DP_LATENCY, cv.a(an.b(), 3000)));
                } catch (IOException unused) {
                }
                if (hostAppContext != null && !aVar.c() && AdUnitConfig.getInstance().getPublisherConfig().isCrawlingEnabled()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    String viewContent = ViewContextProvider.getViewContent();
                    long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    if (!TextUtils.isEmpty(viewContent)) {
                        Logger.debug("##MNetAdLoader##", "pushing activity content");
                        b.a().a(AnalyticsEvent.Events.newEvent("activity_context").addProperty("ad_unit", adRequest.getAdUnitId()).addProperty(Constants.HB.AD_CYCLE_ID, adRequest.getAdCycleId()).addProperty("app_link", hostAppContext.getAppLink()).addProperty("crawler_link", hostAppContext.getCrawlerLink()).addProperty("time_taken", Long.valueOf(currentTimeMillis2)).addProperty("content", viewContent));
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final AdResponse adResponse) {
        aa.a((Runnable) new ac() {
            public void a() {
                String str;
                b a2 = b.a();
                AnalyticsEvent newEvent = AnalyticsEvent.Events.newEvent("impression");
                if (adResponse.getWinningBid() == null) {
                    str = "";
                } else {
                    str = adResponse.getWinningBid().getContextLink();
                }
                a2.a(newEvent.addProperty(ShareConstants.STORY_DEEP_LINK_URL, str).addProperty("winning_bid", adResponse.getWinningBid()).addProperty("participants", adResponse.getBidResponses()).addProperty("client_side_auction", Boolean.valueOf(adResponse.isClientAuction())).addProperty("reuse", false).addProperty(Constants.HB.AD_CYCLE_ID, adResponse.getAdRequest().getAdCycleId()));
            }
        });
    }
}
