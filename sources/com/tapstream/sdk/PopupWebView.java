package com.tapstream.sdk;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;

public class PopupWebView {
    final PopupWindow window;
    final WebView wv;

    public static PopupWebView initializeWithActivity(Activity activity) {
        try {
            WebView webView = new WebView(activity.getApplicationContext());
            return new PopupWebView(new PopupWindow(webView, -1, -1), webView);
        } catch (RuntimeException e) {
            Logging.log(6, "RuntimeException thrown creating WebView. This probablymeans you tried to show a popup on a non-ui thread. Stack trace: %s", Log.getStackTraceString(e));
            throw e;
        }
    }

    PopupWebView(PopupWindow popupWindow, WebView webView) {
        this.window = popupWindow;
        this.wv = webView;
    }

    public void showPopupWithUrl(View view, String str, WebViewClient webViewClient) {
        this.window.showAtLocation(view, 0, 0, 0);
        this.wv.loadUrl(str);
        this.wv.setBackgroundColor(0);
        this.wv.setWebViewClient(webViewClient);
    }

    public void showPopupWithMarkup(View view, String str, WebViewClient webViewClient) {
        this.window.showAtLocation(view, 0, 0, 0);
        this.wv.loadDataWithBaseURL("https://tapstream.com/", str, "text/html", (String) null, "https://tapstream.com/");
        this.wv.setBackgroundColor(0);
        this.wv.setWebViewClient(webViewClient);
    }

    public void dismiss() {
        this.window.dismiss();
    }
}
