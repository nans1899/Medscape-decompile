package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.facebook.GraphRequest;
import com.facebook.appevents.InternalAppEventsLogger;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

final class UserSettingsManager {
    private static final String ADVERTISERID_COLLECTION_FALSE_WARNING = "The value for AdvertiserIDCollectionEnabled is currently set to FALSE so you're sending app events without collecting Advertiser ID. This can affect the quality of your advertising and analytics results.";
    private static final String ADVERTISERID_COLLECTION_NOT_SET_WARNING = "You haven't set a value for AdvertiserIDCollectionEnabled. Set the flag to TRUE if you want to collect Advertiser ID for better advertising and analytics results. To request user consent before collecting data, set the flag value to FALSE, then change to TRUE once user consent is received. Learn more: https://developers.facebook.com/docs/app-events/getting-started-app-events-android#disable-auto-events.";
    private static final String ADVERTISER_ID_KEY = "advertiser_id";
    private static final String APPLICATION_FIELDS = "fields";
    private static final String AUTOLOG_APPEVENT_NOT_SET_WARNING = "Please set a value for AutoLogAppEventsEnabled. Set the flag to TRUE if you want to collect app install, app launch and in-app purchase events automatically. To request user consent before collecting data, set the flag value to FALSE, then change to TRUE once user consent is received. Learn more: https://developers.facebook.com/docs/app-events/getting-started-app-events-android#disable-auto-events.";
    private static final String EVENTS_CODELESS_SETUP_ENABLED = "auto_event_setup_enabled";
    private static final String LAST_TIMESTAMP = "last_timestamp";
    private static final String TAG = UserSettingsManager.class.getName();
    private static final long TIMEOUT_7D = 604800000;
    private static final String USER_SETTINGS = "com.facebook.sdk.USER_SETTINGS";
    private static final String USER_SETTINGS_BITMASK = "com.facebook.sdk.USER_SETTINGS_BITMASK";
    private static final String VALUE = "value";
    /* access modifiers changed from: private */
    public static UserSetting advertiserIDCollectionEnabled = new UserSetting(true, FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY);
    private static UserSetting autoInitEnabled = new UserSetting(true, FacebookSdk.AUTO_INIT_ENABLED_PROPERTY);
    private static UserSetting autoLogAppEventsEnabled = new UserSetting(true, FacebookSdk.AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY);
    /* access modifiers changed from: private */
    public static UserSetting codelessSetupEnabled = new UserSetting(false, EVENTS_CODELESS_SETUP_ENABLED);
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static SharedPreferences userSettingPref;
    private static SharedPreferences.Editor userSettingPrefEditor;

    UserSettingsManager() {
    }

    public static void initializeIfNotInitialized() {
        if (FacebookSdk.isInitialized() && isInitialized.compareAndSet(false, true)) {
            SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences(USER_SETTINGS, 0);
            userSettingPref = sharedPreferences;
            userSettingPrefEditor = sharedPreferences.edit();
            initializeUserSetting(autoLogAppEventsEnabled, advertiserIDCollectionEnabled, autoInitEnabled);
            initializeCodelessSepupEnabledAsync();
            logWarnings();
            logIfSDKSettingsChanged();
        }
    }

    private static void initializeUserSetting(UserSetting... userSettingArr) {
        for (UserSetting userSetting : userSettingArr) {
            if (userSetting == codelessSetupEnabled) {
                initializeCodelessSepupEnabledAsync();
            } else if (userSetting.value == null) {
                readSettingFromCache(userSetting);
                if (userSetting.value == null) {
                    loadSettingFromManifest(userSetting);
                }
            } else {
                writeSettingToCache(userSetting);
            }
        }
    }

