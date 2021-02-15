package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import java.util.concurrent.atomic.AtomicBoolean;

public class PullToRefreshWebView2 extends PullToRefreshWebView {
    static final String DEF_JS_READY_PULL_DOWN_CALL = "javascript:isReadyForPullDown();";
    static final String DEF_JS_READY_PULL_UP_CALL = "javascript:isReadyForPullUp();";
    static final String JS_INTERFACE_PKG = "ptr";
    /* access modifiers changed from: private */
    public final AtomicBoolean mIsReadyForPullDown = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final AtomicBoolean mIsReadyForPullUp = new AtomicBoolean(false);
    private JsValueCallback mJsCallback;

    public PullToRefreshWebView2(Context context) {
        super(context);
    }

    public PullToRefreshWebView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshWebView2(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    /* access modifiers changed from: protected */
    public WebView createRefreshableView(Context context, AttributeSet attributeSet) {
        WebView createRefreshableView = super.createRefreshableView(context, attributeSet);
        JsValueCallback jsValueCallback = new JsValueCallback();
        this.mJsCallback = jsValueCallback;
        createRefreshableView.addJavascriptInterface(jsValueCallback, JS_INTERFACE_PKG);
        return createRefreshableView;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullStart() {
        ((WebView) getRefreshableView()).loadUrl(DEF_JS_READY_PULL_DOWN_CALL);
        return this.mIsReadyForPullDown.get();
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullEnd() {
        ((WebView) getRefreshableView()).loadUrl(DEF_JS_READY_PULL_UP_CALL);
        return this.mIsReadyForPullUp.get();
    }

    final class JsValueCallback {
        JsValueCallback() {
        }

        public void isReadyForPullUpResponse(boolean z) {
            PullToRefreshWebView2.this.mIsReadyForPullUp.set(z);
        }

        public void isReadyForPullDownResponse(boolean z) {
            PullToRefreshWebView2.this.mIsReadyForPullDown.set(z);
        }
    }
}
