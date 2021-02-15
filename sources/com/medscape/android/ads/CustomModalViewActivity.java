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
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.BI.BIManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.player.VideoPlayer;
import com.medscape.android.slideshow.BrandAdvanceImageViewer;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomWebView;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;

public class CustomModalViewActivity extends BaseActivity implements View.OnClickListener {
    public static final int HIDE_PROGRESS_BAR = 4;
    public static final int SEND_BI = 5;
    private static final int SHOW_NETWORK_ERROR_DIALOG = 1;
    private static final String TAG = "CustomModalViewAcitivity";
    public static long startTime;
    private String adID;
    /* access modifiers changed from: private */
    public String fullScreenAd_AnnounceURL;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 4) {
                    CustomModalViewActivity.this.progressBar.setVisibility(8);
                } else if (i == 5) {
                    CustomModalViewActivity.this.sendBI();
                }
            } else if (!CustomModalViewActivity.this.isFinishing()) {
                CustomModalViewActivity.this.showDialog(1);
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
    /* access modifiers changed from: private */
    public boolean isImmediate;
    private String mSfNumber = "";
    private ImageButton nextButton;
    private ImageButton prevButton;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private TextView rightButton;
    private boolean toolBar = false;
    private String uri;
    private CustomWebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.d(TAG, "OnCreate()", new Object[0]);
        setContentView((int) R.layout.custom_modal_webview);
        this.isFullScreenAd = getIntent().getBooleanExtra("isFullScreenAd", false);
        this.isImmediate = getIntent().getBooleanExtra("isImmediate", true);
        boolean booleanExtra = getIntent().getBooleanExtra("isNotesModal", false);
        int intExtra = getIntent().getIntExtra("slideNumber", 1);
        this.mSfNumber = getIntent().getStringExtra("sfNumber");
        boolean z = getIntent().getExtras().getBoolean("isBrandPlay");
        String string = getIntent().getExtras().getString("orientationLock");
        if (string != null) {
            if (string.equalsIgnoreCase("portrait")) {
                setRequestedOrientation(1);
            } else if (string.equalsIgnoreCase("landscape")) {
                setRequestedOrientation(0);
            }
        }
        if (this.mSfNumber == null) {
            this.mSfNumber = "";
        }
        boolean booleanExtra2 = getIntent().getBooleanExtra("isZoomModal", false);
        if (this.isFullScreenAd) {
            this.fullScreenAd_AnnounceURL = getIntent().getStringExtra("announceURL");
            this.adID = getIntent().getStringExtra("adID");
        }
        Util.setCookie(this);
        this.uri = getIntent().getData().toString();
        boolean booleanExtra3 = getIntent().getBooleanExtra("navBar", false);
        this.headerLayout = (RelativeLayout) findViewById(R.id.headerLayout);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        TextView textView = (TextView) findViewById(R.id.rightImageButton);
        this.rightButton = textView;
        textView.setVisibility(8);
        if (booleanExtra) {
            findViewById(R.id.closeNotes).setVisibility(0);
            findViewById(R.id.zoomout).setVisibility(8);
        }
        if (booleanExtra2) {
            findViewById(R.id.zoomout).setVisibility(0);
            findViewById(R.id.closeNotes).setVisibility(8);
        }
        if (booleanExtra3) {
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
            this.rightButton.setVisibility(0);
            this.rightButton.setText(stringExtra2);
            this.rightButton.setOnClickListener(this);
            if (stringExtra2.equals("")) {
                this.rightButton.setVisibility(8);
            }
        } else {
            this.headerLayout.setVisibility(8);
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
        if (!this.toolBar) {
            findViewById(R.id.ToolBarLayout).setVisibility(8);
        } else {
            findViewById(R.id.ToolBarLayout).setVisibility(0);
            this.nextButton = (ImageButton) findViewById(R.id.next);
            this.prevButton = (ImageButton) findViewById(R.id.prev);
            this.nextButton.setOnClickListener(this);
            this.prevButton.setOnClickListener(this);
        }
        if (!this.isImmediate) {
            RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.fullscreen_custom_webview);
            CustomWebView webview = AdWebView.getInstance(getApplicationContext()).getWebview();
            this.webView = webview;
            if (webview == null) {
                AdFinish();
                return;
            }
            webview.setScrollBarStyle(33554432);
            this.webView.setmContext(this);
            this.webView.setId(13902930);
            this.webView.setWebViewClient(new InsideWebViewClient());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(6, this.webView.getId());
            layoutParams.addRule(2, R.id.ToolBarLayout);
            if (this.webView.getParent() != null) {
                ((ViewGroup) this.webView.getParent()).removeView(this.webView);
            }
            relativeLayout2.addView(this.webView, layoutParams);
        } else {
            CustomWebView customWebView2 = (CustomWebView) findViewById(R.id.webView);
            this.webView = customWebView2;
            customWebView2.setScrollBarStyle(33554432);
            this.webView.getSettings().setCacheMode(2);
            if (Util.isOnline(this)) {
                loadContent();
            } else {
                this.h.sendEmptyMessage(1);
            }
        }
        if (booleanExtra) {
            String MD5 = Util.MD5(this.mSfNumber + "_SLIDE" + intExtra + "_SLIDENOTES");
            if (z) {
                sendBrandPlayBI(MD5);
            } else {
                sendBrandAdvanceBI(MD5);
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

    private void sendBrandAdvanceBI(String str) {
        String str2;
        this.mBIManager = new BIManager();
        if (str != null) {
            str2 = "www.medscape.com/brandadvance/" + this.mSfNumber + "/" + str;
        } else {
            str2 = null;
        }
        this.mBIManager.startBI(this, "view_slide_notes", "brandadvance_" + this.mSfNumber, str2);
    }

    private void sendBrandPlayBI(String str) {
        String str2;
        this.mBIManager = new BIManager();
        if (str != null) {
            str2 = "www.medscape.com/brandplay/" + this.mSfNumber + "/" + str;
        } else {
            str2 = null;
        }
        this.mBIManager.startBI(this, "view_slide_notes", "brandaplay_" + this.mSfNumber, str2);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.isFullScreenAd) {
            Util.isFullScreenAd = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.webView.destroy();
        super.onDestroy();
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
                CustomModalViewActivity.this.AdFinish();
            }
        }).setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 84 || i == 82;
            }
        });
        builder.setNegativeButton(getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CustomModalViewActivity.this.loadContent();
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
                CustomModalViewActivity.this.AdFinish();
                return true;
            } else if (str.contains("webmd_link_target=safari")) {
                Uri.parse(str);
                CustomModalViewActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.contains("webmd_page_title=")) {
                if (CustomModalViewActivity.this.headerLayout.getVisibility() == 0) {
                    CustomModalViewActivity.this.setTitle(str.substring(str.indexOf("webmd_page_title=") + 17, str.length()));
                }
                return false;
            } else if (str.contains("mp4") || str.contains("mp3")) {
                Intent intent = new Intent(CustomModalViewActivity.this, VideoPlayer.class);
                if (str.contains("exturl=")) {
                    str = str.substring(str.indexOf("exturl=") + 7);
                }
                intent.putExtra("path", str.trim());
                intent.putExtra("articleTitle", CustomModalViewActivity.this.headerText);
                CustomModalViewActivity.this.startActivity(intent);
                return true;
            } else {
                if (!Util.isOnline(CustomModalViewActivity.this)) {
                    CustomModalViewActivity.this.h.sendEmptyMessage(1);
                } else if (parse.getScheme().toLowerCase().equals("customurl")) {
                    Util.openExternalApp(CustomModalViewActivity.this, parse);
                    return true;
                } else if (parse.getScheme() != null && parse.getScheme().equalsIgnoreCase("viewimage")) {
                    String queryParameter = parse.getQueryParameter("src");
                    Intent intent2 = new Intent(CustomModalViewActivity.this, BrandAdvanceImageViewer.class);
                    intent2.putExtra("com.medscape.android.EXTRA_WEBVIEW_URL", queryParameter);
                    CustomModalViewActivity.this.startActivity(intent2);
                    return true;
                }
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            CustomModalViewActivity.this.h.sendEmptyMessage(4);
            CustomModalViewActivity.this.setNavigation();
            if (CustomModalViewActivity.this.isFullScreenAd && !CustomModalViewActivity.this.isAnnounced) {
                new AdServerPingTask(CustomModalViewActivity.this).execute(new String[]{CustomModalViewActivity.this.fullScreenAd_AnnounceURL});
                boolean unused = CustomModalViewActivity.this.isAnnounced = true;
                if (CustomModalViewActivity.this.isImmediate) {
                    CustomModalViewActivity.startTime = System.currentTimeMillis();
                }
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
        bIManager.setFullScreenAD_ELPST(startTime > 0 ? String.valueOf(System.currentTimeMillis() - startTime) : AppEventsConstants.EVENT_PARAM_VALUE_NO);
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

    public void onCloseClicked(View view) {
        AdFinish();
    }

    /* access modifiers changed from: private */
    public void AdFinish() {
        if (this.webView != null) {
            ((RelativeLayout) findViewById(R.id.fullscreen_custom_webview)).removeView(this.webView);
        }
        finish();
        if (AdWebView.getInstance(getApplicationContext()).getClassName() == null || !AdWebView.getInstance(getApplicationContext()).getClassName().contains("RSSArticleActivity")) {
            overridePendingTransition(0, R.anim.slide_out_down);
        } else {
            overridePendingTransition(0, 0);
        }
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
