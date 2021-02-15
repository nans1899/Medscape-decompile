package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import androidx.lifecycle.MutableLiveData;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001c\u0010\u0010\u001a\u00020\u00112\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\u0012"}, d2 = {"com/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragment$renderWebViewAndLoadSite$2", "Landroid/webkit/WebViewClient;", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "onPageStarted", "favicon", "Landroid/graphics/Bitmap;", "onReceivedHttpError", "request", "Landroid/webkit/WebResourceRequest;", "errorResponse", "Landroid/webkit/WebResourceResponse;", "shouldOverrideUrlLoading", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventFragment.kt */
public final class LiveEventFragment$renderWebViewAndLoadSite$2 extends WebViewClient {
    final /* synthetic */ LiveEventFragment this$0;

    LiveEventFragment$renderWebViewAndLoadSite$2(LiveEventFragment liveEventFragment) {
        this.this$0 = liveEventFragment;
    }

    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        if (Build.VERSION.SDK_INT >= 21 && this.this$0.getContext() != null) {
            MutableLiveData<Error> errorObserver = this.this$0.getViewModel().getErrorObserver();
            Drawable drawable = null;
            String reasonPhrase = webResourceResponse != null ? webResourceResponse.getReasonPhrase() : null;
            if (webResourceResponse != null) {
                int statusCode = webResourceResponse.getStatusCode();
                Context requireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                drawable = ExtensionsKt.getErrorImageRes(statusCode, requireContext);
            }
            errorObserver.setValue(new Error(true, reasonPhrase, drawable));
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.this$0.handleLoadingResponse((String) null);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        this.this$0.getBinding().wvEvents.loadUrl(str);
        return false;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        RelativeLayout relativeLayout = this.this$0.getBinding().lytLoader;
        Intrinsics.checkNotNullExpressionValue(relativeLayout, "binding.lytLoader");
        relativeLayout.setVisibility(8);
        WebView webView2 = this.this$0.getBinding().wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView2, "binding.wvEvents");
        webView2.setVisibility(0);
    }
}
