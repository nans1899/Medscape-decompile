package com.adobe.mobile;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import com.adobe.mobile.MessageFullScreen;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

final class MessageTargetExperienceUIFullScreen extends MessageFullScreen {
    private static final String URL_QUERY_KEY_AT_PREVIEW_PARAMS = "at_preview_params=";

    MessageTargetExperienceUIFullScreen() {
        setShouldSendAnalyticsHitsOnInteractions(false);
    }

    /* access modifiers changed from: protected */
    public MessageFullScreen.MessageFullScreenRunner getNewMessageFullScreenRunner(MessageFullScreen messageFullScreen) {
        return new TargetPreviewFullScreenRunner(messageFullScreen);
    }

    private static class TargetPreviewFullScreenRunner extends MessageFullScreen.MessageFullScreenRunner {
        protected TargetPreviewFullScreenRunner(MessageFullScreen messageFullScreen) {
            super(messageFullScreen);
        }

        /* access modifiers changed from: protected */
        public WebView createWebView() {
            WebView createWebView = super.createWebView();
            WebSettings settings = createWebView.getSettings();
            settings.setDomStorageEnabled(true);
            File cacheDirectory = StaticMethods.getCacheDirectory();
            if (cacheDirectory != null) {
                settings.setDatabasePath(cacheDirectory.getPath());
                settings.setDatabaseEnabled(true);
            }
            return createWebView;
        }
    }

    private static class TargetPreviewExperienceUIWebviewClient extends MessageFullScreen.MessageFullScreenWebViewClient {
        protected TargetPreviewExperienceUIWebviewClient(MessageFullScreen messageFullScreen) {
            super(messageFullScreen);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            super.shouldOverrideUrlLoading(webView, str);
            if (!str.startsWith("adbinapp")) {
                Toast.makeText(webView.getContext(), "Error while reading the response from the Experience UI! (Response mal-formed)", 0).show();
                dismissMessage(webView);
            } else if (str.contains("confirm")) {
                int indexOf = str.indexOf(MessageTargetExperienceUIFullScreen.URL_QUERY_KEY_AT_PREVIEW_PARAMS);
                if (indexOf >= 0) {
                    int indexOf2 = str.indexOf(38, indexOf);
                    if (indexOf2 < 0) {
                        indexOf2 = str.length();
                    }
                    String substring = str.substring(indexOf + 18, indexOf2);
                    if (!substring.isEmpty()) {
                        try {
                            TargetPreviewManager.getInstance().setPreviewParams(URLDecoder.decode(substring, "UTF-8"));
                        } catch (UnsupportedEncodingException e) {
                            StaticMethods.logDebugFormat("Could not decode the Target Preview parameters (%s)", e);
                        }
                    }
                }
                String previewRestartDeeplink = TargetPreviewManager.getInstance().getPreviewRestartDeeplink();
                if (previewRestartDeeplink != null && !previewRestartDeeplink.isEmpty()) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(previewRestartDeeplink));
                        this.message.messageFullScreenActivity.startActivity(intent);
                    } catch (Exception e2) {
                        StaticMethods.logDebugFormat("Messages - unable to launch restart deeplink intent from Target Preview message (%s)", e2.getMessage());
                    }
                }
            } else if (str.contains("cancel")) {
                TargetPreviewManager.getInstance().disableTargetPreviewMode();
            }
            Messages.setCurrentMessage((Message) null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public MessageFullScreen.MessageFullScreenWebViewClient getMessageFullScreenWebViewClient() {
        return new TargetPreviewExperienceUIWebviewClient(this);
    }

    /* access modifiers changed from: protected */
    public void show() {
        this.html = TargetPreviewManager.getInstance().getTargetPreviewExperienceUIHtml();
        if (this.html == null) {
            StaticMethods.logErrorFormat("Could not display the Target Preview Experience UI (no html payload found!)", new Object[0]);
        } else {
            super.show();
        }
    }
}
