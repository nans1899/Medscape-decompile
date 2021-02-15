package com.afollestad.date.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/afollestad/date/data/DateFormatter;", "", "()V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "monthAndYearFormatter", "monthFormatter", "yearFormatter", "date", "", "calendar", "Ljava/util/Calendar;", "month", "monthAndYear", "year", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: DateFormatter.kt */
public final class DateFormatter {
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, MMM dd", Locale.getDefault());
    private final SimpleDateFormat monthAndYearFormatter = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    private final SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM", Locale.getDefault());
    private final SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy", Locale.getDefault());

    public final String monthAndYear(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        String format = this.monthAndYearFormatter.format(calendar.getTime());
        Intrinsics.checkExpressionValueIsNotNull(format, "monthAndYearFormatter.format(calendar.time)");
        return format;
    }

    public final String year(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        String format = this.yearFormatter.format(calendar.getTime());
        Intrinsics.checkExpressionValueIsNotNull(format, "yearFormatter.format(calendar.time)");
        return format;
    }

    public final String date(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        String format = this.dateFormatter.format(calendar.getTime());
        Intrinsics.checkExpressionValueIsNotNull(format, "dateFormatter.format(calendar.time)");
        return format;
    }

    public final String month(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        String format = this.monthFormatter.format(calendar.getTime());
        Intrinsics.checkExpressionValueIsNotNull(format, "monthFormatter.format(calendar.time)");
        return format;
    }
}
