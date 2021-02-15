package com.appboy.ui.inappmessage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.models.IInAppMessage;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.ui.inappmessage.listeners.IInAppMessageWebViewClientListener;
import com.appboy.ui.support.UriUtils;
import java.util.Map;

public class InAppMessageWebViewClient extends WebViewClient {
    private static final String APPBOY_INAPP_MESSAGE_SCHEME = "appboy";
    private static final String AUTHORITY_NAME_CLOSE = "close";
    private static final String AUTHORITY_NAME_CUSTOM_EVENT = "customEvent";
    private static final String AUTHORITY_NAME_NEWSFEED = "feed";
    public static final String JAVASCRIPT_PREFIX = "javascript:";
    public static final String QUERY_NAME_BUTTON_ID = "abButtonId";
    public static final String QUERY_NAME_DEEPLINK = "abDeepLink";
    public static final String QUERY_NAME_EXTERNAL_OPEN = "abExternalOpen";
    private static final String TAG = AppboyLogger.getAppboyLogTag(InAppMessageWebViewClient.class);
    private Context mContext;
    private final IInAppMessage mInAppMessage;
    private IInAppMessageWebViewClientListener mInAppMessageWebViewClientListener;

    public InAppMessageWebViewClient(Context context, IInAppMessage iInAppMessage, IInAppMessageWebViewClientListener iInAppMessageWebViewClientListener) {
        this.mInAppMessageWebViewClientListener = iInAppMessageWebViewClientListener;
        this.mInAppMessage = iInAppMessage;
        this.mContext = context;
    }

    public void onPageFinished(WebView webView, String str) {
        appendBridgeJavascript(webView);
    }

    private void appendBridgeJavascript(WebView webView) {
        String assetFileStringContents = AppboyFileUtils.getAssetFileStringContents(this.mContext.getAssets(), "appboy-html-in-app-message-javascript-component.js");
        if (assetFileStringContents == null) {
            AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(false);
            AppboyLogger.e(TAG, "Failed to get HTML in-app message javascript additions");
            return;
        }
        webView.loadUrl(JAVASCRIPT_PREFIX + assetFileStringContents);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.mInAppMessageWebViewClientListener == null) {
            AppboyLogger.i(TAG, "InAppMessageWebViewClient was given null IInAppMessageWebViewClientListener listener. Returning true.");
            return true;
        } else if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.i(TAG, "InAppMessageWebViewClient.shouldOverrideUrlLoading was given null or blank url. Returning true.");
            return true;
        } else {
            Uri parse = Uri.parse(str);
            Bundle bundleFromUrl = getBundleFromUrl(str);
            if (parse.getScheme().equals(APPBOY_INAPP_MESSAGE_SCHEME)) {
                String authority = parse.getAuthority();
                if (authority.equals(AUTHORITY_NAME_CLOSE)) {
                    this.mInAppMessageWebViewClientListener.onCloseAction(this.mInAppMessage, str, bundleFromUrl);
                } else if (authority.equals("feed")) {
                    this.mInAppMessageWebViewClientListener.onNewsfeedAction(this.mInAppMessage, str, bundleFromUrl);
                } else if (authority.equals(AUTHORITY_NAME_CUSTOM_EVENT)) {
                    this.mInAppMessageWebViewClientListener.onCustomEventAction(this.mInAppMessage, str, bundleFromUrl);
                }
                return true;
            }
            this.mInAppMessageWebViewClientListener.onOtherUrlAction(this.mInAppMessage, str, bundleFromUrl);
            return true;
        }
    }

    static Bundle getBundleFromUrl(String str) {
        Bundle bundle = new Bundle();
        if (StringUtils.isNullOrBlank(str)) {
            return bundle;
        }
        Map<String, String> queryParameters = UriUtils.getQueryParameters(Uri.parse(str));
        for (String next : queryParameters.keySet()) {
            bundle.putString(next, queryParameters.get(next));
        }
        return bundle;
    }
}
