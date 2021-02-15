package com.wbmd.qxcalculator.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;

public class AnalyticsHandler {
    private static AnalyticsHandler mInstance;
    private Context context;
    private FirebaseAnalytics mFirebaseAnalytics;

    public static void initialize(Application application) {
        AnalyticsHandler instance = getInstance();
        mInstance = instance;
        instance.initializeFirebaseAnalytics(application);
    }

    public static AnalyticsHandler getInstance() {
        if (mInstance == null) {
            mInstance = new AnalyticsHandler();
        }
        return mInstance;
    }

    private AnalyticsHandler() {
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    private void initializeFirebaseAnalytics(Application application) {
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(application);
    }

    public void setUserDetails() {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        if (dbUser == null || !dbUser.getIsPersonalizationEnabled()) {
            clearUserProperties();
            return;
        }
        if (UserManager.getInstance().getUserEmail() != null) {
            this.mFirebaseAnalytics.setUserId(UserManager.getInstance().getUserEmail());
        }
        if (dbUser.getProfession() != null) {
            this.mFirebaseAnalytics.setUserProperty("profession", dbUser.getProfession().getName());
        }
        if (dbUser.getProfession() != null) {
            this.mFirebaseAnalytics.setUserProperty("profession_id", dbUser.getProfession().getIdentifier().toString());
        }
        if (dbUser.getSpecialty() != null) {
            this.mFirebaseAnalytics.setUserProperty(OmnitureConstants.OMNITURE_FILTER_SPECIALTY, dbUser.getSpecialty().getName());
        }
        if (dbUser.getSpecialty() != null) {
            this.mFirebaseAnalytics.setUserProperty("specialty_id", dbUser.getSpecialty().getIdentifier().toString());
        }
        if (dbUser.getLocation() != null) {
            this.mFirebaseAnalytics.setUserProperty("location", dbUser.getLocation().getName());
        }
        if (dbUser.getLocation() != null) {
            this.mFirebaseAnalytics.setUserProperty(FirebaseAnalytics.Param.LOCATION_ID, dbUser.getLocation().getIdentifier().toString());
        }
    }

    private void clearUserProperties() {
        this.mFirebaseAnalytics.setUserId("-");
        this.mFirebaseAnalytics.setUserProperty("profession", "-");
        this.mFirebaseAnalytics.setUserProperty("profession_id", "-");
        this.mFirebaseAnalytics.setUserProperty(OmnitureConstants.OMNITURE_FILTER_SPECIALTY, "-");
        this.mFirebaseAnalytics.setUserProperty("specialty_id", "-");
        this.mFirebaseAnalytics.setUserProperty("location", "-");
        this.mFirebaseAnalytics.setUserProperty(FirebaseAnalytics.Param.LOCATION_ID, "-");
    }

    public void trackPageView(Activity activity, String str) {
        if (UserManager.getInstance().getDbUser() != null && UserManager.getInstance().getDbUser().getIsAnalyticsEnabled() && activity != null) {
            this.mFirebaseAnalytics.setCurrentScreen(activity, str, (String) null);
        }
    }
}
