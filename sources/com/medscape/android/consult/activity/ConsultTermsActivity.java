package com.medscape.android.consult.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.LinkBrowserActivity;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import java.util.Map;

public class ConsultTermsActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public TextView mAcceptItem;
    /* access modifiers changed from: private */
    public String mConsultDeeplinkUrl;
    /* access modifiers changed from: private */
    public boolean mDidPageLoad = false;
    /* access modifiers changed from: private */
    public boolean mDidPageLoadWithError = false;
    /* access modifiers changed from: private */
    public MedscapeException mException;
    /* access modifiers changed from: private */
    public boolean mGotoProfile;
    private boolean mHideAccept = false;
    /* access modifiers changed from: private */
    public boolean mIsAcceptEnabled = false;
    /* access modifiers changed from: private */
    public View mProgressBar;
    /* access modifiers changed from: private */
    public View mRoot;
    private String mUrl;
    /* access modifiers changed from: private */
    public WebView mWebview;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle extras;
        super.onCreate(bundle);
        setContentView((int) R.layout.consult_terms_layout);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            supportActionBar.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
            supportActionBar.setLogo((Drawable) null);
        }
        this.mRoot = findViewById(R.id.root);
        View findViewById = findViewById(R.id.progressBar);
        this.mProgressBar = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebview = webView;
        webView.setWebViewClient(new InsideWebViewClient());
        WebSettings settings = this.mWebview.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        Intent intent = getIntent();
        if (!(intent == null || (extras = intent.getExtras()) == null)) {
            this.mUrl = extras.getString("com.medscape.android.EXTRA_WEBVIEW_URL");
            this.mConsultDeeplinkUrl = extras.getString(Constants.EXTRA_CONSULT_DEEPLINK_URL);
            this.mGotoProfile = extras.getBoolean(Constants.CONSULT_GO_PROFILE);
            loadUrl();
        }
        this.mIsAcceptEnabled = false;
    }

    private void loadUrl() {
        WebView webView;
        String str = this.mUrl;
        if (str != null && !str.equalsIgnoreCase("") && (webView = this.mWebview) != null) {
            webView.loadUrl(this.mUrl);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consult_terms, menu);
        TextView textView = new TextView(this);
        this.mAcceptItem = textView;
        textView.setText("ACCEPT");
        this.mAcceptItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OmnitureManager.get().trackModule(ConsultTermsActivity.this, "consult", "consult-intro", "accept", (Map<String, Object>) null);
                CapabilitiesManager instance = CapabilitiesManager.getInstance(ConsultTermsActivity.this);
                instance.updateConsultLastAcceptedEulaVersionToCurrent();
                AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(ConsultTermsActivity.this, AppboyConstants.APPBOY_EVENT_CONSULT_VIEWED);
                if (ConsultTermsActivity.this.mGotoProfile) {
                    ConsultTermsActivity.this.mProgressBar.setVisibility(0);
                    ConsultTermsActivity consultTermsActivity = ConsultTermsActivity.this;
                    instance.startConsultProfile(consultTermsActivity, consultTermsActivity, consultTermsActivity.mProgressBar);
                    return;
                }
                OmnitureManager.get().trackModule(ConsultTermsActivity.this, "consult", Constants.OMNITURE_MMODULE_REFMENU, "consult", (Map<String, Object>) null);
                String str = OmnitureManager.get().getmCurrentPageName();
                if (str == null || !str.contains("drug/view")) {
                    OmnitureManager.get().markModule("consultnav", "tap", (Map<String, Object>) null);
                } else {
                    OmnitureManager.get().markModule("housead", "drvr", (Map<String, Object>) null);
                }
                Intent intent = new Intent(ConsultTermsActivity.this, ConsultTimelineActivity.class);
                if (StringUtil.isNotEmpty(ConsultTermsActivity.this.mConsultDeeplinkUrl)) {
                    intent.putExtra(Constants.EXTRA_CONSULT_DEEPLINK_URL, ConsultTermsActivity.this.mConsultDeeplinkUrl);
                }
                ConsultTermsActivity.this.startActivity(intent);
                ConsultTermsActivity.this.finish();
            }
        });
        this.mAcceptItem.setPadding(5, 0, 30, 0);
        this.mAcceptItem.setTextSize(14.0f);
        menu.add(0, 20, 1, "ACCEPT").setActionView(this.mAcceptItem).setShowAsAction(2);
        this.mAcceptItem.setEnabled(this.mIsAcceptEnabled);
        if (!this.mIsAcceptEnabled) {
            this.mAcceptItem.setTextColor(ContextCompat.getColor(this, R.color.separator_color));
        } else {
            this.mAcceptItem.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        TextView textView = this.mAcceptItem;
        if (textView == null) {
            return true;
        }
        if (this.mHideAccept) {
            textView.setVisibility(4);
            return true;
        }
        textView.setVisibility(0);
        return true;
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!Util.isOnline(ConsultTermsActivity.this)) {
                MedscapeException unused = ConsultTermsActivity.this.mException = new MedscapeException(ConsultTermsActivity.this.getResources().getString(R.string.internet_required));
                ConsultTermsActivity.this.mException.showSnackBar(ConsultTermsActivity.this.mRoot, 0, (String) null, new View.OnClickListener() {
                    public void onClick(View view) {
                        ConsultTermsActivity.this.retryNetwork();
                    }
                });
                return true;
            } else if (!ConsultTermsActivity.this.mDidPageLoad) {
                return false;
            } else {
                Intent intent = new Intent(ConsultTermsActivity.this, LinkBrowserActivity.class);
                intent.putExtra("com.medscape.android.EXTRA_WEBVIEW_URL", str);
                ConsultTermsActivity.this.startActivity(intent);
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            String str3;
            boolean unused = ConsultTermsActivity.this.mDidPageLoadWithError = true;
            ConsultTermsActivity.this.mWebview.setVisibility(8);
            if (!Util.isOnline(ConsultTermsActivity.this) || i == -6 || i == -5) {
                str3 = ConsultTermsActivity.this.getResources().getString(R.string.internet_required);
            } else {
                str3 = ConsultTermsActivity.this.getResources().getString(R.string.unknown_error);
            }
            String string = ConsultTermsActivity.this.getResources().getString(R.string.retry);
            MedscapeException unused2 = ConsultTermsActivity.this.mException = new MedscapeException(str3);
            ConsultTermsActivity.this.mException.showSnackBar(ConsultTermsActivity.this.mRoot, -2, string, new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultTermsActivity.this.retryNetwork();
                }
            });
        }

        public void onPageFinished(WebView webView, String str) {
            boolean unused = ConsultTermsActivity.this.mDidPageLoad = true;
            if (ConsultTermsActivity.this.mProgressBar != null) {
                ConsultTermsActivity.this.mProgressBar.setVisibility(8);
            }
            if (!ConsultTermsActivity.this.mDidPageLoadWithError) {
                if (ConsultTermsActivity.this.mWebview != null) {
                    ConsultTermsActivity.this.mWebview.setVisibility(0);
                }
                if (ConsultTermsActivity.this.mAcceptItem != null) {
                    ConsultTermsActivity.this.mAcceptItem.setTextColor(ContextCompat.getColor(ConsultTermsActivity.this, R.color.white));
                    boolean unused2 = ConsultTermsActivity.this.mIsAcceptEnabled = true;
                    ConsultTermsActivity.this.mAcceptItem.setEnabled(ConsultTermsActivity.this.mIsAcceptEnabled);
                }
                if (ConsultTermsActivity.this.mException != null) {
                    ConsultTermsActivity.this.mException.dismissSnackBar();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void retryNetwork() {
        this.mDidPageLoadWithError = false;
        View view = this.mProgressBar;
        if (view != null) {
            view.setVisibility(0);
        }
        loadUrl();
        this.mDidPageLoad = false;
    }
}
