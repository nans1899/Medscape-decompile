package com.appboy.ui.inappmessage.listeners;

import android.net.Uri;
import android.os.Bundle;
import com.appboy.Appboy;
import com.appboy.enums.Channel;
import com.appboy.models.IInAppMessage;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.BundleUtils;
import com.appboy.support.StringUtils;
import com.appboy.ui.AppboyNavigator;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.actions.NewsfeedAction;
import com.appboy.ui.actions.UriAction;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;

public class AppboyInAppMessageWebViewClientListener implements IInAppMessageWebViewClientListener {
    private static final String HTML_IAM_CUSTOM_EVENT_NAME_KEY = "name";
    private static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageWebViewClientListener.class);

    public void onCloseAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        AppboyLogger.d(TAG, "IInAppMessageWebViewClientListener.onCloseAction called.");
        logHtmlInAppMessageClick(iInAppMessage, bundle);
        getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(true);
        getInAppMessageManager().getHtmlInAppMessageActionListener().onCloseClicked(iInAppMessage, str, bundle);
    }

    public void onNewsfeedAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        AppboyLogger.d(TAG, "IInAppMessageWebViewClientListener.onNewsfeedAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.w(TAG, "Can't perform news feed action because the cached activity is null.");
            return;
        }
        logHtmlInAppMessageClick(iInAppMessage, bundle);
        if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onNewsfeedClicked(iInAppMessage, str, bundle)) {
            iInAppMessage.setAnimateOut(false);
            getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
            AppboyNavigator.getAppboyNavigator().gotoNewsFeed(getInAppMessageManager().getActivity(), new NewsfeedAction(BundleUtils.mapToBundle(iInAppMessage.getExtras()), Channel.INAPP_MESSAGE));
        }
    }

    public void onCustomEventAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        AppboyLogger.d(TAG, "IInAppMessageWebViewClientListener.onCustomEventAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.w(TAG, "Can't perform custom event action because the activity is null.");
        } else if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onCustomEventFired(iInAppMessage, str, bundle)) {
            String parseCustomEventNameFromQueryBundle = parseCustomEventNameFromQueryBundle(bundle);
            if (!StringUtils.isNullOrBlank(parseCustomEventNameFromQueryBundle)) {
                Appboy.getInstance(getInAppMessageManager().getActivity()).logCustomEvent(parseCustomEventNameFromQueryBundle, parsePropertiesFromQueryBundle(bundle));
            }
        }
    }

    public void onOtherUrlAction(IInAppMessage iInAppMessage, String str, Bundle bundle) {
        AppboyLogger.d(TAG, "IInAppMessageWebViewClientListener.onOtherUrlAction called.");
        if (getInAppMessageManager().getActivity() == null) {
            AppboyLogger.w(TAG, "Can't perform other url action because the cached activity is null.");
            return;
        }
        logHtmlInAppMessageClick(iInAppMessage, bundle);
        if (!getInAppMessageManager().getHtmlInAppMessageActionListener().onOtherUrlAction(iInAppMessage, str, bundle)) {
            boolean parseUseWebViewFromQueryBundle = parseUseWebViewFromQueryBundle(iInAppMessage, bundle);
            Bundle mapToBundle = BundleUtils.mapToBundle(iInAppMessage.getExtras());
            mapToBundle.putAll(bundle);
            UriAction createUriActionFromUrlString = ActionFactory.createUriActionFromUrlString(str, mapToBundle, parseUseWebViewFromQueryBundle, Channel.INAPP_MESSAGE);
            Uri uri = createUriActionFromUrlString.getUri();
            if (uri == null || !AppboyFileUtils.isLocalUri(uri)) {
                iInAppMessage.setAnimateOut(false);
                getInAppMessageManager().hideCurrentlyDisplayingInAppMessage(false);
                if (createUriActionFromUrlString != null) {
                    AppboyNavigator.getAppboyNavigator().gotoUri(getInAppMessageManager().getApplicationContext(), createUriActionFromUrlString);
                    return;
                }
                return;
            }
            String str2 = TAG;
            AppboyLogger.w(str2, "Not passing local URI to AppboyNavigator. Got local uri: " + uri);
        }
    }

    static boolean parseUseWebViewFromQueryBundle(IInAppMessage iInAppMessage, Bundle bundle) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        if (bundle.containsKey(InAppMessageWebViewClient.QUERY_NAME_DEEPLINK)) {
            z2 = Boolean.parseBoolean(bundle.getString(InAppMessageWebViewClient.QUERY_NAME_DEEPLINK));
            z = true;
        } else {
            z2 = false;
            z = false;
        }
        if (bundle.containsKey(InAppMessageWebViewClient.QUERY_NAME_EXTERNAL_OPEN)) {
            z3 = Boolean.parseBoolean(bundle.getString(InAppMessageWebViewClient.QUERY_NAME_EXTERNAL_OPEN));
            z = true;
        } else {
            z3 = false;
        }
        boolean openUriInWebView = iInAppMessage.getOpenUriInWebView();
        if (!z) {
            return openUriInWebView;
        }
        if (z2 || z3) {
            z4 = false;
        }
        return z4;
    }

    private AppboyInAppMessageManager getInAppMessageManager() {
        return AppboyInAppMessageManager.getInstance();
    }

    private void logHtmlInAppMessageClick(IInAppMessage iInAppMessage, Bundle bundle) {
        if (bundle == null || !bundle.containsKey(InAppMessageWebViewClient.QUERY_NAME_BUTTON_ID)) {
            iInAppMessage.logClick();
        } else {
            ((IInAppMessageHtml) iInAppMessage).logButtonClick(bundle.getString(InAppMessageWebViewClient.QUERY_NAME_BUTTON_ID));
        }
    }

    static String parseCustomEventNameFromQueryBundle(Bundle bundle) {
        return bundle.getString("name");
    }

    static AppboyProperties parsePropertiesFromQueryBundle(Bundle bundle) {
        AppboyProperties appboyProperties = new AppboyProperties();
        for (String str : bundle.keySet()) {
            if (!str.equals("name")) {
                String string = bundle.getString(str, (String) null);
                if (!StringUtils.isNullOrBlank(string)) {
                    appboyProperties.addProperty(str, string);
                }
            }
        }
        return appboyProperties;
    }
}
