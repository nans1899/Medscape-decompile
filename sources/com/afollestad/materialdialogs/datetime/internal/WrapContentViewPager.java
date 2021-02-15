package com.afollestad.materialdialogs.datetime.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.afollestad.materialdialogs.utils.MDUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nH\u0002J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0014¨\u0006\u0010"}, d2 = {"Lcom/afollestad/materialdialogs/datetime/internal/WrapContentViewPager;", "Landroidx/viewpager/widget/ViewPager;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "forEachChild", "", "each", "Lkotlin/Function1;", "Landroid/view/View;", "onMeasure", "widthMeasureSpec", "", "heightMeasureSpec", "datetime"}, k = 1, mv = {1, 1, 16})
/* compiled from: WrapContentViewPager.kt */
public final class WrapContentViewPager extends ViewPager {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WrapContentViewPager(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrapContentViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        forEachChild(new WrapContentViewPager$onMeasure$1(i, intRef));
        int size = View.MeasureSpec.getSize(i2);
        if (intRef.element > size) {
            intRef.element = size;
        }
        MDUtil mDUtil = MDUtil.INSTANCE;
        int i3 = intRef.element;
        if (i3 != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    private final void forEachChild(Function1<? super View, Unit> function1) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "child");
            function1.invoke(childAt);
        }
    }
}
