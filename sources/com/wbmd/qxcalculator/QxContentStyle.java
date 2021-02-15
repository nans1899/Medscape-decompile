package com.wbmd.qxcalculator;

import android.util.Base64;
import android.webkit.WebView;

public class QxContentStyle {
    private static QxContentStyle instance;
    private QxContentStyleProvider provider;

    private QxContentStyle() {
    }

    public static synchronized QxContentStyle getInstance() {
        QxContentStyle qxContentStyle;
        synchronized (QxContentStyle.class) {
            if (instance == null) {
                instance = new QxContentStyle();
            }
            qxContentStyle = instance;
        }
        return qxContentStyle;
    }

    public void setProvider(QxContentStyleProvider qxContentStyleProvider) {
        this.provider = qxContentStyleProvider;
    }

    public QxContentStyleProvider getProvider() {
        return this.provider;
    }

    public void applyCSSStyle(WebView webView) {
        try {
            if (getProvider() != null) {
                String encodeToString = Base64.encodeToString(getProvider().moreInfoCSSString().getBytes(), 2);
                webView.loadUrl("javascript:(function() {var parent = document.getElementsByTagName('head').item(0);var style = document.createElement('style');style.type = 'text/css';style.innerHTML = window.atob('" + encodeToString + "');parent.appendChild(style)})()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
