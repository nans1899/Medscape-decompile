package org.threeten.bp.temporal;

import org.threeten.bp.Duration;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.ChronoLocalDateTime;
import org.threeten.bp.chrono.ChronoZonedDateTime;

public enum ChronoUnit implements TemporalUnit {
    NANOS("Nanos", Duration.ofNanos(1)),
    MICROS("Micros", Duration.ofNanos(1000)),
    MILLIS("Millis", Duration.ofNanos(1000000)),
    SECONDS("Seconds", Duration.ofSeconds(1)),
    MINUTES("Minutes", Duration.ofSeconds(60)),
    HOURS("Hours", Duration.ofSeconds(3600)),
    HALF_DAYS("HalfDays", Duration.ofSeconds(43200)),
    DAYS("Days", Duration.ofSeconds(86400)),
    WEEKS("Weeks", Duration.ofSeconds(604800)),
    MONTHS("Months", Duration.ofSeconds(2629746)),
    YEARS("Years", Duration.ofSeconds(31556952)),
    DECADES("Decades", Duration.ofSeconds(315569520)),
    CENTURIES("Centuries", Duration.ofSeconds(3155695200L)),
    MILLENNIA("Millennia", Duration.ofSeconds(31556952000L)),
    ERAS("Eras", Duration.ofSeconds(31556952000000000L)),
    FOREVER("Forever", Duration.ofSeconds(Long.MAX_VALUE, 999999999));
    
    private final Duration duration;
    private final String name;

    private ChronoUnit(String str, Duration duration2) {
        this.name = str;
        this.duration = duration2;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public boolean isDurationEstimated() {
        return isDateBased() || this == FOREVER;
    }

    public boolean isDateBased() {
        return compareTo(DAYS) >= 0 && this != FOREVER;
    }

    public boolean isTimeBased() {
        return compareTo(DAYS) < 0;
    }

    public boolean isSupportedBy(Temporal temporal) {
        if (this == FOREVER) {
            return false;
        }
        if (temporal instanceof ChronoLocalDate) {
            return isDateBased();
        }
        if ((temporal instanceof ChronoLocalDateTime) || (temporal instanceof ChronoZonedDateTime)) {
            return true;
        }
        try {
            temporal.plus(1, this);
            return true;
        } catch (RuntimeException unused) {
            try {
                temporal.plus(-1, this);
                return true;
            } catch (RuntimeException unused2) {
                return false;
            }
        }
    }

    public <R extends Temporal> R addTo(R r, long j) {
        return r.plus(j, this);
    }

    public long between(Temporal temporal, Temporal temporal2) {
        return temporal.until(temporal2, this);
    }

    public String toString() {
        return this.name;
    }
}
