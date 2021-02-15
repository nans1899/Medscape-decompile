package com.medscape.android.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.security.SimpleCrypto;
import com.wbmd.wbmddatacompliance.utils.Constants;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.media.android.bidder.base.models.MNetUser;

public class Settings {
    public static final String ADDRESS = "address";
    private static final String AD_DESCRIPTOR_CONTENT_KEY = "ad_descriptor_content_key";
    private static final String AD_DESCRIPTOR_IMAGE_KEY = "ad_descriptor_image_key";
    private static final String AD_DESCRIPTOR_IMAGE_TYPE_KEY = "ad_descriptor_imagetype_key";
    private static final String AD_DESCRIPTOR_TEXT_KEY = "ad_descriptor_text_key";
    private static final String AD_DESCRIPTOR_TRACK_KEY = "ad_descriptor_track_key";
    private static final String AD_DESCRIPTOR_TYPE_KEY = "ad_descriptor_type_key";
    private static final String AD_DESCRIPTOR_URL_KEY = "ad_descriptor_url_key";
    public static final String AGE = "age";
    public static final String APP_AD_VALUE = "APP_AD_VALUE";
    public static final String APP_BACKGROUND_TIME_KEY = "app_background_time";
    public static final String APP_DIRECTORY_PATH = "app_directory_path";
    public static final String APP_REPLACED = "AppReplaced";
    public static final String APP_VERSION = "AppVersion";
    public static final String CITY = "city";
    private static final String CONTACTS_SAVE_DIALOG_KEY = "contacts_save_dialog";
    private static final String CONTACTS_SAVE_KEY = "contacts_save";
    public static final String CONTENT_VERSION = "contentVersion";
    public static final String DIAGNOSTIC_ENABLED_KEY = "diagnostic_enabled";
    private static final String DIAGNOSTIC_REPORT_KEY = "DIAGNOSTIC_REPORT_KEY";
    public static final String DIRECTION_ZIP = "direction_zip";
    private static final String DISPLAY_SAVED_REMIND_ME_SIGNIN = "display_saved_remind_me_signin";
    public static final String EMAIL = "email";
    public static final String FIRST_SYMPTOM_VISIT = "firstSymptomVisit";
    public static final String FIRST_TOUR_VISIT = "firstTourVisit";
    public static final String GENDER = "gender";
    public static final String HAS_BREAKING_NEWS_NOTIFICATION = "has_breaking_news_notification";
    public static final String HAS_EVER_LOGGED_IN_KEY = "has_ever_logged_in";
    public static final String HAS_RECOMMENDED_CONTENT_NOTIFICATION = "has_recommended_content_notification";
    private static final String HAS_SEEN_ARTICLE_SWIPE_HINT = "has_seen_swipe_hint";
    public static final String HAS_TOP_STORIES_NOTIFICATION = "has_top_stories_notification";
    private static final String IS_FIRST_APP_LAUNCH = "is_first_app_laucnh_setting";
    private static final String IS_REAUTH_REQUIRED = "is_pin_required";
    private static final String IS_RETURNING_APP_BACKGROUNDED = "is_app_backgrounded";
    public static final String IS_TABLET = "isTablet";
    public static final String LAST_OPTIONAL_UPDATE_DATE_KEY = "last_optional_update";
    public static final String LAST_USER_LOCATION_CALCULATED_TIME = "last_user_location_time";
    public static final String LATITUDE_KEY = "lat";
    public static final String LEGAL_VERSION = "legalVersion";
    private static final String LOGIN_COOKIE_KEY = "login_cookie";
    public static final String LONGITUDE_KEY = "long";
    public static final String LegalSettings = "legalSettings";
    public static final String MAPP_KEY = "mapp";
    public static final String MAPP_KEY_VALUE = "";
    private static final String PERSISTENCE_PROD_ENVIRONMENT_KEY = "persistence_prod_key";
    private static final String PIN_KEY = "pin";
    public static final String PREFERENCE_REG_ID = "preference_reg_id";
    private static final String RATE_COUNT_KEY = "rate_count";
    public static final String SEARCH_FILTER_KEY = "filter_key";
    private static final String SESSIONS_KEY = "sessions";
    private static final String SESSIONS_LAST_RATE_KEY = "sessions_since_last_rate";
    private static final String SESSIONS_LAST_SHARE_KEY = "sessions_since_last_share";
    private static final String SESSIONS_UNTIL_NEXT_RATE_KEY = "sessions_until_next_rate";
    private static final String SESSIONS_UNTIL_NEXT_SHARE_KEY = "sessions_until_next_share";
    private static final String SHARE_COUNT_KEY = "share_count";
    public static final String STATE = "state";
    private static final String SUBSCRIBER_ID_KEY = "subscriber_id";
    private static final String SYMPTOMS_FIRST_USAGE = "symptom_firsttime";
    public static final String USER_AGE = "user_age";
    public static final String USER_EMAIL_KEY = "user_name";
    public static final String USER_GENDER = "user_gender";
    public static final String UUID = "uuid";
    public static final String WALGREENS_DRIVER_SETTING = "walgreens_driver_setting";
    private static final String WAS_REQUIRED_PIN_BEING_ENTERED = "was_required_pin_being_entered";
    public static final String ZIP = "zip";
    private static Settings _sd = null;
    public static final String advertising_identifier = "advertising_identifier";
    public static final String defaultMaidID = "00000000-0000-0000-0000-000000000000";
    public static String firstTimeInstallfireSignInAndSignUpEvent = "firstTimeInstallfireSignInAndSignUpEvent";
    private static boolean isAllUpToDate = false;
    private static boolean isUserUnderAge = false;
    public static Map<String, List<String>> loginHeaderFields = new HashMap();
    private static Context mContext;
    public static String signAndSignUpEventFired = "signAndSignUpEventFired";
    public static String tapStreamActivationFired = "tapStreamActivationFired";

