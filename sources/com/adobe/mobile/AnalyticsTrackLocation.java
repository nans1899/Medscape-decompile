package com.adobe.mobile;

import android.location.Location;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

final class AnalyticsTrackLocation {
    private static final String ACCURACY_KEY = "a.loc.acc";
    private static final String LOCATION_ACTION_NAME = "Location";
    private static final String LOCATION_LAT_PART1_KEY = "a.loc.lat.a";
    private static final String LOCATION_LAT_PART2_KEY = "a.loc.lat.b";
    private static final String LOCATION_LAT_PART3_KEY = "a.loc.lat.c";
    private static final String LOCATION_LON_PART1_KEY = "a.loc.lon.a";
    private static final String LOCATION_LON_PART2_KEY = "a.loc.lon.b";
    private static final String LOCATION_LON_PART3_KEY = "a.loc.lon.c";
    private static final String POI_DIST_KEY = "a.loc.dist";
    private static final String POI_NAME_KEY = "a.loc.poi";
    private static final String WHOLE_ONLY_FLOAT_FORMAT = "%.0f";
    private static final String ZERO_PADDED_11_6_FLOAT_FORMAT = "% 011.6f";

    AnalyticsTrackLocation() {
    }

    public static void trackLocation(Location location, Map<String, Object> map) {
        Location location2 = location;
        Map<String, Object> map2 = map;
        int i = 0;
        if (location2 == null) {
            StaticMethods.logWarningFormat("Analytics - trackLocation failed, invalid location specified", new Object[0]);
            return;
        }
        String format = String.format(Locale.US, ZERO_PADDED_11_6_FLOAT_FORMAT, new Object[]{Double.valueOf(location.getLatitude())});
        String format2 = String.format(Locale.US, ZERO_PADDED_11_6_FLOAT_FORMAT, new Object[]{Double.valueOf(location.getLongitude())});
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        hashMap.put(LOCATION_LAT_PART1_KEY, format.substring(0, 6).trim());
        hashMap.put(LOCATION_LAT_PART2_KEY, format.substring(6, 8));
        hashMap.put(LOCATION_LAT_PART3_KEY, format.substring(8, 10));
        hashMap.put(LOCATION_LON_PART1_KEY, format2.substring(0, 6).trim());
        hashMap.put(LOCATION_LON_PART2_KEY, format2.substring(6, 8));
        hashMap.put(LOCATION_LON_PART3_KEY, format2.substring(8, 10));
        if (location.hasAccuracy() && location.getAccuracy() > 0.0f) {
            hashMap.put(ACCURACY_KEY, String.format(Locale.US, WHOLE_ONLY_FLOAT_FORMAT, new Object[]{Float.valueOf(location.getAccuracy())}));
        }
        TargetWorker.removePersistentParameter(POI_NAME_KEY);
        TargetWorker.removePersistentParameter(POI_DIST_KEY);
        Lifecycle.removeContextData(POI_NAME_KEY);
        List<List<Object>> pointsOfInterest = MobileConfig.getInstance().getPointsOfInterest();
        if (pointsOfInterest != null) {
            Iterator<List<Object>> it = pointsOfInterest.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                List next = it.next();
                if (next != null && next.size() == 4) {
                    try {
                        String obj = next.get(i).toString();
                        double doubleValue = ((Double) next.get(1)).doubleValue();
                        double doubleValue2 = ((Double) next.get(2)).doubleValue();
                        double doubleValue3 = ((Double) next.get(3)).doubleValue();
                        Location location3 = new Location("poi");
                        location3.setLatitude(doubleValue);
                        location3.setLongitude(doubleValue2);
                        double distanceTo = (double) location3.distanceTo(location2);
                        if (distanceTo <= doubleValue3 && obj != null) {
                            hashMap.put(POI_NAME_KEY, obj);
                            TargetWorker.addPersistentParameter(POI_NAME_KEY, obj);
                            hashMap.put(POI_DIST_KEY, String.format(Locale.US, WHOLE_ONLY_FLOAT_FORMAT, new Object[]{Double.valueOf(distanceTo)}));
                            TargetWorker.addPersistentParameter(POI_DIST_KEY, String.valueOf(distanceTo));
                            HashMap hashMap2 = new HashMap();
                            hashMap2.put(POI_NAME_KEY, obj);
                            Lifecycle.updateContextData(hashMap2);
                            break;
                        }
                    } catch (ClassCastException e) {
                        StaticMethods.logWarningFormat("Analytics - Invalid data for point of interest(%s)", e.getLocalizedMessage());
                    }
                    i = 0;
                }
            }
        }
        AnalyticsTrackInternal.trackInternal("Location", hashMap, StaticMethods.getTimeSince1970());
    }
}
