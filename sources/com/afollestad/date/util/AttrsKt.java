package com.afollestad.date.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0001\u001a,\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\u0003\u001a\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H\u0000¨\u0006\n"}, d2 = {"color", "", "Landroid/content/res/TypedArray;", "attr", "fallback", "Lkotlin/Function0;", "font", "Landroid/graphics/Typeface;", "context", "Landroid/content/Context;", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: Attrs.kt */
public final class AttrsKt {
    public static final int color(TypedArray typedArray, int i, Function0<Integer> function0) {
        Intrinsics.checkParameterIsNotNull(typedArray, "$this$color");
        Intrinsics.checkParameterIsNotNull(function0, "fallback");
        int color = typedArray.getColor(i, 0);
        return color == 0 ? function0.invoke().intValue() : color;
    }

    public static final Typeface font(TypedArray typedArray, Context context, int i, Function0<? extends Typeface> function0) {
        Intrinsics.checkParameterIsNotNull(typedArray, "$this$font");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(function0, "fallback");
        int resourceId = typedArray.getResourceId(i, 0);
        if (resourceId == 0) {
            return (Typeface) function0.invoke();
        }
        Typeface font = ResourcesCompat.getFont(context, resourceId);
        return font != null ? font : (Typeface) function0.invoke();
    }
}
