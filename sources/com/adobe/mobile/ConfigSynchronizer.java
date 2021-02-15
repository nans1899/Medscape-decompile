package com.adobe.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import com.adobe.mobile.StaticMethods;
import com.adobe.mobile.WearableDataResponse;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.Wearable;
import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

final class ConfigSynchronizer {
    private static final Object _adiDMutex = new Object();
    private static final Object _aidMutex = new Object();
    private static final Object _handheldInstallDateMutex = new Object();
    private static final Object _privacyStatusMutex = new Object();
    private static final Object _pushEnabledMutex = new Object();
    private static final Object _visServiceMutex = new Object();
    private static final Object _visitorIDMutex = new Object();

    ConfigSynchronizer() {
    }

    protected static void syncVisitorID(String str) {
        if (!StaticMethods.isWearableApp()) {
            DataMap dataMap = new DataMap();
            dataMap.putString("APP_MEASUREMENT_VISITOR_ID", str);
            syncData("/abdmobile/data/config/visitorId", dataMap);
        }
    }

    protected static void syncAdvertisingIdentifier(String str) {
        if (!StaticMethods.isWearableApp()) {
            DataMap dataMap = new DataMap();
            dataMap.putString("ADOBEMOBILE_STOREDDEFAULTS_ADVERTISING_IDENTIFIER", str);
            syncData("/abdmobile/data/config/adId", dataMap);
        }
    }

    protected static void syncPushEnabled(boolean z) {
        if (!StaticMethods.isWearableApp()) {
            DataMap dataMap = new DataMap();
            dataMap.putBoolean("ADBMOBILE_KEY_PUSH_ENABLED", z);
            syncData("/abdmobile/data/config/pushEnabled", dataMap);
        }
    }

    protected static void syncVidService(String str, String str2, String str3, long j, long j2, String str4) {
        if (!StaticMethods.isWearableApp()) {
            DataMap dataMap = new DataMap();
            dataMap.putString("ADBMOBILE_PERSISTED_MID", str);
            dataMap.putString("ADBMOBILE_PERSISTED_MID_BLOB", str3);
            dataMap.putString("ADBMOBILE_PERSISTED_MID_HINT", str2);
            dataMap.putLong("ADBMOBILE_VISITORID_TTL", j);
            dataMap.putLong("ADBMOBILE_VISITORID_SYNC", j2);
            dataMap.putString("ADBMOBILE_VISITORID_IDS", str4);
            syncData("/abdmobile/data/config/vidService", dataMap);
        }
    }

    protected static void syncPrivacyStatus(int i) {
        if (!StaticMethods.isWearableApp()) {
            DataMap dataMap = new DataMap();
            dataMap.putInt("PrivacyStatus", i);
            syncData("/abdmobile/data/config/privacyStatus", dataMap);
        }
    }

    protected static void syncData(String str, DataMap dataMap) {
        try {
            GoogleApiClient build = new GoogleApiClient.Builder(StaticMethods.getSharedContext()).addApi(Wearable.API).build();
            GoogleApiClientWrapper.connect(build);
            ConnectionResult blockingConnect = GoogleApiClientWrapper.blockingConnect(build, 15000, TimeUnit.MILLISECONDS);
            if (blockingConnect == null || !blockingConnect.isSuccess()) {
                StaticMethods.logWarningFormat("Wearable - Failed to setup connection", new Object[0]);
                return;
            }
            PutDataMapRequest create = PutDataMapRequest.create(str);
            create.getDataMap().putAll(dataMap);
            Wearable.DataApi.putDataItem(build, create.asPutDataRequest());
        } catch (StaticMethods.NullContextException unused) {
        }
    }

