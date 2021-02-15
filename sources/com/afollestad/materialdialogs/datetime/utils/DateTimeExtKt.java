package com.afollestad.materialdialogs.datetime.utils;

import android.widget.TimePicker;
import com.afollestad.date.DatePicker;
import java.util.Calendar;
import java.util.GregorianCalendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\f\u0010\b\u001a\u00020\u0001*\u00020\u0003H\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\u0005H\u0000¨\u0006\t"}, d2 = {"isFutureTime", "", "datePicker", "Lcom/afollestad/date/DatePicker;", "timePicker", "Landroid/widget/TimePicker;", "toCalendar", "Ljava/util/Calendar;", "isFutureDate", "datetime"}, k = 2, mv = {1, 1, 16})
/* compiled from: DateTimeExt.kt */
public final class DateTimeExtKt {
    public static final boolean isFutureTime(DatePicker datePicker, TimePicker timePicker) {
        Intrinsics.checkParameterIsNotNull(datePicker, "datePicker");
        Intrinsics.checkParameterIsNotNull(timePicker, "timePicker");
        Calendar instance = Calendar.getInstance();
        long timeInMillis = toCalendar(datePicker, timePicker).getTimeInMillis();
        Intrinsics.checkExpressionValueIsNotNull(instance, "now");
        return timeInMillis >= instance.getTimeInMillis();
    }

    public static final boolean isFutureTime(TimePicker timePicker) {
        Intrinsics.checkParameterIsNotNull(timePicker, "$this$isFutureTime");
        Calendar instance = Calendar.getInstance();
        long timeInMillis = toCalendar(timePicker).getTimeInMillis();
        Intrinsics.checkExpressionValueIsNotNull(instance, "now");
        return timeInMillis >= instance.getTimeInMillis();
    }

    public static final boolean isFutureDate(DatePicker datePicker) {
        Intrinsics.checkParameterIsNotNull(datePicker, "$this$isFutureDate");
        Calendar instance = Calendar.getInstance();
        Calendar date = datePicker.getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        long timeInMillis = date.getTimeInMillis();
        Intrinsics.checkExpressionValueIsNotNull(instance, "now");
        return timeInMillis >= instance.getTimeInMillis();
    }

    public static final Calendar toCalendar(TimePicker timePicker) {
        Intrinsics.checkParameterIsNotNull(timePicker, "$this$toCalendar");
        Calendar instance = Calendar.getInstance();
        return new GregorianCalendar(instance.get(1), instance.get(2), instance.get(5), ViewExtKt.hour(timePicker), ViewExtKt.minute(timePicker));
    }

    public static final Calendar toCalendar(DatePicker datePicker, TimePicker timePicker) {
        Intrinsics.checkParameterIsNotNull(datePicker, "datePicker");
        Intrinsics.checkParameterIsNotNull(timePicker, "timePicker");
        Calendar date = datePicker.getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        date.set(11, ViewExtKt.hour(timePicker));
        date.set(12, ViewExtKt.minute(timePicker));
        return date;
    }
}
