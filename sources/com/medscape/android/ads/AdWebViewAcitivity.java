package com.medscape.android.ads;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.BI.BIManager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.AndroidPdfViewerActivity;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.player.VideoPlayer;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomWebView;
import com.wbmd.wbmdcommons.utils.Extensions;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.HashMap;
import java.util.Map;

public class AdWebViewAcitivity extends BaseActivity implements View.OnClickListener {
    public static final int HIDE_PROGRESS_BAR = 4;
    public static final int SEND_BI = 5;
    private static final int SHOW_NETWORK_ERROR_DIALOG = 1;
    private static final String TAG = "AdWebViewAcitivity";
    public static long startTime;
    private String adID;
    /* access modifiers changed from: private */
    public String contentUri;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 4) {
                    AdWebViewAcitivity.this.progressBar.setVisibility(8);
                } else if (i == 5) {
                    AdWebViewAcitivity.this.sendBI();
                }
            } else if (!AdWebViewAcitivity.this.isFinishing()) {
                AdWebViewAcitivity.this.showDialog(1);
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public RelativeLayout headerLayout;
    /* access modifiers changed from: private */
    public String headerText;
    /* access modifiers changed from: private */
    public boolean isFullScreenAd = false;
    /* access modifiers changed from: private */
    public boolean isImmediate;
    public View mCustomView;
    private boolean mInit = false;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private Button rightButton;
    private boolean sendBI;
    private CustomWebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d(TAG, "OnCreate()", new Object[0]);
        setContentView((int) R.layout.generic_video_webview);
        this.isFullScreenAd = getIntent().getBooleanExtra("isFullScreenAd", false);
        this.isImmediate = getIntent().getBooleanExtra("isImmediate", true);
        this.sendBI = getIntent().getBooleanExtra("sendBI", false);
        String stringExtra = getIntent().getStringExtra("adContent");
        if (this.isFullScreenAd) {
            this.adID = getIntent().getStringExtra("adID");
        }
        Util.setCookie(this);
        String uri = getIntent().getData().toString();
        this.contentUri = uri;
        if (StringUtil.isNotEmpty(uri) && this.contentUri.contains("adurl=http://")) {
            this.contentUri = this.contentUri.replace("adurl=http://", "adurl=https://");
        }
        boolean booleanExtra = getIntent().getBooleanExtra("navBar", false);
        this.headerLayout = (RelativeLayout) findViewById(R.id.headerLayout);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        Button button = (Button) findViewById(R.id.rightImageButton);
        this.rightButton = button;
        button.setVisibility(8);
        if (booleanExtra) {
            this.headerText = getIntent().getStringExtra("headerText");
            String stringExtra2 = getIntent().getStringExtra("headerColor");
            String stringExtra3 = getIntent().getStringExtra("buttonText");
            String str = this.headerText;
            if (str == null || str.equals("")) {
                this.headerText = Util.AD_DEFAULT_NAV_BAR_TITLE;
            }
            if (stringExtra2 == null || stringExtra2.equals("")) {
                stringExtra2 = Util.AD_DEFAULT_NAV_BAR_COLOR;
            }
            if (stringExtra3 == null || stringExtra3.equals("")) {
                stringExtra3 = "Close";
            }
            setTitle(this.headerText);
            ((TextView) findViewById(R.id.headerTextView)).setText(this.headerText);
            this.headerLayout.setBackgroundDrawable((Drawable) null);
            try {
                if (!stringExtra2.startsWith("#")) {
                    stringExtra2 = "#" + stringExtra2;
                }
                this.headerLayout.setBackgroundColor(Color.parseColor(stringExtra2));
            } catch (IllegalArgumentException unused) {
                this.headerLayout.setBackgroundColor(Color.parseColor("#dcdcdc"));
            }
            findViewById(R.id.leftImageButton).setVisibility(4);
            this.rightButton.setVisibility(0);
            this.rightButton.setText(stringExtra3);
            this.rightButton.setOnClickListener(this);
            if (stringExtra3.equals("")) {
                this.rightButton.setVisibility(8);
            }
        } else {
            this.headerLayout.setVisibility(8);
        }
        if (!this.isImmediate) {
            CustomWebView customWebView = (CustomWebView) findViewById(R.id.webView);
            this.webView = customWebView;
            customWebView.setScrollBarStyle(33554432);
            this.webView.getSettings().setCacheMode(2);
            if (stringExtra != null) {
                loadContent(stringExtra);
            } else if (Util.isOnline(this)) {
                loadContent();
            }
        } else {
            CustomWebView customWebView2 = (CustomWebView) findViewById(R.id.webView);
            this.webView = customWebView2;
            customWebView2.setScrollBarStyle(33554432);
            if (Util.isOnline(this)) {
                loadContent();
            } else {
                this.h.sendEmptyMessage(1);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void loadContent() {
        if (Util.isOnline(this)) {
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.setWebChromeClient(new WebChromeClient());
            this.webView.getSettings().setBuiltInZoomControls(true);
            this.webView.getSettings().setDomStorageEnabled(true);
            this.webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            this.webView.setWebViewClient(new InsideWebViewClient());
            this.webView.getSettings().setUserAgentString(Util.addUserAgent(this.webView, this));
            this.webView.loadUrl(this.contentUri);
            startTime = System.currentTimeMillis();
            this.mInit = true;
            return;
        }
        this.h.sendEmptyMessage(1);
    }

    public void loadContent(String str) {
        if (Util.isOnline(this)) {
            this.webView.getSettings().setJavaScriptEnabled(true);
            this.webView.getSettings().setBuiltInZoomControls(true);
            this.webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            this.webView.setWebViewClient(new InsideWebViewClient());
            String str2 = this.contentUri;
            if (Extensions.IsNullOrEmpty(str2)) {
                str2 = "https://";
            }
            this.webView.loadDataWithBaseURL(str2, str, "text/html", "utf-8", this.contentUri);
            startTime = System.currentTimeMillis();
            this.mInit = true;
            return;
        }
        this.h.sendEmptyMessage(1);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            AdFinish();
        } else if (this.isFullScreenAd && !Settings.singleton(this).getSetting(Constants.PREF_IS_FULL_SCREEN_IS_IN_FRONT, "false").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
            AdFinish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.isFullScreenAd) {
            Util.isFullScreenAd = false;
        }
    }

    public Dialog onCreateDialog(int i) {
        ProgressBar progressBar2;
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 1) {
            return null;
        }
        if (!isFinishing() && (progressBar2 = this.progressBar) != null) {
            progressBar2.setVisibility(8);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.msg_network_error)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_test_drive_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AdWebViewAcitivity.this.AdFinish();
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 84 || i == 82;
            }
        });
        builder.setNegativeButton(getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AdWebViewAcitivity.this.loadContent();
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }

    public void onBackPressed() {
        this.webView.stopLoading();
        super.onBackPressed();
    }

    final class InsideWebViewClient extends WebViewClient {
        InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (!parse.equals(AdWebViewAcitivity.this.contentUri)) {
                AdWebViewAcitivity.this.sendOmniture(str);
            }
            if (str.startsWith("close")) {
                AdWebViewAcitivity.this.AdFinish();
                return true;
            } else if (str.contains("webmd_link_target=safari")) {
                Uri.parse(str);
                AdWebViewAcitivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.contains("webmd_page_title=")) {
                if (AdWebViewAcitivity.this.headerLayout.getVisibility() == 0) {
                    AdWebViewAcitivity.this.setTitle(str.substring(str.indexOf("webmd_page_title=") + 17, str.length()));
                }
                return false;
            } else if (str.contains("mp4") || str.contains("mp3")) {
                Intent intent = new Intent(AdWebViewAcitivity.this, VideoPlayer.class);
                if (str.contains("exturl=")) {
                    str = str.substring(str.indexOf("exturl=") + 7);
                }
                intent.putExtra("path", str.trim());
                intent.putExtra("articleTitle", AdWebViewAcitivity.this.headerText);
                AdWebViewAcitivity.this.startActivity(intent);
                return true;
            } else if (str.endsWith(".pdf")) {
                String queryParameter = parse.getQueryParameter("url");
                if (Extensions.IsNullOrEmpty(queryParameter)) {
                    AdWebViewAcitivity.this.startActivity(new Intent("android.intent.action.VIEW", parse));
                } else {
                    Intent intent2 = new Intent(AdWebViewAcitivity.this, AndroidPdfViewerActivity.class);
                    intent2.putExtra("pdf_url", queryParameter);
                    AdWebViewAcitivity.this.startActivity(intent2);
                }
                return true;
            } else {
                if (!Util.isOnline(AdWebViewAcitivity.this)) {
                    AdWebViewAcitivity.this.h.sendEmptyMessage(1);
                } else if (parse.getScheme().toLowerCase().equals("customurl")) {
                    Util.openExternalApp(AdWebViewAcitivity.this, parse);
                    return true;
                }
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            AdWebViewAcitivity.this.h.sendEmptyMessage(4);
            Util.addZoomControl(webView);
            if (!AdWebViewAcitivity.this.isFullScreenAd) {
                return;
            }
            if (!AdWebViewAcitivity.this.isImmediate) {
                AdWebViewAcitivity.startTime = System.currentTimeMillis();
            } else {
                AdWebViewAcitivity.startTime = System.currentTimeMillis();
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendOmniture(String str) {
        if (this.sendBI) {
            HashMap hashMap = new HashMap();
            hashMap.put("exiturl", str);
            OmnitureManager.get().trackModuleAbsolute(this, Constants.OMNITURE_CHANNEL_REFERENCE, "footer-expand", "exlnk", hashMap);
        }
    }

    public void hideCustomeView() {
        if (this.webView.inCustomView()) {
            this.webView.hideCustomView();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        if (this.webView.inCustomView()) {
            hideCustomeView();
            return true;
        }
        CustomWebView customWebView = this.webView;
        if (customWebView == null || customWebView.getUrl() == null) {
            if (this.isFullScreenAd) {
                sendBI();
            }
            AdFinish();
            return false;
        }
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            if (this.isFullScreenAd) {
                sendBI();
            }
            AdFinish();
        }
        return true;
    }

    public void sendBI() {
        BIManager bIManager = new BIManager();
        bIManager.setFullScreenAD_ELPST(startTime > 0 ? String.valueOf(System.currentTimeMillis() - startTime) : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        bIManager.startBI(this, "view", "alert", this.adID, "fullscreenad");
    }

    public void onClick(View view) {
        if (view.equals(this.rightButton)) {
            OmnitureManager.get().trackModuleAbsolute(this, Constants.OMNITURE_CHANNEL_REFERENCE, "footer-expand", "dsexp", (Map<String, Object>) null);
            this.webView.stopLoading();
            if (this.isFullScreenAd) {
                this.h.sendEmptyMessage(5);
            }
            if (this.webView.inCustomView()) {
                hideCustomeView();
            }
            AdFinish();
        }
    }

    /* access modifiers changed from: private */
    public void AdFinish() {
        finish();
        if (AdWebView.getInstance(getApplicationContext()).getClassName() == null || !AdWebView.getInstance(getApplicationContext()).getClassName().contains("RSSArticleActivity")) {
            overridePendingTransition(0, 0);
        } else {
            overridePendingTransition(0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.webView != null) {
            ((RelativeLayout) findViewById(R.id.fullscreen_custom_webview)).removeView(this.webView);
        }
        super.onDestroy();
    }
}