    protected static DataMap getSharedConfig() {
        DataMap dataMap = new DataMap();
        try {
            dataMap.putLong("ADMS_InstallDate", StaticMethods.getSharedPreferences().getLong("ADMS_InstallDate", 0));
            dataMap.putBoolean("ADOBEMOBILE_STOREDDEFAULTS_IGNORE_AID", StaticMethods.getSharedPreferences().getBoolean("ADOBEMOBILE_STOREDDEFAULTS_IGNORE_AID", false));
            dataMap.putString("ADOBEMOBILE_STOREDDEFAULTS_AID", StaticMethods.getSharedPreferences().getString("ADOBEMOBILE_STOREDDEFAULTS_AID", (String) null));
            dataMap.putBoolean("ADOBEMOBILE_STOREDDEFAULTS_AID_SYNCED", StaticMethods.getSharedPreferences().getBoolean("ADOBEMOBILE_STOREDDEFAULTS_AID_SYNCED", false));
            dataMap.putString("APP_MEASUREMENT_VISITOR_ID", StaticMethods.getSharedPreferences().getString("APP_MEASUREMENT_VISITOR_ID", (String) null));
            dataMap.putString("ADOBEMOBILE_STOREDDEFAULTS_ADVERTISING_IDENTIFIER", StaticMethods.getSharedPreferences().getString("ADOBEMOBILE_STOREDDEFAULTS_ADVERTISING_IDENTIFIER", (String) null));
            dataMap.putBoolean("ADBMOBILE_KEY_PUSH_ENABLED", StaticMethods.getSharedPreferences().getBoolean("ADBMOBILE_KEY_PUSH_ENABLED", false));
            dataMap.putString("ADBMOBILE_PERSISTED_MID", StaticMethods.getSharedPreferences().getString("ADBMOBILE_PERSISTED_MID", (String) null));
            dataMap.putString("ADBMOBILE_PERSISTED_MID_HINT", StaticMethods.getSharedPreferences().getString("ADBMOBILE_PERSISTED_MID_HINT", (String) null));
            String str = "ADBMOBILE_PERSISTED_MID_BLOB";
            dataMap.putString(str, StaticMethods.getSharedPreferences().getString(str, (String) null));
            String str2 = "ADBMOBILE_VISITORID_TTL";
            dataMap.putLong(str2, StaticMethods.getSharedPreferences().getLong(str2, 0));
            String str3 = "ADBMOBILE_VISITORID_SYNC";
            dataMap.putLong(str3, StaticMethods.getSharedPreferences().getLong(str3, 0));
            String str4 = "ADBMOBILE_VISITORID_IDS";
            dataMap.putString(str4, StaticMethods.getSharedPreferences().getString(str4, (String) null));
            if (StaticMethods.getSharedPreferences().contains("PrivacyStatus")) {
                dataMap.putInt("PrivacyStatus", StaticMethods.getSharedPreferences().getInt("PrivacyStatus", 0));
            }
        } catch (StaticMethods.NullContextException unused) {
            StaticMethods.logErrorFormat("Wearable - Error getting shared preferences", new Object[0]);
        }
        return dataMap;
    }

    protected static void restoreSharedConfig(DataMap dataMap) {
        restoreHandheldInstallDate(dataMap);
        restorePrivacyStatus(dataMap);
        restoreVisitorID(dataMap);
        restoreVidService(dataMap);
        restoreAid(dataMap);
        restoreAdid(dataMap);
        restorePushEnabled(dataMap);
    }

