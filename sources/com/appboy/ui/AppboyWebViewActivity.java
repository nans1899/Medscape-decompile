package com.appboy.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.appboy.enums.Channel;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.actions.ActionFactory;
import com.appboy.ui.activities.AppboyBaseActivity;

public class AppboyWebViewActivity extends AppboyBaseActivity {
    /* access modifiers changed from: private */
    public static final String TAG = AppboyLogger.getAppboyLogTag(AppboyWebViewActivity.class);
    @Deprecated
    public static final String URL_EXTRA = "url";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(2);
        requestWindowFeature(5);
        getWindow().setFlags(16777216, 16777216);
        setContentView(R.layout.com_appboy_webview_activity);
        setProgressBarVisibility(true);
        WebView webView = (WebView) findViewById(R.id.com_appboy_webview_activity_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setPluginState(WebSettings.PluginState.OFF);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        webView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                if (i < 100) {
                    AppboyWebViewActivity.this.setProgressBarVisibility(true);
                } else {
                    AppboyWebViewActivity.this.setProgressBarVisibility(false);
                }
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                AppboyWebViewActivity.this.startActivity(intent);
            }
        });
        webView.getSettings().setCacheMode(2);
        webView.setLayerType(2, (Paint) null);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                try {
                    if (!AppboyFileUtils.REMOTE_SCHEMES.contains(Uri.parse(str).getScheme())) {
                        ActionFactory.createUriActionFromUrlString(str, AppboyWebViewActivity.this.getIntent().getExtras(), false, Channel.UNKNOWN).execute(webView.getContext());
                        AppboyWebViewActivity.this.finish();
                        return true;
                    }
                } catch (Exception e) {
                    String access$000 = AppboyWebViewActivity.TAG;
                    AppboyLogger.i(access$000, "Unexpected exception while processing url " + str + ". Passing url back to WebView.", (Throwable) e);
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("url")) {
            webView.loadUrl(extras.getString("url"));
        }
    }
}
