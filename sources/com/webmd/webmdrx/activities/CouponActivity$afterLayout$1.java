package com.webmd.webmdrx.activities;

import android.view.View;
import android.view.ViewTreeObserver;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/webmd/webmdrx/activities/CouponActivity$afterLayout$1", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CouponActivity.kt */
public final class CouponActivity$afterLayout$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ Function1 $func;
    final /* synthetic */ View $this_afterLayout;

    public CouponActivity$afterLayout$1(View view, Function1 function1) {
        this.$this_afterLayout = view;
        this.$func = function1;
    }

    public void onGlobalLayout() {
        if (this.$this_afterLayout.getMeasuredWidth() > 0 && this.$this_afterLayout.getMeasuredHeight() > 0) {
            this.$this_afterLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.$func.invoke(this.$this_afterLayout);
        }
    }
}
