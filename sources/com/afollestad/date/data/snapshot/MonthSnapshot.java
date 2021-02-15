package com.afollestad.date.data.snapshot;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0000H\u0002J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/afollestad/date/data/snapshot/MonthSnapshot;", "", "month", "", "year", "(II)V", "getMonth", "()I", "getYear", "compareTo", "other", "component1", "component2", "copy", "equals", "", "hashCode", "toString", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthSnapshot.kt */
public final class MonthSnapshot {
    private final int month;
    private final int year;

    public static /* synthetic */ MonthSnapshot copy$default(MonthSnapshot monthSnapshot, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = monthSnapshot.month;
        }
        if ((i3 & 2) != 0) {
            i2 = monthSnapshot.year;
        }
        return monthSnapshot.copy(i, i2);
    }

    public final int component1() {
        return this.month;
    }

    public final int component2() {
        return this.year;
    }

    public final MonthSnapshot copy(int i, int i2) {
        return new MonthSnapshot(i, i2);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof MonthSnapshot) {
                MonthSnapshot monthSnapshot = (MonthSnapshot) obj;
                if (this.month == monthSnapshot.month) {
                    if (this.year == monthSnapshot.year) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.month * 31) + this.year;
    }

    public String toString() {
        return "MonthSnapshot(month=" + this.month + ", year=" + this.year + ")";
    }

    public MonthSnapshot(int i, int i2) {
        this.month = i;
        this.year = i2;
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getYear() {
        return this.year;
    }

    public final int compareTo(MonthSnapshot monthSnapshot) {
        Intrinsics.checkParameterIsNotNull(monthSnapshot, "other");
        if (this.month == monthSnapshot.month && this.year == monthSnapshot.year) {
            return 0;
        }
        int i = this.year;
        int i2 = monthSnapshot.year;
        if (i < i2) {
            return -1;
        }
        if (i != i2 || this.month >= monthSnapshot.month) {
            return 1;
        }
        return -1;
    }
}