    private static void restoreHandheldInstallDate(DataMap dataMap) {
        synchronized (_handheldInstallDateMutex) {
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                if (dataMap.containsKey("ADMS_InstallDate")) {
                    sharedPreferencesEditor.putLong("ADMS_Handheld_App_InstallDate", dataMap.getLong("ADMS_InstallDate", 0));
                }
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException unused) {
                StaticMethods.logErrorFormat("Wearable - Error saving Handheld App install date to shared preferences", new Object[0]);
            }
        }
    }

    private static void restoreAid(DataMap dataMap) {
        synchronized (_aidMutex) {
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.putBoolean("ADOBEMOBILE_STOREDDEFAULTS_IGNORE_AID", dataMap.getBoolean("ADOBEMOBILE_STOREDDEFAULTS_IGNORE_AID"));
                sharedPreferencesEditor.putString("ADOBEMOBILE_STOREDDEFAULTS_AID", dataMap.getString("ADOBEMOBILE_STOREDDEFAULTS_AID"));
                sharedPreferencesEditor.putBoolean("ADOBEMOBILE_STOREDDEFAULTS_AID_SYNCED", dataMap.getBoolean("ADOBEMOBILE_STOREDDEFAULTS_AID_SYNCED"));
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException unused) {
                StaticMethods.logErrorFormat("Wearable - Error saving Aid data to shared preferences", new Object[0]);
            }
        }
    }

    private static void restoreVisitorID(DataMap dataMap) {
        synchronized (_visitorIDMutex) {
            Config.setUserIdentifier(dataMap.getString("APP_MEASUREMENT_VISITOR_ID"));
        }
    }

    private static void restorePushEnabled(DataMap dataMap) {
        synchronized (_pushEnabledMutex) {
            StaticMethods.setPushEnabled(dataMap.getBoolean("ADBMOBILE_KEY_PUSH_ENABLED"));
        }
    }

    private static void restoreAdid(final DataMap dataMap) {
        synchronized (_adiDMutex) {
            Config.submitAdvertisingIdentifierTask(new Callable<String>() {
                public String call() throws Exception {
                    return dataMap.getString("ADOBEMOBILE_STOREDDEFAULTS_ADVERTISING_IDENTIFIER");
                }
            });
        }
    }

    private static void restoreVidService(DataMap dataMap) {
        synchronized (_visServiceMutex) {
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.putString("ADBMOBILE_PERSISTED_MID", dataMap.getString("ADBMOBILE_PERSISTED_MID"));
                sharedPreferencesEditor.putString("ADBMOBILE_PERSISTED_MID_HINT", dataMap.getString("ADBMOBILE_PERSISTED_MID_HINT"));
                sharedPreferencesEditor.putString("ADBMOBILE_PERSISTED_MID_BLOB", dataMap.getString("ADBMOBILE_PERSISTED_MID_BLOB"));
                sharedPreferencesEditor.putLong("ADBMOBILE_VISITORID_TTL", dataMap.getLong("ADBMOBILE_VISITORID_TTL"));
                sharedPreferencesEditor.putLong("ADBMOBILE_VISITORID_SYNC", dataMap.getLong("ADBMOBILE_VISITORID_SYNC"));
                sharedPreferencesEditor.putString("ADBMOBILE_VISITORID_IDS", dataMap.getString("ADBMOBILE_VISITORID_IDS"));
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException unused) {
                StaticMethods.logErrorFormat("Wearable - Error saving Visitor Id Service data to shared preferences", new Object[0]);
            }
            VisitorIDService.sharedInstance().resetVariablesFromSharedPreferences();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void restorePrivacyStatus(com.google.android.gms.wearable.DataMap r5) {
        /*
            java.lang.Object r0 = _privacyStatusMutex
            monitor-enter(r0)
            java.lang.String r1 = "PrivacyStatus"
            int r1 = r5.getInt(r1)     // Catch:{ all -> 0x0040 }
            com.adobe.mobile.MobilePrivacyStatus[] r2 = com.adobe.mobile.MobilePrivacyStatus.values()     // Catch:{ all -> 0x0040 }
            int r2 = r2.length     // Catch:{ all -> 0x0040 }
            if (r1 < r2) goto L_0x0027
            java.lang.String r1 = "Wearable - Invalid PrivacyStatus value (%d)"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0040 }
            r3 = 0
            java.lang.String r4 = "PrivacyStatus"
            int r5 = r5.getInt(r4)     // Catch:{ all -> 0x0040 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0040 }
            r2[r3] = r5     // Catch:{ all -> 0x0040 }
            com.adobe.mobile.StaticMethods.logWarningFormat(r1, r2)     // Catch:{ all -> 0x0040 }
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0027:
            java.lang.String r1 = "PrivacyStatus"
            boolean r1 = r5.containsKey(r1)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x003e
            com.adobe.mobile.MobilePrivacyStatus[] r1 = com.adobe.mobile.MobilePrivacyStatus.values()     // Catch:{ all -> 0x0040 }
            java.lang.String r2 = "PrivacyStatus"
            int r5 = r5.getInt(r2)     // Catch:{ all -> 0x0040 }
            r5 = r1[r5]     // Catch:{ all -> 0x0040 }
            com.adobe.mobile.Config.setPrivacyStatus(r5)     // Catch:{ all -> 0x0040 }
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0040:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.ConfigSynchronizer.restorePrivacyStatus(com.google.android.gms.wearable.DataMap):void");
    }

    protected static void restoreConfig(DataItem dataItem) {
        DataMap dataMap;
        if (dataItem != null && dataItem.getUri() != null && dataItem.getUri().getPath() != null && (dataMap = DataMapItem.fromDataItem(dataItem).getDataMap()) != null) {
            String path = dataItem.getUri().getPath();
            if (path.compareTo("/abdmobile/data/config/visitorId") == 0) {
                restoreVisitorID(dataMap);
            } else if (path.compareTo("/abdmobile/data/config/vidService") == 0) {
                restoreVidService(dataMap);
            } else if (path.compareTo("/abdmobile/data/config/privacyStatus") == 0) {
                restorePrivacyStatus(dataMap);
            } else if (path.compareTo("/abdmobile/data/config/adId") == 0) {
                restoreAdid(dataMap);
            } else if (path.compareTo("/abdmobile/data/config/pushEnabled") == 0) {
                restorePushEnabled(dataMap);
            }
        }
    }

    protected static void syncConfigFromHandheld() {
        if (StaticMethods.isWearableApp()) {
            try {
                Context applicationContext = StaticMethods.getSharedContext().getApplicationContext();
                WearableDataResponse.ShareConfigResponse shareConfigResponse = (WearableDataResponse.ShareConfigResponse) new WearableDataConnection(applicationContext).send(WearableDataRequest.createShareConfigRequest(15000));
                if (!(shareConfigResponse == null || shareConfigResponse.getResult() == null)) {
                    restoreSharedConfig(shareConfigResponse.getResult());
                }
                String pointsOfInterestURL = MobileConfig.getInstance().getPointsOfInterestURL();
                String inAppMessageURL = MobileConfig.getInstance().getInAppMessageURL();
                if (pointsOfInterestURL != null || inAppMessageURL != null) {
                    File fileForCachedURL = RemoteDownload.getFileForCachedURL(pointsOfInterestURL);
                    String str = null;
                    String name = fileForCachedURL != null ? fileForCachedURL.getName() : null;
                    File fileForCachedURL2 = RemoteDownload.getFileForCachedURL(inAppMessageURL);
                    if (fileForCachedURL2 != null) {
                        str = fileForCachedURL2.getName();
                    }
                    new WearableDataConnection(applicationContext).send(WearableDataRequest.createFileRequest(pointsOfInterestURL, name, 15000));
                    new WearableDataConnection(applicationContext).send(WearableDataRequest.createFileRequest(inAppMessageURL, str, 15000));
                    MobileConfig.getInstance().loadCachedRemotes();
                }
            } catch (StaticMethods.NullContextException e) {
                StaticMethods.logErrorFormat("Analytics - Error syncing Points of Interest and In-app Messages from handheld app to wearable app (%s)", e.getLocalizedMessage());
            }
        }
    }
}
