package com.adobe.mobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import com.adobe.mobile.AbstractHitDatabase;
import com.adobe.mobile.AudienceManager;
import com.adobe.mobile.Config;
import com.adobe.mobile.StaticMethods;
import com.adobe.mobile.VisitorID;
import com.medscape.android.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class Lifecycle {
    protected static final String ADB_DEEPLINK_TYPE_APP_LINK = "applink";
    protected static final String ADB_DEEPLINK_TYPE_TARGET_PREVIEW_LINK = "targetPreviewlink";
    protected static final String ADB_LIFECYCLE_PUSH_MESSAGE_ID_KEY = "adb_m_id";
    protected static final String ADB_TRACK_INTERNAL_ADOBE_LINK = "AdobeLink";
    protected static final String ADB_TRACK_INTERNAL_PUSH_CLICK_THROUGH = "PushMessage";
    private static final HashMap<String, Object> _lifecycleContextData = new HashMap<>();
    private static final HashMap<String, Object> _lifecycleContextDataLowercase = new HashMap<>();
    private static final Object _lifecycleContextDataLowercaseMutex = new Object();
    private static final Object _lifecycleContextDataMutex = new Object();
    private static final HashMap<String, Object> _previousSessionlifecycleContextData = new HashMap<>();
    private static final Object _previousSessionlifecycleContextDataMutex = new Object();
    private static boolean appIsInBackground = false;
    protected static volatile boolean lifecycleHasRun = false;
    protected static long sessionStartTime;

    private static long secToMs(long j) {
        return j * 1000;
    }

    Lifecycle() {
    }

    protected static void start(Activity activity, Map<String, Object> map) {
        Activity activity2;
        HashMap hashMap;
        HashMap hashMap2;
        appIsInBackground = false;
        updateLifecycleDataForUpgradeIfNecessary();
        if (!lifecycleHasRun) {
            lifecycleHasRun = true;
            try {
                SharedPreferences sharedPreferences = StaticMethods.getSharedPreferences();
                try {
                    activity2 = StaticMethods.getCurrentActivity();
                } catch (StaticMethods.NullActivityException unused) {
                    activity2 = null;
                }
                if (!(activity2 == null || activity == null || !activity2.getComponentName().toString().equals(activity.getComponentName().toString()))) {
                    Messages.checkForInAppMessage((Map<String, Object>) null, (Map<String, Object>) null, (Map<String, Object>) null);
                }
                StaticMethods.setCurrentActivity(activity);
                Map<String, Object> checkForAdobeLinkData = checkForAdobeLinkData(activity, ADB_DEEPLINK_TYPE_TARGET_PREVIEW_LINK);
                if (checkForAdobeLinkData != null && TargetPreviewManager.getInstance().getToken() == null) {
                    extractTargetPreviewData(checkForAdobeLinkData);
                    TargetPreviewManager.getInstance().downloadAndShowTargetPreviewUI();
                }
                TargetPreviewManager.getInstance().setupPreviewButton();
                MobileConfig instance = MobileConfig.getInstance();
                long msToSec = msToSec(sharedPreferences.getLong("ADMS_PauseDate", 0));
                int lifecycleTimeout = instance.getLifecycleTimeout();
                if (msToSec > 0) {
                    long timeSince1970 = StaticMethods.getTimeSince1970() - msToSec;
                    long msToSec2 = msToSec(sharedPreferences.getLong("ADMS_SessionStart", 0));
                    sessionStartTime = msToSec2;
                    AnalyticsTrackTimedAction.sharedInstance().trackTimedActionUpdateAdjustedStartTime(timeSince1970);
                    if (timeSince1970 < ((long) lifecycleTimeout) && msToSec2 > 0) {
                        try {
                            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                            sharedPreferencesEditor.putLong("ADMS_SessionStart", secToMs(msToSec2 + timeSince1970));
                            sharedPreferencesEditor.putBoolean("ADMS_SuccessfulClose", false);
                            sharedPreferencesEditor.remove("ADMS_PauseDate");
                            sharedPreferencesEditor.commit();
                        } catch (StaticMethods.NullContextException e) {
                            StaticMethods.logErrorFormat("Lifecycle - Error while updating start time (%s).", e.getMessage());
                        }
                        sessionStartTime = msToSec(sharedPreferences.getLong("ADMS_SessionStart", 0));
                        checkForAdobeClickThrough(activity, false);
                        return;
                    }
                }
                VisitorIDService.sharedInstance().idSync((Map<String, String>) null, (Map<String, String>) null, (VisitorID.VisitorIDAuthenticationState) null, true);
                instance.downloadRemoteConfigs();
                synchronized (_lifecycleContextDataMutex) {
                    _lifecycleContextData.clear();
                }
                synchronized (_lifecycleContextDataLowercaseMutex) {
                    _lifecycleContextDataLowercase.clear();
                }
                if (map == null) {
                    hashMap = new HashMap();
                }
                Map<String, Object> checkForAdobeLinkData2 = checkForAdobeLinkData(activity, ADB_DEEPLINK_TYPE_APP_LINK);
                if (checkForAdobeLinkData2 != null) {
                    hashMap.putAll(checkForAdobeLinkData2);
                }
                long secToMs = secToMs(StaticMethods.getTimeSince1970());
                if (!sharedPreferences.contains("ADMS_InstallDate")) {
                    addInstallData(hashMap, secToMs);
                } else {
                    addNonInstallData(hashMap, secToMs);
                    addUpgradeData(hashMap, secToMs);
                    addSessionLengthData(hashMap);
                    checkReferrerDataForLaunch();
                }
                addLifecycleGenericData(hashMap, secToMs);
                generateLifecycleToBeSaved(hashMap);
                persistLifecycleContextData();
                MobileConfig.getInstance().invokeAdobeDataCallback(Config.MobileDataEvent.MOBILE_EVENT_LIFECYCLE, hashMap);
                AnalyticsTrackInternal.trackInternal("Lifecycle", hashMap, StaticMethods.getTimeSince1970() - 1);
                if (!instance.getAamAnalyticsForwardingEnabled()) {
                    synchronized (_lifecycleContextDataMutex) {
                        hashMap2 = new HashMap(_lifecycleContextData);
                    }
                    AudienceManagerWorker.SubmitSignal(hashMap2, (AudienceManager.AudienceManagerCallback<Map<String, Object>>) null);
                }
                checkForAdobeClickThrough(activity, true);
                resetLifecycleFlags(secToMs);
            } catch (StaticMethods.NullContextException e2) {
                StaticMethods.logErrorFormat("Lifecycle - Error starting lifecycle (%s).", e2.getMessage());
            }
        }
    }

    private static void extractTargetPreviewData(Map<String, Object> map) {
        if (map != null) {
            Object obj = map.get("at_preview_token");
            if (obj != null && (obj instanceof String)) {
                TargetPreviewManager.getInstance().setTargetPreviewToken((String) obj);
            }
            Object obj2 = map.get("at_preview_endpoint");
            if (obj2 != null && (obj2 instanceof String)) {
                TargetPreviewManager.getInstance().setTargetPreviewApiUiFetchUrlBaseOverride((String) obj2);
            }
        }
    }

    protected static void stop() {
        appIsInBackground = true;
        lifecycleHasRun = false;
        StaticMethods.updateLastKnownTimestamp(Long.valueOf(StaticMethods.getTimeSince1970()));
        try {
            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
            sharedPreferencesEditor.putBoolean("ADMS_SuccessfulClose", true);
            sharedPreferencesEditor.putLong("ADMS_PauseDate", secToMs(StaticMethods.getTimeSince1970()));
            sharedPreferencesEditor.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Error updating lifecycle pause data (%s)", e.getMessage());
        }
        try {
            if (StaticMethods.getCurrentActivity().isFinishing()) {
                Messages.resetAllInAppMessages();
            }
        } catch (StaticMethods.NullActivityException unused) {
        }
    }

    private static void persistLifecycleContextData() {
        JSONObject jSONObject;
        try {
            synchronized (_lifecycleContextDataMutex) {
                jSONObject = new JSONObject(_lifecycleContextData);
            }
            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
            sharedPreferencesEditor.putString("ADMS_LifecycleData", jSONObject.toString());
            sharedPreferencesEditor.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logWarningFormat("Lifecycle - Error persisting lifecycle data (%s)", e.getMessage());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        if (_previousSessionlifecycleContextData.size() <= 0) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r0 = new java.util.HashMap<>(_previousSessionlifecycleContextData);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        addPersistedLifecycleToMap(_previousSessionlifecycleContextData);
        r0 = new java.util.HashMap<>(_previousSessionlifecycleContextData);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        r1 = _previousSessionlifecycleContextDataMutex;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.util.HashMap<java.lang.String, java.lang.Object> getContextData() {
        /*
            java.lang.Object r0 = _lifecycleContextDataMutex
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = _lifecycleContextData     // Catch:{ all -> 0x003a }
            int r1 = r1.size()     // Catch:{ all -> 0x003a }
            if (r1 <= 0) goto L_0x0014
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x003a }
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = _lifecycleContextData     // Catch:{ all -> 0x003a }
            r1.<init>(r2)     // Catch:{ all -> 0x003a }
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            return r1
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            java.lang.Object r1 = _previousSessionlifecycleContextDataMutex
            monitor-enter(r1)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = _previousSessionlifecycleContextData     // Catch:{ all -> 0x0037 }
            int r0 = r0.size()     // Catch:{ all -> 0x0037 }
            if (r0 <= 0) goto L_0x0029
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0037 }
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = _previousSessionlifecycleContextData     // Catch:{ all -> 0x0037 }
            r0.<init>(r2)     // Catch:{ all -> 0x0037 }
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            return r0
        L_0x0029:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = _previousSessionlifecycleContextData     // Catch:{ all -> 0x0037 }
            addPersistedLifecycleToMap(r0)     // Catch:{ all -> 0x0037 }
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ all -> 0x0037 }
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = _previousSessionlifecycleContextData     // Catch:{ all -> 0x0037 }
            r0.<init>(r2)     // Catch:{ all -> 0x0037 }
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            return r0
        L_0x0037:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0037 }
            throw r0
        L_0x003a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.Lifecycle.getContextData():java.util.HashMap");
    }

    protected static void updateContextData(Map<String, Object> map) {
        synchronized (_lifecycleContextDataMutex) {
            _lifecycleContextData.putAll(map);
        }
        synchronized (_lifecycleContextDataLowercaseMutex) {
            for (Map.Entry next : map.entrySet()) {
                _lifecycleContextDataLowercase.put(((String) next.getKey()).toLowerCase(), next.getValue());
            }
        }
    }

    protected static void removeContextData(String str) {
        synchronized (_lifecycleContextDataMutex) {
            _lifecycleContextData.remove(str);
        }
        synchronized (_lifecycleContextDataLowercaseMutex) {
            _lifecycleContextDataLowercase.remove(str.toLowerCase());
        }
    }

    protected static Map<String, Object> getContextDataLowercase() {
        HashMap hashMap;
        synchronized (_lifecycleContextDataLowercaseMutex) {
            if (_lifecycleContextDataLowercase.size() <= 0) {
                HashMap hashMap2 = new HashMap();
                addPersistedLifecycleToMap(hashMap2);
                for (Map.Entry entry : hashMap2.entrySet()) {
                    _lifecycleContextDataLowercase.put(((String) entry.getKey()).toLowerCase(), entry.getValue());
                }
            }
            hashMap = new HashMap(_lifecycleContextDataLowercase);
        }
        return hashMap;
    }

    protected static void processAdobeDeepLink(Uri uri) {
        Map<String, Object> adobeDeepLinkQueryParameters = getAdobeDeepLinkQueryParameters(uri, ADB_DEEPLINK_TYPE_TARGET_PREVIEW_LINK);
        if (adobeDeepLinkQueryParameters != null) {
            extractTargetPreviewData(adobeDeepLinkQueryParameters);
            TargetPreviewManager.getInstance().setupPreviewButton();
        }
        Map<String, Object> adobeDeepLinkQueryParameters2 = getAdobeDeepLinkQueryParameters(uri, ADB_DEEPLINK_TYPE_APP_LINK);
        if (adobeDeepLinkQueryParameters2 != null) {
            if (!ReferrerHandler.getReferrerProcessed()) {
                ReferrerHandler.saveReferrerDataFromOtherSource(adobeDeepLinkQueryParameters2);
                AbstractHitDatabase.Hit selectOldestHit = AnalyticsWorker.sharedInstance().selectOldestHit();
                if (selectOldestHit != null) {
                    selectOldestHit.urlFragment = StaticMethods.appendContextData(adobeDeepLinkQueryParameters2, selectOldestHit.urlFragment);
                    AnalyticsWorker.sharedInstance().updateHitInDatabase(selectOldestHit);
                    return;
                }
            }
            HashMap hashMap = new HashMap();
            hashMap.putAll(adobeDeepLinkQueryParameters2);
            updateContextData(hashMap);
            if (MobileConfig.getInstance().mobileUsingAnalytics()) {
                AnalyticsTrackInternal.trackInternal(ADB_TRACK_INTERNAL_ADOBE_LINK, hashMap, StaticMethods.getTimeSince1970());
            }
        }
    }

    protected static boolean applicationIsInBackground() {
        return appIsInBackground;
    }

    private static void addPersistedLifecycleToMap(Map<String, Object> map) {
        try {
            String string = StaticMethods.getSharedPreferences().getString("ADMS_LifecycleData", (String) null);
            if (string != null && string.length() > 0) {
                map.putAll(StaticMethods.mapFromJson(new JSONObject(string)));
            }
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Issue loading persisted lifecycle data", e.getMessage());
        } catch (JSONException e2) {
            StaticMethods.logWarningFormat("Lifecycle - Issue loading persisted lifecycle data (%s)", e2.getMessage());
        }
    }

    private static void generateLifecycleToBeSaved(Map<String, Object> map) {
        HashMap hashMap;
        if (map == null) {
            hashMap = new HashMap();
        }
        hashMap.putAll(StaticMethods.getDefaultData());
        hashMap.put("a.locale", StaticMethods.getDefaultAcceptLanguage());
        hashMap.put("a.ltv.amount", AnalyticsTrackLifetimeValueIncrease.getLifetimeValue());
        synchronized (_lifecycleContextDataMutex) {
            _lifecycleContextData.putAll(hashMap);
            synchronized (_lifecycleContextDataLowercaseMutex) {
                _lifecycleContextDataLowercase.clear();
                for (Map.Entry next : _lifecycleContextData.entrySet()) {
                    _lifecycleContextDataLowercase.put(((String) next.getKey()).toLowerCase(), next.getValue());
                }
            }
        }
    }

    private static void resetLifecycleFlags(long j) {
        try {
            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
            if (!StaticMethods.getSharedPreferences().contains("ADMS_SessionStart")) {
                sharedPreferencesEditor.putLong("ADMS_SessionStart", j);
                sessionStartTime = j / 1000;
            }
            sharedPreferencesEditor.putString("ADMS_LastVersion", StaticMethods.getApplicationVersion());
            sharedPreferencesEditor.putBoolean("ADMS_SuccessfulClose", false);
            sharedPreferencesEditor.remove("ADMS_PauseDate");
            sharedPreferencesEditor.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Error resetting lifecycle flags (%s).", e.getMessage());
        }
    }

    private static void checkReferrerDataForLaunch() {
        Map<String, Object> referrerDataFromSharedPreferences = getReferrerDataFromSharedPreferences();
        if (referrerDataFromSharedPreferences != null && referrerDataFromSharedPreferences.size() != 0) {
            updateContextData(referrerDataFromSharedPreferences);
            MobileConfig.getInstance().invokeAdobeDataCallback(Config.MobileDataEvent.MOBILE_EVENT_ACQUISITION_LAUNCH, referrerDataFromSharedPreferences);
        }
    }

    private static Map<String, Object> getReferrerDataFromSharedPreferences() {
        HashMap<String, Object> parseOtherReferrerFields;
        try {
            if (StaticMethods.getSharedPreferences().contains("ADMS_Referrer_ContextData_Json_String")) {
                HashMap hashMap = new HashMap();
                String processReferrerDataFromV3Server = ReferrerHandler.processReferrerDataFromV3Server(StaticMethods.getSharedPreferences().getString("ADMS_Referrer_ContextData_Json_String", (String) null));
                hashMap.putAll(ReferrerHandler.parseV3ContextDataFromResponse(processReferrerDataFromV3Server));
                if (hashMap.size() > 0) {
                    hashMap.putAll(ReferrerHandler.processV3ResponseAndReturnAdobeData(processReferrerDataFromV3Server));
                } else {
                    HashMap<String, Object> parseGoogleReferrerFields = ReferrerHandler.parseGoogleReferrerFields(processReferrerDataFromV3Server);
                    if (parseGoogleReferrerFields.containsKey("a.referrer.campaign.name") && parseGoogleReferrerFields.containsKey("a.referrer.campaign.source")) {
                        hashMap.putAll(parseGoogleReferrerFields);
                    }
                    if (hashMap.size() == 0 && (parseOtherReferrerFields = ReferrerHandler.parseOtherReferrerFields(processReferrerDataFromV3Server)) != null && parseOtherReferrerFields.size() > 0) {
                        hashMap.putAll(parseOtherReferrerFields);
                    }
                }
                return hashMap;
            }
            if (StaticMethods.getSharedPreferences().contains("utm_campaign")) {
                String string = StaticMethods.getSharedPreferences().getString("utm_source", (String) null);
                String string2 = StaticMethods.getSharedPreferences().getString("utm_medium", (String) null);
                String string3 = StaticMethods.getSharedPreferences().getString("utm_term", (String) null);
                String string4 = StaticMethods.getSharedPreferences().getString("utm_content", (String) null);
                String string5 = StaticMethods.getSharedPreferences().getString("utm_campaign", (String) null);
                String string6 = StaticMethods.getSharedPreferences().getString("trackingcode", (String) null);
                if (!(string == null || string5 == null)) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("a.referrer.campaign.source", string);
                    hashMap2.put("a.referrer.campaign.medium", string2);
                    hashMap2.put("a.referrer.campaign.term", string3);
                    hashMap2.put("a.referrer.campaign.content", string4);
                    hashMap2.put("a.referrer.campaign.name", string5);
                    hashMap2.put("a.referrer.campaign.trackingcode", string6);
                    try {
                        SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("googleReferrerData", new JSONObject(hashMap2));
                        sharedPreferencesEditor.putString("ADMS_Referrer_ContextData_Json_String", jSONObject.toString());
                        sharedPreferencesEditor.commit();
                    } catch (StaticMethods.NullContextException e) {
                        StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e.getMessage());
                    } catch (JSONException e2) {
                        StaticMethods.logErrorFormat("Analytics - Error persisting referrer data (%s)", e2.getMessage());
                    }
                    return hashMap2;
                }
            }
            return null;
        } catch (StaticMethods.NullContextException e3) {
            StaticMethods.logErrorFormat("Lifecycle - Error pulling persisted Acquisition data (%s)", e3.getMessage());
        }
    }

    private static void addInstallData(Map<String, Object> map, long j) {
        map.put("a.InstallDate", new SimpleDateFormat("M/d/yyyy", Locale.US).format(Long.valueOf(j)));
        map.put("a.InstallEvent", "InstallEvent");
        map.put("a.DailyEngUserEvent", "DailyEngUserEvent");
        map.put("a.MonthlyEngUserEvent", "MonthlyEngUserEvent");
        try {
            if (!StaticMethods.getSharedPreferences().contains("ADMS_Referrer_ContextData_Json_String")) {
                if (!StaticMethods.getSharedPreferences().contains("utm_campaign")) {
                    if (MobileConfig.getInstance().mobileReferrerConfigured() && MobileConfig.getInstance().getReferrerTimeout() > 0) {
                        ReferrerHandler.setReferrerProcessed(false);
                        Messages.block3rdPartyCallbacksQueueForReferrer();
                    }
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    sharedPreferencesEditor.putLong("ADMS_InstallDate", j);
                    sharedPreferencesEditor.commit();
                }
            }
            Map<String, Object> referrerDataFromSharedPreferences = getReferrerDataFromSharedPreferences();
            ReferrerHandler.triggerDeepLink(ReferrerHandler.getDeepLinkFromJSON(ReferrerHandler.translateV3StringResponseToJSONObject(StaticMethods.getSharedPreferences().getString("ADMS_Referrer_ContextData_Json_String", (String) null))));
            if (referrerDataFromSharedPreferences != null && referrerDataFromSharedPreferences.size() >= 0) {
                map.putAll(referrerDataFromSharedPreferences);
                MobileConfig.getInstance().invokeAdobeDataCallback(Config.MobileDataEvent.MOBILE_EVENT_ACQUISITION_INSTALL, referrerDataFromSharedPreferences);
            }
            SharedPreferences.Editor sharedPreferencesEditor2 = StaticMethods.getSharedPreferencesEditor();
            sharedPreferencesEditor2.putLong("ADMS_InstallDate", j);
            sharedPreferencesEditor2.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Error setting install data (%s).", e.getMessage());
        }
    }

    private static void addUpgradeData(Map<String, Object> map, long j) {
        try {
            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
            long j2 = StaticMethods.getSharedPreferences().getLong("ADMS_UpgradeDate", 0);
            if (isApplicationUpgrade()) {
                map.put("a.UpgradeEvent", "UpgradeEvent");
                sharedPreferencesEditor.putLong("ADMS_UpgradeDate", j);
                sharedPreferencesEditor.putInt("ADMS_LaunchesAfterUpgrade", 0);
            } else if (j2 > 0) {
                map.put("a.DaysSinceLastUpgrade", calculateDaysSince(j2, j));
            }
            if (j2 > 0) {
                int i = StaticMethods.getSharedPreferences().getInt("ADMS_LaunchesAfterUpgrade", 0) + 1;
                map.put("a.LaunchesSinceUpgrade", "" + i);
                sharedPreferencesEditor.putInt("ADMS_LaunchesAfterUpgrade", i);
            }
            sharedPreferencesEditor.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Error setting upgrade data (%s).", e.getMessage());
        }
    }

    private static boolean isApplicationUpgrade() {
        try {
            return true ^ StaticMethods.getApplicationVersion().equalsIgnoreCase(StaticMethods.getSharedPreferences().getString("ADMS_LastVersion", ""));
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Unable to get application version (%s)", e.getLocalizedMessage());
            return false;
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    private static void updateLifecycleDataForUpgradeIfNecessary() {
        /*
            boolean r0 = isApplicationUpgrade()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            java.util.HashMap r0 = getContextData()
            if (r0 == 0) goto L_0x0075
            int r1 = r0.size()
            if (r1 > 0) goto L_0x0014
            goto L_0x0075
        L_0x0014:
            java.lang.String r1 = "a.AppID"
            java.lang.String r2 = com.adobe.mobile.StaticMethods.getApplicationID()
            r0.put(r1, r2)
            java.lang.Object r1 = _lifecycleContextDataMutex
            monitor-enter(r1)
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = _lifecycleContextData     // Catch:{ all -> 0x0072 }
            int r2 = r2.size()     // Catch:{ all -> 0x0072 }
            if (r2 <= 0) goto L_0x002c
            updateContextData(r0)     // Catch:{ all -> 0x0072 }
            goto L_0x0070
        L_0x002c:
            java.lang.Object r2 = _previousSessionlifecycleContextDataMutex     // Catch:{ NullContextException -> 0x0060 }
            monitor-enter(r2)     // Catch:{ NullContextException -> 0x0060 }
            java.util.HashMap<java.lang.String, java.lang.Object> r3 = _previousSessionlifecycleContextData     // Catch:{ all -> 0x005d }
            java.lang.String r4 = "a.AppID"
            java.lang.String r5 = com.adobe.mobile.StaticMethods.getApplicationID()     // Catch:{ all -> 0x005d }
            r3.put(r4, r5)     // Catch:{ all -> 0x005d }
            monitor-exit(r2)     // Catch:{ all -> 0x005d }
            android.content.SharedPreferences$Editor r2 = com.adobe.mobile.StaticMethods.getSharedPreferencesEditor()     // Catch:{ NullContextException -> 0x0060 }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ NullContextException -> 0x0060 }
            r3.<init>(r0)     // Catch:{ NullContextException -> 0x0060 }
            java.lang.String r0 = "ADMS_LifecycleData"
            java.lang.String r3 = r3.toString()     // Catch:{ NullContextException -> 0x0060 }
            r2.putString(r0, r3)     // Catch:{ NullContextException -> 0x0060 }
            r2.commit()     // Catch:{ NullContextException -> 0x0060 }
            java.lang.Object r0 = _lifecycleContextDataLowercaseMutex     // Catch:{ NullContextException -> 0x0060 }
            monitor-enter(r0)     // Catch:{ NullContextException -> 0x0060 }
            java.util.HashMap<java.lang.String, java.lang.Object> r2 = _lifecycleContextDataLowercase     // Catch:{ all -> 0x005a }
            r2.clear()     // Catch:{ all -> 0x005a }
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            goto L_0x0070
        L_0x005a:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            throw r2     // Catch:{ NullContextException -> 0x0060 }
        L_0x005d:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x005d }
            throw r0     // Catch:{ NullContextException -> 0x0060 }
        L_0x0060:
            r0 = move-exception
            java.lang.String r2 = "Lifecycle - Error persisting lifecycle data (%s)"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0072 }
            r4 = 0
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0072 }
            r3[r4] = r0     // Catch:{ all -> 0x0072 }
            com.adobe.mobile.StaticMethods.logWarningFormat(r2, r3)     // Catch:{ all -> 0x0072 }
        L_0x0070:
            monitor-exit(r1)     // Catch:{ all -> 0x0072 }
            return
        L_0x0072:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0072 }
            throw r0
        L_0x0075:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.Lifecycle.updateLifecycleDataForUpgradeIfNecessary():void");
    }

    private static void addLifecycleGenericData(Map<String, Object> map, long j) {
        map.putAll(StaticMethods.getDefaultData());
        map.put("a.LaunchEvent", "LaunchEvent");
        map.put("a.OSVersion", StaticMethods.getOperatingSystem());
        map.put("a.HourOfDay", new SimpleDateFormat("H", Locale.US).format(Long.valueOf(j)));
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        map.put("a.DayOfWeek", Integer.toString(instance.get(7)));
        String advertisingIdentifier = StaticMethods.getAdvertisingIdentifier();
        if (advertisingIdentifier != null) {
            map.put("a.adid", advertisingIdentifier);
        }
        try {
            SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
            int i = StaticMethods.getSharedPreferences().getInt("ADMS_Launches", 0) + 1;
            map.put("a.Launches", Integer.toString(i));
            sharedPreferencesEditor.putInt("ADMS_Launches", i);
            sharedPreferencesEditor.putLong("ADMS_LastDateUsed", j);
            sharedPreferencesEditor.commit();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Lifecycle - Error adding generic data (%s).", e.getMessage());
        }
    }

    private static void addNonInstallData(Map<String, Object> map, long j) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/d", Locale.US);
            long j2 = StaticMethods.getSharedPreferences().getLong("ADMS_LastDateUsed", 0);
            if (!simpleDateFormat.format(Long.valueOf(j)).equalsIgnoreCase(simpleDateFormat.format(new Date(j2)))) {
                map.put("a.DailyEngUserEvent", "DailyEngUserEvent");
            }
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/M", Locale.US);
            if (!simpleDateFormat2.format(Long.valueOf(j)).equalsIgnoreCase(simpleDateFormat2.format(new Date(j2)))) {
                map.put("a.MonthlyEngUserEvent", "MonthlyEngUserEvent");
            }
            map.put("a.DaysSinceFirstUse", calculateDaysSince(StaticMethods.getSharedPreferences().getLong("ADMS_InstallDate", 0), j));
            map.put("a.DaysSinceLastUse", calculateDaysSince(j2, j));
            if (!StaticMethods.getSharedPreferences().getBoolean("ADMS_SuccessfulClose", false)) {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.remove("ADMS_PauseDate");
                sharedPreferencesEditor.remove("ADMS_SessionStart");
                sessionStartTime = StaticMethods.getTimeSince1970();
                sharedPreferencesEditor.commit();
                long j3 = StaticMethods.getSharedPreferences().getLong("ADBLastKnownTimestampKey", 0);
                if (j3 <= 0 || !MobileConfig.getInstance().mobileUsingAnalytics() || !MobileConfig.getInstance().getOfflineTrackingEnabled() || !MobileConfig.getInstance().getBackdateSessionInfoEnabled()) {
                    map.put("a.CrashEvent", "CrashEvent");
                } else {
                    try {
                        SharedPreferences sharedPreferences = StaticMethods.getSharedPreferences();
                        HashMap hashMap = new HashMap();
                        hashMap.put("a.CrashEvent", "CrashEvent");
                        hashMap.put("a.OSVersion", sharedPreferences.getString("ADOBEMOBILE_STOREDDEFAULTS_OS", ""));
                        hashMap.put("a.AppID", sharedPreferences.getString("ADOBEMOBILE_STOREDDEFAULTS_APPID", ""));
                        AnalyticsTrackInternal.trackInternal("Crash", hashMap, j3 + 1);
                        synchronized (_lifecycleContextDataMutex) {
                            _lifecycleContextData.put("a.CrashEvent", "CrashEvent");
                        }
                    } catch (StaticMethods.NullContextException e) {
                        StaticMethods.logWarningFormat("Config - Unable to get crash data for backdated hit (%s)", e.getLocalizedMessage());
                    }
                }
                AnalyticsTrackTimedAction.sharedInstance().trackTimedActionUpdateActionsClearAdjustedStartTime();
            }
        } catch (StaticMethods.NullContextException e2) {
            StaticMethods.logErrorFormat("Lifecycle - Error setting non install data (%s).", e2.getMessage());
        }
    }

    private static void addSessionLengthData(Map<String, Object> map) {
        try {
            long msToSec = msToSec(StaticMethods.getSharedPreferences().getLong("ADMS_PauseDate", 0));
            if (StaticMethods.getTimeSince1970() - msToSec >= ((long) MobileConfig.getInstance().getLifecycleTimeout())) {
                long msToSec2 = msToSec - msToSec(StaticMethods.getSharedPreferences().getLong("ADMS_SessionStart", 0));
                sessionStartTime = StaticMethods.getTimeSince1970();
                if (msToSec2 <= 0 || msToSec2 >= 604800) {
                    map.put("a.ignoredSessionLength", Long.toString(msToSec2));
                } else {
                    long j = StaticMethods.getSharedPreferences().getLong("ADBLastKnownTimestampKey", 0);
                    if (j <= 0 || !MobileConfig.getInstance().mobileUsingAnalytics() || !MobileConfig.getInstance().getOfflineTrackingEnabled() || !MobileConfig.getInstance().getBackdateSessionInfoEnabled()) {
                        map.put("a.PrevSessionLength", Long.toString(msToSec2));
                    } else {
                        try {
                            SharedPreferences sharedPreferences = StaticMethods.getSharedPreferences();
                            HashMap hashMap = new HashMap();
                            hashMap.put("a.PrevSessionLength", String.valueOf(msToSec2));
                            hashMap.put("a.OSVersion", sharedPreferences.getString("ADOBEMOBILE_STOREDDEFAULTS_OS", ""));
                            hashMap.put("a.AppID", sharedPreferences.getString("ADOBEMOBILE_STOREDDEFAULTS_APPID", ""));
                            AnalyticsTrackInternal.trackInternal("SessionInfo", hashMap, j + 1);
                            synchronized (_lifecycleContextDataMutex) {
                                _lifecycleContextData.put("a.PrevSessionLength", String.valueOf(msToSec2));
                            }
                        } catch (StaticMethods.NullContextException e) {
                            StaticMethods.logWarningFormat("Config - Unable to get session data for backdated hit (%s)", e.getLocalizedMessage());
                        }
                    }
                }
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.remove("ADMS_SessionStart");
                sharedPreferencesEditor.commit();
            }
        } catch (StaticMethods.NullContextException e2) {
            StaticMethods.logErrorFormat("Lifecycle - Error adding session length data (%s).", e2.getMessage());
        }
    }

    private static String calculateDaysSince(long j, long j2) {
        return Integer.toString((int) ((j2 - j) / Constants.DAY_IN_MILLIS));
    }

    private static void checkForAdobeClickThrough(Activity activity, boolean z) {
        Intent intent;
        if (activity != null && (intent = activity.getIntent()) != null) {
            String str = null;
            String stringExtra = intent.getStringExtra(ADB_LIFECYCLE_PUSH_MESSAGE_ID_KEY);
            String stringExtra2 = intent.getStringExtra("adb_m_l_id");
            Map<String, Object> adobeDeepLinkQueryParameters = getAdobeDeepLinkQueryParameters(intent.getData(), ADB_DEEPLINK_TYPE_APP_LINK);
            HashMap hashMap = new HashMap();
            if (!z && adobeDeepLinkQueryParameters != null) {
                hashMap.putAll(adobeDeepLinkQueryParameters);
                updateContextData(hashMap);
                str = ADB_TRACK_INTERNAL_ADOBE_LINK;
            }
            if (stringExtra != null && stringExtra.length() > 0) {
                hashMap.put("a.push.payloadId", stringExtra);
                updateContextData(hashMap);
                str = ADB_TRACK_INTERNAL_PUSH_CLICK_THROUGH;
            } else if (stringExtra2 != null && stringExtra2.length() > 0) {
                hashMap.put("a.message.id", stringExtra2);
                hashMap.put("a.message.clicked", 1);
                updateContextData(hashMap);
                str = "In-App Message";
            }
            if (str != null && MobileConfig.getInstance().mobileUsingAnalytics()) {
                AnalyticsTrackInternal.trackInternal(str, hashMap, StaticMethods.getTimeSince1970());
            }
        }
    }

    private static Map<String, Object> checkForAdobeLinkData(Activity activity, String str) {
        Intent intent;
        Uri data;
        if (activity == null || (intent = activity.getIntent()) == null || (data = intent.getData()) == null) {
            return null;
        }
        Map<String, Object> adobeDeepLinkQueryParameters = getAdobeDeepLinkQueryParameters(data, str);
        clearTargetPreviewTokenInIntent(intent, adobeDeepLinkQueryParameters);
        return adobeDeepLinkQueryParameters;
    }

    private static void clearTargetPreviewTokenInIntent(Intent intent, Map<String, Object> map) {
        try {
            Uri data = intent.getData();
            if (data != null && map != null) {
                if (!map.isEmpty()) {
                    if (map.containsKey("at_preview_token")) {
                        intent.setData(data.buildUpon().encodedQuery("").build());
                    }
                }
            }
        } catch (Exception e) {
            StaticMethods.logErrorFormat("Lifecycle - Exception while attempting to remove target token parameters from Uri (%s).", e.getMessage());
        }
    }

    private static Map<String, Object> getAdobeDeepLinkQueryParameters(Uri uri, String str) {
        String str2;
        String str3;
        HashMap hashMap = null;
        if (uri == null) {
            return null;
        }
        String encodedQuery = uri.getEncodedQuery();
        String str4 = str.equals(ADB_DEEPLINK_TYPE_TARGET_PREVIEW_LINK) ? "at_preview_token" : "a.deeplink.id";
        if (encodedQuery != null && encodedQuery.length() > 0) {
            if (encodedQuery.contains(str4 + "=")) {
                hashMap = new HashMap();
                for (String str5 : encodedQuery.split("&")) {
                    if (str5 != null && str5.length() > 0) {
                        String[] split = str5.split("=", 2);
                        if (split.length == 1 || (split.length == 2 && split[1].isEmpty())) {
                            StaticMethods.logWarningFormat("Deep Link - Skipping an invalid variable on the URI query (%s).", split[0]);
                        } else {
                            try {
                                str2 = URLDecoder.decode(split[0], "UTF-8");
                            } catch (UnsupportedEncodingException unused) {
                                str2 = split[0];
                            }
                            try {
                                str3 = URLDecoder.decode(split[1], "UTF-8");
                            } catch (UnsupportedEncodingException unused2) {
                                str3 = split[1];
                            }
                            if (str2.startsWith("ctx")) {
                                hashMap.put(str2.substring(3), str3);
                            } else if (str2.startsWith("adb")) {
                                hashMap.put("a.acquisition.custom.".concat(str2.substring(3)), str3);
                            } else {
                                hashMap.put(str2, str3);
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private static long msToSec(long j) {
        return j / 1000;
    }
}
