package org.threeten.bp.chrono;

import androidx.exifinterface.media.ExifInterface;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.ValueRange;

public final class IsoChronology extends Chronology implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    public String getCalendarType() {
        return "iso8601";
    }

    public String getId() {
        return ExifInterface.TAG_RW2_ISO;
    }

    private IsoChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public LocalDate date(Era era, int i, int i2, int i3) {
        return date(prolepticYear(era, i), i2, i3);
    }

    public LocalDate date(int i, int i2, int i3) {
        return LocalDate.of(i, i2, i3);
    }

    public LocalDate dateYearDay(Era era, int i, int i2) {
        return dateYearDay(prolepticYear(era, i), i2);
    }

    public LocalDate dateYearDay(int i, int i2) {
        return LocalDate.ofYearDay(i, i2);
    }

    public LocalDate dateEpochDay(long j) {
        return LocalDate.ofEpochDay(j);
    }

    public LocalDate date(TemporalAccessor temporalAccessor) {
        return LocalDate.from(temporalAccessor);
    }

    public LocalDateTime localDateTime(TemporalAccessor temporalAccessor) {
        return LocalDateTime.from(temporalAccessor);
    }

    public ZonedDateTime zonedDateTime(TemporalAccessor temporalAccessor) {
        return ZonedDateTime.from(temporalAccessor);
    }

    public ZonedDateTime zonedDateTime(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(instant, zoneId);
    }

    public LocalDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    public LocalDate dateNow(ZoneId zoneId) {
        return dateNow(Clock.system(zoneId));
    }

    public LocalDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    public boolean isLeapYear(long j) {
        return (3 & j) == 0 && (j % 100 != 0 || j % 400 == 0);
    }

    public int prolepticYear(Era era, int i) {
        if (era instanceof IsoEra) {
            return era == IsoEra.CE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    public IsoEra eraOf(int i) {
        return IsoEra.of(i);
    }

    public List<Era> eras() {
        return Arrays.asList(IsoEra.values());
    }

    public ValueRange range(ChronoField chronoField) {
        return chronoField.range();
    }

    public LocalDate resolveDate(Map<TemporalField, Long> map, ResolverStyle resolverStyle) {
        if (map.containsKey(ChronoField.EPOCH_DAY)) {
            return LocalDate.ofEpochDay(map.remove(ChronoField.EPOCH_DAY).longValue());
        }
        Long remove = map.remove(ChronoField.PROLEPTIC_MONTH);
        if (remove != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.PROLEPTIC_MONTH.checkValidValue(remove.longValue());
            }
            updateResolveMap(map, ChronoField.MONTH_OF_YEAR, (long) (Jdk8Methods.floorMod(remove.longValue(), 12) + 1));
            updateResolveMap(map, ChronoField.YEAR, Jdk8Methods.floorDiv(remove.longValue(), 12));
        }
        Long remove2 = map.remove(ChronoField.YEAR_OF_ERA);
        if (remove2 != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.YEAR_OF_ERA.checkValidValue(remove2.longValue());
            }
            Long remove3 = map.remove(ChronoField.ERA);
            if (remove3 == null) {
                Long l = map.get(ChronoField.YEAR);
                if (resolverStyle != ResolverStyle.STRICT) {
                    updateResolveMap(map, ChronoField.YEAR, (l == null || l.longValue() > 0) ? remove2.longValue() : Jdk8Methods.safeSubtract(1, remove2.longValue()));
                } else if (l != null) {
                    updateResolveMap(map, ChronoField.YEAR, l.longValue() > 0 ? remove2.longValue() : Jdk8Methods.safeSubtract(1, remove2.longValue()));
                } else {
                    map.put(ChronoField.YEAR_OF_ERA, remove2);
                }
            } else if (remove3.longValue() == 1) {
                updateResolveMap(map, ChronoField.YEAR, remove2.longValue());
            } else if (remove3.longValue() == 0) {
                updateResolveMap(map, ChronoField.YEAR, Jdk8Methods.safeSubtract(1, remove2.longValue()));
            } else {
                throw new DateTimeException("Invalid value for era: " + remove3);
            }
        } else if (map.containsKey(ChronoField.ERA)) {
            ChronoField.ERA.checkValidValue(map.get(ChronoField.ERA).longValue());
        }
        if (!map.containsKey(ChronoField.YEAR)) {
            return null;
        }
        if (map.containsKey(ChronoField.MONTH_OF_YEAR)) {
            if (map.containsKey(ChronoField.DAY_OF_MONTH)) {
                int checkValidIntValue = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                int safeToInt = Jdk8Methods.safeToInt(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                int safeToInt2 = Jdk8Methods.safeToInt(map.remove(ChronoField.DAY_OF_MONTH).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(checkValidIntValue, 1, 1).plusMonths((long) Jdk8Methods.safeSubtract(safeToInt, 1)).plusDays((long) Jdk8Methods.safeSubtract(safeToInt2, 1));
                } else if (resolverStyle != ResolverStyle.SMART) {
                    return LocalDate.of(checkValidIntValue, safeToInt, safeToInt2);
                } else {
                    ChronoField.DAY_OF_MONTH.checkValidValue((long) safeToInt2);
                    if (safeToInt == 4 || safeToInt == 6 || safeToInt == 9 || safeToInt == 11) {
                        safeToInt2 = Math.min(safeToInt2, 30);
                    } else if (safeToInt == 2) {
                        safeToInt2 = Math.min(safeToInt2, Month.FEBRUARY.length(Year.isLeap((long) checkValidIntValue)));
                    }
                    return LocalDate.of(checkValidIntValue, safeToInt, safeToInt2);
                }
            } else if (map.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    int checkValidIntValue2 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return LocalDate.of(checkValidIntValue2, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1)).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1));
                    }
                    int checkValidIntValue3 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    LocalDate plusDays = LocalDate.of(checkValidIntValue2, checkValidIntValue3, 1).plusDays((long) (((ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue()) - 1)));
                    if (resolverStyle != ResolverStyle.STRICT || plusDays.get(ChronoField.MONTH_OF_YEAR) == checkValidIntValue3) {
                        return plusDays;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                } else if (map.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int checkValidIntValue4 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        return LocalDate.of(checkValidIntValue4, 1, 1).plusMonths(Jdk8Methods.safeSubtract(map.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1)).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1));
                    }
                    int checkValidIntValue5 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(map.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    LocalDate with = LocalDate.of(checkValidIntValue4, checkValidIntValue5, 1).plusWeeks((long) (ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1)).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                    if (resolverStyle != ResolverStyle.STRICT || with.get(ChronoField.MONTH_OF_YEAR) == checkValidIntValue5) {
                        return with;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        if (map.containsKey(ChronoField.DAY_OF_YEAR)) {
            int checkValidIntValue6 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
            if (resolverStyle != ResolverStyle.LENIENT) {
                return LocalDate.ofYearDay(checkValidIntValue6, ChronoField.DAY_OF_YEAR.checkValidIntValue(map.remove(ChronoField.DAY_OF_YEAR).longValue()));
            }
            return LocalDate.ofYearDay(checkValidIntValue6, 1).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_YEAR).longValue(), 1));
        } else if (!map.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        } else {
            if (map.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                int checkValidIntValue7 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(checkValidIntValue7, 1, 1).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1));
                }
                LocalDate plusDays2 = LocalDate.of(checkValidIntValue7, 1, 1).plusDays((long) (((ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue()) - 1)));
                if (resolverStyle != ResolverStyle.STRICT || plusDays2.get(ChronoField.YEAR) == checkValidIntValue7) {
                    return plusDays2;
                }
                throw new DateTimeException("Strict mode rejected date parsed to a different year");
            } else if (!map.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            } else {
                int checkValidIntValue8 = ChronoField.YEAR.checkValidIntValue(map.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return LocalDate.of(checkValidIntValue8, 1, 1).plusWeeks(Jdk8Methods.safeSubtract(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1)).plusDays(Jdk8Methods.safeSubtract(map.remove(ChronoField.DAY_OF_WEEK).longValue(), 1));
                }
                LocalDate with2 = LocalDate.of(checkValidIntValue8, 1, 1).plusWeeks((long) (ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(map.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1)).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(map.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                if (resolverStyle != ResolverStyle.STRICT || with2.get(ChronoField.YEAR) == checkValidIntValue8) {
                    return with2;
                }
                throw new DateTimeException("Strict mode rejected date parsed to a different month");
            }
        }
    }
}
