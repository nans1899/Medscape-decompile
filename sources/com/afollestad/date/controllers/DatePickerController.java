package com.afollestad.date.controllers;

import com.afollestad.date.CalendarsKt;
import com.afollestad.date.data.MonthGraph;
import com.afollestad.date.data.MonthItem;
import com.afollestad.date.data.snapshot.DateSnapshot;
import com.afollestad.date.data.snapshot.DateSnapshotKt;
import com.afollestad.date.data.snapshot.MonthSnapshot;
import com.afollestad.date.data.snapshot.MonthSnapshotKt;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B­\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0018\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\t0\u000b\u0012!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\t0\u000b\u0012!\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\t0\u000b\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0015\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015¢\u0006\u0002\u0010\u0017JB\u0010;\u001a\u00020\t2:\u0010<\u001a6\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\u001cJ\u0006\u0010=\u001a\u00020\tJ\b\u0010>\u001a\u00020\bH\u0002J\n\u0010?\u001a\u0004\u0018\u00010\bH\u0007J\u0006\u0010@\u001a\u00020\tJ\u0006\u0010A\u001a\u00020\tJ\u001e\u0010B\u001a\u00020\t2\u0006\u0010C\u001a\u00020\b2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0002J\u0006\u0010E\u001a\u00020\tJ\u0010\u0010F\u001a\u00020\t2\u0006\u0010G\u001a\u00020\bH\u0002J\u000e\u0010H\u001a\u00020\t2\u0006\u0010I\u001a\u00020JJ\u0018\u0010K\u001a\u00020\t2\u0006\u0010G\u001a\u00020\b2\b\b\u0002\u0010B\u001a\u00020\u000fJ5\u0010K\u001a\u00020\t2\n\b\u0003\u0010L\u001a\u0004\u0018\u00010J2\u0006\u0010M\u001a\u00020J2\n\b\u0003\u0010-\u001a\u0004\u0018\u00010J2\b\b\u0002\u0010B\u001a\u00020\u000f¢\u0006\u0002\u0010NJ\u000e\u0010O\u001a\u00020\t2\u0006\u0010M\u001a\u00020JJ\u000e\u0010P\u001a\u00020\t2\u0006\u0010L\u001a\u00020JJ\u0010\u0010Q\u001a\u00020\t2\u0006\u0010G\u001a\u00020\bH\u0002RH\u0010\u0018\u001a<\u00128\u00126\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\u001c0\u0019X\u0004¢\u0006\u0002\n\u0000R$\u0010\u001d\u001a\u00020\u000f8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015X\u0004¢\u0006\u0002\n\u0000R)\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R)\u0010\u0013\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R&\u0010$\u001a\u0004\u0018\u00010%8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b&\u0010\u001f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\t0\u000bX\u0004¢\u0006\u0002\n\u0000R0\u0010-\u001a\u0004\u0018\u00010,2\b\u0010+\u001a\u0004\u0018\u00010,8\u0006@FX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b.\u0010\u001f\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0010\u00103\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R&\u00104\u001a\u0004\u0018\u0001058\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b6\u0010\u001f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006R"}, d2 = {"Lcom/afollestad/date/controllers/DatePickerController;", "", "vibrator", "Lcom/afollestad/date/controllers/VibratorController;", "minMaxController", "Lcom/afollestad/date/controllers/MinMaxController;", "renderHeaders", "Lkotlin/Function2;", "Ljava/util/Calendar;", "", "renderMonthItems", "Lkotlin/Function1;", "", "Lcom/afollestad/date/data/MonthItem;", "goBackVisibility", "", "Lkotlin/ParameterName;", "name", "visible", "goForwardVisibility", "switchToDaysOfMonthMode", "Lkotlin/Function0;", "getNow", "(Lcom/afollestad/date/controllers/VibratorController;Lcom/afollestad/date/controllers/MinMaxController;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "dateChangedListeners", "", "previous", "date", "Lcom/afollestad/date/OnDateChanged;", "didInit", "didInit$annotations", "()V", "getDidInit", "()Z", "setDidInit", "(Z)V", "monthGraph", "Lcom/afollestad/date/data/MonthGraph;", "monthGraph$annotations", "getMonthGraph", "()Lcom/afollestad/date/data/MonthGraph;", "setMonthGraph", "(Lcom/afollestad/date/data/MonthGraph;)V", "value", "Lcom/afollestad/date/data/snapshot/DateSnapshot;", "selectedDate", "selectedDate$annotations", "getSelectedDate", "()Lcom/afollestad/date/data/snapshot/DateSnapshot;", "setSelectedDate", "(Lcom/afollestad/date/data/snapshot/DateSnapshot;)V", "selectedDateCalendar", "viewingMonth", "Lcom/afollestad/date/data/snapshot/MonthSnapshot;", "viewingMonth$annotations", "getViewingMonth", "()Lcom/afollestad/date/data/snapshot/MonthSnapshot;", "setViewingMonth", "(Lcom/afollestad/date/data/snapshot/MonthSnapshot;)V", "addDateChangedListener", "listener", "clearDateChangedListeners", "currentSelectedOrNow", "getFullDate", "maybeInit", "nextMonth", "notifyListeners", "old", "block", "previousMonth", "render", "calendar", "setDayOfMonth", "day", "", "setFullDate", "year", "month", "(Ljava/lang/Integer;ILjava/lang/Integer;Z)V", "setMonth", "setYear", "updateCurrentMonth", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: DatePickerController.kt */
public final class DatePickerController {
    private final List<Function2<Calendar, Calendar, Unit>> dateChangedListeners;
    private boolean didInit;
    private final Function0<Calendar> getNow;
    private final Function1<Boolean, Unit> goBackVisibility;
    private final Function1<Boolean, Unit> goForwardVisibility;
    private final MinMaxController minMaxController;
    private MonthGraph monthGraph;
    private final Function2<Calendar, Calendar, Unit> renderHeaders;
    private final Function1<List<? extends MonthItem>, Unit> renderMonthItems;
    private DateSnapshot selectedDate;
    private Calendar selectedDateCalendar;
    private final Function0<Unit> switchToDaysOfMonthMode;
    private final VibratorController vibrator;
    private MonthSnapshot viewingMonth;

    public static /* synthetic */ void didInit$annotations() {
    }

    public static /* synthetic */ void monthGraph$annotations() {
    }

    public static /* synthetic */ void selectedDate$annotations() {
    }

    public static /* synthetic */ void viewingMonth$annotations() {
    }

    public DatePickerController(VibratorController vibratorController, MinMaxController minMaxController2, Function2<? super Calendar, ? super Calendar, Unit> function2, Function1<? super List<? extends MonthItem>, Unit> function1, Function1<? super Boolean, Unit> function12, Function1<? super Boolean, Unit> function13, Function0<Unit> function0, Function0<? extends Calendar> function02) {
        Intrinsics.checkParameterIsNotNull(vibratorController, "vibrator");
        Intrinsics.checkParameterIsNotNull(minMaxController2, "minMaxController");
        Intrinsics.checkParameterIsNotNull(function2, "renderHeaders");
        Intrinsics.checkParameterIsNotNull(function1, "renderMonthItems");
        Intrinsics.checkParameterIsNotNull(function12, "goBackVisibility");
        Intrinsics.checkParameterIsNotNull(function13, "goForwardVisibility");
        Intrinsics.checkParameterIsNotNull(function0, "switchToDaysOfMonthMode");
        Intrinsics.checkParameterIsNotNull(function02, "getNow");
        this.vibrator = vibratorController;
        this.minMaxController = minMaxController2;
        this.renderHeaders = function2;
        this.renderMonthItems = function1;
        this.goBackVisibility = function12;
        this.goForwardVisibility = function13;
        this.switchToDaysOfMonthMode = function0;
        this.getNow = function02;
        this.dateChangedListeners = new ArrayList();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DatePickerController(VibratorController vibratorController, MinMaxController minMaxController2, Function2 function2, Function1 function1, Function1 function12, Function1 function13, Function0 function0, Function0 function02, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(vibratorController, minMaxController2, function2, function1, function12, function13, function0, (i & 128) != 0 ? AnonymousClass1.INSTANCE : function02);
    }

    public final boolean getDidInit() {
        return this.didInit;
    }

    public final void setDidInit(boolean z) {
        this.didInit = z;
    }

    public final MonthSnapshot getViewingMonth() {
        return this.viewingMonth;
    }

    public final void setViewingMonth(MonthSnapshot monthSnapshot) {
        this.viewingMonth = monthSnapshot;
    }

    public final MonthGraph getMonthGraph() {
        return this.monthGraph;
    }

    public final void setMonthGraph(MonthGraph monthGraph2) {
        this.monthGraph = monthGraph2;
    }

    public final DateSnapshot getSelectedDate() {
        return this.selectedDate;
    }

    public final void setSelectedDate(DateSnapshot dateSnapshot) {
        this.selectedDate = dateSnapshot;
        this.selectedDateCalendar = dateSnapshot != null ? dateSnapshot.asCalendar() : null;
    }

    public final void maybeInit() {
        if (!this.didInit) {
            Calendar invoke = this.getNow.invoke();
            DateSnapshot snapshot = DateSnapshotKt.snapshot(invoke);
            if (this.minMaxController.isOutOfMaxRange(snapshot)) {
                invoke = this.minMaxController.getMaxDate();
                if (invoke == null) {
                    Intrinsics.throwNpe();
                }
            } else if (this.minMaxController.isOutOfMinRange(snapshot) && (invoke = this.minMaxController.getMinDate()) == null) {
                Intrinsics.throwNpe();
            }
            setFullDate(invoke, false);
        }
    }

    public final void previousMonth() {
        this.switchToDaysOfMonthMode.invoke();
        MonthSnapshot monthSnapshot = this.viewingMonth;
        if (monthSnapshot == null) {
            Intrinsics.throwNpe();
        }
        Calendar decrementMonth = CalendarsKt.decrementMonth(MonthSnapshotKt.asCalendar(monthSnapshot, 1));
        updateCurrentMonth(decrementMonth);
        render(decrementMonth);
        this.vibrator.vibrateForSelection();
    }

    public final void nextMonth() {
        this.switchToDaysOfMonthMode.invoke();
        MonthSnapshot monthSnapshot = this.viewingMonth;
        if (monthSnapshot == null) {
            Intrinsics.throwNpe();
        }
        Calendar incrementMonth = CalendarsKt.incrementMonth(MonthSnapshotKt.asCalendar(monthSnapshot, 1));
        updateCurrentMonth(incrementMonth);
        render(incrementMonth);
        this.vibrator.vibrateForSelection();
    }

    public final void setMonth(int i) {
        this.switchToDaysOfMonthMode.invoke();
        MonthSnapshot monthSnapshot = this.viewingMonth;
        if (monthSnapshot == null) {
            Intrinsics.throwNpe();
        }
        Calendar asCalendar = MonthSnapshotKt.asCalendar(monthSnapshot, 1);
        CalendarsKt.setMonth(asCalendar, i);
        updateCurrentMonth(asCalendar);
        render(asCalendar);
        this.vibrator.vibrateForSelection();
    }

    public static /* synthetic */ void setFullDate$default(DatePickerController datePickerController, Calendar calendar, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        datePickerController.setFullDate(calendar, z);
    }

    public final void setFullDate(Calendar calendar, boolean z) {
        Intrinsics.checkParameterIsNotNull(calendar, "calendar");
        Calendar currentSelectedOrNow = currentSelectedOrNow();
        this.didInit = true;
        setSelectedDate(DateSnapshotKt.snapshot(calendar));
        if (z) {
            notifyListeners(currentSelectedOrNow, new DatePickerController$setFullDate$1(calendar));
        }
        updateCurrentMonth(calendar);
        render(calendar);
    }

    public static /* synthetic */ void setFullDate$default(DatePickerController datePickerController, Integer num, int i, Integer num2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = null;
        }
        if ((i2 & 4) != 0) {
            num2 = null;
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        datePickerController.setFullDate(num, i, num2, z);
    }

    public final void setFullDate(Integer num, int i, Integer num2, boolean z) {
        Calendar invoke = this.getNow.invoke();
        if (num != null) {
            CalendarsKt.setYear(invoke, num.intValue());
        }
        CalendarsKt.setMonth(invoke, i);
        if (num2 != null) {
            CalendarsKt.setDayOfMonth(invoke, num2.intValue());
        }
        setFullDate(invoke, z);
    }

    public final Calendar getFullDate() {
        if (this.minMaxController.isOutOfMinRange(this.selectedDate) || this.minMaxController.isOutOfMaxRange(this.selectedDate)) {
            return null;
        }
        return this.selectedDateCalendar;
    }

    public final void setDayOfMonth(int i) {
        if (!this.didInit) {
            Calendar invoke = this.getNow.invoke();
            CalendarsKt.setDayOfMonth(invoke, i);
            setFullDate$default(this, invoke, false, 2, (Object) null);
            return;
        }
        Calendar currentSelectedOrNow = currentSelectedOrNow();
        MonthSnapshot monthSnapshot = this.viewingMonth;
        if (monthSnapshot == null) {
            Intrinsics.throwNpe();
        }
        Calendar asCalendar = MonthSnapshotKt.asCalendar(monthSnapshot, i);
        setSelectedDate(DateSnapshotKt.snapshot(asCalendar));
        this.vibrator.vibrateForSelection();
        notifyListeners(currentSelectedOrNow, new DatePickerController$setDayOfMonth$2(asCalendar));
        render(asCalendar);
    }

    public final void setYear(int i) {
        int month;
        MonthSnapshot monthSnapshot = this.viewingMonth;
        if (monthSnapshot != null) {
            month = monthSnapshot.getMonth();
        } else {
            DateSnapshot dateSnapshot = this.selectedDate;
            if (dateSnapshot == null) {
                Intrinsics.throwNpe();
            }
            month = dateSnapshot.getMonth();
        }
        int i2 = month;
        Integer valueOf = Integer.valueOf(i);
        DateSnapshot dateSnapshot2 = this.selectedDate;
        setFullDate$default(this, valueOf, i2, dateSnapshot2 != null ? Integer.valueOf(dateSnapshot2.getDay()) : null, false, 8, (Object) null);
        this.switchToDaysOfMonthMode.invoke();
    }

    public final void addDateChangedListener(Function2<? super Calendar, ? super Calendar, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.dateChangedListeners.add(function2);
    }

    public final void clearDateChangedListeners() {
        this.dateChangedListeners.clear();
    }

    private final void updateCurrentMonth(Calendar calendar) {
        this.viewingMonth = MonthSnapshotKt.snapshotMonth(calendar);
        this.monthGraph = new MonthGraph(calendar);
    }

    private final void render(Calendar calendar) {
        Function2<Calendar, Calendar, Unit> function2 = this.renderHeaders;
        Calendar calendar2 = this.selectedDateCalendar;
        if (calendar2 == null) {
            Intrinsics.throwNpe();
        }
        function2.invoke(calendar, calendar2);
        Function1<List<? extends MonthItem>, Unit> function1 = this.renderMonthItems;
        MonthGraph monthGraph2 = this.monthGraph;
        if (monthGraph2 == null) {
            Intrinsics.throwNpe();
        }
        DateSnapshot dateSnapshot = this.selectedDate;
        if (dateSnapshot == null) {
            Intrinsics.throwNpe();
        }
        function1.invoke(monthGraph2.getMonthItems(dateSnapshot));
        this.goBackVisibility.invoke(Boolean.valueOf(this.minMaxController.canGoBack(calendar)));
        this.goForwardVisibility.invoke(Boolean.valueOf(this.minMaxController.canGoForward(calendar)));
    }

    private final void notifyListeners(Calendar calendar, Function0<? extends Calendar> function0) {
        if (!this.dateChangedListeners.isEmpty()) {
            Calendar calendar2 = (Calendar) function0.invoke();
            DateSnapshot snapshot = DateSnapshotKt.snapshot(calendar2);
            if (!this.minMaxController.isOutOfMinRange(snapshot) && !this.minMaxController.isOutOfMaxRange(snapshot)) {
                for (Function2 invoke : this.dateChangedListeners) {
                    invoke.invoke(calendar, calendar2);
                }
            }
        }
    }

    private final Calendar currentSelectedOrNow() {
        Calendar calendar = this.selectedDateCalendar;
        return calendar != null ? calendar : this.getNow.invoke();
    }
}
