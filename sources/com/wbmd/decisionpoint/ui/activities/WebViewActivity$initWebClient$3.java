package com.wbmd.decisionpoint.ui.activities;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.wbmd.decisionpoint.extensions.ViewExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\u0012"}, d2 = {"com/wbmd/decisionpoint/ui/activities/WebViewActivity$initWebClient$3", "Landroid/webkit/WebViewClient;", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "onPageStarted", "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "shouldOverrideUrlLoading", "", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WebViewActivity.kt */
public final class WebViewActivity$initWebClient$3 extends WebViewClient {
    final /* synthetic */ WebViewActivity this$0;

    WebViewActivity$initWebClient$3(WebViewActivity webViewActivity) {
        this.this$0 = webViewActivity;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        ProgressBar progressBar = WebViewActivity.access$getBinding$p(this.this$0).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
        ViewExtensionsKt.visible(progressBar);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Intrinsics.checkNotNullParameter(webView, "view");
        if (str == null) {
            return true;
        }
        webView.loadUrl(str);
        return true;
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        ProgressBar progressBar = WebViewActivity.access$getBinding$p(this.this$0).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
        ViewExtensionsKt.gone(progressBar);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        ProgressBar progressBar = WebViewActivity.access$getBinding$p(this.this$0).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "binding.progressBar");
        ViewExtensionsKt.gone(progressBar);
    }
}
