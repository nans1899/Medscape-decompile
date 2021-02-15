package com.medscape.android.activity.formulary;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.util.Util;
import java.util.Map;

public class TierDescription extends AbstractBreadcrumbNavigableActivity {
    private static final String TAG = "TierDescription";
    private String mBrandId;
    private String mFileName = "tier_info.html";
    private String mTitle;
    private WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.formulary_webview_page);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String str = "";
            this.mTitle = extras.containsKey("TITLE") ? extras.getString("TITLE") : str;
            if (extras.containsKey(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY)) {
                str = extras.getString(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY);
            }
            this.mBrandId = str;
        }
        setTitle(this.mTitle);
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebView = webView;
        webView.getSettings().setUserAgentString(Util.addUserAgent(this.mWebView, this));
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.setScrollBarStyle(33554432);
        this.mWebView.setWebViewClient(new InsideWebViewClient());
        WebView webView2 = this.mWebView;
        webView2.loadUrl("file://" + FileHelper.getDataDirectory(this) + "/Medscape/" + this.mFileName);
        sendBIPing();
    }

    public void sendBIPing() {
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "formulary", "view", "" + this.mBrandId, "tierdescript", (Map<String, Object>) null);
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri.parse(str);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
