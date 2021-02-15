package com.appboy.configuration;

import bo.app.v;
import com.appboy.enums.SdkFlavor;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.EnumSet;
import java.util.List;

public class AppboyConfig {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(AppboyConfig.class);
    private Boolean A;
    private EnumSet<v> B;
    private Boolean C;
    private final List<String> D;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final SdkFlavor k;
    private final Integer l;
    private final Integer m;
    private final Integer n;
    private final Integer o;
    private final Integer p;
    private final Integer q;
    private final Boolean r;
    private final Boolean s;
    private final Boolean t;
    private final Boolean u;
    private final Boolean v;
    private final Boolean w;
    private final Boolean x;
    private final Boolean y;
    private final Boolean z;

    private AppboyConfig(Builder builder) {
        this.b = builder.a;
        this.r = builder.q;
        this.d = builder.c;
        this.e = builder.d;
        this.f = builder.e;
        this.l = builder.k;
        this.D = builder.C;
        this.u = builder.t;
        this.m = builder.l;
        this.n = builder.m;
        this.s = builder.r;
        this.t = builder.s;
        this.v = builder.u;
        this.o = builder.n;
        this.p = builder.o;
        this.q = builder.p;
        this.c = builder.b;
        this.k = builder.j;
        this.g = builder.f;
        this.h = builder.g;
        this.w = builder.v;
        this.i = builder.h;
        this.x = builder.w;
        this.j = builder.i;
        this.y = builder.x;
        this.z = builder.y;
        this.B = builder.A;
        this.C = builder.B;
        this.A = builder.z;
    }

    public String getApiKey() {
        return this.b;
    }

    public String getSmallNotificationIcon() {
        return this.d;
    }

    public String getLargeNotificationIcon() {
        return this.e;
    }

    public String getCustomEndpoint() {
        return this.f;
    }

    public Integer getSessionTimeout() {
        return this.l;
    }

    public Integer getDefaultNotificationAccentColor() {
        return this.m;
    }

    public Integer getTriggerActionMinimumTimeIntervalSeconds() {
        return this.n;
    }

    public Boolean getAdmMessagingRegistrationEnabled() {
        return this.r;
    }

    public Boolean getHandlePushDeepLinksAutomatically() {
        return this.s;
    }

    @Deprecated
    public Boolean getNotificationsEnabledTrackingOn() {
        return this.t;
    }

    public Boolean getDisableLocationCollection() {
        return this.u;
    }

    public List<String> getLocaleToApiMapping() {
        return this.D;
    }

    public Boolean getIsNewsFeedVisualIndicatorOn() {
        return this.v;
    }

    public Integer getBadNetworkDataFlushInterval() {
        return this.o;
    }

    public Integer getGoodNetworkDataFlushInterval() {
        return this.p;
    }

    public Integer getGreatNetworkDataFlushInterval() {
        return this.q;
    }

    public String getServerTarget() {
        return this.c;
    }

    public SdkFlavor getSdkFlavor() {
        return this.k;
    }

    public String getDefaultNotificationChannelName() {
        return this.g;
    }

    public String getDefaultNotificationChannelDescription() {
        return this.h;
    }

    public Boolean getPushDeepLinkBackStackActivityEnabled() {
        return this.w;
    }

    public String getPushDeepLinkBackStackActivityClassName() {
        return this.i;
    }

    public Boolean getIsSessionStartBasedTimeoutEnabled() {
        return this.x;
    }

    public Boolean getIsFirebaseCloudMessagingRegistrationEnabled() {
        return this.y;
    }

    public String getFirebaseCloudMessagingSenderIdKey() {
        return this.j;
    }

    public Boolean getContentCardsUnreadVisualIndicatorEnabled() {
        return this.z;
    }

    public EnumSet<v> getDeviceObjectWhitelist() {
        return this.B;
    }

    public Boolean getDeviceObjectWhitelistEnabled() {
        return this.C;
    }

