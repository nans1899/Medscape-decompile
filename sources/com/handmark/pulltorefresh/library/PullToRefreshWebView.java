package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class PullToRefreshWebView extends PullToRefreshBase<WebView> {
    private static final PullToRefreshBase.OnRefreshListener<WebView> defaultOnRefreshListener = new PullToRefreshBase.OnRefreshListener<WebView>() {
        public void onRefresh(PullToRefreshBase<WebView> pullToRefreshBase) {
            pullToRefreshBase.getRefreshableView().reload();
        }
    };
    private final WebChromeClient defaultWebChromeClient = new WebChromeClient() {
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                PullToRefreshWebView.this.onRefreshComplete();
            }
        }
    };

    public PullToRefreshWebView(Context context) {
        super(context);
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView) this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
    }

    public PullToRefreshWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView) this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
    }

    public PullToRefreshWebView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView) this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
    }

    public PullToRefreshWebView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        setOnRefreshListener(defaultOnRefreshListener);
        ((WebView) this.mRefreshableView).setWebChromeClient(this.defaultWebChromeClient);
    }

    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* access modifiers changed from: protected */
    public WebView createRefreshableView(Context context, AttributeSet attributeSet) {
        WebView webView;
        if (Build.VERSION.SDK_INT >= 9) {
            webView = new InternalWebViewSDK9(context, attributeSet);
        } else {
            webView = new WebView(context, attributeSet);
        }
        webView.setId(R.id.webview);
        return webView;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullStart() {
        return ((WebView) this.mRefreshableView).getScrollY() == 0;
    }

    /* access modifiers changed from: protected */
    public boolean isReadyForPullEnd() {
        return ((float) ((WebView) this.mRefreshableView).getScrollY()) >= ((float) Math.floor((double) (((float) ((WebView) this.mRefreshableView).getContentHeight()) * ((WebView) this.mRefreshableView).getScale()))) - ((float) ((WebView) this.mRefreshableView).getHeight());
    }

    /* access modifiers changed from: protected */
    public void onPtrRestoreInstanceState(Bundle bundle) {
        super.onPtrRestoreInstanceState(bundle);
        ((WebView) this.mRefreshableView).restoreState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPtrSaveInstanceState(Bundle bundle) {
        super.onPtrSaveInstanceState(bundle);
        ((WebView) this.mRefreshableView).saveState(bundle);
    }

    final class InternalWebViewSDK9 extends WebView {
        static final int OVERSCROLL_FUZZY_THRESHOLD = 2;
        static final float OVERSCROLL_SCALE_FACTOR = 1.5f;

        public InternalWebViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* access modifiers changed from: protected */
        public boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshWebView.this, i, i3, i2, i4, getScrollRange(), 2, OVERSCROLL_SCALE_FACTOR, z);
            return overScrollBy;
        }

        private int getScrollRange() {
            return (int) Math.max(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, Math.floor((double) (((float) ((WebView) PullToRefreshWebView.this.mRefreshableView).getContentHeight()) * ((WebView) PullToRefreshWebView.this.mRefreshableView).getScale())) - ((double) ((getHeight() - getPaddingBottom()) - getPaddingTop())));
        }
    }
}
