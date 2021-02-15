package com.medscape.android.activity.webviews;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.activity.rss.NewsManager;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPNewsAdListener;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomChromeClient;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.receivers.ShareDataObservable;
import com.wbmd.wbmdcommons.receivers.ShareReceiver;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.bytebuddy.description.type.TypeDescription;
import org.jetbrains.anko.custom.DeprecatedKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001YB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010.\u001a\u00020/H\u0002J\u001a\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020\n2\b\u00102\u001a\u0004\u0018\u000103H\u0002J\b\u00104\u001a\u00020/H\u0002J\u0010\u00105\u001a\u00020/2\u0006\u00106\u001a\u00020\u0012H\u0016J\u0006\u00107\u001a\u00020\u0012J\b\u00108\u001a\u00020/H\u0002J\b\u00109\u001a\u00020/H\u0016J\b\u0010:\u001a\u00020/H\u0016J\b\u0010;\u001a\u00020/H\u0016J\u0012\u0010<\u001a\u00020/2\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\u0012\u0010?\u001a\u00020\u00122\b\u0010@\u001a\u0004\u0018\u00010AH\u0016J\b\u0010B\u001a\u00020/H\u0014J\u0010\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020/H\u0016J\b\u0010G\u001a\u00020/H\u0014J\b\u0010H\u001a\u00020/H\u0014J\b\u0010I\u001a\u00020/H\u0016J\u0006\u0010J\u001a\u00020/J\u0006\u0010K\u001a\u00020/J\u0006\u0010L\u001a\u00020/J\b\u0010M\u001a\u00020/H\u0007J\b\u0010N\u001a\u00020/H\u0014J\u001a\u0010O\u001a\u0002032\b\u0010P\u001a\u0004\u0018\u0001032\b\u0010Q\u001a\u0004\u0018\u000103J\b\u0010R\u001a\u00020/H\u0002J\b\u0010S\u001a\u00020/H\u0002J\u001c\u0010T\u001a\u00020/2\b\u0010U\u001a\u0004\u0018\u00010V2\b\u0010W\u001a\u0004\u0018\u00010XH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u001bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020)X.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006Z"}, d2 = {"Lcom/medscape/android/activity/webviews/CommonWebViewActivity;", "Lcom/medscape/android/base/BaseActivity;", "Lcom/medscape/android/view/CustomChromeClient$OnPageLoaded;", "Lcom/medscape/android/ads/OnAdListener;", "Lcom/medscape/android/ads/DFPNewsAdListener;", "Ljava/util/Observer;", "()V", "chromeClient", "Lcom/medscape/android/view/CustomChromeClient;", "eventsCounter", "", "firebaseRouter", "Lcom/webmd/wbmdsimullytics/platform/PlatformRouteDispatcher;", "getFirebaseRouter", "()Lcom/webmd/wbmdsimullytics/platform/PlatformRouteDispatcher;", "setFirebaseRouter", "(Lcom/webmd/wbmdsimullytics/platform/PlatformRouteDispatcher;)V", "isRecapArticle", "", "mWebView", "Landroid/webkit/WebView;", "getMWebView", "()Landroid/webkit/WebView;", "setMWebView", "(Landroid/webkit/WebView;)V", "omnitureCallsCounter", "progress", "Landroid/widget/ProgressBar;", "getProgress", "()Landroid/widget/ProgressBar;", "setProgress", "(Landroid/widget/ProgressBar;)V", "rootView", "Landroid/widget/RelativeLayout;", "getRootView", "()Landroid/widget/RelativeLayout;", "setRootView", "(Landroid/widget/RelativeLayout;)V", "skipADInterface", "skipFirstonResume", "webViewModel", "Lcom/medscape/android/activity/webviews/WebViewViewModel;", "getWebViewModel", "()Lcom/medscape/android/activity/webviews/WebViewViewModel;", "setWebViewModel", "(Lcom/medscape/android/activity/webviews/WebViewViewModel;)V", "getAd", "", "handleOnReceivedErrorWebview", "errorCode", "failingUrl", "", "hideCustomeView", "isAdExpandedByUser", "expanded", "isScreenSpecificMapSet", "loadLink", "onAdAvailable", "onAdNotAvilable", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPageLoaded", "onPause", "onResume", "onTimeOut", "setScreenSpecificMap", "setScreenSpecificMapSet", "setUpViews", "setUpWebView", "setupActionBar", "setupAssetID", "link", "assetID", "setupObservers", "showErrorSnackBar", "update", "observable", "Ljava/util/Observable;", "arg", "", "MyJavaScriptInterface", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CommonWebViewActivity.kt */
public class CommonWebViewActivity extends BaseActivity implements CustomChromeClient.OnPageLoaded, OnAdListener, DFPNewsAdListener, Observer {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public CustomChromeClient chromeClient;
    /* access modifiers changed from: private */
    public int eventsCounter;
    public PlatformRouteDispatcher firebaseRouter;
    /* access modifiers changed from: private */
    public boolean isRecapArticle;
    public WebView mWebView;
    /* access modifiers changed from: private */
    public int omnitureCallsCounter;
    public ProgressBar progress;
    public RelativeLayout rootView;
    /* access modifiers changed from: private */
    public boolean skipADInterface;
    private boolean skipFirstonResume = true;
    public WebViewViewModel webViewModel;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final WebView getMWebView() {
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        return webView;
    }

