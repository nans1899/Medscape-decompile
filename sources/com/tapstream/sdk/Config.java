package com.tapstream.sdk;

import com.tapstream.sdk.Event;
import com.tapstream.sdk.Retry;

public class Config {
    private final String accountName;
    private boolean activityListenerBindsLate = false;
    private String androidId = null;
    private boolean collectAdvertisingId = true;
    private Retry.Strategy dataCollectionRetryStrategy = Retry.DEFAULT_DATA_COLLECTION_STRATEGY;
    private final String developerSecret;
    private String deviceId = null;
    private boolean fireAutomaticInstallEvent = true;
    private boolean fireAutomaticOpenEvent = true;
    private final Event.Params globalEventParams = new Event.Params();
    private String installEventName = null;
    private String odin1 = null;
    private String openEventName = null;
    private String openUdid = null;
    private boolean useInAppLanders = false;
    private boolean useWordOfMouth = false;
    private Retry.Strategy userFacingRequestRetryStrategy = Retry.DEFAULT_USER_FACING_RETRY_STRATEGY;
    private String wifiMac = null;

    public Config(String str, String str2) {
        this.accountName = str;
        this.developerSecret = str2;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public String getDeveloperSecret() {
        return this.developerSecret;
    }

    public void setGlobalEventParameter(String str, Object obj) {
        this.globalEventParams.put(str, obj.toString());
    }

    public Event.Params getGlobalEventParams() {
        return this.globalEventParams;
    }

    public String getOdin1() {
        return this.odin1;
    }

    public void setOdin1(String str) {
        this.odin1 = str;
    }

    public String getOpenUdid() {
        return this.openUdid;
    }

    public void setOpenUdid(String str) {
        this.openUdid = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getWifiMac() {
        return this.wifiMac;
    }

    public void setWifiMac(String str) {
        this.wifiMac = str;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    public void setAndroidId(String str) {
        this.androidId = str;
    }

    public String getInstallEventName() {
        return this.installEventName;
    }

    public void setInstallEventName(String str) {
        this.installEventName = str;
    }

    public String getOpenEventName() {
        return this.openEventName;
    }

    public void setOpenEventName(String str) {
        this.openEventName = str;
    }

    public boolean getFireAutomaticInstallEvent() {
        return this.fireAutomaticInstallEvent;
    }

    public void setFireAutomaticInstallEvent(boolean z) {
        this.fireAutomaticInstallEvent = z;
    }

    public boolean getFireAutomaticOpenEvent() {
        return this.fireAutomaticOpenEvent;
    }

    public void setFireAutomaticOpenEvent(boolean z) {
        this.fireAutomaticOpenEvent = z;
    }

    public boolean getCollectAdvertisingId() {
        return this.collectAdvertisingId;
    }

    public void setCollectAdvertisingId(boolean z) {
        this.collectAdvertisingId = z;
    }

    public boolean getUseWordOfMouth() {
        return this.useWordOfMouth;
    }

    public void setUseWordOfMouth(boolean z) {
        this.useWordOfMouth = z;
    }

    public boolean getUseInAppLanders() {
        return this.useInAppLanders;
    }

    public void setUseInAppLanders(boolean z) {
        this.useInAppLanders = z;
    }

    public Retry.Strategy getDataCollectionRetryStrategy() {
        return this.dataCollectionRetryStrategy;
    }

    public void setDataCollectionRetryStrategy(Retry.Strategy strategy) {
        this.dataCollectionRetryStrategy = strategy;
    }

    public Retry.Strategy getUserFacingRequestRetryStrategy() {
        return this.userFacingRequestRetryStrategy;
    }

    public void setUserFacingRequestRetryStrategy(Retry.Strategy strategy) {
        this.userFacingRequestRetryStrategy = strategy;
    }

    public boolean getActivityListenerBindsLate() {
        return this.activityListenerBindsLate;
    }

    public void setActivityListenerBindsLate(boolean z) {
        this.activityListenerBindsLate = z;
    }
}
