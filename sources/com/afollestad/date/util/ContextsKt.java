package com.afollestad.date.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0001\u001a(\u0010\u0005\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00042\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\bH\u0000¨\u0006\t"}, d2 = {"getFloat", "", "Landroid/content/Context;", "dimen", "", "resolveColor", "attr", "fallback", "Lkotlin/Function0;", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: Contexts.kt */
public final class ContextsKt {
    public static /* synthetic */ int resolveColor$default(Context context, int i, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function0 = null;
        }
        return resolveColor(context, i, function0);
    }

    public static final int resolveColor(Context context, int i, Function0<Integer> function0) {
        Intrinsics.checkParameterIsNotNull(context, "$this$resolveColor");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            int color = obtainStyledAttributes.getColor(0, 0);
            if (color == 0 && function0 != null) {
                return function0.invoke().intValue();
            }
            obtainStyledAttributes.recycle();
            return color;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static final float getFloat(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getFloat");
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(i, typedValue, true);
        return typedValue.getFloat();
    }
}
