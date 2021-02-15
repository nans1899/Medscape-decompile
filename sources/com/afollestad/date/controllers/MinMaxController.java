package com.afollestad.date.controllers;

import com.afollestad.date.CalendarsKt;
import com.afollestad.date.R;
import com.afollestad.date.data.snapshot.DateSnapshot;
import com.afollestad.date.data.snapshot.DateSnapshotKt;
import com.google.firebase.messaging.Constants;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\n\u0010\u000b\u001a\u0004\u0018\u00010\tH\u0007J\n\u0010\f\u001a\u0004\u0018\u00010\tH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0007J\u0012\u0010\u0011\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010\u0012\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0007J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\tJ\"\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\b\b\u0001\u0010\u0017\u001a\u00020\u000eJ\u000e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\tJ\"\u0010\u0018\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000e2\b\b\u0001\u0010\u0017\u001a\u00020\u000eJ\b\u0010\u0019\u001a\u00020\u0014H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/afollestad/date/controllers/MinMaxController;", "", "()V", "maxDate", "Lcom/afollestad/date/data/snapshot/DateSnapshot;", "minDate", "canGoBack", "", "from", "Ljava/util/Calendar;", "canGoForward", "getMaxDate", "getMinDate", "getOutOfMaxRangeBackgroundRes", "", "date", "getOutOfMinRangeBackgroundRes", "isOutOfMaxRange", "isOutOfMinRange", "setMaxDate", "", "year", "month", "dayOfMonth", "setMinDate", "validateMinAndMax", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MinMaxController.kt */
public final class MinMaxController {
    private DateSnapshot maxDate;
    private DateSnapshot minDate;

    public final Calendar getMinDate() {
        DateSnapshot dateSnapshot = this.minDate;
        if (dateSnapshot != null) {
            return dateSnapshot.asCalendar();
        }
        return null;
    }

