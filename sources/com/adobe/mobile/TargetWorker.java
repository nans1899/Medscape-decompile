package com.adobe.mobile;

import android.content.SharedPreferences;
import com.adobe.mobile.StaticMethods;
import com.adobe.mobile.Target;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class TargetWorker {
    protected static final String COOKIE_EXPIRES_KEY_SUFFIX = "_Expires";
    protected static final String COOKIE_NAME_PCID = "mboxPC";
    protected static final String COOKIE_VALUE_KEY_SUFFIX = "_Value";
    protected static final String PROFILE_PARAMETER_PREFIX = "profile.";
    protected static final String TARGET_API_A4T_ACTION_NAME = "AnalyticsForTarget";
    protected static final String TARGET_API_CONTENT_TYPE = "application/json";
    protected static final String TARGET_API_JSON_EDGE_HOST = "edgeHost";
    protected static final String TARGET_API_JSON_ERROR_MESSAGE = "message";
    protected static final String TARGET_API_JSON_THIRD_PARTY_ID = "thirdPartyId";
    protected static final String TARGET_API_JSON_TNT_ID = "tntId";
    protected static final String TARGET_API_URL_HOST_BASE = "%s.tt.omtrdc.net";
    protected static final String TARGET_API_URL_PREVIEW_TOKEN_PARAM = "at_preview_token=%s";
    protected static final String TARGET_PREFETCH_API_URL_BASE = "https://%s/rest/v2/batchmbox/";
    private static final Object _checkOldCookiesMutex = new Object();
    private static String _edgeHost = null;
    private static boolean _oldCookiesHaveBeenChecked = false;
    private static HashMap<String, Object> _persistentParameters = null;
    private static final Object _persistentParametersMutex = new Object();
    private static String _sessionId = null;
    private static final Object _sessionIdMutex = new Object();
    private static long _sessionIdTimestampSeconds = 0;
    private static String _thirdPartyId = null;
    private static final Object _thirdPartyIdMutex = new Object();
    private static String _tntId = null;
    private static final Object _tntIdMutex = new Object();
    private static final List<String> cacheLoadedMboxAcceptedKeys = Arrays.asList(new String[]{"mbox", "profileScriptToken", "clickToken"});
    private static final List<String> cacheMboxAcceptedKeys = Arrays.asList(new String[]{"mbox", "parameters", "product", "order", "content", "eventTokens", "clientSideAnalyticsLoggingPayload", "errorType", "profileScriptToken", "clickToken"});
    /* access modifiers changed from: private */
    public static Map<String, JSONObject> loadedMboxes = new HashMap();
    /* access modifiers changed from: private */
    public static List<JSONObject> notifications = new ArrayList();
    /* access modifiers changed from: private */
    public static Map<String, JSONObject> prefetchedMboxes = new HashMap();

    TargetWorker() {
    }

    protected static void loadRequest(TargetLocationRequest targetLocationRequest, Target.TargetCallback<String> targetCallback) {
        if (targetLocationRequest == null) {
            StaticMethods.logWarningFormat("Target - \"TargetLocationRequest\" object cannot be null", new Object[0]);
            if (targetCallback != null) {
                targetCallback.call(null);
                return;
            }
            return;
        }
        HashMap<String, Object> hashMap = targetLocationRequest.parameters;
        getThirdPartyIDLegacy(hashMap);
        loadRequest(targetLocationRequest.name, targetLocationRequest.defaultContent, getProfileParametersLegacy(hashMap), getOrderParametersLegacy(hashMap), hashMap, targetCallback);
    }

    protected static void loadRequest(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Target.TargetCallback<String> targetCallback) {
        HashMap hashMap;
        if (!MobileConfig.getInstance().mobileUsingTarget() || MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
            if (targetCallback != null) {
                targetCallback.call(str2);
            }
        } else if (isNullOrEmpty(str)) {
            StaticMethods.logWarningFormat("Target - \"mboxName\" parameter is required for Target calls - returning default content", new Object[0]);
            if (targetCallback != null) {
                targetCallback.call(str2);
            }
        } else {
            TargetRequestObject targetRequestObject = new TargetRequestObject(str, str2, map3, map2, (Map<String, Object>) null, targetCallback);
            ArrayList arrayList = new ArrayList();
            arrayList.add(targetRequestObject);
            if (map == null) {
                hashMap = new HashMap();
            }
            StaticMethods.getAnalyticsExecutor().execute(new TargetBatchRunner(arrayList, hashMap));
        }
    }

    protected static void loadRequests(List<TargetRequestObject> list, Map<String, Object> map) {
        HashMap hashMap;
        if (isNullOrEmpty((List) list)) {
            StaticMethods.logWarningFormat("Target - request array should contain at least one mbox", new Object[0]);
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        if (!MobileConfig.getInstance().mobileUsingTarget() || MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
            replyWithDefaultContent(arrayList);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            TargetRequestObject targetRequestObject = (TargetRequestObject) it.next();
            if (isNullOrEmpty(targetRequestObject.getMboxName())) {
                if (targetRequestObject.getCallback() != null) {
                    targetRequestObject.getCallback().call(targetRequestObject.getDefaultContent());
                }
                it.remove();
            }
        }
        if (map == null) {
            hashMap = new HashMap();
        }
        StaticMethods.getAnalyticsExecutor().execute(new TargetBatchRunner(arrayList, hashMap));
    }

    protected static void prefetchContent(List<TargetPrefetchObject> list, Map<String, Object> map, Target.TargetCallback<Boolean> targetCallback) {
        HashMap hashMap;
        if (!MobileConfig.getInstance().mobileUsingTarget() || MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
            StaticMethods.logWarningFormat("Target - prefetch can't be used if target is not enabled or privacy is not opted in", new Object[0]);
            if (targetCallback != null) {
                targetCallback.call(false);
            }
        } else if (Boolean.valueOf(!isNullOrEmpty(TargetPreviewManager.getInstance().getPreviewParams())).booleanValue()) {
            StaticMethods.logWarningFormat("Target - Prefetch feature is not supported in preview mode", new Object[0]);
            if (targetCallback != null) {
                targetCallback.call(false);
            }
        } else if (isNullOrEmpty((List) list)) {
            StaticMethods.logWarningFormat("Target - prefetch array should contain at least one mbox", new Object[0]);
            if (targetCallback != null) {
                targetCallback.call(false);
            }
        } else {
            Iterator<TargetPrefetchObject> it = list.iterator();
            while (it.hasNext()) {
                if (isNullOrEmpty(it.next().getMboxName())) {
                    it.remove();
                }
            }
            if (isNullOrEmpty((List) list)) {
                StaticMethods.logWarningFormat("Target - prefetch array should contain mboxes with valid mbox names", new Object[0]);
                if (targetCallback != null) {
                    targetCallback.call(false);
                    return;
                }
                return;
            }
            if (map == null) {
                hashMap = new HashMap();
            }
            StaticMethods.getAnalyticsExecutor().execute(new TargetPrefetchRunner(list, hashMap, targetCallback));
        }
    }

    protected static void locationClicked(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Map<String, Object> map4) {
        HashMap hashMap;
        HashMap hashMap2;
        HashMap hashMap3;
        HashMap hashMap4;
        if (isNullOrEmpty(str)) {
            StaticMethods.logWarningFormat("Target - click notification can't be sent if mbox name is empty/null", new Object[0]);
        } else if (!MobileConfig.getInstance().mobileUsingTarget() || MobileConfig.getInstance().getPrivacyStatus() != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
            StaticMethods.logWarningFormat("Target - click notification can't be sent if target is not enabled or privacy is not opted in", new Object[0]);
        } else {
            if (map == null) {
                hashMap = new HashMap();
            }
            HashMap hashMap5 = hashMap;
            if (map2 == null) {
                hashMap2 = new HashMap();
            }
            HashMap hashMap6 = hashMap2;
            if (map3 == null) {
                hashMap3 = new HashMap();
            }
            HashMap hashMap7 = hashMap3;
            if (map4 == null) {
                hashMap4 = new HashMap();
            }
            StaticMethods.getAnalyticsExecutor().execute(new TargetClickNotification(str, hashMap5, hashMap6, hashMap7, hashMap4));
        }
    }

    protected static void clearPrefetchCache() {
        Map<String, JSONObject> map = prefetchedMboxes;
        if (map != null) {
            map.clear();
        }
    }

    protected static String getThirdPartyId() {
        String str;
        synchronized (_thirdPartyIdMutex) {
            if (isNullOrEmpty(_thirdPartyId)) {
                try {
                    _thirdPartyId = StaticMethods.getSharedPreferences().getString("ADBMOBILE_TARGET_3RD_PARTY_ID", (String) null);
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
                    return null;
                }
            }
            str = _thirdPartyId;
        }
        return str;
    }

    protected static void setThirdPartyId(String str) {
        synchronized (_thirdPartyIdMutex) {
            if (str != null) {
                if (!str.isEmpty() && MobileConfig.getInstance().getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT) {
                    return;
                }
            }
            String thirdPartyId = getThirdPartyId();
            if (thirdPartyId == null || str == null || !thirdPartyId.equals(str)) {
                _thirdPartyId = str;
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    if (isNullOrEmpty(_thirdPartyId)) {
                        sharedPreferencesEditor.remove("ADBMOBILE_TARGET_3RD_PARTY_ID");
                    } else {
                        sharedPreferencesEditor.putString("ADBMOBILE_TARGET_3RD_PARTY_ID", _thirdPartyId);
                    }
                    sharedPreferencesEditor.commit();
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
                }
            }
        }
    }

    protected static String getTntId() {
        String str;
        setTntIdFromOldCookieValues();
        synchronized (_tntIdMutex) {
            if (isNullOrEmpty(_tntId)) {
                try {
                    _tntId = StaticMethods.getSharedPreferences().getString("ADBMOBILE_TARGET_TNT_ID", (String) null);
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
                    return null;
                }
            }
            str = _tntId;
        }
        return str;
    }

    protected static void setTntId(String str) {
        synchronized (_tntIdMutex) {
            if (!tntIdValuesAreEqual(_tntId, str)) {
                _tntId = str;
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    if (isNullOrEmpty(_tntId)) {
                        sharedPreferencesEditor.remove("ADBMOBILE_TARGET_TNT_ID");
                    } else {
                        sharedPreferencesEditor.putString("ADBMOBILE_TARGET_TNT_ID", _tntId);
                    }
                    sharedPreferencesEditor.commit();
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
                }
            }
        }
    }

    protected static void resetExperience() {
        StaticMethods.logDebugFormat("Target - resetting experience for this user", new Object[0]);
        setTntIdFromOldCookieValues();
        setTntId((String) null);
        setThirdPartyId((String) null);
        setEdgeHost((String) null);
        resetSession();
    }

    private static String getEdgeHost() {
        String str;
        synchronized (_sessionIdMutex) {
            if (isSessionExpired()) {
                _edgeHost = null;
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    sharedPreferencesEditor.remove("ADBMOBILE_TARGET_EDGE_HOST");
                    sharedPreferencesEditor.commit();
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error persisting edge host in shared preferences - application context is null", new Object[0]);
                }
            } else if (isNullOrEmpty(_edgeHost)) {
                try {
                    _edgeHost = StaticMethods.getSharedPreferences().getString("ADBMOBILE_TARGET_EDGE_HOST", (String) null);
                } catch (StaticMethods.NullContextException unused2) {
                    StaticMethods.logErrorFormat("Target - Error retrieving edge host from shared preferences - application context is null", new Object[0]);
                    return null;
                }
            }
            str = _edgeHost;
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void setEdgeHost(java.lang.String r3) {
        /*
            java.lang.Object r0 = _sessionIdMutex
            monitor-enter(r0)
            java.lang.String r1 = _edgeHost     // Catch:{ all -> 0x0040 }
            if (r1 != 0) goto L_0x0009
            if (r3 == 0) goto L_0x0015
        L_0x0009:
            java.lang.String r1 = _edgeHost     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0017
            java.lang.String r1 = _edgeHost     // Catch:{ all -> 0x0040 }
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0017
        L_0x0015:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0017:
            _edgeHost = r3     // Catch:{ all -> 0x0040 }
            android.content.SharedPreferences$Editor r3 = com.adobe.mobile.StaticMethods.getSharedPreferencesEditor()     // Catch:{ NullContextException -> 0x0036 }
            java.lang.String r1 = _edgeHost     // Catch:{ NullContextException -> 0x0036 }
            boolean r1 = isNullOrEmpty((java.lang.String) r1)     // Catch:{ NullContextException -> 0x0036 }
            if (r1 == 0) goto L_0x002b
            java.lang.String r1 = "ADBMOBILE_TARGET_EDGE_HOST"
            r3.remove(r1)     // Catch:{ NullContextException -> 0x0036 }
            goto L_0x0032
        L_0x002b:
            java.lang.String r1 = "ADBMOBILE_TARGET_EDGE_HOST"
            java.lang.String r2 = _edgeHost     // Catch:{ NullContextException -> 0x0036 }
            r3.putString(r1, r2)     // Catch:{ NullContextException -> 0x0036 }
        L_0x0032:
            r3.commit()     // Catch:{ NullContextException -> 0x0036 }
            goto L_0x003e
        L_0x0036:
            java.lang.String r3 = "Target - Error persisting edge host in shared preferences - application context is null"
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0040 }
            com.adobe.mobile.StaticMethods.logErrorFormat(r3, r1)     // Catch:{ all -> 0x0040 }
        L_0x003e:
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            return
        L_0x0040:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0040 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.TargetWorker.setEdgeHost(java.lang.String):void");
    }

    protected static void addPersistentParameter(String str, String str2) {
        if (str != null && str2 != null) {
            synchronized (_persistentParametersMutex) {
                if (_persistentParameters == null) {
                    _persistentParameters = new HashMap<>();
                }
                _persistentParameters.put(str, str2);
            }
        }
    }

    protected static void removePersistentParameter(String str) {
        if (str != null) {
            synchronized (_persistentParametersMutex) {
                if (_persistentParameters != null) {
                    _persistentParameters.remove(str);
                }
            }
        }
    }

    private static class TargetBatchRunner implements Runnable {
        private List<TargetRequestObject> batchRequests;
        private Map<String, Object> profileParameters;

        private TargetBatchRunner(List<TargetRequestObject> list, Map<String, Object> map) {
            this.batchRequests = list;
            this.profileParameters = map;
        }

        public void run() {
            TargetWorker.setTntIdFromOldCookieValues();
            Boolean.valueOf(false);
            if (!Boolean.valueOf(!TargetWorker.isNullOrEmpty(TargetPreviewManager.getInstance().getPreviewParams())).booleanValue()) {
                Iterator<TargetRequestObject> it = this.batchRequests.iterator();
                while (it.hasNext()) {
                    TargetRequestObject next = it.next();
                    if (TargetWorker.prefetchedMboxes.get(next.getMboxName()) != null) {
                        TargetWorker.processMboxResponse(next, (JSONObject) TargetWorker.prefetchedMboxes.get(next.getMboxName()));
                        JSONObject notificationsJsonObject = TargetJsonBuilder.getNotificationsJsonObject((JSONObject) TargetWorker.prefetchedMboxes.get(next.getMboxName()), next, TargetWorker.getInternalTargetParams());
                        if (notificationsJsonObject != null) {
                            TargetWorker.notifications.add(notificationsJsonObject);
                        }
                        it.remove();
                    }
                }
            }
            if (!this.batchRequests.isEmpty() || !TargetWorker.isNullOrEmpty(TargetWorker.notifications)) {
                try {
                    JSONObject requestPayload = TargetJsonBuilder.getRequestPayload((List<TargetPrefetchObject>) null, this.batchRequests, this.profileParameters, TargetWorker.notifications, TargetWorker.getInternalTargetParams());
                    String access$1000 = TargetWorker.getTargetRequestURL();
                    String sessionId = TargetWorker.getSessionId();
                    StaticMethods.logDebugFormat("Target - requesting content from url \"%s\" with session id: \"%s\" and parameters: %s", access$1000, sessionId, requestPayload.toString());
                    JSONObject access$1100 = TargetWorker.extractServerJsonResponse(RequestHandler.retrieveNetworkObject(access$1000, "POST", (String) null, requestPayload.toString(), MobileConfig.getInstance().getDefaultLocationTimeout(), "application/json", "Target", sessionId), access$1000);
                    if (access$1100 == null) {
                        TargetWorker.replyWithDefaultContent(this.batchRequests);
                        return;
                    }
                    TargetWorker.notifications.clear();
                    try {
                        TargetWorker.updateSessionIdTimestamp();
                        TargetWorker.saveTargetResponseVariables(access$1100);
                        Map access$1400 = TargetWorker.extractMBoxResponses(access$1100);
                        TargetWorker.processMboxResponse(this.batchRequests, (Map<String, JSONObject>) access$1400);
                        TargetWorker.cacheLoadedMboxResponses(access$1400);
                    } catch (NullPointerException e) {
                        StaticMethods.logErrorFormat("Target - NullPointerException while trying to get content (%s)", e.getLocalizedMessage());
                        TargetWorker.replyWithDefaultContent(this.batchRequests);
                    } catch (JSONException e2) {
                        StaticMethods.logErrorFormat("Target - JSONException while trying to get content from the response (%s)", e2.getLocalizedMessage());
                        TargetWorker.replyWithDefaultContent(this.batchRequests);
                    }
                } catch (JSONException e3) {
                    StaticMethods.logWarningFormat("Target - couldn't create the target load request %s", e3.getLocalizedMessage());
                    TargetWorker.replyWithDefaultContent(this.batchRequests);
                }
            }
        }
    }

    private static class TargetPrefetchRunner implements Runnable {
        private Target.TargetCallback<Boolean> callback;
        private Map<String, Object> profileParameters;
        private List<TargetPrefetchObject> targetPrefetchArray;

        private TargetPrefetchRunner(List<TargetPrefetchObject> list, Map<String, Object> map, Target.TargetCallback<Boolean> targetCallback) {
            this.targetPrefetchArray = list;
            this.profileParameters = map;
            this.callback = targetCallback;
        }

        public void run() {
            try {
                JSONObject requestPayload = TargetJsonBuilder.getRequestPayload(this.targetPrefetchArray, (List<TargetRequestObject>) null, this.profileParameters, TargetWorker.notifications, TargetWorker.getInternalTargetParams());
                String access$1000 = TargetWorker.getTargetRequestURL();
                String sessionId = TargetWorker.getSessionId();
                StaticMethods.logDebugFormat("Target - requesting content from url \"%s\" with session id: \"%s\" and parameters: %s", access$1000, sessionId, requestPayload.toString());
                JSONObject access$1100 = TargetWorker.extractServerJsonResponse(RequestHandler.retrieveNetworkObject(access$1000, "POST", (String) null, requestPayload.toString(), MobileConfig.getInstance().getDefaultLocationTimeout(), "application/json", "Target", sessionId), access$1000);
                if (access$1100 == null) {
                    returnCallback(false);
                    return;
                }
                try {
                    StaticMethods.logDebugFormat("Target - prefetch response received %s", access$1100.toString());
                    TargetWorker.updateSessionIdTimestamp();
                    TargetWorker.saveTargetResponseVariables(access$1100);
                    Map access$1700 = TargetWorker.extractPrefetchResponses(access$1100);
                    if (TargetWorker.isNullOrEmpty(access$1700)) {
                        returnCallback(false);
                        return;
                    }
                    if (TargetWorker.prefetchedMboxes != null) {
                        TargetWorker.prefetchedMboxes.putAll(access$1700);
                    } else {
                        Map unused = TargetWorker.prefetchedMboxes = access$1700;
                    }
                    TargetWorker.removeDuplicatedMboxesFromCaches();
                    returnCallback(true);
                } catch (NullPointerException e) {
                    StaticMethods.logErrorFormat("Target - NullPointerException while trying to get content from the response (%s)", e.getLocalizedMessage());
                    returnCallback(false);
                } catch (JSONException e2) {
                    StaticMethods.logErrorFormat("Target - JSONException while trying to get content from the response (%s)", e2.getLocalizedMessage());
                    returnCallback(false);
                }
            } catch (JSONException e3) {
                StaticMethods.logWarningFormat("Target - couldn't create the target prefetch request %s", e3.getLocalizedMessage());
                returnCallback(false);
            }
        }

        private void returnCallback(boolean z) {
            Target.TargetCallback<Boolean> targetCallback = this.callback;
            if (targetCallback != null) {
                targetCallback.call(Boolean.valueOf(z));
            }
        }
    }

    private static class TargetClickNotification implements Runnable {
        private String mboxName;
        private Map<String, Object> mboxParameters;
        private Map<String, Object> orderParameters;
        private Map<String, Object> productParameters;
        private Map<String, Object> profileParameters;

        private TargetClickNotification(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Map<String, Object> map4) {
            this.mboxName = str;
            this.mboxParameters = map;
            this.orderParameters = map2;
            this.productParameters = map3;
            this.profileParameters = map4;
        }

        public void run() {
            JSONObject jSONObject;
            if (TargetWorker.prefetchedMboxes.containsKey(this.mboxName)) {
                jSONObject = (JSONObject) TargetWorker.prefetchedMboxes.get(this.mboxName);
            } else if (TargetWorker.loadedMboxes.containsKey(this.mboxName)) {
                jSONObject = (JSONObject) TargetWorker.loadedMboxes.get(this.mboxName);
            } else {
                StaticMethods.logWarningFormat("Target - couldn't send click notification for %s, the location should be pre-fetched/loaded before", this.mboxName);
                return;
            }
            if (TargetWorker.isNullOrEmpty(jSONObject != null ? jSONObject.optString("clickToken") : null)) {
                StaticMethods.logWarningFormat("Target - couldn't send click notification for %s, the location doesn't have click metrics enabled", this.mboxName);
                return;
            }
            TargetWorker.notifications.add(TargetJsonBuilder.getClickNotificationJsonObject(jSONObject, this.mboxParameters, this.orderParameters, this.productParameters, TargetWorker.getInternalTargetParams()));
            try {
                JSONObject requestPayload = TargetJsonBuilder.getRequestPayload((List<TargetPrefetchObject>) null, (List<TargetRequestObject>) null, this.profileParameters, TargetWorker.notifications, TargetWorker.getInternalTargetParams());
                String access$1000 = TargetWorker.getTargetRequestURL();
                String sessionId = TargetWorker.getSessionId();
                StaticMethods.logDebugFormat("Target - sending click notification to url \"%s\" with parameters: %s", access$1000, requestPayload.toString());
                JSONObject access$1100 = TargetWorker.extractServerJsonResponse(RequestHandler.retrieveNetworkObject(access$1000, "POST", (String) null, requestPayload.toString(), MobileConfig.getInstance().getDefaultLocationTimeout(), "application/json", "Target", sessionId), access$1000);
                if (access$1100 != null) {
                    TargetWorker.updateSessionIdTimestamp();
                    TargetWorker.notifications.clear();
                    TargetWorker.saveTargetResponseVariables(access$1100);
                    StaticMethods.logDebugFormat("Target - click notification successfully sent for mbox (%s) with url \"%s\" with parameters: %s", this.mboxName, access$1000, requestPayload.toString());
                }
            } catch (JSONException e) {
                StaticMethods.logWarningFormat("Target - couldn't create the target click notification %s", e.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void processMboxResponse(com.adobe.mobile.TargetRequestObject r6, org.json.JSONObject r7) {
        /*
            java.lang.String r0 = "errorType"
            if (r6 != 0) goto L_0x0005
            return
        L_0x0005:
            com.adobe.mobile.Target$TargetCallback r1 = r6.getCallback()
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x001b
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r6 = r6.getMboxName()
            r7[r2] = r6
            java.lang.String r6 = "Target - No callback is provided for Target Location Request with mboxName - \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r6, r7)
            return
        L_0x001b:
            if (r7 != 0) goto L_0x0036
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r0 = r6.getMboxName()
            r7[r2] = r0
            java.lang.String r0 = "Target - Invalid prefetched data is found for the mbox mboxName - \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r7)
            com.adobe.mobile.Target$TargetCallback r7 = r6.getCallback()
            java.lang.String r6 = r6.getDefaultContent()
            r7.call(r6)
            return
        L_0x0036:
            forwardAnalyticsForTargetPayload(r7)
            r1 = 0
            java.lang.String r4 = "content"
            java.lang.String r4 = r7.getString(r4)     // Catch:{ JSONException -> 0x004e }
            boolean r5 = r7.has(r0)     // Catch:{ JSONException -> 0x004c }
            if (r5 == 0) goto L_0x005d
            java.lang.String r7 = r7.getString(r0)     // Catch:{ JSONException -> 0x004c }
            r1 = r7
            goto L_0x005d
        L_0x004c:
            r7 = move-exception
            goto L_0x0050
        L_0x004e:
            r7 = move-exception
            r4 = r1
        L_0x0050:
            java.lang.Object[] r0 = new java.lang.Object[r3]
            java.lang.String r7 = r7.getLocalizedMessage()
            r0[r2] = r7
            java.lang.String r7 = "Target - JSONException while trying to get errorType&content (%s)"
            com.adobe.mobile.StaticMethods.logDebugFormat(r7, r0)
        L_0x005d:
            if (r1 != 0) goto L_0x007d
            boolean r7 = isNullOrEmpty((java.lang.String) r4)
            if (r7 != 0) goto L_0x007d
            com.adobe.mobile.Target$TargetCallback r7 = r6.getCallback()
            r7.call(r4)
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]
            java.lang.String r6 = r6.getMboxName()
            r7[r2] = r6
            r7[r3] = r4
            java.lang.String r6 = "Target - Response returned for location \"%s\" - \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r6, r7)
            return
        L_0x007d:
            java.lang.Object[] r7 = new java.lang.Object[r3]
            java.lang.String r0 = r6.getMboxName()
            r7[r2] = r0
            java.lang.String r0 = "Target - Default content was returned for \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r7)
            com.adobe.mobile.Target$TargetCallback r7 = r6.getCallback()
            java.lang.String r6 = r6.getDefaultContent()
            r7.call(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.TargetWorker.processMboxResponse(com.adobe.mobile.TargetRequestObject, org.json.JSONObject):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void processMboxResponse(java.util.List<com.adobe.mobile.TargetRequestObject> r8, java.util.Map<java.lang.String, org.json.JSONObject> r9) {
        /*
            java.lang.String r0 = "errorType"
            if (r9 != 0) goto L_0x0008
            replyWithDefaultContent(r8)
            return
        L_0x0008:
            java.util.Iterator r8 = r8.iterator()
        L_0x000c:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x00b5
            java.lang.Object r1 = r8.next()
            com.adobe.mobile.TargetRequestObject r1 = (com.adobe.mobile.TargetRequestObject) r1
            java.lang.String r2 = r1.getMboxName()
            java.lang.Object r2 = r9.get(r2)
            org.json.JSONObject r2 = (org.json.JSONObject) r2
            forwardAnalyticsForTargetPayload(r2)
            com.adobe.mobile.Target$TargetCallback r3 = r1.getCallback()
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L_0x003b
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r1 = r1.getMboxName()
            r2[r4] = r1
            java.lang.String r1 = "Target - No callback is provided for Target Location Request with mboxName - \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r2)
            goto L_0x000c
        L_0x003b:
            if (r2 != 0) goto L_0x0056
            com.adobe.mobile.Target$TargetCallback r2 = r1.getCallback()
            java.lang.String r3 = r1.getDefaultContent()
            r2.call(r3)
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r1 = r1.getMboxName()
            r2[r4] = r1
            java.lang.String r1 = "Target - No response is obtain for Target Location Request with mboxName - \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r2)
            goto L_0x000c
        L_0x0056:
            r3 = 0
            java.lang.String r6 = "content"
            java.lang.String r6 = r2.getString(r6)     // Catch:{ JSONException -> 0x006b }
            boolean r7 = r2.has(r0)     // Catch:{ JSONException -> 0x0069 }
            if (r7 == 0) goto L_0x007a
            java.lang.String r2 = r2.getString(r0)     // Catch:{ JSONException -> 0x0069 }
            r3 = r2
            goto L_0x007a
        L_0x0069:
            r2 = move-exception
            goto L_0x006d
        L_0x006b:
            r2 = move-exception
            r6 = r3
        L_0x006d:
            java.lang.Object[] r7 = new java.lang.Object[r5]
            java.lang.String r2 = r2.getLocalizedMessage()
            r7[r4] = r2
            java.lang.String r2 = "Target - JSONException while trying to get errorType&content (%s)"
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r7)
        L_0x007a:
            if (r3 != 0) goto L_0x009b
            boolean r2 = isNullOrEmpty((java.lang.String) r6)
            if (r2 != 0) goto L_0x009b
            com.adobe.mobile.Target$TargetCallback r2 = r1.getCallback()
            r2.call(r6)
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r1 = r1.getMboxName()
            r2[r4] = r1
            r2[r5] = r6
            java.lang.String r1 = "Target - Response returned for location \"%s\" - \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r1, r2)
            goto L_0x000c
        L_0x009b:
            java.lang.Object[] r2 = new java.lang.Object[r5]
            java.lang.String r3 = r1.getMboxName()
            r2[r4] = r3
            java.lang.String r3 = "Target - Default content was returned for \"%s\""
            com.adobe.mobile.StaticMethods.logDebugFormat(r3, r2)
            com.adobe.mobile.Target$TargetCallback r2 = r1.getCallback()
            java.lang.String r1 = r1.getDefaultContent()
            r2.call(r1)
            goto L_0x000c
        L_0x00b5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.TargetWorker.processMboxResponse(java.util.List, java.util.Map):void");
    }

    /* access modifiers changed from: private */
    public static JSONObject extractServerJsonResponse(NetworkObject networkObject, String str) {
        if (networkObject == null) {
            StaticMethods.logErrorFormat("Target - unable to open connection (%s)", str);
            return null;
        } else if (isNullOrEmpty(networkObject.response)) {
            StaticMethods.logDebugFormat("Target - Response was empty", new Object[0]);
            return null;
        } else if (networkObject.responseCode != 200) {
            StaticMethods.logDebugFormat("Target - Response code from Target server (%d) was invalid, returning default content", Integer.valueOf(networkObject.responseCode));
            return null;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(networkObject.response);
                if (isNullOrEmpty(getErrorMessage(jSONObject))) {
                    return jSONObject;
                }
                StaticMethods.logDebugFormat("Target - An error was reported by the server (%s)", new Object[0]);
                return null;
            } catch (JSONException e) {
                StaticMethods.logErrorFormat("Target - JSONException while trying to get target response (%s)", e.getLocalizedMessage());
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void replyWithDefaultContent(List<TargetRequestObject> list) {
        for (TargetRequestObject next : list) {
            if (next.getCallback() != null) {
                next.getCallback().call(next.getDefaultContent());
            }
        }
    }

    /* access modifiers changed from: private */
    public static Map<String, JSONObject> extractPrefetchResponses(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray;
        HashMap hashMap = new HashMap();
        if (!jSONObject.has("prefetchResponses") || (jSONArray = jSONObject.getJSONArray("prefetchResponses")) == null) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject createCacheMboxNodeFromPrefetchResponse = createCacheMboxNodeFromPrefetchResponse(jSONArray.getJSONObject(i));
            if (createCacheMboxNodeFromPrefetchResponse != null && createCacheMboxNodeFromPrefetchResponse.length() > 0 && createCacheMboxNodeFromPrefetchResponse.has("mbox")) {
                hashMap.put(createCacheMboxNodeFromPrefetchResponse.getString("mbox"), createCacheMboxNodeFromPrefetchResponse);
            }
        }
        return hashMap;
    }

    private static JSONObject createCacheMboxNodeFromPrefetchResponse(JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        if (jSONObject == null || jSONObject.length() == 0) {
            return null;
        }
        try {
            JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!cacheMboxAcceptedKeys.contains(next)) {
                        jSONObject3.remove(next);
                    }
                }
                return jSONObject3;
            } catch (JSONException unused) {
                jSONObject2 = jSONObject3;
                StaticMethods.logWarningFormat("Target - failed to parse prefetch mbox response for %s", jSONObject.toString());
                return jSONObject2;
            }
        } catch (JSONException unused2) {
            StaticMethods.logWarningFormat("Target - failed to parse prefetch mbox response for %s", jSONObject.toString());
            return jSONObject2;
        }
    }

    /* access modifiers changed from: private */
    public static Map<String, JSONObject> extractMBoxResponses(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray;
        HashMap hashMap = new HashMap();
        if (!jSONObject.has("mboxResponses") || (jSONArray = jSONObject.getJSONArray("mboxResponses")) == null) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2.has("mbox")) {
                String string = jSONObject2.getString("mbox");
                if (!isNullOrEmpty(string)) {
                    hashMap.put(string, jSONObject2);
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public static void cacheLoadedMboxResponses(Map<String, JSONObject> map) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                JSONObject jSONObject = (JSONObject) next.getValue();
                if (!isNullOrEmpty(str) && !prefetchedMboxes.containsKey(str) && jSONObject != null) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next2 = keys.next();
                            if (!cacheLoadedMboxAcceptedKeys.contains(next2)) {
                                jSONObject2.remove(next2);
                            }
                        }
                        loadedMboxes.put(str, jSONObject2);
                    } catch (JSONException e) {
                        StaticMethods.logDebugFormat("Target - failed to save viewed mbox responses for click notification %s, error %s", jSONObject.toString(), e.getLocalizedMessage());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void removeDuplicatedMboxesFromCaches() {
        for (String next : prefetchedMboxes.keySet()) {
            if (next != null && loadedMboxes.containsKey(next)) {
                loadedMboxes.remove(next);
            }
        }
    }

    /* access modifiers changed from: private */
    public static String getTargetRequestURL() {
        String clientCode = MobileConfig.getInstance().getClientCode();
        String edgeHost = getEdgeHost();
        if (isNullOrEmpty(edgeHost)) {
            edgeHost = String.format(TARGET_API_URL_HOST_BASE, new Object[]{clientCode});
        }
        return String.format(TARGET_PREFETCH_API_URL_BASE, new Object[]{edgeHost});
    }

    public static String getSessionId() {
        String str;
        synchronized (_sessionIdMutex) {
            if (isNullOrEmpty(_sessionId)) {
                try {
                    _sessionId = StaticMethods.getSharedPreferences().getString("ADBMOBILE_TARGET_SESSION_ID", (String) null);
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
                }
            }
            if (isNullOrEmpty(_sessionId) || isSessionExpired()) {
                _sessionId = UUID.randomUUID().toString();
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    sharedPreferencesEditor.putString("ADBMOBILE_TARGET_SESSION_ID", _sessionId);
                    sharedPreferencesEditor.commit();
                } catch (StaticMethods.NullContextException unused2) {
                    StaticMethods.logErrorFormat("Target - Error storing session id in shared preferences - application context is null", new Object[0]);
                }
                updateSessionIdTimestamp();
            }
            str = _sessionId;
        }
        return str;
    }

    private static boolean isSessionExpired() {
        boolean z;
        synchronized (_sessionIdMutex) {
            z = false;
            if (_sessionIdTimestampSeconds <= 0) {
                try {
                    _sessionIdTimestampSeconds = StaticMethods.getSharedPreferences().getLong("ADBMOBILE_TARGET_LAST_TIMESTAMP", 0);
                } catch (StaticMethods.NullContextException unused) {
                    StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
                }
            }
            long timeSince1970 = StaticMethods.getTimeSince1970();
            int targetSessionTimeout = MobileConfig.getInstance().getTargetSessionTimeout();
            if (_sessionIdTimestampSeconds > 0 && timeSince1970 - _sessionIdTimestampSeconds > ((long) targetSessionTimeout)) {
                z = true;
            }
        }
        return z;
    }

    private static void resetSession() {
        synchronized (_sessionIdMutex) {
            _sessionId = null;
            _sessionIdTimestampSeconds = 0;
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.remove("ADBMOBILE_TARGET_SESSION_ID");
                sharedPreferencesEditor.remove("ADBMOBILE_TARGET_LAST_TIMESTAMP");
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException unused) {
                StaticMethods.logErrorFormat("Target - Error resetting session from shared preferences - application context is null", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void updateSessionIdTimestamp() {
        synchronized (_sessionIdMutex) {
            _sessionIdTimestampSeconds = StaticMethods.getTimeSince1970();
            try {
                SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                sharedPreferencesEditor.putLong("ADBMOBILE_TARGET_LAST_TIMESTAMP", _sessionIdTimestampSeconds);
                sharedPreferencesEditor.commit();
            } catch (StaticMethods.NullContextException unused) {
                StaticMethods.logErrorFormat("Target - Error persisting session id timestamp in shared preferences - application context is null", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void saveTargetResponseVariables(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.getJSONObject("id") == null) {
                    setTntId((String) null);
                } else {
                    setTntId(jSONObject.getJSONObject("id").getString(TARGET_API_JSON_TNT_ID));
                }
            } catch (JSONException unused) {
                setTntId((String) null);
            }
            try {
                setEdgeHost(jSONObject.getString(TARGET_API_JSON_EDGE_HOST));
            } catch (JSONException unused2) {
                setEdgeHost((String) null);
            }
        }
    }

    private static String getErrorMessage(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getString("message");
        } catch (JSONException unused) {
            return null;
        }
    }

    protected static void setTntIdFromOldCookieValues() {
        synchronized (_checkOldCookiesMutex) {
            if (!_oldCookiesHaveBeenChecked) {
                String checkForOldCookieValue = checkForOldCookieValue("mboxPC");
                if (checkForOldCookieValue != null) {
                    setTntId(checkForOldCookieValue);
                }
                _oldCookiesHaveBeenChecked = true;
            }
        }
    }

    private static String checkForOldCookieValue(String str) {
        String str2 = null;
        if (!(str == null || str.length() == 0)) {
            try {
                SharedPreferences sharedPreferences = StaticMethods.getSharedPreferences();
                if (sharedPreferences.contains(str + COOKIE_EXPIRES_KEY_SUFFIX)) {
                    if (sharedPreferences.getLong(str + COOKIE_EXPIRES_KEY_SUFFIX, 0) > System.currentTimeMillis()) {
                        String string = sharedPreferences.getString(str + COOKIE_VALUE_KEY_SUFFIX, "");
                        if (string.length() > 0) {
                            str2 = string;
                        }
                    }
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    sharedPreferencesEditor.remove(str + COOKIE_VALUE_KEY_SUFFIX);
                    sharedPreferencesEditor.remove(str + COOKIE_EXPIRES_KEY_SUFFIX);
                    sharedPreferencesEditor.commit();
                }
            } catch (StaticMethods.NullContextException unused) {
                StaticMethods.logErrorFormat("Target - Error retrieving shared preferences - application context is null", new Object[0]);
            }
        }
        return str2;
    }

    private static boolean tntIdValuesAreEqual(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        if (str.equals(str2)) {
            return true;
        }
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        int indexOf2 = str2.indexOf(46);
        if (indexOf2 != -1) {
            str2 = str2.substring(0, indexOf2);
        }
        return str.equals(str2);
    }

    private static Map<String, Object> getProfileParametersLegacy(Map<String, Object> map) {
        if (isNullOrEmpty((Map) map)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry entry : new HashMap(map).entrySet()) {
            String str = (String) entry.getKey();
            if (str != null) {
                String trim = str.trim();
                String lowerCase = trim.toLowerCase();
                if (lowerCase.startsWith(PROFILE_PARAMETER_PREFIX)) {
                    hashMap.put(trim.substring(lowerCase.indexOf(".") + 1), entry.getValue());
                    map.remove(entry.getKey());
                }
            }
        }
        return hashMap;
    }

    private static Map<String, Object> getOrderParametersLegacy(Map<String, Object> map) {
        if (isNullOrEmpty((Map) map)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Object obj = map.get(TargetLocationRequest.TARGET_PARAMETER_ORDER_ID);
        Object obj2 = map.get(TargetLocationRequest.TARGET_PARAMETER_ORDER_TOTAL);
        Object obj3 = map.get(TargetLocationRequest.TARGET_PARAMETER_PRODUCT_PURCHASE_ID);
        if (obj != null && !obj.toString().isEmpty()) {
            hashMap.put("id", obj.toString());
            map.remove(TargetLocationRequest.TARGET_PARAMETER_ORDER_ID);
        }
        if (obj2 != null && !obj2.toString().isEmpty()) {
            hashMap.put("total", obj2);
            map.remove(TargetLocationRequest.TARGET_PARAMETER_ORDER_TOTAL);
        }
        if (obj3 != null && !obj3.toString().isEmpty()) {
            hashMap.put(TargetLocationRequest.TARGET_PARAMETER_PRODUCT_PURCHASE_ID, obj3);
            map.remove(TargetLocationRequest.TARGET_PARAMETER_PRODUCT_PURCHASE_ID);
        }
        return hashMap;
    }

    private static void getThirdPartyIDLegacy(Map<String, Object> map) {
        if (map != null && map.containsKey(TargetLocationRequest.TARGET_PARAMETER_MBOX_3RDPARTY_ID)) {
            Object obj = map.get(TargetLocationRequest.TARGET_PARAMETER_MBOX_3RDPARTY_ID);
            if (obj != null) {
                setThirdPartyId(obj.toString());
            } else {
                setThirdPartyId((String) null);
            }
            map.remove(TargetLocationRequest.TARGET_PARAMETER_MBOX_3RDPARTY_ID);
        }
    }

    /* access modifiers changed from: private */
    public static HashMap<String, Object> getInternalTargetParams() {
        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> contextData = Lifecycle.getContextData();
        if (!isNullOrEmpty((Map) contextData)) {
            hashMap.putAll(contextData);
        }
        BigDecimal lifetimeValue = AnalyticsTrackLifetimeValueIncrease.getLifetimeValue();
        if (lifetimeValue != null) {
            addPersistentParameter("a.ltv.amount", lifetimeValue.toString());
        }
        synchronized (_persistentParametersMutex) {
            if (!isNullOrEmpty((Map) _persistentParameters)) {
                hashMap.putAll(_persistentParameters);
            }
        }
        return hashMap;
    }

    private static void forwardAnalyticsForTargetPayload(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() > 0) {
            HashMap<String, Object> hashMap = null;
            try {
                hashMap = StaticMethods.mapFromJson(jSONObject.getJSONObject("clientSideAnalyticsLoggingPayload"));
            } catch (JSONException e) {
                StaticMethods.logDebugFormat("Target - A4T is not enabled for this location (%s)", e.getLocalizedMessage());
            }
            if (!isNullOrEmpty((Map) hashMap)) {
                HashMap hashMap2 = new HashMap(hashMap.size());
                for (Map.Entry next : hashMap.entrySet()) {
                    hashMap2.put("&&" + ((String) next.getKey()), next.getValue());
                }
                AnalyticsTrackInternal.trackInternal(TARGET_API_A4T_ACTION_NAME, hashMap2);
                StaticMethods.logDebugFormat("Target - A4T is enabled, the payload was successfully forwarded to Analytics", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /* access modifiers changed from: private */
    public static boolean isNullOrEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /* access modifiers changed from: private */
    public static boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }
}
