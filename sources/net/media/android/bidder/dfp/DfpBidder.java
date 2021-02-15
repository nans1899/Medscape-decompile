package net.media.android.bidder.dfp;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import net.media.android.bidder.dfp.callback.DfpBidderCallback;

public final class DfpBidder {
    private static final a sInstance = new a();

    private DfpBidder() {
    }

    public static void addBids(PublisherAdView publisherAdView, PublisherAdRequest publisherAdRequest, DfpBidderCallback dfpBidderCallback) {
        sInstance.a(publisherAdView, publisherAdRequest, false, dfpBidderCallback);
    }

    public static void addBids(PublisherInterstitialAd publisherInterstitialAd, PublisherAdRequest publisherAdRequest, DfpBidderCallback dfpBidderCallback) {
        sInstance.a(publisherInterstitialAd, publisherAdRequest, false, dfpBidderCallback);
    }

    public static void addBids(String str, AdSize[] adSizeArr, PublisherAdRequest publisherAdRequest, DfpBidderCallback dfpBidderCallback) {
        sInstance.a(str, adSizeArr, false, publisherAdRequest, dfpBidderCallback);
    }

    public static void addBidsSync(PublisherAdView publisherAdView, PublisherAdRequest publisherAdRequest) {
        sInstance.a(publisherAdView, publisherAdRequest, true, (DfpBidderCallback) null);
    }

    public static void addBidsSync(PublisherInterstitialAd publisherInterstitialAd, PublisherAdRequest publisherAdRequest) {
        sInstance.a(publisherInterstitialAd, publisherAdRequest, true, (DfpBidderCallback) null);
    }

    public static void addBidsSync(String str, AdSize[] adSizeArr, PublisherAdRequest publisherAdRequest) {
        sInstance.a(str, adSizeArr, true, publisherAdRequest, (DfpBidderCallback) null);
    }
}
