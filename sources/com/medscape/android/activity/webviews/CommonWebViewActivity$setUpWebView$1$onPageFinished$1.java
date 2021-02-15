package com.medscape.android.activity.webviews;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: CommonWebViewActivity.kt */
final class CommonWebViewActivity$setUpWebView$1$onPageFinished$1 implements Runnable {
    final /* synthetic */ CommonWebViewActivity$setUpWebView$1 this$0;

    CommonWebViewActivity$setUpWebView$1$onPageFinished$1(CommonWebViewActivity$setUpWebView$1 commonWebViewActivity$setUpWebView$1) {
        this.this$0 = commonWebViewActivity$setUpWebView$1;
    }

    public final void run() {
        if (this.this$0.this$0.getWebViewModel().getCanSendEvent()) {
            this.this$0.this$0.getWebViewModel().getLeadConceptSegVar().setValue(this.this$0.this$0.getWebViewModel().getLeadConceptSegVar().getValue());
        }
    }
}
