package com.wbmd.wbmddatacompliance.utils;

import com.wbmd.wbmddatacompliance.activities.AcceptActivity;

public class Constants {
    public static final String APPLICATION_TYPE_CONSUMER = "consumer";
    public static final String APPLICATION_TYPE_PROFESSIONAL = "professional";
    public static final String BASE_URL = "https://www.webmd.com/";
    public static final String BLOCKER_ACTIVITY_NAME = ("com.wbmd.wbmddatacompliance.activities." + AcceptActivity.class.getSimpleName());
    public static final String BROADCAST_ACCEPT_ACTION = "com.webmd.android.intent.GDPR_ACCEPT_ACTION";
    public static final String BROADCAST_ACTIVITY_VIEW = "com.webmd.android.intent.GDPR_OPEN_BLOCKER_VIEW";
    public static final String COOKIE_PRIVACY_TITLE = "Cookie Policy";
    public static final String EXTRA_APPLICATION_TYPE = "extra_application_type";
    public static final String EXTRA_PRIVACY_URL_KEY = "privacy_url";
    public static final String EXTRA_TITLE_KEY = "extra_title_key";
    public static final String EXTRA_URL_KEY = "web_view_url";
    public static final String MORE_INFO_TITLE = "More Information";
    public static final String MORE_INFO_URL = "https://www.webmd.com/about-webmd-policies/about-privacy-policy";
    public static final String PRIVACY_TITLE = "Privacy Policy";
    public static final String SHARED_PREF_KEY_IS_ACCEPTED = "wbmd_shared_pref_is_accepted";
    public static final String SHARED_PREF_KEY_IS_FORCE_OVERRIDE = "wbmd_shared_pref_is_force_override";
    public static final String SHARED_PREF_KEY_IS_GEO_CODE_EU = "wbmd_shared_pref_is_geo_code_eu";
    public static final String SHARED_PREF_KEY_IS_GEO_LOCATED = "wbmd_shared_pref_is_geo_located";
    public static final String SHARED_PREF_KEY_SNOOZE_TIMER_VALUE = "reminder_snooze_timer_value";
}
