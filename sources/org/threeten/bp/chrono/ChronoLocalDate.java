package org.threeten.bp.chrono;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Comparator;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.jdk8.DefaultInterfaceTemporal;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalAmount;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.TemporalUnit;

public abstract class ChronoLocalDate extends DefaultInterfaceTemporal implements Temporal, TemporalAdjuster, Comparable<ChronoLocalDate> {
    private static final Comparator<ChronoLocalDate> DATE_COMPARATOR = new Comparator<ChronoLocalDate>() {
        public int compare(ChronoLocalDate chronoLocalDate, ChronoLocalDate chronoLocalDate2) {
            return Jdk8Methods.compareLongs(chronoLocalDate.toEpochDay(), chronoLocalDate2.toEpochDay());
        }
    };

    public abstract Chronology getChronology();

    public abstract int lengthOfMonth();

    public abstract ChronoLocalDate plus(long j, TemporalUnit temporalUnit);

    public abstract ChronoPeriod until(ChronoLocalDate chronoLocalDate);

    public abstract ChronoLocalDate with(TemporalField temporalField, long j);

    public static Comparator<ChronoLocalDate> timeLineOrder() {
        return DATE_COMPARATOR;
    }

    public static ChronoLocalDate from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        if (temporalAccessor instanceof ChronoLocalDate) {
            return (ChronoLocalDate) temporalAccessor;
        }
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null) {
            return chronology.date(temporalAccessor);
        }
        throw new DateTimeException("No Chronology found to create ChronoLocalDate: " + temporalAccessor.getClass());
    }

    public Era getEra() {
        return getChronology().eraOf(get(ChronoField.ERA));
    }

    public boolean isLeapYear() {
        return getChronology().isLeapYear(getLong(ChronoField.YEAR));
    }

    public int lengthOfYear() {
        return isLeapYear() ? 366 : 365;
    }

    public boolean isSupported(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            return temporalField.isDateBased();
        }
        return temporalField != null && temporalField.isSupportedBy(this);
    }

    public boolean isSupported(TemporalUnit temporalUnit) {
        if (temporalUnit instanceof ChronoUnit) {
            return temporalUnit.isDateBased();
        }
        return temporalUnit != null && temporalUnit.isSupportedBy(this);
    }

    public ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return getChronology().ensureChronoLocalDate(super.with(temporalAdjuster));
    }

    public ChronoLocalDate plus(TemporalAmount temporalAmount) {
        return getChronology().ensureChronoLocalDate(super.plus(temporalAmount));
    }

    public ChronoLocalDate minus(TemporalAmount temporalAmount) {
        return getChronology().ensureChronoLocalDate(super.minus(temporalAmount));
    }

    public ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        return getChronology().ensureChronoLocalDate(super.minus(j, temporalUnit));
    }

    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.chronology()) {
            return getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return ChronoUnit.DAYS;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            return LocalDate.ofEpochDay(toEpochDay());
        }
        if (temporalQuery == TemporalQueries.localTime() || temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.offset()) {
            return null;
        }
        return super.query(temporalQuery);
    }

    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toEpochDay());
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    public ChronoLocalDateTime<?> atTime(LocalTime localTime) {
        return ChronoLocalDateTimeImpl.of(this, localTime);
    }

    public long toEpochDay() {
        return getLong(ChronoField.EPOCH_DAY);
    }

    public int compareTo(ChronoLocalDate chronoLocalDate) {
        int compareLongs = Jdk8Methods.compareLongs(toEpochDay(), chronoLocalDate.toEpochDay());
        return compareLongs == 0 ? getChronology().compareTo(chronoLocalDate.getChronology()) : compareLongs;
    }

    public boolean isAfter(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() > chronoLocalDate.toEpochDay();
    }

    public boolean isBefore(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() < chronoLocalDate.toEpochDay();
    }

    public boolean isEqual(ChronoLocalDate chronoLocalDate) {
        return toEpochDay() == chronoLocalDate.toEpochDay();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoLocalDate) || compareTo((ChronoLocalDate) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long epochDay = toEpochDay();
        return getChronology().hashCode() ^ ((int) (epochDay ^ (epochDay >>> 32)));
    }

    public String toString() {
        long j = getLong(ChronoField.YEAR_OF_ERA);
        long j2 = getLong(ChronoField.MONTH_OF_YEAR);
        long j3 = getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(getChronology().toString());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(getEra());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(j);
        String str = "-0";
        sb.append(j2 < 10 ? str : "-");
        sb.append(j2);
        if (j3 >= 10) {
            str = "-";
        }
        sb.append(str);
        sb.append(j3);
        return sb.toString();
    }
}
