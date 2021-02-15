package org.threeten.bp.format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;

final class DateTimeParseContext {
    private boolean caseSensitive;
    private Locale locale;
    private Chronology overrideChronology;
    /* access modifiers changed from: private */
    public ZoneId overrideZone;
    private final ArrayList<Parsed> parsed;
    private boolean strict;
    private DecimalStyle symbols;

    DateTimeParseContext(DateTimeFormatter dateTimeFormatter) {
        this.caseSensitive = true;
        this.strict = true;
        this.parsed = new ArrayList<>();
        this.locale = dateTimeFormatter.getLocale();
        this.symbols = dateTimeFormatter.getDecimalStyle();
        this.overrideChronology = dateTimeFormatter.getChronology();
        this.overrideZone = dateTimeFormatter.getZone();
        this.parsed.add(new Parsed());
    }

    DateTimeParseContext(Locale locale2, DecimalStyle decimalStyle, Chronology chronology) {
        this.caseSensitive = true;
        this.strict = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.parsed = arrayList;
        this.locale = locale2;
        this.symbols = decimalStyle;
        this.overrideChronology = chronology;
        this.overrideZone = null;
        arrayList.add(new Parsed());
    }

    DateTimeParseContext(DateTimeParseContext dateTimeParseContext) {
        this.caseSensitive = true;
        this.strict = true;
        ArrayList<Parsed> arrayList = new ArrayList<>();
        this.parsed = arrayList;
        this.locale = dateTimeParseContext.locale;
        this.symbols = dateTimeParseContext.symbols;
        this.overrideChronology = dateTimeParseContext.overrideChronology;
        this.overrideZone = dateTimeParseContext.overrideZone;
        this.caseSensitive = dateTimeParseContext.caseSensitive;
        this.strict = dateTimeParseContext.strict;
        arrayList.add(new Parsed());
    }

