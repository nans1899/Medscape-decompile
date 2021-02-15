package com.webmd.webmdrx.activities;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.wbmd.wbmdcommons.logging.Trace;
import kotlin.Metadata;
import kotlin.math.MathKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, d2 = {"com/webmd/webmdrx/activities/CouponActivity$afterLayout$1", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onGlobalLayout", "", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CouponActivity.kt */
public final class CouponActivity$displayData$$inlined$afterLayout$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ View $this_afterLayout;
    final /* synthetic */ CouponActivity this$0;

    public CouponActivity$displayData$$inlined$afterLayout$1(View view, CouponActivity couponActivity) {
        this.$this_afterLayout = view;
        this.this$0 = couponActivity;
    }

    public void onGlobalLayout() {
        if (this.$this_afterLayout.getMeasuredWidth() > 0 && this.$this_afterLayout.getMeasuredHeight() > 0) {
            this.$this_afterLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Trace.d("coupon position", "width = " + CouponActivity.access$getCouponImage$p(this.this$0).getWidth());
            Trace.d("coupon position", "height = " + CouponActivity.access$getCouponImage$p(this.this$0).getHeight());
            int roundToInt = MathKt.roundToInt(((double) CouponActivity.access$getCouponImage$p(this.this$0).getWidth()) * 0.58d);
            int roundToInt2 = MathKt.roundToInt(((double) CouponActivity.access$getCouponImage$p(this.this$0).getHeight()) * 0.57d);
            Trace.d("coupon position", "width = " + (((double) CouponActivity.access$getCouponImage$p(this.this$0).getWidth()) * 0.5d));
            Trace.d("coupon position", "height = " + CouponActivity.access$getCouponImage$p(this.this$0).getHeight());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(MathKt.roundToInt(((double) CouponActivity.access$getCouponImage$p(this.this$0).getWidth()) * 0.35d), MathKt.roundToInt(((double) CouponActivity.access$getCouponImage$p(this.this$0).getHeight()) * 0.35d));
            layoutParams.leftMargin = roundToInt;
            layoutParams.topMargin = roundToInt2;
            CouponActivity.access$getCouponDetailsLayout$p(this.this$0).setLayoutParams(layoutParams);
        }
    }
}
