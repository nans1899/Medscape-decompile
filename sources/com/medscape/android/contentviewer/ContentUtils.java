package com.medscape.android.contentviewer;

import com.facebook.appevents.AppEventsConstants;

public class ContentUtils {
    public static int getHeaderFontSize(int i) {
        if (i == 0) {
            return 21;
        }
        if (i == 1) {
            return 22;
        }
        if (i == 2) {
            return 23;
        }
        if (i != 4) {
            return (i == 5 || i == 6) ? 27 : 24;
        }
        return 25;
    }

    public static String getOmnitureValueForFontSize(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 4 ? i != 5 ? i != 6 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "p3" : "p2" : "p1" : "m1" : "m2" : "m3";
    }

    public static int getPopOverFontSize(int i) {
        if (i == 0) {
            return 11;
        }
        if (i == 1) {
            return 12;
        }
        if (i == 2) {
            return 13;
        }
        if (i == 4) {
            return 15;
        }
        if (i != 5) {
            return i != 6 ? 14 : 17;
        }
        return 16;
    }

    public static int getSubHeaderFontSize(int i) {
        if (i == 0) {
            return 17;
        }
        if (i == 1) {
            return 18;
        }
        if (i == 2) {
            return 19;
        }
        if (i == 4) {
            return 21;
        }
        if (i != 5) {
            return i != 6 ? 20 : 25;
        }
        return 23;
    }

    public static int getTextFontSize(int i) {
        if (i == 0) {
            return 13;
        }
        if (i == 1) {
            return 14;
        }
        if (i == 2) {
            return 15;
        }
        if (i == 4) {
            return 17;
        }
        if (i != 5) {
            return i != 6 ? 16 : 21;
        }
        return 19;
    }
}
