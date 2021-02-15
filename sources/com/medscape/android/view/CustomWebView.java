package com.medscape.android.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.medscape.android.R;
import com.medscape.android.activity.search.ExternalLinkActivity;
import com.medscape.android.ads.AdWebViewAcitivity;
import com.medscape.android.custom.CustomMenu;
import com.medscape.android.slideshow.SlideshowViewer;
import com.medscape.android.util.Util;
import java.lang.reflect.InvocationTargetException;

public class CustomWebView extends WebView {
    static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
    private static final String TAG = "CustomWebView";
    private View mBrowserFrameLayout;
    protected FrameLayout mContentView;
    private Context mContext;
    /* access modifiers changed from: private */
    public View mCustomView;
    /* access modifiers changed from: private */
    public WebChromeClient.CustomViewCallback mCustomViewCallback;
    protected FrameLayout mCustomViewContainer;
    private FrameLayout mLayout;
    /* access modifiers changed from: private */
    public boolean mShouldChangeActivityTitle = true;
    private MyWebChromeClient mWebChromeClient;

    private void init(Context context) {
        setmContext(context);
        this.mLayout = new FrameLayout(context);
        MyWebChromeClient myWebChromeClient = new MyWebChromeClient();
        this.mWebChromeClient = myWebChromeClient;
        setWebChromeClient(myWebChromeClient);
        WebSettings settings = getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setUserAgentString(Util.addUserAgent(this, context));
        settings.setDomStorageEnabled(true);
        this.mShouldChangeActivityTitle = true;
    }

    /* access modifiers changed from: private */
    public void initVideo(Context context) {
        Activity activity;
        this.mContext = context;
        if (context instanceof AdWebViewAcitivity) {
            activity = (AdWebViewAcitivity) context;
        } else if (context instanceof ExternalLinkActivity) {
            activity = (ExternalLinkActivity) context;
        } else {
            activity = context instanceof SlideshowViewer ? (SlideshowViewer) context : null;
        }
        this.mContentView = (FrameLayout) activity.findViewById(R.id.main_content);
        this.mCustomViewContainer = (FrameLayout) activity.findViewById(R.id.fullscreen_custom_content);
    }

    public CustomWebView(Context context) {
        super(context);
        init(context);
    }

    public CustomWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CustomWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            getFocusedChild();
            callOnResume();
        } else {
            callOnPause();
        }
        super.onWindowFocusChanged(z);
    }

    public void destroy() {
        if (getSettings() != null) {
            getSettings().setBuiltInZoomControls(false);
            getSettings().setSupportZoom(false);
        }
        setVisibility(8);
        postDelayed(new Runnable() {
            public void run() {
                try {
                    CustomWebView.super.destroy();
                } catch (Exception unused) {
                }
            }
        }, 3000);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        destroy();
    }

    private void callOnResume() {
        if (Build.VERSION.SDK_INT >= 11) {
            onResume();
        } else {
            callMethod("onResume");
        }
    }

    private void callOnPause() {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                onPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            callMethod("onPause");
        }
    }

    private void callMethod(String str) {
        try {
            WebView.class.getMethod(str, new Class[0]).invoke(this, new Object[0]);
        } catch (NoSuchMethodException e) {
            Log.w(TAG, "callMethod: Failed to call method " + str, e);
        } catch (InvocationTargetException e2) {
            Log.w(TAG, "callMethod: Failed to call method " + str, e2);
        } catch (IllegalAccessException e3) {
            Log.w(TAG, "callMethod: Failed to call method " + str, e3);
        }
    }

    public FrameLayout getLayout() {
        return this.mLayout;
    }

    public boolean inCustomView() {
        return this.mCustomView != null;
    }

    public void hideCustomView() {
        this.mWebChromeClient.onHideCustomView();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (CustomMenu.isShowing()) {
                CustomMenu.hide();
                return true;
            } else if (this.mCustomView == null && canGoBack()) {
                goBack();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public Context getmContext() {
        return this.mContext;
    }

    public void setmContext(Context context) {
        this.mContext = context;
    }

    public void setShouldChangeActivityTitle(boolean z) {
        this.mShouldChangeActivityTitle = z;
    }

    private class MyWebChromeClient extends WebChromeClient {
        private Bitmap mDefaultVideoPoster;
        private View mVideoProgressView;

        public void onProgressChanged(WebView webView, int i) {
        }

        private MyWebChromeClient() {
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            return super.onCreateWindow(webView, z, z2, message);
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            CustomWebView.this.setVisibility(8);
            CustomWebView customWebView = CustomWebView.this;
            customWebView.initVideo(customWebView.getmContext());
            if (CustomWebView.this.mCustomView != null) {
                customViewCallback.onCustomViewHidden();
                return;
            }
            CustomWebView.this.mCustomViewContainer.addView(view);
            View unused = CustomWebView.this.mCustomView = view;
            WebChromeClient.CustomViewCallback unused2 = CustomWebView.this.mCustomViewCallback = customViewCallback;
            CustomWebView.this.mCustomViewContainer.setVisibility(0);
        }

        public void onHideCustomView() {
            if (CustomWebView.this.mCustomView != null) {
                CustomWebView.this.mCustomView.setVisibility(8);
                CustomWebView.this.mCustomViewContainer.removeView(CustomWebView.this.mCustomView);
                View unused = CustomWebView.this.mCustomView = null;
                CustomWebView.this.mCustomViewContainer.setVisibility(8);
                if (CustomWebView.this.mCustomViewCallback != null) {
                    CustomWebView.this.mCustomViewCallback.onCustomViewHidden();
                }
                try {
                    if (CustomWebView.this.mCustomViewCallback != null) {
                        CustomWebView.this.mCustomViewCallback.onCustomViewHidden();
                    }
                    if (CustomWebView.this.mCustomViewCallback != null) {
                        CustomWebView.this.mCustomViewCallback.onCustomViewHidden();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CustomWebView.this.setVisibility(0);
            }
        }

        public View getVideoLoadingProgressView() {
            return this.mVideoProgressView;
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (CustomWebView.this.mShouldChangeActivityTitle && (CustomWebView.this.getmContext() instanceof Activity)) {
                ((Activity) CustomWebView.this.getmContext()).setTitle(str);
            }
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            callback.invoke(str, true, false);
        }
    }
}
