package com.medscape.android.consult.managers;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Settings;
import com.medscape.android.consult.util.ConsultConstants;

public class ConsultFilterManager {
    public static boolean getShowStudentStatus(Context context) {
        String setting;
        if (context == null || (setting = Settings.singleton(context).getSetting((String) ConsultConstants.MED_STUDENTS_FILTER_SHOW, (String) AppEventsConstants.EVENT_PARAM_VALUE_NO)) == null || !setting.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return false;
        }
        return true;
    }

    public static boolean getGlobalToggleStatus(Context context) {
        String setting;
        if (context == null || (setting = Settings.singleton(context).getSetting((String) ConsultConstants.GLOBAL_POSTS_SHOW, (String) AppEventsConstants.EVENT_PARAM_VALUE_NO)) == null || setting.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return false;
        }
        return true;
    }

    public static String getFilters(String str, String str2) {
        if (str == null || str.isEmpty()) {
            str = null;
        }
        if (str2 == null || str2.isEmpty()) {
            return str;
        }
        if (str == null || str.isEmpty()) {
            return str2;
        }
        return str + "," + str2;
    }
}
