package org.threeten.bp.chrono;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.Comparator;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
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

public abstract class ChronoLocalDateTime<D extends ChronoLocalDate> extends DefaultInterfaceTemporal implements Temporal, TemporalAdjuster, Comparable<ChronoLocalDateTime<?>> {
    private static final Comparator<ChronoLocalDateTime<?>> DATE_TIME_COMPARATOR = new Comparator<ChronoLocalDateTime<?>>() {
        /* JADX WARNING: type inference failed for: r5v0, types: [org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime<?>] */
        /* JADX WARNING: type inference failed for: r6v0, types: [org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime<?>] */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int compare(org.threeten.bp.chrono.ChronoLocalDateTime<?> r5, org.threeten.bp.chrono.ChronoLocalDateTime<?> r6) {
            /*
                r4 = this;
                org.threeten.bp.chrono.ChronoLocalDate r0 = r5.toLocalDate()
                long r0 = r0.toEpochDay()
                org.threeten.bp.chrono.ChronoLocalDate r2 = r6.toLocalDate()
                long r2 = r2.toEpochDay()
                int r0 = org.threeten.bp.jdk8.Jdk8Methods.compareLongs(r0, r2)
                if (r0 != 0) goto L_0x002a
                org.threeten.bp.LocalTime r5 = r5.toLocalTime()
                long r0 = r5.toNanoOfDay()
                org.threeten.bp.LocalTime r5 = r6.toLocalTime()
                long r5 = r5.toNanoOfDay()
                int r0 = org.threeten.bp.jdk8.Jdk8Methods.compareLongs(r0, r5)
            L_0x002a:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.ChronoLocalDateTime.AnonymousClass1.compare(org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime):int");
        }
    };

    public abstract ChronoZonedDateTime<D> atZone(ZoneId zoneId);

    public abstract ChronoLocalDateTime<D> plus(long j, TemporalUnit temporalUnit);

    public abstract D toLocalDate();

    public abstract LocalTime toLocalTime();

    public abstract ChronoLocalDateTime<D> with(TemporalField temporalField, long j);

    public static Comparator<ChronoLocalDateTime<?>> timeLineOrder() {
        return DATE_TIME_COMPARATOR;
    }

    public static ChronoLocalDateTime<?> from(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        if (temporalAccessor instanceof ChronoLocalDateTime) {
            return (ChronoLocalDateTime) temporalAccessor;
        }
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology != null) {
            return chronology.localDateTime(temporalAccessor);
        }
        throw new DateTimeException("No Chronology found to create ChronoLocalDateTime: " + temporalAccessor.getClass());
    }

    public Chronology getChronology() {
        return toLocalDate().getChronology();
    }

    public ChronoLocalDateTime<D> with(TemporalAdjuster temporalAdjuster) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.with(temporalAdjuster));
    }

    public ChronoLocalDateTime<D> plus(TemporalAmount temporalAmount) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.plus(temporalAmount));
    }

    public ChronoLocalDateTime<D> minus(TemporalAmount temporalAmount) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.minus(temporalAmount));
    }

    public ChronoLocalDateTime<D> minus(long j, TemporalUnit temporalUnit) {
        return toLocalDate().getChronology().ensureChronoLocalDateTime(super.minus(j, temporalUnit));
    }

    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.chronology()) {
            return getChronology();
        }
        if (temporalQuery == TemporalQueries.precision()) {
            return ChronoUnit.NANOS;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            return LocalDate.ofEpochDay(toLocalDate().toEpochDay());
        }
        if (temporalQuery == TemporalQueries.localTime()) {
            return toLocalTime();
        }
        if (temporalQuery == TemporalQueries.zone() || temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.offset()) {
            return null;
        }
        return super.query(temporalQuery);
    }

    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.EPOCH_DAY, toLocalDate().toEpochDay()).with(ChronoField.NANO_OF_DAY, toLocalTime().toNanoOfDay());
    }

    public String format(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        return dateTimeFormatter.format(this);
    }

    public Instant toInstant(ZoneOffset zoneOffset) {
        return Instant.ofEpochSecond(toEpochSecond(zoneOffset), (long) toLocalTime().getNano());
    }

    public long toEpochSecond(ZoneOffset zoneOffset) {
        Jdk8Methods.requireNonNull(zoneOffset, "offset");
        return ((toLocalDate().toEpochDay() * 86400) + ((long) toLocalTime().toSecondOfDay())) - ((long) zoneOffset.getTotalSeconds());
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compareTo(org.threeten.bp.chrono.ChronoLocalDateTime<?> r3) {
        /*
            r2 = this;
            org.threeten.bp.chrono.ChronoLocalDate r0 = r2.toLocalDate()
            org.threeten.bp.chrono.ChronoLocalDate r1 = r3.toLocalDate()
            int r0 = r0.compareTo((org.threeten.bp.chrono.ChronoLocalDate) r1)
            if (r0 != 0) goto L_0x0028
            org.threeten.bp.LocalTime r0 = r2.toLocalTime()
            org.threeten.bp.LocalTime r1 = r3.toLocalTime()
            int r0 = r0.compareTo((org.threeten.bp.LocalTime) r1)
            if (r0 != 0) goto L_0x0028
            org.threeten.bp.chrono.Chronology r0 = r2.getChronology()
            org.threeten.bp.chrono.Chronology r3 = r3.getChronology()
            int r0 = r0.compareTo((org.threeten.bp.chrono.Chronology) r3)
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.ChronoLocalDateTime.compareTo(org.threeten.bp.chrono.ChronoLocalDateTime):int");
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isAfter(org.threeten.bp.chrono.ChronoLocalDateTime<?> r6) {
        /*
            r5 = this;
            org.threeten.bp.chrono.ChronoLocalDate r0 = r5.toLocalDate()
            long r0 = r0.toEpochDay()
            org.threeten.bp.chrono.ChronoLocalDate r2 = r6.toLocalDate()
            long r2 = r2.toEpochDay()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 > 0) goto L_0x002d
            if (r4 != 0) goto L_0x002b
            org.threeten.bp.LocalTime r0 = r5.toLocalTime()
            long r0 = r0.toNanoOfDay()
            org.threeten.bp.LocalTime r6 = r6.toLocalTime()
            long r2 = r6.toNanoOfDay()
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            r6 = 0
            goto L_0x002e
        L_0x002d:
            r6 = 1
        L_0x002e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.ChronoLocalDateTime.isAfter(org.threeten.bp.chrono.ChronoLocalDateTime):boolean");
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isBefore(org.threeten.bp.chrono.ChronoLocalDateTime<?> r6) {
        /*
            r5 = this;
            org.threeten.bp.chrono.ChronoLocalDate r0 = r5.toLocalDate()
            long r0 = r0.toEpochDay()
            org.threeten.bp.chrono.ChronoLocalDate r2 = r6.toLocalDate()
            long r2 = r2.toEpochDay()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x002d
            if (r4 != 0) goto L_0x002b
            org.threeten.bp.LocalTime r0 = r5.toLocalTime()
            long r0 = r0.toNanoOfDay()
            org.threeten.bp.LocalTime r6 = r6.toLocalTime()
            long r2 = r6.toNanoOfDay()
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            r6 = 0
            goto L_0x002e
        L_0x002d:
            r6 = 1
        L_0x002e:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.ChronoLocalDateTime.isBefore(org.threeten.bp.chrono.ChronoLocalDateTime):boolean");
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [org.threeten.bp.chrono.ChronoLocalDateTime, org.threeten.bp.chrono.ChronoLocalDateTime<?>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isEqual(org.threeten.bp.chrono.ChronoLocalDateTime<?> r6) {
        /*
            r5 = this;
            org.threeten.bp.LocalTime r0 = r5.toLocalTime()
            long r0 = r0.toNanoOfDay()
            org.threeten.bp.LocalTime r2 = r6.toLocalTime()
            long r2 = r2.toNanoOfDay()
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x002a
            org.threeten.bp.chrono.ChronoLocalDate r0 = r5.toLocalDate()
            long r0 = r0.toEpochDay()
            org.threeten.bp.chrono.ChronoLocalDate r6 = r6.toLocalDate()
            long r2 = r6.toEpochDay()
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x002a
            r6 = 1
            goto L_0x002b
        L_0x002a:
            r6 = 0
        L_0x002b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.chrono.ChronoLocalDateTime.isEqual(org.threeten.bp.chrono.ChronoLocalDateTime):boolean");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChronoLocalDateTime) || compareTo((ChronoLocalDateTime<?>) (ChronoLocalDateTime) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return toLocalDate().hashCode() ^ toLocalTime().hashCode();
    }

    public String toString() {
        return toLocalDate().toString() + ASCIIPropertyListParser.DATE_APPLE_DATE_TIME_DELIMITER + toLocalTime().toString();
    }
}
