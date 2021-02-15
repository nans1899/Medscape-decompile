package net.media.android.bidder.base.models;

import android.location.Location;
import android.view.View;
import java.util.Map;

public final class ProcessorOptions {
    private AdSize[] mAdSizes;
    private String mAdUnitId;
    private View mAdView;
    private String mContextLink;
    private boolean mIsInterstitial;
    private Location mLocation;
    private Map<String, Object> mTargeting;
    private MNetUser mUser;
    private boolean sync;

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public void setAdUnitId(String str) {
        this.mAdUnitId = str;
    }

    public AdSize[] getAdSizes() {
        return this.mAdSizes;
    }

    public void setAdSizes(AdSize[] adSizeArr) {
        this.mAdSizes = adSizeArr;
    }

    public Map<String, Object> getTargeting() {
        return this.mTargeting;
    }

    private void setTargeting(Map<String, Object> map) {
        this.mTargeting = map;
    }

    public View getAdView() {
        return this.mAdView;
    }

    private void setAdView(View view) {
        this.mAdView = view;
    }

    public String getContextLink() {
        return this.mContextLink;
    }

    private void setContextLink(String str) {
        this.mContextLink = str;
    }

    public boolean isInterstitial() {
        return this.mIsInterstitial;
    }

    private void setIsInterstitial(boolean z) {
        this.mIsInterstitial = z;
    }

    private void setIsSync(boolean z) {
        this.sync = z;
    }

    public boolean isSync() {
        return this.sync;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    private void setLocation(Location location) {
        this.mLocation = location;
    }

    public MNetUser getUser() {
        return this.mUser;
    }

    private void setUser(MNetUser mNetUser) {
        this.mUser = mNetUser;
    }

    public static ProcessorOptions newInstance(String str, AdSize[] adSizeArr, Map<String, Object> map, View view, String str2, boolean z, Location location, MNetUser mNetUser, boolean z2) {
        ProcessorOptions processorOptions = new ProcessorOptions();
        processorOptions.setAdUnitId(str);
        processorOptions.setAdSizes(adSizeArr);
        processorOptions.setTargeting(map);
        processorOptions.setAdView(view);
        processorOptions.setContextLink(str2);
        processorOptions.setIsInterstitial(z);
        processorOptions.setLocation(location);
        processorOptions.setUser(mNetUser);
        processorOptions.setIsSync(z2);
        return processorOptions;
    }
}
