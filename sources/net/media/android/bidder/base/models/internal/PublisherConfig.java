package net.media.android.bidder.base.models.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mnetinternal.c;

public final class PublisherConfig {
    @c(a = "ad_units")
    private List<AdUnit> mAdUnits;
    @c(a = "adview_cache_max")
    private int mAdViewCacheMax = 3;
    @c(a = "adview_cache_timeout")
    private int mAdViewCacheTimeout = 30;
    @c(a = "adview_reuse_enabled")
    private boolean mAdViewReuseEnabled = true;
    @c(a = "append_keywords_requrl")
    private boolean mAppendKeywordsReqUrl = false;
    @c(a = "mnet_ag_bid_enabled")
    private boolean mBidPrefetchEnabled = false;
    @c(a = "b_ru")
    private boolean mBidReuse = false;
    @c(a = "is_rtc_enabled")
    private boolean mCrawlingEnabled;
    @c(a = "default_bids")
    private List<DefaultBid> mDefaultBids;
    @c(a = "disable_ads_without_consent")
    private boolean mDisableAdsWithoutConsent = false;
    @c(a = "enabled")
    private boolean mIsBidderEnabled = true;
    @c(a = "mnet_inapp_browsing_enabled")
    private boolean mIsInAppBrowsingEnabled = true;
    @c(a = "device_permissions")
    private List<String> mPermissions = new ArrayList();
    @c(a = "loaders")
    private Map<String, Object> mShimmerLoders;
    @c(a = "enable_pulse")
    private boolean mShouldEnablePulse = false;
    @c(a = "mn_se")
    private boolean mShouldEncode = true;
    @c(a = "lc_msk")
    private boolean mShouldMaskLocation = true;
    @c(a = "push_errors")
    private boolean mShouldPushErrors = false;
    @c(a = "should_use_gzip")
    private boolean mShouldUseGzip = false;
    @c(a = "mnet_should_shimmer")
    private boolean mShowShimmer = false;
    @c(a = "wh_p_ev")
    private List<String> mWhiteListedPulseEvents = new ArrayList();

    public boolean isAdViewReuseEnabled() {
        return this.mAdViewReuseEnabled;
    }

    public int getAdViewCacheTimeout() {
        return this.mAdViewCacheTimeout;
    }

    public int getAdViewCacheMax() {
        return this.mAdViewCacheMax;
    }

    public boolean shouldUseGzip() {
        return this.mShouldUseGzip;
    }

    public boolean isBidderEnabled() {
        return this.mIsBidderEnabled;
    }

    public List<AdUnit> getAdUnits() {
        return this.mAdUnits;
    }

    public List<DefaultBid> getDefaultBids() {
        return this.mDefaultBids;
    }

    public boolean isCrawlingEnabled() {
        return this.mCrawlingEnabled;
    }

    public boolean isInAppBrowsingEnabled() {
        return this.mIsInAppBrowsingEnabled;
    }

    public boolean appendKeywordsReqUrl() {
        return this.mAppendKeywordsReqUrl;
    }

    public Map<String, Object> getShimmerLoaders() {
        return this.mShimmerLoders;
    }

    public boolean isBidPrefetchEnabled() {
        return this.mBidPrefetchEnabled;
    }

    public boolean showShimmer() {
        return this.mShowShimmer;
    }

    public boolean disableAdsWithoutConsent() {
        return this.mDisableAdsWithoutConsent;
    }

    public boolean isPulseEnabled() {
        return this.mShouldEnablePulse;
    }

    public List<String> pulseEventWhiteList() {
        if (this.mWhiteListedPulseEvents == null) {
            this.mWhiteListedPulseEvents = new ArrayList();
        }
        return this.mWhiteListedPulseEvents;
    }

    public boolean shouldMaskLocation() {
        return this.mShouldMaskLocation;
    }

    public boolean shouldPushErrors() {
        return this.mShouldPushErrors;
    }

    public List<String> getPermissions() {
        return this.mPermissions;
    }

    public boolean shouldEncode() {
        return this.mShouldEncode;
    }

    public boolean shouldReuseBids() {
        return this.mBidReuse;
    }
}
