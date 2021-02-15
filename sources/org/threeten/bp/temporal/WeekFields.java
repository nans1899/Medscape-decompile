package org.threeten.bp.temporal;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Year;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;

public final class WeekFields implements Serializable {
    private static final ConcurrentMap<String, WeekFields> CACHE = new ConcurrentHashMap(4, 0.75f, 2);
    public static final WeekFields ISO = new WeekFields(DayOfWeek.MONDAY, 4);
    public static final WeekFields SUNDAY_START = of(DayOfWeek.SUNDAY, 1);
    private static final long serialVersionUID = -1177360819670808121L;
    private final transient TemporalField dayOfWeek = ComputedDayOfField.ofDayOfWeekField(this);
    private final DayOfWeek firstDayOfWeek;
    private final int minimalDays;
    private final transient TemporalField weekBasedYear = ComputedDayOfField.ofWeekBasedYearField(this);
    private final transient TemporalField weekOfMonth = ComputedDayOfField.ofWeekOfMonthField(this);
    /* access modifiers changed from: private */
    public final transient TemporalField weekOfWeekBasedYear = ComputedDayOfField.ofWeekOfWeekBasedYearField(this);
    private final transient TemporalField weekOfYear = ComputedDayOfField.ofWeekOfYearField(this);

    public static WeekFields of(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        GregorianCalendar gregorianCalendar = new GregorianCalendar(new Locale(locale.getLanguage(), locale.getCountry()));
        return of(DayOfWeek.SUNDAY.plus((long) (gregorianCalendar.getFirstDayOfWeek() - 1)), gregorianCalendar.getMinimalDaysInFirstWeek());
    }

    public static WeekFields of(DayOfWeek dayOfWeek2, int i) {
        String str = dayOfWeek2.toString() + i;
        WeekFields weekFields = (WeekFields) CACHE.get(str);
        if (weekFields != null) {
            return weekFields;
        }
        CACHE.putIfAbsent(str, new WeekFields(dayOfWeek2, i));
        return (WeekFields) CACHE.get(str);
    }

    private WeekFields(DayOfWeek dayOfWeek2, int i) {
        Jdk8Methods.requireNonNull(dayOfWeek2, "firstDayOfWeek");
        if (i < 1 || i > 7) {
            throw new IllegalArgumentException("Minimal number of days is invalid");
        }
        this.firstDayOfWeek = dayOfWeek2;
        this.minimalDays = i;
    }

    private Object readResolve() throws InvalidObjectException {
        try {
            return of(this.firstDayOfWeek, this.minimalDays);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectException("Invalid WeekFields" + e.getMessage());
        }
    }

    public DayOfWeek getFirstDayOfWeek() {
        return this.firstDayOfWeek;
    }

    public int getMinimalDaysInFirstWeek() {
        return this.minimalDays;
    }

    public TemporalField dayOfWeek() {
        return this.dayOfWeek;
    }

    public TemporalField weekOfMonth() {
        return this.weekOfMonth;
    }

    public TemporalField weekOfYear() {
        return this.weekOfYear;
    }

    public TemporalField weekOfWeekBasedYear() {
        return this.weekOfWeekBasedYear;
    }

