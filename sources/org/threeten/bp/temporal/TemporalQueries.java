package org.threeten.bp.temporal;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.Chronology;

public final class TemporalQueries {
    static final TemporalQuery<Chronology> CHRONO = new TemporalQuery<Chronology>() {
        public Chronology queryFrom(TemporalAccessor temporalAccessor) {
            return (Chronology) temporalAccessor.query(this);
        }
    };
    static final TemporalQuery<LocalDate> LOCAL_DATE = new TemporalQuery<LocalDate>() {
        public LocalDate queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                return LocalDate.ofEpochDay(temporalAccessor.getLong(ChronoField.EPOCH_DAY));
            }
            return null;
        }
    };
    static final TemporalQuery<LocalTime> LOCAL_TIME = new TemporalQuery<LocalTime>() {
        public LocalTime queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.NANO_OF_DAY)) {
                return LocalTime.ofNanoOfDay(temporalAccessor.getLong(ChronoField.NANO_OF_DAY));
            }
            return null;
        }
    };
    static final TemporalQuery<ZoneOffset> OFFSET = new TemporalQuery<ZoneOffset>() {
        public ZoneOffset queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor.isSupported(ChronoField.OFFSET_SECONDS)) {
                return ZoneOffset.ofTotalSeconds(temporalAccessor.get(ChronoField.OFFSET_SECONDS));
            }
            return null;
        }
    };
    static final TemporalQuery<TemporalUnit> PRECISION = new TemporalQuery<TemporalUnit>() {
        public TemporalUnit queryFrom(TemporalAccessor temporalAccessor) {
            return (TemporalUnit) temporalAccessor.query(this);
        }
    };
    static final TemporalQuery<ZoneId> ZONE = new TemporalQuery<ZoneId>() {
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.ZONE_ID);
            return zoneId != null ? zoneId : (ZoneId) temporalAccessor.query(TemporalQueries.OFFSET);
        }
    };
    static final TemporalQuery<ZoneId> ZONE_ID = new TemporalQuery<ZoneId>() {
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            return (ZoneId) temporalAccessor.query(this);
        }
    };

    private TemporalQueries() {
    }

    public static final TemporalQuery<ZoneId> zoneId() {
        return ZONE_ID;
    }

    public static final TemporalQuery<Chronology> chronology() {
        return CHRONO;
    }

    public static final TemporalQuery<TemporalUnit> precision() {
        return PRECISION;
    }

    public static final TemporalQuery<ZoneId> zone() {
        return ZONE;
    }

    public static final TemporalQuery<ZoneOffset> offset() {
        return OFFSET;
    }

    public static final TemporalQuery<LocalDate> localDate() {
        return LOCAL_DATE;
    }

    public static final TemporalQuery<LocalTime> localTime() {
        return LOCAL_TIME;
    }
}