    public Boolean getIsInAppMessageAccessibilityExclusiveModeEnabled() {
        return this.C;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public EnumSet<v> A;
        /* access modifiers changed from: private */
        public Boolean B;
        /* access modifiers changed from: private */
        public List<String> C;
        /* access modifiers changed from: private */
        public String a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d;
        /* access modifiers changed from: private */
        public String e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public String g;
        /* access modifiers changed from: private */
        public String h;
        /* access modifiers changed from: private */
        public String i;
        /* access modifiers changed from: private */
        public SdkFlavor j;
        /* access modifiers changed from: private */
        public Integer k;
        /* access modifiers changed from: private */
        public Integer l;
        /* access modifiers changed from: private */
        public Integer m;
        /* access modifiers changed from: private */
        public Integer n;
        /* access modifiers changed from: private */
        public Integer o;
        /* access modifiers changed from: private */
        public Integer p;
        /* access modifiers changed from: private */
        public Boolean q;
        /* access modifiers changed from: private */
        public Boolean r;
        /* access modifiers changed from: private */
        public Boolean s;
        /* access modifiers changed from: private */
        public Boolean t;
        /* access modifiers changed from: private */
        public Boolean u;
        /* access modifiers changed from: private */
        public Boolean v;
        /* access modifiers changed from: private */
        public Boolean w;
        /* access modifiers changed from: private */
        public Boolean x;
        /* access modifiers changed from: private */
        public Boolean y;
        /* access modifiers changed from: private */
        public Boolean z;

        public AppboyConfig build() {
            return new AppboyConfig(this);
        }

        public Builder setApiKey(String str) {
            if (!StringUtils.isNullOrBlank(str)) {
                this.a = str;
            } else {
                AppboyLogger.e(AppboyConfig.a, "Cannot set Braze API key to null or blank string. API key field not set");
            }
            return this;
        }

        public Builder setDefaultNotificationChannelName(String str) {
            if (!StringUtils.isNullOrBlank(str)) {
                this.f = str;
            } else {
                AppboyLogger.e(AppboyConfig.a, "Cannot set Braze default NotificationChannel name to null or blank string. NotificationChannel name field not set.");
            }
            return this;
        }

        public Builder setDefaultNotificationChannelDescription(String str) {
            if (!StringUtils.isNullOrBlank(str)) {
                this.g = str;
            } else {
                AppboyLogger.e(AppboyConfig.a, "Cannot set Braze default NotificationChannel description to null or blank string. NotificationChannel description field not set.");
            }
            return this;
        }

        public Builder setLocaleToApiMapping(List<String> list) {
            if (!list.isEmpty()) {
                this.C = list;
            } else {
                AppboyLogger.e(AppboyConfig.a, "Cannot set locale to API key mapping to empty list. Locale mapping not set.");
            }
            return this;
        }

        public Builder setServerTarget(String str) {
            this.b = str;
            return this;
        }

        public Builder setSdkFlavor(SdkFlavor sdkFlavor) {
            this.j = sdkFlavor;
            return this;
        }

        public Builder setNewsfeedVisualIndicatorOn(boolean z2) {
            this.u = Boolean.valueOf(z2);
            return this;
        }

        public Builder setSmallNotificationIcon(String str) {
            this.c = str;
            return this;
        }

        public Builder setLargeNotificationIcon(String str) {
            this.d = str;
            return this;
        }

        public Builder setCustomEndpoint(String str) {
            this.e = str;
            return this;
        }

        public Builder setSessionTimeout(int i2) {
            this.k = Integer.valueOf(i2);
            return this;
        }

        public Builder setDefaultNotificationAccentColor(int i2) {
            this.l = Integer.valueOf(i2);
            return this;
        }

        public Builder setTriggerActionMinimumTimeIntervalSeconds(int i2) {
            this.m = Integer.valueOf(i2);
            return this;
        }

        public Builder setAdmMessagingRegistrationEnabled(boolean z2) {
            this.q = Boolean.valueOf(z2);
            return this;
        }

        public Builder setHandlePushDeepLinksAutomatically(boolean z2) {
            this.r = Boolean.valueOf(z2);
            return this;
        }

        @Deprecated
        public Builder setNotificationsEnabledTrackingOn(boolean z2) {
            this.s = Boolean.valueOf(z2);
            return this;
        }

        public Builder setPushDeepLinkBackStackActivityEnabled(boolean z2) {
            this.v = Boolean.valueOf(z2);
            return this;
        }

        public Builder setPushDeepLinkBackStackActivityClass(Class cls) {
            if (cls != null) {
                this.h = cls.getName();
            }
            return this;
        }

        public Builder setDisableLocationCollection(boolean z2) {
            this.t = Boolean.valueOf(z2);
            return this;
        }

        public Builder setBadNetworkDataFlushInterval(int i2) {
            this.n = Integer.valueOf(i2);
            return this;
        }

        public Builder setGoodNetworkDataFlushInterval(int i2) {
            this.o = Integer.valueOf(i2);
            return this;
        }

        public Builder setGreatNetworkDataFlushInterval(int i2) {
            this.p = Integer.valueOf(i2);
            return this;
        }

        public Builder setIsSessionStartBasedTimeoutEnabled(boolean z2) {
            this.w = Boolean.valueOf(z2);
            return this;
        }

        public Builder setIsFirebaseCloudMessagingRegistrationEnabled(boolean z2) {
            this.x = Boolean.valueOf(z2);
            return this;
        }

        public Builder setFirebaseCloudMessagingSenderIdKey(String str) {
            if (!StringUtils.isNullOrEmpty(str)) {
                this.i = str;
            } else {
                AppboyLogger.e(AppboyConfig.a, "Cannot set Firebase Cloud Messaging Sender Id to null or empty string. Firebase Cloud Messaging Sender Id field not set");
            }
            return this;
        }

        public Builder setContentCardsUnreadVisualIndicatorEnabled(boolean z2) {
            this.y = Boolean.valueOf(z2);
            return this;
        }

        public Builder setDeviceObjectWhitelist(EnumSet<v> enumSet) {
            this.A = enumSet;
            return this;
        }

        public Builder setDeviceObjectWhitelistEnabled(boolean z2) {
            this.B = Boolean.valueOf(z2);
            return this;
        }

        public Builder setIsInAppMessageAccessibilityExclusiveModeEnabled(boolean z2) {
            this.z = Boolean.valueOf(z2);
            return this;
        }
    }