    public TemporalField weekBasedYear() {
        return this.weekBasedYear;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WeekFields) || hashCode() != obj.hashCode()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.firstDayOfWeek.ordinal() * 7) + this.minimalDays;
    }

    public String toString() {
        return "WeekFields[" + this.firstDayOfWeek + ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN + this.minimalDays + ']';
    }

    static class ComputedDayOfField implements TemporalField {
        private static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(1, 7);
        private static final ValueRange WEEK_BASED_YEAR_RANGE = ChronoField.YEAR.range();
        private static final ValueRange WEEK_OF_MONTH_RANGE = ValueRange.of(0, 1, 4, 6);
        private static final ValueRange WEEK_OF_WEEK_BASED_YEAR_RANGE = ValueRange.of(1, 52, 53);
        private static final ValueRange WEEK_OF_YEAR_RANGE = ValueRange.of(0, 1, 52, 54);
        private final TemporalUnit baseUnit;
        private final String name;
        private final ValueRange range;
        private final TemporalUnit rangeUnit;
        private final WeekFields weekDef;

        public boolean isDateBased() {
            return true;
        }

        public boolean isTimeBased() {
            return false;
        }

        static ComputedDayOfField ofDayOfWeekField(WeekFields weekFields) {
            return new ComputedDayOfField("DayOfWeek", weekFields, ChronoUnit.DAYS, ChronoUnit.WEEKS, DAY_OF_WEEK_RANGE);
        }

        static ComputedDayOfField ofWeekOfMonthField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfMonth", weekFields, ChronoUnit.WEEKS, ChronoUnit.MONTHS, WEEK_OF_MONTH_RANGE);
        }

        static ComputedDayOfField ofWeekOfYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfYear", weekFields, ChronoUnit.WEEKS, ChronoUnit.YEARS, WEEK_OF_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekOfWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekOfWeekBasedYear", weekFields, ChronoUnit.WEEKS, IsoFields.WEEK_BASED_YEARS, WEEK_OF_WEEK_BASED_YEAR_RANGE);
        }

        static ComputedDayOfField ofWeekBasedYearField(WeekFields weekFields) {
            return new ComputedDayOfField("WeekBasedYear", weekFields, IsoFields.WEEK_BASED_YEARS, ChronoUnit.FOREVER, WEEK_BASED_YEAR_RANGE);
        }

        private ComputedDayOfField(String str, WeekFields weekFields, TemporalUnit temporalUnit, TemporalUnit temporalUnit2, ValueRange valueRange) {
            this.name = str;
            this.weekDef = weekFields;
            this.baseUnit = temporalUnit;
            this.rangeUnit = temporalUnit2;
            this.range = valueRange;
        }

        public long getFrom(TemporalAccessor temporalAccessor) {
            int localizedWBY;
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return (long) floorMod;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                int i = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
                localizedWBY = computeWeek(startOfWeekOffset(i, floorMod), i);
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
                localizedWBY = computeWeek(startOfWeekOffset(i2, floorMod), i2);
            } else if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                localizedWBY = localizedWOWBY(temporalAccessor);
            } else if (this.rangeUnit == ChronoUnit.FOREVER) {
                localizedWBY = localizedWBY(temporalAccessor);
            } else {
                throw new IllegalStateException("unreachable");
            }
            return (long) localizedWBY;
        }

        private int localizedDayOfWeek(TemporalAccessor temporalAccessor, int i) {
            return Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - i, 7) + 1;
        }

        private long localizedWeekOfMonth(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
            return (long) computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private long localizedWeekOfYear(TemporalAccessor temporalAccessor, int i) {
            int i2 = temporalAccessor.get(ChronoField.DAY_OF_YEAR);
            return (long) computeWeek(startOfWeekOffset(i2, i), i2);
        }

        private int localizedWOWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return ((int) localizedWeekOfYear(Chronology.from(temporalAccessor).date(temporalAccessor).minus(1, (TemporalUnit) ChronoUnit.WEEKS), floorMod)) + 1;
            }
            if (localizedWeekOfYear >= 53) {
                int computeWeek = computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek());
                if (localizedWeekOfYear >= ((long) computeWeek)) {
                    return (int) (localizedWeekOfYear - ((long) (computeWeek - 1)));
                }
            }
            return (int) localizedWeekOfYear;
        }

        private int localizedWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            int i = temporalAccessor.get(ChronoField.YEAR);
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return i - 1;
            }
            if (localizedWeekOfYear < 53) {
                return i;
            }
            return localizedWeekOfYear >= ((long) computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), (Year.isLeap((long) i) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek())) ? i + 1 : i;
        }

        private int startOfWeekOffset(int i, int i2) {
            int floorMod = Jdk8Methods.floorMod(i - i2, 7);
            return floorMod + 1 > this.weekDef.getMinimalDaysInFirstWeek() ? 7 - floorMod : -floorMod;
        }

        private int computeWeek(int i, int i2) {
            return ((i + 7) + (i2 - 1)) / 7;
        }

        public <R extends Temporal> R adjustInto(R r, long j) {
            int checkValidIntValue = this.range.checkValidIntValue(j, this);
            int i = r.get(this);
            if (checkValidIntValue == i) {
                return r;
            }
            if (this.rangeUnit != ChronoUnit.FOREVER) {
                return r.plus((long) (checkValidIntValue - i), this.baseUnit);
            }
            int i2 = r.get(this.weekDef.weekOfWeekBasedYear);
            Temporal plus = r.plus((long) (((double) (j - ((long) i))) * 52.1775d), ChronoUnit.WEEKS);
            if (plus.get(this) > checkValidIntValue) {
                return plus.minus((long) plus.get(this.weekDef.weekOfWeekBasedYear), ChronoUnit.WEEKS);
            }
            if (plus.get(this) < checkValidIntValue) {
                plus = plus.plus(2, ChronoUnit.WEEKS);
            }
            R plus2 = plus.plus((long) (i2 - plus.get(this.weekDef.weekOfWeekBasedYear)), ChronoUnit.WEEKS);
            return plus2.get(this) > checkValidIntValue ? plus2.minus(1, ChronoUnit.WEEKS) : plus2;
        }

        public TemporalAccessor resolve(Map<TemporalField, Long> map, TemporalAccessor temporalAccessor, ResolverStyle resolverStyle) {
            long j;
            ChronoLocalDate chronoLocalDate;
            long j2;
            ChronoLocalDate chronoLocalDate2;
            long j3;
            long j4;
            int i;
            int value = this.weekDef.getFirstDayOfWeek().getValue();
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                map.put(ChronoField.DAY_OF_WEEK, Long.valueOf((long) (Jdk8Methods.floorMod((value - 1) + (this.range.checkValidIntValue(map.remove(this).longValue(), this) - 1), 7) + 1)));
                return null;
            } else if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            } else {
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    if (!map.containsKey(this.weekDef.weekOfWeekBasedYear)) {
                        return null;
                    }
                    Chronology from = Chronology.from(temporalAccessor);
                    int floorMod = Jdk8Methods.floorMod(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.get(ChronoField.DAY_OF_WEEK).longValue()) - value, 7) + 1;
                    int checkValidIntValue = range().checkValidIntValue(map.get(this).longValue(), this);
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        chronoLocalDate2 = from.date(checkValidIntValue, 1, this.weekDef.getMinimalDaysInFirstWeek());
                        j4 = map.get(this.weekDef.weekOfWeekBasedYear).longValue();
                        i = localizedDayOfWeek(chronoLocalDate2, value);
                        j3 = localizedWeekOfYear(chronoLocalDate2, i);
                    } else {
                        chronoLocalDate2 = from.date(checkValidIntValue, 1, this.weekDef.getMinimalDaysInFirstWeek());
                        j4 = (long) this.weekDef.weekOfWeekBasedYear.range().checkValidIntValue(map.get(this.weekDef.weekOfWeekBasedYear).longValue(), this.weekDef.weekOfWeekBasedYear);
                        i = localizedDayOfWeek(chronoLocalDate2, value);
                        j3 = localizedWeekOfYear(chronoLocalDate2, i);
                    }
                    ChronoLocalDate plus = chronoLocalDate2.plus(((j4 - j3) * 7) + ((long) (floorMod - i)), (TemporalUnit) ChronoUnit.DAYS);
                    if (resolverStyle != ResolverStyle.STRICT || plus.getLong(this) == map.get(this).longValue()) {
                        map.remove(this);
                        map.remove(this.weekDef.weekOfWeekBasedYear);
                        map.remove(ChronoField.DAY_OF_WEEK);
                        return plus;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different year");
                } else if (!map.containsKey(ChronoField.YEAR)) {
                    return null;
                } else {
                    int floorMod2 = Jdk8Methods.floorMod(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.get(ChronoField.DAY_OF_WEEK).longValue()) - value, 7) + 1;
                    int checkValidIntValue2 = ChronoField.YEAR.checkValidIntValue(map.get(ChronoField.YEAR).longValue());
                    Chronology from2 = Chronology.from(temporalAccessor);
                    if (this.rangeUnit == ChronoUnit.MONTHS) {
                        if (!map.containsKey(ChronoField.MONTH_OF_YEAR)) {
                            return null;
                        }
                        long longValue = map.remove(this).longValue();
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            chronoLocalDate = from2.date(checkValidIntValue2, 1, 1).plus(map.get(ChronoField.MONTH_OF_YEAR).longValue() - 1, (TemporalUnit) ChronoUnit.MONTHS);
                            int localizedDayOfWeek = localizedDayOfWeek(chronoLocalDate, value);
                            j2 = ((longValue - localizedWeekOfMonth(chronoLocalDate, localizedDayOfWeek)) * 7) + ((long) (floorMod2 - localizedDayOfWeek));
                        } else {
                            chronoLocalDate = from2.date(checkValidIntValue2, ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.get(ChronoField.MONTH_OF_YEAR).longValue()), 8);
                            int localizedDayOfWeek2 = localizedDayOfWeek(chronoLocalDate, value);
                            j2 = ((((long) this.range.checkValidIntValue(longValue, this)) - localizedWeekOfMonth(chronoLocalDate, localizedDayOfWeek2)) * 7) + ((long) (floorMod2 - localizedDayOfWeek2));
                        }
                        ChronoLocalDate plus2 = chronoLocalDate.plus(j2, (TemporalUnit) ChronoUnit.DAYS);
                        if (resolverStyle != ResolverStyle.STRICT || plus2.getLong(ChronoField.MONTH_OF_YEAR) == map.get(ChronoField.MONTH_OF_YEAR).longValue()) {
                            map.remove(this);
                            map.remove(ChronoField.YEAR);
                            map.remove(ChronoField.MONTH_OF_YEAR);
                            map.remove(ChronoField.DAY_OF_WEEK);
                            return plus2;
                        }
                        throw new DateTimeException("Strict mode rejected date parsed to a different month");
                    } else if (this.rangeUnit == ChronoUnit.YEARS) {
                        long longValue2 = map.remove(this).longValue();
                        ChronoLocalDate date = from2.date(checkValidIntValue2, 1, 1);
                        if (resolverStyle == ResolverStyle.LENIENT) {
                            int localizedDayOfWeek3 = localizedDayOfWeek(date, value);
                            j = ((longValue2 - localizedWeekOfYear(date, localizedDayOfWeek3)) * 7) + ((long) (floorMod2 - localizedDayOfWeek3));
                        } else {
                            int localizedDayOfWeek4 = localizedDayOfWeek(date, value);
                            j = ((((long) this.range.checkValidIntValue(longValue2, this)) - localizedWeekOfYear(date, localizedDayOfWeek4)) * 7) + ((long) (floorMod2 - localizedDayOfWeek4));
                        }
                        ChronoLocalDate plus3 = date.plus(j, (TemporalUnit) ChronoUnit.DAYS);
                        if (resolverStyle != ResolverStyle.STRICT || plus3.getLong(ChronoField.YEAR) == map.get(ChronoField.YEAR).longValue()) {
                            map.remove(this);
                            map.remove(ChronoField.YEAR);
                            map.remove(ChronoField.DAY_OF_WEEK);
                            return plus3;
                        }
                        throw new DateTimeException("Strict mode rejected date parsed to a different year");
                    } else {
                        throw new IllegalStateException("unreachable");
                    }
                }
            }
        }

        public TemporalUnit getBaseUnit() {
            return this.baseUnit;
        }

        public TemporalUnit getRangeUnit() {
            return this.rangeUnit;
        }

        public ValueRange range() {
            return this.range;
        }

        public boolean isSupportedBy(TemporalAccessor temporalAccessor) {
            if (!temporalAccessor.isSupported(ChronoField.DAY_OF_WEEK)) {
                return false;
            }
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return true;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_MONTH);
            }
            if (this.rangeUnit == ChronoUnit.YEARS) {
                return temporalAccessor.isSupported(ChronoField.DAY_OF_YEAR);
            }
            if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            if (this.rangeUnit == ChronoUnit.FOREVER) {
                return temporalAccessor.isSupported(ChronoField.EPOCH_DAY);
            }
            return false;
        }

        public ValueRange rangeRefinedBy(TemporalAccessor temporalAccessor) {
            ChronoField chronoField;
            if (this.rangeUnit == ChronoUnit.WEEKS) {
                return this.range;
            }
            if (this.rangeUnit == ChronoUnit.MONTHS) {
                chronoField = ChronoField.DAY_OF_MONTH;
            } else if (this.rangeUnit == ChronoUnit.YEARS) {
                chronoField = ChronoField.DAY_OF_YEAR;
            } else if (this.rangeUnit == IsoFields.WEEK_BASED_YEARS) {
                return rangeWOWBY(temporalAccessor);
            } else {
                if (this.rangeUnit == ChronoUnit.FOREVER) {
                    return temporalAccessor.range(ChronoField.YEAR);
                }
                throw new IllegalStateException("unreachable");
            }
            int value = this.weekDef.getFirstDayOfWeek().getValue();
            int startOfWeekOffset = startOfWeekOffset(temporalAccessor.get(chronoField), Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - value, 7) + 1);
            ValueRange range2 = temporalAccessor.range(chronoField);
            return ValueRange.of((long) computeWeek(startOfWeekOffset, (int) range2.getMinimum()), (long) computeWeek(startOfWeekOffset, (int) range2.getMaximum()));
        }

        private ValueRange rangeWOWBY(TemporalAccessor temporalAccessor) {
            int floorMod = Jdk8Methods.floorMod(temporalAccessor.get(ChronoField.DAY_OF_WEEK) - this.weekDef.getFirstDayOfWeek().getValue(), 7) + 1;
            long localizedWeekOfYear = localizedWeekOfYear(temporalAccessor, floorMod);
            if (localizedWeekOfYear == 0) {
                return rangeWOWBY(Chronology.from(temporalAccessor).date(temporalAccessor).minus(2, (TemporalUnit) ChronoUnit.WEEKS));
            }
            int computeWeek = computeWeek(startOfWeekOffset(temporalAccessor.get(ChronoField.DAY_OF_YEAR), floorMod), (Year.isLeap((long) temporalAccessor.get(ChronoField.YEAR)) ? 366 : 365) + this.weekDef.getMinimalDaysInFirstWeek());
            if (localizedWeekOfYear >= ((long) computeWeek)) {
                return rangeWOWBY(Chronology.from(temporalAccessor).date(temporalAccessor).plus(2, (TemporalUnit) ChronoUnit.WEEKS));
            }
            return ValueRange.of(1, (long) (computeWeek - 1));
        }

        public String getDisplayName(Locale locale) {
            Jdk8Methods.requireNonNull(locale, "locale");
            if (this.rangeUnit == ChronoUnit.YEARS) {
                return "Week";
            }
            return toString();
        }

        public String toString() {
            return this.name + "[" + this.weekDef.toString() + "]";
        }
    }
}
