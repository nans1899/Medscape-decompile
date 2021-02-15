package com.medscape.android.slideshow;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.medscape.android.R;
import com.medscape.android.activity.rss.NewsManager;
import com.medscape.android.activity.rss.RSSExternalArticleActivity;
import com.medscape.android.parser.model.Slideshow;
import com.medscape.android.util.Util;

public class SlideshowPageFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    Bundle args;
    WebView bottom;
    View bottomPane;
    View divider;
    View header;
    /* access modifiers changed from: private */
    public int mBottomPaneLandscapeHeight = -1;
    /* access modifiers changed from: private */
    public int mBottomPaneLandscapeWidth = -1;
    /* access modifiers changed from: private */
    public int mBottomPanePortraitHeight = -1;
    /* access modifiers changed from: private */
    public int mBottomPanePortraitWidth = -1;
    private MyChromeClient mClient;
    /* access modifiers changed from: private */
    public View mCustomView;
    /* access modifiers changed from: private */
    public WebChromeClient.CustomViewCallback mCustomViewCallback;
    /* access modifiers changed from: private */
    public LinearLayout mSlide;
    /* access modifiers changed from: private */
    public FrameLayout mTargetView;
    /* access modifiers changed from: private */
    public int mTopPaneLandscapeHeight = -1;
    /* access modifiers changed from: private */
    public int mTopPaneLandscapeWidth = -1;
    /* access modifiers changed from: private */
    public int mTopPanePortraitHeight = -1;
    /* access modifiers changed from: private */
    public int mTopPanePortraitWidth = -1;
    WebView top;
    View topPane;

    public static SlideshowPageFragment newInstance(Slideshow slideshow, int i) {
        new SlideshowPageFragment();
        SlideshowPageFragment slideshowPageFragment = new SlideshowPageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("activityId", slideshow.activityId);
        bundle.putString("parId", slideshow.parId);
        bundle.putString("tacticId", slideshow.tacticId);
        bundle.putString("resumeUrl", slideshow.resumeUrl);
        bundle.putBoolean("showAds", slideshow.showAds);
        bundle.putString("audioUrl", slideshow.slides[i].audioUrl);
        bundle.putString("notesUrl", slideshow.slides[i].notesUrl);
        bundle.putString("topPaneUrl", slideshow.slides[i].topPane.url);
        bundle.putString("bottomPaneUrl", slideshow.slides[i].bottomPane != null ? slideshow.slides[i].bottomPane.url : null);
        bundle.putDouble("topScale", slideshow.slides[i].topScale);
        bundle.putDouble("bottomScale", 1.0d - slideshow.slides[i].topScale);
        bundle.putInt("slideNumber", i);
        bundle.putInt("totalSlides", slideshow.slides.length);
        bundle.putString("slideTitle", i + " of " + slideshow.slides.length);
        bundle.putBoolean("reverseLandscape", slideshow.slides[i].reverseLandscape);
        bundle.putBoolean("topShowsZoom", slideshow.slides[i].topPane.showsZoom);
        bundle.putBoolean("bottomShowsZoom", slideshow.slides[i].bottomPane != null ? slideshow.slides[i].bottomPane.showsZoom : false);
        slideshowPageFragment.setArguments(bundle);
        return slideshowPageFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.args = getArguments();
    }

    public void onActivityCreated(Bundle bundle) {
        int i;
        super.onActivityCreated(bundle);
        View view = getView();
        this.mSlide = (LinearLayout) view.findViewById(R.id.slide);
        this.topPane = view.findViewById(R.id.topView);
        WebView webView = (WebView) view.findViewById(R.id.topWebview);
        this.top = webView;
        webView.getSettings().setDomStorageEnabled(true);
        this.top.getSettings().setJavaScriptEnabled(true);
        this.top.getSettings().setUserAgentString(Util.addUserAgent(this.top, getActivity()));
        if (Build.VERSION.SDK_INT >= 14) {
            this.top.getSettings().setUseWideViewPort(true);
            this.top.getSettings().setLoadWithOverviewMode(true);
        }
        this.mClient = new MyChromeClient();
        this.mTargetView = (FrameLayout) view.findViewById(R.id.target_view);
        this.top.setWebChromeClient(this.mClient);
        float f = (float) ((!isLandscape() || !this.args.getBoolean("reverseLandscape")) ? this.args.getDouble("topScale") : this.args.getDouble("bottomScale"));
        float f2 = (float) ((!isLandscape() || !this.args.getBoolean("reverseLandscape")) ? this.args.getDouble("bottomScale") : this.args.getDouble("topScale"));
        String string = (!isLandscape() || !this.args.getBoolean("reverseLandscape")) ? this.args.getString("topPaneUrl") : this.args.getString("bottomPaneUrl");
        boolean z = this.args.getBoolean("topShowsZoom");
        View findViewById = view.findViewById(R.id.topZoomin);
        if (string == null || f <= 0.0f) {
            i = 8;
            this.topPane.setVisibility(8);
        } else {
            i = 8;
            loadPane(this.topPane, this.top, f2, string, z, findViewById);
        }
        View findViewById2 = view.findViewById(R.id.divider);
        this.divider = findViewById2;
        findViewById2.setVisibility(this.topPane.getVisibility());
        this.bottomPane = view.findViewById(R.id.bottomView);
        WebView webView2 = (WebView) view.findViewById(R.id.bottomWebview);
        this.bottom = webView2;
        webView2.getSettings().setDomStorageEnabled(true);
        this.bottom.getSettings().setJavaScriptEnabled(true);
        this.bottom.getSettings().setUserAgentString(Util.addUserAgent(this.bottom, getActivity()));
        this.bottom.setWebChromeClient(this.mClient);
        String string2 = (!isLandscape() || !this.args.getBoolean("reverseLandscape")) ? this.args.getString("bottomPaneUrl") : this.args.getString("topPaneUrl");
        boolean z2 = this.args.getBoolean("bottomShowsZoom");
        View findViewById3 = view.findViewById(R.id.bottomZoomin);
        if (string2 == null || f2 <= 0.0f) {
            this.bottomPane.setVisibility(i);
        } else {
            loadPane(this.bottomPane, this.bottom, f, string2, z2, findViewById3);
        }
        this.topPane.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SlideshowPageFragment.this.topPane.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (SlideshowPageFragment.this.getResources().getConfiguration().orientation == 2) {
                    if (SlideshowPageFragment.this.mTopPaneLandscapeHeight < 0) {
                        SlideshowPageFragment slideshowPageFragment = SlideshowPageFragment.this;
                        int unused = slideshowPageFragment.mTopPaneLandscapeHeight = slideshowPageFragment.topPane.getHeight();
                    }
                    if (SlideshowPageFragment.this.mTopPaneLandscapeWidth < 0) {
                        SlideshowPageFragment slideshowPageFragment2 = SlideshowPageFragment.this;
                        int unused2 = slideshowPageFragment2.mTopPaneLandscapeWidth = slideshowPageFragment2.topPane.getWidth();
                        return;
                    }
                    return;
                }
                if (SlideshowPageFragment.this.mTopPanePortraitHeight < 0) {
                    SlideshowPageFragment slideshowPageFragment3 = SlideshowPageFragment.this;
                    int unused3 = slideshowPageFragment3.mTopPanePortraitHeight = slideshowPageFragment3.topPane.getHeight();
                }
                if (SlideshowPageFragment.this.mTopPanePortraitWidth < 0) {
                    SlideshowPageFragment slideshowPageFragment4 = SlideshowPageFragment.this;
                    int unused4 = slideshowPageFragment4.mTopPanePortraitWidth = slideshowPageFragment4.topPane.getWidth();
                }
            }
        });
        this.bottomPane.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                SlideshowPageFragment.this.bottomPane.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if (SlideshowPageFragment.this.getResources().getConfiguration().orientation == 2) {
                    if (SlideshowPageFragment.this.mBottomPaneLandscapeHeight < 0) {
                        SlideshowPageFragment slideshowPageFragment = SlideshowPageFragment.this;
                        int unused = slideshowPageFragment.mBottomPaneLandscapeHeight = slideshowPageFragment.bottomPane.getHeight();
                    }
                    if (SlideshowPageFragment.this.mBottomPaneLandscapeWidth < 0) {
                        SlideshowPageFragment slideshowPageFragment2 = SlideshowPageFragment.this;
                        int unused2 = slideshowPageFragment2.mBottomPaneLandscapeWidth = slideshowPageFragment2.bottomPane.getWidth();
                        return;
                    }
                    return;
                }
                if (SlideshowPageFragment.this.mBottomPanePortraitHeight < 0) {
                    SlideshowPageFragment slideshowPageFragment3 = SlideshowPageFragment.this;
                    int unused3 = slideshowPageFragment3.mBottomPanePortraitHeight = slideshowPageFragment3.bottomPane.getHeight();
                }
                if (SlideshowPageFragment.this.mBottomPanePortraitWidth < 0) {
                    SlideshowPageFragment slideshowPageFragment4 = SlideshowPageFragment.this;
                    int unused4 = slideshowPageFragment4.mBottomPanePortraitWidth = slideshowPageFragment4.bottomPane.getWidth();
                }
            }
        });
    }

    private void setMediaPlaybackRequiresUserGesture(WebView webView) {
        if (Build.VERSION.SDK_INT >= 17) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            WebView webView = this.top;
            if (webView != null) {
                setMediaPlaybackRequiresUserGesture(webView);
                this.top.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
            }
            WebView webView2 = this.bottom;
            if (webView2 != null) {
                setMediaPlaybackRequiresUserGesture(webView2);
                this.bottom.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_slide_collection_object, viewGroup, false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            if (this.mTopPaneLandscapeHeight > 0) {
                this.topPane.setLayoutParams(new LinearLayout.LayoutParams(this.mTopPaneLandscapeWidth, this.mTopPaneLandscapeHeight));
                this.bottomPane.setLayoutParams(new LinearLayout.LayoutParams(this.mBottomPaneLandscapeWidth, this.mBottomPaneLandscapeHeight));
            } else {
                this.topPane.setLayoutParams(new LinearLayout.LayoutParams(0, -1, (float) this.args.getDouble("topScale")));
                this.bottomPane.setLayoutParams(new LinearLayout.LayoutParams(0, -1, (float) this.args.getDouble("bottomScale")));
            }
            this.divider.setLayoutParams(new LinearLayout.LayoutParams(1, -1));
            this.mSlide.setOrientation(0);
            this.mSlide.removeAllViews();
            if (!isLandscape() || !this.args.getBoolean("reverseLandscape")) {
                this.mSlide.addView(this.bottomPane);
                this.mSlide.addView(this.divider);
                this.mSlide.addView(this.topPane);
            } else {
                this.mSlide.addView(this.topPane);
                this.mSlide.addView(this.divider);
                this.mSlide.addView(this.bottomPane);
            }
        } else if (configuration.orientation == 1) {
            if (this.mTopPanePortraitHeight > 0) {
                this.topPane.setLayoutParams(new LinearLayout.LayoutParams(this.mTopPanePortraitWidth, this.mTopPanePortraitHeight));
                this.bottomPane.setLayoutParams(new LinearLayout.LayoutParams(this.mBottomPanePortraitWidth, this.mBottomPanePortraitHeight));
            } else {
                this.topPane.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, (float) this.args.getDouble("topScale")));
                this.bottomPane.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, (float) this.args.getDouble("bottomScale")));
            }
            this.divider.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            this.mSlide.setOrientation(1);
            this.mSlide.removeAllViews();
            this.mSlide.addView(this.topPane);
            this.mSlide.addView(this.divider);
            this.mSlide.addView(this.bottomPane);
        }
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.top.getSettings().setLoadWithOverviewMode(true);
        this.top.getSettings().setUseWideViewPort(true);
        this.bottom.getSettings().setLoadWithOverviewMode(true);
        this.bottom.getSettings().setUseWideViewPort(true);
    }

    private void setScale(View view, float f) {
        ((LinearLayout.LayoutParams) view.getLayoutParams()).weight = f;
    }

    private void loadPane(View view, final WebView webView, float f, final String str, boolean z, View view2) {
        setScale(view, f);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Intent intent;
                if (str == null) {
                    return false;
                }
                Uri parse = Uri.parse(str);
                if (parse != null && "openmodal".equalsIgnoreCase(parse.getScheme())) {
                    SlideshowPageFragment.this.showFullScreenModal(parse.getQueryParameter("url"), false, true);
                    return true;
                } else if (parse != null && "slideshow".equalsIgnoreCase(parse.getScheme())) {
                    String contentUrlFromSchemeSpecificPart = SlideshowUtil.getContentUrlFromSchemeSpecificPart(parse.getSchemeSpecificPart());
                    Uri parse2 = Uri.parse(contentUrlFromSchemeSpecificPart);
                    String queryParameter = parse2.getQueryParameter("orientationLock");
                    String queryParameter2 = parse2.getQueryParameter("slideType");
                    Intent intent2 = new Intent(SlideshowPageFragment.this.getActivity(), SlideshowViewer.class);
                    Intent putExtra = intent2.putExtra("slideshowUrl", contentUrlFromSchemeSpecificPart + SlideshowUtil.toAppend(contentUrlFromSchemeSpecificPart)).putExtra("orientationLock", queryParameter).putExtra("slideType", queryParameter2);
                    SlideshowPageFragment.this.getActivity().finish();
                    SlideshowPageFragment.this.startActivity(putExtra);
                    return true;
                } else if (parse == null || parse.getScheme() == null || !parse.getScheme().equalsIgnoreCase("viewimage")) {
                    if ("portrait".equalsIgnoreCase(((SlideshowViewer) SlideshowPageFragment.this.getActivity()).orientationLock)) {
                        intent = new Intent(SlideshowPageFragment.this.getActivity(), LinkBrowserPortraitActivity.class);
                    } else if ("landscape".equalsIgnoreCase(((SlideshowViewer) SlideshowPageFragment.this.getActivity()).orientationLock)) {
                        intent = new Intent(SlideshowPageFragment.this.getActivity(), LinkBrowserLandscapeActivity.class);
                    } else {
                        intent = new Intent(SlideshowPageFragment.this.getActivity(), RSSExternalArticleActivity.class);
                    }
                    intent.putExtra("url", str);
                    intent.putExtra("contentType", "promo");
                    SlideshowPageFragment.this.startActivity(intent);
                    return true;
                } else {
                    String queryParameter3 = parse.getQueryParameter("src");
                    Intent intent3 = new Intent(SlideshowPageFragment.this.getActivity(), BrandAdvanceImageViewer.class);
                    intent3.putExtra("com.medscape.android.EXTRA_WEBVIEW_URL", queryParameter3);
                    intent3.putExtra("orientationLock", ((SlideshowViewer) SlideshowPageFragment.this.getActivity()).orientationLock);
                    SlideshowPageFragment.this.startActivity(intent3);
                    return true;
                }
            }

            public void onPageFinished(WebView webView, String str) {
                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                if (SlideshowPageFragment.this.getActivity() instanceof SlideshowViewer) {
                    if (((SlideshowViewer) SlideshowPageFragment.this.getActivity()).isBrandPlay && !Util.isEmulator()) {
                        webView.loadUrl("javascript:(function() { document.getElementsByTagName('video')[0].play(); })()");
                    }
                    ((SlideshowViewer) SlideshowPageFragment.this.getActivity()).pageFinished();
                }
            }
        });
        webView.getSettings().setSupportMultipleWindows(true);
        webView.addJavascriptInterface(new AdsSegvarParserInterface(), "HTMLOUT");
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                webView.loadUrl(str);
            }
        });
        view.setVisibility(0);
        if (z) {
            view2.setVisibility(0);
            view2.setOnClickListener(new ModalViewOpener(str, true));
        }
    }

    public void zoom(View view, String str) {
        showFullScreenModal(str, true, false);
    }

    /* access modifiers changed from: private */
    public void showFullScreenModal(String str, boolean z, boolean z2) {
        ((SlideshowViewer) getActivity()).showFullScreenModal(str, false, z, z2);
    }

    class ModalViewOpener implements View.OnClickListener {
        static final String zoomSuffix = "?zoom";
        String mUrl;
        boolean zoom;

        public ModalViewOpener(String str, boolean z) {
            this.mUrl = z ? str.concat(zoomSuffix) : str;
        }

        public void onClick(View view) {
            if (this.zoom) {
                SlideshowPageFragment.this.zoom(view, this.mUrl);
            } else {
                SlideshowPageFragment.this.showFullScreenModal(this.mUrl, true, false);
            }
        }
    }

    public void onDestroyView() {
        WebView webView = this.top;
        if (webView != null) {
            ViewGroup viewGroup = (ViewGroup) webView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.top);
            }
            this.top.removeAllViews();
        }
        WebView webView2 = this.bottom;
        if (webView2 != null) {
            ViewGroup viewGroup2 = (ViewGroup) webView2.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.bottom);
            }
            this.bottom.removeAllViews();
        }
        if (getActivity() instanceof SlideshowViewer) {
            ((SlideshowViewer) getActivity()).removeFragment(this);
        }
        super.onDestroyView();
    }

    public boolean isLandscape() {
        return getResources().getConfiguration().orientation == 2;
    }

    class MyChromeClient extends WebChromeClient {
        MyChromeClient() {
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            if (SlideshowPageFragment.this.getActivity() instanceof SlideshowViewer) {
                ((SlideshowViewer) SlideshowPageFragment.this.getActivity()).hideHeader();
                ((SlideshowViewer) SlideshowPageFragment.this.getActivity()).disableSwipe();
            }
            WebChromeClient.CustomViewCallback unused = SlideshowPageFragment.this.mCustomViewCallback = customViewCallback;
            SlideshowPageFragment.this.mTargetView.addView(view);
            View unused2 = SlideshowPageFragment.this.mCustomView = view;
            SlideshowPageFragment.this.mSlide.setVisibility(8);
            SlideshowPageFragment.this.mTargetView.setVisibility(0);
            SlideshowPageFragment.this.mTargetView.bringToFront();
        }

        public void onHideCustomView() {
            SlideshowPageFragment.this.hideCustomView();
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            Intent intent;
            WebView.HitTestResult hitTestResult = webView.getHitTestResult();
            if (!(hitTestResult == null || hitTestResult.getExtra() == null)) {
                if ("portrait".equalsIgnoreCase(((SlideshowViewer) SlideshowPageFragment.this.getActivity()).orientationLock)) {
                    intent = new Intent(SlideshowPageFragment.this.getActivity(), LinkBrowserPortraitActivity.class);
                } else if ("landscape".equalsIgnoreCase(((SlideshowViewer) SlideshowPageFragment.this.getActivity()).orientationLock)) {
                    intent = new Intent(SlideshowPageFragment.this.getActivity(), LinkBrowserLandscapeActivity.class);
                } else {
                    intent = new Intent(SlideshowPageFragment.this.getActivity(), RSSExternalArticleActivity.class);
                }
                intent.putExtra("url", hitTestResult.getExtra());
                intent.putExtra("contentType", "promo");
                SlideshowPageFragment.this.startActivity(intent);
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void hideCustomView() {
        if (getActivity() instanceof SlideshowViewer) {
            ((SlideshowViewer) getActivity()).showHeader();
            ((SlideshowViewer) getActivity()).enableSwipe();
        }
        View view = this.mCustomView;
        if (view != null) {
            view.setVisibility(8);
            this.mTargetView.removeView(this.mCustomView);
            this.mCustomView = null;
            this.mTargetView.setVisibility(8);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mSlide.setVisibility(0);
            this.top.refreshDrawableState();
            this.bottom.refreshDrawableState();
            if (Build.VERSION.SDK_INT == 14 || Build.VERSION.SDK_INT == 15) {
                this.top.reload();
                this.bottom.reload();
                stopContent(this.top);
                stopContent(this.bottom);
                this.top.onPause();
                this.bottom.onPause();
            }
        }
    }

    public void stopWebview() {
        hideCustomView();
        stopContent(this.top);
        stopContent(this.bottom);
        this.top.onPause();
        this.bottom.onPause();
    }

    public void resumeWebview() {
        this.top.onResume();
        resumeContent(this.top);
        this.bottom.onResume();
        resumeContent(this.bottom);
    }

    public boolean isSlideVisible() {
        LinearLayout linearLayout = this.mSlide;
        return linearLayout == null || linearLayout.getVisibility() != 8;
    }

    public void onBackPressed() {
        if (this.mCustomView != null) {
            this.mClient.onHideCustomView();
        }
    }

    class AdsSegvarParserInterface {
        AdsSegvarParserInterface() {
        }

        @JavascriptInterface
        public void processHTML(String str) {
            String parseAdSegvars = NewsManager.parseAdSegvars(str);
            if (parseAdSegvars != null && !parseAdSegvars.isEmpty() && ((SlideshowViewer) SlideshowPageFragment.this.getActivity()) != null && SlideshowPageFragment.this.isAdded()) {
                ((SlideshowViewer) SlideshowPageFragment.this.getActivity()).setScreenSpecificMap(parseAdSegvars);
            }
        }
    }

    private void stopContent(WebView webView) {
        webView.loadUrl(InAppMessageWebViewClient.JAVASCRIPT_PREFIX + "var allVideo = document.getElementsByTagName(\"video\");for(var i = 0; i < allVideo.length; i++){    var video = allVideo[i];    video.pause();}");
        webView.loadUrl(InAppMessageWebViewClient.JAVASCRIPT_PREFIX + "var allAudio = document.getElementsByTagName(\"audio\");for(var i = 0; i < allAudio.length; i++){    var audio = allAudio[i];    audio.pause();}");
        webView.loadUrl("javascript:if (typeof stopContent == 'function') {stopContent();}");
    }

    private void resumeContent(WebView webView) {
        webView.loadUrl(InAppMessageWebViewClient.JAVASCRIPT_PREFIX + "var allVideo = document.getElementsByTagName(\"video\");for(var i = 0; i < allVideo.length; i++){    var video = allVideo[i];    if (video.paused) {\t\tvideo.play(); \tbreak;  }}");
    }

    /* access modifiers changed from: package-private */
    public void gotoAnchorInTopPane(String str) {
        WebView webView = this.top;
        webView.loadUrl("javascript:window.location.hash='" + str + "'");
    }

    /* access modifiers changed from: package-private */
    public void loadChapter(String str) {
        this.top.loadUrl(str);
    }
}
