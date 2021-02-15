package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import io.branch.referral.Defines;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PrefHelper {
    private static boolean BNC_Dev_Debug = false;
    private static String Branch_Key = null;
    private static final int INTERVAL_RETRY = 1000;
    private static final String KEY_ACTIONS = "bnc_actions";
    private static final String KEY_APP_LINK = "bnc_app_link";
    private static final String KEY_APP_VERSION = "bnc_app_version";
    private static final String KEY_BRANCH_ANALYTICAL_DATA = "bnc_branch_analytical_data";
    private static final String KEY_BRANCH_KEY = "bnc_branch_key";
    private static final String KEY_BRANCH_VIEW_NUM_OF_USE = "bnc_branch_view_use";
    private static final String KEY_BUCKETS = "bnc_buckets";
    private static final String KEY_CREDIT_BASE = "bnc_credit_base_";
    private static final String KEY_DEVICE_FINGERPRINT_ID = "bnc_device_fingerprint_id";
    private static final String KEY_EXTERNAL_INTENT_EXTRA = "bnc_external_intent_extra";
    private static final String KEY_EXTERNAL_INTENT_URI = "bnc_external_intent_uri";
    private static final String KEY_GOOGLE_PLAY_INSTALL_REFERRER_EXTRA = "bnc_google_play_install_referrer_extras";
    private static final String KEY_GOOGLE_SEARCH_INSTALL_IDENTIFIER = "bnc_google_search_install_identifier";
    private static final String KEY_IDENTITY = "bnc_identity";
    private static final String KEY_IDENTITY_ID = "bnc_identity_id";
    static final String KEY_INSTALL_BEGIN_TS = "bnc_install_begin_ts";
    private static final String KEY_INSTALL_PARAMS = "bnc_install_params";
    private static final String KEY_INSTALL_REFERRER = "bnc_install_referrer";
    private static final String KEY_IS_FULL_APP_CONVERSION = "bnc_is_full_app_conversion";
    private static final String KEY_IS_REFERRABLE = "bnc_is_referrable";
    private static final String KEY_IS_TRIGGERED_BY_FB_APP_LINK = "bnc_triggered_by_fb_app_link";
    static final String KEY_LAST_KNOWN_UPDATE_TIME = "bnc_last_known_update_time";
    private static final String KEY_LAST_READ_SYSTEM = "bnc_system_read_date";
    private static final String KEY_LAST_STRONG_MATCH_TIME = "bnc_branch_strong_match_time";
    private static final String KEY_LIMIT_FACEBOOK_TRACKING = "bnc_limit_facebook_tracking";
    private static final String KEY_LINK_CLICK_ID = "bnc_link_click_id";
    private static final String KEY_LINK_CLICK_IDENTIFIER = "bnc_link_click_identifier";
    static final String KEY_ORIGINAL_INSTALL_TIME = "bnc_original_install_time";
    static final String KEY_PREVIOUS_UPDATE_TIME = "bnc_previous_update_time";
    private static final String KEY_PUSH_IDENTIFIER = "bnc_push_identifier";
    static final String KEY_REFERRER_CLICK_TS = "bnc_referrer_click_ts";
    private static final String KEY_RETRY_COUNT = "bnc_retry_count";
    private static final String KEY_RETRY_INTERVAL = "bnc_retry_interval";
    private static final String KEY_SESSION_ID = "bnc_session_id";
    private static final String KEY_SESSION_PARAMS = "bnc_session_params";
    private static final String KEY_TIMEOUT = "bnc_timeout";
    private static final String KEY_TOTAL_BASE = "bnc_total_base_";
    static final String KEY_TRACKING_STATE = "bnc_tracking_state";
    private static final String KEY_UNIQUE_BASE = "bnc_balance_base_";
    private static final String KEY_USER_URL = "bnc_user_url";
    private static final int MAX_RETRIES = 3;
    public static final String NO_STRING_VALUE = "bnc_no_value";
    private static final String SHARED_PREF_FILE = "branch_referral_shared_pref";
    private static final int TIMEOUT = 5500;
    private static PrefHelper prefHelper_;
    private static JSONObject savedAnalyticsData_;
    private SharedPreferences appSharedPrefs_;
    private Context context_;
    private final JSONObject installMetadata = new JSONObject();
    private SharedPreferences.Editor prefsEditor_;
    private final JSONObject requestMetadata = new JSONObject();

    public String getAPIBaseUrl() {
        return "https://api.branch.io/";
    }

    private PrefHelper(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE, 0);
        this.appSharedPrefs_ = sharedPreferences;
        this.prefsEditor_ = sharedPreferences.edit();
        this.context_ = context;
    }

    public static PrefHelper getInstance(Context context) {
        if (prefHelper_ == null) {
            prefHelper_ = new PrefHelper(context);
        }
        return prefHelper_;
    }

    public void setTimeout(int i) {
        setInteger(KEY_TIMEOUT, i);
    }

    public int getTimeout() {
        return getInteger(KEY_TIMEOUT, TIMEOUT);
    }

    public void setRetryCount(int i) {
        setInteger(KEY_RETRY_COUNT, i);
    }

    public int getRetryCount() {
        return getInteger(KEY_RETRY_COUNT, 3);
    }

    public void setRetryInterval(int i) {
        setInteger(KEY_RETRY_INTERVAL, i);
    }

    public int getRetryInterval() {
        return getInteger(KEY_RETRY_INTERVAL, 1000);
    }

    public void setAppVersion(String str) {
        setString(KEY_APP_VERSION, str);
    }

    public String getAppVersion() {
        return getString(KEY_APP_VERSION);
    }

    public boolean setBranchKey(String str) {
        Branch_Key = str;
        String string = getString(KEY_BRANCH_KEY);
        if (str != null && string != null && string.equals(str)) {
            return false;
        }
        clearPrefOnBranchKeyChange();
        setString(KEY_BRANCH_KEY, str);
        return true;
    }

    public String getBranchKey() {
        if (Branch_Key == null) {
            Branch_Key = getString(KEY_BRANCH_KEY);
        }
        return Branch_Key;
    }

    public String readBranchKey(boolean z) {
        String str = z ? "io.branch.sdk.BranchKey" : "io.branch.sdk.BranchKey.test";
        if (!z) {
            setExternDebug();
        }
        String str2 = null;
        try {
            ApplicationInfo applicationInfo = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128);
            if (applicationInfo.metaData != null && (str2 = applicationInfo.metaData.getString(str)) == null && !z) {
                str2 = applicationInfo.metaData.getString("io.branch.sdk.BranchKey");
            }
        } catch (Exception unused) {
        }
        if (TextUtils.isEmpty(str2)) {
            try {
                Resources resources = this.context_.getResources();
                str2 = resources.getString(resources.getIdentifier(str, "string", this.context_.getPackageName()));
            } catch (Exception unused2) {
            }
        }
        return str2 == null ? "bnc_no_value" : str2;
    }

    public void setDeviceFingerPrintID(String str) {
        setString(KEY_DEVICE_FINGERPRINT_ID, str);
    }

    public String getDeviceFingerPrintID() {
        return getString(KEY_DEVICE_FINGERPRINT_ID);
    }

    public void setSessionID(String str) {
        setString(KEY_SESSION_ID, str);
    }

    public String getSessionID() {
        return getString(KEY_SESSION_ID);
    }

    public void setIdentityID(String str) {
        setString(KEY_IDENTITY_ID, str);
    }

    public String getIdentityID() {
        return getString(KEY_IDENTITY_ID);
    }

    public void setIdentity(String str) {
        setString(KEY_IDENTITY, str);
    }

    public String getIdentity() {
        return getString(KEY_IDENTITY);
    }

    public void setLinkClickID(String str) {
        setString(KEY_LINK_CLICK_ID, str);
    }

    public String getLinkClickID() {
        return getString(KEY_LINK_CLICK_ID);
    }

    public void setIsAppLinkTriggeredInit(Boolean bool) {
        setBool(KEY_IS_TRIGGERED_BY_FB_APP_LINK, bool);
    }

    public boolean getIsAppLinkTriggeredInit() {
        return getBool(KEY_IS_TRIGGERED_BY_FB_APP_LINK);
    }

    public void setExternalIntentUri(String str) {
        setString(KEY_EXTERNAL_INTENT_URI, str);
    }

    public String getExternalIntentUri() {
        return getString(KEY_EXTERNAL_INTENT_URI);
    }

    public void setExternalIntentExtra(String str) {
        setString(KEY_EXTERNAL_INTENT_EXTRA, str);
    }

    public String getExternalIntentExtra() {
        return getString(KEY_EXTERNAL_INTENT_EXTRA);
    }

    public void setLinkClickIdentifier(String str) {
        setString(KEY_LINK_CLICK_IDENTIFIER, str);
    }

    public String getLinkClickIdentifier() {
        return getString(KEY_LINK_CLICK_IDENTIFIER);
    }

    public void setGoogleSearchInstallIdentifier(String str) {
        setString(KEY_GOOGLE_SEARCH_INSTALL_IDENTIFIER, str);
    }

    public String getGoogleSearchInstallIdentifier() {
        return getString(KEY_GOOGLE_SEARCH_INSTALL_IDENTIFIER);
    }

    public void setGooglePlayReferrer(String str) {
        setString(KEY_GOOGLE_PLAY_INSTALL_REFERRER_EXTRA, str);
    }

    public String getGooglePlayReferrer() {
        return getString(KEY_GOOGLE_PLAY_INSTALL_REFERRER_EXTRA);
    }

    public void setAppLink(String str) {
        setString(KEY_APP_LINK, str);
    }

    public String getAppLink() {
        return getString(KEY_APP_LINK);
    }

    public void setIsFullAppConversion(boolean z) {
        setBool(KEY_IS_FULL_APP_CONVERSION, Boolean.valueOf(z));
    }

    public boolean isFullAppConversion() {
        return getBool(KEY_IS_FULL_APP_CONVERSION);
    }

    public void setPushIdentifier(String str) {
        setString(KEY_PUSH_IDENTIFIER, str);
    }

    public String getPushIdentifier() {
        return getString(KEY_PUSH_IDENTIFIER);
    }

    public String getSessionParams() {
        return getString(KEY_SESSION_PARAMS);
    }

    public void setSessionParams(String str) {
        setString(KEY_SESSION_PARAMS, str);
    }

    public String getInstallParams() {
        return getString(KEY_INSTALL_PARAMS);
    }

    public void setInstallParams(String str) {
        setString(KEY_INSTALL_PARAMS, str);
    }

    public void setInstallReferrerParams(String str) {
        setString(KEY_INSTALL_REFERRER, str);
    }

    public String getInstallReferrerParams() {
        return getString(KEY_INSTALL_REFERRER);
    }

    public void setUserURL(String str) {
        setString(KEY_USER_URL, str);
    }

    public String getUserURL() {
        return getString(KEY_USER_URL);
    }

    public int getIsReferrable() {
        return getInteger(KEY_IS_REFERRABLE);
    }

    public void setIsReferrable() {
        setInteger(KEY_IS_REFERRABLE, 1);
    }

    public void clearIsReferrable() {
        setInteger(KEY_IS_REFERRABLE, 0);
    }

    public void clearSystemReadStatus() {
        setLong(KEY_LAST_READ_SYSTEM, Calendar.getInstance().getTimeInMillis() / 1000);
    }

    /* access modifiers changed from: package-private */
    public void setLimitFacebookTracking(boolean z) {
        setBool(KEY_LIMIT_FACEBOOK_TRACKING, Boolean.valueOf(z));
    }

    /* access modifiers changed from: package-private */
    public boolean isAppTrackingLimited() {
        return getBool(KEY_LIMIT_FACEBOOK_TRACKING);
    }

    public void clearUserValues() {
        Iterator<String> it = getBuckets().iterator();
        while (it.hasNext()) {
            setCreditCount(it.next(), 0);
        }
        setBuckets(new ArrayList());
        Iterator<String> it2 = getActions().iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            setActionTotalCount(next, 0);
            setActionUniqueCount(next, 0);
        }
        setActions(new ArrayList());
    }

    private ArrayList<String> getBuckets() {
        String string = getString(KEY_BUCKETS);
        if (string.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return deserializeString(string);
    }

    private void setBuckets(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            setString(KEY_BUCKETS, "bnc_no_value");
        } else {
            setString(KEY_BUCKETS, serializeArrayList(arrayList));
        }
    }

    public void setCreditCount(int i) {
        setCreditCount(Defines.Jsonkey.DefaultBucket.getKey(), i);
    }

    public void setCreditCount(String str, int i) {
        ArrayList<String> buckets = getBuckets();
        if (!buckets.contains(str)) {
            buckets.add(str);
            setBuckets(buckets);
        }
        setInteger(KEY_CREDIT_BASE + str, i);
    }

    public int getCreditCount() {
        return getCreditCount(Defines.Jsonkey.DefaultBucket.getKey());
    }

    public int getCreditCount(String str) {
        return getInteger(KEY_CREDIT_BASE + str);
    }

    private ArrayList<String> getActions() {
        String string = getString(KEY_ACTIONS);
        if (string.equals("bnc_no_value")) {
            return new ArrayList<>();
        }
        return deserializeString(string);
    }

    private void setActions(ArrayList<String> arrayList) {
        if (arrayList.size() == 0) {
            setString(KEY_ACTIONS, "bnc_no_value");
        } else {
            setString(KEY_ACTIONS, serializeArrayList(arrayList));
        }
    }

    public void setActionTotalCount(String str, int i) {
        ArrayList<String> actions = getActions();
        if (!actions.contains(str)) {
            actions.add(str);
            setActions(actions);
        }
        setInteger(KEY_TOTAL_BASE + str, i);
    }

    public void setActionUniqueCount(String str, int i) {
        setInteger(KEY_UNIQUE_BASE + str, i);
    }

    public int getActionTotalCount(String str) {
        return getInteger(KEY_TOTAL_BASE + str);
    }

    public int getActionUniqueCount(String str) {
        return getInteger(KEY_UNIQUE_BASE + str);
    }

    private String serializeArrayList(ArrayList<String> arrayList) {
        Iterator<String> it = arrayList.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next() + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    private ArrayList<String> deserializeString(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, str.split(","));
        return arrayList;
    }

    public int getInteger(String str) {
        return getInteger(str, 0);
    }

    public int getInteger(String str, int i) {
        return prefHelper_.appSharedPrefs_.getInt(str, i);
    }

    public long getLong(String str) {
        return prefHelper_.appSharedPrefs_.getLong(str, 0);
    }

    public float getFloat(String str) {
        return prefHelper_.appSharedPrefs_.getFloat(str, 0.0f);
    }

    public String getString(String str) {
        return prefHelper_.appSharedPrefs_.getString(str, "bnc_no_value");
    }

    public boolean getBool(String str) {
        return prefHelper_.appSharedPrefs_.getBoolean(str, false);
    }

    public void setInteger(String str, int i) {
        prefHelper_.prefsEditor_.putInt(str, i);
        prefHelper_.prefsEditor_.apply();
    }

    public void setLong(String str, long j) {
        prefHelper_.prefsEditor_.putLong(str, j);
        prefHelper_.prefsEditor_.apply();
    }

    public void setFloat(String str, float f) {
        prefHelper_.prefsEditor_.putFloat(str, f);
        prefHelper_.prefsEditor_.apply();
    }

    public void setString(String str, String str2) {
        prefHelper_.prefsEditor_.putString(str, str2);
        prefHelper_.prefsEditor_.apply();
    }

    public void setBool(String str, Boolean bool) {
        prefHelper_.prefsEditor_.putBoolean(str, bool.booleanValue());
        prefHelper_.prefsEditor_.apply();
    }

    public void updateBranchViewUsageCount(String str) {
        setInteger("bnc_branch_view_use_" + str, getBranchViewUsageCount(str) + 1);
    }

    public int getBranchViewUsageCount(String str) {
        return getInteger("bnc_branch_view_use_" + str, 0);
    }

    public JSONObject getBranchAnalyticsData() {
        JSONObject jSONObject = savedAnalyticsData_;
        if (jSONObject != null) {
            return jSONObject;
        }
        String string = getString(KEY_BRANCH_ANALYTICAL_DATA);
        JSONObject jSONObject2 = new JSONObject();
        if (!TextUtils.isEmpty(string) && !string.equals("bnc_no_value")) {
            try {
                return new JSONObject(string);
            } catch (JSONException unused) {
            }
        }
        return jSONObject2;
    }

    public void clearBranchAnalyticsData() {
        savedAnalyticsData_ = null;
        setString(KEY_BRANCH_ANALYTICAL_DATA, "");
    }

    public void saveBranchAnalyticsData(JSONObject jSONObject) {
        JSONArray jSONArray;
        String sessionID = getSessionID();
        if (!sessionID.equals("bnc_no_value")) {
            if (savedAnalyticsData_ == null) {
                savedAnalyticsData_ = getBranchAnalyticsData();
            }
            try {
                if (savedAnalyticsData_.has(sessionID)) {
                    jSONArray = savedAnalyticsData_.getJSONArray(sessionID);
                } else {
                    JSONArray jSONArray2 = new JSONArray();
                    savedAnalyticsData_.put(sessionID, jSONArray2);
                    jSONArray = jSONArray2;
                }
                jSONArray.put(jSONObject);
                setString(KEY_BRANCH_ANALYTICAL_DATA, savedAnalyticsData_.toString());
            } catch (JSONException unused) {
            }
        }
    }

    public void saveLastStrongMatchTime(long j) {
        setLong(KEY_LAST_STRONG_MATCH_TIME, j);
    }

    public long getLastStrongMatchTime() {
        return getLong(KEY_LAST_STRONG_MATCH_TIME);
    }

    private void clearPrefOnBranchKeyChange() {
        String linkClickID = getLinkClickID();
        String linkClickIdentifier = getLinkClickIdentifier();
        String appLink = getAppLink();
        String pushIdentifier = getPushIdentifier();
        this.prefsEditor_.clear();
        setLinkClickID(linkClickID);
        setLinkClickIdentifier(linkClickIdentifier);
        setAppLink(appLink);
        setPushIdentifier(pushIdentifier);
        prefHelper_.prefsEditor_.apply();
    }

    public void setExternDebug() {
        BNC_Dev_Debug = true;
    }

    public boolean getExternDebug() {
        return BNC_Dev_Debug;
    }

    public void setRequestMetadata(String str, String str2) {
        if (str != null) {
            if (this.requestMetadata.has(str) && str2 == null) {
                this.requestMetadata.remove(str);
            }
            try {
                this.requestMetadata.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    public JSONObject getRequestMetadata() {
        return this.requestMetadata;
    }

    /* access modifiers changed from: package-private */
    public void addInstallMetadata(String str, String str2) {
        if (str != null) {
            try {
                this.installMetadata.putOpt(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    public JSONObject getInstallMetadata() {
        return this.installMetadata;
    }

    public static void Debug(String str, String str2) {
        if ((Branch.isLogging_ == null && BNC_Dev_Debug) || (Branch.isLogging_ != null && Branch.isLogging_.booleanValue())) {
            if (str2 != null) {
                Log.i(str, str2);
            } else {
                Log.i(str, "An error occurred. Unable to print the log message");
            }
        }
    }
}
