package com.medscape.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import androidx.appcompat.app.ActionBar;
import androidx.core.net.MailTo;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;

public class LinkBrowserActivity extends BaseActivity {
    private ImageView mBackView;
    /* access modifiers changed from: private */
    public boolean mDidPageLoad = false;
    /* access modifiers changed from: private */
    public boolean mDidPageLoadWithError = false;
    /* access modifiers changed from: private */
    public MedscapeException mException;
    private ImageView mForwardView;
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
        setContentView((int) R.layout.activity_link_browser);
        this.mRoot = findViewById(R.id.root);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setUpNavigationButtons();
        highlightNavigationButtons();
        View findViewById = findViewById(R.id.progressBar);
        this.mProgressBar = findViewById;
        if (findViewById != null) {
            findViewById.setVisibility(0);
        }
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebview = webView;
        webView.setWebViewClient(new InsideWebViewClient());
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.mUrl = extras.getString("com.medscape.android.EXTRA_WEBVIEW_URL");
            loadUrl();
        }
    }

    private void setUpNavigationButtons() {
        ImageView imageView = (ImageView) findViewById(R.id.prev);
        this.mBackView = imageView;
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (LinkBrowserActivity.this.mWebview != null && LinkBrowserActivity.this.mWebview.canGoBack()) {
                        LinkBrowserActivity.this.mWebview.goBack();
                    }
                }
            });
        }
        ImageView imageView2 = (ImageView) findViewById(R.id.next);
        this.mForwardView = imageView2;
        if (imageView2 != null) {
            imageView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (LinkBrowserActivity.this.mWebview != null && LinkBrowserActivity.this.mWebview.canGoForward()) {
                        LinkBrowserActivity.this.mWebview.goForward();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void highlightNavigationButtons() {
        WebView webView = this.mWebview;
        if (webView != null) {
            if (this.mBackView != null) {
                if (webView.canGoBack()) {
                    this.mBackView.setColorFilter(getResources().getColor(R.color.medscape_blue));
                } else {
                    this.mBackView.setColorFilter(getResources().getColor(R.color.link_browser_default_nav_color));
                }
            }
            if (this.mForwardView == null) {
                return;
            }
            if (this.mWebview.canGoForward()) {
                this.mForwardView.setColorFilter(getResources().getColor(R.color.medscape_blue));
            } else {
                this.mForwardView.setColorFilter(getResources().getColor(R.color.link_browser_default_nav_color));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: private */
    public void loadUrl() {
        WebView webView;
        String str = this.mUrl;
        if (str != null && !str.equalsIgnoreCase("") && (webView = this.mWebview) != null) {
            webView.loadUrl(this.mUrl);
        }
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!Util.isOnline(LinkBrowserActivity.this)) {
                MedscapeException unused = LinkBrowserActivity.this.mException = new MedscapeException(LinkBrowserActivity.this.getResources().getString(R.string.internet_required));
                LinkBrowserActivity.this.mException.showSnackBar(LinkBrowserActivity.this.mRoot, 0, (String) null, (View.OnClickListener) null);
                return true;
            } else if (str == null) {
                return false;
            } else {
                if (str.startsWith(MailTo.MAILTO_SCHEME)) {
                    Uri.parse(str);
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    LinkBrowserActivity linkBrowserActivity = LinkBrowserActivity.this;
                    if (Util.isEmailConfigured(linkBrowserActivity, intent)) {
                        LinkBrowserActivity.this.startActivity(intent);
                    } else if (!linkBrowserActivity.isFinishing()) {
                        DialogUtil.showAlertDialog(24, (String) null, linkBrowserActivity.getString(R.string.alert_show_email_configure_message), linkBrowserActivity).show();
                    }
                    return true;
                }
                webView.loadUrl(str);
                return true;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            String str3;
            boolean unused = LinkBrowserActivity.this.mDidPageLoadWithError = true;
            LinkBrowserActivity.this.mWebview.setVisibility(8);
            if (!Util.isOnline(LinkBrowserActivity.this) || i == -6 || i == -5) {
                str3 = LinkBrowserActivity.this.getResources().getString(R.string.internet_required);
            } else {
                str3 = LinkBrowserActivity.this.getResources().getString(R.string.unknown_error);
            }
            String string = LinkBrowserActivity.this.getResources().getString(R.string.retry);
            MedscapeException unused2 = LinkBrowserActivity.this.mException = new MedscapeException(str3);
            LinkBrowserActivity.this.mException.showSnackBar(LinkBrowserActivity.this.mRoot, -2, string, new View.OnClickListener() {
                public void onClick(View view) {
                    boolean unused = LinkBrowserActivity.this.mDidPageLoadWithError = false;
                    if (LinkBrowserActivity.this.mProgressBar != null) {
                        LinkBrowserActivity.this.mProgressBar.setVisibility(0);
                    }
                    LinkBrowserActivity.this.loadUrl();
                }
            });
        }

        public void onPageFinished(WebView webView, String str) {
            boolean unused = LinkBrowserActivity.this.mDidPageLoad = true;
            if (LinkBrowserActivity.this.mProgressBar != null) {
                LinkBrowserActivity.this.mProgressBar.setVisibility(8);
            }
            if (!LinkBrowserActivity.this.mDidPageLoadWithError) {
                if (LinkBrowserActivity.this.mWebview != null) {
                    LinkBrowserActivity.this.mWebview.setVisibility(0);
                }
                if (LinkBrowserActivity.this.mException != null) {
                    LinkBrowserActivity.this.mException.dismissSnackBar();
                }
            }
            LinkBrowserActivity.this.highlightNavigationButtons();
        }
    }
}