    public final void setMinDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, OmnitureConstants.OMNITURE_FILTER_DATE);
        this.minDate = DateSnapshotKt.snapshot(calendar);
        validateMinAndMax();
    }

    public final void setMinDate(int i, int i2, int i3) {
        this.minDate = new DateSnapshot(i2, i3, i);
        validateMinAndMax();
    }

    public final Calendar getMaxDate() {
        DateSnapshot dateSnapshot = this.maxDate;
        if (dateSnapshot != null) {
            return dateSnapshot.asCalendar();
        }
        return null;
    }

    public final void setMaxDate(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, OmnitureConstants.OMNITURE_FILTER_DATE);
        this.maxDate = DateSnapshotKt.snapshot(calendar);
        validateMinAndMax();
    }

    public final void setMaxDate(int i, int i2, int i3) {
        this.maxDate = new DateSnapshot(i2, i3, i);
        validateMinAndMax();
    }

    public final boolean canGoBack(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, Constants.MessagePayloadKeys.FROM);
        if (this.minDate == null) {
            return true;
        }
        return !isOutOfMinRange(DateSnapshotKt.snapshot(CalendarsKt.decrementMonth(calendar)));
    }

    public final boolean canGoForward(Calendar calendar) {
        Intrinsics.checkParameterIsNotNull(calendar, Constants.MessagePayloadKeys.FROM);
        if (this.maxDate == null) {
            return true;
        }
        return !isOutOfMaxRange(DateSnapshotKt.snapshot(CalendarsKt.incrementMonth(calendar)));
    }

    public final boolean isOutOfMinRange(DateSnapshot dateSnapshot) {
        DateSnapshot dateSnapshot2;
        if (dateSnapshot == null || (dateSnapshot2 = this.minDate) == null) {
            return false;
        }
        if (dateSnapshot2 == null) {
            Intrinsics.throwNpe();
        }
        if (dateSnapshot.compareTo(dateSnapshot2) < 0) {
            return true;
        }
        return false;
    }

    public final int getOutOfMinRangeBackgroundRes(DateSnapshot dateSnapshot) {
        Intrinsics.checkParameterIsNotNull(dateSnapshot, OmnitureConstants.OMNITURE_FILTER_DATE);
        Calendar asCalendar = dateSnapshot.asCalendar();
        if (CalendarsKt.getDayOfMonth(asCalendar) == CalendarsKt.getTotalDaysInMonth(asCalendar)) {
            return R.drawable.ic_tube_end;
        }
        if (dateSnapshot.getDay() == 1) {
            return R.drawable.ic_tube_start;
        }
        int day = dateSnapshot.getDay();
        DateSnapshot dateSnapshot2 = this.minDate;
        if (dateSnapshot2 == null) {
            Intrinsics.throwNpe();
        }
        if (day == dateSnapshot2.getDay() - 1) {
            int month = dateSnapshot.getMonth();
            DateSnapshot dateSnapshot3 = this.minDate;
            if (dateSnapshot3 == null) {
                Intrinsics.throwNpe();
            }
            if (month == dateSnapshot3.getMonth()) {
                int year = dateSnapshot.getYear();
                DateSnapshot dateSnapshot4 = this.minDate;
                if (dateSnapshot4 == null) {
                    Intrinsics.throwNpe();
                }
                if (year == dateSnapshot4.getYear()) {
                    return R.drawable.ic_tube_end;
                }
            }
        }
        return R.drawable.ic_tube_middle;
    }

    public final boolean isOutOfMaxRange(DateSnapshot dateSnapshot) {
        DateSnapshot dateSnapshot2;
        if (dateSnapshot == null || (dateSnapshot2 = this.maxDate) == null) {
            return false;
        }
        if (dateSnapshot2 == null) {
            Intrinsics.throwNpe();
        }
        if (dateSnapshot.compareTo(dateSnapshot2) > 0) {
            return true;
        }
        return false;
    }

    public final int getOutOfMaxRangeBackgroundRes(DateSnapshot dateSnapshot) {
        Intrinsics.checkParameterIsNotNull(dateSnapshot, OmnitureConstants.OMNITURE_FILTER_DATE);
        Calendar asCalendar = dateSnapshot.asCalendar();
        boolean z = CalendarsKt.getDayOfMonth(asCalendar) == CalendarsKt.getTotalDaysInMonth(asCalendar);
        if (dateSnapshot.getDay() == 1) {
            return R.drawable.ic_tube_start;
        }
        int day = dateSnapshot.getDay();
        DateSnapshot dateSnapshot2 = this.maxDate;
        if (dateSnapshot2 == null) {
            Intrinsics.throwNpe();
        }
        if (day == dateSnapshot2.getDay() + 1) {
            int month = dateSnapshot.getMonth();
            DateSnapshot dateSnapshot3 = this.maxDate;
            if (dateSnapshot3 == null) {
                Intrinsics.throwNpe();
            }
            if (month == dateSnapshot3.getMonth()) {
                int year = dateSnapshot.getYear();
                DateSnapshot dateSnapshot4 = this.maxDate;
                if (dateSnapshot4 == null) {
                    Intrinsics.throwNpe();
                }
                if (year == dateSnapshot4.getYear()) {
                    return R.drawable.ic_tube_start;
                }
            }
        }
        if (z) {
            return R.drawable.ic_tube_end;
        }
        return R.drawable.ic_tube_middle;
    }

    private final void validateMinAndMax() {
        DateSnapshot dateSnapshot = this.minDate;
        if (dateSnapshot != null && this.maxDate != null) {
            if (dateSnapshot == null) {
                Intrinsics.throwNpe();
            }
            DateSnapshot dateSnapshot2 = this.maxDate;
            if (dateSnapshot2 == null) {
                Intrinsics.throwNpe();
            }
            if (!(dateSnapshot.compareTo(dateSnapshot2) < 0)) {
                throw new IllegalStateException("Min date must be less than max date.".toString());
            }
        }
    }
}
