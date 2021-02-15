package com.webmd.webmdrx.activities;

import android.view.View;
import com.webmd.webmdrx.fragments.SendSmsMailDialogFragment;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: CouponActivity.kt */
final class CouponActivity$setupListeners$4 implements View.OnClickListener {
    final /* synthetic */ CouponActivity this$0;

    CouponActivity$setupListeners$4(CouponActivity couponActivity) {
        this.this$0 = couponActivity;
    }

    public final void onClick(View view) {
        SendSmsMailDialogFragment.Companion.newInstance(this.this$0.MAIL, CouponActivity.access$getCookieString$p(this.this$0)).show(this.this$0.getSupportFragmentManager(), "dialog");
    }
}
