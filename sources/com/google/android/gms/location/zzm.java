package com.google.android.gms.location;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzm implements Comparator<DetectedActivity> {
    zzm() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        DetectedActivity detectedActivity = (DetectedActivity) obj;
        DetectedActivity detectedActivity2 = (DetectedActivity) obj2;
        int compareTo = Integer.valueOf(detectedActivity2.getConfidence()).compareTo(Integer.valueOf(detectedActivity.getConfidence()));
        return compareTo == 0 ? Integer.valueOf(detectedActivity.getType()).compareTo(Integer.valueOf(detectedActivity2.getType())) : compareTo;
    }
}
