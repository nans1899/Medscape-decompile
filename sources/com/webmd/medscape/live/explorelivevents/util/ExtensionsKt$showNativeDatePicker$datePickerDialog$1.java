package com.webmd.medscape.live.explorelivevents.util;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/DatePicker;", "kotlin.jvm.PlatformType", "year", "", "month", "dayOfMonth", "onDateSet"}, k = 3, mv = {1, 4, 0})
/* compiled from: Extensions.kt */
final class ExtensionsKt$showNativeDatePicker$datePickerDialog$1 implements DatePickerDialog.OnDateSetListener {
    final /* synthetic */ Function1 $calendarCallback;

    ExtensionsKt$showNativeDatePicker$datePickerDialog$1(Function1 function1) {
        this.$calendarCallback = function1;
    }

    public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3);
        Intrinsics.checkNotNullExpressionValue(instance, "chosenDate");
        Instant ofEpochMilli = Instant.ofEpochMilli(instance.getTimeInMillis());
        Function1 function1 = this.$calendarCallback;
        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(ofEpochMilli, ZoneId.systemDefault());
        Intrinsics.checkNotNullExpressionValue(ofInstant, "ZonedDateTime.ofInstant(…, ZoneId.systemDefault())");
        function1.invoke(ofInstant);
    }
}
