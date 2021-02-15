package com.medscape.android.activity.webviews;

import android.content.Context;
import com.medscape.android.activity.webviews.CommonWebViewActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: CommonWebViewActivity.kt */
final class CommonWebViewActivity$MyJavaScriptInterface$processHTML$1 extends Lambda implements Function1<Context, Unit> {
    final /* synthetic */ CommonWebViewActivity.MyJavaScriptInterface this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CommonWebViewActivity$MyJavaScriptInterface$processHTML$1(CommonWebViewActivity.MyJavaScriptInterface myJavaScriptInterface) {
        super(1);
        this.this$0 = myJavaScriptInterface;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Context) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Context context) {
        Intrinsics.checkNotNullParameter(context, "$receiver");
        CommonWebViewActivity.this.getAd();
    }
}