    public int getAppAgeRange(int i) {
        if (i >= 13 && i <= 17) {
            return 2;
        }
        if (i >= 18 && i <= 20) {
            return 3;
        }
        if (i >= 21 && i <= 24) {
            return 4;
        }
        if (i >= 25 && i <= 29) {
            return 5;
        }
        if (i >= 30 && i <= 34) {
            return 6;
        }
        if (i >= 35 && i <= 44) {
            return 7;
        }
        if (i >= 45 && i <= 54) {
            return 8;
        }
        if (i < 55 || i > 64) {
            return i >= 65 ? 10 : 0;
        }
        return 9;
    }

    public void getPersistenceEnvironment() {
    }

    private Settings(Context context) {
        mContext = context;
    }

    public String getVersionName() {
        String str;
        PackageManager.NameNotFoundException e;
        try {
            str = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
            try {
                return str.replace(".", "");
            } catch (PackageManager.NameNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        } catch (PackageManager.NameNotFoundException e3) {
            PackageManager.NameNotFoundException nameNotFoundException = e3;
            str = "";
            e = nameNotFoundException;
            e.printStackTrace();
            return str;
        }
    }

    public boolean hasService() {
        return mContext != null;
    }

    public static boolean isPhone() {
        if (mContext != null) {
        }
        return true;
    }

    public static Settings singleton(Context context) {
        Settings settings = _sd;
        if (settings == null || !settings.hasService()) {
            _sd = new Settings(context);
        }
        return _sd;
    }

    public String getSetting(String str, String str2) {
        String str3 = str + "xml";
        Context context = mContext;
        if (context != null) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (defaultSharedPreferences.contains(str3)) {
                if (!str3.equals("genderxml")) {
                    return defaultSharedPreferences.getString(str3, str2);
                }
                String string = defaultSharedPreferences.getString(str3, str2);
                if (string.equals("Male")) {
                    return "M";
                }
                if (string.equals("Female")) {
                    return MNetUser.FEMALE;
                }
                return defaultSharedPreferences.getString(str3, str2);
            }
        }
        return str2;
    }

