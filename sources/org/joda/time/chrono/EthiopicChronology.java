package org.joda.time.chrono;

import com.medscape.android.Constants;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.SkipDateTimeField;

public final class EthiopicChronology extends BasicFixedMonthChronology {
    public static final int EE = 1;
    private static final DateTimeField ERA_FIELD = new BasicSingleEraDateTimeField("EE");
    private static final EthiopicChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final int MAX_YEAR = 292272984;
    private static final int MIN_YEAR = -292269337;
    private static final ConcurrentHashMap<DateTimeZone, EthiopicChronology[]> cCache = new ConcurrentHashMap<>();
    private static final long serialVersionUID = -5972804258688333942L;

    /* access modifiers changed from: package-private */
    public long getApproxMillisAtEpochDividedByTwo() {
        return 30962844000000L;
    }

    /* access modifiers changed from: package-private */
    public int getMaxYear() {
        return MAX_YEAR;
    }

    /* access modifiers changed from: package-private */
    public int getMinYear() {
        return MIN_YEAR;
    }

    public static EthiopicChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static EthiopicChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static EthiopicChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = new org.joda.time.chrono.EthiopicChronology[7];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.joda.time.chrono.EthiopicChronology getInstance(org.joda.time.DateTimeZone r13, int r14) {
        /*
            if (r13 != 0) goto L_0x0006
            org.joda.time.DateTimeZone r13 = org.joda.time.DateTimeZone.getDefault()
        L_0x0006:
            java.util.concurrent.ConcurrentHashMap<org.joda.time.DateTimeZone, org.joda.time.chrono.EthiopicChronology[]> r0 = cCache
            java.lang.Object r0 = r0.get(r13)
            org.joda.time.chrono.EthiopicChronology[] r0 = (org.joda.time.chrono.EthiopicChronology[]) r0
            if (r0 != 0) goto L_0x001e
            r0 = 7
            org.joda.time.chrono.EthiopicChronology[] r0 = new org.joda.time.chrono.EthiopicChronology[r0]
            java.util.concurrent.ConcurrentHashMap<org.joda.time.DateTimeZone, org.joda.time.chrono.EthiopicChronology[]> r1 = cCache
            java.lang.Object r1 = r1.putIfAbsent(r13, r0)
            org.joda.time.chrono.EthiopicChronology[] r1 = (org.joda.time.chrono.EthiopicChronology[]) r1
            if (r1 == 0) goto L_0x001e
            r0 = r1
        L_0x001e:
            int r1 = r14 + -1
            r2 = r0[r1]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0063 }
            if (r2 != 0) goto L_0x0062
            monitor-enter(r0)
            r2 = r0[r1]     // Catch:{ all -> 0x005f }
            if (r2 != 0) goto L_0x005d
            org.joda.time.DateTimeZone r2 = org.joda.time.DateTimeZone.UTC     // Catch:{ all -> 0x005f }
            r3 = 0
            if (r13 != r2) goto L_0x004b
            org.joda.time.chrono.EthiopicChronology r13 = new org.joda.time.chrono.EthiopicChronology     // Catch:{ all -> 0x005f }
            r13.<init>(r3, r3, r14)     // Catch:{ all -> 0x005f }
            org.joda.time.DateTime r2 = new org.joda.time.DateTime     // Catch:{ all -> 0x005f }
            r5 = 1
            r6 = 1
            r7 = 1
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r4 = r2
            r12 = r13
            r4.<init>((int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (int) r10, (int) r11, (org.joda.time.Chronology) r12)     // Catch:{ all -> 0x005f }
            org.joda.time.chrono.EthiopicChronology r4 = new org.joda.time.chrono.EthiopicChronology     // Catch:{ all -> 0x005f }
            org.joda.time.chrono.LimitChronology r13 = org.joda.time.chrono.LimitChronology.getInstance(r13, r2, r3)     // Catch:{ all -> 0x005f }
            r4.<init>(r13, r3, r14)     // Catch:{ all -> 0x005f }
            goto L_0x005a
        L_0x004b:
            org.joda.time.DateTimeZone r2 = org.joda.time.DateTimeZone.UTC     // Catch:{ all -> 0x005f }
            org.joda.time.chrono.EthiopicChronology r2 = getInstance(r2, r14)     // Catch:{ all -> 0x005f }
            org.joda.time.chrono.EthiopicChronology r4 = new org.joda.time.chrono.EthiopicChronology     // Catch:{ all -> 0x005f }
            org.joda.time.chrono.ZonedChronology r13 = org.joda.time.chrono.ZonedChronology.getInstance(r2, r13)     // Catch:{ all -> 0x005f }
            r4.<init>(r13, r3, r14)     // Catch:{ all -> 0x005f }
        L_0x005a:
            r0[r1] = r4     // Catch:{ all -> 0x005f }
            r2 = r4
        L_0x005d:
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            goto L_0x0062
        L_0x005f:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005f }
            throw r13
        L_0x0062:
            return r2
        L_0x0063:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid min days in first week: "
            r0.append(r1)
            r0.append(r14)
            java.lang.String r14 = r0.toString()
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.chrono.EthiopicChronology.getInstance(org.joda.time.DateTimeZone, int):org.joda.time.chrono.EthiopicChronology");
    }

    EthiopicChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    private Object readResolve() {
        Chronology base = getBase();
        return getInstance(base == null ? DateTimeZone.UTC : base.getZone(), getMinimumDaysInFirstWeek());
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        if (dateTimeZone == getZone()) {
            return this;
        }
        return getInstance(dateTimeZone);
    }

    /* access modifiers changed from: package-private */
    public boolean isLeapDay(long j) {
        return dayOfMonth().get(j) == 6 && monthOfYear().isLeap(j);
    }

    /* access modifiers changed from: package-private */
    public long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1963;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            int i4 = i3 >> 2;
            i2 = !isLeapYear(i) ? i4 + 1 : i4;
        }
        return (((((long) i3) * 365) + ((long) i2)) * Constants.DAY_IN_MILLIS) + 21859200000L;
    }

    /* access modifiers changed from: protected */
    public void assemble(AssembledChronology.Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
            fields.era = ERA_FIELD;
            fields.monthOfYear = new BasicMonthOfYearDateTimeField(this, 13);
            fields.months = fields.monthOfYear.getDurationField();
        }
    }
}
