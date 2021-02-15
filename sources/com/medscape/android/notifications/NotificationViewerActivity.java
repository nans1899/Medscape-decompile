package com.medscape.android.notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.adobe.mobile.Config;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.rss.NewsManager;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.ads.AdsSegvarIntf;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPNewsAdListener;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.homescreen.views.HomeScreenActivity;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomWebView;
import com.medscape.android.view.DropDownListView;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.wbmdcommons.receivers.ShareDataObservable;
import com.wbmd.wbmdcommons.receivers.ShareReceiver;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class NotificationViewerActivity extends BaseActivity implements OnAdListener, AdsSegvarIntf, DFPNewsAdListener, Observer {
    protected static final int GET_NEXT_AD = 102;
    private static final int PREPARE_AD = 103;
    private static final String SCHEME_ADDREFRESH = "adrefresh";
    private static final String SCHEME_HTTP = "http";
    private static final int START_TIMER = 101;
    protected DFPAdAction adAction;
    protected View adLayout;
    protected PublisherAdView adView;
    private String affiliate = "53";
    protected long autohide;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 51) {
                NotificationViewerActivity.this.mHandler.postDelayed(NotificationViewerActivity.this.mAutohideTimer, NotificationViewerActivity.this.autohide * 1000);
                return true;
            } else if (i != 52) {
                switch (i) {
                    case 101:
                        NotificationViewerActivity.this.mHandler.postDelayed(NotificationViewerActivity.this.mTimer, NotificationViewerActivity.this.rotate * 1000);
                        return true;
                    case 102:
                        NotificationViewerActivity.this.getAd();
                        return true;
                    case 103:
                        NotificationViewerActivity.this.prepareAd();
                        return true;
                    default:
                        return true;
                }
            } else {
                NotificationViewerActivity.this.onAdNotAvilable();
                return true;
            }
        }
    });
    private volatile boolean isExpandedByUser = false;
    Runnable mAutohideTimer = new Runnable() {
        public void run() {
            NotificationViewerActivity.this.h.sendEmptyMessage(52);
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    View mShareButton;
    PopupWindow mSharePopup;
    Runnable mTimer = new Runnable() {
        public void run() {
            NotificationViewerActivity.this.h.sendEmptyMessage(102);
        }
    };
    String mURL;
    CustomWebView mWebView;
    private String pos = NativeContentAd.ASSET_LOGO;
    protected long rotate;
    private HashMap<String, String> screenSpecificMap = new HashMap<>();
    private boolean screenSpecificMapSet = false;
    private String site = "20";

    public void update(Observable observable, Object obj) {
        if (observable instanceof ShareDataObservable) {
            if (obj instanceof String) {
                OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), obj.toString(), (Map<String, Object>) null);
            }
            ShareDataObservable.INSTANCE.deleteObserver(this);
        }
    }

    class MyJavaScriptInterface {
        MyJavaScriptInterface() {
        }

        @JavascriptInterface
        public void processHTML(String str) {
            NewsManager.parseAdSegvars(str);
            NotificationViewerActivity.this.setScreenSpecificMap();
            NotificationViewerActivity.this.setScreenSpecificMapSet(true);
            if (Util.getDisplayOrientation(NotificationViewerActivity.this) == 0) {
                NotificationViewerActivity.this.h.sendEmptyMessage(103);
            } else {
                NotificationViewerActivity.this.h.sendEmptyMessage(52);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView((int) R.layout.push_notification_viewer);
        setupAd();
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
        this.mShareButton = findViewById(R.id.bttn_share);
        initShareDropdown();
        initWebView();
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_NEWS, "push", "viewarticle", Uri.parse(this.mURL).getLastPathSegment(), (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public void setupAd() {
        PublisherAdView publisherAdView = new PublisherAdView(this);
        this.adView = publisherAdView;
        publisherAdView.setAdUnitId(DFPNewsAdListener.AD_UNIT_ID);
        View findViewById = findViewById(R.id.ad);
        this.adLayout = findViewById;
        if (findViewById != null) {
            ((LinearLayout) findViewById).addView(this.adView);
        }
        this.adAction = new DFPAdAction(this, this.adLayout, this.adView);
    }

    private void initWebView() {
        this.mShareButton.setEnabled(false);
        CustomWebView customWebView = (CustomWebView) findViewById(R.id.webview);
        this.mWebView = customWebView;
        customWebView.getSettings().setUserAgentString(Util.addUserAgent(this.mWebView, this));
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                NotificationViewerActivity.this.mShareButton.setEnabled(true);
                NotificationViewerActivity.this.mWebView.loadUrl("javascript:hasAdRefresh=1");
                if (!str.contains("about:blank")) {
                    webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                }
                Util.addZoomControl(webView);
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (TextUtils.isEmpty(str) || !str.startsWith(NotificationViewerActivity.SCHEME_ADDREFRESH)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                NotificationViewerActivity.this.mWebView.loadUrl(str.replace(NotificationViewerActivity.SCHEME_ADDREFRESH, NotificationViewerActivity.SCHEME_HTTP));
                return true;
            }
        });
        this.mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        this.mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        this.mWebView.setScrollBarStyle(33554432);
        this.mURL = getIntent().getStringExtra(Constants.EXTRA_CONTENT);
        if (!Util.isOnline(this) && !isFinishing()) {
            showDialog(5);
        } else if (this.mURL != null) {
            String str = (this.mURL.trim() + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(this.mURL.trim())) + "&ref=pushalert";
            if (!str.startsWith("http://")) {
                str = "http://" + str;
            }
            Util.setCookie(this);
            this.mWebView.loadUrl(str);
        }
    }

    private void initShareDropdown() {
        FrameLayout frameLayout = new FrameLayout(this);
        final DropDownListView dropDownListView = new DropDownListView(this);
        dropDownListView.setBackgroundResource(R.color.grey);
        dropDownListView.setPadding(5, 5, 5, 5);
        dropDownListView.setDivider(getResources().getDrawable(R.drawable.color_333333));
        dropDownListView.setDividerHeight((int) Util.dpToPixel(this, 1));
        dropDownListView.setAdapter(new ArrayAdapter<Pair<String, Integer>>(this, 0, new Pair[]{new Pair("Email", Integer.valueOf(R.drawable.ic_action_email)), new Pair("Facebook", Integer.valueOf(R.drawable.facebook_icon))}) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = View.inflate(NotificationViewerActivity.this, R.layout.dropdown_item, (ViewGroup) null);
                }
                Pair pair = (Pair) getItem(i);
                ((ImageView) view.findViewById(R.id.icon)).setImageResource(((Integer) pair.second).intValue());
                ((TextView) view.findViewById(R.id.title)).setText((CharSequence) pair.first);
                return view;
            }
        });
        dropDownListView.setVerticalFadingEdgeEnabled(true);
        dropDownListView.setFocusable(true);
        dropDownListView.setFocusableInTouchMode(true);
        dropDownListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == 0) {
                    String charSequence = ((TextView) NotificationViewerActivity.this.findViewById(R.id.title)).getText().toString();
                    String str = NotificationViewerActivity.this.mURL + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(NotificationViewerActivity.this.mURL) + "&ref=email";
                    PendingIntent createPendingIntent = new ShareReceiver().createPendingIntent(NotificationViewerActivity.this.getApplicationContext());
                    if (Build.VERSION.SDK_INT <= 22) {
                        createPendingIntent = null;
                        OmnitureManager.get().trackModule(NotificationViewerActivity.this.getApplicationContext(), Constants.OMNITURE_CHANNEL_REFERENCE, ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), AdParameterKeys.SECTION_ID, (Map<String, Object>) null);
                    } else {
                        ShareDataObservable.INSTANCE.addObserver(NotificationViewerActivity.this);
                    }
                    com.wbmd.wbmdcommons.utils.Util.share(NotificationViewerActivity.this.getApplicationContext(), str, charSequence, "", "", createPendingIntent);
                }
                NotificationViewerActivity.this.mSharePopup.dismiss();
            }
        });
        dropDownListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                DropDownListView dropDownListView;
                if (i != -1 && (dropDownListView = dropDownListView) != null) {
                    dropDownListView.setListSelectionHidden(false);
                }
            }
        });
        dropDownListView.post(new Runnable() {
            public void run() {
                NotificationViewerActivity.this.mSharePopup.setWindowLayoutMode((int) Util.dpToPixel(NotificationViewerActivity.this, 200), -2);
            }
        });
        frameLayout.addView(dropDownListView);
        this.mSharePopup = new PopupWindow(frameLayout);
    }

    public void onPause() {
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.pause();
        }
        super.onPause();
        Config.pauseCollectingLifecycleData();
        this.mHandler.removeCallbacks(this.mAutohideTimer);
        this.mHandler.removeCallbacks(this.mTimer);
    }

    public void onResume() {
        super.onResume();
        Config.collectLifecycleData(this);
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
        } else {
            prepareAd();
        }
    }

    public void finish() {
        startActivity(new Intent(this, HomeScreenActivity.class).setAction("android.intent.action.MAIN").setFlags(67108864));
        super.finish();
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        ((TextView) findViewById(R.id.title)).setText(charSequence);
    }

    public void onShareClicked(View view) {
        this.mSharePopup.setWidth((int) Util.dpToPixel(this, 200));
        if (this.mSharePopup.isShowing()) {
            this.mSharePopup.dismiss();
        } else {
            this.mSharePopup.showAsDropDown(view, -((int) Util.dpToPixel(this, 152)), 0);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && this.mSharePopup.isShowing()) {
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            this.mSharePopup.getContentView().getLocationOnScreen(iArr);
            this.mShareButton.getLocationOnScreen(iArr2);
            Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.mSharePopup.getContentView().getWidth(), iArr[1] + this.mSharePopup.getContentView().getHeight());
            Rect rect2 = new Rect(iArr2[0], iArr2[1], iArr2[0] + this.mShareButton.getWidth(), iArr2[1] + this.mShareButton.getHeight());
            if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && !rect2.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                this.mSharePopup.dismiss();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setScreenSpecificMap() {
        this.screenSpecificMap.put("pos", getResources().getString(R.string.banner_ad_pos));
        this.screenSpecificMap.put("pc", "alertviewer");
        String[] screenSpecificDataFromHtml = AdsSegvar.getInstance().getScreenSpecificDataFromHtml();
        for (int i = 0; i < screenSpecificDataFromHtml.length; i++) {
            if (screenSpecificDataFromHtml.length >= i) {
                String str = screenSpecificDataFromHtml[i];
                if (str.contains("=")) {
                    String[] split = str.split("=");
                    if (split.length == 2) {
                        this.screenSpecificMap.put(split[0], split[1]);
                    }
                }
            }
        }
    }

    public void onAdAvailable() {
        this.adView.setVisibility(0);
    }

    public void onAdNotAvilable() {
        View view = this.adLayout;
        if (view != null) {
            view.setVisibility(8);
        }
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

    /* access modifiers changed from: private */
    public void prepareAd() {
        View view = this.adLayout;
        if (view != null) {
            view.setVisibility(8);
        }
        getAd();
    }

    public void getAd() {
        if (Util.isOnline(this) && Util.getDisplayOrientation(this) == 0) {
            DFPAdAction dFPAdAction = new DFPAdAction(this, this.adLayout, this.adView);
            this.adAction = dFPAdAction;
            dFPAdAction.setOnUpdateListener(this);
            new Date().getTime();
            if (NewsManager.metaString != null) {
                NewsManager.metaString.trim();
            }
            NewsManager.metaString = "";
            if (isScreenSpecificMapSet()) {
                this.adAction.makeADRequestWithoutBidding(this.screenSpecificMap);
            }
        }
    }

    public synchronized boolean isScreenSpecificMapSet() {
        return this.screenSpecificMapSet;
    }

    public synchronized void setScreenSpecificMapSet(boolean z) {
        this.screenSpecificMapSet = z;
    }

    public Dialog onCreateDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (i != 5) {
            return null;
        }
        return builder.setMessage(getString(R.string.alert_dialog_rss_article_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_button_text_close), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                NotificationViewerActivity.this.finish();
            }
        }).create();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.destroy();
        }
        super.onDestroy();
    }
}
