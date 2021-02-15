package com.webmd.wbmdcmepulse.models;

import java.io.Serializable;

public class Topic implements Serializable {
    public static final String CUSTUM_SUBSCRIPTION_TYPE = "custom";
    public int Order;
    public String ThumbnailUrl;
    public long UniqueId;
    private int[] mHomePageIds;
    private boolean mIsDefault;
    private boolean mIsRequired;
    private boolean mIsSelected = false;
    private boolean mIsSubscribed;
    private String mKey;
    private String mSubscriptionId;
    private String mSubscriptionType;
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getSubscriptionId() {
        return this.mSubscriptionId;
    }

    public void setSubscriptionId(String str) {
        this.mSubscriptionId = str;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public int[] getHomePageIds() {
        return this.mHomePageIds;
    }

    public void setHomePageIds(int[] iArr) {
        this.mHomePageIds = iArr;
    }

    public boolean isIsRequired() {
        return this.mIsRequired;
    }

    public void setIsRequired(boolean z) {
        this.mIsRequired = z;
    }

    public boolean isIsSubscribed() {
        return this.mIsSubscribed;
    }

    public void setIsSubscribed(boolean z) {
        this.mIsSubscribed = z;
    }

    public boolean isIsDefault() {
        return this.mIsDefault;
    }

    public void setIsDefault(boolean z) {
        this.mIsDefault = z;
    }

    public String getType() {
        return this.mSubscriptionType;
    }

    public void setType(String str) {
        this.mSubscriptionType = str;
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public void setIsSelected(boolean z) {
        this.mIsSelected = z;
    }

    public boolean isCustom() {
        return this.mSubscriptionType.equals("custom");
    }
}
