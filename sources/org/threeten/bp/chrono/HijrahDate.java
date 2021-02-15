package org.threeten.bp.chrono;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;

public final class HijrahDate extends ChronoDateImpl<HijrahDate> implements Serializable {
    private static final Long[] ADJUSTED_CYCLES = new Long[MAX_ADJUSTED_CYCLE];
    private static final HashMap<Integer, Integer[]> ADJUSTED_CYCLE_YEARS = new HashMap<>();
    private static final Integer[] ADJUSTED_LEAST_MAX_VALUES = new Integer[LEAST_MAX_VALUES.length];
    private static final Integer[] ADJUSTED_MAX_VALUES = new Integer[MAX_VALUES.length];
    private static final Integer[] ADJUSTED_MIN_VALUES = new Integer[MIN_VALUES.length];
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_DAYS = new HashMap<>();
    private static final HashMap<Integer, Integer[]> ADJUSTED_MONTH_LENGTHS = new HashMap<>();
    private static final int[] CYCLEYEAR_START_DATE = {0, 354, 709, 1063, 1417, 1772, 2126, 2481, 2835, 3189, 3544, 3898, 4252, 4607, 4961, 5315, 5670, 6024, 6379, 6733, 7087, 7442, 7796, 8150, 8505, 8859, 9214, 9568, 9922, 10277};
    private static final String DEFAULT_CONFIG_FILENAME = "hijrah_deviation.cfg";
    private static final String DEFAULT_CONFIG_PATH = ("org" + FILE_SEP + "threeten" + FILE_SEP + "bp" + FILE_SEP + "chrono");
    private static final Integer[] DEFAULT_CYCLE_YEARS = new Integer[CYCLEYEAR_START_DATE.length];
    private static final Integer[] DEFAULT_LEAP_MONTH_DAYS = new Integer[LEAP_NUM_DAYS.length];
    private static final Integer[] DEFAULT_LEAP_MONTH_LENGTHS = new Integer[LEAP_MONTH_LENGTH.length];
    private static final Integer[] DEFAULT_MONTH_DAYS = new Integer[NUM_DAYS.length];
    private static final Integer[] DEFAULT_MONTH_LENGTHS = new Integer[MONTH_LENGTH.length];
    private static final char FILE_SEP = File.separatorChar;
    private static final int HIJRAH_JAN_1_1_GREGORIAN_DAY = -492148;
    private static final int[] LEAP_MONTH_LENGTH = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 30};
    private static final int[] LEAP_NUM_DAYS = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS};
    private static final int[] LEAST_MAX_VALUES = {1, 9999, 11, 51, 5, 29, 354};
    private static final int MAX_ADJUSTED_CYCLE = 334;
    private static final int[] MAX_VALUES = {1, 9999, 11, 52, 6, 30, 355};
    public static final int MAX_VALUE_OF_ERA = 9999;
    private static final int[] MIN_VALUES = {0, 1, 0, 1, 0, 1, 1};
    public static final int MIN_VALUE_OF_ERA = 1;
    private static final int[] MONTH_LENGTH = {30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 29};
    private static final int[] NUM_DAYS = {0, 30, 59, 89, 118, 148, 177, 207, 236, 266, 295, PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS};
    private static final String PATH_SEP = File.pathSeparator;
    private static final int POSITION_DAY_OF_MONTH = 5;
    private static final int POSITION_DAY_OF_YEAR = 6;
    private static final long serialVersionUID = -5207853542612002020L;
    private final transient int dayOfMonth;
    private final transient DayOfWeek dayOfWeek;
    private final transient int dayOfYear;
    private final transient HijrahEra era;
    private final long gregorianEpochDay;
    private final transient boolean isLeapYear = isLeapYear((long) this.yearOfEra);
    private final transient int monthOfYear;
    private final transient int yearOfEra;

    public /* bridge */ /* synthetic */ long until(Temporal temporal, TemporalUnit temporalUnit) {
        return super.until(temporal, temporalUnit);
    }

    public /* bridge */ /* synthetic */ ChronoPeriod until(ChronoLocalDate chronoLocalDate) {
        return super.until(chronoLocalDate);
    }

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = NUM_DAYS;
            if (i2 >= iArr.length) {
                break;
            }
            DEFAULT_MONTH_DAYS[i2] = Integer.valueOf(iArr[i2]);
            i2++;
        }
        int i3 = 0;
        while (true) {
            int[] iArr2 = LEAP_NUM_DAYS;
            if (i3 >= iArr2.length) {
                break;
            }
            DEFAULT_LEAP_MONTH_DAYS[i3] = Integer.valueOf(iArr2[i3]);
            i3++;
        }
        int i4 = 0;
        while (true) {
            int[] iArr3 = MONTH_LENGTH;
            if (i4 >= iArr3.length) {
                break;
            }
            DEFAULT_MONTH_LENGTHS[i4] = Integer.valueOf(iArr3[i4]);
            i4++;
        }
        int i5 = 0;
        while (true) {
            int[] iArr4 = LEAP_MONTH_LENGTH;
            if (i5 >= iArr4.length) {
                break;
            }
            DEFAULT_LEAP_MONTH_LENGTHS[i5] = Integer.valueOf(iArr4[i5]);
            i5++;
        }
        int i6 = 0;
        while (true) {
            int[] iArr5 = CYCLEYEAR_START_DATE;
            if (i6 >= iArr5.length) {
                break;
            }
            DEFAULT_CYCLE_YEARS[i6] = Integer.valueOf(iArr5[i6]);
            i6++;
        }
        int i7 = 0;
        while (true) {
            Long[] lArr = ADJUSTED_CYCLES;
            if (i7 >= lArr.length) {
                break;
            }
            lArr[i7] = Long.valueOf((long) (i7 * 10631));
            i7++;
        }
        int i8 = 0;
        while (true) {
            int[] iArr6 = MIN_VALUES;
            if (i8 >= iArr6.length) {
                break;
            }
            ADJUSTED_MIN_VALUES[i8] = Integer.valueOf(iArr6[i8]);
            i8++;
        }
        int i9 = 0;
        while (true) {
            int[] iArr7 = LEAST_MAX_VALUES;
            if (i9 >= iArr7.length) {
                break;
            }
            ADJUSTED_LEAST_MAX_VALUES[i9] = Integer.valueOf(iArr7[i9]);
            i9++;
        }
        while (true) {
            int[] iArr8 = MAX_VALUES;
            if (i < iArr8.length) {
                ADJUSTED_MAX_VALUES[i] = Integer.valueOf(iArr8[i]);
                i++;
            } else {
                try {
                    readDeviationConfig();
                    return;
                } catch (IOException | ParseException unused) {
                    return;
                }
            }
        }
    }

    public static HijrahDate now() {
        return now(Clock.systemDefaultZone());
    }

    public static HijrahDate now(ZoneId zoneId) {
        return now(Clock.system(zoneId));
    }

    public static HijrahDate now(Clock clock) {
        return HijrahChronology.INSTANCE.dateNow(clock);
    }

    public static HijrahDate of(int i, int i2, int i3) {
        if (i >= 1) {
            return of(HijrahEra.AH, i, i2, i3);
        }
        return of(HijrahEra.BEFORE_AH, 1 - i, i2, i3);
    }

    static HijrahDate of(HijrahEra hijrahEra, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(hijrahEra, "era");
        checkValidYearOfEra(i);
        checkValidMonth(i2);
        checkValidDayOfMonth(i3);
        return new HijrahDate(getGregorianEpochDay(hijrahEra.prolepticYear(i), i2, i3));
    }

    private static void checkValidYearOfEra(int i) {
        if (i < 1 || i > 9999) {
            throw new DateTimeException("Invalid year of Hijrah Era");
        }
    }

    private static void checkValidDayOfYear(int i) {
        if (i < 1 || i > getMaximumDayOfYear()) {
            throw new DateTimeException("Invalid day of year of Hijrah date");
        }
    }

    private static void checkValidMonth(int i) {
        if (i < 1 || i > 12) {
            throw new DateTimeException("Invalid month of Hijrah date");
        }
    }

    private static void checkValidDayOfMonth(int i) {
        if (i < 1 || i > getMaximumDayOfMonth()) {
            throw new DateTimeException("Invalid day of month of Hijrah date, day " + i + " greater than " + getMaximumDayOfMonth() + " or less than 1");
        }
    }

    static HijrahDate of(LocalDate localDate) {
        return new HijrahDate(localDate.toEpochDay());
    }

    static HijrahDate ofEpochDay(long j) {
        return new HijrahDate(j);
    }

    public static HijrahDate from(TemporalAccessor temporalAccessor) {
        return HijrahChronology.INSTANCE.date(temporalAccessor);
    }

    private HijrahDate(long j) {
        int[] hijrahDateInfo = getHijrahDateInfo(j);
        checkValidYearOfEra(hijrahDateInfo[1]);
        checkValidMonth(hijrahDateInfo[2]);
        checkValidDayOfMonth(hijrahDateInfo[3]);
        checkValidDayOfYear(hijrahDateInfo[4]);
        this.era = HijrahEra.of(hijrahDateInfo[0]);
        this.yearOfEra = hijrahDateInfo[1];
        this.monthOfYear = hijrahDateInfo[2];
        this.dayOfMonth = hijrahDateInfo[3];
        this.dayOfYear = hijrahDateInfo[4];
        this.dayOfWeek = DayOfWeek.of(hijrahDateInfo[5]);
        this.gregorianEpochDay = j;
    }

    private Object readResolve() {
        return new HijrahDate(this.gregorianEpochDay);
    }

    public HijrahChronology getChronology() {
        return HijrahChronology.INSTANCE;
    }

    public HijrahEra getEra() {
        return this.era;
    }

    public ValueRange range(TemporalField temporalField) {
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.rangeRefinedBy(this);
        }
        if (isSupported(temporalField)) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()];
            if (i == 1) {
                return ValueRange.of(1, (long) lengthOfMonth());
            }
            if (i == 2) {
                return ValueRange.of(1, (long) lengthOfYear());
            }
            if (i == 3) {
                return ValueRange.of(1, 5);
            }
            if (i != 4) {
                return getChronology().range(chronoField);
            }
            return ValueRange.of(1, 1000);
        }
        throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
    }

    /* renamed from: org.threeten.bp.chrono.HijrahDate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$ChronoField;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.threeten.bp.temporal.ChronoField[] r0 = org.threeten.bp.temporal.ChronoField.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$threeten$bp$temporal$ChronoField = r0
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.DAY_OF_MONTH     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x001d }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.DAY_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.ALIGNED_WEEK_OF_MONTH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.YEAR_OF_ERA     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x003e }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.DAY_OF_WEEK     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.EPOCH_DAY     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x006c }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.MONTH_OF_YEAR     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.YEAR     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$ChronoField     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.ERA     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.HijrahDate.AnonymousClass1.<clinit>():void");
        }
    }

    public long getLong(TemporalField temporalField) {
        int i;
        int i2;
        if (!(temporalField instanceof ChronoField)) {
            return temporalField.getFrom(this);
        }
        switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[((ChronoField) temporalField).ordinal()]) {
            case 1:
                i = this.dayOfMonth;
                break;
            case 2:
                i = this.dayOfYear;
                break;
            case 3:
                i2 = (this.dayOfMonth - 1) / 7;
                break;
            case 4:
                i = this.yearOfEra;
                break;
            case 5:
                i = this.dayOfWeek.getValue();
                break;
            case 6:
                i2 = (this.dayOfMonth - 1) % 7;
                break;
            case 7:
                i2 = (this.dayOfYear - 1) % 7;
                break;
            case 8:
                return toEpochDay();
            case 9:
                i2 = (this.dayOfYear - 1) / 7;
                break;
            case 10:
                i = this.monthOfYear;
                break;
            case 11:
                i = this.yearOfEra;
                break;
            case 12:
                i = this.era.getValue();
                break;
            default:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        i = i2 + 1;
        return (long) i;
    }

    public HijrahDate with(TemporalAdjuster temporalAdjuster) {
        return (HijrahDate) super.with(temporalAdjuster);
    }

    public HijrahDate with(TemporalField temporalField, long j) {
        if (!(temporalField instanceof ChronoField)) {
            return (HijrahDate) temporalField.adjustInto(this, j);
        }
        ChronoField chronoField = (ChronoField) temporalField;
        chronoField.checkValidValue(j);
        int i = (int) j;
        switch (AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$ChronoField[chronoField.ordinal()]) {
            case 1:
                return resolvePreviousValid(this.yearOfEra, this.monthOfYear, i);
            case 2:
                int i2 = i - 1;
                return resolvePreviousValid(this.yearOfEra, (i2 / 30) + 1, (i2 % 30) + 1);
            case 3:
                return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_MONTH)) * 7);
            case 4:
                if (this.yearOfEra < 1) {
                    i = 1 - i;
                }
                return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
            case 5:
                return plusDays(j - ((long) this.dayOfWeek.getValue()));
            case 6:
                return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
            case 7:
                return plusDays(j - getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
            case 8:
                return new HijrahDate((long) i);
            case 9:
                return plusDays((j - getLong(ChronoField.ALIGNED_WEEK_OF_YEAR)) * 7);
            case 10:
                return resolvePreviousValid(this.yearOfEra, i, this.dayOfMonth);
            case 11:
                return resolvePreviousValid(i, this.monthOfYear, this.dayOfMonth);
            case 12:
                return resolvePreviousValid(1 - this.yearOfEra, this.monthOfYear, this.dayOfMonth);
            default:
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
    }

    private static HijrahDate resolvePreviousValid(int i, int i2, int i3) {
        int monthDays = getMonthDays(i2 - 1, i);
        if (i3 > monthDays) {
            i3 = monthDays;
        }
        return of(i, i2, i3);
    }

    public HijrahDate plus(TemporalAmount temporalAmount) {
        return (HijrahDate) super.plus(temporalAmount);
    }

    public HijrahDate plus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.plus(j, temporalUnit);
    }

    public HijrahDate minus(TemporalAmount temporalAmount) {
        return (HijrahDate) super.minus(temporalAmount);
    }

    public HijrahDate minus(long j, TemporalUnit temporalUnit) {
        return (HijrahDate) super.minus(j, temporalUnit);
    }

    public final ChronoLocalDateTime<HijrahDate> atTime(LocalTime localTime) {
        return super.atTime(localTime);
    }

    public long toEpochDay() {
        return getGregorianEpochDay(this.yearOfEra, this.monthOfYear, this.dayOfMonth);
    }

    public boolean isLeapYear() {
        return this.isLeapYear;
    }

    /* access modifiers changed from: package-private */
    public HijrahDate plusYears(long j) {
        if (j == 0) {
            return this;
        }
        return of(this.era, Jdk8Methods.safeAdd(this.yearOfEra, (int) j), this.monthOfYear, this.dayOfMonth);
    }

    /* access modifiers changed from: package-private */
    public HijrahDate plusMonths(long j) {
        if (j == 0) {
            return this;
        }
        int i = (this.monthOfYear - 1) + ((int) j);
        int i2 = i / 12;
        int i3 = i % 12;
        while (i3 < 0) {
            i3 += 12;
            i2 = Jdk8Methods.safeSubtract(i2, 1);
        }
        return of(this.era, Jdk8Methods.safeAdd(this.yearOfEra, i2), i3 + 1, this.dayOfMonth);
    }

    /* access modifiers changed from: package-private */
    public HijrahDate plusDays(long j) {
        return new HijrahDate(this.gregorianEpochDay + j);
    }

    private static int[] getHijrahDateInfo(long j) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        long j2 = j - -492148;
        if (j2 >= 0) {
            int cycleNumber = getCycleNumber(j2);
            int dayOfCycle = getDayOfCycle(j2, cycleNumber);
            int yearInCycle = getYearInCycle(cycleNumber, (long) dayOfCycle);
            i4 = getDayOfYear(cycleNumber, dayOfCycle, yearInCycle);
            i5 = (cycleNumber * 30) + yearInCycle + 1;
            i3 = getMonthOfYear(i4, i5);
            i2 = getDayOfMonth(i4, i3, i5) + 1;
            i = HijrahEra.AH.getValue();
        } else {
            int i6 = (int) j2;
            int i7 = i6 / 10631;
            int i8 = i6 % 10631;
            if (i8 == 0) {
                i8 = -10631;
                i7++;
            }
            int yearInCycle2 = getYearInCycle(i7, (long) i8);
            int dayOfYear2 = getDayOfYear(i7, i8, yearInCycle2);
            int i9 = 1 - ((i7 * 30) - yearInCycle2);
            int i10 = isLeapYear((long) i9) ? dayOfYear2 + 355 : dayOfYear2 + 354;
            i3 = getMonthOfYear(i10, i9);
            i2 = getDayOfMonth(i10, i3, i9) + 1;
            i = HijrahEra.BEFORE_AH.getValue();
            int i11 = i9;
            i4 = i10;
            i5 = i11;
        }
        int i12 = (int) ((j2 + 5) % 7);
        return new int[]{i, i5, i3 + 1, i2, i4 + 1, i12 + (i12 <= 0 ? 7 : 0)};
    }

    private static long getGregorianEpochDay(int i, int i2, int i3) {
        return yearToGregorianEpochDay(i) + ((long) getMonthDays(i2 - 1, i)) + ((long) i3);
    }

    private static long yearToGregorianEpochDay(int i) {
        Long l;
        int i2 = i - 1;
        int i3 = i2 / 30;
        int i4 = i2 % 30;
        int intValue = getAdjustedCycle(i3)[Math.abs(i4)].intValue();
        if (i4 < 0) {
            intValue = -intValue;
        }
        try {
            l = ADJUSTED_CYCLES[i3];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l = null;
        }
        if (l == null) {
            l = Long.valueOf((long) (i3 * 10631));
        }
        return ((l.longValue() + ((long) intValue)) - 492148) - 1;
    }

    private static int getCycleNumber(long j) {
        Long[] lArr = ADJUSTED_CYCLES;
        int i = 0;
        while (i < lArr.length) {
            try {
                if (j < lArr[i].longValue()) {
                    return i - 1;
                }
                i++;
            } catch (ArrayIndexOutOfBoundsException unused) {
                return ((int) j) / 10631;
            }
        }
        return ((int) j) / 10631;
    }

    private static int getDayOfCycle(long j, int i) {
        Long l;
        try {
            l = ADJUSTED_CYCLES[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            l = null;
        }
        if (l == null) {
            l = Long.valueOf((long) (i * 10631));
        }
        return (int) (j - l.longValue());
    }

    private static int getYearInCycle(int i, long j) {
        Integer[] adjustedCycle = getAdjustedCycle(i);
        int i2 = 0;
        int i3 = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i3 == 0) {
            return 0;
        }
        if (i3 > 0) {
            while (i2 < adjustedCycle.length) {
                if (j < ((long) adjustedCycle[i2].intValue())) {
                    return i2 - 1;
                }
                i2++;
            }
            return 29;
        }
        long j2 = -j;
        while (i2 < adjustedCycle.length) {
            if (j2 <= ((long) adjustedCycle[i2].intValue())) {
                return i2 - 1;
            }
            i2++;
        }
        return 29;
    }

    private static Integer[] getAdjustedCycle(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        return numArr == null ? DEFAULT_CYCLE_YEARS : numArr;
    }

    private static Integer[] getAdjustedMonthDays(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr != null) {
            return numArr;
        }
        if (isLeapYear((long) i)) {
            return DEFAULT_LEAP_MONTH_DAYS;
        }
        return DEFAULT_MONTH_DAYS;
    }

    private static Integer[] getAdjustedMonthLength(int i) {
        Integer[] numArr;
        try {
            numArr = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr != null) {
            return numArr;
        }
        if (isLeapYear((long) i)) {
            return DEFAULT_LEAP_MONTH_LENGTHS;
        }
        return DEFAULT_MONTH_LENGTHS;
    }

    private static int getDayOfYear(int i, int i2, int i3) {
        Integer[] adjustedCycle = getAdjustedCycle(i);
        if (i2 > 0) {
            return i2 - adjustedCycle[i3].intValue();
        }
        return adjustedCycle[i3].intValue() + i2;
    }

    private static int getMonthOfYear(int i, int i2) {
        Integer[] adjustedMonthDays = getAdjustedMonthDays(i2);
        int i3 = 0;
        if (i >= 0) {
            while (i3 < adjustedMonthDays.length) {
                if (i < adjustedMonthDays[i3].intValue()) {
                    return i3 - 1;
                }
                i3++;
            }
            return 11;
        }
        int i4 = isLeapYear((long) i2) ? i + 355 : i + 354;
        while (i3 < adjustedMonthDays.length) {
            if (i4 < adjustedMonthDays[i3].intValue()) {
                return i3 - 1;
            }
            i3++;
        }
        return 11;
    }

    private static int getDayOfMonth(int i, int i2, int i3) {
        int intValue;
        Integer[] adjustedMonthDays = getAdjustedMonthDays(i3);
        if (i < 0) {
            i = isLeapYear((long) i3) ? i + 355 : i + 354;
            if (i2 <= 0) {
                return i;
            }
            intValue = adjustedMonthDays[i2].intValue();
        } else if (i2 <= 0) {
            return i;
        } else {
            intValue = adjustedMonthDays[i2].intValue();
        }
        return i - intValue;
    }

    static boolean isLeapYear(long j) {
        if (j <= 0) {
            j = -j;
        }
        return ((j * 11) + 14) % 30 < 11;
    }

    private static int getMonthDays(int i, int i2) {
        return getAdjustedMonthDays(i2)[i].intValue();
    }

    static int getMonthLength(int i, int i2) {
        return getAdjustedMonthLength(i2)[i].intValue();
    }

    public int lengthOfMonth() {
        return getMonthLength(this.monthOfYear - 1, this.yearOfEra);
    }

    static int getYearLength(int i) {
        Integer[] numArr;
        int intValue;
        int intValue2;
        int i2 = i - 1;
        int i3 = i2 / 30;
        try {
            numArr = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i3));
        } catch (ArrayIndexOutOfBoundsException unused) {
            numArr = null;
        }
        if (numArr == null) {
            return isLeapYear((long) i) ? 355 : 354;
        }
        int i4 = i2 % 30;
        if (i4 == 29) {
            intValue = ADJUSTED_CYCLES[i3 + 1].intValue() - ADJUSTED_CYCLES[i3].intValue();
            intValue2 = numArr[i4].intValue();
        } else {
            intValue = numArr[i4 + 1].intValue();
            intValue2 = numArr[i4].intValue();
        }
        return intValue - intValue2;
    }

    public int lengthOfYear() {
        return getYearLength(this.yearOfEra);
    }

    static int getMaximumDayOfMonth() {
        return ADJUSTED_MAX_VALUES[5].intValue();
    }

    static int getSmallestMaximumDayOfMonth() {
        return ADJUSTED_LEAST_MAX_VALUES[5].intValue();
    }

    static int getMaximumDayOfYear() {
        return ADJUSTED_MAX_VALUES[6].intValue();
    }

    static int getSmallestMaximumDayOfYear() {
        return ADJUSTED_LEAST_MAX_VALUES[6].intValue();
    }

    private static void addDeviationAsHijrah(int i, int i2, int i3, int i4, int i5) {
        int i6 = i;
        int i7 = i2;
        int i8 = i3;
        int i9 = i4;
        int i10 = i5;
        if (i6 < 1) {
            throw new IllegalArgumentException("startYear < 1");
        } else if (i8 < 1) {
            throw new IllegalArgumentException("endYear < 1");
        } else if (i7 < 0 || i7 > 11) {
            throw new IllegalArgumentException("startMonth < 0 || startMonth > 11");
        } else if (i9 < 0 || i9 > 11) {
            throw new IllegalArgumentException("endMonth < 0 || endMonth > 11");
        } else if (i8 > 9999) {
            throw new IllegalArgumentException("endYear > 9999");
        } else if (i8 < i6) {
            throw new IllegalArgumentException("startYear > endYear");
        } else if (i8 != i6 || i9 >= i7) {
            boolean isLeapYear2 = isLeapYear((long) i6);
            Integer[] numArr = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i));
            if (numArr == null) {
                if (!isLeapYear2) {
                    numArr = new Integer[NUM_DAYS.length];
                    int i11 = 0;
                    while (true) {
                        int[] iArr = NUM_DAYS;
                        if (i11 >= iArr.length) {
                            break;
                        }
                        numArr[i11] = Integer.valueOf(iArr[i11]);
                        i11++;
                    }
                } else {
                    numArr = new Integer[LEAP_NUM_DAYS.length];
                    int i12 = 0;
                    while (true) {
                        int[] iArr2 = LEAP_NUM_DAYS;
                        if (i12 >= iArr2.length) {
                            break;
                        }
                        numArr[i12] = Integer.valueOf(iArr2[i12]);
                        i12++;
                    }
                }
            }
            Integer[] numArr2 = new Integer[numArr.length];
            for (int i13 = 0; i13 < 12; i13++) {
                if (i13 > i7) {
                    numArr2[i13] = Integer.valueOf(numArr[i13].intValue() - i10);
                } else {
                    numArr2[i13] = Integer.valueOf(numArr[i13].intValue());
                }
            }
            ADJUSTED_MONTH_DAYS.put(Integer.valueOf(i), numArr2);
            Integer[] numArr3 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i));
            if (numArr3 == null) {
                if (!isLeapYear2) {
                    numArr3 = new Integer[MONTH_LENGTH.length];
                    int i14 = 0;
                    while (true) {
                        int[] iArr3 = MONTH_LENGTH;
                        if (i14 >= iArr3.length) {
                            break;
                        }
                        numArr3[i14] = Integer.valueOf(iArr3[i14]);
                        i14++;
                    }
                } else {
                    numArr3 = new Integer[LEAP_MONTH_LENGTH.length];
                    int i15 = 0;
                    while (true) {
                        int[] iArr4 = LEAP_MONTH_LENGTH;
                        if (i15 >= iArr4.length) {
                            break;
                        }
                        numArr3[i15] = Integer.valueOf(iArr4[i15]);
                        i15++;
                    }
                }
            }
            Integer[] numArr4 = new Integer[numArr3.length];
            for (int i16 = 0; i16 < 12; i16++) {
                if (i16 == i7) {
                    numArr4[i16] = Integer.valueOf(numArr3[i16].intValue() - i10);
                } else {
                    numArr4[i16] = Integer.valueOf(numArr3[i16].intValue());
                }
            }
            ADJUSTED_MONTH_LENGTHS.put(Integer.valueOf(i), numArr4);
            if (i6 != i8) {
                int i17 = i6 - 1;
                int i18 = i17 / 30;
                int i19 = i17 % 30;
                Integer[] numArr5 = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i18));
                if (numArr5 == null) {
                    int length = CYCLEYEAR_START_DATE.length;
                    Integer[] numArr6 = new Integer[length];
                    for (int i20 = 0; i20 < length; i20++) {
                        numArr6[i20] = Integer.valueOf(CYCLEYEAR_START_DATE[i20]);
                    }
                    numArr5 = numArr6;
                }
                for (int i21 = i19 + 1; i21 < CYCLEYEAR_START_DATE.length; i21++) {
                    numArr5[i21] = Integer.valueOf(numArr5[i21].intValue() - i10);
                }
                ADJUSTED_CYCLE_YEARS.put(Integer.valueOf(i18), numArr5);
                int i22 = i8 - 1;
                int i23 = i22 / 30;
                if (i18 != i23) {
                    int i24 = i18 + 1;
                    while (true) {
                        Long[] lArr = ADJUSTED_CYCLES;
                        if (i24 >= lArr.length) {
                            break;
                        }
                        lArr[i24] = Long.valueOf(lArr[i24].longValue() - ((long) i10));
                        i24++;
                    }
                    int i25 = i23 + 1;
                    while (true) {
                        Long[] lArr2 = ADJUSTED_CYCLES;
                        if (i25 >= lArr2.length) {
                            break;
                        }
                        lArr2[i25] = Long.valueOf(lArr2[i25].longValue() + ((long) i10));
                        i25++;
                        i23 = i23;
                    }
                }
                int i26 = i23;
                int i27 = i22 % 30;
                Integer[] numArr7 = ADJUSTED_CYCLE_YEARS.get(Integer.valueOf(i26));
                if (numArr7 == null) {
                    int length2 = CYCLEYEAR_START_DATE.length;
                    Integer[] numArr8 = new Integer[length2];
                    for (int i28 = 0; i28 < length2; i28++) {
                        numArr8[i28] = Integer.valueOf(CYCLEYEAR_START_DATE[i28]);
                    }
                    numArr7 = numArr8;
                }
                for (int i29 = i27 + 1; i29 < CYCLEYEAR_START_DATE.length; i29++) {
                    numArr7[i29] = Integer.valueOf(numArr7[i29].intValue() + i10);
                }
                ADJUSTED_CYCLE_YEARS.put(Integer.valueOf(i26), numArr7);
            }
            boolean isLeapYear3 = isLeapYear((long) i8);
            Integer[] numArr9 = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i3));
            if (numArr9 == null) {
                if (!isLeapYear3) {
                    numArr9 = new Integer[NUM_DAYS.length];
                    int i30 = 0;
                    while (true) {
                        int[] iArr5 = NUM_DAYS;
                        if (i30 >= iArr5.length) {
                            break;
                        }
                        numArr9[i30] = Integer.valueOf(iArr5[i30]);
                        i30++;
                    }
                } else {
                    numArr9 = new Integer[LEAP_NUM_DAYS.length];
                    int i31 = 0;
                    while (true) {
                        int[] iArr6 = LEAP_NUM_DAYS;
                        if (i31 >= iArr6.length) {
                            break;
                        }
                        numArr9[i31] = Integer.valueOf(iArr6[i31]);
                        i31++;
                    }
                }
            }
            Integer[] numArr10 = new Integer[numArr9.length];
            for (int i32 = 0; i32 < 12; i32++) {
                if (i32 > i9) {
                    numArr10[i32] = Integer.valueOf(numArr9[i32].intValue() + i10);
                } else {
                    numArr10[i32] = Integer.valueOf(numArr9[i32].intValue());
                }
            }
            ADJUSTED_MONTH_DAYS.put(Integer.valueOf(i3), numArr10);
            Integer[] numArr11 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i3));
            if (numArr11 == null) {
                if (!isLeapYear3) {
                    numArr11 = new Integer[MONTH_LENGTH.length];
                    int i33 = 0;
                    while (true) {
                        int[] iArr7 = MONTH_LENGTH;
                        if (i33 >= iArr7.length) {
                            break;
                        }
                        numArr11[i33] = Integer.valueOf(iArr7[i33]);
                        i33++;
                    }
                } else {
                    numArr11 = new Integer[LEAP_MONTH_LENGTH.length];
                    int i34 = 0;
                    while (true) {
                        int[] iArr8 = LEAP_MONTH_LENGTH;
                        if (i34 >= iArr8.length) {
                            break;
                        }
                        numArr11[i34] = Integer.valueOf(iArr8[i34]);
                        i34++;
                    }
                }
            }
            Integer[] numArr12 = new Integer[numArr11.length];
            for (int i35 = 0; i35 < 12; i35++) {
                if (i35 == i9) {
                    numArr12[i35] = Integer.valueOf(numArr11[i35].intValue() + i10);
                } else {
                    numArr12[i35] = Integer.valueOf(numArr11[i35].intValue());
                }
            }
            ADJUSTED_MONTH_LENGTHS.put(Integer.valueOf(i3), numArr12);
            Integer[] numArr13 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i));
            Integer[] numArr14 = ADJUSTED_MONTH_LENGTHS.get(Integer.valueOf(i3));
            int intValue = numArr13[i7].intValue();
            int intValue2 = numArr14[i9].intValue();
            int intValue3 = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i))[11].intValue() + numArr13[11].intValue();
            int intValue4 = ADJUSTED_MONTH_DAYS.get(Integer.valueOf(i3))[11].intValue() + numArr14[11].intValue();
            int intValue5 = ADJUSTED_MAX_VALUES[5].intValue();
            int intValue6 = ADJUSTED_LEAST_MAX_VALUES[5].intValue();
            if (intValue5 < intValue) {
                intValue5 = intValue;
            }
            if (intValue5 < intValue2) {
                intValue5 = intValue2;
            }
            ADJUSTED_MAX_VALUES[5] = Integer.valueOf(intValue5);
            if (intValue6 <= intValue) {
                intValue = intValue6;
            }
            if (intValue <= intValue2) {
                intValue2 = intValue;
            }
            ADJUSTED_LEAST_MAX_VALUES[5] = Integer.valueOf(intValue2);
            int intValue7 = ADJUSTED_MAX_VALUES[6].intValue();
            int intValue8 = ADJUSTED_LEAST_MAX_VALUES[6].intValue();
            if (intValue7 < intValue3) {
                intValue7 = intValue3;
            }
            if (intValue7 < intValue4) {
                intValue7 = intValue4;
            }
            ADJUSTED_MAX_VALUES[6] = Integer.valueOf(intValue7);
            if (intValue8 <= intValue3) {
                intValue3 = intValue8;
            }
            if (intValue3 <= intValue4) {
                intValue4 = intValue3;
            }
            ADJUSTED_LEAST_MAX_VALUES[6] = Integer.valueOf(intValue4);
        } else {
            throw new IllegalArgumentException("startYear == endYear && endMonth < startMonth");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void readDeviationConfig() throws java.io.IOException, java.text.ParseException {
        /*
            java.io.InputStream r0 = getConfigFileInputStream()
            if (r0 == 0) goto L_0x0030
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x0029 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x0029 }
            r3.<init>(r0)     // Catch:{ all -> 0x0029 }
            r2.<init>(r3)     // Catch:{ all -> 0x0029 }
            r0 = 0
        L_0x0012:
            java.lang.String r1 = r2.readLine()     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0022
            int r0 = r0 + 1
            java.lang.String r1 = r1.trim()     // Catch:{ all -> 0x0026 }
            parseLine(r1, r0)     // Catch:{ all -> 0x0026 }
            goto L_0x0012
        L_0x0022:
            r2.close()
            goto L_0x0030
        L_0x0026:
            r0 = move-exception
            r1 = r2
            goto L_0x002a
        L_0x0029:
            r0 = move-exception
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            throw r0
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.HijrahDate.readDeviationConfig():void");
    }

    private static void parseLine(String str, int i) throws ParseException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ";");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(58);
            if (indexOf != -1) {
                try {
                    int parseInt = Integer.parseInt(nextToken.substring(indexOf + 1, nextToken.length()));
                    int indexOf2 = nextToken.indexOf(45);
                    if (indexOf2 != -1) {
                        String substring = nextToken.substring(0, indexOf2);
                        String substring2 = nextToken.substring(indexOf2 + 1, indexOf);
                        int indexOf3 = substring.indexOf(47);
                        int indexOf4 = substring2.indexOf(47);
                        if (indexOf3 != -1) {
                            String substring3 = substring.substring(0, indexOf3);
                            String substring4 = substring.substring(indexOf3 + 1, substring.length());
                            try {
                                int parseInt2 = Integer.parseInt(substring3);
                                try {
                                    int parseInt3 = Integer.parseInt(substring4);
                                    if (indexOf4 != -1) {
                                        String substring5 = substring2.substring(0, indexOf4);
                                        String substring6 = substring2.substring(indexOf4 + 1, substring2.length());
                                        try {
                                            int parseInt4 = Integer.parseInt(substring5);
                                            try {
                                                int parseInt5 = Integer.parseInt(substring6);
                                                if (parseInt2 == -1 || parseInt3 == -1 || parseInt4 == -1 || parseInt5 == -1) {
                                                    throw new ParseException("Unknown error at line " + i + ".", i);
                                                }
                                                addDeviationAsHijrah(parseInt2, parseInt3, parseInt4, parseInt5, parseInt);
                                            } catch (NumberFormatException unused) {
                                                throw new ParseException("End month is not properly set at line " + i + ".", i);
                                            }
                                        } catch (NumberFormatException unused2) {
                                            throw new ParseException("End year is not properly set at line " + i + ".", i);
                                        }
                                    } else {
                                        throw new ParseException("End year/month has incorrect format at line " + i + ".", i);
                                    }
                                } catch (NumberFormatException unused3) {
                                    throw new ParseException("Start month is not properly set at line " + i + ".", i);
                                }
                            } catch (NumberFormatException unused4) {
                                throw new ParseException("Start year is not properly set at line " + i + ".", i);
                            }
                        } else {
                            throw new ParseException("Start year/month has incorrect format at line " + i + ".", i);
                        }
                    } else {
                        throw new ParseException("Start and end year/month has incorrect format at line " + i + ".", i);
                    }
                } catch (NumberFormatException unused5) {
                    throw new ParseException("Offset is not properly set at line " + i + ".", i);
                }
            } else {
                throw new ParseException("Offset has incorrect format at line " + i + ".", i);
            }
        }
    }

    private static InputStream getConfigFileInputStream() throws IOException {
        ZipFile zipFile;
        String property = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigFile");
        if (property == null) {
            property = DEFAULT_CONFIG_FILENAME;
        }
        String property2 = System.getProperty("org.threeten.bp.i18n.HijrahDate.deviationConfigDir");
        if (property2 != null) {
            if (property2.length() != 0 || !property2.endsWith(System.getProperty("file.separator"))) {
                property2 = property2 + System.getProperty("file.separator");
            }
            File file = new File(property2 + FILE_SEP + property);
            if (!file.exists()) {
                return null;
            }
            try {
                return new FileInputStream(file);
            } catch (IOException e) {
                throw e;
            }
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(System.getProperty("java.class.path"), PATH_SEP);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                File file2 = new File(nextToken);
                if (file2.exists()) {
                    if (file2.isDirectory()) {
                        if (new File(nextToken + FILE_SEP + DEFAULT_CONFIG_PATH, property).exists()) {
                            try {
                                return new FileInputStream(nextToken + FILE_SEP + DEFAULT_CONFIG_PATH + FILE_SEP + property);
                            } catch (IOException e2) {
                                throw e2;
                            }
                        }
                    } else {
                        try {
                            zipFile = new ZipFile(file2);
                        } catch (IOException unused) {
                            zipFile = null;
                        }
                        if (zipFile != null) {
                            String str = DEFAULT_CONFIG_PATH + FILE_SEP + property;
                            ZipEntry entry = zipFile.getEntry(str);
                            if (entry == null) {
                                char c = FILE_SEP;
                                if (c == '/') {
                                    str = str.replace('/', '\\');
                                } else if (c == '\\') {
                                    str = str.replace('\\', '/');
                                }
                                entry = zipFile.getEntry(str);
                            }
                            if (entry != null) {
                                try {
                                    return zipFile.getInputStream(entry);
                                } catch (IOException e3) {
                                    throw e3;
                                }
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            return null;
        }
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(get(ChronoField.YEAR));
        dataOutput.writeByte(get(ChronoField.MONTH_OF_YEAR));
        dataOutput.writeByte(get(ChronoField.DAY_OF_MONTH));
    }

    static ChronoLocalDate readExternal(DataInput dataInput) throws IOException {
        return HijrahChronology.INSTANCE.date(dataInput.readInt(), (int) dataInput.readByte(), (int) dataInput.readByte());
    }
}
