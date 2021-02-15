package com.afollestad.date.data.snapshot;

import com.afollestad.date.CalendarsKt;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0007J\u0011\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0000H\u0002J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0018"}, d2 = {"Lcom/afollestad/date/data/snapshot/DateSnapshot;", "", "month", "", "day", "year", "(III)V", "getDay", "()I", "getMonth", "getYear", "asCalendar", "Ljava/util/Calendar;", "compareTo", "other", "component1", "component2", "component3", "copy", "equals", "", "hashCode", "toString", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: DateSnapshot.kt */
public final class DateSnapshot {
    private final int day;
    private final int month;
    private final int year;

    public static /* synthetic */ DateSnapshot copy$default(DateSnapshot dateSnapshot, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = dateSnapshot.month;
        }
        if ((i4 & 2) != 0) {
            i2 = dateSnapshot.day;
        }
        if ((i4 & 4) != 0) {
            i3 = dateSnapshot.year;
        }
        return dateSnapshot.copy(i, i2, i3);
    }

    public final int component1() {
        return this.month;
    }

    public final int component2() {
        return this.day;
    }

    public final int component3() {
        return this.year;
    }

    public final DateSnapshot copy(int i, int i2, int i3) {
        return new DateSnapshot(i, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DateSnapshot) {
                DateSnapshot dateSnapshot = (DateSnapshot) obj;
                if (this.month == dateSnapshot.month) {
                    if (this.day == dateSnapshot.day) {
                        if (this.year == dateSnapshot.year) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.month * 31) + this.day) * 31) + this.year;
    }

    public String toString() {
        return "DateSnapshot(month=" + this.month + ", day=" + this.day + ", year=" + this.year + ")";
    }

    public DateSnapshot(int i, int i2, int i3) {
        this.month = i;
        this.day = i2;
        this.year = i3;
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getDay() {
        return this.day;
    }

    public final int getYear() {
        return this.year;
    }

    public final Calendar asCalendar() {
        int i = this.month;
        int i2 = this.day;
        int i3 = this.year;
        Calendar instance = Calendar.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(instance, "this");
        CalendarsKt.setYear(instance, i3);
        CalendarsKt.setMonth(instance, i);
        CalendarsKt.setDayOfMonth(instance, i2);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Calendar.getInstance(Loc…fMonth = newDay\n        }");
        return instance;
    }

    public final int compareTo(DateSnapshot dateSnapshot) {
        Intrinsics.checkParameterIsNotNull(dateSnapshot, "other");
        if (this.month == dateSnapshot.month && this.year == dateSnapshot.year && this.day == dateSnapshot.day) {
            return 0;
        }
        int i = this.year;
        int i2 = dateSnapshot.year;
        if (i < i2) {
            return -1;
        }
        if (i == i2 && this.month < dateSnapshot.month) {
            return -1;
        }
        if (this.year == dateSnapshot.year && this.month == dateSnapshot.month && this.day < dateSnapshot.day) {
            return -1;
        }
        return 1;
    }
}
