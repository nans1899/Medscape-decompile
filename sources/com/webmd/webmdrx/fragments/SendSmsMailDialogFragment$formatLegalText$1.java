package com.webmd.webmdrx.fragments;

import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/webmd/webmdrx/fragments/SendSmsMailDialogFragment$formatLegalText$1", "Landroid/text/style/ClickableSpan;", "onClick", "", "widget", "Landroid/view/View;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SendSmsMailDialogFragment.kt */
public final class SendSmsMailDialogFragment$formatLegalText$1 extends ClickableSpan {
    final /* synthetic */ SendSmsMailDialogFragment this$0;

    SendSmsMailDialogFragment$formatLegalText$1(SendSmsMailDialogFragment sendSmsMailDialogFragment) {
        this.this$0 = sendSmsMailDialogFragment;
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "widget");
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.webmd.com/about-webmd-policies/about-terms-and-conditions-of-use")));
    }
}
