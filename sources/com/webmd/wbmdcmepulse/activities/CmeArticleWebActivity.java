package com.webmd.wbmdcmepulse.activities;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.core.app.ActivityCompat;
import androidx.core.net.MailTo;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.SavedArticle;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.util.HashMap;
import java.util.List;

public class CmeArticleWebActivity extends CmeBaseActivity {
    private static final Integer REQUEST_EXTERNAL_STORAGE = 220;
    /* access modifiers changed from: private */
    public static final String TAG = CmeArticleWebActivity.class.getSimpleName();
    private String articleId;
    private String assetId;
    /* access modifiers changed from: protected */
    public String mArticleId;
    protected String mArticleTitle;
    private String mDownloadUrl;
    /* access modifiers changed from: private */
    public View mErrorView;
    private String mFeedUrl;
    private String mReferringLink;
    private String mReferringModule;
    private String mReferringPage;
    private String mReferringQuery;
    private View mRootLayout;
    private SavedArticle mSavedArticle;
    private String mUpTargetActivity;
    protected WebView mWebView;
    /* access modifiers changed from: private */
    public View progressBar;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_article_web);
        performLoginIfNeeded();
        initializeToolBar();
        this.mRootLayout = findViewById(R.id.root_layout);
        Intent intent = getIntent();
        this.articleId = intent.getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
        this.assetId = intent.getStringExtra(Constants.BUNDLE_KEY_ASSET_ID);
        this.mSavedArticle = (SavedArticle) intent.getParcelableExtra(Constants.BUNDLE_KEY_SAVED_ARTICLE);
        this.mReferringPage = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_PAGE);
        this.mReferringModule = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_MODULE);
        this.mReferringLink = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_LINK);
        this.mReferringQuery = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_QUERY);
        this.mFeedUrl = intent.getStringExtra(Constants.BUNDLE_KEY_FEED_URL);
        this.mArticleTitle = intent.getStringExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE);
        this.mUpTargetActivity = intent.getStringExtra(Constants.RETURN_ACTIVITY);
        loadContent();
    }

    private void performLoginIfNeeded() {
        if (this.mUserProfile == null) {
            try {
                logInUser(new ICallbackEvent<Boolean, CMEPulseException>() {
                    public void onError(CMEPulseException cMEPulseException) {
                        Trace.d(CmeArticleWebActivity.TAG, cMEPulseException.getMessage());
                        CmeArticleWebActivity.this.returnToLandingActivity();
                    }

                    public void onCompleted(Boolean bool) {
                        if (bool.booleanValue()) {
                            Trace.d(CmeArticleWebActivity.TAG, "Logged in user from keychain");
                            return;
                        }
                        Trace.d(CmeArticleWebActivity.TAG, "Could not in user from keychain");
                        CmeArticleWebActivity.this.returnToLandingActivity();
                    }
                });
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
                returnToLandingActivity();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        checkIfSaved(this.mFeedUrl, this.mArticleId);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        executeJavascript("javascript:document.querySelector('audio').pause();", $$Lambda$CmeArticleWebActivity$LEJTxwG4ln0rw8X2cTlOVAR7DUs.INSTANCE);
        super.onPause();
    }

    public void onBackPressed() {
        this.mWebView.stopLoading();
        if (this.mWebView.getUrl() == null || !this.mWebView.canGoBack()) {
            finish();
        } else {
            this.mWebView.goBack();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return setMenuItems(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_save) {
            saveFieldsToSaveArticle();
            SavedArticle savedArticle = this.mSavedArticle;
            if (savedArticle != null) {
                onSaveButtonClicked(this, savedArticle, this.mArticleId, savedArticle.maxCredits, this.mFeedUrl);
            }
            return true;
        } else if (itemId == R.id.action_share) {
            shareArticle(this, this.mFeedUrl, getArticleTitle());
            return true;
        } else if (itemId == R.id.action_activity_tracker) {
            launchCMETracker(this);
            return true;
        } else {
            if (itemId == 16908332) {
                onBackPressed();
            }
            return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: protected */
    public void loadContent() {
        int indexOf;
        setUpDefaultActionBar("CME Education", true);
        if (!Extensions.isStringNullOrEmpty(this.mFeedUrl)) {
            if (!Extensions.isStringNullOrEmpty(this.mArticleId)) {
                List<String> pathSegments = Uri.parse(this.mFeedUrl).getPathSegments();
                if (pathSegments.contains("viewarticle") && pathSegments.size() > (indexOf = pathSegments.indexOf("viewarticle") + 1)) {
                    String str = pathSegments.get(indexOf);
                    this.mArticleId = str;
                    if (str.contains("_")) {
                        this.mArticleId = this.mArticleId.split("_")[0];
                    }
                }
            } else {
                this.mArticleId = this.articleId;
            }
        } else if (!Extensions.isStringNullOrEmpty(this.articleId)) {
            this.mFeedUrl = Utilities.generateEnvironment(this, "https://www%s.medscape.org/viewarticle/") + this.articleId;
            this.mArticleId = this.articleId;
        } else {
            finish();
        }
        initializeViews(this.mFeedUrl);
        initCookies(this.mFeedUrl);
        loadWebPage(this.mFeedUrl);
        trackOmniture();
    }

    private void saveFieldsToSaveArticle() {
        getArticleTitle();
        if (this.mSavedArticle == null && this.mUserProfile != null && this.mUserProfile.getBasicProfile() != null && !Extensions.isStringNullOrEmpty(this.mUserProfile.getBasicProfile().getUserId())) {
            SavedArticle savedArticle = new SavedArticle();
            this.mSavedArticle = savedArticle;
            savedArticle.userId = this.mUserProfile.getBasicProfile().getUserId();
            this.mSavedArticle.articleId = this.mArticleId;
            this.mSavedArticle.title = this.mArticleTitle;
        }
        SavedArticle savedArticle2 = this.mSavedArticle;
        if (savedArticle2 != null) {
            if (!Extensions.isStringNullOrEmpty(savedArticle2.creditType) && this.mSavedArticle.creditType.length() > 2 && this.mSavedArticle.creditType.contains("[")) {
                SavedArticle savedArticle3 = this.mSavedArticle;
                savedArticle3.creditType = Utilities.removeJsonFromCreditType(savedArticle3.creditType);
            }
            if (!Extensions.isStringNullOrEmpty(this.mSavedArticle.maxCredits) && !this.mSavedArticle.maxCredits.contains("Credits")) {
                StringBuilder sb = new StringBuilder();
                SavedArticle savedArticle4 = this.mSavedArticle;
                sb.append(savedArticle4.maxCredits);
                sb.append(" Credits");
                savedArticle4.maxCredits = sb.toString();
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getArticleTitle() {
        String str;
        if (!StringExtensions.isNullOrEmpty(this.mArticleTitle)) {
            str = this.mArticleTitle;
        } else {
            SavedArticle savedArticle = this.mSavedArticle;
            if (savedArticle != null) {
                str = savedArticle.title;
            } else {
                str = this.mWebView.getTitle();
            }
        }
        this.mArticleTitle = str;
        return str;
    }

    /* access modifiers changed from: protected */
    public void initializeViews(String str) {
        this.mWebView = (WebView) findViewById(R.id.web_view);
        this.mErrorView = findViewById(R.id.noNetworkView);
        this.progressBar = findViewById(R.id.progress_bar);
        ((Button) findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                CmeArticleWebActivity.this.lambda$initializeViews$1$CmeArticleWebActivity(this.f$1, view);
            }
        });
        setUserAgent();
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.setWebViewClient(new InsideWebViewClient());
        this.mWebView.setDownloadListener(new DownloadListener() {
            public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                CmeArticleWebActivity.this.lambda$initializeViews$2$CmeArticleWebActivity(str, str2, str3, str4, j);
            }
        });
    }

    public /* synthetic */ void lambda$initializeViews$1$CmeArticleWebActivity(String str, View view) {
        this.mErrorView.setVisibility(8);
        loadWebPage(str);
    }

    public /* synthetic */ void lambda$initializeViews$2$CmeArticleWebActivity(String str, String str2, String str3, String str4, long j) {
        if (Build.VERSION.SDK_INT < 23) {
            downloadFile(str);
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            downloadFile(str);
        } else {
            this.mDownloadUrl = str;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, REQUEST_EXTERNAL_STORAGE.intValue());
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == REQUEST_EXTERNAL_STORAGE.intValue() && iArr[0] == 0 && !this.mDownloadUrl.isEmpty()) {
            downloadFile(this.mDownloadUrl);
        }
    }

    private void downloadFile(String str) {
        Utilities.downloadFile(this, this.mRootLayout, str);
    }

    private void setUserAgent() {
        String str;
        String userAgentString = this.mWebView.getSettings().getUserAgentString();
        try {
            str = getPackageManager().getPackageInfo(new ComponentName(this, CmeArticleWebActivity.class).getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            str = "1.0";
        }
        this.mWebView.getSettings().setUserAgentString(userAgentString + getUserAgentAppName() + str);
    }

    /* access modifiers changed from: protected */
    public void loadWebPage(String str) {
        if (Utilities.isNetworkAvailable(this)) {
            if (this.mErrorView.getVisibility() != 8) {
                this.mErrorView.setVisibility(8);
            }
            this.mWebView.setVisibility(0);
            this.mWebView.setWebViewClient(new InsideWebViewClient());
            this.mWebView.loadUrl(str);
            return;
        }
        this.mErrorView.setVisibility(0);
    }

    private void executeJavascript(String str, ValueCallback valueCallback) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.mWebView.evaluateJavascript(str, valueCallback);
        } else {
            this.mWebView.loadUrl(str);
        }
    }

    /* access modifiers changed from: protected */
    public void initCookies(String str) {
        CookieSyncManager instance = CookieSyncManager.getInstance();
        CookieManager instance2 = CookieManager.getInstance();
        instance2.setAcceptCookie(true);
        try {
            String cookieString = Utilities.getCookieString(this);
            Uri parse = Uri.parse(str);
            parse.getHost();
            parse.getHost();
            String[] strArr = {"domain=medscape.com;", "domain=medscape.org;"};
            String[] strArr2 = {"medscape.com", "medscape.org"};
            String[] split = cookieString.split(";\\s*");
            for (int i = 0; i < split.length; i++) {
                for (int i2 = 0; i2 < 2; i2++) {
                    String str2 = strArr2[i2];
                    instance2.setCookie(str2, split[i] + "; " + strArr[i2]);
                }
            }
            instance.sync();
        } catch (Exception unused) {
            Trace.e(TAG, "Error getting session cookie");
        }
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith(MailTo.MAILTO_SCHEME)) {
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse(str));
                try {
                    CmeArticleWebActivity.this.startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException unused) {
                    Trace.e(CmeArticleWebActivity.TAG, "Error not found email client");
                    return true;
                }
            } else {
                if (str.endsWith(".pdf")) {
                    webView.loadUrl("https://docs.google.com/viewer?url=" + str);
                }
                return false;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            CmeArticleWebActivity.this.progressBar.setVisibility(0);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            webView.setVisibility(8);
            CmeArticleWebActivity.this.mErrorView.setVisibility(0);
        }

        public void onPageFinished(WebView webView, String str) {
            if (str != null) {
                try {
                    if (!str.contains("about:blank")) {
                        webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                    }
                } catch (Exception e) {
                    Trace.e(CmeArticleWebActivity.TAG, e.getMessage());
                }
            }
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() {document.getElementById('menuContainer').style.display='none';})()");
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() {document.getElementById('menuList').style.display='none';})()");
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() {document.getElementById('medscapeheadercontainer').style.display='none';})()");
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() {document.getElementById('article_tools').style.display='none';})()");
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() {document.getElementById('legal_block').style.display='none';})()");
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() { document.getElementsByClassName('supporting_links')[0].style.display='none'; })()");
            CmeArticleWebActivity.this.mWebView.loadUrl("javascript:(function() {document.getElementById('footercontents').style.display='none';})()");
            CmeArticleWebActivity.this.progressBar.setVisibility(8);
        }
    }

    private void trackOmniture() {
        if (!Extensions.isStringNullOrEmpty(this.mArticleId)) {
            String format = String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId});
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.assetId, format));
            if (Extensions.isStringNullOrEmpty(this.mReferringPage)) {
                String str = this.mReferringModule;
                if (str == null || !str.equals("save")) {
                    WBMDOmnitureManager.sendPageView(format, hashMap, (WBMDOmnitureModule) null);
                } else {
                    WBMDOmnitureManager.sendPageView(format, hashMap, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, (String) null));
                }
            } else if (this.mReferringPage.contains("activitytracker")) {
                WBMDOmnitureManager.sendPageView(format, hashMap, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, String.format(this.mReferringPage, new Object[0])));
            } else if (this.mReferringPage.contains("browse-search")) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("wapp.querytext", this.mReferringQuery);
                hashMap2.putAll(hashMap);
                WBMDOmnitureManager.sendPageView(format, hashMap2, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, String.format(this.mReferringPage, new Object[0])));
            } else {
                WBMDOmnitureManager.sendPageView(format, hashMap, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, this.mReferringPage));
            }
        }
    }
}
