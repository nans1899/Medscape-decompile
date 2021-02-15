package org.threeten.bp.format;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.android.gms.actions.SearchIntents;
import java.io.IOException;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.pool.TypePool;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Period;
import org.threeten.bp.ZoneId;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.DateTimeParseContext;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQuery;

public final class DateTimeFormatter {
    public static final DateTimeFormatter BASIC_ISO_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(ChronoField.YEAR, 4).appendValue(ChronoField.MONTH_OF_YEAR, 2).appendValue(ChronoField.DAY_OF_MONTH, 2).optionalStart().appendOffset("+HHMMss", "Z").toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_DATE_TIME = new DateTimeFormatterBuilder().append(ISO_LOCAL_DATE_TIME).optionalStart().appendOffsetId().optionalStart().appendLiteral((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH).parseCaseSensitive().appendZoneRegionId().appendLiteral(']').toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_INSTANT = new DateTimeFormatterBuilder().parseCaseInsensitive().appendInstant().toFormatter(ResolverStyle.STRICT);
    public static final DateTimeFormatter ISO_LOCAL_DATE = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_LOCAL_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE).appendLiteral((char) ASCIIPropertyListParser.DATE_APPLE_DATE_TIME_DELIMITER).append(ISO_LOCAL_TIME).toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_LOCAL_TIME = new DateTimeFormatterBuilder().appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER).appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER).appendValue(ChronoField.SECOND_OF_MINUTE, 2).optionalStart().appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter(ResolverStyle.STRICT);
    public static final DateTimeFormatter ISO_OFFSET_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE).appendOffsetId().toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_OFFSET_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_DATE_TIME).appendOffsetId().toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_OFFSET_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_TIME).appendOffsetId().toFormatter(ResolverStyle.STRICT);
    public static final DateTimeFormatter ISO_ORDINAL_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.DAY_OF_YEAR, 3).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().append(ISO_LOCAL_TIME).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT);
    public static final DateTimeFormatter ISO_WEEK_DATE = new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(IsoFields.WEEK_BASED_YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral("-W").appendValue(IsoFields.WEEK_OF_WEEK_BASED_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_WEEK, 1).optionalStart().appendOffsetId().toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    public static final DateTimeFormatter ISO_ZONED_DATE_TIME = new DateTimeFormatterBuilder().append(ISO_OFFSET_DATE_TIME).optionalStart().appendLiteral((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH).parseCaseSensitive().appendZoneRegionId().appendLiteral(']').toFormatter(ResolverStyle.STRICT).withChronology(IsoChronology.INSTANCE);
    private static final TemporalQuery<Period> PARSED_EXCESS_DAYS = new TemporalQuery<Period>() {
        public Period queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor instanceof DateTimeBuilder) {
                return ((DateTimeBuilder) temporalAccessor).excessDays;
            }
            return Period.ZERO;
        }
    };
    private static final TemporalQuery<Boolean> PARSED_LEAP_SECOND = new TemporalQuery<Boolean>() {
        public Boolean queryFrom(TemporalAccessor temporalAccessor) {
            if (temporalAccessor instanceof DateTimeBuilder) {
                return Boolean.valueOf(((DateTimeBuilder) temporalAccessor).leapSecond);
            }
            return Boolean.FALSE;
        }
    };
    public static final DateTimeFormatter RFC_1123_DATE_TIME;
    private final Chronology chrono;
    private final DecimalStyle decimalStyle;
    private final Locale locale;
    private final DateTimeFormatterBuilder.CompositePrinterParser printerParser;
    private final Set<TemporalField> resolverFields;
    private final ResolverStyle resolverStyle;
    private final ZoneId zone;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(1L, "Mon");
        hashMap.put(2L, "Tue");
        hashMap.put(3L, "Wed");
        hashMap.put(4L, "Thu");
        hashMap.put(5L, "Fri");
        hashMap.put(6L, "Sat");
        hashMap.put(7L, "Sun");
        HashMap hashMap2 = new HashMap();
        hashMap2.put(1L, "Jan");
        hashMap2.put(2L, "Feb");
        hashMap2.put(3L, "Mar");
        hashMap2.put(4L, "Apr");
        hashMap2.put(5L, "May");
        hashMap2.put(6L, "Jun");
        hashMap2.put(7L, "Jul");
        hashMap2.put(8L, "Aug");
        hashMap2.put(9L, "Sep");
        hashMap2.put(10L, "Oct");
        hashMap2.put(11L, "Nov");
        hashMap2.put(12L, "Dec");
        RFC_1123_DATE_TIME = new DateTimeFormatterBuilder().parseCaseInsensitive().parseLenient().optionalStart().appendText((TemporalField) ChronoField.DAY_OF_WEEK, (Map<Long, String>) hashMap).appendLiteral(", ").optionalEnd().appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE).appendLiteral(' ').appendText((TemporalField) ChronoField.MONTH_OF_YEAR, (Map<Long, String>) hashMap2).appendLiteral(' ').appendValue(ChronoField.YEAR, 4).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER).appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER).appendValue(ChronoField.SECOND_OF_MINUTE, 2).optionalEnd().appendLiteral(' ').appendOffset("+HHMM", "GMT").toFormatter(ResolverStyle.SMART).withChronology(IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofPattern(String str) {
        return new DateTimeFormatterBuilder().appendPattern(str).toFormatter();
    }

    public static DateTimeFormatter ofPattern(String str, Locale locale2) {
        return new DateTimeFormatterBuilder().appendPattern(str).toFormatter(locale2);
    }

    public static DateTimeFormatter ofLocalizedDate(FormatStyle formatStyle) {
        Jdk8Methods.requireNonNull(formatStyle, "dateStyle");
        return new DateTimeFormatterBuilder().appendLocalized(formatStyle, (FormatStyle) null).toFormatter().withChronology(IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofLocalizedTime(FormatStyle formatStyle) {
        Jdk8Methods.requireNonNull(formatStyle, "timeStyle");
        return new DateTimeFormatterBuilder().appendLocalized((FormatStyle) null, formatStyle).toFormatter().withChronology(IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofLocalizedDateTime(FormatStyle formatStyle) {
        Jdk8Methods.requireNonNull(formatStyle, "dateTimeStyle");
        return new DateTimeFormatterBuilder().appendLocalized(formatStyle, formatStyle).toFormatter().withChronology(IsoChronology.INSTANCE);
    }

    public static DateTimeFormatter ofLocalizedDateTime(FormatStyle formatStyle, FormatStyle formatStyle2) {
        Jdk8Methods.requireNonNull(formatStyle, "dateStyle");
        Jdk8Methods.requireNonNull(formatStyle2, "timeStyle");
        return new DateTimeFormatterBuilder().appendLocalized(formatStyle, formatStyle2).toFormatter().withChronology(IsoChronology.INSTANCE);
    }

    public static final TemporalQuery<Period> parsedExcessDays() {
        return PARSED_EXCESS_DAYS;
    }

    public static final TemporalQuery<Boolean> parsedLeapSecond() {
        return PARSED_LEAP_SECOND;
    }

    DateTimeFormatter(DateTimeFormatterBuilder.CompositePrinterParser compositePrinterParser, Locale locale2, DecimalStyle decimalStyle2, ResolverStyle resolverStyle2, Set<TemporalField> set, Chronology chronology, ZoneId zoneId) {
        this.printerParser = (DateTimeFormatterBuilder.CompositePrinterParser) Jdk8Methods.requireNonNull(compositePrinterParser, "printerParser");
        this.locale = (Locale) Jdk8Methods.requireNonNull(locale2, "locale");
        this.decimalStyle = (DecimalStyle) Jdk8Methods.requireNonNull(decimalStyle2, "decimalStyle");
        this.resolverStyle = (ResolverStyle) Jdk8Methods.requireNonNull(resolverStyle2, "resolverStyle");
        this.resolverFields = set;
        this.chrono = chronology;
        this.zone = zoneId;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public DateTimeFormatter withLocale(Locale locale2) {
        if (this.locale.equals(locale2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, locale2, this.decimalStyle, this.resolverStyle, this.resolverFields, this.chrono, this.zone);
    }

    public DecimalStyle getDecimalStyle() {
        return this.decimalStyle;
    }

    public DateTimeFormatter withDecimalStyle(DecimalStyle decimalStyle2) {
        if (this.decimalStyle.equals(decimalStyle2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, decimalStyle2, this.resolverStyle, this.resolverFields, this.chrono, this.zone);
    }

    public Chronology getChronology() {
        return this.chrono;
    }

    public DateTimeFormatter withChronology(Chronology chronology) {
        if (Jdk8Methods.equals(this.chrono, chronology)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, this.resolverFields, chronology, this.zone);
    }

    public ZoneId getZone() {
        return this.zone;
    }

    public DateTimeFormatter withZone(ZoneId zoneId) {
        if (Jdk8Methods.equals(this.zone, zoneId)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, this.resolverFields, this.chrono, zoneId);
    }

    public ResolverStyle getResolverStyle() {
        return this.resolverStyle;
    }

    public DateTimeFormatter withResolverStyle(ResolverStyle resolverStyle2) {
        Jdk8Methods.requireNonNull(resolverStyle2, "resolverStyle");
        if (Jdk8Methods.equals(this.resolverStyle, resolverStyle2)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, resolverStyle2, this.resolverFields, this.chrono, this.zone);
    }

    public Set<TemporalField> getResolverFields() {
        return this.resolverFields;
    }

    public DateTimeFormatter withResolverFields(TemporalField... temporalFieldArr) {
        if (temporalFieldArr == null) {
            return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, (Set<TemporalField>) null, this.chrono, this.zone);
        }
        HashSet hashSet = new HashSet(Arrays.asList(temporalFieldArr));
        if (Jdk8Methods.equals(this.resolverFields, hashSet)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, Collections.unmodifiableSet(hashSet), this.chrono, this.zone);
    }

    public DateTimeFormatter withResolverFields(Set<TemporalField> set) {
        if (set == null) {
            return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, (Set<TemporalField>) null, this.chrono, this.zone);
        }
        if (Jdk8Methods.equals(this.resolverFields, set)) {
            return this;
        }
        return new DateTimeFormatter(this.printerParser, this.locale, this.decimalStyle, this.resolverStyle, Collections.unmodifiableSet(new HashSet(set)), this.chrono, this.zone);
    }

    public String format(TemporalAccessor temporalAccessor) {
        StringBuilder sb = new StringBuilder(32);
        formatTo(temporalAccessor, sb);
        return sb.toString();
    }

    public void formatTo(TemporalAccessor temporalAccessor, Appendable appendable) {
        Jdk8Methods.requireNonNull(temporalAccessor, "temporal");
        Jdk8Methods.requireNonNull(appendable, "appendable");
        try {
            DateTimePrintContext dateTimePrintContext = new DateTimePrintContext(temporalAccessor, this);
            if (appendable instanceof StringBuilder) {
                this.printerParser.print(dateTimePrintContext, (StringBuilder) appendable);
                return;
            }
            StringBuilder sb = new StringBuilder(32);
            this.printerParser.print(dateTimePrintContext, sb);
            appendable.append(sb);
        } catch (IOException e) {
            throw new DateTimeException(e.getMessage(), e);
        }
    }

    public TemporalAccessor parse(CharSequence charSequence) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        try {
            return parseToBuilder(charSequence, (ParsePosition) null).resolve(this.resolverStyle, this.resolverFields);
        } catch (DateTimeParseException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw createError(charSequence, e2);
        }
    }

    public TemporalAccessor parse(CharSequence charSequence, ParsePosition parsePosition) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        Jdk8Methods.requireNonNull(parsePosition, "position");
        try {
            return parseToBuilder(charSequence, parsePosition).resolve(this.resolverStyle, this.resolverFields);
        } catch (DateTimeParseException e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            throw createError(charSequence, e3);
        }
    }

    public <T> T parse(CharSequence charSequence, TemporalQuery<T> temporalQuery) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        Jdk8Methods.requireNonNull(temporalQuery, "type");
        try {
            return parseToBuilder(charSequence, (ParsePosition) null).resolve(this.resolverStyle, this.resolverFields).build(temporalQuery);
        } catch (DateTimeParseException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw createError(charSequence, e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004d, code lost:
        throw r5;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c A[ExcHandler: DateTimeParseException (r5v2 'e' org.threeten.bp.format.DateTimeParseException A[CUSTOM_DECLARE]), Splitter:B:3:0x000f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.threeten.bp.temporal.TemporalAccessor parseBest(java.lang.CharSequence r5, org.threeten.bp.temporal.TemporalQuery<?>... r6) {
        /*
            r4 = this;
            java.lang.String r0 = "text"
            org.threeten.bp.jdk8.Jdk8Methods.requireNonNull(r5, r0)
            java.lang.String r0 = "types"
            org.threeten.bp.jdk8.Jdk8Methods.requireNonNull(r6, r0)
            int r0 = r6.length
            r1 = 2
            if (r0 < r1) goto L_0x004e
            r0 = 0
            org.threeten.bp.format.DateTimeBuilder r0 = r4.parseToBuilder(r5, r0)     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            org.threeten.bp.format.ResolverStyle r1 = r4.resolverStyle     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            java.util.Set<org.threeten.bp.temporal.TemporalField> r2 = r4.resolverFields     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            org.threeten.bp.format.DateTimeBuilder r0 = r0.resolve(r1, r2)     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            int r1 = r6.length     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            r2 = 0
        L_0x001d:
            if (r2 >= r1) goto L_0x002b
            r3 = r6[r2]     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            java.lang.Object r3 = r0.build(r3)     // Catch:{ RuntimeException -> 0x0028, DateTimeParseException -> 0x004c }
            org.threeten.bp.temporal.TemporalAccessor r3 = (org.threeten.bp.temporal.TemporalAccessor) r3     // Catch:{ RuntimeException -> 0x0028, DateTimeParseException -> 0x004c }
            return r3
        L_0x0028:
            int r2 = r2 + 1
            goto L_0x001d
        L_0x002b:
            org.threeten.bp.DateTimeException r0 = new org.threeten.bp.DateTimeException     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            r1.<init>()     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            java.lang.String r2 = "Unable to convert parsed text to any specified type: "
            r1.append(r2)     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            java.lang.String r6 = java.util.Arrays.toString(r6)     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            r1.append(r6)     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            java.lang.String r6 = r1.toString()     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            r0.<init>(r6)     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
            throw r0     // Catch:{ DateTimeParseException -> 0x004c, RuntimeException -> 0x0046 }
        L_0x0046:
            r6 = move-exception
            org.threeten.bp.format.DateTimeParseException r5 = r4.createError(r5, r6)
            throw r5
        L_0x004c:
            r5 = move-exception
            throw r5
        L_0x004e:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "At least two types must be specified"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatter.parseBest(java.lang.CharSequence, org.threeten.bp.temporal.TemporalQuery[]):org.threeten.bp.temporal.TemporalAccessor");
    }

    private DateTimeParseException createError(CharSequence charSequence, RuntimeException runtimeException) {
        String str;
        if (charSequence.length() > 64) {
            str = charSequence.subSequence(0, 64).toString() + "...";
        } else {
            str = charSequence.toString();
        }
        return new DateTimeParseException("Text '" + str + "' could not be parsed: " + runtimeException.getMessage(), charSequence, 0, runtimeException);
    }

    /* access modifiers changed from: private */
    public DateTimeBuilder parseToBuilder(CharSequence charSequence, ParsePosition parsePosition) {
        String str;
        ParsePosition parsePosition2 = parsePosition != null ? parsePosition : new ParsePosition(0);
        DateTimeParseContext.Parsed parseUnresolved0 = parseUnresolved0(charSequence, parsePosition2);
        if (parseUnresolved0 != null && parsePosition2.getErrorIndex() < 0 && (parsePosition != null || parsePosition2.getIndex() >= charSequence.length())) {
            return parseUnresolved0.toBuilder();
        }
        if (charSequence.length() > 64) {
            str = charSequence.subSequence(0, 64).toString() + "...";
        } else {
            str = charSequence.toString();
        }
        if (parsePosition2.getErrorIndex() >= 0) {
            throw new DateTimeParseException("Text '" + str + "' could not be parsed at index " + parsePosition2.getErrorIndex(), charSequence, parsePosition2.getErrorIndex());
        }
        throw new DateTimeParseException("Text '" + str + "' could not be parsed, unparsed text found at index " + parsePosition2.getIndex(), charSequence, parsePosition2.getIndex());
    }

    public TemporalAccessor parseUnresolved(CharSequence charSequence, ParsePosition parsePosition) {
        return parseUnresolved0(charSequence, parsePosition);
    }

    /* access modifiers changed from: private */
    public DateTimeParseContext.Parsed parseUnresolved0(CharSequence charSequence, ParsePosition parsePosition) {
        Jdk8Methods.requireNonNull(charSequence, "text");
        Jdk8Methods.requireNonNull(parsePosition, "position");
        DateTimeParseContext dateTimeParseContext = new DateTimeParseContext(this);
        int parse = this.printerParser.parse(dateTimeParseContext, charSequence, parsePosition.getIndex());
        if (parse < 0) {
            parsePosition.setErrorIndex(~parse);
            return null;
        }
        parsePosition.setIndex(parse);
        return dateTimeParseContext.toParsed();
    }

    /* access modifiers changed from: package-private */
    public DateTimeFormatterBuilder.CompositePrinterParser toPrinterParser(boolean z) {
        return this.printerParser.withOptional(z);
    }

    public Format toFormat() {
        return new ClassicFormat(this, (TemporalQuery<?>) null);
    }

    public Format toFormat(TemporalQuery<?> temporalQuery) {
        Jdk8Methods.requireNonNull(temporalQuery, SearchIntents.EXTRA_QUERY);
        return new ClassicFormat(this, temporalQuery);
    }

    public String toString() {
        String compositePrinterParser = this.printerParser.toString();
        return compositePrinterParser.startsWith("[") ? compositePrinterParser : compositePrinterParser.substring(1, compositePrinterParser.length() - 1);
    }

    static class ClassicFormat extends Format {
        private final DateTimeFormatter formatter;
        private final TemporalQuery<?> query;

        public ClassicFormat(DateTimeFormatter dateTimeFormatter, TemporalQuery<?> temporalQuery) {
            this.formatter = dateTimeFormatter;
            this.query = temporalQuery;
        }

        public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
            Jdk8Methods.requireNonNull(obj, "obj");
            Jdk8Methods.requireNonNull(stringBuffer, "toAppendTo");
            Jdk8Methods.requireNonNull(fieldPosition, "pos");
            if (obj instanceof TemporalAccessor) {
                fieldPosition.setBeginIndex(0);
                fieldPosition.setEndIndex(0);
                try {
                    this.formatter.formatTo((TemporalAccessor) obj, stringBuffer);
                    return stringBuffer;
                } catch (RuntimeException e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            } else {
                throw new IllegalArgumentException("Format target must implement TemporalAccessor");
            }
        }

        public Object parseObject(String str) throws ParseException {
            Jdk8Methods.requireNonNull(str, "text");
            try {
                if (this.query == null) {
                    return this.formatter.parseToBuilder(str, (ParsePosition) null).resolve(this.formatter.getResolverStyle(), this.formatter.getResolverFields());
                }
                return this.formatter.parse((CharSequence) str, this.query);
            } catch (DateTimeParseException e) {
                throw new ParseException(e.getMessage(), e.getErrorIndex());
            } catch (RuntimeException e2) {
                throw ((ParseException) new ParseException(e2.getMessage(), 0).initCause(e2));
            }
        }

        public Object parseObject(String str, ParsePosition parsePosition) {
            Jdk8Methods.requireNonNull(str, "text");
            try {
                DateTimeParseContext.Parsed access$100 = this.formatter.parseUnresolved0(str, parsePosition);
                if (access$100 == null) {
                    if (parsePosition.getErrorIndex() < 0) {
                        parsePosition.setErrorIndex(0);
                    }
                    return null;
                }
                try {
                    DateTimeBuilder resolve = access$100.toBuilder().resolve(this.formatter.getResolverStyle(), this.formatter.getResolverFields());
                    if (this.query == null) {
                        return resolve;
                    }
                    return resolve.build(this.query);
                } catch (RuntimeException unused) {
                    parsePosition.setErrorIndex(0);
                    return null;
                }
            } catch (IndexOutOfBoundsException unused2) {
                if (parsePosition.getErrorIndex() < 0) {
                    parsePosition.setErrorIndex(0);
                }
                return null;
            }
        }
    }
}
