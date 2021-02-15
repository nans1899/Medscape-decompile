package com.adobe.mobile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.adobe.mobile.Config;
import com.adobe.mobile.RemoteDownload;
import com.adobe.mobile.StaticMethods;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class MobileConfig {
    private static final String CONFIG_FILE_NAME = "ADBMobileConfig.json";
    private static final String CONFIG_PRIVACY_OPTED_IN = "optedin";
    private static final String CONFIG_PRIVACY_OPTED_OUT = "optedout";
    private static final String CONFIG_PRIVACY_UNKNOWN = "optunknown";
    private static final boolean DEFAULT_AAM_ANALYTICS_FORWARD_ENABLED = false;
    private static final int DEFAULT_AAM_TIMEOUT = 2;
    private static final boolean DEFAULT_BACKDATE_SESSION_INFO = true;
    private static final int DEFAULT_BATCH_LIMIT = 0;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final boolean DEFAULT_JSON_CONFIG_COOP_UNSAFE = false;
    private static final int DEFAULT_LIFECYCLE_TIMEOUT = 300;
    private static final int DEFAULT_LOCATION_TIMEOUT = 2;
    private static final boolean DEFAULT_OFFLINE_TRACKING = false;
    private static final MobilePrivacyStatus DEFAULT_PRIVACY_STATUS = MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN;
    private static final boolean DEFAULT_REACHABILITY_CHECKS_ENABLED = true;
    private static final int DEFAULT_REFERRER_TIMEOUT = 0;
    private static final boolean DEFAULT_SSL = true;
    private static final int DEFAULT_TARGET_SESSION_TIMEOUT_SECONDS = 1800;
    private static final String JSON_CONFIG_AAM_ANALYTICS_FORWARD_KEY = "analyticsForwardingEnabled";
    private static final String JSON_CONFIG_AAM_KEY = "audienceManager";
    private static final String JSON_CONFIG_ACQUISITION_KEY = "acquisition";
    private static final String JSON_CONFIG_ANALYTICS_KEY = "analytics";
    private static final String JSON_CONFIG_APP_IDENTIFIER_KEY = "appid";
    private static final String JSON_CONFIG_BACKDATE_SESSION_INFO_KEY = "backdateSessionInfo";
    private static final String JSON_CONFIG_BATCH_LIMIT_KEY = "batchLimit";
    private static final String JSON_CONFIG_CHAR_SET_KEY = "charset";
    private static final String JSON_CONFIG_CLIENT_CODE_KEY = "clientCode";
    private static final String JSON_CONFIG_COOP_UNSAFE_KEY = "coopUnsafe";
    private static final String JSON_CONFIG_ENVIRONMENTID_KEY = "environmentId";
    private static final String JSON_CONFIG_LIFECYCLE_TIMEOUT_KEY = "lifecycleTimeout";
    private static final String JSON_CONFIG_MARKETINGCLOUD_KEY = "marketingCloud";
    private static final String JSON_CONFIG_MESSAGES_KEY = "messages";
    private static final String JSON_CONFIG_MESSAGES_URL_KEY = "messages";
    private static final String JSON_CONFIG_OFFLINE_TRACKING_KEY = "offlineEnabled";
    private static final String JSON_CONFIG_ORGID_KEY = "org";
    private static final String JSON_CONFIG_POINTS_OF_INTEREST_KEY = "poi";
    private static final String JSON_CONFIG_POI_REMOTES_KEY = "analytics.poi";
    private static final String JSON_CONFIG_PRIVACY_DEFAULT_KEY = "privacyDefault";
    private static final String JSON_CONFIG_REACHABILITY_CHECKS_ENABLED_KEY = "reachabilityChecksEnabled";
    private static final String JSON_CONFIG_REFERRER_TIMEOUT_KEY = "referrerTimeout";
    private static final String JSON_CONFIG_REMOTES_KEY = "remotes";
    private static final String JSON_CONFIG_REPORT_SUITES_KEY = "rsids";
    private static final String JSON_CONFIG_SERVER_KEY = "server";
    private static final String JSON_CONFIG_SESSION_TIMEOUT_KEY = "sessionTimeout";
    private static final String JSON_CONFIG_SSL_KEY = "ssl";
    private static final String JSON_CONFIG_TARGET_KEY = "target";
    private static final String JSON_CONFIG_TIMEOUT_KEY = "timeout";
    private static MobileConfig _instance = null;
    private static final Object _instanceMutex = new Object();
    private static InputStream _userDefinedInputStream = null;
    private static final Object _userDefinedInputStreamMutex = new Object();
    private static final Object _usingAnalyticsMutex = new Object();
    private static final Object _usingAudienceManagerMutex = new Object();
    private static final Object _usingGooglePlayServicesMutex = new Object();
    private static final Object _usingTargetMutex = new Object();
    private boolean _aamAnalyticsForwardingEnabled = false;
    private String _aamServer = null;
    private int _aamTimeout = 2;
    private String _acquisitionAppIdentifier = null;
    private String _acquisitionServer = null;
    private Config.AdobeDataCallback _adobeDataCallback = null;
    private boolean _backdateSessionInfoEnabled = true;
    private int _batchLimit = 0;
    private ArrayList<Message> _callbackTemplates = null;
    private String _characterSet = "UTF-8";
    private String _clientCode = null;
    private int _defaultLocationTimeout = 2;
    private long _environmentId = 0;
    /* access modifiers changed from: private */
    public ArrayList<Message> _inAppMessages = null;
    private int _lifecycleTimeout = 300;
    private boolean _marketingCloudCoopUnsafe = false;
    private String _marketingCloudOrganizationId = null;
    private String _marketingCloudServer = null;
    /* access modifiers changed from: private */
    public String _messagesURL = null;
    /* access modifiers changed from: private */
    public boolean _networkConnectivity = false;
    private boolean _offlineTrackingEnabled = false;
    private ArrayList<Message> _piiRequests = null;
    private List<List<Object>> _pointsOfInterest = null;
    private String _pointsOfInterestURL = null;
    private MobilePrivacyStatus _privacyStatus = DEFAULT_PRIVACY_STATUS;
    private boolean _reachabilityChecksEnabled = true;
    private int _referrerTimeout = 0;
    private String _reportSuiteIds = null;
    private boolean _ssl = true;
    private int _targetSessionTimeout = DEFAULT_TARGET_SESSION_TIMEOUT_SECONDS;
    private String _trackingServer = null;
    private Boolean _usingAnalytics = null;
    private Boolean _usingAudienceManager = null;
    private Boolean _usingGooglePlayServices = null;
    private Boolean _usingTarget = null;

    protected static MobileConfig getInstance() {
        MobileConfig mobileConfig;
        synchronized (_instanceMutex) {
            if (_instance == null) {
                _instance = new MobileConfig();
            }
            mobileConfig = _instance;
        }
        return mobileConfig;
    }

    /* JADX WARNING: Removed duplicated region for block: B:112:0x019e A[SYNTHETIC, Splitter:B:112:0x019e] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x020a A[SYNTHETIC, Splitter:B:147:0x020a] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x025f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private MobileConfig() {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r0 = "PrivacyStatus"
            java.lang.String r2 = "Audience Manager - Not Configured."
            java.lang.String r3 = "Target - Not Configured."
            r16.<init>()
            r4 = 0
            r1._networkConnectivity = r4
            r5 = 0
            r1._adobeDataCallback = r5
            r6 = 1
            r1._reachabilityChecksEnabled = r6
            r1._reportSuiteIds = r5
            r1._trackingServer = r5
            java.lang.String r7 = "UTF-8"
            r1._characterSet = r7
            r1._ssl = r6
            r1._offlineTrackingEnabled = r4
            r1._backdateSessionInfoEnabled = r6
            r8 = 300(0x12c, float:4.2E-43)
            r1._lifecycleTimeout = r8
            r1._referrerTimeout = r4
            r1._batchLimit = r4
            com.adobe.mobile.MobilePrivacyStatus r9 = DEFAULT_PRIVACY_STATUS
            r1._privacyStatus = r9
            r1._pointsOfInterest = r5
            r1._pointsOfInterestURL = r5
            r1._clientCode = r5
            r9 = 2
            r1._defaultLocationTimeout = r9
            r10 = 0
            r1._environmentId = r10
            r12 = 1800(0x708, float:2.522E-42)
            r1._targetSessionTimeout = r12
            r1._aamServer = r5
            r1._aamAnalyticsForwardingEnabled = r4
            r1._aamTimeout = r9
            r1._acquisitionServer = r5
            r1._acquisitionAppIdentifier = r5
            r1._messagesURL = r5
            r1._inAppMessages = r5
            r1._callbackTemplates = r5
            r1._piiRequests = r5
            r1._marketingCloudOrganizationId = r5
            r1._marketingCloudServer = r5
            r1._marketingCloudCoopUnsafe = r4
            r1._usingAnalytics = r5
            r1._usingGooglePlayServices = r5
            r1._usingAudienceManager = r5
            r1._usingTarget = r5
            org.json.JSONObject r13 = r16.loadBundleConfig()
            if (r13 != 0) goto L_0x0066
            return
        L_0x0066:
            java.lang.String r14 = "reachabilityChecksEnabled"
            boolean r14 = r13.optBoolean(r14, r6)
            r1._reachabilityChecksEnabled = r14
            if (r14 == 0) goto L_0x0073
            r16.startNotifier()
        L_0x0073:
            java.lang.String r14 = "analytics"
            org.json.JSONObject r14 = r13.getJSONObject(r14)     // Catch:{ JSONException -> 0x007a }
            goto L_0x0082
        L_0x007a:
            java.lang.Object[] r14 = new java.lang.Object[r4]
            java.lang.String r15 = "Analytics - Not configured."
            com.adobe.mobile.StaticMethods.logDebugFormat(r15, r14)
            r14 = r5
        L_0x0082:
            java.lang.String r15 = "server"
            if (r14 == 0) goto L_0x0143
            java.lang.String r12 = r14.getString(r15)     // Catch:{ JSONException -> 0x0095 }
            r1._trackingServer = r12     // Catch:{ JSONException -> 0x0095 }
            java.lang.String r12 = "rsids"
            java.lang.String r12 = r14.getString(r12)     // Catch:{ JSONException -> 0x0095 }
            r1._reportSuiteIds = r12     // Catch:{ JSONException -> 0x0095 }
            goto L_0x00a0
        L_0x0095:
            r1._trackingServer = r5
            r1._reportSuiteIds = r5
            java.lang.Object[] r12 = new java.lang.Object[r4]
            java.lang.String r10 = "Analytics - Not Configured."
            com.adobe.mobile.StaticMethods.logDebugFormat(r10, r12)
        L_0x00a0:
            java.lang.String r10 = "charset"
            java.lang.String r10 = r14.getString(r10)     // Catch:{ JSONException -> 0x00a9 }
            r1._characterSet = r10     // Catch:{ JSONException -> 0x00a9 }
            goto L_0x00ab
        L_0x00a9:
            r1._characterSet = r7
        L_0x00ab:
            java.lang.String r7 = "ssl"
            boolean r7 = r14.getBoolean(r7)     // Catch:{ JSONException -> 0x00b4 }
            r1._ssl = r7     // Catch:{ JSONException -> 0x00b4 }
            goto L_0x00b6
        L_0x00b4:
            r1._ssl = r6
        L_0x00b6:
            java.lang.String r7 = "offlineEnabled"
            boolean r7 = r14.getBoolean(r7)     // Catch:{ JSONException -> 0x00bf }
            r1._offlineTrackingEnabled = r7     // Catch:{ JSONException -> 0x00bf }
            goto L_0x00c1
        L_0x00bf:
            r1._offlineTrackingEnabled = r4
        L_0x00c1:
            java.lang.String r7 = "backdateSessionInfo"
            boolean r7 = r14.getBoolean(r7)     // Catch:{ JSONException -> 0x00ca }
            r1._backdateSessionInfoEnabled = r7     // Catch:{ JSONException -> 0x00ca }
            goto L_0x00cc
        L_0x00ca:
            r1._backdateSessionInfoEnabled = r6
        L_0x00cc:
            java.lang.String r7 = "lifecycleTimeout"
            int r7 = r14.getInt(r7)     // Catch:{ JSONException -> 0x00d5 }
            r1._lifecycleTimeout = r7     // Catch:{ JSONException -> 0x00d5 }
            goto L_0x00d7
        L_0x00d5:
            r1._lifecycleTimeout = r8
        L_0x00d7:
            java.lang.String r7 = "referrerTimeout"
            int r7 = r14.getInt(r7)     // Catch:{ JSONException -> 0x00e0 }
            r1._referrerTimeout = r7     // Catch:{ JSONException -> 0x00e0 }
            goto L_0x00e2
        L_0x00e0:
            r1._referrerTimeout = r4
        L_0x00e2:
            java.lang.String r7 = "batchLimit"
            int r7 = r14.getInt(r7)     // Catch:{ JSONException -> 0x00eb }
            r1._batchLimit = r7     // Catch:{ JSONException -> 0x00eb }
            goto L_0x00ed
        L_0x00eb:
            r1._batchLimit = r4
        L_0x00ed:
            android.content.SharedPreferences r7 = com.adobe.mobile.StaticMethods.getSharedPreferences()     // Catch:{ NullContextException -> 0x0134 }
            boolean r7 = r7.contains(r0)     // Catch:{ NullContextException -> 0x0134 }
            if (r7 == 0) goto L_0x0108
            com.adobe.mobile.MobilePrivacyStatus[] r7 = com.adobe.mobile.MobilePrivacyStatus.values()     // Catch:{ NullContextException -> 0x0134 }
            android.content.SharedPreferences r8 = com.adobe.mobile.StaticMethods.getSharedPreferences()     // Catch:{ NullContextException -> 0x0134 }
            int r0 = r8.getInt(r0, r4)     // Catch:{ NullContextException -> 0x0134 }
            r0 = r7[r0]     // Catch:{ NullContextException -> 0x0134 }
            r1._privacyStatus = r0     // Catch:{ NullContextException -> 0x0134 }
            goto L_0x011b
        L_0x0108:
            java.lang.String r0 = "privacyDefault"
            java.lang.String r0 = r14.getString(r0)     // Catch:{ JSONException -> 0x010f }
            goto L_0x0110
        L_0x010f:
            r0 = r5
        L_0x0110:
            if (r0 == 0) goto L_0x0117
            com.adobe.mobile.MobilePrivacyStatus r0 = r1.privacyStatusFromString(r0)     // Catch:{ NullContextException -> 0x0134 }
            goto L_0x0119
        L_0x0117:
            com.adobe.mobile.MobilePrivacyStatus r0 = DEFAULT_PRIVACY_STATUS     // Catch:{ NullContextException -> 0x0134 }
        L_0x0119:
            r1._privacyStatus = r0     // Catch:{ NullContextException -> 0x0134 }
        L_0x011b:
            java.lang.String r0 = "poi"
            org.json.JSONArray r0 = r14.getJSONArray(r0)     // Catch:{ JSONException -> 0x0125 }
            r1.loadPoiFromJsonArray(r0)     // Catch:{ JSONException -> 0x0125 }
            goto L_0x0143
        L_0x0125:
            r0 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r6]
            java.lang.String r0 = r0.getLocalizedMessage()
            r7[r4] = r0
            java.lang.String r0 = "Analytics - Malformed POI List(%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r7)
            goto L_0x0143
        L_0x0134:
            r0 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r0 = r0.getMessage()
            r2[r4] = r0
            java.lang.String r0 = "Config - Error pulling privacy from shared preferences. (%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
            return
        L_0x0143:
            java.lang.String r0 = "target"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch:{ JSONException -> 0x014b }
            goto L_0x0151
        L_0x014b:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r3, r0)
            r0 = r5
        L_0x0151:
            java.lang.String r7 = "timeout"
            if (r0 == 0) goto L_0x018f
            java.lang.String r8 = "clientCode"
            java.lang.String r8 = r0.getString(r8)     // Catch:{ JSONException -> 0x015f }
            r1._clientCode = r8     // Catch:{ JSONException -> 0x015f }
            goto L_0x0166
        L_0x015f:
            r1._clientCode = r5
            java.lang.Object[] r8 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r3, r8)
        L_0x0166:
            int r3 = r0.getInt(r7)     // Catch:{ JSONException -> 0x016d }
            r1._defaultLocationTimeout = r3     // Catch:{ JSONException -> 0x016d }
            goto L_0x016f
        L_0x016d:
            r1._defaultLocationTimeout = r9
        L_0x016f:
            java.lang.String r3 = "environmentId"
            long r10 = r0.getLong(r3)     // Catch:{ JSONException -> 0x0178 }
            r1._environmentId = r10     // Catch:{ JSONException -> 0x0178 }
            goto L_0x017c
        L_0x0178:
            r10 = 0
            r1._environmentId = r10
        L_0x017c:
            java.lang.String r3 = "sessionTimeout"
            int r0 = r0.getInt(r3)     // Catch:{ JSONException -> 0x018b }
            r1._targetSessionTimeout = r0     // Catch:{ JSONException -> 0x018b }
            if (r0 >= 0) goto L_0x018f
            r3 = 1800(0x708, float:2.522E-42)
            r1._targetSessionTimeout = r3     // Catch:{ JSONException -> 0x018d }
            goto L_0x018f
        L_0x018b:
            r3 = 1800(0x708, float:2.522E-42)
        L_0x018d:
            r1._targetSessionTimeout = r3
        L_0x018f:
            java.lang.String r0 = "audienceManager"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch:{ JSONException -> 0x0196 }
            goto L_0x019c
        L_0x0196:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
            r0 = r5
        L_0x019c:
            if (r0 == 0) goto L_0x01cb
            java.lang.String r3 = r0.getString(r15)     // Catch:{ JSONException -> 0x01a5 }
            r1._aamServer = r3     // Catch:{ JSONException -> 0x01a5 }
            goto L_0x01ac
        L_0x01a5:
            r1._aamServer = r5
            java.lang.Object[] r3 = new java.lang.Object[r4]
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r3)
        L_0x01ac:
            java.lang.String r2 = "analyticsForwardingEnabled"
            boolean r2 = r0.getBoolean(r2)     // Catch:{ JSONException -> 0x01b5 }
            r1._aamAnalyticsForwardingEnabled = r2     // Catch:{ JSONException -> 0x01b5 }
            goto L_0x01b7
        L_0x01b5:
            r1._aamAnalyticsForwardingEnabled = r4
        L_0x01b7:
            boolean r2 = r1._aamAnalyticsForwardingEnabled
            if (r2 == 0) goto L_0x01c2
            java.lang.Object[] r2 = new java.lang.Object[r4]
            java.lang.String r3 = "Audience Manager - Analytics Server-Side Forwarding Is Enabled."
            com.adobe.mobile.StaticMethods.logDebugFormat(r3, r2)
        L_0x01c2:
            int r0 = r0.getInt(r7)     // Catch:{ JSONException -> 0x01c9 }
            r1._aamTimeout = r0     // Catch:{ JSONException -> 0x01c9 }
            goto L_0x01cb
        L_0x01c9:
            r1._aamTimeout = r9
        L_0x01cb:
            java.lang.String r0 = "acquisition"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch:{ JSONException -> 0x01d2 }
            goto L_0x01da
        L_0x01d2:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Acquisition - Not Configured."
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
            r0 = r5
        L_0x01da:
            if (r0 == 0) goto L_0x01f6
            java.lang.String r2 = "appid"
            java.lang.String r2 = r0.getString(r2)     // Catch:{ JSONException -> 0x01eb }
            r1._acquisitionAppIdentifier = r2     // Catch:{ JSONException -> 0x01eb }
            java.lang.String r0 = r0.getString(r15)     // Catch:{ JSONException -> 0x01eb }
            r1._acquisitionServer = r0     // Catch:{ JSONException -> 0x01eb }
            goto L_0x01f6
        L_0x01eb:
            r1._acquisitionAppIdentifier = r5
            r1._acquisitionServer = r5
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Acquisition - Not configured correctly (missing server or app identifier)."
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
        L_0x01f6:
            java.lang.String r0 = "remotes"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch:{ JSONException -> 0x01fe }
            r2 = r0
            goto L_0x0206
        L_0x01fe:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Remotes - Not Configured."
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
            r2 = r5
        L_0x0206:
            java.lang.String r3 = "messages"
            if (r2 == 0) goto L_0x0236
            java.lang.String r0 = r2.getString(r3)     // Catch:{ JSONException -> 0x0211 }
            r1._messagesURL = r0     // Catch:{ JSONException -> 0x0211 }
            goto L_0x021f
        L_0x0211:
            r0 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r6]
            java.lang.String r0 = r0.getLocalizedMessage()
            r7[r4] = r0
            java.lang.String r0 = "Config - No in-app messages remote url loaded (%s)"
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r7)
        L_0x021f:
            java.lang.String r0 = "analytics.poi"
            java.lang.String r0 = r2.getString(r0)     // Catch:{ JSONException -> 0x0228 }
            r1._pointsOfInterestURL = r0     // Catch:{ JSONException -> 0x0228 }
            goto L_0x0236
        L_0x0228:
            r0 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r6]
            java.lang.String r0 = r0.getLocalizedMessage()
            r2[r4] = r0
            java.lang.String r0 = "Config - No points of interest remote url loaded (%s)"
            com.adobe.mobile.StaticMethods.logDebugFormat(r0, r2)
        L_0x0236:
            org.json.JSONArray r0 = r13.getJSONArray(r3)     // Catch:{ JSONException -> 0x023b }
            goto L_0x0243
        L_0x023b:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Messages - Not configured locally."
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
            r0 = r5
        L_0x0243:
            if (r0 == 0) goto L_0x024e
            int r2 = r0.length()
            if (r2 <= 0) goto L_0x024e
            r1.loadMessagesFromJsonArray(r0)
        L_0x024e:
            java.lang.String r0 = "marketingCloud"
            org.json.JSONObject r0 = r13.getJSONObject(r0)     // Catch:{ JSONException -> 0x0255 }
            goto L_0x025d
        L_0x0255:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Marketing Cloud - Not configured locally."
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
            r0 = r5
        L_0x025d:
            if (r0 == 0) goto L_0x0291
            java.lang.String r2 = "org"
            java.lang.String r2 = r0.getString(r2)     // Catch:{ JSONException -> 0x0268 }
            r1._marketingCloudOrganizationId = r2     // Catch:{ JSONException -> 0x0268 }
            goto L_0x0271
        L_0x0268:
            r1._marketingCloudOrganizationId = r5
            java.lang.Object[] r2 = new java.lang.Object[r4]
            java.lang.String r3 = "Visitor - ID Service Not Configured."
            com.adobe.mobile.StaticMethods.logDebugFormat(r3, r2)
        L_0x0271:
            java.lang.String r2 = r0.getString(r15)     // Catch:{ JSONException -> 0x0278 }
            r1._marketingCloudServer = r2     // Catch:{ JSONException -> 0x0278 }
            goto L_0x0281
        L_0x0278:
            r1._marketingCloudServer = r5
            java.lang.Object[] r2 = new java.lang.Object[r4]
            java.lang.String r3 = "Visitor ID Service - Custom endpoint not found in configuration, using default endpoint."
            com.adobe.mobile.StaticMethods.logDebugFormat(r3, r2)
        L_0x0281:
            java.lang.String r2 = "coopUnsafe"
            boolean r0 = r0.getBoolean(r2)     // Catch:{ JSONException -> 0x028a }
            r1._marketingCloudCoopUnsafe = r0     // Catch:{ JSONException -> 0x028a }
            goto L_0x0291
        L_0x028a:
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r2 = "Visitor - Coop Unsafe Not Configured."
            com.adobe.mobile.StaticMethods.logDebugFormat(r2, r0)
        L_0x0291:
            r16.loadCachedRemotes()
            r16.updateBlacklist()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MobileConfig.<init>():void");
    }

    /* access modifiers changed from: protected */
    public boolean mobileUsingAnalytics() {
        boolean booleanValue;
        synchronized (_usingAnalyticsMutex) {
            if (this._usingAnalytics == null) {
                Boolean valueOf = Boolean.valueOf(getReportSuiteIds() != null && getReportSuiteIds().length() > 0 && getTrackingServer() != null && getTrackingServer().length() > 0);
                this._usingAnalytics = valueOf;
                if (!valueOf.booleanValue()) {
                    StaticMethods.logDebugFormat("Analytics - Your config file is not set up to use Analytics(missing report suite id(s) or tracking server information)", new Object[0]);
                }
            }
            booleanValue = this._usingAnalytics.booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public boolean mobileUsingGooglePlayServices() {
        boolean booleanValue;
        synchronized (_usingGooglePlayServicesMutex) {
            if (this._usingGooglePlayServices == null) {
                this._usingGooglePlayServices = Boolean.valueOf(WearableFunctionBridge.isGooglePlayServicesEnabled());
            }
            booleanValue = this._usingGooglePlayServices.booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public boolean mobileUsingAudienceManager() {
        boolean booleanValue;
        if (StaticMethods.isWearableApp()) {
            return false;
        }
        synchronized (_usingAudienceManagerMutex) {
            if (this._usingAudienceManager == null) {
                Boolean valueOf = Boolean.valueOf(getAamServer() != null && getAamServer().length() > 0);
                this._usingAudienceManager = valueOf;
                if (!valueOf.booleanValue()) {
                    StaticMethods.logDebugFormat("Audience Manager - Your config file is not set up to use Audience Manager(missing audience manager server information)", new Object[0]);
                }
            }
            booleanValue = this._usingAudienceManager.booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public boolean mobileUsingTarget() {
        boolean booleanValue;
        if (StaticMethods.isWearableApp()) {
            return false;
        }
        synchronized (_usingTargetMutex) {
            if (this._usingTarget == null) {
                Boolean valueOf = Boolean.valueOf(getClientCode() != null && getClientCode().length() > 0);
                this._usingTarget = valueOf;
                if (!valueOf.booleanValue()) {
                    StaticMethods.logDebugFormat("Target Worker - Your config file is not set up to use Target(missing client code information)", new Object[0]);
                }
            }
            booleanValue = this._usingTarget.booleanValue();
        }
        return booleanValue;
    }

    /* access modifiers changed from: protected */
    public boolean reachabilityChecksEnabled() {
        return this._reachabilityChecksEnabled;
    }

    /* access modifiers changed from: protected */
    public boolean mobileReferrerConfigured() {
        String str = this._acquisitionServer;
        return str != null && this._acquisitionAppIdentifier != null && str.length() > 0 && this._acquisitionAppIdentifier.length() > 0;
    }

    /* access modifiers changed from: protected */
    public void setAdobeDataCallback(Config.AdobeDataCallback adobeDataCallback) {
        this._adobeDataCallback = adobeDataCallback;
    }

    /* access modifiers changed from: protected */
    public void invokeAdobeDataCallback(Config.MobileDataEvent mobileDataEvent, Map<String, Object> map) {
        Config.AdobeDataCallback adobeDataCallback = this._adobeDataCallback;
        if (adobeDataCallback == null) {
            StaticMethods.logDebugFormat("Config - A callback has not been registered for Adobe events.", new Object[0]);
        } else if (map != null) {
            adobeDataCallback.call(mobileDataEvent, new HashMap(map));
        } else {
            adobeDataCallback.call(mobileDataEvent, (Map<String, Object>) null);
        }
    }

    /* access modifiers changed from: protected */
    public String getReportSuiteIds() {
        return this._reportSuiteIds;
    }

    /* access modifiers changed from: protected */
    public String getTrackingServer() {
        return this._trackingServer;
    }

    /* access modifiers changed from: protected */
    public String getCharacterSet() {
        return this._characterSet;
    }

    /* access modifiers changed from: protected */
    public boolean getSSL() {
        return this._ssl;
    }

    /* access modifiers changed from: protected */
    public boolean getOfflineTrackingEnabled() {
        return this._offlineTrackingEnabled;
    }

    /* access modifiers changed from: protected */
    public boolean getBackdateSessionInfoEnabled() {
        return this._backdateSessionInfoEnabled;
    }

    /* access modifiers changed from: protected */
    public int getLifecycleTimeout() {
        return this._lifecycleTimeout;
    }

    /* access modifiers changed from: protected */
    public int getBatchLimit() {
        return this._batchLimit;
    }

    /* access modifiers changed from: protected */
    public void setPrivacyStatus(MobilePrivacyStatus mobilePrivacyStatus) {
        if (mobilePrivacyStatus != null) {
            if (mobilePrivacyStatus != MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_UNKNOWN || this._offlineTrackingEnabled) {
                if (mobilePrivacyStatus == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
                    StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                        public void run() {
                            StaticMethods.logDebugFormat("Analytics - Privacy status set to opt in, attempting to send Analytics hits and postbacks in queue.", new Object[0]);
                            AnalyticsWorker.sharedInstance().kick(false);
                        }
                    });
                    StaticMethods.getThirdPartyCallbacksExecutor().execute(new Runnable() {
                        public void run() {
                            StaticMethods.logDebugFormat("Data Callback - Privacy status set to opt in, attempting to send all requests in queue", new Object[0]);
                            ThirdPartyQueue.sharedInstance().kick(false);
                        }
                    });
                    StaticMethods.getPIIExecutor().execute(new Runnable() {
                        public void run() {
                            StaticMethods.logDebugFormat("Pii Callback - Privacy status set to opt in, attempting to send all requests in queue", new Object[0]);
                            PiiQueue.sharedInstance().kick(false);
                        }
                    });
                }
                if (mobilePrivacyStatus == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT) {
                    StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                        public void run() {
                            StaticMethods.logDebugFormat("Analytics - Privacy status set to opt out, purging database of Analytics requests and postbacks.", new Object[0]);
                            AnalyticsWorker.sharedInstance().clearTrackingQueue();
                        }
                    });
                    StaticMethods.getThirdPartyCallbacksExecutor().execute(new Runnable() {
                        public void run() {
                            StaticMethods.logDebugFormat("Data Callback - Privacy status set to opt out, attempting to clear queue of all requests", new Object[0]);
                            ThirdPartyQueue.sharedInstance().clearTrackingQueue();
                        }
                    });
                    StaticMethods.getPIIExecutor().execute(new Runnable() {
                        public void run() {
                            StaticMethods.logDebugFormat("PII - Privacy status set to opt out, attempting to clear queue of all requests.", new Object[0]);
                            PiiQueue.sharedInstance().clearTrackingQueue();
                        }
                    });
                    MobileIdentities.resetAllIdentifiers();
                }
                this._privacyStatus = mobilePrivacyStatus;
                WearableFunctionBridge.syncPrivacyStatusToWearable(mobilePrivacyStatus.getValue());
                try {
                    SharedPreferences.Editor sharedPreferencesEditor = StaticMethods.getSharedPreferencesEditor();
                    sharedPreferencesEditor.putInt("PrivacyStatus", mobilePrivacyStatus.getValue());
                    sharedPreferencesEditor.commit();
                } catch (StaticMethods.NullContextException e) {
                    StaticMethods.logErrorFormat("Config - Error persisting privacy status (%s).", e.getMessage());
                }
            } else {
                StaticMethods.logWarningFormat("Analytics - Cannot set privacy status to unknown when offline tracking is disabled", new Object[0]);
            }
        }
    }

    /* access modifiers changed from: protected */
    public MobilePrivacyStatus getPrivacyStatus() {
        return this._privacyStatus;
    }

    /* access modifiers changed from: protected */
    public List<List<Object>> getPointsOfInterest() {
        return this._pointsOfInterest;
    }

    /* access modifiers changed from: protected */
    public int getReferrerTimeout() {
        return this._referrerTimeout * 1000;
    }

    /* access modifiers changed from: protected */
    public int getAnalyticsResponseType() {
        return this._aamAnalyticsForwardingEnabled ? 10 : 0;
    }

    /* access modifiers changed from: protected */
    public String getClientCode() {
        return this._clientCode;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLocationTimeout() {
        return this._defaultLocationTimeout;
    }

    /* access modifiers changed from: protected */
    public long getEnvironmentID() {
        return this._environmentId;
    }

    /* access modifiers changed from: protected */
    public int getTargetSessionTimeout() {
        return this._targetSessionTimeout;
    }

    /* access modifiers changed from: protected */
    public String getAamServer() {
        return this._aamServer;
    }

    /* access modifiers changed from: protected */
    public boolean getAamAnalyticsForwardingEnabled() {
        return this._aamAnalyticsForwardingEnabled;
    }

    /* access modifiers changed from: protected */
    public int getAamTimeout() {
        return this._aamTimeout;
    }

    /* access modifiers changed from: protected */
    public String getAcquisitionAppId() {
        return this._acquisitionAppIdentifier;
    }

    /* access modifiers changed from: protected */
    public String getAcquisitionServer() {
        return this._acquisitionServer;
    }

    /* access modifiers changed from: protected */
    public void downloadRemoteConfigs() {
        StaticMethods.getMessagesExecutor().execute(new Runnable() {
            public void run() {
                if (MobileConfig.this._messagesURL == null || MobileConfig.this._messagesURL.length() <= 0) {
                    MobileConfig.this.loadMessageImages();
                } else {
                    RemoteDownload.remoteDownloadSync(MobileConfig.this._messagesURL, new RemoteDownload.RemoteDownloadBlock() {
                        public void call(boolean z, File file) {
                            MobileConfig.this.updateMessagesData(file);
                            MobileConfig.this.loadMessageImages();
                            MobileConfig.this.updateBlacklist();
                        }
                    });
                }
            }
        });
        StaticMethods.getThirdPartyCallbacksExecutor().execute(new Runnable() {
            public void run() {
                FutureTask futureTask = new FutureTask(new Callable<Void>() {
                    public Void call() throws Exception {
                        return null;
                    }
                });
                StaticMethods.getMessagesExecutor().execute(futureTask);
                try {
                    futureTask.get();
                } catch (Exception e) {
                    StaticMethods.logErrorFormat("Data Callback - Error waiting for callbacks being loaded (%s)", e.getMessage());
                }
            }
        });
        StaticMethods.getPIIExecutor().execute(new Runnable() {
            public void run() {
                FutureTask futureTask = new FutureTask(new Callable<Void>() {
                    public Void call() throws Exception {
                        return null;
                    }
                });
                StaticMethods.getMessagesExecutor().execute(futureTask);
                try {
                    futureTask.get();
                } catch (Exception e) {
                    StaticMethods.logErrorFormat("Pii Callback - Error waiting for callbacks being loaded (%s)", e.getMessage());
                }
            }
        });
        String str = this._pointsOfInterestURL;
        if (str != null && str.length() > 0) {
            RemoteDownload.remoteDownloadAsync(this._pointsOfInterestURL, new RemoteDownload.RemoteDownloadBlock() {
                public void call(boolean z, final File file) {
                    StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                        public void run() {
                            if (file != null) {
                                StaticMethods.logDebugFormat("Config - Using remote definition for points of interest", new Object[0]);
                                MobileConfig.this.updatePOIData(file);
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A[SYNTHETIC, Splitter:B:24:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0061 A[SYNTHETIC, Splitter:B:33:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0074 A[SYNTHETIC, Splitter:B:38:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateMessagesData(java.io.File r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Messages - Unable to close file stream (%s)"
            if (r7 != 0) goto L_0x0005
            return
        L_0x0005:
            r1 = 0
            r2 = 1
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ JSONException -> 0x0051, IOException -> 0x0030 }
            r4.<init>(r7)     // Catch:{ JSONException -> 0x0051, IOException -> 0x0030 }
            org.json.JSONObject r7 = r6.loadConfigFromStream(r4)     // Catch:{ JSONException -> 0x002b, IOException -> 0x0028, all -> 0x0025 }
            r6.loadMessagesDataFromRemote(r7)     // Catch:{ JSONException -> 0x002b, IOException -> 0x0028, all -> 0x0025 }
            r4.close()     // Catch:{ IOException -> 0x0018 }
            goto L_0x0071
        L_0x0018:
            r7 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r7 = r7.getLocalizedMessage()
            r2[r1] = r7
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
            goto L_0x0071
        L_0x0025:
            r7 = move-exception
            r3 = r4
            goto L_0x0072
        L_0x0028:
            r7 = move-exception
            r3 = r4
            goto L_0x0031
        L_0x002b:
            r7 = move-exception
            r3 = r4
            goto L_0x0052
        L_0x002e:
            r7 = move-exception
            goto L_0x0072
        L_0x0030:
            r7 = move-exception
        L_0x0031:
            java.lang.String r4 = "Messages - Unable to open messages config file, falling back to bundled messages (%s)"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x002e }
            java.lang.String r7 = r7.getLocalizedMessage()     // Catch:{ all -> 0x002e }
            r5[r1] = r7     // Catch:{ all -> 0x002e }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r5)     // Catch:{ all -> 0x002e }
            if (r3 == 0) goto L_0x0071
            r3.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0071
        L_0x0044:
            r7 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r7 = r7.getLocalizedMessage()
            r2[r1] = r7
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
            goto L_0x0071
        L_0x0051:
            r7 = move-exception
        L_0x0052:
            java.lang.String r4 = "Messages - Unable to read messages remote config file, falling back to bundled messages (%s)"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x002e }
            java.lang.String r7 = r7.getLocalizedMessage()     // Catch:{ all -> 0x002e }
            r5[r1] = r7     // Catch:{ all -> 0x002e }
            com.adobe.mobile.StaticMethods.logErrorFormat(r4, r5)     // Catch:{ all -> 0x002e }
            if (r3 == 0) goto L_0x0071
            r3.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x0071
        L_0x0065:
            r7 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r7 = r7.getLocalizedMessage()
            r2[r1] = r7
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
        L_0x0071:
            return
        L_0x0072:
            if (r3 == 0) goto L_0x0084
            r3.close()     // Catch:{ IOException -> 0x0078 }
            goto L_0x0084
        L_0x0078:
            r3 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r3.getLocalizedMessage()
            r2[r1] = r3
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
        L_0x0084:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MobileConfig.updateMessagesData(java.io.File):void");
    }

    /* access modifiers changed from: protected */
    public void enableTargetPreviewMessage() {
        if (this._inAppMessages == null) {
            this._inAppMessages = new ArrayList<>();
        }
        MessageTargetExperienceUIFullScreen messageTargetExperienceUIFullscreen = TargetPreviewManager.getInstance().getMessageTargetExperienceUIFullscreen();
        if (Messages.getFullScreenMessageById(messageTargetExperienceUIFullscreen.messageId) == null) {
            this._inAppMessages.add(messageTargetExperienceUIFullscreen);
        }
    }

    /* access modifiers changed from: protected */
    public void disableTargetPreviewMessage() {
        if (this._inAppMessages != null) {
            MessageTargetExperienceUIFullScreen messageTargetExperienceUIFullscreen = TargetPreviewManager.getInstance().getMessageTargetExperienceUIFullscreen();
            Iterator<Message> it = this._inAppMessages.iterator();
            while (it.hasNext()) {
                if (it.next().messageId.equalsIgnoreCase(messageTargetExperienceUIFullscreen.messageId)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getPointsOfInterestURL() {
        return this._pointsOfInterestURL;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004e A[SYNTHETIC, Splitter:B:26:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x006f A[SYNTHETIC, Splitter:B:35:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0082 A[SYNTHETIC, Splitter:B:40:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePOIData(java.io.File r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Config - Unable to close file stream (%s)"
            if (r7 != 0) goto L_0x0005
            return
        L_0x0005:
            r1 = 0
            r2 = 1
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ JSONException -> 0x005f, IOException -> 0x003e }
            r4.<init>(r7)     // Catch:{ JSONException -> 0x005f, IOException -> 0x003e }
            org.json.JSONObject r7 = r6.loadConfigFromStream(r4)     // Catch:{ JSONException -> 0x0039, IOException -> 0x0036, all -> 0x0033 }
            if (r7 == 0) goto L_0x0022
            java.lang.String r3 = "analytics"
            org.json.JSONObject r7 = r7.getJSONObject(r3)     // Catch:{ JSONException -> 0x0039, IOException -> 0x0036, all -> 0x0033 }
            java.lang.String r3 = "poi"
            org.json.JSONArray r7 = r7.getJSONArray(r3)     // Catch:{ JSONException -> 0x0039, IOException -> 0x0036, all -> 0x0033 }
            r6.loadPoiFromJsonArray(r7)     // Catch:{ JSONException -> 0x0039, IOException -> 0x0036, all -> 0x0033 }
        L_0x0022:
            r4.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x007f
        L_0x0026:
            r7 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r7 = r7.getLocalizedMessage()
            r2[r1] = r7
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
            goto L_0x007f
        L_0x0033:
            r7 = move-exception
            r3 = r4
            goto L_0x0080
        L_0x0036:
            r7 = move-exception
            r3 = r4
            goto L_0x003f
        L_0x0039:
            r7 = move-exception
            r3 = r4
            goto L_0x0060
        L_0x003c:
            r7 = move-exception
            goto L_0x0080
        L_0x003e:
            r7 = move-exception
        L_0x003f:
            java.lang.String r4 = "Config - Unable to open points of interest config file, falling back to bundled poi (%s)"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x003c }
            java.lang.String r7 = r7.getLocalizedMessage()     // Catch:{ all -> 0x003c }
            r5[r1] = r7     // Catch:{ all -> 0x003c }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r5)     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x007f
            r3.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x007f
        L_0x0052:
            r7 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r7 = r7.getLocalizedMessage()
            r2[r1] = r7
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
            goto L_0x007f
        L_0x005f:
            r7 = move-exception
        L_0x0060:
            java.lang.String r4 = "Config - Unable to read points of interest remote config file, falling back to bundled poi (%s)"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x003c }
            java.lang.String r7 = r7.getLocalizedMessage()     // Catch:{ all -> 0x003c }
            r5[r1] = r7     // Catch:{ all -> 0x003c }
            com.adobe.mobile.StaticMethods.logErrorFormat(r4, r5)     // Catch:{ all -> 0x003c }
            if (r3 == 0) goto L_0x007f
            r3.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x007f
        L_0x0073:
            r7 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r7 = r7.getLocalizedMessage()
            r2[r1] = r7
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
        L_0x007f:
            return
        L_0x0080:
            if (r3 == 0) goto L_0x0092
            r3.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x0092
        L_0x0086:
            r3 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = r3.getLocalizedMessage()
            r2[r1] = r3
            com.adobe.mobile.StaticMethods.logErrorFormat(r0, r2)
        L_0x0092:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MobileConfig.updatePOIData(java.io.File):void");
    }

    /* access modifiers changed from: protected */
    public ArrayList<Message> getInAppMessages() {
        return this._inAppMessages;
    }

    /* access modifiers changed from: protected */
    public String getInAppMessageURL() {
        return this._messagesURL;
    }

    /* access modifiers changed from: protected */
    public ArrayList<Message> getCallbackTemplates() {
        return this._callbackTemplates;
    }

    /* access modifiers changed from: protected */
    public ArrayList<Message> getPiiRequests() {
        return this._piiRequests;
    }

    /* access modifiers changed from: protected */
    public String getMarketingCloudOrganizationId() {
        return this._marketingCloudOrganizationId;
    }

    /* access modifiers changed from: protected */
    public String getMarketingCloudCustomServer() {
        return this._marketingCloudServer;
    }

    /* access modifiers changed from: protected */
    public boolean getMarketingCloudCoopUnsafe() {
        return this._marketingCloudCoopUnsafe;
    }

    /* access modifiers changed from: protected */
    public boolean getVisitorIdServiceEnabled() {
        String str = this._marketingCloudOrganizationId;
        return str != null && str.length() > 0;
    }

    private JSONObject loadBundleConfig() {
        InputStream inputStream;
        synchronized (_userDefinedInputStreamMutex) {
            inputStream = _userDefinedInputStream;
        }
        JSONObject jSONObject = null;
        if (inputStream != null) {
            try {
                StaticMethods.logDebugFormat("Config - Attempting to load config file from override stream", new Object[0]);
                jSONObject = loadConfigFromStream(inputStream);
            } catch (IOException e) {
                StaticMethods.logDebugFormat("Config - Error loading user defined config (%s)", e.getMessage());
            } catch (JSONException e2) {
                StaticMethods.logDebugFormat("Config - Error parsing user defined config (%s)", e2.getMessage());
            }
        }
        if (jSONObject != null) {
            return jSONObject;
        }
        if (inputStream != null) {
            StaticMethods.logDebugFormat("Config - Failed attempting to load custom config, will fall back to standard config location.", new Object[0]);
        }
        StaticMethods.logDebugFormat("Config - Attempting to load config file from default location", new Object[0]);
        JSONObject loadConfigFile = loadConfigFile(CONFIG_FILE_NAME);
        if (loadConfigFile != null) {
            return loadConfigFile;
        }
        StaticMethods.logDebugFormat("Config - Could not find config file at expected location.  Attempting to load from www folder", new Object[0]);
        return loadConfigFile("www" + File.separator + CONFIG_FILE_NAME);
    }

    private JSONObject loadConfigFile(String str) {
        AssetManager assets;
        try {
            Resources resources = StaticMethods.getSharedContext().getResources();
            if (resources == null || (assets = resources.getAssets()) == null) {
                return null;
            }
            return loadConfigFromStream(assets.open(str));
        } catch (IOException e) {
            StaticMethods.logErrorFormat("Config - Exception loading config file (%s)", e.getMessage());
            return null;
        } catch (JSONException e2) {
            StaticMethods.logErrorFormat("Config - Exception parsing config file (%s)", e2.getMessage());
            return null;
        } catch (StaticMethods.NullContextException e3) {
            StaticMethods.logErrorFormat("Config - Null context when attempting to read config file (%s)", e3.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void loadCachedRemotes() {
        String str = this._messagesURL;
        if (str != null && str.length() > 0) {
            updateMessagesData(RemoteDownload.getFileForCachedURL(this._messagesURL));
        }
        String str2 = this._pointsOfInterestURL;
        if (str2 != null && str2.length() > 0) {
            updatePOIData(RemoteDownload.getFileForCachedURL(this._pointsOfInterestURL));
        }
    }

    private JSONObject loadConfigFromStream(InputStream inputStream) throws JSONException, IOException {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            JSONObject jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            try {
                inputStream.close();
            } catch (IOException e) {
                StaticMethods.logErrorFormat("Config - Unable to close stream (%s)", e.getMessage());
            }
            return jSONObject;
        } catch (IOException e2) {
            StaticMethods.logErrorFormat("Config - Exception when reading config (%s)", e2.getMessage());
            try {
                inputStream.close();
            } catch (IOException e3) {
                StaticMethods.logErrorFormat("Config - Unable to close stream (%s)", e3.getMessage());
            }
        } catch (NullPointerException e4) {
            StaticMethods.logErrorFormat("Config - Stream closed when attempting to load config (%s)", e4.getMessage());
            try {
                inputStream.close();
            } catch (IOException e5) {
                StaticMethods.logErrorFormat("Config - Unable to close stream (%s)", e5.getMessage());
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e6) {
                StaticMethods.logErrorFormat("Config - Unable to close stream (%s)", e6.getMessage());
            }
            throw th;
        }
        return new JSONObject();
    }

    public static void setUserDefinedConfigPath(InputStream inputStream) {
        synchronized (_userDefinedInputStreamMutex) {
            if (_userDefinedInputStream == null) {
                _userDefinedInputStream = inputStream;
            }
        }
    }

    private MobilePrivacyStatus privacyStatusFromString(String str) {
        if (str != null) {
            if (str.equalsIgnoreCase(CONFIG_PRIVACY_OPTED_IN)) {
                return MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN;
            }
            if (str.equalsIgnoreCase(CONFIG_PRIVACY_OPTED_OUT)) {
                return MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT;
            }
            if (str.equalsIgnoreCase(CONFIG_PRIVACY_UNKNOWN)) {
                return MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_UNKNOWN;
            }
        }
        return DEFAULT_PRIVACY_STATUS;
    }

    private void loadPoiFromJsonArray(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                this._pointsOfInterest = new ArrayList();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                    ArrayList arrayList = new ArrayList(4);
                    arrayList.add(jSONArray2.getString(0));
                    arrayList.add(Double.valueOf(jSONArray2.getDouble(1)));
                    arrayList.add(Double.valueOf(jSONArray2.getDouble(2)));
                    arrayList.add(Double.valueOf(jSONArray2.getDouble(3)));
                    this._pointsOfInterest.add(arrayList);
                }
            } catch (JSONException e) {
                StaticMethods.logErrorFormat("Messages - Unable to parse remote points of interest JSON (%s)", e.getMessage());
            }
        }
    }

    private void loadMessagesDataFromRemote(JSONObject jSONObject) {
        JSONArray jSONArray;
        if (jSONObject == null) {
            StaticMethods.logDebugFormat("Messages - Remote messages config was null, falling back to bundled messages", new Object[0]);
            RemoteDownload.deleteFilesInDirectory("messageImages");
            return;
        }
        try {
            jSONArray = jSONObject.getJSONArray("messages");
        } catch (JSONException unused) {
            StaticMethods.logDebugFormat("Messages - Remote messages not configured, falling back to bundled messages", new Object[0]);
            jSONArray = null;
        }
        StaticMethods.logDebugFormat("Messages - Using remote definition for messages", new Object[0]);
        if (jSONArray == null || jSONArray.length() <= 0) {
            RemoteDownload.deleteFilesInDirectory("messageImages");
            this._inAppMessages = null;
            this._callbackTemplates = null;
            this._piiRequests = null;
            return;
        }
        loadMessagesFromJsonArray(jSONArray);
    }

    private void loadMessagesFromJsonArray(JSONArray jSONArray) {
        try {
            ArrayList<Message> arrayList = new ArrayList<>();
            ArrayList<Message> arrayList2 = new ArrayList<>();
            ArrayList<Message> arrayList3 = new ArrayList<>();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Message messageWithJsonObject = Message.messageWithJsonObject(jSONArray.getJSONObject(i));
                if (messageWithJsonObject != null) {
                    StaticMethods.logDebugFormat("Messages - loaded message - %s", messageWithJsonObject.description());
                    if (messageWithJsonObject.getClass() == MessageTemplatePii.class) {
                        arrayList3.add(messageWithJsonObject);
                    } else {
                        if (messageWithJsonObject.getClass() != MessageTemplateCallback.class) {
                            if (messageWithJsonObject.getClass() != MessageOpenURL.class) {
                                arrayList.add(messageWithJsonObject);
                            }
                        }
                        arrayList2.add(messageWithJsonObject);
                    }
                }
            }
            this._inAppMessages = arrayList;
            this._callbackTemplates = arrayList2;
            this._piiRequests = arrayList3;
        } catch (JSONException e) {
            StaticMethods.logErrorFormat("Messages - Unable to parse messages JSON (%s)", e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void updateBlacklist() {
        ArrayList<Message> arrayList = this._inAppMessages;
        if (arrayList != null) {
            Iterator<Message> it = arrayList.iterator();
            while (it.hasNext()) {
                Message next = it.next();
                HashMap<String, Integer> loadBlacklist = next.loadBlacklist();
                if (next.isBlacklisted() && next.showRule.getValue() != loadBlacklist.get(next.messageId).intValue()) {
                    next.removeFromBlacklist();
                }
            }
        }
        ArrayList<Message> arrayList2 = this._callbackTemplates;
        if (arrayList2 != null) {
            Iterator<Message> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                Message next2 = it2.next();
                HashMap<String, Integer> loadBlacklist2 = next2.loadBlacklist();
                if (next2.isBlacklisted() && next2.showRule.getValue() != loadBlacklist2.get(next2.messageId).intValue()) {
                    next2.removeFromBlacklist();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void loadMessageImages() {
        StaticMethods.getMessageImageCachingExecutor().execute(new Runnable() {
            public void run() {
                if (MobileConfig.this._inAppMessages == null || MobileConfig.this._inAppMessages.size() <= 0) {
                    RemoteDownload.deleteFilesInDirectory("messageImages");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = MobileConfig.this._inAppMessages.iterator();
                while (it.hasNext()) {
                    Message message = (Message) it.next();
                    if (message.assets != null && message.assets.size() > 0) {
                        Iterator<ArrayList<String>> it2 = message.assets.iterator();
                        while (it2.hasNext()) {
                            ArrayList next = it2.next();
                            if (next.size() > 0) {
                                Iterator it3 = next.iterator();
                                while (it3.hasNext()) {
                                    String str = (String) it3.next();
                                    arrayList.add(str);
                                    RemoteDownload.remoteDownloadSync(str, 10000, 10000, (RemoteDownload.RemoteDownloadBlock) null, "messageImages");
                                }
                            }
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    RemoteDownload.deleteFilesForDirectoryNotInList("messageImages", arrayList);
                } else {
                    RemoteDownload.deleteFilesInDirectory("messageImages");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean networkConnectivity() {
        return StaticMethods.isWearableApp() || this._networkConnectivity;
    }

    /* access modifiers changed from: protected */
    public void startNotifier() {
        Context context;
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            context = StaticMethods.getSharedContext().getApplicationContext();
        } catch (StaticMethods.NullContextException e) {
            StaticMethods.logErrorFormat("Analytics - Error registering network receiver (%s)", e.getMessage());
            context = null;
        }
        if (context != null) {
            context.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    MobileConfig mobileConfig = MobileConfig.this;
                    boolean unused = mobileConfig._networkConnectivity = mobileConfig.getNetworkConnectivity(context);
                    if (MobileConfig.this._networkConnectivity) {
                        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                            public void run() {
                                StaticMethods.logDebugFormat("Analytics - Network status changed (reachable)", new Object[0]);
                                AnalyticsWorker.sharedInstance().kick(false);
                            }
                        });
                    } else {
                        StaticMethods.logDebugFormat("Analytics - Network status changed (unreachable)", new Object[0]);
                    }
                }
            }, intentFilter);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r4.isConnected() != false) goto L_0x006c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getNetworkConnectivity(android.content.Context r4) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L_0x006c
            r1 = 0
            java.lang.String r2 = "connectivity"
            java.lang.Object r4 = r4.getSystemService(r2)     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            if (r4 == 0) goto L_0x0034
            android.net.NetworkInfo r4 = r4.getActiveNetworkInfo()     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            if (r4 == 0) goto L_0x0023
            boolean r2 = r4.isAvailable()     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            if (r2 == 0) goto L_0x0021
            boolean r4 = r4.isConnected()     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            if (r4 == 0) goto L_0x0021
            goto L_0x006c
        L_0x0021:
            r0 = 0
            goto L_0x006c
        L_0x0023:
            java.lang.String r4 = "Analytics - Unable to determine connectivity status due to there being no default network currently active"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ NullPointerException -> 0x0031, SecurityException -> 0x002e, Exception -> 0x002b }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r2)     // Catch:{ NullPointerException -> 0x0031, SecurityException -> 0x002e, Exception -> 0x002b }
            goto L_0x0021
        L_0x002b:
            r4 = move-exception
            r2 = 0
            goto L_0x003e
        L_0x002e:
            r4 = move-exception
            r2 = 0
            goto L_0x004e
        L_0x0031:
            r4 = move-exception
            r2 = 0
            goto L_0x005e
        L_0x0034:
            java.lang.String r4 = "Analytics - Unable to determine connectivity status due to the system service requested being unrecognized"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r2)     // Catch:{ NullPointerException -> 0x005c, SecurityException -> 0x004c, Exception -> 0x003c }
            goto L_0x006c
        L_0x003c:
            r4 = move-exception
            r2 = 1
        L_0x003e:
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r4 = r4.getLocalizedMessage()
            r0[r1] = r4
            java.lang.String r4 = "Analytics - Unable to access connectivity status due to an unexpected error (%s)"
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r0)
            goto L_0x006d
        L_0x004c:
            r4 = move-exception
            r2 = 1
        L_0x004e:
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r4 = r4.getLocalizedMessage()
            r0[r1] = r4
            java.lang.String r4 = "Analytics - Unable to access connectivity status due to a security error (%s)"
            com.adobe.mobile.StaticMethods.logErrorFormat(r4, r0)
            goto L_0x006d
        L_0x005c:
            r4 = move-exception
            r2 = 1
        L_0x005e:
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r4 = r4.getLocalizedMessage()
            r0[r1] = r4
            java.lang.String r4 = "Analytics - Unable to determine connectivity status due to an unexpected error (%s)"
            com.adobe.mobile.StaticMethods.logWarningFormat(r4, r0)
            goto L_0x006d
        L_0x006c:
            r2 = r0
        L_0x006d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MobileConfig.getNetworkConnectivity(android.content.Context):boolean");
    }
}
