package com.medscape.android.analytics;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;
import com.medscape.android.Constants;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.parser.model.UserProfile;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;

public class FirebaseConversionEventHandler {
    public static void logFirebaseConversionEvent(int i, Activity activity) {
        if (activity != null) {
            AppEventsLogger newLogger = AppEventsLogger.newLogger(activity);
            PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(activity);
            UserProfile userProfile = UserProfileProvider.INSTANCE.getUserProfile(activity);
            Bundle bundle = new Bundle();
            bundle.putString(Constants.CONSULT_USER_PROFESSION_STRING, userProfile.getProfession());
            String specialty = userProfile.getSpecialty();
            if (specialty == null || specialty.isEmpty()) {
                bundle.putString(Constants.CONSULT_USER_SPECIALTY, "none");
            } else {
                bundle.putString(Constants.CONSULT_USER_SPECIALTY, specialty);
            }
            if (i == 1) {
                platformRouteDispatcher.routeEvent("login", bundle);
                newLogger.logEvent("login", bundle);
            } else if (i == 2) {
                platformRouteDispatcher.routeEvent("sign_up", bundle);
                newLogger.logEvent("sign_up", bundle);
                if (userProfile.getCountryId().contains("us")) {
                    if (userProfile.getProfessionId().equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.PHYSICIAN_ID)) {
                        platformRouteDispatcher.routeEvent(FirebaseEventsConstants.US_DOCTOR_REGISTER_EVENT, (Bundle) null);
                    } else if (userProfile.getProfessionId().equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.MEDICAL_LABORATORY_ID)) {
                        platformRouteDispatcher.routeEvent(FirebaseEventsConstants.US_STUDENT_REGISTER_EVENT, (Bundle) null);
                    } else {
                        platformRouteDispatcher.routeEvent(FirebaseEventsConstants.US_REGISTER_EVENT, (Bundle) null);
                    }
                } else if (userProfile.getProfessionId().equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.PHYSICIAN_ID)) {
                    platformRouteDispatcher.routeEvent(FirebaseEventsConstants.NON_US_DOCTOR_REGISTER_EVENT, (Bundle) null);
                } else if (userProfile.getProfessionId().equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.MEDICAL_LABORATORY_ID)) {
                    platformRouteDispatcher.routeEvent(FirebaseEventsConstants.NON_US_STUDENT_REGISTER_EVENT, (Bundle) null);
                } else {
                    platformRouteDispatcher.routeEvent(FirebaseEventsConstants.NON_US_REGISTER_EVENT, (Bundle) null);
                }
            }
        }
    }
}
