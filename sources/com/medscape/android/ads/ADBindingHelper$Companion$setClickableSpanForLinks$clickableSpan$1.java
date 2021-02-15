package com.medscape.android.ads;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.medscape.android.activity.webviews.WebviewUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/medscape/android/ads/ADBindingHelper$Companion$setClickableSpanForLinks$clickableSpan$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "textView", "Landroid/view/View;", "updateDrawState", "ds", "Landroid/text/TextPaint;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ADBindingHelper.kt */
public final class ADBindingHelper$Companion$setClickableSpanForLinks$clickableSpan$1 extends ClickableSpan {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $spanUrl;

    ADBindingHelper$Companion$setClickableSpanForLinks$clickableSpan$1(String str, Context context) {
        this.$spanUrl = str;
        this.$context = context;
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "textView");
        CharSequence charSequence = this.$spanUrl;
        if (!(charSequence == null || charSequence.length() == 0)) {
            WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this.$context, this.$spanUrl, "", "", "", "", "", 0, false, 256, (Object) null);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        Intrinsics.checkNotNullParameter(textPaint, "ds");
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
    }
}
