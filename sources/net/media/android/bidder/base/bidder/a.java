package net.media.android.bidder.base.bidder;

import android.app.Activity;
import android.location.Location;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import mnetinternal.aa;
import mnetinternal.ac;
import mnetinternal.ck;
import mnetinternal.da;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.adloader.AdLoader;
import net.media.android.bidder.base.adloader.AdLoaderListener;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.common.ViewContextProvider;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.configs.c;
import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.factory.AdLoaderFactory;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.macro.ServerExtraMacros;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.MNetUser;
import net.media.android.bidder.base.models.ProcessorOptions;
import net.media.android.bidder.base.models.internal.AdResponse;
import net.media.android.bidder.base.models.internal.AdUnit;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.HostAppContext;

final class a {
    a() {
    }

    public void a(ProcessorOptions processorOptions, final AdProcessListener adProcessListener) {
        if (processorOptions == null) {
            Logger.error("##AdProcessor##", "Processor options shouldn't be null");
            adProcessListener.onError(new MNetError("Invalid Input"));
        } else if (AdUnitConfig.getInstance().disableAdsWithoutConsent() && b.a().d()) {
            Logger.debug("##AdProcessor##", "gdpr consent not present, aborting ad load");
            net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("disabled_for_gdpr"));
            adProcessListener.onError(new MNetError("Gdpr consent not present"));
        } else if (!AdUnitConfig.getInstance().isBidderEnabled()) {
            Logger.debug("##AdProcessor##", "header bidder is disabled");
            adProcessListener.onError(new MNetError("header bidder is disabled"));
        } else {
            AdRequest.Builder builder = new AdRequest.Builder();
            AdUnit adUnit = AdUnitConfig.getInstance().getAdUnit(processorOptions.getAdUnitId(), processorOptions.getTargeting(), processorOptions.getAdView());
            if (adUnit == null || !adUnit.isEnabled()) {
                Logger.warning("##AdProcessor##", "publisher mapping not found or ad unit is disabled adunid id " + processorOptions.getAdUnitId() + " and targeting params " + processorOptions.getTargeting().toString());
                adProcessListener.onError(new MNetError("publisher mapping not found or ad unit is disabled"));
                return;
            }
            MNetUser user = processorOptions.getUser();
            if (user != null && !b.a().e()) {
                MNet.setMNetUser("bidder_user", user.getGender(), user.getYob(), (Map<String, Object>) null, (String) null);
            }
            int a = net.media.android.bidder.base.configs.b.a().a("gptrd");
            if (processorOptions.isSync()) {
                a = 1;
            }
            int a2 = net.media.android.bidder.base.configs.b.a().a("hb_delay_extra");
            builder.setAdUnitId(adUnit.getCreativeId()).setHostAppContext(new HostAppContext(processorOptions.getContextLink(), processorOptions.getContextLink())).setLocation(processorOptions.getLocation()).setInternal(false).setTimeout(a);
            if (!processorOptions.isInterstitial()) {
                builder.setAdSizes(processorOptions.getAdSizes());
            }
            builder.setInterstitial(processorOptions.isInterstitial());
            AdLoader adLoader = AdLoaderFactory.getAdLoader();
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            final AdRequest build = builder.build();
            adLoader.loadAd(build, new AdLoaderListener() {
                public void onSuccess(AdResponse adResponse) {
                    if (countDownLatch.getCount() == 0) {
                        Logger.debug("##AdProcessor##", "Ad fallback timeout already triggered");
                        return;
                    }
                    countDownLatch.countDown();
                    HashMap hashMap = new HashMap();
                    if (adResponse == null || adResponse.getBidResponses() == null) {
                        adProcessListener.onError(new MNetError("NO-AD"));
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (BidResponse next : adResponse.getBidResponses()) {
                        if (!next.isAdx()) {
                            arrayList.add(next);
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        BidResponse bidResponse = (BidResponse) it.next();
                        if (!bidResponse.isAdx()) {
                            for (Map.Entry next2 : bidResponse.getServerExtras().entrySet()) {
                                Object value = next2.getValue();
                                if (next2.getValue() instanceof String) {
                                    value = ServerExtraMacros.process((String) next2.getValue(), bidResponse);
                                }
                                hashMap.put(next2.getKey(), value);
                            }
                        }
                    }
                    hashMap.put(Constants.HB.AD_CYCLE_ID, build.getAdCycleId());
                    adProcessListener.onCompleted(hashMap);
                }

                public void onError(MNetError mNetError) {
                    if (countDownLatch.getCount() == 0) {
                        Logger.debug("##AdProcessor##", "Ad fallback timeout already triggered");
                        return;
                    }
                    countDownLatch.countDown();
                    adProcessListener.onError(mNetError);
                }
            });
            aa.a(new ac() {
                public void a() {
                    if (countDownLatch.getCount() > 0) {
                        countDownLatch.countDown();
                        adProcessListener.onError(new MNetError("TIMEOUT"));
                        Logger.error("##AdProcessor##", "Ad fallback timeout with latch");
                        net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("ad_timeout_fallback").addProperty(Constants.HB.AD_CYCLE_ID, build.getAdCycleId()));
                    }
                }
            }, (long) (a + a2), TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    public String a(AdSize[] adSizeArr) {
        if (adSizeArr == null || adSizeArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (AdSize adSize : adSizeArr) {
            sb.append(adSize.toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(ViewGroup viewGroup, String str, Map<String, Object> map, boolean z, String str2, AdSize[] adSizeArr) {
        if (Math.random() <= c.a().c("mnet_slot_debug_rate")) {
            try {
                AnalyticsEvent newEvent = AnalyticsEvent.Events.newEvent("adslot_debug");
                newEvent.addProperty("ad_unit_id", str).addProperty("provider", "dfp").addProperty("targeting_params", map).addProperty("targeted", Boolean.valueOf(z));
                if (str2 != null) {
                    newEvent.addProperty("req_url", str2);
                } else {
                    newEvent.addProperty("req_url", ViewContextProvider.getViewContext().getCrawlerLink());
                }
                if (adSizeArr != null) {
                    newEvent.addProperty("adsizes", adSizeArr);
                }
                newEvent.addProperty("device", ck.a().a(b.a().e(), (Location) null));
                if (viewGroup != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("vw", Integer.valueOf(viewGroup.getWidth()));
                    hashMap.put("vh", Integer.valueOf(viewGroup.getHeight()));
                    hashMap.put(com.medscape.android.Constants.CONSULT_DEEPLINK_TOP_POSTS, Integer.valueOf(viewGroup.getTop()));
                    hashMap.put("left", Integer.valueOf(viewGroup.getLeft()));
                    hashMap.put("right", Integer.valueOf(viewGroup.getRight()));
                    hashMap.put("bottom", Integer.valueOf(viewGroup.getBottom()));
                    newEvent.addProperty("pos", hashMap);
                    newEvent.addProperty("ad_view_id", da.b((View) viewGroup)).addProperty("parent_id", da.a((View) viewGroup));
                } else {
                    newEvent.addProperty("type", "interstitial");
                }
                Activity a = a(viewGroup);
                if (a != null) {
                    newEvent.addProperty("activity_name", a.getClass().getCanonicalName());
                }
                net.media.android.bidder.base.analytics.b.a().a(newEvent);
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Activity a(ViewGroup viewGroup) {
        Activity c = viewGroup != null ? da.c((View) viewGroup) : null;
        return c == null ? da.a() : c;
    }
}
