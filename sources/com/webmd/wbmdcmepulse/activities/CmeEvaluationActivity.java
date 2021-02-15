package com.webmd.wbmdcmepulse.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.util.HashMap;

public class CmeEvaluationActivity extends CmeBaseActivity {
    private String mArticleAssetId;
    private String mArticleId;
    private Context mContext;
    /* access modifiers changed from: private */
    public View mErrorLayout;
    /* access modifiers changed from: private */
    public boolean mIsEvalRequireDialogVisible;
    private boolean mIsMocEligible;
    private boolean mIsRequired;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private String mQnaId;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    public void onCreate(Bundle bundle) {
        final String str;
        String str2;
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_evaluation);
        initializeToolBar();
        this.mIsEvalRequireDialogVisible = false;
        this.mQnaId = getIntent().getStringExtra(Constants.BUNDLE_KEY_QNA_ID);
        this.mIsRequired = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, false);
        this.mIsMocEligible = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, false);
        this.mArticleId = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
        this.mArticleAssetId = getIntent().getStringExtra(Constants.BUNDLE_KEY_ASSET_ID);
        this.mContext = this;
        ActionBar supportActionBar = getSupportActionBar();
        this.mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (supportActionBar != null) {
            if (!this.mIsRequired) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            } else {
                supportActionBar.setDisplayHomeAsUpEnabled(false);
            }
            supportActionBar.setTitle((CharSequence) "Evaluation");
        }
        if (getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_STAND_ALONE, false)) {
            str = Constants.Config.getStandAloneEvaluationUrl(this, this.mQnaId);
        } else {
            str = Constants.Config.getArticleEvaluationUrl(this, this.mQnaId);
        }
        final WebView webView = (WebView) findViewById(R.id.webActivityWebView);
        String userAgentString = webView.getSettings().getUserAgentString();
        try {
            str2 = this.mContext.getPackageManager().getPackageInfo(new ComponentName(this.mContext, CmeEvaluationActivity.class).getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            str2 = "1.0";
        }
        webView.getSettings().setUserAgentString(userAgentString + " cmepulseapp/" + str2);
        webView.getSettings().setJavaScriptEnabled(true);
        this.mErrorLayout = findViewById(R.id.noNetworkView);
        ((Button) findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CmeEvaluationActivity.this.mErrorLayout.setVisibility(8);
                if (CmeEvaluationActivity.this.mProgressBar != null) {
                    CmeEvaluationActivity.this.mProgressBar.setVisibility(0);
                }
                webView.setVisibility(0);
                webView.loadUrl(str);
            }
        });
        webView.setWebViewClient(new InsideWebViewClient());
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        initCookies(str);
        if (Utilities.isNetworkAvailable(this)) {
            if (this.mErrorLayout.getVisibility() != 8) {
                this.mErrorLayout.setVisibility(8);
            }
            webView.loadUrl(str);
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
        super.onResume();
        if (!isLockScreenVisible()) {
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticleAssetId, String.format(OmnitureData.PAGE_NAME_EVAL_WEB, new Object[]{this.mQnaId})));
            WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_EVAL_WEB, new Object[]{this.mQnaId}), hashMap, (WBMDOmnitureModule) null);
        }
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle.getBoolean(Constants.BUNDLE_KEY_IS_EVAL_REQ_DIALOG_VISIBLE, false)) {
            showEvalRequiredDialog();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(Constants.BUNDLE_KEY_IS_EVAL_REQ_DIALOG_VISIBLE, this.mIsEvalRequireDialogVisible);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!this.mIsRequired) {
            return true;
        }
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return true;
    }

    public void onBackPressed() {
        if (!this.mIsRequired) {
            moveToCongratulationsScreen(false);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            moveToCongratulationsScreen(false);
            return true;
        }
        if (itemId == R.id.clear) {
            showEvalRequiredDialog();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void showEvalRequiredDialog() {
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.button_home, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Utilities.goToHomeScreen(CmeEvaluationActivity.this);
                    CmeEvaluationActivity.this.finish();
                }
            }).setNegativeButton(R.string.button_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    boolean unused = CmeEvaluationActivity.this.mIsEvalRequireDialogVisible = false;
                }
            }).setMessage(R.string.dialog_message_exit_eval).setTitle(R.string.dialog_title_exit_eval).create().show();
            this.mIsEvalRequireDialogVisible = true;
            return;
        }
        new AlertDialog.Builder(this, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.button_home, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Utilities.goToHomeScreen(CmeEvaluationActivity.this);
                CmeEvaluationActivity.this.finish();
            }
        }).setNegativeButton(R.string.button_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                boolean unused = CmeEvaluationActivity.this.mIsEvalRequireDialogVisible = false;
            }
        }).setMessage(R.string.dialog_message_exit_eval).setTitle(R.string.dialog_title_exit_eval).create().show();
        this.mIsEvalRequireDialogVisible = true;
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

    public class MyJavaScriptInterface {
        public MyJavaScriptInterface() {
        }

        @JavascriptInterface
        public void processHTML(String str) {
            if (str.contains("edu_view_cert_btn")) {
                CmeEvaluationActivity.this.moveToCongratulationsScreen(true);
            }
        }
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            CmeEvaluationActivity.this.startActivity(intent);
            return true;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            webView.setVisibility(8);
            CmeEvaluationActivity.this.mErrorLayout.setVisibility(0);
            if (CmeEvaluationActivity.this.mProgressBar != null) {
                CmeEvaluationActivity.this.mProgressBar.setVisibility(8);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (CmeEvaluationActivity.this.mProgressBar != null) {
                CmeEvaluationActivity.this.mProgressBar.setVisibility(8);
            }
            webView.loadUrl("javascript:(function() {document.getElementById('medscapeheadercontainer').style.display='none';})()");
            webView.loadUrl("javascript:(function() {document.getElementById('return_to_row').style.display='none';})()");
            webView.loadUrl("javascript:(function() {document.getElementById('activity_return').style.display='none';})()");
            webView.loadUrl("javascript:(function() {document.getElementById('footercontents').style.display='none';})()");
            if (str.contains("?formsuccess=true")) {
                CmeEvaluationActivity.this.moveToCongratulationsScreen(true);
            } else if (!str.contains("about:blank")) {
                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }
        }
    }

    public void moveToCongratulationsScreen(boolean z) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, z);
        setResult(9, intent);
        finish();
    }
}