    /* access modifiers changed from: package-private */
    public DateTimeParseContext copy() {
        return new DateTimeParseContext(this);
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
    public Chronology getEffectiveChronology() {
        Chronology chronology = currentParsed().chrono;
        if (chronology != null) {
            return chronology;
        }
        Chronology chronology2 = this.overrideChronology;
        return chronology2 == null ? IsoChronology.INSTANCE : chronology2;
    }

    /* access modifiers changed from: package-private */
    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    /* access modifiers changed from: package-private */
    public void setCaseSensitive(boolean z) {
        this.caseSensitive = z;
    }

    /* access modifiers changed from: package-private */
    public boolean subSequenceEquals(CharSequence charSequence, int i, CharSequence charSequence2, int i2, int i3) {
        if (i + i3 > charSequence.length() || i2 + i3 > charSequence2.length()) {
            return false;
        }
        if (isCaseSensitive()) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (charSequence.charAt(i + i4) != charSequence2.charAt(i2 + i4)) {
                    return false;
                }
            }
            return true;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            char charAt = charSequence.charAt(i + i5);
            char charAt2 = charSequence2.charAt(i2 + i5);
            if (charAt != charAt2 && Character.toUpperCase(charAt) != Character.toUpperCase(charAt2) && Character.toLowerCase(charAt) != Character.toLowerCase(charAt2)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean charEquals(char c, char c2) {
        if (isCaseSensitive()) {
            return c == c2;
        }
        return charEqualsIgnoreCase(c, c2);
    }

    static boolean charEqualsIgnoreCase(char c, char c2) {
        return c == c2 || Character.toUpperCase(c) == Character.toUpperCase(c2) || Character.toLowerCase(c) == Character.toLowerCase(c2);
    }

    /* access modifiers changed from: package-private */
    public boolean isStrict() {
        return this.strict;
    }

    /* access modifiers changed from: package-private */
    public void setStrict(boolean z) {
        this.strict = z;
    }

    /* access modifiers changed from: package-private */
    public void startOptional() {
        this.parsed.add(currentParsed().copy());
    }

    /* access modifiers changed from: package-private */
    public void endOptional(boolean z) {
        if (z) {
            ArrayList<Parsed> arrayList = this.parsed;
            arrayList.remove(arrayList.size() - 2);
            return;
        }
        ArrayList<Parsed> arrayList2 = this.parsed;
        arrayList2.remove(arrayList2.size() - 1);
    }

    private Parsed currentParsed() {
        ArrayList<Parsed> arrayList = this.parsed;
        return arrayList.get(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public Long getParsed(TemporalField temporalField) {
        return currentParsed().fieldValues.get(temporalField);
    }

    /* access modifiers changed from: package-private */
    public int setParsedField(TemporalField temporalField, long j, int i, int i2) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Long put = currentParsed().fieldValues.put(temporalField, Long.valueOf(j));
        return (put == null || put.longValue() == j) ? i2 : ~i;
    }

    /* access modifiers changed from: package-private */
    public void setParsed(Chronology chronology) {
        Jdk8Methods.requireNonNull(chronology, "chrono");
        Parsed currentParsed = currentParsed();
        currentParsed.chrono = chronology;
        if (currentParsed.callbacks != null) {
            ArrayList<Object[]> arrayList = new ArrayList<>(currentParsed.callbacks);
            currentParsed.callbacks.clear();
            for (Object[] objArr : arrayList) {
                ((DateTimeFormatterBuilder.ReducedPrinterParser) objArr[0]).setValue(this, ((Long) objArr[1]).longValue(), ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addChronologyChangedParser(DateTimeFormatterBuilder.ReducedPrinterParser reducedPrinterParser, long j, int i, int i2) {
        Parsed currentParsed = currentParsed();
        if (currentParsed.callbacks == null) {
            currentParsed.callbacks = new ArrayList(2);
        }
        currentParsed.callbacks.add(new Object[]{reducedPrinterParser, Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(i2)});
    }

    /* access modifiers changed from: package-private */
    public void setParsed(ZoneId zoneId) {
        Jdk8Methods.requireNonNull(zoneId, "zone");
        currentParsed().zone = zoneId;
    }

    /* access modifiers changed from: package-private */
    public void setParsedLeapSecond() {
        currentParsed().leapSecond = true;
    }

    /* access modifiers changed from: package-private */
    public Parsed toParsed() {
        return currentParsed();
    }

    public String toString() {
        return currentParsed().toString();
    }

    final class Parsed extends DefaultInterfaceTemporalAccessor {
        List<Object[]> callbacks;
        Chronology chrono;
        Period excessDays;
        final Map<TemporalField, Long> fieldValues;
        boolean leapSecond;
        ZoneId zone;

        private Parsed() {
            this.chrono = null;
            this.zone = null;
            this.fieldValues = new HashMap();
            this.excessDays = Period.ZERO;
        }

        /* access modifiers changed from: protected */
        public Parsed copy() {
            Parsed parsed = new Parsed();
            parsed.chrono = this.chrono;
            parsed.zone = this.zone;
            parsed.fieldValues.putAll(this.fieldValues);
            parsed.leapSecond = this.leapSecond;
            return parsed;
        }

        public String toString() {
            return this.fieldValues.toString() + "," + this.chrono + "," + this.zone;
        }

        public boolean isSupported(TemporalField temporalField) {
            return this.fieldValues.containsKey(temporalField);
        }

        public int get(TemporalField temporalField) {
            if (this.fieldValues.containsKey(temporalField)) {
                return Jdk8Methods.safeToInt(this.fieldValues.get(temporalField).longValue());
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }

        public long getLong(TemporalField temporalField) {
            if (this.fieldValues.containsKey(temporalField)) {
                return this.fieldValues.get(temporalField).longValue();
            }
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }

        public <R> R query(TemporalQuery<R> temporalQuery) {
            if (temporalQuery == TemporalQueries.chronology()) {
                return this.chrono;
            }
            if (temporalQuery == TemporalQueries.zoneId() || temporalQuery == TemporalQueries.zone()) {
                return this.zone;
            }
            return super.query(temporalQuery);
        }

        /* access modifiers changed from: package-private */
        public DateTimeBuilder toBuilder() {
            DateTimeBuilder dateTimeBuilder = new DateTimeBuilder();
            dateTimeBuilder.fieldValues.putAll(this.fieldValues);
            dateTimeBuilder.chrono = DateTimeParseContext.this.getEffectiveChronology();
            ZoneId zoneId = this.zone;
            if (zoneId != null) {
                dateTimeBuilder.zone = zoneId;
            } else {
                dateTimeBuilder.zone = DateTimeParseContext.this.overrideZone;
            }
            dateTimeBuilder.leapSecond = this.leapSecond;
            dateTimeBuilder.excessDays = this.excessDays;
            return dateTimeBuilder;
        }
    }

    /* access modifiers changed from: package-private */
    public void setLocale(Locale locale2) {
        Jdk8Methods.requireNonNull(locale2, "locale");
        this.locale = locale2;
    }
}
