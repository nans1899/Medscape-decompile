package org.threeten.bp.format;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Locale;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;

final class DateTimePrintContext {
    private Locale locale;
    private int optional;
    private DecimalStyle symbols;
    private TemporalAccessor temporal;

    DateTimePrintContext(TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        this.temporal = adjust(temporalAccessor, dateTimeFormatter);
        this.locale = dateTimeFormatter.getLocale();
        this.symbols = dateTimeFormatter.getDecimalStyle();
    }

    DateTimePrintContext(TemporalAccessor temporalAccessor, Locale locale2, DecimalStyle decimalStyle) {
        this.temporal = temporalAccessor;
        this.locale = locale2;
        this.symbols = decimalStyle;
    }

    private static TemporalAccessor adjust(final TemporalAccessor temporalAccessor, DateTimeFormatter dateTimeFormatter) {
        Chronology chronology = dateTimeFormatter.getChronology();
        ZoneId zone = dateTimeFormatter.getZone();
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        Chronology chronology2 = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        final ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
        final ChronoLocalDate chronoLocalDate = null;
        if (Jdk8Methods.equals(chronology2, chronology)) {
            chronology = null;
        }
        if (Jdk8Methods.equals(zoneId, zone)) {
            zone = null;
        }
        if (chronology == null && zone == null) {
            return temporalAccessor;
        }
        final Chronology chronology3 = chronology != null ? chronology : chronology2;
        if (zone != null) {
            zoneId = zone;
        }
        if (zone != null) {
            if (temporalAccessor.isSupported(ChronoField.INSTANT_SECONDS)) {
                if (chronology3 == null) {
                    chronology3 = IsoChronology.INSTANCE;
                }
                return chronology3.zonedDateTime(Instant.from(temporalAccessor), zone);
            }
            ZoneId normalized = zone.normalized();
            ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.query(TemporalQueries.offset());
            if ((normalized instanceof ZoneOffset) && zoneOffset != null && !normalized.equals(zoneOffset)) {
                throw new DateTimeException("Invalid override zone for temporal: " + zone + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + temporalAccessor);
            }
        }
        if (chronology != null) {
            if (temporalAccessor.isSupported(ChronoField.EPOCH_DAY)) {
                chronoLocalDate = chronology3.date(temporalAccessor);
            } else if (!(chronology == IsoChronology.INSTANCE && chronology2 == null)) {
                ChronoField[] values = ChronoField.values();
                int length = values.length;
                int i = 0;
                while (i < length) {
                    ChronoField chronoField = values[i];
                    if (!chronoField.isDateBased() || !temporalAccessor.isSupported(chronoField)) {
                        i++;
                    } else {
                        throw new DateTimeException("Invalid override chronology for temporal: " + chronology + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + temporalAccessor);
                    }
                }
            }
        }
        return new DefaultInterfaceTemporalAccessor() {
            public boolean isSupported(TemporalField temporalField) {
                if (ChronoLocalDate.this == null || !temporalField.isDateBased()) {
                    return temporalAccessor.isSupported(temporalField);
                }
                return ChronoLocalDate.this.isSupported(temporalField);
            }

            public ValueRange range(TemporalField temporalField) {
                if (ChronoLocalDate.this == null || !temporalField.isDateBased()) {
                    return temporalAccessor.range(temporalField);
                }
                return ChronoLocalDate.this.range(temporalField);
            }

            public long getLong(TemporalField temporalField) {
                if (ChronoLocalDate.this == null || !temporalField.isDateBased()) {
                    return temporalAccessor.getLong(temporalField);
                }
                return ChronoLocalDate.this.getLong(temporalField);
            }

            public <R> R query(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.chronology()) {
                    return chronology3;
                }
                if (temporalQuery == TemporalQueries.zoneId()) {
                    return zoneId;
                }
                if (temporalQuery == TemporalQueries.precision()) {
                    return temporalAccessor.query(temporalQuery);
                }
                return temporalQuery.queryFrom(this);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public TemporalAccessor getTemporal() {
        return this.temporal;
    }

    /* access modifiers changed from: package-private */
    public Locale getLocale() {
        return this.locale;
    }

    /* access modifiers changed from: package-private */
    public DecimalStyle getSymbols() {
        return this.symbols;
    }

    /* access modifiers changed from: package-private */
    public void startOptional() {
        this.optional++;
    }

    /* access modifiers changed from: package-private */
    public void endOptional() {
        this.optional--;
    }

    /* access modifiers changed from: package-private */
    public <R> R getValue(TemporalQuery<R> temporalQuery) {
        R query = this.temporal.query(temporalQuery);
        if (query != null || this.optional != 0) {
            return query;
        }
        throw new DateTimeException("Unable to extract value: " + this.temporal.getClass());
    }

    /* access modifiers changed from: package-private */
    public Long getValue(TemporalField temporalField) {
        try {
            return Long.valueOf(this.temporal.getLong(temporalField));
        } catch (DateTimeException e) {
            if (this.optional > 0) {
                return null;
            }
            throw e;
        }
    }

    public String toString() {
        return this.temporal.toString();
    }

    /* access modifiers changed from: package-private */
    public void setDateTime(TemporalAccessor temporalAccessor) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        this.temporal = temporalAccessor;
    }

    /* access modifiers changed from: package-private */
    public void setLocale(Locale locale2) {
        Jdk8Methods.requireNonNull(locale2, "locale");
        this.locale = locale2;
    }
}