    private static void initializeCodelessSepupEnabledAsync() {
        readSettingFromCache(codelessSetupEnabled);
        final long currentTimeMillis = System.currentTimeMillis();
        if (codelessSetupEnabled.value == null || currentTimeMillis - codelessSetupEnabled.lastTS >= TIMEOUT_7D) {
            codelessSetupEnabled.value = null;
            codelessSetupEnabled.lastTS = 0;
            FacebookSdk.getExecutor().execute(new Runnable() {
                public void run() {
                    FetchedAppSettings queryAppSettings;
                    if (UserSettingsManager.advertiserIDCollectionEnabled.getValue() && (queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false)) != null && queryAppSettings.getCodelessEventsEnabled()) {
                        AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                        if (((attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null) ? null : attributionIdentifiers.getAndroidAdvertiserId()) != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString(UserSettingsManager.ADVERTISER_ID_KEY, attributionIdentifiers.getAndroidAdvertiserId());
                            bundle.putString("fields", UserSettingsManager.EVENTS_CODELESS_SETUP_ENABLED);
                            GraphRequest newGraphPathRequest = GraphRequest.newGraphPathRequest((AccessToken) null, FacebookSdk.getApplicationId(), (GraphRequest.Callback) null);
                            newGraphPathRequest.setSkipClientToken(true);
                            newGraphPathRequest.setParameters(bundle);
                            JSONObject jSONObject = newGraphPathRequest.executeAndWait().getJSONObject();
                            if (jSONObject != null) {
                                UserSettingsManager.codelessSetupEnabled.value = Boolean.valueOf(jSONObject.optBoolean(UserSettingsManager.EVENTS_CODELESS_SETUP_ENABLED, false));
                                UserSettingsManager.codelessSetupEnabled.lastTS = currentTimeMillis;
                                UserSettingsManager.writeSettingToCache(UserSettingsManager.codelessSetupEnabled);
                            }
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void writeSettingToCache(UserSetting userSetting) {
        validateInitialized();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("value", userSetting.value);
            jSONObject.put(LAST_TIMESTAMP, userSetting.lastTS);
            userSettingPrefEditor.putString(userSetting.key, jSONObject.toString()).commit();
            logIfSDKSettingsChanged();
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
    }

    private static void readSettingFromCache(UserSetting userSetting) {
        validateInitialized();
        try {
            String string = userSettingPref.getString(userSetting.key, "");
            if (!string.isEmpty()) {
                JSONObject jSONObject = new JSONObject(string);
                userSetting.value = Boolean.valueOf(jSONObject.getBoolean("value"));
                userSetting.lastTS = jSONObject.getLong(LAST_TIMESTAMP);
            }
        } catch (JSONException e) {
            Utility.logd(TAG, (Exception) e);
        }
    }

    private static void loadSettingFromManifest(UserSetting userSetting) {
        validateInitialized();
        try {
            Context applicationContext = FacebookSdk.getApplicationContext();
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey(userSetting.key)) {
                userSetting.value = Boolean.valueOf(applicationInfo.metaData.getBoolean(userSetting.key, userSetting.defaultVal));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Utility.logd(TAG, (Exception) e);
        }
    }

    private static void logWarnings() {
        try {
            Context applicationContext = FacebookSdk.getApplicationContext();
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                if (!applicationInfo.metaData.containsKey(FacebookSdk.AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY)) {
                    Log.w(TAG, AUTOLOG_APPEVENT_NOT_SET_WARNING);
                }
                if (!applicationInfo.metaData.containsKey(FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY)) {
                    Log.w(TAG, ADVERTISERID_COLLECTION_NOT_SET_WARNING);
                }
                if (!getAdvertiserIDCollectionEnabled()) {
                    Log.w(TAG, ADVERTISERID_COLLECTION_FALSE_WARNING);
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    private static void logIfSDKSettingsChanged() {
        int i;
        if (isInitialized.get() && FacebookSdk.isInitialized()) {
            Context applicationContext = FacebookSdk.getApplicationContext();
            int i2 = 0;
            int i3 = ((autoInitEnabled.getValue() ? 1 : 0) << false) | 0 | ((autoLogAppEventsEnabled.getValue() ? 1 : 0) << true) | ((advertiserIDCollectionEnabled.getValue() ? 1 : 0) << true);
            int i4 = userSettingPref.getInt(USER_SETTINGS_BITMASK, 0);
            if (i4 != i3) {
                userSettingPrefEditor.putInt(USER_SETTINGS_BITMASK, i3).commit();
                try {
                    ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
                    if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                        String[] strArr = {FacebookSdk.AUTO_INIT_ENABLED_PROPERTY, FacebookSdk.AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY, FacebookSdk.ADVERTISER_ID_COLLECTION_ENABLED_PROPERTY};
                        boolean[] zArr = {true, true, true};
                        int i5 = 0;
                        int i6 = 0;
                        i = 0;
                        while (i5 < 3) {
                            try {
                                i |= (applicationInfo.metaData.containsKey(strArr[i5]) ? 1 : 0) << i5;
                                i6 |= (applicationInfo.metaData.getBoolean(strArr[i5], zArr[i5]) ? 1 : 0) << i5;
                                i5++;
                            } catch (PackageManager.NameNotFoundException unused) {
                            }
                        }
                        i2 = i6;
                        InternalAppEventsLogger internalAppEventsLogger = new InternalAppEventsLogger(applicationContext);
                        Bundle bundle = new Bundle();
                        bundle.putInt("usage", i);
                        bundle.putInt("initial", i2);
                        bundle.putInt("previous", i4);
                        bundle.putInt("current", i3);
                        internalAppEventsLogger.logEventImplicitly("fb_sdk_settings_changed", bundle);
                    }
                } catch (PackageManager.NameNotFoundException unused2) {
                }
                i = 0;
                InternalAppEventsLogger internalAppEventsLogger2 = new InternalAppEventsLogger(applicationContext);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("usage", i);
                bundle2.putInt("initial", i2);
                bundle2.putInt("previous", i4);
                bundle2.putInt("current", i3);
                internalAppEventsLogger2.logEventImplicitly("fb_sdk_settings_changed", bundle2);
            }
        }
    }

    private static void validateInitialized() {
        if (!isInitialized.get()) {
            throw new FacebookSdkNotInitializedException("The UserSettingManager has not been initialized successfully");
        }
    }

    public static void setAutoInitEnabled(boolean z) {
        autoInitEnabled.value = Boolean.valueOf(z);
        autoInitEnabled.lastTS = System.currentTimeMillis();
        if (isInitialized.get()) {
            writeSettingToCache(autoInitEnabled);
        } else {
            initializeIfNotInitialized();
        }
    }

    public static boolean getAutoInitEnabled() {
        initializeIfNotInitialized();
        return autoInitEnabled.getValue();
    }

    public static void setAutoLogAppEventsEnabled(boolean z) {
        autoLogAppEventsEnabled.value = Boolean.valueOf(z);
        autoLogAppEventsEnabled.lastTS = System.currentTimeMillis();
        if (isInitialized.get()) {
            writeSettingToCache(autoLogAppEventsEnabled);
        } else {
            initializeIfNotInitialized();
        }
    }

    public static boolean getAutoLogAppEventsEnabled() {
        initializeIfNotInitialized();
        return autoLogAppEventsEnabled.getValue();
    }

    public static void setAdvertiserIDCollectionEnabled(boolean z) {
        advertiserIDCollectionEnabled.value = Boolean.valueOf(z);
        advertiserIDCollectionEnabled.lastTS = System.currentTimeMillis();
        if (isInitialized.get()) {
            writeSettingToCache(advertiserIDCollectionEnabled);
        } else {
            initializeIfNotInitialized();
        }
    }

    public static boolean getAdvertiserIDCollectionEnabled() {
        initializeIfNotInitialized();
        return advertiserIDCollectionEnabled.getValue();
    }

    public static boolean getCodelessSetupEnabled() {
        initializeIfNotInitialized();
        return codelessSetupEnabled.getValue();
    }

    private static class UserSetting {
        boolean defaultVal;
        String key;
        long lastTS;
        Boolean value;

        UserSetting(boolean z, String str) {
            this.defaultVal = z;
            this.key = str;
        }

        /* access modifiers changed from: package-private */
        public boolean getValue() {
            Boolean bool = this.value;
            return bool == null ? this.defaultVal : bool.booleanValue();
        }
    }
}
