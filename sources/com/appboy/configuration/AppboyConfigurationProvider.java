package com.appboy.configuration;

import android.content.Context;
import android.content.pm.PackageManager;
import bo.app.ce;
import bo.app.dv;
import bo.app.v;
import com.appboy.enums.SdkFlavor;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import com.appboy.support.StringUtils;
import com.medscape.android.EnvironmentConfig;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Locale;

public class AppboyConfigurationProvider extends CachedConfigurationProvider {
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyConfigurationProvider.class);
    private final Context b;

    enum a {
        SMALL,
        LARGE
    }

    public AppboyConfigurationProvider(Context context) {
        super(context);
        this.b = context;
    }

    public String getBaseUrlForRequests() {
        return EnvironmentConfig.ENV_STAGING.equals(a().toUpperCase(Locale.US)) ? "https://sondheim.appboy.com/api/v3/" : "https://dev.appboy.com/api/v3/";
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAppboyApiKeyStringFromLocaleMapping(java.util.Locale r12) {
        /*
            r11 = this;
            r0 = 0
            if (r12 != 0) goto L_0x000b
            java.lang.String r12 = a
            java.lang.String r1 = "Passed in a null locale to match."
            com.appboy.support.AppboyLogger.d(r12, r1)
            return r0
        L_0x000b:
            bo.app.m r1 = r11.mRuntimeAppConfigurationProvider
            java.lang.String r2 = "com_appboy_locale_api_key_map"
            boolean r1 = r1.a((java.lang.String) r2)
            r3 = 0
            if (r1 == 0) goto L_0x0043
            bo.app.m r1 = r11.mRuntimeAppConfigurationProvider
            java.lang.String r1 = r1.a((java.lang.String) r2, (java.lang.String) r0)
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0039 }
            r2.<init>(r1)     // Catch:{ JSONException -> 0x0039 }
            int r1 = r2.length()     // Catch:{ JSONException -> 0x0039 }
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ JSONException -> 0x0039 }
            r4 = 0
        L_0x0028:
            int r5 = r2.length()     // Catch:{ JSONException -> 0x0037 }
            if (r4 >= r5) goto L_0x0047
            java.lang.String r5 = r2.getString(r4)     // Catch:{ JSONException -> 0x0037 }
            r1[r4] = r5     // Catch:{ JSONException -> 0x0037 }
            int r4 = r4 + 1
            goto L_0x0028
        L_0x0037:
            r2 = move-exception
            goto L_0x003b
        L_0x0039:
            r2 = move-exception
            r1 = r0
        L_0x003b:
            java.lang.String r4 = a
            java.lang.String r5 = "Caught exception creating locale to api key mapping from override cache"
            com.appboy.support.AppboyLogger.e(r4, r5, r2)
            goto L_0x0047
        L_0x0043:
            java.lang.String[] r1 = r11.readStringArrayResourceValue(r2, r0)
        L_0x0047:
            if (r1 != 0) goto L_0x0051
            java.lang.String r12 = a
            java.lang.String r1 = "Locale to api key mappings not present in XML."
            com.appboy.support.AppboyLogger.d(r12, r1)
            return r0
        L_0x0051:
            int r2 = r1.length
            r4 = 0
        L_0x0053:
            if (r4 >= r2) goto L_0x00a1
            r5 = r1[r4]
            java.lang.String r6 = ","
            int r7 = com.appboy.support.StringUtils.countOccurrences(r5, r6)
            r8 = 1
            if (r7 == r8) goto L_0x0061
            goto L_0x0097
        L_0x0061:
            java.lang.String[] r5 = r5.split(r6)
            int r6 = r5.length
            r7 = 2
            if (r6 == r7) goto L_0x006a
            goto L_0x0097
        L_0x006a:
            r6 = r5[r3]
            java.lang.String r6 = r6.trim()
            java.util.Locale r7 = java.util.Locale.US
            java.lang.String r6 = r6.toLowerCase(r7)
            java.lang.String r7 = r12.toString()
            java.util.Locale r9 = java.util.Locale.US
            java.lang.String r7 = r7.toLowerCase(r9)
            boolean r7 = r6.equals(r7)
            java.lang.String r9 = r12.getCountry()
            java.util.Locale r10 = java.util.Locale.US
            java.lang.String r9 = r9.toLowerCase(r10)
            boolean r6 = r6.equals(r9)
            if (r6 != 0) goto L_0x009a
            if (r7 == 0) goto L_0x0097
            goto L_0x009a
        L_0x0097:
            int r4 = r4 + 1
            goto L_0x0053
        L_0x009a:
            r12 = r5[r8]
            java.lang.String r12 = r12.trim()
            return r12
        L_0x00a1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.configuration.AppboyConfigurationProvider.getAppboyApiKeyStringFromLocaleMapping(java.util.Locale):java.lang.String");
    }

    public ce getAppboyApiKey() {
        ce ceVar = (ce) this.mConfigurationCache.get("com_appboy_api_key");
        if (ceVar == null) {
            String a2 = this.mRuntimeAppConfigurationProvider.a("com_appboy_api_key", (String) null);
            if (a2 != null) {
                AppboyLogger.i(a, "Found an override api key. Using it to configure Appboy.");
            } else {
                a2 = getAppboyApiKeyStringFromLocaleMapping(Locale.getDefault());
                if (a2 != null) {
                    AppboyLogger.i(a, "Found a locale that matches the current locale in appboy.xml. Using the associated api key");
                } else {
                    a2 = readStringResourceValue("com_appboy_api_key", (String) null);
                }
            }
            if (a2 != null) {
                ceVar = new ce(a2);
                this.mConfigurationCache.put("com_appboy_api_key", ceVar);
            }
        }
        if (ceVar != null) {
            return ceVar;
        }
        AppboyLogger.e(a, "****************************************************");
        AppboyLogger.e(a, "**                                                **");
        AppboyLogger.e(a, "**                 !! WARNING !!                  **");
        AppboyLogger.e(a, "**                                                **");
        AppboyLogger.e(a, "**     No API key set in res/values/appboy.xml    **");
        AppboyLogger.e(a, "** No cached API Key found from Appboy.configure  **");
        AppboyLogger.e(a, "**          Braze functionality disabled          **");
        AppboyLogger.e(a, "**                                                **");
        AppboyLogger.e(a, "****************************************************");
        throw new RuntimeException("Unable to read the Braze API key from the res/values/appboy.xml file. See log for more details.");
    }

    public boolean isAdmMessagingRegistrationEnabled() {
        return getBooleanValue("com_appboy_push_adm_messaging_registration_enabled", false);
    }

    public boolean isLocationCollectionEnabled() {
        return !getBooleanValue("com_appboy_disable_location_collection", false);
    }

    public int getSmallNotificationIconResourceId() {
        return a(a.SMALL);
    }

    public int getLargeNotificationIconResourceId() {
        return a(a.LARGE);
    }

    public long getTriggerActionMinimumTimeIntervalInSeconds() {
        return (long) getIntValue("com_appboy_trigger_action_minimum_time_interval_seconds", 30);
    }

    public int getSessionTimeoutSeconds() {
        return getIntValue("com_appboy_session_timeout", 10);
    }

    public int getVersionCode() {
        int i;
        if (this.mConfigurationCache.containsKey("version_code")) {
            return ((Integer) this.mConfigurationCache.get("version_code")).intValue();
        }
        try {
            i = this.b.getPackageManager().getPackageInfo(PackageUtils.getResourcePackageName(this.b), 0).versionCode;
        } catch (Exception e) {
            AppboyLogger.e(a, "Unable to read the version code.", e);
            i = -1;
        }
        this.mConfigurationCache.put("version_code", Integer.valueOf(i));
        return i;
    }

    public String getCustomEndpoint() {
        return getStringValue("com_appboy_custom_endpoint", (String) null);
    }

    public boolean getHandlePushDeepLinksAutomatically() {
        return getBooleanValue("com_appboy_handle_push_deep_links_automatically", false);
    }

    @Deprecated
    public boolean getIsNotificationsEnabledTrackingOn() {
        return getBooleanValue("com_appboy_notifications_enabled_tracking_on", false);
    }

    public boolean getIsNewsfeedVisualIndicatorOn() {
        return getBooleanValue("com_appboy_newsfeed_unread_visual_indicator_on", true);
    }

    public String getDefaultNotificationChannelName() {
        return getStringValue("com_appboy_default_notification_channel_name", "General");
    }

    public String getDefaultNotificationChannelDescription() {
        return getStringValue("com_appboy_default_notification_channel_description", "");
    }

    public int getApplicationIconResourceId() {
        if (this.mConfigurationCache.containsKey("application_icon")) {
            return ((Integer) this.mConfigurationCache.get("application_icon")).intValue();
        }
        String packageName = this.b.getPackageName();
        int i = 0;
        try {
            i = this.b.getPackageManager().getApplicationInfo(packageName, 0).icon;
        } catch (PackageManager.NameNotFoundException unused) {
            String str = a;
            AppboyLogger.e(str, "Cannot find package named " + packageName);
            try {
                i = this.b.getPackageManager().getApplicationInfo(this.b.getPackageName(), 0).icon;
            } catch (PackageManager.NameNotFoundException unused2) {
                String str2 = a;
                AppboyLogger.e(str2, "Cannot find package named " + packageName);
            }
        }
        this.mConfigurationCache.put("application_icon", Integer.valueOf(i));
        return i;
    }

    public int getDefaultNotificationAccentColor() {
        return getIntValue("com_appboy_default_notification_accent_color", 0);
    }

    public SdkFlavor getSdkFlavor() {
        String stringValue = getStringValue("com_appboy_sdk_flavor", (String) null);
        if (StringUtils.isNullOrBlank(stringValue)) {
            return null;
        }
        try {
            return SdkFlavor.valueOf(stringValue.toUpperCase(Locale.US));
        } catch (Exception e) {
            AppboyLogger.e(a, "Exception while parsing stored SDK flavor. Returning null.", e);
            return null;
        }
    }

    public boolean getIsPushDeepLinkBackStackActivityEnabled() {
        return getBooleanValue("com_appboy_push_deep_link_back_stack_activity_enabled", true);
    }

    public String getPushDeepLinkBackStackActivityClassName() {
        return getStringValue("com_appboy_push_deep_link_back_stack_activity_class_name", "");
    }

    public boolean getIsSessionStartBasedTimeoutEnabled() {
        return getBooleanValue("com_appboy_session_start_based_timeout_enabled", false);
    }

    public boolean isFirebaseCloudMessagingRegistrationEnabled() {
        return getBooleanValue("com_appboy_firebase_cloud_messaging_registration_enabled", false);
    }

    public boolean isContentCardsUnreadVisualIndicatorEnabled() {
        return getBooleanValue("com_appboy_content_cards_unread_visual_indicator_enabled", true);
    }

    public String getFirebaseCloudMessagingSenderIdKey() {
        return getStringValue("com_appboy_firebase_cloud_messaging_sender_id", (String) null);
    }

    public EnumSet<v> getDeviceObjectWhitelist() {
        if (this.mConfigurationCache.containsKey("com_appboy_device_object_whitelist")) {
            return (EnumSet) this.mConfigurationCache.get("com_appboy_device_object_whitelist");
        }
        EnumSet<v> a2 = dv.a(v.class, getStringSetValue("com_appboy_device_object_whitelist", new HashSet()));
        this.mConfigurationCache.put("com_appboy_device_object_whitelist", a2);
        return a2;
    }

    public boolean getIsDeviceObjectWhitelistEnabled() {
        return getBooleanValue("com_appboy_device_object_whitelisting_enabled", false);
    }

    public boolean getIsInAppMessageAccessibilityExclusiveModeEnabled() {
        return getBooleanValue("com_appboy_device_in_app_message_accessibility_exclusive_mode_enabled", false);
    }

    private int a(a aVar) {
        String str = aVar.equals(a.LARGE) ? "com_appboy_push_large_notification_icon" : "com_appboy_push_small_notification_icon";
        if (this.mConfigurationCache.containsKey(str)) {
            return ((Integer) this.mConfigurationCache.get(str)).intValue();
        }
        if (this.mRuntimeAppConfigurationProvider.a(str)) {
            String a2 = this.mRuntimeAppConfigurationProvider.a(str, "");
            int identifier = this.b.getResources().getIdentifier(a2, "drawable", PackageUtils.getResourcePackageName(this.b));
            this.mConfigurationCache.put(str, Integer.valueOf(identifier));
            String str2 = a;
            AppboyLogger.d(str2, "Using runtime override value for key: " + str + " and value: " + a2);
            return identifier;
        }
        int identifier2 = this.b.getResources().getIdentifier(str, "drawable", PackageUtils.getResourcePackageName(this.b));
        this.mConfigurationCache.put(str, Integer.valueOf(identifier2));
        return identifier2;
    }

    private String a() {
        return getStringValue("com_appboy_server_target", EnvironmentConfig.ENV_PROD);
    }
}