    public final void setMWebView(WebView webView) {
        Intrinsics.checkNotNullParameter(webView, "<set-?>");
        this.mWebView = webView;
    }

    public final ProgressBar getProgress() {
        ProgressBar progressBar = this.progress;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_PROGRESS);
        }
        return progressBar;
    }

    public final void setProgress(ProgressBar progressBar) {
        Intrinsics.checkNotNullParameter(progressBar, "<set-?>");
        this.progress = progressBar;
    }

    public final WebViewViewModel getWebViewModel() {
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        return webViewViewModel;
    }

    public final void setWebViewModel(WebViewViewModel webViewViewModel) {
        Intrinsics.checkNotNullParameter(webViewViewModel, "<set-?>");
        this.webViewModel = webViewViewModel;
    }

    public final RelativeLayout getRootView() {
        RelativeLayout relativeLayout = this.rootView;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return relativeLayout;
    }

    public final void setRootView(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.rootView = relativeLayout;
    }

    public final PlatformRouteDispatcher getFirebaseRouter() {
        PlatformRouteDispatcher platformRouteDispatcher = this.firebaseRouter;
        if (platformRouteDispatcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firebaseRouter");
        }
        return platformRouteDispatcher;
    }

    public final void setFirebaseRouter(PlatformRouteDispatcher platformRouteDispatcher) {
        Intrinsics.checkNotNullParameter(platformRouteDispatcher, "<set-?>");
        this.firebaseRouter = platformRouteDispatcher;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_base_web_view);
        super.setupAd();
        ViewModel viewModel = ViewModelProviders.of((FragmentActivity) this).get(WebViewViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(th…iewViewModel::class.java)");
        WebViewViewModel webViewViewModel = (WebViewViewModel) viewModel;
        this.webViewModel = webViewViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        Activity activity = this;
        webViewViewModel.setIntentData(activity, getIntent());
        WebViewViewModel webViewViewModel2 = this.webViewModel;
        if (webViewViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (webViewViewModel2.isAutoRotate()) {
            setRequestedOrientation(-1);
        } else {
            setRequestedOrientation(1);
        }
        this.firebaseRouter = new PlatformRouteDispatcher(activity, false, true);
        WebViewViewModel webViewViewModel3 = this.webViewModel;
        if (webViewViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (webViewViewModel3.getLink() != null) {
            WebViewViewModel webViewViewModel4 = this.webViewModel;
            if (webViewViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
            }
            String link = webViewViewModel4.getLink();
            Boolean bool = null;
            if (link != null) {
                bool = Boolean.valueOf(StringsKt.contains$default((CharSequence) link, (CharSequence) "recap", false, 2, (Object) null));
            }
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                this.isRecapArticle = true;
                this.eventsCounter++;
                PlatformRouteDispatcher platformRouteDispatcher = this.firebaseRouter;
                if (platformRouteDispatcher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("firebaseRouter");
                }
                platformRouteDispatcher.routeEvent("RecapLaunch_" + this.eventsCounter);
            }
        }
        setupActionBar();
        setupObservers();
        setUpViews();
        this.skipFirstonResume = true;
    }

    private final void setupObservers() {
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        webViewViewModel.getLeadConceptSegVar().observe(this, new CommonWebViewActivity$setupObservers$1(this));
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        ActionBar supportActionBar;
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setDisplayHomeAsUpEnabled(true);
        }
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        Context context = this;
        webViewViewModel.setActionBarTitle(context, getSupportActionBar(), getTitle());
        if (getSupportActionBar() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.medscape_blue));
            ActionBar supportActionBar3 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar3);
            supportActionBar3.setBackgroundDrawable(colorDrawable);
            WebViewViewModel webViewViewModel2 = this.webViewModel;
            if (webViewViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
            }
            boolean z = false;
            if (webViewViewModel2.getActionBarTitleMode() != 0) {
                WebViewViewModel webViewViewModel3 = this.webViewModel;
                if (webViewViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                CharSequence title = webViewViewModel3.getTitle();
                if (title == null || title.length() == 0) {
                    z = true;
                }
                if (!z) {
                    ActionBar supportActionBar4 = getSupportActionBar();
                    Intrinsics.checkNotNull(supportActionBar4);
                    supportActionBar4.setDisplayShowTitleEnabled(true);
                    WebViewViewModel webViewViewModel4 = this.webViewModel;
                    if (webViewViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                    }
                    setTitle(webViewViewModel4.getTitle());
                    WebViewViewModel webViewViewModel5 = this.webViewModel;
                    if (webViewViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                    }
                    ActionBar supportActionBar5 = getSupportActionBar();
                    WebViewViewModel webViewViewModel6 = this.webViewModel;
                    if (webViewViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                    }
                    webViewViewModel5.setActionBarTitle(context, supportActionBar5, webViewViewModel6.getTitle());
                    return;
                }
                return;
            }
            WebViewViewModel webViewViewModel7 = this.webViewModel;
            if (webViewViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
            }
            if (webViewViewModel7.getLogoType() > -1 && (supportActionBar = getSupportActionBar()) != null) {
                WebviewUtil.Companion companion = WebviewUtil.Companion;
                WebViewViewModel webViewViewModel8 = this.webViewModel;
                if (webViewViewModel8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                supportActionBar.setLogo(companion.getActionBarLogo(context, webViewViewModel8.getLogoType()));
            }
            ActionBar supportActionBar6 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar6);
            supportActionBar6.setDisplayShowTitleEnabled(false);
        }
    }

    public final void setUpViews() {
        View findViewById = findViewById(R.id.base_webview_progress);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.base_webview_progress)");
        this.progress = (ProgressBar) findViewById;
        View findViewById2 = findViewById(R.id.base_root_view);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.base_root_view)");
        this.rootView = (RelativeLayout) findViewById2;
        setUpWebView();
    }

    public final void setUpWebView() {
        Context context = this;
        Util.setCookie(context);
        View findViewById = findViewById(R.id.base_webview);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.base_webview)");
        WebView webView = (WebView) findViewById;
        this.mWebView = webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        WebSettings settings = webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "mWebView.settings");
        settings.setJavaScriptEnabled(true);
        WebView webView2 = this.mWebView;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        WebSettings settings2 = webView2.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings2, "mWebView.settings");
        settings2.setDomStorageEnabled(true);
        WebView webView3 = this.mWebView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        WebSettings settings3 = webView3.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings3, "mWebView.settings");
        settings3.setPluginState(WebSettings.PluginState.ON);
        WebView webView4 = this.mWebView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        WebSettings settings4 = webView4.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings4, "mWebView.settings");
        WebView webView5 = this.mWebView;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        settings4.setUserAgentString(Util.addUserAgent(webView5, context));
        WebView webView6 = this.mWebView;
        if (webView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView6.setScrollBarStyle(33554432);
        WebView webView7 = this.mWebView;
        if (webView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView7.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        Activity activity = this;
        CustomChromeClient.OnPageLoaded onPageLoaded = this;
        WebView webView8 = this.mWebView;
        if (webView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        this.chromeClient = new CustomChromeClient(activity, onPageLoaded, webView8);
        WebView webView9 = this.mWebView;
        if (webView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView9.setWebChromeClient(this.chromeClient);
        WebView webView10 = this.mWebView;
        if (webView10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView10.setWebViewClient(new CommonWebViewActivity$setUpWebView$1(this));
        WebView webView11 = this.mWebView;
        if (webView11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView11.clearHistory();
        loadLink();
        WebView webView12 = this.mWebView;
        if (webView12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView12.setDownloadListener(new CommonWebViewActivity$setUpWebView$2(this));
    }

    /* access modifiers changed from: private */
    public final void loadLink() {
        if (Util.isOnline(this)) {
            WebView webView = this.mWebView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            String url = webView.getUrl();
            CharSequence charSequence = url;
            if (charSequence == null || StringsKt.isBlank(charSequence)) {
                WebViewViewModel webViewViewModel = this.webViewModel;
                if (webViewViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                url = webViewViewModel.getLink();
            }
            if (url != null) {
                WebView webView2 = this.mWebView;
                if (webView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                }
                webView2.loadUrl(url);
            }
            ProgressBar progressBar = this.progress;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_PROGRESS);
            }
            progressBar.setVisibility(0);
            return;
        }
        ProgressBar progressBar2 = this.progress;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_PROGRESS);
        }
        progressBar2.setVisibility(8);
        showErrorSnackBar();
    }

    /* access modifiers changed from: private */
    public final void handleOnReceivedErrorWebview(int i, String str) {
        if (!Util.isOnline(this)) {
            showErrorSnackBar();
        } else if (str != null && !StringsKt.endsWith$default(str, ".pdf", false, 2, (Object) null) && !StringsKt.endsWith$default(str, ".mp3", false, 2, (Object) null) && !StringsKt.endsWith$default(str, ".mp4", false, 2, (Object) null) && i > 0 && i == -8) {
            showErrorSnackBar();
        }
    }

    public void onBackPressed() {
        CustomChromeClient customChromeClient = this.chromeClient;
        if (customChromeClient == null || !customChromeClient.inCustomView()) {
            WebView webView = this.mWebView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            webView.stopLoading();
            WebView webView2 = this.mWebView;
            if (webView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            boolean z = false;
            if (webView2.getUrl() != null) {
                WebView webView3 = this.mWebView;
                if (webView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                }
                String url = webView3.getUrl();
                Boolean bool = null;
                if (url != null) {
                    bool = Boolean.valueOf(StringsKt.contains$default((CharSequence) url, (CharSequence) "submit-irm.trustarc.com", false, 2, (Object) null));
                }
                Intrinsics.checkNotNull(bool);
                if (!bool.booleanValue()) {
                    z = true;
                }
            }
            WebView webView4 = this.mWebView;
            if (webView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            if (!webView4.canGoBack() || !z) {
                if (this.isRecapArticle) {
                    this.eventsCounter++;
                    PlatformRouteDispatcher platformRouteDispatcher = this.firebaseRouter;
                    if (platformRouteDispatcher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("firebaseRouter");
                    }
                    platformRouteDispatcher.routeEvent("Close_" + this.eventsCounter);
                }
                finish();
                return;
            }
            WebView webView5 = this.mWebView;
            if (webView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            webView5.goBack();
            return;
        }
        CustomChromeClient customChromeClient2 = this.chromeClient;
        if (customChromeClient2 != null) {
            customChromeClient2.onHideCustomView();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView.onPause();
        hideCustomeView();
    }

    private final void hideCustomeView() {
        CustomChromeClient customChromeClient;
        CustomChromeClient customChromeClient2 = this.chromeClient;
        if (customChromeClient2 != null && customChromeClient2.inCustomView() && (customChromeClient = this.chromeClient) != null) {
            customChromeClient.onHideCustomView();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (webViewViewModel.isSavable()) {
            WebViewViewModel webViewViewModel2 = this.webViewModel;
            if (webViewViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
            }
            if (webViewViewModel2.isContentSaved(this)) {
                getMenuInflater().inflate(R.menu.content_page_save_full, menu);
                return true;
            }
            getMenuInflater().inflate(R.menu.content_page_save_empty, menu);
            return true;
        }
        WebViewViewModel webViewViewModel3 = this.webViewModel;
        if (webViewViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (!webViewViewModel3.isShareable()) {
            return true;
        }
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }

    public void onPageLoaded() {
        boolean z;
        ProgressBar progressBar = this.progress;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_PROGRESS);
        }
        progressBar.setVisibility(8);
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        String url = webView.getUrl();
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (webViewViewModel.getActionBarTitleMode() == 2) {
            if (url != null) {
                WebViewViewModel webViewViewModel2 = this.webViewModel;
                if (webViewViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                z = url.equals(webViewViewModel2.getLink());
            } else {
                z = false;
            }
            if (z) {
                WebViewViewModel webViewViewModel3 = this.webViewModel;
                if (webViewViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                webViewViewModel3.setActionBarTitle(this, getSupportActionBar(), getTitle());
            } else {
                WebViewViewModel webViewViewModel4 = this.webViewModel;
                if (webViewViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                Context context = this;
                ActionBar supportActionBar = getSupportActionBar();
                WebView webView2 = this.mWebView;
                if (webView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                }
                String title = webView2.getTitle();
                webViewViewModel4.setActionBarTitle(context, supportActionBar, title != null ? title : getTitle());
            }
        }
        if (url == null || !StringsKt.contains$default((CharSequence) url, (CharSequence) "about:blank", false, 2, (Object) null)) {
            WebView webView3 = this.mWebView;
            if (webView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            webView3.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        }
        WebView webView4 = this.mWebView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView4.loadUrl("javascript:document.getElementById('medscapeheader').style.display = 'none';");
        WebView webView5 = this.mWebView;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView5.loadUrl("javascript:document.getElementById('medscapefooter').style.display = 'none';");
    }

    public void onTimeOut() {
        ProgressBar progressBar = this.progress;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException(NotificationCompat.CATEGORY_PROGRESS);
        }
        progressBar.setVisibility(8);
        showErrorSnackBar();
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView.stopLoading();
    }

    /* access modifiers changed from: private */
    public final void showErrorSnackBar() {
        CMEPulseException cMEPulseException = new CMEPulseException(getString(R.string.error_connection_required));
        RelativeLayout relativeLayout = this.rootView;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        cMEPulseException.showSnackBar(relativeLayout, -2, getString(R.string.retry), new CommonWebViewActivity$showErrorSnackBar$1(this));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        PendingIntent pendingIntent;
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            case R.id.action_remove:
            case R.id.action_save:
                WebViewViewModel webViewViewModel = this.webViewModel;
                if (webViewViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                Context context = this;
                WebView webView = this.mWebView;
                if (webView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                }
                webViewViewModel.onClickSaveIcon(context, webView, this);
                invalidateOptionsMenu();
                return true;
            case R.id.action_share:
                WebViewViewModel webViewViewModel2 = this.webViewModel;
                if (webViewViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                String title = webViewViewModel2.getTitle();
                WebViewViewModel webViewViewModel3 = this.webViewModel;
                if (webViewViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                String link = webViewViewModel3.getLink();
                WebViewViewModel webViewViewModel4 = this.webViewModel;
                if (webViewViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                if (webViewViewModel4.getLogoType() == -1) {
                    WebView webView2 = this.mWebView;
                    if (webView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                    }
                    title = webView2.getTitle();
                    WebView webView3 = this.mWebView;
                    if (webView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                    }
                    link = webView3.getUrl();
                }
                String str = title;
                if (link != null) {
                    CharSequence charSequence = link;
                    if (StringsKt.contains$default(charSequence, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null)) {
                        link = link.substring(0, StringsKt.indexOf$default(charSequence, TypeDescription.Generic.OfWildcardType.SYMBOL, 0, false, 6, (Object) null));
                        Intrinsics.checkNotNullExpressionValue(link, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    }
                }
                String str2 = link;
                Context context2 = this;
                PendingIntent createPendingIntent = new ShareReceiver().createPendingIntent(context2);
                if (Build.VERSION.SDK_INT <= 22) {
                    OmnitureManager.get().trackModule(context2, "news", ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), AdParameterKeys.SECTION_ID, (Map<String, Object>) null);
                    pendingIntent = null;
                } else {
                    ShareDataObservable.INSTANCE.addObserver(this);
                    pendingIntent = createPendingIntent;
                }
                com.wbmd.wbmdcommons.utils.Util.share(context2, str2, str, "", "", pendingIntent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/activity/webviews/CommonWebViewActivity$MyJavaScriptInterface;", "", "(Lcom/medscape/android/activity/webviews/CommonWebViewActivity;)V", "processHTML", "", "html", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: CommonWebViewActivity.kt */
    public final class MyJavaScriptInterface {
        public MyJavaScriptInterface() {
        }

        @JavascriptInterface
        public final void processHTML(String str) {
            Intrinsics.checkNotNullParameter(str, "html");
            if (CommonWebViewActivity.this.getWebViewModel().getWithAds() && !CommonWebViewActivity.this.skipADInterface) {
                CommonWebViewActivity.this.getWebViewModel().getScreenSpecificMap().putAll(NewsManager.getScreenMapsFromHtml(str));
                CommonWebViewActivity.this.setScreenSpecificMap();
                CommonWebViewActivity.this.setScreenSpecificMapSet();
                CommonWebViewActivity.this.skipADInterface = true;
                DeprecatedKt.onUiThread((Context) CommonWebViewActivity.this, (Function1<? super Context, Unit>) new CommonWebViewActivity$MyJavaScriptInterface$processHTML$1(this));
            }
        }
    }

    public final synchronized boolean isScreenSpecificMapSet() {
        WebViewViewModel webViewViewModel;
        webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        return webViewViewModel.getScreenSpecificMapSet();
    }

    public final synchronized void setScreenSpecificMapSet() {
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        webViewViewModel.setScreenSpecificMapSet(true);
    }

    public final void setScreenSpecificMap() {
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        webViewViewModel.getScreenSpecificMap().put("pos", getResources().getString(R.string.banner_ad_pos));
        WebViewViewModel webViewViewModel2 = this.webViewModel;
        if (webViewViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        webViewViewModel2.getScreenSpecificMap().put("pc", "pc");
        WebViewViewModel webViewViewModel3 = this.webViewModel;
        if (webViewViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        webViewViewModel3.getScreenSpecificMap().put("pvid", this.mPvid);
        WebViewViewModel webViewViewModel4 = this.webViewModel;
        if (webViewViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (webViewViewModel4.getScreenSpecificMap().containsKey(AdContentData.LEAD_CONCEPT)) {
            WebViewViewModel webViewViewModel5 = this.webViewModel;
            if (webViewViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
            }
            MutableLiveData<String> leadConceptSegVar = webViewViewModel5.getLeadConceptSegVar();
            WebViewViewModel webViewViewModel6 = this.webViewModel;
            if (webViewViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
            }
            leadConceptSegVar.postValue(webViewViewModel6.getScreenSpecificMap().get(AdContentData.LEAD_CONCEPT));
        }
        WebViewViewModel webViewViewModel7 = this.webViewModel;
        if (webViewViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        String str = webViewViewModel7.getScreenSpecificMap().get(AdsConstants.ASSET_ID);
        WebViewViewModel webViewViewModel8 = this.webViewModel;
        if (webViewViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        String str2 = setupAssetID(webViewViewModel8.getLink(), str);
        ClickStreamManager clickStreamManager = ClickStreamManager.INSTANCE;
        Context context = this;
        ClickstreamConstants.EventType eventType = ClickstreamConstants.EventType.pageView;
        WebViewViewModel webViewViewModel9 = this.webViewModel;
        if (webViewViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        String title = webViewViewModel9.getTitle();
        if (title == null) {
            title = "";
        }
        String str3 = title;
        WebViewViewModel webViewViewModel10 = this.webViewModel;
        if (webViewViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        clickStreamManager.sendEvent(context, eventType, str3, webViewViewModel10.getScreenSpecificMap(), (Impression[]) null, (String[]) null, this.mPvid, str2);
    }

    public final String setupAssetID(String str, String str2) {
        if (str == null) {
            return "";
        }
        URI uri = new URI(str);
        String replace$default = StringsKt.replace$default(StringsKt.replace$default(str, "https://", "", false, 4, (Object) null), uri.getHost().toString(), "", false, 4, (Object) null);
        String path = uri.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "articleURI.path");
        String path2 = uri.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "articleURI.path");
        int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) path2, '/', 0, false, 6, (Object) null) + 1;
        if (path != null) {
            String substring = path.substring(lastIndexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
            return String.valueOf(new ChronicleIDUtil().generateAssetId(substring, str2, replace$default));
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView.onResume();
        if (this.skipFirstonResume) {
            this.skipFirstonResume = false;
        } else {
            getAd();
        }
    }

    /* access modifiers changed from: private */
    public final void getAd() {
        WebViewViewModel webViewViewModel = this.webViewModel;
        if (webViewViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
        }
        if (webViewViewModel.getWithAds() && Util.isOnline(this) && !this.isPause && this.adAction != null) {
            WebView webView = this.mWebView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            Util.setContainerRule(false, webView, R.id.ad);
            this.adAction.setOnUpdateListener(this);
            NewsManager.metaString = "";
            if (isScreenSpecificMapSet()) {
                WebViewViewModel webViewViewModel2 = this.webViewModel;
                if (webViewViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("webViewModel");
                }
                DFPAdAction dFPAdAction = this.adAction;
                Intrinsics.checkNotNullExpressionValue(dFPAdAction, "adAction");
                webViewViewModel2.makeADCall(dFPAdAction);
            }
        }
    }

    public void onAdAvailable() {
        View view = this.adLayout;
        Intrinsics.checkNotNullExpressionValue(view, "adLayout");
        view.setVisibility(0);
        if (this.adView != null) {
            PublisherAdView publisherAdView = this.adView;
            Intrinsics.checkNotNullExpressionValue(publisherAdView, "adView");
            publisherAdView.setVisibility(0);
        }
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        Util.setContainerRule(true, webView, R.id.ad);
    }

    public void onAdNotAvilable() {
        if (this.adLayout != null) {
            View view = this.adLayout;
            Intrinsics.checkNotNullExpressionValue(view, "adLayout");
            view.setVisibility(8);
        }
    }

    public void isAdExpandedByUser(boolean z) {
        throw new NotImplementedError("An operation is not implemented: " + "not implemented");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView.destroy();
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof ShareDataObservable) {
            if (obj instanceof String) {
                OmnitureManager.get().trackModule(this, "news", ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), (String) obj, (Map<String, Object>) null);
            }
            ShareDataObservable.INSTANCE.deleteObserver(this);
        }
    }
}
