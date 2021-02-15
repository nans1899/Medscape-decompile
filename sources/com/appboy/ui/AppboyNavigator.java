package com.appboy.ui;

import android.content.Context;
import com.appboy.IAppboyNavigator;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.actions.UriAction;

public class AppboyNavigator implements IAppboyNavigator {
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyNavigator.class);
    private static volatile IAppboyNavigator sCustomAppboyNavigator;
    private static volatile IAppboyNavigator sDefaultAppboyNavigator = new AppboyNavigator();

    public void gotoNewsFeed(Context context, NewsfeedAction newsfeedAction) {
        executeNewsFeedAction(context, newsfeedAction);
    }

    public void gotoUri(Context context, UriAction uriAction) {
        executeUriAction(context, uriAction);
    }

    public static void executeNewsFeedAction(Context context, NewsfeedAction newsfeedAction) {
        if (newsfeedAction == null) {
            AppboyLogger.e(TAG, "IAppboyNavigator cannot open News feed because the news feed action object was null.");
        } else {
            newsfeedAction.execute(context);
        }
    }

    public static void executeUriAction(Context context, UriAction uriAction) {
        if (uriAction == null) {
            AppboyLogger.e(TAG, "IAppboyNavigator cannot open Uri because the Uri action object was null.");
        } else {
            uriAction.execute(context);
        }
    }

    public static IAppboyNavigator getAppboyNavigator() {
        if (sCustomAppboyNavigator != null) {
            return sCustomAppboyNavigator;
        }
        return sDefaultAppboyNavigator;
    }

    public static void setAppboyNavigator(IAppboyNavigator iAppboyNavigator) {
        AppboyLogger.d(TAG, "Custom IAppboyNavigator set");
        sCustomAppboyNavigator = iAppboyNavigator;
    }
}
