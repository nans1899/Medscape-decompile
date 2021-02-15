package com.medscape.android.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.ads.AdsSegvarIntf;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.HashMap;
import java.util.Map;

public class InformationWebViewAcitivity extends NavigableBaseActivity implements OnAdListener, DFPReferenceAdListener, AdsSegvarIntf {
    protected static final int GET_NEXT_AD = 102;
    private static final int START_TIMER = 101;
    private static final String TAG = "InformationWebViewAcitivity";
    private String affiliate = "55";
    protected long autohide;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 51) {
                InformationWebViewAcitivity.this.mHandler.postDelayed(InformationWebViewAcitivity.this.mAutohideTimer, InformationWebViewAcitivity.this.autohide * 1000);
                return true;
            } else if (i == 52) {
                InformationWebViewAcitivity.this.onAdNotAvilable();
                return true;
            } else if (i == 101) {
                InformationWebViewAcitivity.this.mHandler.postDelayed(InformationWebViewAcitivity.this.mTimer, InformationWebViewAcitivity.this.rotate * 1000);
                return true;
            } else if (i != 102) {
                return true;
            } else {
                InformationWebViewAcitivity.this.getAd();
                return true;
            }
        }
    });
    private boolean isAdVisible;
    private volatile boolean isExpandedByUser = false;
    Runnable mAutohideTimer = new Runnable() {
        public void run() {
            InformationWebViewAcitivity.this.h.sendEmptyMessage(52);
        }
    };
    private int mContentId;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    Runnable mTimer = new Runnable() {
        public void run() {
            InformationWebViewAcitivity.this.h.sendEmptyMessage(102);
        }
    };
    private String pos = NativeContentAd.ASSET_LOGO;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    protected long rotate;
    private HashMap<String, String> screenSpecificMap = new HashMap<>();
    private String site = "20";

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_webview);
        super.setupAd();
        findViewById(R.id.sectionHeaderTextView).setVisibility(8);
        findViewById(R.id.ad).setVisibility(8);
        this.mContentId = getIntent().getIntExtra("contentId", -1);
        getIntent().getStringExtra("contentType");
        setScreenSpecificMap();
        String uri = getIntent().getData().toString();
        String stringExtra = getIntent().getStringExtra("header");
        if (stringExtra != null) {
            setTitle(stringExtra);
        } else {
            setTitle("");
        }
        Util.setCookie(this);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress);
        this.progressBar = progressBar2;
        progressBar2.setVisibility(0);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setUserAgentString(Util.addUserAgent(webView, this));
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setScrollBarStyle(33554432);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new InsideWebViewClient());
        if (uri.startsWith("file")) {
            webView.loadUrl(uri);
        } else if (Util.isOnline(this)) {
            webView.loadUrl(uri);
        } else if (!isFinishing()) {
            showDialog(5);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("isAdVisible")) {
            this.isAdVisible = extras.getBoolean("isAdVisible");
        }
    }

    private void sendBI() {
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drug", "view", "" + this.mContentId, "9-images", (Map<String, Object>) null);
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null || i != 5) {
            return onCreateDialog;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.alert_dialog_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                InformationWebViewAcitivity.this.finish();
            }
        });
        return builder.create();
    }

    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
        } else if (isSessionValid()) {
            if (Util.getDisplayOrientation(this) != 0) {
                onAdNotAvilable();
            } else if (this.isAdVisible) {
                prepareAd();
            }
        }
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.mAutohideTimer);
        this.mHandler.removeCallbacks(this.mTimer);
    }

    private void prepareAd() {
        if (this.adLayout != null) {
            getAd();
        }
    }

    public void getAd() {
        if (Util.isOnline(this) && Util.getDisplayOrientation(this) == 0 && !this.isPause) {
            this.adAction.setOnUpdateListener(this);
            this.adAction.makeADRequestWithoutBidding(this.screenSpecificMap);
        }
    }

    public void onAdAvailable() {
        this.adView.setVisibility(0);
    }

    public void onAdNotAvilable() {
        this.adLayout.setVisibility(8);
    }

    final class InsideWebViewClient extends WebViewClient {
        public void onReceivedError(WebView webView, int i, String str, String str2) {
        }

        InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (str.startsWith("tel:")) {
                Uri.parse(str);
                InformationWebViewAcitivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("geo")) {
                Uri.parse(str);
                InformationWebViewAcitivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("mail")) {
                Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                InformationWebViewAcitivity informationWebViewAcitivity = InformationWebViewAcitivity.this;
                if (Util.isEmailConfigured(informationWebViewAcitivity, intent)) {
                    InformationWebViewAcitivity.this.startActivity(intent);
                } else if (!informationWebViewAcitivity.isFinishing()) {
                    DialogUtil.showAlertDialog(24, (String) null, informationWebViewAcitivity.getString(R.string.alert_show_email_configure_message), informationWebViewAcitivity).show();
                }
                return true;
            } else {
                webView.loadUrl(str);
                return false;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            InformationWebViewAcitivity.this.progressBar.setVisibility(8);
            webView.loadUrl("javascript:alert(document.cookie);");
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            InformationWebViewAcitivity.this.progressBar.setVisibility(0);
        }
    }

    public void setScreenSpecificMap() {
        this.screenSpecificMap.put("pos", getResources().getString(R.string.banner_ad_pos));
        this.screenSpecificMap.put("pc", "content");
        this.screenSpecificMap.putAll(AdsSegvar.getInstance().queryDatabase(this, this.mContentId, 1));
        HashMap<String, String> hashMap = this.screenSpecificMap;
        hashMap.put("art", "" + this.mContentId);
    }

    public void isAdExpandedByUser(boolean z) {
        if (z) {
            this.mHandler.removeCallbacks(this.mAutohideTimer);
            this.mHandler.removeCallbacks(this.mTimer);
            this.isExpandedByUser = true;
            return;
        }
        if (this.isExpandedByUser) {
            long j = this.rotate;
            if (j > 0) {
                this.mHandler.postDelayed(this.mTimer, j * 1000);
            }
        }
        this.isExpandedByUser = false;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
        }
    }
}
