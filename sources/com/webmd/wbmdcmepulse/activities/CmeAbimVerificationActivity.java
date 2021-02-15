package com.webmd.wbmdcmepulse.activities;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.util.Map;

public class CmeAbimVerificationActivity extends CmeBaseActivity {
    /* access modifiers changed from: private */
    public static final String TAG = CmeAbimVerificationActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public View mErrorLayout;
    /* access modifiers changed from: private */
    public boolean mIsFormRequireDialogVisible;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    WebView mWebview;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_abim_verification);
        initializeToolBar();
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        WebView webView = (WebView) findViewById(R.id.web_view);
        this.mWebview = webView;
        String userAgentString = webView.getSettings().getUserAgentString();
        try {
            str = getPackageManager().getPackageInfo(new ComponentName(this, CmeAbimVerificationActivity.class).getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            str = "1.0";
        }
        this.mWebview.getSettings().setUserAgentString(userAgentString + " cmepulseapp/" + str);
        this.mWebview.getSettings().setJavaScriptEnabled(true);
        this.mErrorLayout = findViewById(R.id.noNetworkView);
        ((Button) findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CmeAbimVerificationActivity.this.mProgressBar != null) {
                    CmeAbimVerificationActivity.this.mProgressBar.setVisibility(0);
                }
                CmeAbimVerificationActivity.this.mWebview.setVisibility(0);
                CmeAbimVerificationActivity.this.mWebview.loadUrl(Constants.getAbimVerficationUrl(CmeAbimVerificationActivity.this));
            }
        });
        this.mWebview.setWebViewClient(new InsideWebViewClient());
        this.mWebview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        initCookies(Constants.getAbimVerficationUrl(this));
        if (Utilities.isNetworkAvailable(this)) {
            if (this.mErrorLayout.getVisibility() != 8) {
                this.mErrorLayout.setVisibility(8);
            }
            this.mWebview.loadUrl(Constants.getAbimVerficationUrl(this));
            return;
        }
        this.mErrorLayout.setVisibility(0);
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Intent intent;
        super.onResume();
        if (!isLockScreenVisible() && (intent = getIntent()) != null) {
            trackeOmniture(intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_PAGE), intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_MODULE), intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_LINK));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        showEvalRequiredDialog();
        return true;
    }

    public void onBackPressed() {
        showEvalRequiredDialog();
    }

    private void showEvalRequiredDialog() {
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.alert_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CmeAbimVerificationActivity.this.setResult(0, new Intent());
                    CmeAbimVerificationActivity.this.finish();
                }
            }).setNegativeButton(R.string.button_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    boolean unused = CmeAbimVerificationActivity.this.mIsFormRequireDialogVisible = false;
                }
            }).setMessage(R.string.dialog_message_exit_moc_form).setTitle(R.string.dialog_title_exit_moc_form).create().show();
            this.mIsFormRequireDialogVisible = true;
            return;
        }
        new AlertDialog.Builder(this, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.alert_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CmeAbimVerificationActivity.this.setResult(0, new Intent());
                CmeAbimVerificationActivity.this.finish();
            }
        }).setNegativeButton(R.string.button_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                boolean unused = CmeAbimVerificationActivity.this.mIsFormRequireDialogVisible = false;
            }
        }).setMessage(R.string.dialog_message_exit_moc_form).setTitle(R.string.dialog_title_exit_moc_form).create().show();
        this.mIsFormRequireDialogVisible = true;
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

    private void trackeOmniture(String str, String str2, String str3) {
        WBMDOmnitureManager.sendPageView(OmnitureData.PAGE_NAME_PAGE_NAME_ABIM_ID, (Map<String, String>) null, new WBMDOmnitureModule(str2, str3, str));
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            try {
                if (!str.contains("?cmeapp")) {
                    return false;
                }
                String access$300 = CmeAbimVerificationActivity.TAG;
                Trace.i(access$300, "Redirected with url " + str);
                CmeAbimVerificationActivity.this.setResult(-1, new Intent());
                CmeAbimVerificationActivity.this.finish();
                return false;
            } catch (Exception e) {
                Trace.e(CmeAbimVerificationActivity.TAG, e.getMessage());
                return false;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            String access$300 = CmeAbimVerificationActivity.TAG;
            Trace.i(access$300, "Received an error " + str2);
            webView.setVisibility(8);
            CmeAbimVerificationActivity.this.mErrorLayout.setVisibility(0);
            if (CmeAbimVerificationActivity.this.mProgressBar != null) {
                CmeAbimVerificationActivity.this.mProgressBar.setVisibility(8);
            }
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            onReceivedError(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }

        public void onPageFinished(WebView webView, String str) {
            try {
                CmeAbimVerificationActivity.this.mWebview.loadUrl("javascript:(function() {document.getElementById('menuContainer').style.display='none';})()");
                CmeAbimVerificationActivity.this.mWebview.loadUrl("javascript:(function() {document.getElementById('menuList').style.display='none';})()");
                CmeAbimVerificationActivity.this.mWebview.loadUrl("javascript:(function() {document.getElementById('medscapeheadercontainer').style.display='none';})()");
                CmeAbimVerificationActivity.this.mWebview.loadUrl("javascript:(function() {document.getElementById('footercontents').style.display='none';})()");
                if (!str.contains("about:blank")) {
                    CmeAbimVerificationActivity.this.mWebview.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                }
            } catch (Exception e) {
                Trace.e(CmeAbimVerificationActivity.TAG, e.getMessage());
            }
            String access$300 = CmeAbimVerificationActivity.TAG;
            Trace.i(access$300, "Finished loading url " + str);
        }
    }

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {
        }

        @JavascriptInterface
        public void processHTML(String str) {
            if (CmeAbimVerificationActivity.this.mProgressBar != null) {
                CmeAbimVerificationActivity.this.mProgressBar.setVisibility(8);
            }
            if (str.contains("ERR_INTERNET_DISCONNECTED")) {
                CmeAbimVerificationActivity.this.mWebview.setVisibility(8);
                CmeAbimVerificationActivity.this.mErrorLayout.setVisibility(0);
            } else if (CmeAbimVerificationActivity.this.mErrorLayout != null && Utilities.isNetworkAvailable(CmeAbimVerificationActivity.this)) {
                CmeAbimVerificationActivity.this.mErrorLayout.setVisibility(8);
                CmeAbimVerificationActivity.this.mWebview.setVisibility(0);
            }
        }
    }
}
