package com.afollestad.date.managers;

import android.graphics.Typeface;
import com.afollestad.date.util.TypefaceHelper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Typeface;", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: DatePickerLayoutManager.kt */
final class DatePickerLayoutManager$normalFont$1 extends Lambda implements Function0<Typeface> {
    public static final DatePickerLayoutManager$normalFont$1 INSTANCE = new DatePickerLayoutManager$normalFont$1();

    DatePickerLayoutManager$normalFont$1() {
        super(0);
    }

    public final Typeface invoke() {
        return TypefaceHelper.INSTANCE.create("sans-serif");
    }
}
