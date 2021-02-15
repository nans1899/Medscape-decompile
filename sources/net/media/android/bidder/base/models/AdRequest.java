package net.media.android.bidder.base.models;

import android.location.Location;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import mnetinternal.da;
import net.media.android.bidder.base.common.ViewContextProvider;
import net.media.android.bidder.base.models.internal.HostAppContext;

public final class AdRequest implements Cloneable {
    private boolean isInterstitial;
    private String mActivityName;
    private String mAdCycleId;
    private AdSize[] mAdSizes;
    private int mAdType;
    private String mAdUnitId;
    private HostAppContext mAppContext;
    private boolean mChildDirectedContent;
    private Map<String, String> mExtras;
    private boolean mIsInternal;
    private String mKeywords;
    private Location mLocation;
    private int mTimeout;

    private AdRequest() {
        this.mIsInternal = false;
        this.mTimeout = 0;
        this.isInterstitial = false;
        this.mExtras = new HashMap();
    }

    public int getTimeout() {
        return this.mTimeout;
    }

    /* access modifiers changed from: private */
    public void setTimeout(int i) {
        this.mTimeout = i;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    /* access modifiers changed from: private */
    public void setAdUnitId(String str) {
        this.mAdUnitId = str == null ? null : str.trim();
    }

    public int getAdType() {
        return this.mAdType;
    }

    /* access modifiers changed from: private */
    public void setAdType(int i) {
        this.mAdType = i;
    }

    public AdSize[] getAdSizes() {
        AdSize[] adSizeArr = this.mAdSizes;
        if (adSizeArr != null) {
            return (AdSize[]) Arrays.copyOf(adSizeArr, adSizeArr.length);
        }
        return new AdSize[]{AdSize.BANNER, AdSize.MEDIUM};
    }

    /* access modifiers changed from: private */
    public void setAdSizes(AdSize... adSizeArr) {
        this.mAdSizes = adSizeArr;
    }

    public String getKeywords() {
        return this.mKeywords;
    }

    /* access modifiers changed from: private */
    public void setKeywords(String str) {
        this.mKeywords = str;
    }

    /* access modifiers changed from: private */
    public void setAdCycleId(String str) {
        this.mAdCycleId = str;
    }

    public String getAdCycleId() {
        return this.mAdCycleId;
    }

    public HostAppContext getHostAppContext() {
        return this.mAppContext;
    }

    /* access modifiers changed from: private */
    public void setAppContext(HostAppContext hostAppContext) {
        this.mAppContext = hostAppContext;
    }

    public boolean isInternal() {
        return this.mIsInternal;
    }

    /* access modifiers changed from: private */
    public void setInternal(boolean z) {
        this.mIsInternal = z;
    }

    public boolean isInterstitial() {
        return this.isInterstitial;
    }

    /* access modifiers changed from: private */
    public void setInterstitial(boolean z) {
        this.isInterstitial = z;
    }

    /* access modifiers changed from: private */
    public void setActivityName(String str) {
        this.mActivityName = str;
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    /* access modifiers changed from: private */
    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public boolean isChildDirectedContent() {
        return this.mChildDirectedContent;
    }

    public static class Builder {
        private AdRequest mAdRequest = new AdRequest();
        private HostAppContext mAppContext;

        public Builder setAdUnitId(String str) {
            this.mAdRequest.setAdUnitId(str);
            return this;
        }

        public Builder setAdType(int i) {
            this.mAdRequest.setAdType(i);
            return this;
        }

        public Builder setAdSizes(AdSize... adSizeArr) {
            this.mAdRequest.setAdSizes(adSizeArr);
            return this;
        }

        public Builder setKeywords(String str) {
            this.mAdRequest.setKeywords(str);
            return this;
        }

        public Builder setInternal(boolean z) {
            this.mAdRequest.setInternal(z);
            return this;
        }

        public Builder setHostAppContext(HostAppContext hostAppContext) {
            this.mAppContext = hostAppContext;
            return this;
        }

        public Builder setTimeout(int i) {
            this.mAdRequest.setTimeout(i);
            return this;
        }

        public Builder setInterstitial(boolean z) {
            this.mAdRequest.setInterstitial(z);
            return this;
        }

        public Builder setActivityName(String str) {
            this.mAdRequest.setActivityName(str);
            return this;
        }

        public Builder setLocation(Location location) {
            this.mAdRequest.setLocation(location);
            return this;
        }

        public AdRequest build() {
            this.mAdRequest.setAdCycleId(da.b());
            HostAppContext hostAppContext = this.mAppContext;
            if (hostAppContext == null || hostAppContext.isNull()) {
                this.mAdRequest.setAppContext(ViewContextProvider.getViewContext());
            } else {
                this.mAdRequest.setAppContext(this.mAppContext);
            }
            return this.mAdRequest;
        }
    }
}
