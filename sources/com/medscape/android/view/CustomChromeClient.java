package com.medscape.android.view;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.medscape.android.R;

public class CustomChromeClient extends WebChromeClient {
    private static final FrameLayout.LayoutParams BACKGROUND_PARAMS = new FrameLayout.LayoutParams(-1, -1);
    private View activityNonVideoView;
    private ViewGroup activityVideoView;
    /* access modifiers changed from: private */
    public CountDownTimer counter;
    protected Activity mActivity = null;
    private FrameLayout mBackgroundContainer;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    public OnPageLoaded mListener;
    private WebView mWebView;
    /* access modifiers changed from: private */
    public int progress = 0;
    /* access modifiers changed from: private */
    public boolean timerCanceled = false;

    public interface OnPageLoaded {
        void onPageLoaded();

        void onTimeOut();
    }

    public CustomChromeClient(Activity activity) {
        this.mActivity = activity;
    }

    public CustomChromeClient(Activity activity, OnPageLoaded onPageLoaded) {
        this.mActivity = activity;
        this.mListener = onPageLoaded;
    }

    public CustomChromeClient(Activity activity, OnPageLoaded onPageLoaded, WebView webView) {
        this.mActivity = activity;
        this.mListener = onPageLoaded;
        this.mWebView = webView;
        this.activityNonVideoView = activity.findViewById(R.id.common_root);
        this.activityVideoView = (ViewGroup) this.mActivity.findViewById(R.id.video_full);
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.mCustomView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        this.mCustomView = view;
        this.mBackgroundContainer = (FrameLayout) view;
        this.mCustomViewCallback = customViewCallback;
        this.activityNonVideoView.setVisibility(8);
        this.activityVideoView.addView(this.mBackgroundContainer, BACKGROUND_PARAMS);
        this.activityVideoView.setVisibility(0);
        WebView webView = this.mWebView;
        if (webView != null && webView.getSettings().getJavaScriptEnabled()) {
            this.mWebView.loadUrl((((((((InAppMessageWebViewClient.JAVASCRIPT_PREFIX + "_ytrp_html5_video = document.getElementsByTagName('video')[0];") + "if (_ytrp_html5_video !== undefined) {") + "function _ytrp_html5_video_ended() {") + "_ytrp_html5_video.removeEventListener('ended', _ytrp_html5_video_ended);") + "_VideoEnabledWebView.notifyVideoEnd();") + "}") + "_ytrp_html5_video.addEventListener('ended', _ytrp_html5_video_ended);") + "}");
        }
        super.onShowCustomView(view, customViewCallback);
    }

    public void onHideCustomView() {
        if (this.mCustomView != null) {
            this.activityVideoView.setVisibility(4);
            this.activityVideoView.removeView(this.mBackgroundContainer);
            this.activityNonVideoView.setVisibility(0);
            WebChromeClient.CustomViewCallback customViewCallback = this.mCustomViewCallback;
            if (customViewCallback != null) {
                customViewCallback.onCustomViewHidden();
            }
            this.mCustomView = null;
            this.mBackgroundContainer = null;
            this.mCustomViewCallback = null;
            WebView webView = this.mWebView;
            if (webView != null && webView.getSettings().getJavaScriptEnabled()) {
                this.mWebView.loadUrl((InAppMessageWebViewClient.JAVASCRIPT_PREFIX + "var _ytrp_html5_video = document.getElementsByTagName('video')[0];") + "_ytrp_html5_video.pause();");
            }
        }
    }

    public boolean inCustomView() {
        return this.mCustomView != null;
    }

    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        this.progress = i;
    }

    public void startTimer() {
        this.timerCanceled = false;
        this.progress = 0;
        AnonymousClass1 r1 = new CountDownTimer(120000, 1000) {
            public void onTick(long j) {
                if (CustomChromeClient.this.progress == 100) {
                    if (CustomChromeClient.this.mListener != null) {
                        CustomChromeClient.this.mListener.onPageLoaded();
                        boolean unused = CustomChromeClient.this.timerCanceled = true;
                        cancel();
                    }
                    CountDownTimer unused2 = CustomChromeClient.this.counter = null;
                }
            }

            public void onFinish() {
                if (!CustomChromeClient.this.timerCanceled && CustomChromeClient.this.mListener != null) {
                    CustomChromeClient.this.mListener.onTimeOut();
                }
                CountDownTimer unused = CustomChromeClient.this.counter = null;
            }
        };
        this.counter = r1;
        r1.start();
    }
}
