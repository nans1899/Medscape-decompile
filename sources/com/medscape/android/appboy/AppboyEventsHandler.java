package com.medscape.android.appboy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.analytics.NotificationAnalyticsHandler;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AppboyEventsHandler {
    private static final long DEFAULT_TIME = -1;
    private static final String EVENT_PREF_PREFIX = "event-pref-";
    private static final long MINUTES_IN_DAY = 1440;

    public static void logDailyEvent(Context context, String str, Activity activity) {
        if (!isLastEventCallWithTwentyFourHours(str)) {
            NotificationAnalyticsHandler.INSTANCE.logEvent(context, str, activity);
        }
    }

    public static void resetDailyLogEvents() {
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        sharedPreferenceProvider.save("event-pref-consultViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-formularyViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-calculatorViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-newsViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-cmeViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-cmetrackerViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-drugsViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-ckbViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-drugSaved", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-ckbSaved", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-newsSaved", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-pillIdViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-interactionCheckerViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-directoryViewed", (Long) -1L);
        sharedPreferenceProvider.save("event-pref-savedViewed", (Long) -1L);
    }

    public static boolean isLastEventCallWithTwentyFourHours(String str) {
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (!isDifferenceGreaterThanTwentyFourHours(timeInMillis, sharedPreferenceProvider.get(EVENT_PREF_PREFIX + str, -1))) {
            return true;
        }
        sharedPreferenceProvider.save(EVENT_PREF_PREFIX + str, Long.valueOf(timeInMillis));
        return false;
    }

    private static boolean isDifferenceGreaterThanTwentyFourHours(long j, long j2) {
        return j - j2 > TimeUnit.MINUTES.toMillis(MINUTES_IN_DAY);
    }

    public static Bundle addUserDataToViewedEvents() {
        Bundle bundle = new Bundle();
        AdsSegvar.getInstance().createProfileSpecificDataMap();
        HashMap<String, String> proileSpecificDataMap = AdsSegvar.getInstance().getProileSpecificDataMap();
        bundle.putString("tc", proileSpecificDataMap.get("tc"));
        if (UserProfileProvider.INSTANCE.getUserProfile() != null) {
            bundle.putString("tar", UserProfileProvider.INSTANCE.getUserProfile().getTidMap().get(NativeAppInstallAd.ASSET_HEADLINE));
        }
        bundle.putString("prof", proileSpecificDataMap.get("pf"));
        bundle.putString("ct", proileSpecificDataMap.get("ct"));
        bundle.putString("usp", proileSpecificDataMap.get("usp"));
        bundle.putString("occ", proileSpecificDataMap.get("occ"));
        bundle.putString("st", proileSpecificDataMap.get("st"));
        return bundle;
    }

    public static void routeDailyEventsToFirebaseOrBraze(Activity activity, String str) {
        Bundle addUserDataToViewedEvents = addUserDataToViewedEvents();
        if (isLastEventCallWithTwentyFourHours(str)) {
            new PlatformRouteDispatcher(activity, false, true).routeEvent(str, addUserDataToViewedEvents);
        } else {
            new PlatformRouteDispatcher(activity).routeEvent(str, addUserDataToViewedEvents);
        }
    }
}
