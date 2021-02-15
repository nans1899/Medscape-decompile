package net.media.android.bidder.dfp;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.media.android.bidder.base.analytics.TimeEventTracker;
import net.media.android.bidder.base.bidder.AdProcessListener;
import net.media.android.bidder.base.bidder.AdProcessor;
import net.media.android.bidder.base.common.Constants;
import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.logging.MNetLog;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.MNetUser;
import net.media.android.bidder.base.models.ProcessorOptions;
import net.media.android.bidder.dfp.callback.DfpBidderCallback;

final class a {
    private Handler a = new Handler(Looper.getMainLooper());

    a() {
    }

    private static Map<String, Object> a(PublisherAdRequest publisherAdRequest) {
        HashMap hashMap = new HashMap();
        Bundle customTargeting = publisherAdRequest.getCustomTargeting();
        if (customTargeting == null) {
            return hashMap;
        }
        for (String str : customTargeting.keySet()) {
            hashMap.put(str, customTargeting.get(str));
        }
        return hashMap;
    }

    static /* synthetic */ void a(PublisherAdRequest publisherAdRequest, Map map) {
        Bundle customTargeting = publisherAdRequest.getCustomTargeting();
        if (customTargeting == null) {
            Log.d("##MNetDfpBidder##", "custom extras null");
            return;
        }
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                if (value instanceof Double) {
                    customTargeting.putDouble(str, ((Double) value).doubleValue());
                } else if (value instanceof String) {
                    customTargeting.putString(str, (String) value);
                } else if (value instanceof Float) {
                    customTargeting.putFloat(str, ((Float) value).floatValue());
                } else if (value instanceof Integer) {
                    customTargeting.putInt(str, ((Integer) value).intValue());
                } else if (value instanceof Boolean) {
                    customTargeting.putBoolean(str, ((Boolean) value).booleanValue());
                } else if (value instanceof Character) {
                    customTargeting.putChar(str, ((Character) value).charValue());
                }
            }
        }
    }

    private void a(String str, AdSize[] adSizeArr, PublisherAdView publisherAdView, PublisherAdRequest publisherAdRequest, boolean z, DfpBidderCallback dfpBidderCallback) {
        AdSize[] adSizeArr2 = adSizeArr;
        try {
            if (TextUtils.isEmpty(str)) {
                MNetLog.error("Ad unit id should not be null");
                Log.e("##MNetDfpBidder##", "Ad unit id should not be null");
                return;
            }
            if (adSizeArr2 != null) {
                if (adSizeArr2.length != 0) {
                    AdProcessor adProcessor = new AdProcessor();
                    Map<String, Object> a2 = a(publisherAdRequest);
                    String b = b(publisherAdRequest);
                    Log.d("##MNetDfpBidder##", "captured ad load");
                    Log.d("##MNetDfpBidder##", "ad unit id: " + str);
                    Log.d("##MNetDfpBidder##", "targeting params: " + a2.toString());
                    Log.d("##MNetDfpBidder##", "content link: " + b);
                    Log.d("##MNetDfpBidder##", "ad sizes: " + adProcessor.printSizes(adSizeArr2));
                    adProcessor.getCurrentActivity(publisherAdView);
                    ProcessorOptions newInstance = ProcessorOptions.newInstance(str, adSizeArr, a2, publisherAdView, b, false, (Location) null, (MNetUser) null, z);
                    final AdProcessor adProcessor2 = adProcessor;
                    final PublisherAdView publisherAdView2 = publisherAdView;
                    final String str2 = str;
                    final Map<String, Object> map = a2;
                    final String str3 = b;
                    final AdSize[] adSizeArr3 = adSizeArr;
                    AnonymousClass1 r0 = r1;
                    final DfpBidderCallback dfpBidderCallback2 = dfpBidderCallback;
                    final PublisherAdRequest publisherAdRequest2 = publisherAdRequest;
                    AnonymousClass1 r1 = new AdProcessListener() {
                        public final void onCompleted(Map<String, Object> map) {
                            Log.d("##MNetDfpBidder##", "banner adprocessor completed");
                            if (map == null) {
                                Log.d("##MNetDfpBidder##", "banner adprocessor return null extras");
                                adProcessor2.fireAdSlotDebug(publisherAdView2, str2, map, false, str3, adSizeArr3);
                                a.this.a(dfpBidderCallback2);
                                return;
                            }
                            Log.d("##MNetDfpBidder##", "banner custom targeting " + map.toString());
                            a.a(publisherAdRequest2, map);
                            a.this.a(dfpBidderCallback2);
                            adProcessor2.fireAdSlotDebug(publisherAdView2, str2, map, true, str3, adSizeArr3);
                            TimeEventTracker.timeEvent((String) map.get(Constants.HB.AD_CYCLE_ID), TimeEventTracker.EVENT_DFP_RESPONSE);
                        }

                        public final void onError(MNetError mNetError) {
                            Logger.warning("##MNetDfpBidder##", "ad prefetch failed: " + mNetError);
                            a.this.a(dfpBidderCallback2);
                        }
                    };
                    adProcessor.process(newInstance, r0);
                    return;
                }
            }
            MNetLog.error("The supported ad sizes must contain at least one valid ad size");
            Log.e("##MNetDfpBidder##", "The supported ad sizes must contain at least one valid ad size");
        } catch (Exception e) {
            Logger.notify("MNetDfpHB", e.getMessage(), e);
            a(dfpBidderCallback);
        }
    }

    private static AdSize[] a(PublisherAdView publisherAdView) {
        try {
            com.google.android.gms.ads.AdSize[] adSizes = publisherAdView.getAdSizes();
            if (adSizes != null) {
                int length = Array.getLength(adSizes);
                AdSize[] adSizeArr = new AdSize[length];
                for (int i = 0; i < length; i++) {
                    com.google.android.gms.ads.AdSize adSize = adSizes[i];
                    adSizeArr[i] = new AdSize(adSize.getWidth(), adSize.getHeight());
                }
                return adSizeArr;
            }
            com.google.android.gms.ads.AdSize adSize2 = publisherAdView.getAdSize();
            return new AdSize[]{new AdSize(adSize2.getWidth(), adSize2.getHeight())};
        } catch (Exception unused) {
            return new AdSize[]{AdSize.BANNER};
        }
    }

    private static AdSize[] a(com.google.android.gms.ads.AdSize[] adSizeArr) {
        if (adSizeArr != null) {
            try {
                int length = Array.getLength(adSizeArr);
                AdSize[] adSizeArr2 = new AdSize[length];
                for (int i = 0; i < length; i++) {
                    com.google.android.gms.ads.AdSize adSize = adSizeArr[i];
                    adSizeArr2[i] = new AdSize(adSize.getWidth(), adSize.getHeight());
                }
                return adSizeArr2;
            } catch (Exception unused) {
            }
        }
        return new AdSize[]{AdSize.BANNER};
    }

    private static String b(PublisherAdRequest publisherAdRequest) {
        Set<String> keywords;
        String contentUrl;
        if (publisherAdRequest != null && (contentUrl = publisherAdRequest.getContentUrl()) != null) {
            return contentUrl;
        }
        if (publisherAdRequest == null || (keywords = publisherAdRequest.getKeywords()) == null) {
            return null;
        }
        for (String next : keywords) {
            if (next.contains(ShareConstants.STORY_DEEP_LINK_URL)) {
                return next.substring(next.indexOf(":") + 1, next.length());
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void a(PublisherAdView publisherAdView, PublisherAdRequest publisherAdRequest, boolean z, DfpBidderCallback dfpBidderCallback) {
        a(publisherAdView.getAdUnitId(), a(publisherAdView), publisherAdView, publisherAdRequest, z, dfpBidderCallback);
    }

    /* access modifiers changed from: package-private */
    public final void a(PublisherInterstitialAd publisherInterstitialAd, PublisherAdRequest publisherAdRequest, boolean z, DfpBidderCallback dfpBidderCallback) {
        try {
            AdProcessor adProcessor = new AdProcessor();
            final String adUnitId = publisherInterstitialAd.getAdUnitId();
            final Map<String, Object> a2 = a(publisherAdRequest);
            final String b = b(publisherAdRequest);
            Log.d("##MNetDfpBidder##", "captured ad load");
            Log.d("##MNetDfpBidder##", "ad unit id: " + adUnitId);
            Log.d("##MNetDfpBidder##", "targeting params: " + a2.toString());
            Log.d("##MNetDfpBidder##", "content link: " + b);
            Activity currentActivity = adProcessor.getCurrentActivity((ViewGroup) null);
            if (currentActivity != null) {
                Log.d("##MNetDfpBidder##", "activity name: " + currentActivity.getClass().getCanonicalName());
            }
            final DfpBidderCallback dfpBidderCallback2 = dfpBidderCallback;
            final AdProcessor adProcessor2 = adProcessor;
            final PublisherAdRequest publisherAdRequest2 = publisherAdRequest;
            adProcessor.process(ProcessorOptions.newInstance(adUnitId, (AdSize[]) null, a2, (View) null, b, true, (Location) null, (MNetUser) null, z), new AdProcessListener() {
                public final void onCompleted(Map<String, Object> map) {
                    AdProcessor adProcessor;
                    ViewGroup viewGroup;
                    String str;
                    Map map2;
                    boolean z;
                    Log.d("##MNetDfpBidder##", "adprocessor completed");
                    if (map == null) {
                        Log.d("##MNetDfpBidder##", "adprocessor return null extras");
                        a.this.a(dfpBidderCallback2);
                        adProcessor = adProcessor2;
                        viewGroup = null;
                        str = adUnitId;
                        map2 = a2;
                        z = false;
                    } else {
                        Log.d("##MNetDfpBidder##", "custom targeting " + map.toString());
                        a.a(publisherAdRequest2, map);
                        Log.d("##MNetDfpBidder##", "completed callback returned");
                        a.this.a(dfpBidderCallback2);
                        TimeEventTracker.timeEvent((String) map.get(Constants.HB.AD_CYCLE_ID), TimeEventTracker.EVENT_DFP_RESPONSE);
                        adProcessor = adProcessor2;
                        viewGroup = null;
                        str = adUnitId;
                        map2 = a2;
                        z = true;
                    }
                    adProcessor.fireAdSlotDebug(viewGroup, str, map2, z, b, (AdSize[]) null);
                }

                public final void onError(MNetError mNetError) {
                    Logger.warning("##MNetDfpBidder##", "ad prefetch failed: " + mNetError);
                    a.this.a(dfpBidderCallback2);
                }
            });
        } catch (Exception e) {
            Logger.notify("MNetDfpHB", e.getMessage(), e);
            a(dfpBidderCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, com.google.android.gms.ads.AdSize[] adSizeArr, boolean z, PublisherAdRequest publisherAdRequest, DfpBidderCallback dfpBidderCallback) {
        a(str, a(adSizeArr), (PublisherAdView) null, publisherAdRequest, z, dfpBidderCallback);
    }

    /* access modifiers changed from: package-private */
    public final void a(final DfpBidderCallback dfpBidderCallback) {
        this.a.post(new Runnable() {
            public final void run() {
                if (dfpBidderCallback != null) {
                    Logger.debug("##MNetDfpBidder##", "returning to dfp");
                    dfpBidderCallback.onCompleted();
                }
            }
        });
    }
}