    public boolean removeSetting(String str) {
        String str2 = str + "xml";
        Context context = mContext;
        if (context == null || context == null) {
            return false;
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.remove(str2);
        return edit.commit();
    }

    public boolean saveSetting(String str, String str2) {
        String str3 = str + "xml";
        Context context = mContext;
        if (context == null || context == null) {
            return false;
        }
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        if (str3.equals("genderxml")) {
            if (str2.equals("M")) {
                str2 = "Male";
            } else if (str2.equals(MNetUser.FEMALE)) {
                str2 = "Female";
            }
        }
        edit.putString(str3, str2);
        return edit.commit();
    }

    public void saveLoginCookie(String str) {
        HashMap hashMap = new HashMap();
        if (str == null || str.equalsIgnoreCase("")) {
            hashMap.put("wapp.usergroup", "consumer-unregistered");
            hashMap.put("wapp.registered", "unregistered");
        } else {
            hashMap.put("wapp.usergroup", Constants.APPLICATION_TYPE_CONSUMER);
            hashMap.put("wapp.registered", "consumer-full");
        }
        WBMDOmnitureManager.shared.mData.putAll(hashMap);
        saveSetting(LOGIN_COOKIE_KEY, str);
    }

    public String getLoginCookie() {
        return getSetting(LOGIN_COOKIE_KEY, "");
    }

    public void saveLoginHeaderFields(Map<String, List<String>> map) {
        loginHeaderFields = map;
    }

    public Map<String, List<String>> getLoginHeaderFields() {
        return loginHeaderFields;
    }

    public boolean isLoggedIn() {
        String setting = getSetting(LOGIN_COOKIE_KEY, "");
        return setting != null && !setting.isEmpty();
    }

    public static boolean isAllUpToDate() {
        return isAllUpToDate;
    }

    public static void setAllUpToDate(boolean z) {
        isAllUpToDate = z;
    }

    public static boolean isUserUnderAge() {
        return isUserUnderAge;
    }

    public static void setUserUnderAge(boolean z) {
        isUserUnderAge = z;
    }

    public void saveWalgreensDriverSetting(String str) {
        saveSetting(WALGREENS_DRIVER_SETTING, str);
    }

    public void saveContentVersion(String str) {
        saveSetting(CONTENT_VERSION, str);
    }

    public void saveLegalVersion(String str) {
        saveSetting("legalVersion", str);
    }

    public void saveShareCount(int i) {
        saveSetting(SHARE_COUNT_KEY, "" + i);
    }

    public void saveRateCount(int i) {
        saveSetting(RATE_COUNT_KEY, "" + i);
    }

    public String getContentVersion() {
        return getSetting(CONTENT_VERSION, AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public String getWalgreensDriverSetting() {
        return getSetting(WALGREENS_DRIVER_SETTING, AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public String getLegalVersion() {
        return getSetting("legalVersion", AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public void saveLastOptionalUpdatePopUp(long j) {
        saveSetting(LAST_OPTIONAL_UPDATE_DATE_KEY, "" + j);
    }

    public long getLastOptionalUpdate() {
        String setting = getSetting(LAST_OPTIONAL_UPDATE_DATE_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        try {
            return Long.parseLong(setting);
        } catch (NumberFormatException unused) {
            Log.e("Settings", "Error parsing last optional update date. Setting: " + setting + " could not be parsed into a long (milliseconds since 1970 utc).");
            return 0;
        }
    }

    public void saveSessionsUntilNextShare(int i) {
        saveSetting(SESSIONS_UNTIL_NEXT_SHARE_KEY, "" + i);
    }

    public void saveSessionsUntilNextRate(int i) {
        saveSetting(SESSIONS_UNTIL_NEXT_RATE_KEY, "" + i);
    }

    public void saveSessionsSinceLastShare(int i) {
        saveSetting(SESSIONS_LAST_SHARE_KEY, "" + i);
    }

    public void saveSessionsSinceLastRate(int i) {
        saveSetting(SESSIONS_LAST_RATE_KEY, "" + i);
    }

    public void saveNumberOfTimesShareShown(int i) {
        saveSetting(SHARE_COUNT_KEY, "" + i);
    }

    public int getShareCount() {
        try {
            return Integer.parseInt(getSetting(SHARE_COUNT_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getRateCount() {
        try {
            return Integer.parseInt(getSetting(RATE_COUNT_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSessionsSinceLastShare() {
        try {
            return Integer.parseInt(getSetting(SESSIONS_LAST_SHARE_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getSessionsSinceLastRate() {
        try {
            return Integer.parseInt(getSetting(SESSIONS_LAST_RATE_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void saveNumberOfTimesRateShown(int i) {
        saveSetting(RATE_COUNT_KEY, "" + i);
    }

    public void saveSubscriberId(String str) {
        saveSetting(SUBSCRIBER_ID_KEY, str);
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.regid", str);
        WBMDOmnitureManager.shared.mData.putAll(hashMap);
    }

    public String getSubscriberId() {
        return getSetting(SUBSCRIBER_ID_KEY, "");
    }

    public void savePin(String str) {
        saveSetting(PIN_KEY, str);
    }

    public String getPin() {
        return getSetting(PIN_KEY, "");
    }

    public boolean hasPin() {
        String setting = getSetting(PIN_KEY, "");
        return setting != null && !setting.isEmpty();
    }

    public boolean isFirstAppLaunch() {
        return Boolean.parseBoolean(getSetting(IS_FIRST_APP_LAUNCH, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    public void saveOnFirstAppLaunch() {
        saveSetting(IS_FIRST_APP_LAUNCH, "false");
    }

    public void saveHasEverLoggedIn(boolean z) {
        saveSetting(HAS_EVER_LOGGED_IN_KEY, "" + z);
    }

    public boolean getHasEverLoggedIn() {
        return Boolean.parseBoolean(getSetting(HAS_EVER_LOGGED_IN_KEY, "false"));
    }

    public void saveAppBackgroundTime(long j) {
        saveSetting(APP_BACKGROUND_TIME_KEY, "" + j);
    }

    public long getAppBackgroundTime() {
        try {
            return Long.parseLong(getSetting(APP_BACKGROUND_TIME_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public void saveIsAppBackgrounded(boolean z) {
        saveSetting(IS_RETURNING_APP_BACKGROUNDED, "" + z);
    }

    public boolean getIsReturningFromBackground() {
        return Boolean.parseBoolean(getSetting(IS_RETURNING_APP_BACKGROUNDED, "false"));
    }

    public boolean getWasRequiredPinBeingEntered() {
        return Boolean.parseBoolean(getSetting(WAS_REQUIRED_PIN_BEING_ENTERED, "false"));
    }

    public void saveWasRequiredPinBeingEntered(boolean z) {
        saveSetting(WAS_REQUIRED_PIN_BEING_ENTERED, "" + z);
    }

    public boolean getIsReauthRequired() {
        return Boolean.parseBoolean(getSetting(IS_REAUTH_REQUIRED, "false"));
    }

    public void saveIsReauthRequired(boolean z) {
        saveSetting(IS_REAUTH_REQUIRED, "" + z);
    }

    public void saveSessions(int i) {
        saveSetting(SESSIONS_KEY, "" + i);
    }

    public int getSessions() {
        try {
            return Integer.parseInt(getSetting(SESSIONS_KEY, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public String getUserEmail() {
        String setting = getSetting(USER_EMAIL_KEY, "");
        try {
            return SimpleCrypto.decrypt(setting);
        } catch (Exception e) {
            e.printStackTrace();
            return setting;
        }
    }

    public void saveUserEmail(String str) {
        if (str != null) {
            try {
                str = SimpleCrypto.encrypt(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveSetting(USER_EMAIL_KEY, str);
        }
    }

    public void saveDiagnosticEnabled(boolean z) {
        saveSetting(DIAGNOSTIC_ENABLED_KEY, "" + z);
    }

    public boolean getDiagnosticEnabled() {
        return Boolean.parseBoolean(getSetting(DIAGNOSTIC_ENABLED_KEY, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    public void saveDiagnosticReport(String str) {
        saveSetting(DIAGNOSTIC_REPORT_KEY, str);
    }

    public String getDiagnosticReport() {
        return getSetting(DIAGNOSTIC_REPORT_KEY, (String) null);
    }

    public void savePersistenceEnvironment(String str) {
        saveSetting(PERSISTENCE_PROD_ENVIRONMENT_KEY, str);
    }

    public boolean getIsTablet() {
        return Boolean.parseBoolean(getSetting("isTablet", "false"));
    }

    public void setSaveContact(boolean z) {
        saveSetting(CONTACTS_SAVE_KEY, "" + z);
    }

    public boolean getSaveContact() {
        try {
            return Boolean.parseBoolean(getSetting(CONTACTS_SAVE_KEY, "false"));
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void setSymptomFirstUsage(boolean z) {
        saveSetting(SYMPTOMS_FIRST_USAGE, "" + z);
    }

    public boolean getSymptomFirstUsage() {
        try {
            return Boolean.parseBoolean(getSetting(SYMPTOMS_FIRST_USAGE, "false"));
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void setSaveContactDialog(boolean z) {
        saveSetting(CONTACTS_SAVE_DIALOG_KEY, "" + z);
    }

    public boolean getSaveContactDialog() {
        try {
            return Boolean.parseBoolean(getSetting(CONTACTS_SAVE_DIALOG_KEY, "false"));
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void clearSavedAdData(String str) {
        saveSetting(str + AD_DESCRIPTOR_CONTENT_KEY, (String) null);
        saveSetting(str + AD_DESCRIPTOR_TYPE_KEY, (String) null);
        saveSetting(str + AD_DESCRIPTOR_URL_KEY, (String) null);
        saveSetting(str + AD_DESCRIPTOR_TRACK_KEY, (String) null);
        saveSetting(str + AD_DESCRIPTOR_IMAGE_KEY, (String) null);
        saveSetting(str + AD_DESCRIPTOR_IMAGE_TYPE_KEY, (String) null);
        saveSetting(str + AD_DESCRIPTOR_TEXT_KEY, (String) null);
    }

    public boolean hasSeenArticleSwipeHint() {
        return Boolean.parseBoolean(getSetting("has_seen_swipe_hint", "false"));
    }

    public void saveHasSeenArticleSwipeHint() {
        saveSetting("has_seen_swipe_hint", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public void initializeNotificationPref() {
        singleton(mContext).saveSetting(HAS_BREAKING_NEWS_NOTIFICATION, "not_set");
        singleton(mContext).saveSetting(HAS_TOP_STORIES_NOTIFICATION, "not_set");
        singleton(mContext).saveSetting(HAS_RECOMMENDED_CONTENT_NOTIFICATION, "not_set");
    }

    public void incrementDisplaySavedRemindDialogCount() {
        saveSetting(DISPLAY_SAVED_REMIND_ME_SIGNIN, String.valueOf(Integer.parseInt(getSetting(DISPLAY_SAVED_REMIND_ME_SIGNIN, AppEventsConstants.EVENT_PARAM_VALUE_NO)) + 1));
    }

    public void resetDisplaySavedRemindDialogCount() {
        saveSetting(DISPLAY_SAVED_REMIND_ME_SIGNIN, AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    public boolean doDisplaySavedRemindMeDialog() {
        return Integer.parseInt(getSetting(DISPLAY_SAVED_REMIND_ME_SIGNIN, AppEventsConstants.EVENT_PARAM_VALUE_NO)) >= 5 || Integer.parseInt(getSetting(DISPLAY_SAVED_REMIND_ME_SIGNIN, AppEventsConstants.EVENT_PARAM_VALUE_NO)) == 0;
    }
}
