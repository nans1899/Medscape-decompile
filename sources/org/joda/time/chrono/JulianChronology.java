package org.joda.time.chrono;

import com.medscape.android.Constants;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.SkipDateTimeField;

public final class JulianChronology extends BasicGJChronology {
    private static final JulianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final int MAX_YEAR = 292272992;
    private static final long MILLIS_PER_MONTH = 2629800000L;
    private static final long MILLIS_PER_YEAR = 31557600000L;
    private static final int MIN_YEAR = -292269054;
    private static final ConcurrentHashMap<DateTimeZone, JulianChronology[]> cCache = new ConcurrentHashMap<>();
    private static final long serialVersionUID = -8731039522547897247L;

    /* access modifiers changed from: package-private */
    public long getApproxMillisAtEpochDividedByTwo() {
        return 31083663600000L;
    }

    /* access modifiers changed from: package-private */
    public long getAverageMillisPerMonth() {
        return MILLIS_PER_MONTH;
    }

    /* access modifiers changed from: package-private */
    public long getAverageMillisPerYear() {
        return MILLIS_PER_YEAR;
    }

    /* access modifiers changed from: package-private */
    public long getAverageMillisPerYearDividedByTwo() {
        return 15778800000L;
    }

    /* access modifiers changed from: package-private */
    public int getMaxYear() {
        return MAX_YEAR;
    }

    /* access modifiers changed from: package-private */
    public int getMinYear() {
        return MIN_YEAR;
    }

    /* access modifiers changed from: package-private */
    public boolean isLeapYear(int i) {
        return (i & 3) == 0;
    }

    static int adjustYearForSet(int i) {
        if (i > 0) {
            return i;
        }
        if (i != 0) {
            return i + 1;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.year(), (Number) Integer.valueOf(i), (Number) null, (Number) null);
    }

    public static JulianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static JulianChronology getInstance() {
        return getInstance(DateTimeZone.getDefault(), 4);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = new org.joda.time.chrono.JulianChronology[7];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.joda.time.chrono.JulianChronology getInstance(org.joda.time.DateTimeZone r5, int r6) {
        /*
            if (r5 != 0) goto L_0x0006
            org.joda.time.DateTimeZone r5 = org.joda.time.DateTimeZone.getDefault()
        L_0x0006:
            java.util.concurrent.ConcurrentHashMap<org.joda.time.DateTimeZone, org.joda.time.chrono.JulianChronology[]> r0 = cCache
            java.lang.Object r0 = r0.get(r5)
            org.joda.time.chrono.JulianChronology[] r0 = (org.joda.time.chrono.JulianChronology[]) r0
            if (r0 != 0) goto L_0x001e
            r0 = 7
            org.joda.time.chrono.JulianChronology[] r0 = new org.joda.time.chrono.JulianChronology[r0]
            java.util.concurrent.ConcurrentHashMap<org.joda.time.DateTimeZone, org.joda.time.chrono.JulianChronology[]> r1 = cCache
            java.lang.Object r1 = r1.putIfAbsent(r5, r0)
            org.joda.time.chrono.JulianChronology[] r1 = (org.joda.time.chrono.JulianChronology[]) r1
            if (r1 == 0) goto L_0x001e
            r0 = r1
        L_0x001e:
            int r1 = r6 + -1
            r2 = r0[r1]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x004d }
            if (r2 != 0) goto L_0x004c
            monitor-enter(r0)
            r2 = r0[r1]     // Catch:{ all -> 0x0049 }
            if (r2 != 0) goto L_0x0047
            org.joda.time.DateTimeZone r2 = org.joda.time.DateTimeZone.UTC     // Catch:{ all -> 0x0049 }
            r3 = 0
            if (r5 != r2) goto L_0x0034
            org.joda.time.chrono.JulianChronology r5 = new org.joda.time.chrono.JulianChronology     // Catch:{ all -> 0x0049 }
            r5.<init>(r3, r3, r6)     // Catch:{ all -> 0x0049 }
            goto L_0x0044
        L_0x0034:
            org.joda.time.DateTimeZone r2 = org.joda.time.DateTimeZone.UTC     // Catch:{ all -> 0x0049 }
            org.joda.time.chrono.JulianChronology r2 = getInstance(r2, r6)     // Catch:{ all -> 0x0049 }
            org.joda.time.chrono.JulianChronology r4 = new org.joda.time.chrono.JulianChronology     // Catch:{ all -> 0x0049 }
            org.joda.time.chrono.ZonedChronology r5 = org.joda.time.chrono.ZonedChronology.getInstance(r2, r5)     // Catch:{ all -> 0x0049 }
            r4.<init>(r5, r3, r6)     // Catch:{ all -> 0x0049 }
            r5 = r4
        L_0x0044:
            r0[r1] = r5     // Catch:{ all -> 0x0049 }
            r2 = r5
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            goto L_0x004c
        L_0x0049:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0049 }
            throw r5
        L_0x004c:
            return r2
        L_0x004d:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid min days in first week: "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.chrono.JulianChronology.getInstance(org.joda.time.DateTimeZone, int):org.joda.time.chrono.JulianChronology");
    }

    JulianChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    private Object readResolve() {
        Chronology base = getBase();
        int minimumDaysInFirstWeek = getMinimumDaysInFirstWeek();
        if (minimumDaysInFirstWeek == 0) {
            minimumDaysInFirstWeek = 4;
        }
        return getInstance(base == null ? DateTimeZone.UTC : base.getZone(), minimumDaysInFirstWeek);
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
    public long getDateMidnightMillis(int i, int i2, int i3) throws IllegalArgumentException {
        return super.getDateMidnightMillis(adjustYearForSet(i), i2, i3);
    }

    /* access modifiers changed from: package-private */
    public long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1968;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            int i4 = i3 >> 2;
            i2 = !isLeapYear(i) ? i4 + 1 : i4;
        }
        return (((((long) i3) * 365) + ((long) i2)) * Constants.DAY_IN_MILLIS) - 62035200000L;
    }

    /* access modifiers changed from: protected */
    public void assemble(AssembledChronology.Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
        }
    }
}
