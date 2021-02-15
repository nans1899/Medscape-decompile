package com.afollestad.materialdialogs.datetime.internal;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "child", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: WrapContentViewPager.kt */
final class WrapContentViewPager$onMeasure$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ Ref.IntRef $maxChildHeight;
    final /* synthetic */ int $widthMeasureSpec;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WrapContentViewPager$onMeasure$1(int i, Ref.IntRef intRef) {
        super(1);
        this.$widthMeasureSpec = i;
        this.$maxChildHeight = intRef;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Intrinsics.checkParameterIsNotNull(view, "child");
        view.measure(this.$widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = view.getMeasuredHeight();
        if (measuredHeight > this.$maxChildHeight.element) {
            this.$maxChildHeight.element = measuredHeight;
        }
    }
}
