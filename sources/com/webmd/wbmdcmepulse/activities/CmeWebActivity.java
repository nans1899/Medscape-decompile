package com.webmd.wbmdcmepulse.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.Map;

public class CmeWebActivity extends CmeBaseActivity {
    private static final String TAG = CmeWebActivity.class.getSimpleName();
    private Context mContext;
    /* access modifiers changed from: private */
    public View mErrorLayout;
    private String mReturnActivity;
    private UserProfile mUserProfile;
    private WebView webView;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setContentView(R.layout.cme_activity_web);
        initializeToolBar();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mUserProfile = (UserProfile) extras.getParcelable("user_profile");
        }
        Uri data = getIntent().getData();
        if (data == null) {
            setUpWebView();
        } else if (!data.toString().contains("requirements")) {
            setUpWebView();
        }
    }

    private void trackOmniture(Intent intent) {
        if (intent != null && intent.getStringExtra(Constants.WEB_VIEW_TITLE_KEY) != null && intent.getStringExtra(Constants.WEB_VIEW_TITLE_KEY).equals("Certificate")) {
            WBMDOmnitureManager.sendPageView(OmnitureData.PAGE_NAME_CERTIFICATE_VIEW, (Map<String, String>) null, (WBMDOmnitureModule) null);
        }
    }

    private void setUpWebView() {
        final String str;
        String str2;
        String str3;
        ActionBar supportActionBar = getSupportActionBar();
        Intent intent = getIntent();
        if (intent.getData() != null) {
            str = intent.getData().getEncodedPath();
            if (str.contains("abstract")) {
                str = str.replace("/medline/abstract/", Utilities.generateEnvironment(this, "http://www%s.medscape.org/medline/abstract/"));
                if (supportActionBar != null) {
                    supportActionBar.setDisplayHomeAsUpEnabled(true);
                    supportActionBar.setTitle((CharSequence) "Abstract");
                }
            } else if (intent.getData().getScheme() != null && intent.getData().getScheme().startsWith("abstract")) {
                str = intent.getData().toString().replace("abstract", "http");
                if (supportActionBar != null) {
                    supportActionBar.setDisplayHomeAsUpEnabled(true);
                    supportActionBar.setTitle((CharSequence) "Abstract");
                }
            } else if (intent.getData().toString().contains("staterequirements")) {
                String uri = intent.getData().toString();
                UserProfile userProfile = this.mUserProfile;
                if (userProfile != null) {
                    String professionId = userProfile.getProfessionProfile().getProfessionId();
                    if (uri.contains("nursecestaterequirements") || UserProfile.NURSE_ID.equals(professionId)) {
                        str3 = Utilities.generateEnvironment(this, "http://www%s.medscape.org/public/nursecestaterequirements");
                    } else if (uri.contains("pharmcestaterequirements") || UserProfile.PHARMACIST_ID.equals(professionId)) {
                        str3 = Utilities.generateEnvironment(this, "http://www%s.medscape.org/public/pharmcestaterequirements");
                    } else {
                        str3 = Utilities.generateEnvironment(this, "http://www%s.medscape.org/public/staterequirements");
                    }
                    if (supportActionBar != null) {
                        supportActionBar.setDisplayHomeAsUpEnabled(true);
                        supportActionBar.setTitle((CharSequence) "State Requirements");
                    }
                    this.mReturnActivity = Constants.CME_TRACKER_ACTIVITY_NAME;
                    str = str3;
                }
            }
        } else if (intent.getExtras().containsKey(Constants.WEB_VIEW_TITLE_KEY)) {
            String string = getIntent().getExtras().getString(Constants.WEB_VIEW_TITLE_KEY);
            if (supportActionBar != null && Extensions.isStringNullOrEmpty(string)) {
                supportActionBar.setTitle((CharSequence) getResources().getString(R.string.title_activity_cmetracker));
            } else if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setTitle((CharSequence) string);
            }
            this.mReturnActivity = intent.getStringExtra(Constants.RETURN_ACTIVITY);
            str = intent.getExtras().getString(Constants.WEB_VIEW_URL_KEY);
        } else {
            str = "";
        }
        WebView webView2 = (WebView) findViewById(R.id.webActivityWebView);
        this.webView = webView2;
        String userAgentString = webView2.getSettings().getUserAgentString();
        try {
            str2 = this.mContext.getPackageManager().getPackageInfo(new ComponentName(this.mContext, CmeWebActivity.class).getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            str2 = "1.0";
        }
        this.webView.getSettings().setUserAgentString(userAgentString + getUserAgentAppName() + str2);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.mErrorLayout = findViewById(R.id.noNetworkView);
        ((Button) findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CmeWebActivity.this.mErrorLayout.setVisibility(8);
                CmeWebActivity.this.loadWebPage(str);
            }
        });
        initCookies(str);
        this.webView.setWebViewClient(new InsideWebViewClient());
        loadWebPage(str);
    }

    /* access modifiers changed from: private */
    public void loadWebPage(String str) {
        if (Utilities.isNetworkAvailable(this)) {
            if (this.mErrorLayout.getVisibility() != 8) {
                this.mErrorLayout.setVisibility(8);
            }
            this.webView.setVisibility(0);
            this.webView.setWebViewClient(new InsideWebViewClient());
            this.webView.loadUrl(str);
            return;
        }
        this.mErrorLayout.setVisibility(0);
    }

    private void initCookies(String str) {
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
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (!isStartingCustomActivity()) {
            return true;
        }
        finish();
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        if (!isStartingCustomActivity()) {
            super.onBackPressed();
        }
        finish();
    }

    private boolean isStartingCustomActivity() {
        if (Extensions.isStringNullOrEmpty(this.mReturnActivity) || this.mReturnActivity.equals(Constants.CME_TRACKER_ACTIVITY_NAME)) {
            return true;
        }
        if (!this.mReturnActivity.equals("ArticleActivity")) {
            return false;
        }
        finish();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!isLockScreenVisible()) {
            trackOmniture(getIntent());
        }
    }

    private class InsideWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return false;
        }

        private InsideWebViewClient() {
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            webView.setVisibility(8);
            CmeWebActivity.this.mErrorLayout.setVisibility(0);
        }

        public void onPageFinished(WebView webView, String str) {
            ((ProgressBar) CmeWebActivity.this.findViewById(R.id.progress_bar)).setVisibility(8);
        }
    }
}
