package com.afollestad.date;

import com.afollestad.date.data.DayOfWeek;
import com.afollestad.date.data.DayOfWeekKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\u001a\f\u0010\u0014\u001a\u00020\u0003*\u00020\u0003H\u0001\u001a\f\u0010\u0015\u001a\u00020\u0003*\u00020\u0003H\u0001\"(\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u0018\u0010\b\u001a\u00020\t*\u00020\u00038@X\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"(\u0010\f\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u0005\"\u0004\b\u000e\u0010\u0007\"\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005\"(\u0010\u0011\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0007¨\u0006\u0016"}, d2 = {"value", "", "dayOfMonth", "Ljava/util/Calendar;", "getDayOfMonth", "(Ljava/util/Calendar;)I", "setDayOfMonth", "(Ljava/util/Calendar;I)V", "dayOfWeek", "Lcom/afollestad/date/data/DayOfWeek;", "getDayOfWeek", "(Ljava/util/Calendar;)Lcom/afollestad/date/data/DayOfWeek;", "month", "getMonth", "setMonth", "totalDaysInMonth", "getTotalDaysInMonth", "year", "getYear", "setYear", "decrementMonth", "incrementMonth", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: Calendars.kt */
public final class CalendarsKt {
    public static final int getYear(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$year");
        return calendar.get(1);
    }

    public static final void setYear(Calendar calendar, int i) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$year");
        calendar.set(1, i);
    }

    public static final int getMonth(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$month");
        return calendar.get(2);
    }

    public static final void setMonth(Calendar calendar, int i) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$month");
        calendar.set(2, i);
    }

    public static final int getDayOfMonth(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$dayOfMonth");
        return calendar.get(5);
    }

    public static final void setDayOfMonth(Calendar calendar, int i) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$dayOfMonth");
        calendar.set(5, i);
    }

    public static final int getTotalDaysInMonth(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$totalDaysInMonth");
        return calendar.getActualMaximum(5);
    }

    public static final Calendar incrementMonth(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$incrementMonth");
        Object clone = calendar.clone();
        if (clone != null) {
            Calendar calendar2 = (Calendar) clone;
            calendar2.add(2, 1);
            calendar2.set(5, 1);
            return calendar2;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
    }

    public static final Calendar decrementMonth(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$decrementMonth");
        Object clone = calendar.clone();
        if (clone != null) {
            Calendar calendar2 = (Calendar) clone;
            calendar2.add(2, -1);
            calendar2.set(5, getTotalDaysInMonth(calendar2));
            return calendar2;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Calendar");
    }

    public static final DayOfWeek getDayOfWeek(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$dayOfWeek");
        return DayOfWeekKt.asDayOfWeek(calendar.get(7));
    }
}
