package com.afollestad.date.util;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012!\u0010\u0003\u001a\u001d\u0012\u0013\u0012\u0011H\u0001¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0004H\u0000¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"onClickDebounced", "T", "Landroid/view/View;", "click", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: Debouncer.kt */
public final class DebouncerKt {
    public static final <T extends View> T onClickDebounced(T t, Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "$this$onClickDebounced");
        Intrinsics.checkParameterIsNotNull(function1, "click");
        t.setOnClickListener(new DebouncerKt$onClickDebounced$1(function1));
        return t;
    }
}
