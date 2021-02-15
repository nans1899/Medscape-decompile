package net.media.android.bidder.base.models.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import mnetinternal.c;

public final class AdUnit {
    @c(a = "sizes")
    private List<String> mAdSize;
    @c(a = "autorefresh_enabled")
    private boolean mAutoRefreshEnabled;
    @c(a = "autorefresh_interval")
    private long mAutoRefreshInterval;
    @c(a = "bidder_ids")
    private List<String> mBidders;
    @c(a = "crid")
    private String mCreativeId;
    @c(a = "ad_unit_id")
    private String mExtAdUnitId = "";
    @c(a = "enabled")
    private boolean mIsEnabled = true;
    @c(a = "supported_ads")
    private List<Integer> mSupportedAds;
    private List<Map<String, String>> mTargetCopy = null;
    @c(a = "custom_targets")
    private List<Map<String, String>> mTargets;

    public String getExtAdUnitId() {
        return this.mExtAdUnitId;
    }

    public String getCreativeId() {
        return this.mCreativeId;
    }

    public List<String> getAdSize() {
        return this.mAdSize;
    }

    public List<Integer> getSupportedAds() {
        return this.mSupportedAds;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isAutoRefreshEnabled() {
        return this.mAutoRefreshEnabled;
    }

    public long getAutoRefreshInterval() {
        return this.mAutoRefreshInterval;
    }

    public List<Map<String, String>> getTargets() {
        List<Map<String, String>> list = this.mTargets;
        if (list == null) {
            return null;
        }
        if (this.mTargetCopy == null) {
            this.mTargetCopy = Collections.unmodifiableList(list);
        }
        return this.mTargetCopy;
    }

    public List<String> getBidders() {
        return this.mBidders;
    }
}