    public String toString() {
        return "AppboyConfig{ApiKey = '" + this.b + '\'' + "\nServerTarget = '" + this.c + '\'' + "\nSdkFlavor = '" + this.k + '\'' + "\nSmallNotificationIcon = '" + this.d + '\'' + "\nLargeNotificationIcon = '" + this.e + '\'' + "\nSessionTimeout = " + this.l + "\nDefaultNotificationAccentColor = " + this.m + "\nTriggerActionMinimumTimeIntervalSeconds = " + this.n + "\nBadNetworkInterval = " + this.o + "\nGoodNetworkInterval = " + this.p + "\nGreatNetworkInterval = " + this.q + "\nAdmMessagingRegistrationEnabled = " + this.r + "\nHandlePushDeepLinksAutomatically = " + this.s + "\nNotificationsEnabledTrackingOn = " + this.t + "\nDisableLocationCollection = " + this.u + "\nIsNewsFeedVisualIndicatorOn = " + this.v + "\nLocaleToApiMapping = " + this.D + "\nSessionStartBasedTimeoutEnabled = " + this.x + "\nIsFirebaseCloudMessagingRegistrationEnabled = " + this.y + "\nFirebaseCloudMessagingSenderIdKey = '" + this.j + '\'' + "\nIsDeviceObjectWhitelistEnabled = " + this.C + "\nDeviceObjectWhitelist = " + this.B + "\nIsInAppMessageAccessibilityExclusiveModeEnabled = " + this.A + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
