package org.threeten.bp.temporal;

import java.util.Locale;
import java.util.Map;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;

public final class IsoFields {
    public static final TemporalField DAY_OF_QUARTER = Field.DAY_OF_QUARTER;
    public static final TemporalField QUARTER_OF_YEAR = Field.QUARTER_OF_YEAR;
    public static final TemporalUnit QUARTER_YEARS = Unit.QUARTER_YEARS;
    public static final TemporalField WEEK_BASED_YEAR = Field.WEEK_BASED_YEAR;
    public static final TemporalUnit WEEK_BASED_YEARS = Unit.WEEK_BASED_YEARS;
    public static final TemporalField WEEK_OF_WEEK_BASED_YEAR = Field.WEEK_OF_WEEK_BASED_YEAR;

    private IsoFields() {
        throw new AssertionError("Not instantiable");
    }

    private enum Field implements TemporalField {
        DAY_OF_QUARTER {
            public String toString() {
                return "DayOfQuarter";
            }

            public TemporalUnit getBaseUnit() {
                return ChronoUnit.DAYS;
            }

            public TemporalUnit getRangeUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            public ValueRange range() {
                return ValueRange.of(1, 90, 92);
            }

            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR) && temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && temporalAccessor.isSupported(ChronoField.YEAR) && Field.isIso(temporalAccessor);
            }

            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    long j = temporalAccessor.getLong(QUARTER_OF_YEAR);
                    if (j == 1) {
                        return IsoChronology.INSTANCE.isLeapYear(temporalAccessor.getLong(ChronoField.YEAR)) ? ValueRange.of(1, 91) : ValueRange.of(1, 90);
                    } else if (j == 2) {
                        return ValueRange.of(1, 91);
                    } else {
                        if (j == 3 || j == 4) {
                            return ValueRange.of(1, 92);
                        }
                        return range();
                    }
                } else {
                    throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
                }
            }

            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return (long) (temporalAccessor.get(ChronoField.DAY_OF_YEAR) - Field.QUARTER_DAYS[((temporalAccessor.get(ChronoField.MONTH_OF_YEAR) - 1) / 3) + (IsoChronology.INSTANCE.isLeapYear(temporalAccessor.getLong(ChronoField.YEAR)) ? 4 : 0)]);
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: DayOfQuarter");
            }

            public <R extends Temporal> R adjustInto(R r, long j) {
                long from = getFrom(r);
                range().checkValidValue(j, this);
                return r.with(ChronoField.DAY_OF_YEAR, r.getLong(ChronoField.DAY_OF_YEAR) + (j - from));
            }

            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0079, code lost:
                if (r0 == 2) goto L_0x007b;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public org.threeten.bp.temporal.TemporalAccessor resolve(java.util.Map<org.threeten.bp.temporal.TemporalField, java.lang.Long> r11, org.threeten.bp.temporal.TemporalAccessor r12, org.threeten.bp.format.ResolverStyle r13) {
                /*
                    r10 = this;
                    org.threeten.bp.temporal.ChronoField r12 = org.threeten.bp.temporal.ChronoField.YEAR
                    java.lang.Object r12 = r11.get(r12)
                    java.lang.Long r12 = (java.lang.Long) r12
                    org.threeten.bp.temporal.IsoFields$Field r0 = QUARTER_OF_YEAR
                    java.lang.Object r0 = r11.get(r0)
                    java.lang.Long r0 = (java.lang.Long) r0
                    if (r12 == 0) goto L_0x00a8
                    if (r0 != 0) goto L_0x0016
                    goto L_0x00a8
                L_0x0016:
                    org.threeten.bp.temporal.ChronoField r1 = org.threeten.bp.temporal.ChronoField.YEAR
                    long r2 = r12.longValue()
                    int r12 = r1.checkValidIntValue(r2)
                    org.threeten.bp.temporal.IsoFields$Field r1 = DAY_OF_QUARTER
                    java.lang.Object r1 = r11.get(r1)
                    java.lang.Long r1 = (java.lang.Long) r1
                    long r1 = r1.longValue()
                    org.threeten.bp.format.ResolverStyle r3 = org.threeten.bp.format.ResolverStyle.LENIENT
                    r4 = 3
                    r5 = 1
                    r7 = 1
                    if (r13 != r3) goto L_0x0051
                    long r8 = r0.longValue()
                    org.threeten.bp.LocalDate r12 = org.threeten.bp.LocalDate.of((int) r12, (int) r7, (int) r7)
                    long r7 = org.threeten.bp.jdk8.Jdk8Methods.safeSubtract((long) r8, (long) r5)
                    long r3 = org.threeten.bp.jdk8.Jdk8Methods.safeMultiply((long) r7, (int) r4)
                    org.threeten.bp.LocalDate r12 = r12.plusMonths(r3)
                    long r0 = org.threeten.bp.jdk8.Jdk8Methods.safeSubtract((long) r1, (long) r5)
                    org.threeten.bp.LocalDate r12 = r12.plusDays(r0)
                    goto L_0x009a
                L_0x0051:
                    org.threeten.bp.temporal.IsoFields$Field r3 = QUARTER_OF_YEAR
                    org.threeten.bp.temporal.ValueRange r3 = r3.range()
                    long r8 = r0.longValue()
                    org.threeten.bp.temporal.IsoFields$Field r0 = QUARTER_OF_YEAR
                    int r0 = r3.checkValidIntValue(r8, r0)
                    org.threeten.bp.format.ResolverStyle r3 = org.threeten.bp.format.ResolverStyle.STRICT
                    if (r13 != r3) goto L_0x0086
                    r13 = 92
                    r3 = 91
                    if (r0 != r7) goto L_0x0078
                    org.threeten.bp.chrono.IsoChronology r13 = org.threeten.bp.chrono.IsoChronology.INSTANCE
                    long r8 = (long) r12
                    boolean r13 = r13.isLeapYear(r8)
                    if (r13 == 0) goto L_0x0075
                    goto L_0x007b
                L_0x0075:
                    r13 = 90
                    goto L_0x007d
                L_0x0078:
                    r8 = 2
                    if (r0 != r8) goto L_0x007d
                L_0x007b:
                    r13 = 91
                L_0x007d:
                    long r8 = (long) r13
                    org.threeten.bp.temporal.ValueRange r13 = org.threeten.bp.temporal.ValueRange.of(r5, r8)
                    r13.checkValidValue(r1, r10)
                    goto L_0x008d
                L_0x0086:
                    org.threeten.bp.temporal.ValueRange r13 = r10.range()
                    r13.checkValidValue(r1, r10)
                L_0x008d:
                    int r0 = r0 - r7
                    int r0 = r0 * 3
                    int r0 = r0 + r7
                    org.threeten.bp.LocalDate r12 = org.threeten.bp.LocalDate.of((int) r12, (int) r0, (int) r7)
                    long r1 = r1 - r5
                    org.threeten.bp.LocalDate r12 = r12.plusDays(r1)
                L_0x009a:
                    r11.remove(r10)
                    org.threeten.bp.temporal.ChronoField r13 = org.threeten.bp.temporal.ChronoField.YEAR
                    r11.remove(r13)
                    org.threeten.bp.temporal.IsoFields$Field r13 = QUARTER_OF_YEAR
                    r11.remove(r13)
                    return r12
                L_0x00a8:
                    r11 = 0
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.temporal.IsoFields.Field.AnonymousClass1.resolve(java.util.Map, org.threeten.bp.temporal.TemporalAccessor, org.threeten.bp.format.ResolverStyle):org.threeten.bp.temporal.TemporalAccessor");
            }
        },
        QUARTER_OF_YEAR {
            public String toString() {
                return "QuarterOfYear";
            }

            public TemporalUnit getBaseUnit() {
                return IsoFields.QUARTER_YEARS;
            }

            public TemporalUnit getRangeUnit() {
                return ChronoUnit.YEARS;
            }

            public ValueRange range() {
                return ValueRange.of(1, 4);
            }

            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.MONTH_OF_YEAR) && Field.isIso(temporalAccessor);
            }

            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                return range();
            }

            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return (temporalAccessor.getLong(ChronoField.MONTH_OF_YEAR) + 2) / 3;
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: QuarterOfYear");
            }

            public <R extends Temporal> R adjustInto(R r, long j) {
                long from = getFrom(r);
                range().checkValidValue(j, this);
                return r.with(ChronoField.MONTH_OF_YEAR, r.getLong(ChronoField.MONTH_OF_YEAR) + ((j - from) * 3));
            }
        },
        WEEK_OF_WEEK_BASED_YEAR {
            public String toString() {
                return "WeekOfWeekBasedYear";
            }

            public TemporalUnit getBaseUnit() {
                return ChronoUnit.WEEKS;
            }

            public TemporalUnit getRangeUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            public String getDisplayName(Locale locale) {
                Jdk8Methods.requireNonNull(locale, "locale");
                return "Week";
            }

            public ValueRange range() {
                return ValueRange.of(1, 52, 53);
            }

            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporalAccessor);
            }

            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return Field.getWeekRange(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return (long) Field.getWeek(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekOfWeekBasedYear");
            }

            public <R extends Temporal> R adjustInto(R r, long j) {
                range().checkValidValue(j, this);
                return r.plus(Jdk8Methods.safeSubtract(j, getFrom(r)), ChronoUnit.WEEKS);
            }

            public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
                LocalDate localDate;
                Map<TemporalField, Long> map2 = map;
                ResolverStyle resolverStyle2 = resolverStyle;
                Long l = map2.get(WEEK_BASED_YEAR);
                Long l2 = map2.get(ChronoField.DAY_OF_WEEK);
                if (l == null || l2 == null) {
                    return null;
                }
                int checkValidIntValue = WEEK_BASED_YEAR.range().checkValidIntValue(l.longValue(), WEEK_BASED_YEAR);
                long longValue = map2.get(WEEK_OF_WEEK_BASED_YEAR).longValue();
                if (resolverStyle2 == ResolverStyle.LENIENT) {
                    long longValue2 = l2.longValue();
                    long j = 0;
                    if (longValue2 > 7) {
                        long j2 = longValue2 - 1;
                        j = j2 / 7;
                        longValue2 = (j2 % 7) + 1;
                    } else if (longValue2 < 1) {
                        j = (longValue2 / 7) - 1;
                        longValue2 = (longValue2 % 7) + 7;
                    }
                    localDate = LocalDate.of(checkValidIntValue, 1, 4).plusWeeks(longValue - 1).plusWeeks(j).with((TemporalField) ChronoField.DAY_OF_WEEK, longValue2);
                } else {
                    int checkValidIntValue2 = ChronoField.DAY_OF_WEEK.checkValidIntValue(l2.longValue());
                    if (resolverStyle2 == ResolverStyle.STRICT) {
                        Field.getWeekRange(LocalDate.of(checkValidIntValue, 1, 4)).checkValidValue(longValue, this);
                    } else {
                        range().checkValidValue(longValue, this);
                    }
                    localDate = LocalDate.of(checkValidIntValue, 1, 4).plusWeeks(longValue - 1).with((TemporalField) ChronoField.DAY_OF_WEEK, (long) checkValidIntValue2);
                }
                map2.remove(this);
                map2.remove(WEEK_BASED_YEAR);
                map2.remove(ChronoField.DAY_OF_WEEK);
                return localDate;
            }
        },
        WEEK_BASED_YEAR {
            public String toString() {
                return "WeekBasedYear";
            }

            public TemporalUnit getBaseUnit() {
                return IsoFields.WEEK_BASED_YEARS;
            }

            public TemporalUnit getRangeUnit() {
                return ChronoUnit.FOREVER;
            }

            public ValueRange range() {
                return ChronoField.YEAR.range();
            }

            public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY) && Field.isIso(temporalAccessor);
            }

            public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
                return ChronoField.YEAR.range();
            }

            public long getFrom(TemporalAccessor temporalAccessor) {
                if (temporalAccessor.isSupported(this)) {
                    return (long) Field.getWeekBasedYear(LocalDate.from(temporalAccessor));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }

            public <R extends Temporal> R adjustInto(R r, long j) {
                if (isSupportedBy(r)) {
                    int checkValidIntValue = range().checkValidIntValue(j, WEEK_BASED_YEAR);
                    LocalDate from = LocalDate.from(r);
                    int i = from.get(ChronoField.DAY_OF_WEEK);
                    int access$400 = Field.getWeek(from);
                    if (access$400 == 53 && Field.getWeekRange(checkValidIntValue) == 52) {
                        access$400 = 52;
                    }
                    LocalDate of = LocalDate.of(checkValidIntValue, 1, 4);
                    return r.with(of.plusDays((long) ((i - of.get(ChronoField.DAY_OF_WEEK)) + ((access$400 - 1) * 7))));
                }
                throw new UnsupportedTemporalTypeException("Unsupported field: WeekBasedYear");
            }
        };
        
        /* access modifiers changed from: private */
        public static final int[] QUARTER_DAYS = null;

        public boolean isDateBased() {
            return true;
        }

        public boolean isTimeBased() {
            return false;
        }

        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            return null;
        }

        static {
            QUARTER_DAYS = new int[]{0, 90, 181, 273, 0, 91, 182, 274};
        }

        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            return toString();
        }

        /* access modifiers changed from: private */
        public static boolean isIso(TemporalAccessor temporalAccessor) {
            return Chronology.from(temporalAccessor).equals(IsoChronology.INSTANCE);
        }

        /* access modifiers changed from: private */
        public static ValueRange getWeekRange(LocalDate localDate) {
            return ValueRange.of(1, (long) getWeekRange(getWeekBasedYear(localDate)));
        }

        /* access modifiers changed from: private */
        public static int getWeekRange(int i) {
            LocalDate of = LocalDate.of(i, 1, 1);
            if (of.getDayOfWeek() != DayOfWeek.THURSDAY) {
                return (of.getDayOfWeek() != DayOfWeek.WEDNESDAY || !of.isLeapYear()) ? 52 : 53;
            }
            return 53;
        }

        /* access modifiers changed from: private */
        public static int getWeek(LocalDate localDate) {
            int ordinal = localDate.getDayOfWeek().ordinal();
            int dayOfYear = localDate.getDayOfYear() - 1;
            int i = (3 - ordinal) + dayOfYear;
            int i2 = (i - ((i / 7) * 7)) - 3;
            if (i2 < -3) {
                i2 += 7;
            }
            if (dayOfYear < i2) {
                return (int) getWeekRange(localDate.withDayOfYear(180).minusYears(1)).getMaximum();
            }
            int i3 = ((dayOfYear - i2) / 7) + 1;
            if (i3 == 53) {
                if (!(i2 == -3 || (i2 == -2 && localDate.isLeapYear()))) {
                    return 1;
                }
            }
            return i3;
        }

        /* access modifiers changed from: private */
        public static int getWeekBasedYear(LocalDate localDate) {
            int year = localDate.getYear();
            int dayOfYear = localDate.getDayOfYear();
            if (dayOfYear <= 3) {
                if (dayOfYear - localDate.getDayOfWeek().ordinal() < -2) {
                    return year - 1;
                }
                return year;
            } else if (dayOfYear < 363) {
                return year;
            } else {
                return ((dayOfYear - 363) - (localDate.isLeapYear() ? 1 : 0)) - localDate.getDayOfWeek().ordinal() >= 0 ? year + 1 : year;
            }
        }
    }

    private enum Unit implements TemporalUnit {
        WEEK_BASED_YEARS("WeekBasedYears", Duration.ofSeconds(31556952)),
        QUARTER_YEARS("QuarterYears", Duration.ofSeconds(7889238));
        
        private final Duration duration;
        private final String name;

        public boolean isDateBased() {
            return true;
        }

        public boolean isDurationEstimated() {
            return true;
        }

        public boolean isTimeBased() {
            return false;
        }

        private Unit(String str, Duration duration2) {
            this.name = str;
            this.duration = duration2;
        }

        public Duration getDuration() {
            return this.duration;
        }

        public boolean isSupportedBy(Temporal temporal) {
            return temporal.isSupported(ChronoField.EPOCH_DAY);
        }

        public <R extends Temporal> R addTo(R r, long j) {
            int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return r.with(IsoFields.WEEK_BASED_YEAR, Jdk8Methods.safeAdd((long) r.get(IsoFields.WEEK_BASED_YEAR), j));
            } else if (i == 2) {
                return r.plus(j / 256, ChronoUnit.YEARS).plus((j % 256) * 3, ChronoUnit.MONTHS);
            } else {
                throw new IllegalStateException("Unreachable");
            }
        }

        public long between(Temporal temporal, Temporal temporal2) {
            int i = AnonymousClass1.$SwitchMap$org$threeten$bp$temporal$IsoFields$Unit[ordinal()];
            if (i == 1) {
                return Jdk8Methods.safeSubtract(temporal2.getLong(IsoFields.WEEK_BASED_YEAR), temporal.getLong(IsoFields.WEEK_BASED_YEAR));
            }
            if (i == 2) {
                return temporal.until(temporal2, ChronoUnit.MONTHS) / 3;
            }
            throw new IllegalStateException("Unreachable");
        }

        public String toString() {
            return this.name;
        }
    }

    /* renamed from: org.threeten.bp.temporal.IsoFields$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.threeten.bp.temporal.IsoFields$Unit[] r0 = org.threeten.bp.temporal.IsoFields.Unit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit = r0
                org.threeten.bp.temporal.IsoFields$Unit r1 = org.threeten.bp.temporal.IsoFields.Unit.WEEK_BASED_YEARS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$threeten$bp$temporal$IsoFields$Unit     // Catch:{ NoSuchFieldError -> 0x001d }
                org.threeten.bp.temporal.IsoFields$Unit r1 = org.threeten.bp.temporal.IsoFields.Unit.QUARTER_YEARS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.temporal.IsoFields.AnonymousClass1.<clinit>():void");
        }
    }
}
