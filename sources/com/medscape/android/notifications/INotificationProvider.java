package com.medscape.android.notifications;

import android.app.Activity;
import android.content.Context;

public interface INotificationProvider {
    public static final String API_URL = "";
    public static final String APP_KEY = "";
    public static final String APP_KEY_TEST = "";
    public static final String USER_TAG_KEY_APPVER = "appver";
    public static final String USER_TAG_KEY_COUNTRY = "ct";
    public static final String USER_TAG_KEY_DEVICE = "device";
    public static final String USER_TAG_KEY_LOGGEDIN = "loggedin";
    public static final String USER_TAG_KEY_MEDPULSEPUSH = "medpulsePush";
    public static final String USER_TAG_KEY_PROFESSION = "profid";
    public static final String USER_TAG_KEY_SPECIALTY = "spid";
    public static final String USER_TAG_KEY_STATE = "st";
    public static final String USER_TAG_KEY_TIDS = "tids";
    public static final String USER_TAG_USER_ID = "userid";
    public static final String USER_TAG_VALUE_DEVICE = "android";
    public static final String USER_TAG_VALUE_MEDPULSEPUSH_DISABLED = "medpulsePushDisabled";
    public static final String USER_TAG_VALUE_MEDPULSEPUSH_ENABLED = "medpulsePushEnabled";

    void clearAllTags(Context context);

    String getProviderId(Context context);

    void logEvent(Context context, String str, Activity activity);

    void tagLoggedInUserAttributes(Context context, Activity activity);
}
