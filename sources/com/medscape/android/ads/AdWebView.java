package com.medscape.android.ads;

import android.content.Context;
import com.medscape.android.view.CustomWebView;

public class AdWebView {
    private static volatile AdWebView INSTANCE;
    private String className;
    private Context mContext;
    private CustomWebView webview;

    AdWebView(Context context) {
        setmContext(context);
    }

    public static AdWebView getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AdWebView.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AdWebView(context);
                }
            }
        }
        return INSTANCE;
    }

    public CustomWebView getWebview() {
        return this.webview;
    }

    public void setWebview(CustomWebView customWebView) {
        this.webview = customWebView;
    }

    public void destroyWebView() {
        this.webview = null;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public Context getmContext() {
        return this.mContext;
    }

    public void setmContext(Context context) {
        this.mContext = context;
    }
}
