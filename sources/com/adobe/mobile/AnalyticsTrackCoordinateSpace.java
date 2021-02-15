package com.adobe.mobile;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

final class AnalyticsTrackCoordinateSpace {
    private static final String COORDINATE_ACTION_NAME = "Coordinates";
    private static final String COORDINATE_FLOAT_FORMAT = "%.2f";
    private static final String COORDINATE_NAME_KEY = "a.map.name";
    private static final String COORDINATE_X_KEY = "a.map.x";
    private static final String COORDINATE_Y_KEY = "a.map.y";

    AnalyticsTrackCoordinateSpace() {
    }

    public static void trackCoordinateSpace(String str, float f, float f2, Map<String, Object> map) {
        if (f < 0.0f || f > 1.0f || f2 < 0.0f || f2 > 1.0f) {
            StaticMethods.logWarningFormat("Analytics - trackCoordinateSpace failed, the coordinates (x:%.2f, y:%.2f) must be between 0.0f & 1.0f.", Float.valueOf(f), Float.valueOf(f2));
        } else if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("Analytics - trackCoordinateSpace failed, the name was empty or only contained whitespace and is required to map the coorindates to a coordinates space.", new Object[0]);
        } else {
            String format = String.format(Locale.US, COORDINATE_FLOAT_FORMAT, new Object[]{Float.valueOf(Math.abs(f))});
            String format2 = String.format(Locale.US, COORDINATE_FLOAT_FORMAT, new Object[]{Float.valueOf(Math.abs(f2))});
            HashMap hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            hashMap.put(COORDINATE_NAME_KEY, str);
            hashMap.put(COORDINATE_X_KEY, format);
            hashMap.put(COORDINATE_Y_KEY, format2);
            AnalyticsTrackInternal.trackInternal(COORDINATE_ACTION_NAME, hashMap, StaticMethods.getTimeSince1970());
        }
    }
}
