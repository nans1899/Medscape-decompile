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
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.BI.BIManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.player.VideoPlayer;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomWebView;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;

public class AdPreLoadWebViewActivity extends BaseActivity implements View.OnClickListener {
    public static final int HIDE_PROGRESS_BAR = 4;
    public static final int SEND_BI = 5;
    private static final int SHOW_NETWORK_ERROR_DIALOG = 1;
    private static final String TAG = "AdWebViewAcitivity";
    private String adID;
    /* access modifiers changed from: private */
    public String fullScreenAd_AnnounceURL;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 4) {
                    AdPreLoadWebViewActivity.this.progressBar.setVisibility(8);
                } else if (i == 5) {
                    AdPreLoadWebViewActivity.this.sendBI();
                }
            } else if (!AdPreLoadWebViewActivity.this.isFinishing()) {
                AdPreLoadWebViewActivity.this.showDialog(1);
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public RelativeLayout headerLayout;
    /* access modifiers changed from: private */
    public String headerText;
    /* access modifiers changed from: private */
    public boolean isAnnounced = false;
    /* access modifiers changed from: private */
    public boolean isFullScreenAd = false;
    private ImageButton nextButton;
    private ImageButton prevButton;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private Button rightButton;
    /* access modifiers changed from: private */
    public long startTime = 0;
    private boolean toolBar = false;
    private String uri;
    private CustomWebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG, "OnCreate()");
        setContentView((int) R.layout.generic_preload_webview);
        this.isFullScreenAd = getIntent().getBooleanExtra("isFullScreenAd", false);
        boolean booleanExtra = getIntent().getBooleanExtra("isImmediate", true);
        if (this.isFullScreenAd) {
            this.fullScreenAd_AnnounceURL = getIntent().getStringExtra("announceURL");
            this.adID = getIntent().getStringExtra("adID");
        }
        Util.setCookie(this);
        this.uri = getIntent().getData().toString();
        boolean booleanExtra2 = getIntent().getBooleanExtra("navBar", false);
        this.headerLayout = (RelativeLayout) findViewById(R.id.headerLayout);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        Button button = (Button) findViewById(R.id.rightImageButton);
        this.rightButton = button;
        button.setVisibility(8);
        if (booleanExtra2) {
            this.headerText = getIntent().getStringExtra("headerText");
            String stringExtra = getIntent().getStringExtra("headerColor");
            String stringExtra2 = getIntent().getStringExtra("buttonText");
            String str = this.headerText;
            if (str == null || str.equals("")) {
                this.headerText = Util.AD_DEFAULT_NAV_BAR_TITLE;
            }
            if (stringExtra == null || stringExtra.equals("")) {
                stringExtra = Util.AD_DEFAULT_NAV_BAR_COLOR;
            }
            if (stringExtra2 == null || stringExtra2.equals("")) {
                stringExtra2 = "Close";
            }
            this.toolBar = getIntent().getBooleanExtra("toolBar", false);
            setTitle(this.headerText);
            this.headerLayout.setBackgroundDrawable((Drawable) null);
            try {
                RelativeLayout relativeLayout = this.headerLayout;
                relativeLayout.setBackgroundColor(Color.parseColor("#" + stringExtra));
            } catch (IllegalArgumentException unused) {
                this.headerLayout.setBackgroundColor(Color.parseColor("#dcdcdc"));
            }
            findViewById(R.id.leftImageButton).setVisibility(4);
            this.rightButton.setVisibility(0);
            this.rightButton.setText(stringExtra2);
            this.rightButton.setOnClickListener(this);
            if (stringExtra2.equals("")) {
                this.rightButton.setVisibility(8);
            }
        } else {
            this.headerLayout.setVisibility(8);
        }
        if (!this.toolBar) {
            findViewById(R.id.ToolBarLayout).setVisibility(8);
        } else {
            findViewById(R.id.ToolBarLayout).setVisibility(0);
            this.nextButton = (ImageButton) findViewById(R.id.next);
            this.prevButton = (ImageButton) findViewById(R.id.prev);
            this.nextButton.setOnClickListener(this);
            this.prevButton.setOnClickListener(this);
        }
        if (!booleanExtra) {
            CustomWebView webview = AdWebView.getInstance(getApplicationContext()).getWebview();
            this.webView = webview;
            webview.setId(13902930);
            this.webView.setWebViewClient(new InsideWebViewClient());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(6, this.webView.getId());
            layoutParams.addRule(2, R.id.ToolBarLayout);
            ((RelativeLayout) findViewById(R.id.fullscreen_custom_webview)).addView(this.webView, layoutParams);
            return;
        }
        CustomWebView customWebView = (CustomWebView) findViewById(R.id.webView);
        this.webView = customWebView;
        customWebView.setScrollBarStyle(33554432);
        this.webView.getSettings().setCacheMode(2);
        if (Util.isOnline(this)) {
            loadContent();
        } else {
            this.h.sendEmptyMessage(1);
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
            this.webView.getSettings().setBuiltInZoomControls(true);
            this.webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            this.webView.setWebViewClient(new InsideWebViewClient());
            this.webView.loadUrl(this.uri);
            setNavigation();
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

    /* access modifiers changed from: private */
    public void AdFinish() {
        ((RelativeLayout) findViewById(R.id.fullscreen_custom_webview)).removeView(this.webView);
        finish();
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
                AdPreLoadWebViewActivity.this.AdFinish();
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 84 || i == 82;
            }
        });
        builder.setNegativeButton(getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AdPreLoadWebViewActivity.this.loadContent();
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
            if (str.startsWith("close")) {
                AdPreLoadWebViewActivity.this.AdFinish();
                return true;
            } else if (str.contains("webmd_link_target=safari")) {
                Uri.parse(str);
                AdPreLoadWebViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.contains("webmd_page_title=")) {
                if (AdPreLoadWebViewActivity.this.headerLayout.getVisibility() == 0) {
                    AdPreLoadWebViewActivity.this.setTitle(str.substring(str.indexOf("webmd_page_title=") + 17, str.length()));
                }
                return false;
            } else if (str.contains("mp4") || str.contains("mp3")) {
                Intent intent = new Intent(AdPreLoadWebViewActivity.this, VideoPlayer.class);
                if (str.contains("exturl=")) {
                    str = str.substring(str.indexOf("exturl=") + 7);
                }
                intent.putExtra("path", str.trim());
                intent.putExtra("articleTitle", AdPreLoadWebViewActivity.this.headerText);
                AdPreLoadWebViewActivity.this.startActivity(intent);
                return true;
            } else {
                if (!Util.isOnline(AdPreLoadWebViewActivity.this)) {
                    AdPreLoadWebViewActivity.this.h.sendEmptyMessage(1);
                } else if (parse.getScheme().toLowerCase().equals("customurl")) {
                    Util.openExternalApp(AdPreLoadWebViewActivity.this, parse);
                    return true;
                }
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            AdPreLoadWebViewActivity.this.h.sendEmptyMessage(4);
            AdPreLoadWebViewActivity.this.setNavigation();
            Util.addZoomControl(webView);
            if (AdPreLoadWebViewActivity.this.isFullScreenAd && !AdPreLoadWebViewActivity.this.isAnnounced) {
                new AdServerPingTask(AdPreLoadWebViewActivity.this).execute(new String[]{AdPreLoadWebViewActivity.this.fullScreenAd_AnnounceURL});
                boolean unused = AdPreLoadWebViewActivity.this.isAnnounced = true;
                long unused2 = AdPreLoadWebViewActivity.this.startTime = System.currentTimeMillis();
            }
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
        bIManager.setFullScreenAD_ELPST(this.startTime > 0 ? String.valueOf(System.currentTimeMillis() - this.startTime) : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        bIManager.startBI(this, "view", "alert", this.adID, "fullscreenad");
    }

    public void onClick(View view) {
        if (view.equals(this.rightButton)) {
            this.webView.stopLoading();
            if (this.isFullScreenAd) {
                this.h.sendEmptyMessage(5);
            }
            if (this.webView.inCustomView()) {
                hideCustomeView();
            }
            AdFinish();
            overridePendingTransition(0, R.anim.slide_out_down);
            return;
        }
        if (this.nextButton.equals(view)) {
            if (this.webView.canGoForward()) {
                this.webView.goForward();
            }
        } else if (this.prevButton.equals(view) && this.webView.canGoBack()) {
            this.webView.goBack();
        }
        setNavigation();
    }

    public void setNavigation() {
        if (this.toolBar) {
            if (this.webView.canGoBack()) {
                this.prevButton.setEnabled(true);
                this.prevButton.setImageResource(R.drawable.ic_menu_back_off);
            } else {
                this.prevButton.setEnabled(false);
                this.prevButton.setImageResource(R.drawable.ic_menu_back_on);
            }
            if (this.webView.canGoForward()) {
                this.nextButton.setEnabled(true);
                this.nextButton.setImageResource(R.drawable.ic_menu_forward_off);
                return;
            }
            this.nextButton.setEnabled(false);
            this.nextButton.setImageResource(R.drawable.ic_menu_forward_on);
        }
    }
}
