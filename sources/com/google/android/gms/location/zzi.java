package com.google.android.gms.location;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
final class zzi implements Comparator<ActivityTransition> {
    zzi() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        ActivityTransition activityTransition = (ActivityTransition) obj;
        ActivityTransition activityTransition2 = (ActivityTransition) obj2;
        int activityType = activityTransition.getActivityType();
        int activityType2 = activityTransition2.getActivityType();
        if (activityType != activityType2) {
            return activityType < activityType2 ? -1 : 1;
        }
        int transitionType = activityTransition.getTransitionType();
        int transitionType2 = activityTransition2.getTransitionType();
        if (transitionType == transitionType2) {
            return 0;
        }
        return transitionType < transitionType2 ? -1 : 1;
    }
}
