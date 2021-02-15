package com.afollestad.date.data.snapshot;

import com.afollestad.date.CalendarsKt;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0001¨\u0006\u0003"}, d2 = {"snapshot", "Lcom/afollestad/date/data/snapshot/DateSnapshot;", "Ljava/util/Calendar;", "com.afollestad.date-picker"}, k = 2, mv = {1, 1, 15})
/* compiled from: DateSnapshot.kt */
public final class DateSnapshotKt {
    public static final DateSnapshot snapshot(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, "$this$snapshot");
        return new DateSnapshot(CalendarsKt.getMonth(calendar), CalendarsKt.getDayOfMonth(calendar), CalendarsKt.getYear(calendar));
    }
}
