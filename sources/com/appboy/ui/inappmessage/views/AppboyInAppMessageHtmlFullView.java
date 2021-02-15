package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.jsinterface.AppboyInAppMessageHtmlJavascriptInterface;

public class AppboyInAppMessageHtmlFullView extends AppboyInAppMessageHtmlBaseView {
    public static final String APPBOY_BRIDGE_PREFIX = "appboyInternalBridge";
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyInAppMessageHtmlFullView.class);
    private WebView mMessageWebView;

    public AppboyInAppMessageHtmlFullView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WebView getMessageWebView() {
        if (this.mMessageWebView == null) {
            AppboyInAppMessageWebView appboyInAppMessageWebView = (AppboyInAppMessageWebView) findViewById(R.id.com_appboy_inappmessage_html_full_webview);
            this.mMessageWebView = appboyInAppMessageWebView;
            if (appboyInAppMessageWebView != null) {
                WebSettings settings = appboyInAppMessageWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);
                settings.setDisplayZoomControls(false);
                this.mMessageWebView.setLayerType(2, (Paint) null);
                this.mMessageWebView.setBackgroundColor(0);
                this.mMessageWebView.setWebChromeClient(new WebChromeClient() {
                    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                        String access$000 = AppboyInAppMessageHtmlFullView.TAG;
                        AppboyLogger.d(access$000, "Html In-app log. Line: " + consoleMessage.lineNumber() + ". SourceId: " + consoleMessage.sourceId() + ". Log Level: " + consoleMessage.messageLevel() + ". Message: " + consoleMessage.message());
                        return true;
                    }
                });
                this.mMessageWebView.addJavascriptInterface(new AppboyInAppMessageHtmlJavascriptInterface(getContext()), APPBOY_BRIDGE_PREFIX);
            }
        }
        return this.mMessageWebView;
    }
}
