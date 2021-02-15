package com.medscape.android.activity.webviews;

import android.content.Intent;
import android.util.Log;
import android.webkit.DownloadListener;
import com.medscape.android.activity.AndroidPdfViewerActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0006\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "url", "", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "contentDisposition", "<anonymous parameter 3>", "<anonymous parameter 4>", "", "onDownloadStart"}, k = 3, mv = {1, 4, 0})
/* compiled from: CommonWebViewActivity.kt */
final class CommonWebViewActivity$setUpWebView$2 implements DownloadListener {
    final /* synthetic */ CommonWebViewActivity this$0;

    CommonWebViewActivity$setUpWebView$2(CommonWebViewActivity commonWebViewActivity) {
        this.this$0 = commonWebViewActivity;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        Log.i("_TAG", "onDownloadStart: ");
        Intrinsics.checkNotNullExpressionValue(str3, "contentDisposition");
        if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) ".pdf", false, 2, (Object) null)) {
            Intrinsics.checkNotNullExpressionValue(str, "url");
            if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ".pdf", false, 2, (Object) null)) {
                return;
            }
        }
        Intent intent = new Intent(this.this$0, AndroidPdfViewerActivity.class);
        intent.putExtra("pdf_url", str);
        this.this$0.startActivity(intent);
    }
}
