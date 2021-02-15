package com.afollestad.date.data;

import com.afollestad.date.CalendarsKt;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001a2\u0006\u0010!\u001a\u00020\"H\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R1\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0002¢\u0006\u0018\n\u0004\b\u0010\u0010\u0011\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00130\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006$"}, d2 = {"Lcom/afollestad/date/data/MonthGraph;", "", "calendar", "Ljava/util/Calendar;", "(Ljava/util/Calendar;)V", "getCalendar", "()Ljava/util/Calendar;", "<set-?>", "", "daysInMonth", "daysInMonth$annotations", "()V", "getDaysInMonth", "()I", "setDaysInMonth", "(I)V", "daysInMonth$delegate", "Lkotlin/properties/ReadWriteProperty;", "firstWeekDayInMonth", "Lcom/afollestad/date/data/DayOfWeek;", "firstWeekDayInMonth$annotations", "getFirstWeekDayInMonth", "()Lcom/afollestad/date/data/DayOfWeek;", "setFirstWeekDayInMonth", "(Lcom/afollestad/date/data/DayOfWeek;)V", "orderedWeekDays", "", "getOrderedWeekDays", "()Ljava/util/List;", "setOrderedWeekDays", "(Ljava/util/List;)V", "getMonthItems", "Lcom/afollestad/date/data/MonthItem;", "selectedDate", "Lcom/afollestad/date/data/snapshot/DateSnapshot;", "Companion", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: MonthGraph.kt */
public final class MonthGraph {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(MonthGraph.class), "daysInMonth", "getDaysInMonth()I"))};
    @Deprecated
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int EXPECTED_SIZE = 49;
    private final Calendar calendar;
    private final ReadWriteProperty daysInMonth$delegate = Delegates.INSTANCE.notNull();
    private DayOfWeek firstWeekDayInMonth;
    private List<? extends DayOfWeek> orderedWeekDays;

    public static /* synthetic */ void daysInMonth$annotations() {
    }

    public static /* synthetic */ void firstWeekDayInMonth$annotations() {
    }

    public final int getDaysInMonth() {
        return ((Number) this.daysInMonth$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    public final void setDaysInMonth(int i) {
        this.daysInMonth$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    public MonthGraph(Calendar calendar2) {
        Intrinsics.checkParameterIsNotNull(calendar2, "calendar");
        this.calendar = calendar2;
        CalendarsKt.setDayOfMonth(this.calendar, 1);
        setDaysInMonth(CalendarsKt.getTotalDaysInMonth(this.calendar));
        this.firstWeekDayInMonth = CalendarsKt.getDayOfWeek(this.calendar);
        this.orderedWeekDays = DayOfWeekKt.andTheRest(DayOfWeekKt.asDayOfWeek(this.calendar.getFirstDayOfWeek()));
    }

    public final Calendar getCalendar() {
        return this.calendar;
    }

    public final DayOfWeek getFirstWeekDayInMonth() {
        return this.firstWeekDayInMonth;
    }

    public final void setFirstWeekDayInMonth(DayOfWeek dayOfWeek) {
        Intrinsics.checkParameterIsNotNull(dayOfWeek, "<set-?>");
        this.firstWeekDayInMonth = dayOfWeek;
    }

    public final List<DayOfWeek> getOrderedWeekDays() {
        return this.orderedWeekDays;
    }

    public final void setOrderedWeekDays(List<? extends DayOfWeek> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.orderedWeekDays = list;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: com.afollestad.date.data.DayOfWeek} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.afollestad.date.data.MonthItem> getMonthItems(com.afollestad.date.data.snapshot.DateSnapshot r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "selectedDate"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.List r2 = (java.util.List) r2
            java.util.Calendar r3 = r0.calendar
            com.afollestad.date.data.snapshot.MonthSnapshot r3 = com.afollestad.date.data.snapshot.MonthSnapshotKt.snapshotMonth(r3)
            java.util.List<? extends com.afollestad.date.data.DayOfWeek> r4 = r0.orderedWeekDays
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r11 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r4, r11)
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r4 = r4.iterator()
        L_0x002b:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0040
            java.lang.Object r6 = r4.next()
            com.afollestad.date.data.DayOfWeek r6 = (com.afollestad.date.data.DayOfWeek) r6
            com.afollestad.date.data.MonthItem$WeekHeader r7 = new com.afollestad.date.data.MonthItem$WeekHeader
            r7.<init>(r6)
            r5.add(r7)
            goto L_0x002b
        L_0x0040:
            java.util.List r5 = (java.util.List) r5
            java.util.Collection r5 = (java.util.Collection) r5
            r2.addAll(r5)
            java.util.List<? extends com.afollestad.date.data.DayOfWeek> r4 = r0.orderedWeekDays
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x0054:
            boolean r6 = r4.hasNext()
            r13 = 1
            if (r6 == 0) goto L_0x0070
            java.lang.Object r6 = r4.next()
            r7 = r6
            com.afollestad.date.data.DayOfWeek r7 = (com.afollestad.date.data.DayOfWeek) r7
            com.afollestad.date.data.DayOfWeek r8 = r0.firstWeekDayInMonth
            if (r7 == r8) goto L_0x0068
            r7 = 1
            goto L_0x0069
        L_0x0068:
            r7 = 0
        L_0x0069:
            if (r7 != 0) goto L_0x006c
            goto L_0x0070
        L_0x006c:
            r5.add(r6)
            goto L_0x0054
        L_0x0070:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r4 = new java.util.ArrayList
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r11)
            r4.<init>(r6)
            r14 = r4
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.Iterator r15 = r5.iterator()
        L_0x0084:
            boolean r4 = r15.hasNext()
            if (r4 == 0) goto L_0x00a5
            java.lang.Object r4 = r15.next()
            r5 = r4
            com.afollestad.date.data.DayOfWeek r5 = (com.afollestad.date.data.DayOfWeek) r5
            com.afollestad.date.data.MonthItem$DayOfMonth r10 = new com.afollestad.date.data.MonthItem$DayOfMonth
            r7 = 0
            r8 = 0
            r9 = 12
            r16 = 0
            r4 = r10
            r6 = r3
            r12 = r10
            r10 = r16
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r14.add(r12)
            goto L_0x0084
        L_0x00a5:
            java.util.List r14 = (java.util.List) r14
            java.util.Collection r14 = (java.util.Collection) r14
            r2.addAll(r14)
            int r4 = r17.getDaysInMonth()
            if (r13 > r4) goto L_0x00e0
            r5 = 1
        L_0x00b3:
            java.util.Calendar r6 = r0.calendar
            com.afollestad.date.CalendarsKt.setDayOfMonth(r6, r5)
            com.afollestad.date.data.MonthItem$DayOfMonth r6 = new com.afollestad.date.data.MonthItem$DayOfMonth
            java.util.Calendar r7 = r0.calendar
            com.afollestad.date.data.DayOfWeek r7 = com.afollestad.date.CalendarsKt.getDayOfWeek(r7)
            com.afollestad.date.data.snapshot.DateSnapshot r8 = new com.afollestad.date.data.snapshot.DateSnapshot
            java.util.Calendar r9 = r0.calendar
            int r9 = com.afollestad.date.CalendarsKt.getMonth(r9)
            java.util.Calendar r10 = r0.calendar
            int r10 = com.afollestad.date.CalendarsKt.getYear(r10)
            r8.<init>(r9, r5, r10)
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r8)
            r6.<init>(r7, r3, r5, r8)
            r2.add(r6)
            if (r5 == r4) goto L_0x00e0
            int r5 = r5 + 1
            goto L_0x00b3
        L_0x00e0:
            int r1 = r2.size()
            r12 = 49
            if (r1 >= r12) goto L_0x016c
            java.util.List<? extends com.afollestad.date.data.DayOfWeek> r1 = r0.orderedWeekDays
            java.lang.Object r1 = kotlin.collections.CollectionsKt.last(r1)
            com.afollestad.date.data.DayOfWeek r1 = (com.afollestad.date.data.DayOfWeek) r1
            com.afollestad.date.data.DayOfWeek r1 = com.afollestad.date.data.DayOfWeekKt.nextDayOfWeek(r1)
            java.lang.Object r4 = kotlin.collections.CollectionsKt.last(r2)
            if (r4 == 0) goto L_0x0164
            com.afollestad.date.data.MonthItem$DayOfMonth r4 = (com.afollestad.date.data.MonthItem.DayOfMonth) r4
            com.afollestad.date.data.DayOfWeek r4 = r4.getDayOfWeek()
            com.afollestad.date.data.DayOfWeek r4 = com.afollestad.date.data.DayOfWeekKt.nextDayOfWeek(r4)
            java.util.List r4 = com.afollestad.date.data.DayOfWeekKt.andTheRest(r4)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r4.iterator()
        L_0x0113:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x012c
            java.lang.Object r6 = r4.next()
            r7 = r6
            com.afollestad.date.data.DayOfWeek r7 = (com.afollestad.date.data.DayOfWeek) r7
            if (r7 == r1) goto L_0x0124
            r7 = 1
            goto L_0x0125
        L_0x0124:
            r7 = 0
        L_0x0125:
            if (r7 != 0) goto L_0x0128
            goto L_0x012c
        L_0x0128:
            r5.add(r6)
            goto L_0x0113
        L_0x012c:
            java.util.List r5 = (java.util.List) r5
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r1 = new java.util.ArrayList
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r11)
            r1.<init>(r4)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r14 = r5.iterator()
        L_0x013f:
            boolean r4 = r14.hasNext()
            if (r4 == 0) goto L_0x015c
            java.lang.Object r4 = r14.next()
            r5 = r4
            com.afollestad.date.data.DayOfWeek r5 = (com.afollestad.date.data.DayOfWeek) r5
            com.afollestad.date.data.MonthItem$DayOfMonth r15 = new com.afollestad.date.data.MonthItem$DayOfMonth
            r7 = 0
            r8 = 0
            r9 = 12
            r10 = 0
            r4 = r15
            r6 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r1.add(r15)
            goto L_0x013f
        L_0x015c:
            java.util.List r1 = (java.util.List) r1
            java.util.Collection r1 = (java.util.Collection) r1
            r2.addAll(r1)
            goto L_0x016c
        L_0x0164:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type com.afollestad.date.data.MonthItem.DayOfMonth"
            r1.<init>(r2)
            throw r1
        L_0x016c:
            int r1 = r2.size()
            if (r1 >= r12) goto L_0x01ab
            java.util.List<? extends com.afollestad.date.data.DayOfWeek> r1 = r0.orderedWeekDays
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r4 = new java.util.ArrayList
            int r5 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r11)
            r4.<init>(r5)
            r14 = r4
            java.util.Collection r14 = (java.util.Collection) r14
            java.util.Iterator r1 = r1.iterator()
        L_0x0186:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x01a3
            java.lang.Object r4 = r1.next()
            r5 = r4
            com.afollestad.date.data.DayOfWeek r5 = (com.afollestad.date.data.DayOfWeek) r5
            com.afollestad.date.data.MonthItem$DayOfMonth r15 = new com.afollestad.date.data.MonthItem$DayOfMonth
            r7 = -1
            r8 = 0
            r9 = 8
            r10 = 0
            r4 = r15
            r6 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r14.add(r15)
            goto L_0x0186
        L_0x01a3:
            java.util.List r14 = (java.util.List) r14
            java.util.Collection r14 = (java.util.Collection) r14
            r2.addAll(r14)
            goto L_0x016c
        L_0x01ab:
            int r1 = r2.size()
            if (r1 != r12) goto L_0x01b3
            r12 = 1
            goto L_0x01b4
        L_0x01b3:
            r12 = 0
        L_0x01b4:
            if (r12 == 0) goto L_0x01b7
            return r2
        L_0x01b7:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r2 = " must equal 49"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.date.data.MonthGraph.getMonthItems(com.afollestad.date.data.snapshot.DateSnapshot):java.util.List");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/afollestad/date/data/MonthGraph$Companion;", "", "()V", "EXPECTED_SIZE", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
    /* compiled from: MonthGraph.kt */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
