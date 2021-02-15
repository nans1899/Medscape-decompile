package org.threeten.bp.format;

import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.internal.AnalyticsEvents;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import okhttp3.internal.http2.Http2Connection;
import org.joda.time.DateTimeConstants;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.ChronoLocalDate;
import org.threeten.bp.chrono.Chronology;
import org.threeten.bp.format.SimpleDateTimeTextProvider;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.IsoFields;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.temporal.WeekFields;
import org.threeten.bp.zone.ZoneRulesProvider;

public final class DateTimeFormatterBuilder {
    private static final Map<Character, TemporalField> FIELD_MAP;
    static final Comparator<String> LENGTH_SORT = new Comparator<String>() {
        public int compare(String str, String str2) {
            return str.length() == str2.length() ? str.compareTo(str2) : str.length() - str2.length();
        }
    };
    private static final TemporalQuery<ZoneId> QUERY_REGION_ONLY = new TemporalQuery<ZoneId>() {
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zoneId());
            if (zoneId == null || (zoneId instanceof ZoneOffset)) {
                return null;
            }
            return zoneId;
        }
    };
    private DateTimeFormatterBuilder active;
    private final boolean optional;
    private char padNextChar;
    private int padNextWidth;
    private final DateTimeFormatterBuilder parent;
    private final List<DateTimePrinterParser> printerParsers;
    private int valueParserIndex;

    interface DateTimePrinterParser {
        int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i);

        boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb);
    }

    static {
        HashMap hashMap = new HashMap();
        FIELD_MAP = hashMap;
        hashMap.put('G', ChronoField.ERA);
        FIELD_MAP.put('y', ChronoField.YEAR_OF_ERA);
        FIELD_MAP.put('u', ChronoField.YEAR);
        FIELD_MAP.put('Q', IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put('q', IsoFields.QUARTER_OF_YEAR);
        FIELD_MAP.put('M', ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put('L', ChronoField.MONTH_OF_YEAR);
        FIELD_MAP.put(Character.valueOf(ASCIIPropertyListParser.DATA_GSDATE_BEGIN_TOKEN), ChronoField.DAY_OF_YEAR);
        FIELD_MAP.put(Character.valueOf(Advice.OffsetMapping.ForOrigin.Renderer.ForDescriptor.SYMBOL), ChronoField.DAY_OF_MONTH);
        FIELD_MAP.put('F', ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        FIELD_MAP.put('E', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('c', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('e', ChronoField.DAY_OF_WEEK);
        FIELD_MAP.put('a', ChronoField.AMPM_OF_DAY);
        FIELD_MAP.put('H', ChronoField.HOUR_OF_DAY);
        FIELD_MAP.put('k', ChronoField.CLOCK_HOUR_OF_DAY);
        FIELD_MAP.put('K', ChronoField.HOUR_OF_AMPM);
        FIELD_MAP.put('h', ChronoField.CLOCK_HOUR_OF_AMPM);
        FIELD_MAP.put(Character.valueOf(Advice.OffsetMapping.ForOrigin.Renderer.ForMethodName.SYMBOL), ChronoField.MINUTE_OF_HOUR);
        FIELD_MAP.put(Character.valueOf(Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL), ChronoField.SECOND_OF_MINUTE);
        FIELD_MAP.put('S', ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put('A', ChronoField.MILLI_OF_DAY);
        FIELD_MAP.put('n', ChronoField.NANO_OF_SECOND);
        FIELD_MAP.put(Character.valueOf(ASCIIPropertyListParser.DATA_GSBOOL_FALSE_TOKEN), ChronoField.NANO_OF_DAY);
    }

    public static String getLocalizedDateTimePattern(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat dateFormat;
        Jdk8Methods.requireNonNull(locale, "locale");
        Jdk8Methods.requireNonNull(chronology, "chrono");
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either dateStyle or timeStyle must be non-null");
        }
        if (formatStyle == null) {
            dateFormat = DateFormat.getTimeInstance(formatStyle2.ordinal(), locale);
        } else if (formatStyle2 != null) {
            dateFormat = DateFormat.getDateTimeInstance(formatStyle.ordinal(), formatStyle2.ordinal(), locale);
        } else {
            dateFormat = DateFormat.getDateInstance(formatStyle.ordinal(), locale);
        }
        if (dateFormat instanceof SimpleDateFormat) {
            return ((SimpleDateFormat) dateFormat).toPattern();
        }
        throw new IllegalArgumentException("Unable to determine pattern");
    }

    public DateTimeFormatterBuilder() {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = null;
        this.optional = false;
    }

    private DateTimeFormatterBuilder(DateTimeFormatterBuilder dateTimeFormatterBuilder, boolean z) {
        this.active = this;
        this.printerParsers = new ArrayList();
        this.valueParserIndex = -1;
        this.parent = dateTimeFormatterBuilder;
        this.optional = z;
    }

    public DateTimeFormatterBuilder parseCaseSensitive() {
        appendInternal(SettingsParser.SENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseCaseInsensitive() {
        appendInternal(SettingsParser.INSENSITIVE);
        return this;
    }

    public DateTimeFormatterBuilder parseStrict() {
        appendInternal(SettingsParser.STRICT);
        return this;
    }

    public DateTimeFormatterBuilder parseLenient() {
        appendInternal(SettingsParser.LENIENT);
        return this;
    }

    public DateTimeFormatterBuilder parseDefaulting(TemporalField temporalField, long j) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendInternal(new DefaultingParser(temporalField, j));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendValue(new NumberPrinterParser(temporalField, 1, 19, SignStyle.NORMAL));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The width must be from 1 to 19 inclusive but was " + i);
        }
        appendValue(new NumberPrinterParser(temporalField, i, i, SignStyle.NOT_NEGATIVE));
        return this;
    }

    public DateTimeFormatterBuilder appendValue(TemporalField temporalField, int i, int i2, SignStyle signStyle) {
        if (i == i2 && signStyle == SignStyle.NOT_NEGATIVE) {
            return appendValue(temporalField, i2);
        }
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(signStyle, "signStyle");
        if (i < 1 || i > 19) {
            throw new IllegalArgumentException("The minimum width must be from 1 to 19 inclusive but was " + i);
        } else if (i2 < 1 || i2 > 19) {
            throw new IllegalArgumentException("The maximum width must be from 1 to 19 inclusive but was " + i2);
        } else if (i2 >= i) {
            appendValue(new NumberPrinterParser(temporalField, i, i2, signStyle));
            return this;
        } else {
            throw new IllegalArgumentException("The maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
        }
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, int i3) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        appendValue((NumberPrinterParser) new ReducedPrinterParser(temporalField, i, i2, i3, (ChronoLocalDate) null));
        return this;
    }

    public DateTimeFormatterBuilder appendValueReduced(TemporalField temporalField, int i, int i2, ChronoLocalDate chronoLocalDate) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(chronoLocalDate, "baseDate");
        appendValue((NumberPrinterParser) new ReducedPrinterParser(temporalField, i, i2, 0, chronoLocalDate));
        return this;
    }

    private DateTimeFormatterBuilder appendValue(NumberPrinterParser numberPrinterParser) {
        NumberPrinterParser numberPrinterParser2;
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.valueParserIndex;
        if (i < 0 || !(dateTimeFormatterBuilder.printerParsers.get(i) instanceof NumberPrinterParser)) {
            this.active.valueParserIndex = appendInternal(numberPrinterParser);
        } else {
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            int i2 = dateTimeFormatterBuilder2.valueParserIndex;
            NumberPrinterParser numberPrinterParser3 = (NumberPrinterParser) dateTimeFormatterBuilder2.printerParsers.get(i2);
            if (numberPrinterParser.minWidth == numberPrinterParser.maxWidth && numberPrinterParser.signStyle == SignStyle.NOT_NEGATIVE) {
                numberPrinterParser2 = numberPrinterParser3.withSubsequentWidth(numberPrinterParser.maxWidth);
                appendInternal(numberPrinterParser.withFixedWidth());
                this.active.valueParserIndex = i2;
            } else {
                numberPrinterParser2 = numberPrinterParser3.withFixedWidth();
                this.active.valueParserIndex = appendInternal(numberPrinterParser);
            }
            this.active.printerParsers.set(i2, numberPrinterParser2);
        }
        return this;
    }

    public DateTimeFormatterBuilder appendFraction(TemporalField temporalField, int i, int i2, boolean z) {
        appendInternal(new FractionPrinterParser(temporalField, i, i2, z));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField) {
        return appendText(temporalField, TextStyle.FULL);
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, TextStyle textStyle) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        appendInternal(new TextPrinterParser(temporalField, textStyle, DateTimeTextProvider.getInstance()));
        return this;
    }

    public DateTimeFormatterBuilder appendText(TemporalField temporalField, Map<Long, String> map) {
        Jdk8Methods.requireNonNull(temporalField, "field");
        Jdk8Methods.requireNonNull(map, "textLookup");
        final SimpleDateTimeTextProvider.LocaleStore localeStore = new SimpleDateTimeTextProvider.LocaleStore(Collections.singletonMap(TextStyle.FULL, new LinkedHashMap(map)));
        appendInternal(new TextPrinterParser(temporalField, TextStyle.FULL, new DateTimeTextProvider() {
            public String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale) {
                return localeStore.getText(j, textStyle);
            }

            public Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField, TextStyle textStyle, Locale locale) {
                return localeStore.getTextIterator(textStyle);
            }
        }));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant() {
        appendInternal(new InstantPrinterParser(-2));
        return this;
    }

    public DateTimeFormatterBuilder appendInstant(int i) {
        if (i < -1 || i > 9) {
            throw new IllegalArgumentException("Invalid fractional digits: " + i);
        }
        appendInternal(new InstantPrinterParser(i));
        return this;
    }

    public DateTimeFormatterBuilder appendOffsetId() {
        appendInternal(OffsetIdPrinterParser.INSTANCE_ID);
        return this;
    }

    public DateTimeFormatterBuilder appendOffset(String str, String str2) {
        appendInternal(new OffsetIdPrinterParser(str2, str));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalizedOffset(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
        if (textStyle == TextStyle.FULL || textStyle == TextStyle.SHORT) {
            appendInternal(new LocalizedOffsetPrinterParser(textStyle));
            return this;
        }
        throw new IllegalArgumentException("Style must be either full or short");
    }

    public DateTimeFormatterBuilder appendZoneId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zoneId(), "ZoneId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneRegionId() {
        appendInternal(new ZoneIdPrinterParser(QUERY_REGION_ONLY, "ZoneRegionId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneOrOffsetId() {
        appendInternal(new ZoneIdPrinterParser(TemporalQueries.zone(), "ZoneOrOffsetId()"));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle, Set<ZoneId> set) {
        Jdk8Methods.requireNonNull(set, "preferredZones");
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyId() {
        appendInternal(new ChronoPrinterParser((TextStyle) null));
        return this;
    }

    public DateTimeFormatterBuilder appendChronologyText(TextStyle textStyle) {
        Jdk8Methods.requireNonNull(textStyle, "textStyle");
        appendInternal(new ChronoPrinterParser(textStyle));
        return this;
    }

    public DateTimeFormatterBuilder appendLocalized(FormatStyle formatStyle, FormatStyle formatStyle2) {
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Either the date or time style must be non-null");
        }
        appendInternal(new LocalizedPrinterParser(formatStyle, formatStyle2));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        appendInternal(new CharLiteralPrinterParser(c));
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        Jdk8Methods.requireNonNull(str, "literal");
        if (str.length() > 0) {
            if (str.length() == 1) {
                appendInternal(new CharLiteralPrinterParser(str.charAt(0)));
            } else {
                appendInternal(new StringLiteralPrinterParser(str));
            }
        }
        return this;
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(false));
        return this;
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeFormatter dateTimeFormatter) {
        Jdk8Methods.requireNonNull(dateTimeFormatter, "formatter");
        appendInternal(dateTimeFormatter.toPrinterParser(true));
        return this;
    }

    public DateTimeFormatterBuilder appendPattern(String str) {
        Jdk8Methods.requireNonNull(str, "pattern");
        parsePattern(str);
        return this;
    }

    private void parsePattern(String str) {
        int i;
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
                int i3 = i2 + 1;
                while (i3 < str.length() && str.charAt(i3) == charAt) {
                    i3++;
                }
                int i4 = i3 - i2;
                if (charAt == 'p') {
                    if (i3 >= str.length() || (((charAt = str.charAt(i3)) < 'A' || charAt > 'Z') && (charAt < 'a' || charAt > 'z'))) {
                        i = i4;
                        i4 = 0;
                    } else {
                        int i5 = i3 + 1;
                        while (i5 < str.length() && str.charAt(i5) == charAt) {
                            i5++;
                        }
                        i = i5 - i3;
                        i3 = i5;
                    }
                    if (i4 != 0) {
                        padNext(i4);
                        i4 = i;
                    } else {
                        throw new IllegalArgumentException("Pad letter 'p' must be followed by valid pad pattern: " + str);
                    }
                }
                TemporalField temporalField = FIELD_MAP.get(Character.valueOf(charAt));
                if (temporalField != null) {
                    parseField(charAt, i4, temporalField);
                } else if (charAt == 'z') {
                    if (i4 > 4) {
                        throw new IllegalArgumentException("Too many pattern letters: " + charAt);
                    } else if (i4 == 4) {
                        appendZoneText(TextStyle.FULL);
                    } else {
                        appendZoneText(TextStyle.SHORT);
                    }
                } else if (charAt != 'V') {
                    String str2 = "+0000";
                    if (charAt == 'Z') {
                        if (i4 < 4) {
                            appendOffset("+HHMM", str2);
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else if (i4 == 5) {
                            appendOffset("+HH:MM:ss", "Z");
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + charAt);
                        }
                    } else if (charAt == 'O') {
                        if (i4 == 1) {
                            appendLocalizedOffset(TextStyle.SHORT);
                        } else if (i4 == 4) {
                            appendLocalizedOffset(TextStyle.FULL);
                        } else {
                            throw new IllegalArgumentException("Pattern letter count must be 1 or 4: " + charAt);
                        }
                    } else if (charAt == 'X') {
                        if (i4 <= 5) {
                            appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], "Z");
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + charAt);
                        }
                    } else if (charAt == 'x') {
                        if (i4 <= 5) {
                            if (i4 == 1) {
                                str2 = "+00";
                            } else if (i4 % 2 != 0) {
                                str2 = "+00:00";
                            }
                            appendOffset(OffsetIdPrinterParser.PATTERNS[i4 + (i4 == 1 ? 0 : 1)], str2);
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + charAt);
                        }
                    } else if (charAt == 'W') {
                        if (i4 <= 1) {
                            appendInternal(new WeekFieldsPrinterParser('W', i4));
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + charAt);
                        }
                    } else if (charAt == 'w') {
                        if (i4 <= 2) {
                            appendInternal(new WeekFieldsPrinterParser('w', i4));
                        } else {
                            throw new IllegalArgumentException("Too many pattern letters: " + charAt);
                        }
                    } else if (charAt == 'Y') {
                        appendInternal(new WeekFieldsPrinterParser(ASCIIPropertyListParser.DATA_GSBOOL_TRUE_TOKEN, i4));
                    } else {
                        throw new IllegalArgumentException("Unknown pattern letter: " + charAt);
                    }
                } else if (i4 == 2) {
                    appendZoneId();
                } else {
                    throw new IllegalArgumentException("Pattern letter count must be 2: " + charAt);
                }
                i2 = i3 - 1;
            } else if (charAt == '\'') {
                int i6 = i2 + 1;
                int i7 = i6;
                while (i7 < str.length()) {
                    if (str.charAt(i7) == '\'') {
                        int i8 = i7 + 1;
                        if (i8 >= str.length() || str.charAt(i8) != '\'') {
                            break;
                        }
                        i7 = i8;
                    }
                    i7++;
                }
                if (i7 < str.length()) {
                    String substring = str.substring(i6, i7);
                    if (substring.length() == 0) {
                        appendLiteral('\'');
                    } else {
                        appendLiteral(substring.replace("''", "'"));
                    }
                    i2 = i7;
                } else {
                    throw new IllegalArgumentException("Pattern ends with an incomplete string literal: " + str);
                }
            } else if (charAt == '[') {
                optionalStart();
            } else if (charAt == ']') {
                if (this.active.parent != null) {
                    optionalEnd();
                } else {
                    throw new IllegalArgumentException("Pattern invalid as it contains ] without previous [");
                }
            } else if (charAt == '{' || charAt == '}' || charAt == '#') {
                throw new IllegalArgumentException("Pattern includes reserved character: '" + charAt + "'");
            } else {
                appendLiteral(charAt);
            }
            i2++;
        }
    }

    private void parseField(char c, int i, TemporalField temporalField) {
        if (c != 'Q') {
            if (c == 'S') {
                appendFraction(ChronoField.NANO_OF_SECOND, i, i, false);
                return;
            } else if (c != 'a') {
                if (!(c == 'h' || c == 'k' || c == 'm')) {
                    if (c != 'q') {
                        if (c != 's') {
                            if (c != 'u' && c != 'y') {
                                switch (c) {
                                    case 'D':
                                        if (i == 1) {
                                            appendValue(temporalField);
                                            return;
                                        } else if (i <= 3) {
                                            appendValue(temporalField, i);
                                            return;
                                        } else {
                                            throw new IllegalArgumentException("Too many pattern letters: " + c);
                                        }
                                    case 'E':
                                    case 'G':
                                        if (i == 1 || i == 2 || i == 3) {
                                            appendText(temporalField, TextStyle.SHORT);
                                            return;
                                        } else if (i == 4) {
                                            appendText(temporalField, TextStyle.FULL);
                                            return;
                                        } else if (i == 5) {
                                            appendText(temporalField, TextStyle.NARROW);
                                            return;
                                        } else {
                                            throw new IllegalArgumentException("Too many pattern letters: " + c);
                                        }
                                    case 'F':
                                        if (i == 1) {
                                            appendValue(temporalField);
                                            return;
                                        }
                                        throw new IllegalArgumentException("Too many pattern letters: " + c);
                                    case 'H':
                                        break;
                                    default:
                                        switch (c) {
                                            case 'K':
                                                break;
                                            case 'L':
                                                break;
                                            case 'M':
                                                break;
                                            default:
                                                switch (c) {
                                                    case 'c':
                                                        if (i == 1) {
                                                            appendInternal(new WeekFieldsPrinterParser('c', i));
                                                            return;
                                                        } else if (i == 2) {
                                                            throw new IllegalArgumentException("Invalid number of pattern letters: " + c);
                                                        } else if (i == 3) {
                                                            appendText(temporalField, TextStyle.SHORT_STANDALONE);
                                                            return;
                                                        } else if (i == 4) {
                                                            appendText(temporalField, TextStyle.FULL_STANDALONE);
                                                            return;
                                                        } else if (i == 5) {
                                                            appendText(temporalField, TextStyle.NARROW_STANDALONE);
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException("Too many pattern letters: " + c);
                                                        }
                                                    case 'd':
                                                        break;
                                                    case 'e':
                                                        if (i == 1 || i == 2) {
                                                            appendInternal(new WeekFieldsPrinterParser('e', i));
                                                            return;
                                                        } else if (i == 3) {
                                                            appendText(temporalField, TextStyle.SHORT);
                                                            return;
                                                        } else if (i == 4) {
                                                            appendText(temporalField, TextStyle.FULL);
                                                            return;
                                                        } else if (i == 5) {
                                                            appendText(temporalField, TextStyle.NARROW);
                                                            return;
                                                        } else {
                                                            throw new IllegalArgumentException("Too many pattern letters: " + c);
                                                        }
                                                    default:
                                                        if (i == 1) {
                                                            appendValue(temporalField);
                                                            return;
                                                        } else {
                                                            appendValue(temporalField, i);
                                                            return;
                                                        }
                                                }
                                        }
                                }
                            } else if (i == 2) {
                                appendValueReduced(temporalField, 2, 2, (ChronoLocalDate) ReducedPrinterParser.BASE_DATE);
                                return;
                            } else if (i < 4) {
                                appendValue(temporalField, i, 19, SignStyle.NORMAL);
                                return;
                            } else {
                                appendValue(temporalField, i, 19, SignStyle.EXCEEDS_PAD);
                                return;
                            }
                        }
                    }
                    if (i == 1) {
                        appendValue(temporalField);
                        return;
                    } else if (i == 2) {
                        appendValue(temporalField, 2);
                        return;
                    } else if (i == 3) {
                        appendText(temporalField, TextStyle.SHORT_STANDALONE);
                        return;
                    } else if (i == 4) {
                        appendText(temporalField, TextStyle.FULL_STANDALONE);
                        return;
                    } else if (i == 5) {
                        appendText(temporalField, TextStyle.NARROW_STANDALONE);
                        return;
                    } else {
                        throw new IllegalArgumentException("Too many pattern letters: " + c);
                    }
                }
                if (i == 1) {
                    appendValue(temporalField);
                    return;
                } else if (i == 2) {
                    appendValue(temporalField, i);
                    return;
                } else {
                    throw new IllegalArgumentException("Too many pattern letters: " + c);
                }
            } else if (i == 1) {
                appendText(temporalField, TextStyle.SHORT);
                return;
            } else {
                throw new IllegalArgumentException("Too many pattern letters: " + c);
            }
        }
        if (i == 1) {
            appendValue(temporalField);
        } else if (i == 2) {
            appendValue(temporalField, 2);
        } else if (i == 3) {
            appendText(temporalField, TextStyle.SHORT);
        } else if (i == 4) {
            appendText(temporalField, TextStyle.FULL);
        } else if (i == 5) {
            appendText(temporalField, TextStyle.NARROW);
        } else {
            throw new IllegalArgumentException("Too many pattern letters: " + c);
        }
    }

    public DateTimeFormatterBuilder padNext(int i) {
        return padNext(i, ' ');
    }

    public DateTimeFormatterBuilder padNext(int i, char c) {
        if (i >= 1) {
            DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
            dateTimeFormatterBuilder.padNextWidth = i;
            dateTimeFormatterBuilder.padNextChar = c;
            dateTimeFormatterBuilder.valueParserIndex = -1;
            return this;
        }
        throw new IllegalArgumentException("The pad width must be at least one but was " + i);
    }

    public DateTimeFormatterBuilder optionalStart() {
        this.active.valueParserIndex = -1;
        this.active = new DateTimeFormatterBuilder(this.active, true);
        return this;
    }

    public DateTimeFormatterBuilder optionalEnd() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        if (dateTimeFormatterBuilder.parent != null) {
            if (dateTimeFormatterBuilder.printerParsers.size() > 0) {
                DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
                CompositePrinterParser compositePrinterParser = new CompositePrinterParser(dateTimeFormatterBuilder2.printerParsers, dateTimeFormatterBuilder2.optional);
                this.active = this.active.parent;
                appendInternal(compositePrinterParser);
            } else {
                this.active = this.active.parent;
            }
            return this;
        }
        throw new IllegalStateException("Cannot call optionalEnd() as there was no previous call to optionalStart()");
    }

    private int appendInternal(DateTimePrinterParser dateTimePrinterParser) {
        Jdk8Methods.requireNonNull(dateTimePrinterParser, "pp");
        DateTimeFormatterBuilder dateTimeFormatterBuilder = this.active;
        int i = dateTimeFormatterBuilder.padNextWidth;
        if (i > 0) {
            if (dateTimePrinterParser != null) {
                dateTimePrinterParser = new PadPrinterParserDecorator(dateTimePrinterParser, i, dateTimeFormatterBuilder.padNextChar);
            }
            DateTimeFormatterBuilder dateTimeFormatterBuilder2 = this.active;
            dateTimeFormatterBuilder2.padNextWidth = 0;
            dateTimeFormatterBuilder2.padNextChar = 0;
        }
        this.active.printerParsers.add(dateTimePrinterParser);
        DateTimeFormatterBuilder dateTimeFormatterBuilder3 = this.active;
        dateTimeFormatterBuilder3.valueParserIndex = -1;
        return dateTimeFormatterBuilder3.printerParsers.size() - 1;
    }

    public DateTimeFormatter toFormatter() {
        return toFormatter(Locale.getDefault());
    }

    public DateTimeFormatter toFormatter(Locale locale) {
        Jdk8Methods.requireNonNull(locale, "locale");
        while (this.active.parent != null) {
            optionalEnd();
        }
        return new DateTimeFormatter(new CompositePrinterParser(this.printerParsers, false), locale, DecimalStyle.STANDARD, ResolverStyle.SMART, (Set<TemporalField>) null, (Chronology) null, (ZoneId) null);
    }

    /* access modifiers changed from: package-private */
    public DateTimeFormatter toFormatter(ResolverStyle resolverStyle) {
        return toFormatter().withResolverStyle(resolverStyle);
    }

    static final class CompositePrinterParser implements DateTimePrinterParser {
        private final boolean optional;
        private final DateTimePrinterParser[] printerParsers;

        CompositePrinterParser(List<DateTimePrinterParser> list, boolean z) {
            this((DateTimePrinterParser[]) list.toArray(new DateTimePrinterParser[list.size()]), z);
        }

        CompositePrinterParser(DateTimePrinterParser[] dateTimePrinterParserArr, boolean z) {
            this.printerParsers = dateTimePrinterParserArr;
            this.optional = z;
        }

        public CompositePrinterParser withOptional(boolean z) {
            if (z == this.optional) {
                return this;
            }
            return new CompositePrinterParser(this.printerParsers, z);
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (this.optional) {
                dateTimePrintContext.startOptional();
            }
            try {
                for (DateTimePrinterParser print : this.printerParsers) {
                    if (!print.print(dateTimePrintContext, sb)) {
                        sb.setLength(length);
                        return true;
                    }
                }
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
                return true;
            } finally {
                if (this.optional) {
                    dateTimePrintContext.endOptional();
                }
            }
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (this.optional) {
                dateTimeParseContext.startOptional();
                int i2 = i;
                for (DateTimePrinterParser parse : this.printerParsers) {
                    i2 = parse.parse(dateTimeParseContext, charSequence, i2);
                    if (i2 < 0) {
                        dateTimeParseContext.endOptional(false);
                        return i;
                    }
                }
                dateTimeParseContext.endOptional(true);
                return i2;
            }
            for (DateTimePrinterParser parse2 : this.printerParsers) {
                i = parse2.parse(dateTimeParseContext, charSequence, i);
                if (i < 0) {
                    break;
                }
            }
            return i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.printerParsers != null) {
                sb.append(this.optional ? "[" : "(");
                for (DateTimePrinterParser append : this.printerParsers) {
                    sb.append(append);
                }
                sb.append(this.optional ? "]" : ")");
            }
            return sb.toString();
        }
    }

    static final class PadPrinterParserDecorator implements DateTimePrinterParser {
        private final char padChar;
        private final int padWidth;
        private final DateTimePrinterParser printerParser;

        PadPrinterParserDecorator(DateTimePrinterParser dateTimePrinterParser, int i, char c) {
            this.printerParser = dateTimePrinterParser;
            this.padWidth = i;
            this.padChar = c;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            int length = sb.length();
            if (!this.printerParser.print(dateTimePrintContext, sb)) {
                return false;
            }
            int length2 = sb.length() - length;
            if (length2 <= this.padWidth) {
                for (int i = 0; i < this.padWidth - length2; i++) {
                    sb.insert(length, this.padChar);
                }
                return true;
            }
            throw new DateTimeException("Cannot print as output of " + length2 + " characters exceeds pad width of " + this.padWidth);
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            boolean isStrict = dateTimeParseContext.isStrict();
            boolean isCaseSensitive = dateTimeParseContext.isCaseSensitive();
            if (i > charSequence.length()) {
                throw new IndexOutOfBoundsException();
            } else if (i == charSequence.length()) {
                return ~i;
            } else {
                int i2 = this.padWidth + i;
                if (i2 > charSequence.length()) {
                    if (isStrict) {
                        return ~i;
                    }
                    i2 = charSequence.length();
                }
                int i3 = i;
                while (i3 < i2) {
                    if (!isCaseSensitive) {
                        if (!dateTimeParseContext.charEquals(charSequence.charAt(i3), this.padChar)) {
                            break;
                        }
                    } else if (charSequence.charAt(i3) != this.padChar) {
                        break;
                    }
                    i3++;
                }
                int parse = this.printerParser.parse(dateTimeParseContext, charSequence.subSequence(0, i2), i3);
                return (parse == i2 || !isStrict) ? parse : ~(i + i3);
            }
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("Pad(");
            sb.append(this.printerParser);
            sb.append(",");
            sb.append(this.padWidth);
            if (this.padChar == ' ') {
                str = ")";
            } else {
                str = ",'" + this.padChar + "')";
            }
            sb.append(str);
            return sb.toString();
        }
    }

    enum SettingsParser implements DateTimePrinterParser {
        SENSITIVE,
        INSENSITIVE,
        STRICT,
        LENIENT;

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int ordinal = ordinal();
            if (ordinal == 0) {
                dateTimeParseContext.setCaseSensitive(true);
            } else if (ordinal == 1) {
                dateTimeParseContext.setCaseSensitive(false);
            } else if (ordinal == 2) {
                dateTimeParseContext.setStrict(true);
            } else if (ordinal == 3) {
                dateTimeParseContext.setStrict(false);
            }
            return i;
        }

        public String toString() {
            int ordinal = ordinal();
            if (ordinal == 0) {
                return "ParseCaseSensitive(true)";
            }
            if (ordinal == 1) {
                return "ParseCaseSensitive(false)";
            }
            if (ordinal == 2) {
                return "ParseStrict(true)";
            }
            if (ordinal == 3) {
                return "ParseStrict(false)";
            }
            throw new IllegalStateException("Unreachable");
        }
    }

    static class DefaultingParser implements DateTimePrinterParser {
        private final TemporalField field;
        private final long value;

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return true;
        }

        DefaultingParser(TemporalField temporalField, long j) {
            this.field = temporalField;
            this.value = j;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (dateTimeParseContext.getParsed(this.field) == null) {
                dateTimeParseContext.setParsedField(this.field, this.value, i, i);
            }
            return i;
        }
    }

    static final class CharLiteralPrinterParser implements DateTimePrinterParser {
        private final char literal;

        CharLiteralPrinterParser(char c) {
            this.literal = c;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i == charSequence.length()) {
                return ~i;
            }
            return !dateTimeParseContext.charEquals(this.literal, charSequence.charAt(i)) ? ~i : i + 1;
        }

        public String toString() {
            if (this.literal == '\'') {
                return "''";
            }
            return "'" + this.literal + "'";
        }
    }

    static final class StringLiteralPrinterParser implements DateTimePrinterParser {
        private final String literal;

        StringLiteralPrinterParser(String str) {
            this.literal = str;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            sb.append(this.literal);
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i > charSequence.length() || i < 0) {
                throw new IndexOutOfBoundsException();
            }
            String str = this.literal;
            if (!dateTimeParseContext.subSequenceEquals(charSequence, i, str, 0, str.length())) {
                return ~i;
            }
            return i + this.literal.length();
        }

        public String toString() {
            String replace = this.literal.replace("'", "''");
            return "'" + replace + "'";
        }
    }

    static class NumberPrinterParser implements DateTimePrinterParser {
        static final int[] EXCEED_POINTS = {0, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, Http2Connection.DEGRADED_PONG_TIMEOUT_NS};
        final TemporalField field;
        final int maxWidth;
        final int minWidth;
        final SignStyle signStyle;
        final int subsequentWidth;

        /* access modifiers changed from: package-private */
        public long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            return j;
        }

        NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle2) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle2;
            this.subsequentWidth = 0;
        }

        private NumberPrinterParser(TemporalField temporalField, int i, int i2, SignStyle signStyle2, int i3) {
            this.field = temporalField;
            this.minWidth = i;
            this.maxWidth = i2;
            this.signStyle = signStyle2;
            this.subsequentWidth = i3;
        }

        /* access modifiers changed from: package-private */
        public NumberPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, -1);
        }

        /* access modifiers changed from: package-private */
        public NumberPrinterParser withSubsequentWidth(int i) {
            return new NumberPrinterParser(this.field, this.minWidth, this.maxWidth, this.signStyle, this.subsequentWidth + i);
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            String str;
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            long value2 = getValue(dateTimePrintContext, value.longValue());
            DecimalStyle symbols = dateTimePrintContext.getSymbols();
            if (value2 == Long.MIN_VALUE) {
                str = "9223372036854775808";
            } else {
                str = Long.toString(Math.abs(value2));
            }
            if (str.length() <= this.maxWidth) {
                String convertNumberToI18N = symbols.convertNumberToI18N(str);
                if (value2 >= 0) {
                    int i = AnonymousClass4.$SwitchMap$org$threeten$bp$format$SignStyle[this.signStyle.ordinal()];
                    if (i == 1) {
                        int i2 = this.minWidth;
                        if (i2 < 19 && value2 >= ((long) EXCEED_POINTS[i2])) {
                            sb.append(symbols.getPositiveSign());
                        }
                    } else if (i == 2) {
                        sb.append(symbols.getPositiveSign());
                    }
                } else {
                    int i3 = AnonymousClass4.$SwitchMap$org$threeten$bp$format$SignStyle[this.signStyle.ordinal()];
                    if (i3 == 1 || i3 == 2 || i3 == 3) {
                        sb.append(symbols.getNegativeSign());
                    } else if (i3 == 4) {
                        throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value2 + " cannot be negative according to the SignStyle");
                    }
                }
                for (int i4 = 0; i4 < this.minWidth - convertNumberToI18N.length(); i4++) {
                    sb.append(symbols.getZeroDigit());
                }
                sb.append(convertNumberToI18N);
                return true;
            }
            throw new DateTimeException("Field " + this.field + " cannot be printed as the value " + value2 + " exceeds the maximum print width of " + this.maxWidth);
        }

        /* access modifiers changed from: package-private */
        public boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            int i = this.subsequentWidth;
            return i == -1 || (i > 0 && this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:107:0x015d  */
        /* JADX WARNING: Removed duplicated region for block: B:112:0x017b  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parse(org.threeten.bp.format.DateTimeParseContext r20, java.lang.CharSequence r21, int r22) {
            /*
                r19 = this;
                r6 = r19
                r0 = r22
                int r1 = r21.length()
                if (r0 != r1) goto L_0x000c
                int r0 = ~r0
                return r0
            L_0x000c:
                char r2 = r21.charAt(r22)
                org.threeten.bp.format.DecimalStyle r3 = r20.getSymbols()
                char r3 = r3.getPositiveSign()
                r4 = 0
                r5 = 1
                if (r2 != r3) goto L_0x0039
                org.threeten.bp.format.SignStyle r2 = r6.signStyle
                boolean r3 = r20.isStrict()
                int r7 = r6.minWidth
                int r8 = r6.maxWidth
                if (r7 != r8) goto L_0x002a
                r7 = 1
                goto L_0x002b
            L_0x002a:
                r7 = 0
            L_0x002b:
                boolean r2 = r2.parse(r5, r3, r7)
                if (r2 != 0) goto L_0x0033
                int r0 = ~r0
                return r0
            L_0x0033:
                int r0 = r0 + 1
                r7 = r0
                r0 = 0
                r2 = 1
                goto L_0x0070
            L_0x0039:
                org.threeten.bp.format.DecimalStyle r3 = r20.getSymbols()
                char r3 = r3.getNegativeSign()
                if (r2 != r3) goto L_0x005f
                org.threeten.bp.format.SignStyle r2 = r6.signStyle
                boolean r3 = r20.isStrict()
                int r7 = r6.minWidth
                int r8 = r6.maxWidth
                if (r7 != r8) goto L_0x0051
                r7 = 1
                goto L_0x0052
            L_0x0051:
                r7 = 0
            L_0x0052:
                boolean r2 = r2.parse(r4, r3, r7)
                if (r2 != 0) goto L_0x005a
                int r0 = ~r0
                return r0
            L_0x005a:
                int r0 = r0 + 1
                r7 = r0
                r0 = 1
                goto L_0x006f
            L_0x005f:
                org.threeten.bp.format.SignStyle r2 = r6.signStyle
                org.threeten.bp.format.SignStyle r3 = org.threeten.bp.format.SignStyle.ALWAYS
                if (r2 != r3) goto L_0x006d
                boolean r2 = r20.isStrict()
                if (r2 == 0) goto L_0x006d
                int r0 = ~r0
                return r0
            L_0x006d:
                r7 = r0
                r0 = 0
            L_0x006f:
                r2 = 0
            L_0x0070:
                boolean r3 = r20.isStrict()
                if (r3 != 0) goto L_0x007f
                boolean r3 = r19.isFixedWidth(r20)
                if (r3 == 0) goto L_0x007d
                goto L_0x007f
            L_0x007d:
                r3 = 1
                goto L_0x0081
            L_0x007f:
                int r3 = r6.minWidth
            L_0x0081:
                int r8 = r7 + r3
                if (r8 <= r1) goto L_0x0087
                int r0 = ~r7
                return r0
            L_0x0087:
                boolean r9 = r20.isStrict()
                if (r9 != 0) goto L_0x0097
                boolean r9 = r19.isFixedWidth(r20)
                if (r9 == 0) goto L_0x0094
                goto L_0x0097
            L_0x0094:
                r9 = 9
                goto L_0x0099
            L_0x0097:
                int r9 = r6.maxWidth
            L_0x0099:
                int r10 = r6.subsequentWidth
                int r10 = java.lang.Math.max(r10, r4)
                int r9 = r9 + r10
            L_0x00a0:
                r10 = 0
                r11 = 2
                if (r4 >= r11) goto L_0x010b
                int r9 = r9 + r7
                int r9 = java.lang.Math.min(r9, r1)
                r11 = r7
                r14 = 0
            L_0x00ac:
                if (r11 >= r9) goto L_0x00f5
                int r16 = r11 + 1
                r12 = r21
                char r11 = r12.charAt(r11)
                org.threeten.bp.format.DecimalStyle r13 = r20.getSymbols()
                int r11 = r13.convertToDigit(r11)
                if (r11 >= 0) goto L_0x00c6
                int r11 = r16 + -1
                if (r11 >= r8) goto L_0x00f7
                int r0 = ~r7
                return r0
            L_0x00c6:
                int r13 = r16 - r7
                r5 = 18
                if (r13 <= r5) goto L_0x00e5
                if (r10 != 0) goto L_0x00d2
                java.math.BigInteger r10 = java.math.BigInteger.valueOf(r14)
            L_0x00d2:
                java.math.BigInteger r5 = java.math.BigInteger.TEN
                java.math.BigInteger r5 = r10.multiply(r5)
                long r10 = (long) r11
                java.math.BigInteger r10 = java.math.BigInteger.valueOf(r10)
                java.math.BigInteger r10 = r5.add(r10)
                r5 = r8
                r22 = r9
                goto L_0x00ee
            L_0x00e5:
                r17 = 10
                long r14 = r14 * r17
                r5 = r8
                r22 = r9
                long r8 = (long) r11
                long r14 = r14 + r8
            L_0x00ee:
                r9 = r22
                r8 = r5
                r11 = r16
                r5 = 1
                goto L_0x00ac
            L_0x00f5:
                r12 = r21
            L_0x00f7:
                r5 = r8
                int r8 = r6.subsequentWidth
                if (r8 <= 0) goto L_0x0109
                if (r4 != 0) goto L_0x0109
                int r11 = r11 - r7
                int r11 = r11 - r8
                int r9 = java.lang.Math.max(r3, r11)
                int r4 = r4 + 1
                r8 = r5
                r5 = 1
                goto L_0x00a0
            L_0x0109:
                r5 = r11
                goto L_0x010e
            L_0x010b:
                r5 = r7
                r14 = 0
            L_0x010e:
                if (r0 == 0) goto L_0x013c
                if (r10 == 0) goto L_0x0129
                java.math.BigInteger r0 = java.math.BigInteger.ZERO
                boolean r0 = r10.equals(r0)
                if (r0 == 0) goto L_0x0124
                boolean r0 = r20.isStrict()
                if (r0 == 0) goto L_0x0124
                r0 = 1
                int r7 = r7 - r0
                int r0 = ~r7
                return r0
            L_0x0124:
                java.math.BigInteger r10 = r10.negate()
                goto L_0x015a
            L_0x0129:
                r0 = 1
                r1 = 0
                int r3 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
                if (r3 != 0) goto L_0x0139
                boolean r1 = r20.isStrict()
                if (r1 == 0) goto L_0x0139
                int r7 = r7 - r0
                int r0 = ~r7
                return r0
            L_0x0139:
                long r0 = -r14
                r2 = r0
                goto L_0x015b
            L_0x013c:
                org.threeten.bp.format.SignStyle r0 = r6.signStyle
                org.threeten.bp.format.SignStyle r1 = org.threeten.bp.format.SignStyle.EXCEEDS_PAD
                if (r0 != r1) goto L_0x015a
                boolean r0 = r20.isStrict()
                if (r0 == 0) goto L_0x015a
                int r0 = r5 - r7
                if (r2 == 0) goto L_0x0154
                int r1 = r6.minWidth
                if (r0 > r1) goto L_0x015a
                r0 = 1
                int r7 = r7 - r0
                int r0 = ~r7
                return r0
            L_0x0154:
                int r1 = r6.minWidth
                if (r0 <= r1) goto L_0x015a
                int r0 = ~r7
                return r0
            L_0x015a:
                r2 = r14
            L_0x015b:
                if (r10 == 0) goto L_0x017b
                int r0 = r10.bitLength()
                r1 = 63
                if (r0 <= r1) goto L_0x016d
                java.math.BigInteger r0 = java.math.BigInteger.TEN
                java.math.BigInteger r10 = r10.divide(r0)
                int r5 = r5 + -1
            L_0x016d:
                long r2 = r10.longValue()
                r0 = r19
                r1 = r20
                r4 = r7
                int r0 = r0.setValue(r1, r2, r4, r5)
                return r0
            L_0x017b:
                r0 = r19
                r1 = r20
                r4 = r7
                int r0 = r0.setValue(r1, r2, r4, r5)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.NumberPrinterParser.parse(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        /* access modifiers changed from: package-private */
        public int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        public String toString() {
            if (this.minWidth == 1 && this.maxWidth == 19 && this.signStyle == SignStyle.NORMAL) {
                return "Value(" + this.field + ")";
            } else if (this.minWidth == this.maxWidth && this.signStyle == SignStyle.NOT_NEGATIVE) {
                return "Value(" + this.field + "," + this.minWidth + ")";
            } else {
                return "Value(" + this.field + "," + this.minWidth + "," + this.maxWidth + "," + this.signStyle + ")";
            }
        }
    }

    /* renamed from: org.threeten.bp.format.DateTimeFormatterBuilder$4  reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$format$SignStyle;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.threeten.bp.format.SignStyle[] r0 = org.threeten.bp.format.SignStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$threeten$bp$format$SignStyle = r0
                org.threeten.bp.format.SignStyle r1 = org.threeten.bp.format.SignStyle.EXCEEDS_PAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$threeten$bp$format$SignStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                org.threeten.bp.format.SignStyle r1 = org.threeten.bp.format.SignStyle.ALWAYS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$threeten$bp$format$SignStyle     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.threeten.bp.format.SignStyle r1 = org.threeten.bp.format.SignStyle.NORMAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$threeten$bp$format$SignStyle     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.threeten.bp.format.SignStyle r1 = org.threeten.bp.format.SignStyle.NOT_NEGATIVE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.AnonymousClass4.<clinit>():void");
        }
    }

    static final class ReducedPrinterParser extends NumberPrinterParser {
        static final LocalDate BASE_DATE = LocalDate.of(2000, 1, 1);
        private final ChronoLocalDate baseDate;
        private final int baseValue;

        ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE);
            if (i < 1 || i > 10) {
                throw new IllegalArgumentException("The width must be from 1 to 10 inclusive but was " + i);
            } else if (i2 < 1 || i2 > 10) {
                throw new IllegalArgumentException("The maxWidth must be from 1 to 10 inclusive but was " + i2);
            } else if (i2 >= i) {
                if (chronoLocalDate == null) {
                    long j = (long) i3;
                    if (!temporalField.range().isValidValue(j)) {
                        throw new IllegalArgumentException("The base value must be within the range of the field");
                    } else if (j + ((long) EXCEED_POINTS[i]) > 2147483647L) {
                        throw new DateTimeException("Unable to add printer-parser as the range exceeds the capacity of an int");
                    }
                }
                this.baseValue = i3;
                this.baseDate = chronoLocalDate;
            } else {
                throw new IllegalArgumentException("The maxWidth must be greater than the width");
            }
        }

        private ReducedPrinterParser(TemporalField temporalField, int i, int i2, int i3, ChronoLocalDate chronoLocalDate, int i4) {
            super(temporalField, i, i2, SignStyle.NOT_NEGATIVE, i4);
            this.baseValue = i3;
            this.baseDate = chronoLocalDate;
        }

        /* access modifiers changed from: package-private */
        public long getValue(DateTimePrintContext dateTimePrintContext, long j) {
            long abs = Math.abs(j);
            int i = this.baseValue;
            if (this.baseDate != null) {
                i = Chronology.from(dateTimePrintContext.getTemporal()).date(this.baseDate).get(this.field);
            }
            if (j < ((long) i) || j >= ((long) (i + EXCEED_POINTS[this.minWidth]))) {
                return abs % ((long) EXCEED_POINTS[this.maxWidth]);
            }
            return abs % ((long) EXCEED_POINTS[this.minWidth]);
        }

        /* access modifiers changed from: package-private */
        public int setValue(DateTimeParseContext dateTimeParseContext, long j, int i, int i2) {
            int i3 = this.baseValue;
            if (this.baseDate != null) {
                i3 = dateTimeParseContext.getEffectiveChronology().date(this.baseDate).get(this.field);
                dateTimeParseContext.addChronologyChangedParser(this, j, i, i2);
            }
            if (i2 - i == this.minWidth && j >= 0) {
                long j2 = (long) EXCEED_POINTS[this.minWidth];
                long j3 = (long) i3;
                long j4 = j3 - (j3 % j2);
                j = i3 > 0 ? j4 + j : j4 - j;
                if (j < j3) {
                    j += j2;
                }
            }
            return dateTimeParseContext.setParsedField(this.field, j, i, i2);
        }

        /* access modifiers changed from: package-private */
        public NumberPrinterParser withFixedWidth() {
            if (this.subsequentWidth == -1) {
                return this;
            }
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, -1);
        }

        /* access modifiers changed from: package-private */
        public ReducedPrinterParser withSubsequentWidth(int i) {
            return new ReducedPrinterParser(this.field, this.minWidth, this.maxWidth, this.baseValue, this.baseDate, this.subsequentWidth + i);
        }

        /* access modifiers changed from: package-private */
        public boolean isFixedWidth(DateTimeParseContext dateTimeParseContext) {
            if (!dateTimeParseContext.isStrict()) {
                return false;
            }
            return super.isFixedWidth(dateTimeParseContext);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ReducedValue(");
            sb.append(this.field);
            sb.append(",");
            sb.append(this.minWidth);
            sb.append(",");
            sb.append(this.maxWidth);
            sb.append(",");
            Object obj = this.baseDate;
            if (obj == null) {
                obj = Integer.valueOf(this.baseValue);
            }
            sb.append(obj);
            sb.append(")");
            return sb.toString();
        }
    }

    static final class FractionPrinterParser implements DateTimePrinterParser {
        private final boolean decimalPoint;
        private final TemporalField field;
        private final int maxWidth;
        private final int minWidth;

        FractionPrinterParser(TemporalField temporalField, int i, int i2, boolean z) {
            Jdk8Methods.requireNonNull(temporalField, "field");
            if (!temporalField.range().isFixed()) {
                throw new IllegalArgumentException("Field must have a fixed set of values: " + temporalField);
            } else if (i < 0 || i > 9) {
                throw new IllegalArgumentException("Minimum width must be from 0 to 9 inclusive but was " + i);
            } else if (i2 < 1 || i2 > 9) {
                throw new IllegalArgumentException("Maximum width must be from 1 to 9 inclusive but was " + i2);
            } else if (i2 >= i) {
                this.field = temporalField;
                this.minWidth = i;
                this.maxWidth = i2;
                this.decimalPoint = z;
            } else {
                throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + i2 + " < " + i);
            }
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            DecimalStyle symbols = dateTimePrintContext.getSymbols();
            BigDecimal convertToFraction = convertToFraction(value.longValue());
            if (convertToFraction.scale() != 0) {
                String convertNumberToI18N = symbols.convertNumberToI18N(convertToFraction.setScale(Math.min(Math.max(convertToFraction.scale(), this.minWidth), this.maxWidth), RoundingMode.FLOOR).toPlainString().substring(2));
                if (this.decimalPoint) {
                    sb.append(symbols.getDecimalSeparator());
                }
                sb.append(convertNumberToI18N);
                return true;
            } else if (this.minWidth <= 0) {
                return true;
            } else {
                if (this.decimalPoint) {
                    sb.append(symbols.getDecimalSeparator());
                }
                for (int i = 0; i < this.minWidth; i++) {
                    sb.append(symbols.getZeroDigit());
                }
                return true;
            }
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int i3 = 0;
            int i4 = dateTimeParseContext.isStrict() ? this.minWidth : 0;
            int i5 = dateTimeParseContext.isStrict() ? this.maxWidth : 9;
            int length = charSequence.length();
            if (i == length) {
                return i4 > 0 ? ~i : i;
            }
            if (this.decimalPoint) {
                if (charSequence.charAt(i) != dateTimeParseContext.getSymbols().getDecimalSeparator()) {
                    return i4 > 0 ? ~i : i;
                }
                i++;
            }
            int i6 = i;
            int i7 = i4 + i6;
            if (i7 > length) {
                return ~i6;
            }
            int min = Math.min(i5 + i6, length);
            int i8 = i6;
            while (true) {
                if (i8 >= min) {
                    i2 = i8;
                    break;
                }
                int i9 = i8 + 1;
                int convertToDigit = dateTimeParseContext.getSymbols().convertToDigit(charSequence.charAt(i8));
                if (convertToDigit >= 0) {
                    i3 = (i3 * 10) + convertToDigit;
                    i8 = i9;
                } else if (i9 < i7) {
                    return ~i6;
                } else {
                    i2 = i9 - 1;
                }
            }
            return dateTimeParseContext.setParsedField(this.field, convertFromFraction(new BigDecimal(i3).movePointLeft(i2 - i6)), i6, i2);
        }

        private BigDecimal convertToFraction(long j) {
            ValueRange range = this.field.range();
            range.checkValidValue(j, this.field);
            BigDecimal valueOf = BigDecimal.valueOf(range.getMinimum());
            BigDecimal divide = BigDecimal.valueOf(j).subtract(valueOf).divide(BigDecimal.valueOf(range.getMaximum()).subtract(valueOf).add(BigDecimal.ONE), 9, RoundingMode.FLOOR);
            return divide.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : divide.stripTrailingZeros();
        }

        private long convertFromFraction(BigDecimal bigDecimal) {
            ValueRange range = this.field.range();
            BigDecimal valueOf = BigDecimal.valueOf(range.getMinimum());
            return bigDecimal.multiply(BigDecimal.valueOf(range.getMaximum()).subtract(valueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(valueOf).longValueExact();
        }

        public String toString() {
            String str = this.decimalPoint ? ",DecimalPoint" : "";
            return "Fraction(" + this.field + "," + this.minWidth + "," + this.maxWidth + str + ")";
        }
    }

    static final class TextPrinterParser implements DateTimePrinterParser {
        private final TemporalField field;
        private volatile NumberPrinterParser numberPrinterParser;
        private final DateTimeTextProvider provider;
        private final TextStyle textStyle;

        TextPrinterParser(TemporalField temporalField, TextStyle textStyle2, DateTimeTextProvider dateTimeTextProvider) {
            this.field = temporalField;
            this.textStyle = textStyle2;
            this.provider = dateTimeTextProvider;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue(this.field);
            if (value == null) {
                return false;
            }
            String text = this.provider.getText(this.field, value.longValue(), this.textStyle, dateTimePrintContext.getLocale());
            if (text == null) {
                return numberPrinterParser().print(dateTimePrintContext, sb);
            }
            sb.append(text);
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int length = charSequence.length();
            if (i < 0 || i > length) {
                throw new IndexOutOfBoundsException();
            }
            Iterator<Map.Entry<String, Long>> textIterator = this.provider.getTextIterator(this.field, dateTimeParseContext.isStrict() ? this.textStyle : null, dateTimeParseContext.getLocale());
            if (textIterator != null) {
                while (textIterator.hasNext()) {
                    Map.Entry next = textIterator.next();
                    String str = (String) next.getKey();
                    if (dateTimeParseContext.subSequenceEquals(str, 0, charSequence, i, str.length())) {
                        return dateTimeParseContext.setParsedField(this.field, ((Long) next.getValue()).longValue(), i, i + str.length());
                    }
                }
                if (dateTimeParseContext.isStrict()) {
                    return ~i;
                }
            }
            return numberPrinterParser().parse(dateTimeParseContext, charSequence, i);
        }

        private NumberPrinterParser numberPrinterParser() {
            if (this.numberPrinterParser == null) {
                this.numberPrinterParser = new NumberPrinterParser(this.field, 1, 19, SignStyle.NORMAL);
            }
            return this.numberPrinterParser;
        }

        public String toString() {
            if (this.textStyle == TextStyle.FULL) {
                return "Text(" + this.field + ")";
            }
            return "Text(" + this.field + "," + this.textStyle + ")";
        }
    }

    static final class InstantPrinterParser implements DateTimePrinterParser {
        private static final long SECONDS_0000_TO_1970 = 62167219200L;
        private static final long SECONDS_PER_10000_YEARS = 315569520000L;
        private final int fractionalDigits;

        public String toString() {
            return "Instant()";
        }

        InstantPrinterParser(int i) {
            this.fractionalDigits = i;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            StringBuilder sb2 = sb;
            Long value = dateTimePrintContext.getValue((TemporalField) ChronoField.INSTANT_SECONDS);
            Long l = 0L;
            if (dateTimePrintContext.getTemporal().isSupported(ChronoField.NANO_OF_SECOND)) {
                l = Long.valueOf(dateTimePrintContext.getTemporal().getLong(ChronoField.NANO_OF_SECOND));
            }
            int i = 0;
            if (value == null) {
                return false;
            }
            long longValue = value.longValue();
            int checkValidIntValue = ChronoField.NANO_OF_SECOND.checkValidIntValue(l.longValue());
            if (longValue >= -62167219200L) {
                long j = (longValue - SECONDS_PER_10000_YEARS) + SECONDS_0000_TO_1970;
                long floorDiv = Jdk8Methods.floorDiv(j, (long) SECONDS_PER_10000_YEARS) + 1;
                LocalDateTime ofEpochSecond = LocalDateTime.ofEpochSecond(Jdk8Methods.floorMod(j, (long) SECONDS_PER_10000_YEARS) - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                if (floorDiv > 0) {
                    sb2.append(SignatureVisitor.EXTENDS);
                    sb2.append(floorDiv);
                }
                sb2.append(ofEpochSecond);
                if (ofEpochSecond.getSecond() == 0) {
                    sb2.append(":00");
                }
            } else {
                long j2 = longValue + SECONDS_0000_TO_1970;
                long j3 = j2 / SECONDS_PER_10000_YEARS;
                long j4 = j2 % SECONDS_PER_10000_YEARS;
                LocalDateTime ofEpochSecond2 = LocalDateTime.ofEpochSecond(j4 - SECONDS_0000_TO_1970, 0, ZoneOffset.UTC);
                int length = sb.length();
                sb2.append(ofEpochSecond2);
                if (ofEpochSecond2.getSecond() == 0) {
                    sb2.append(":00");
                }
                if (j3 < 0) {
                    if (ofEpochSecond2.getYear() == -10000) {
                        sb2.replace(length, length + 2, Long.toString(j3 - 1));
                    } else if (j4 == 0) {
                        sb2.insert(length, j3);
                    } else {
                        sb2.insert(length + 1, Math.abs(j3));
                    }
                }
            }
            int i2 = this.fractionalDigits;
            if (i2 == -2) {
                if (checkValidIntValue != 0) {
                    sb2.append('.');
                    if (checkValidIntValue % 1000000 == 0) {
                        sb2.append(Integer.toString((checkValidIntValue / 1000000) + 1000).substring(1));
                    } else if (checkValidIntValue % 1000 == 0) {
                        sb2.append(Integer.toString((checkValidIntValue / 1000) + 1000000).substring(1));
                    } else {
                        sb2.append(Integer.toString(checkValidIntValue + Http2Connection.DEGRADED_PONG_TIMEOUT_NS).substring(1));
                    }
                }
            } else if (i2 > 0 || (i2 == -1 && checkValidIntValue > 0)) {
                sb2.append('.');
                int i3 = 100000000;
                while (true) {
                    if ((this.fractionalDigits != -1 || checkValidIntValue <= 0) && i >= this.fractionalDigits) {
                        break;
                    }
                    int i4 = checkValidIntValue / i3;
                    sb2.append((char) (i4 + 48));
                    checkValidIntValue -= i4 * i3;
                    i3 /= 10;
                    i++;
                }
            }
            sb2.append(ASCIIPropertyListParser.DATE_APPLE_END_TOKEN);
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int i3 = i;
            DateTimeParseContext copy = dateTimeParseContext.copy();
            int i4 = this.fractionalDigits;
            int i5 = 0;
            if (i4 < 0) {
                i4 = 0;
            }
            int i6 = this.fractionalDigits;
            if (i6 < 0) {
                i6 = 9;
            }
            int parse = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral((char) ASCIIPropertyListParser.DATE_APPLE_DATE_TIME_DELIMITER).appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER).appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER).appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendFraction(ChronoField.NANO_OF_SECOND, i4, i6, true).appendLiteral((char) ASCIIPropertyListParser.DATE_APPLE_END_TOKEN).toFormatter().toPrinterParser(false).parse(copy, charSequence, i3);
            if (parse < 0) {
                return parse;
            }
            long longValue = copy.getParsed(ChronoField.YEAR).longValue();
            int intValue = copy.getParsed(ChronoField.MONTH_OF_YEAR).intValue();
            int intValue2 = copy.getParsed(ChronoField.DAY_OF_MONTH).intValue();
            int intValue3 = copy.getParsed(ChronoField.HOUR_OF_DAY).intValue();
            int intValue4 = copy.getParsed(ChronoField.MINUTE_OF_HOUR).intValue();
            Long parsed = copy.getParsed(ChronoField.SECOND_OF_MINUTE);
            Long parsed2 = copy.getParsed(ChronoField.NANO_OF_SECOND);
            int intValue5 = parsed != null ? parsed.intValue() : 0;
            int intValue6 = parsed2 != null ? parsed2.intValue() : 0;
            int i7 = ((int) longValue) % 10000;
            if (intValue3 == 24 && intValue4 == 0 && intValue5 == 0 && intValue6 == 0) {
                i2 = intValue5;
                intValue3 = 0;
                i5 = 1;
            } else if (intValue3 == 23 && intValue4 == 59 && intValue5 == 60) {
                dateTimeParseContext.setParsedLeapSecond();
                i2 = 59;
            } else {
                i2 = intValue5;
            }
            try {
                int i8 = intValue6;
                DateTimeParseContext dateTimeParseContext2 = dateTimeParseContext;
                int i9 = i;
                return dateTimeParseContext2.setParsedField(ChronoField.NANO_OF_SECOND, (long) i8, i9, dateTimeParseContext2.setParsedField(ChronoField.INSTANT_SECONDS, LocalDateTime.of(i7, intValue, intValue2, intValue3, intValue4, i2, 0).plusDays((long) i5).toEpochSecond(ZoneOffset.UTC) + Jdk8Methods.safeMultiply(longValue / 10000, (long) SECONDS_PER_10000_YEARS), i9, parse));
            } catch (RuntimeException unused) {
                return ~i3;
            }
        }
    }

    static final class OffsetIdPrinterParser implements DateTimePrinterParser {
        static final OffsetIdPrinterParser INSTANCE_ID = new OffsetIdPrinterParser("Z", "+HH:MM:ss");
        static final String[] PATTERNS = {"+HH", "+HHmm", "+HH:mm", "+HHMM", "+HH:MM", "+HHMMss", "+HH:MM:ss", "+HHMMSS", "+HH:MM:SS"};
        private final String noOffsetText;
        private final int type;

        OffsetIdPrinterParser(String str, String str2) {
            Jdk8Methods.requireNonNull(str, "noOffsetText");
            Jdk8Methods.requireNonNull(str2, "pattern");
            this.noOffsetText = str;
            this.type = checkPattern(str2);
        }

        private int checkPattern(String str) {
            int i = 0;
            while (true) {
                String[] strArr = PATTERNS;
                if (i >= strArr.length) {
                    throw new IllegalArgumentException("Invalid zone offset pattern: " + str);
                } else if (strArr[i].equals(str)) {
                    return i;
                } else {
                    i++;
                }
            }
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue((TemporalField) ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            int safeToInt = Jdk8Methods.safeToInt(value.longValue());
            if (safeToInt == 0) {
                sb.append(this.noOffsetText);
            } else {
                int abs = Math.abs((safeToInt / DateTimeConstants.SECONDS_PER_HOUR) % 100);
                int abs2 = Math.abs((safeToInt / 60) % 60);
                int abs3 = Math.abs(safeToInt % 60);
                int length = sb.length();
                sb.append(safeToInt < 0 ? "-" : "+");
                sb.append((char) ((abs / 10) + 48));
                sb.append((char) ((abs % 10) + 48));
                int i = this.type;
                if (i >= 3 || (i >= 1 && abs2 > 0)) {
                    String str = ":";
                    sb.append(this.type % 2 == 0 ? str : "");
                    sb.append((char) ((abs2 / 10) + 48));
                    sb.append((char) ((abs2 % 10) + 48));
                    abs += abs2;
                    int i2 = this.type;
                    if (i2 >= 7 || (i2 >= 5 && abs3 > 0)) {
                        if (this.type % 2 != 0) {
                            str = "";
                        }
                        sb.append(str);
                        sb.append((char) ((abs3 / 10) + 48));
                        sb.append((char) ((abs3 % 10) + 48));
                        abs += abs3;
                    }
                }
                if (abs == 0) {
                    sb.setLength(length);
                    sb.append(this.noOffsetText);
                }
            }
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:32:0x0080  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parse(org.threeten.bp.format.DateTimeParseContext r16, java.lang.CharSequence r17, int r18) {
            /*
                r15 = this;
                r0 = r15
                r7 = r17
                r8 = r18
                int r1 = r17.length()
                java.lang.String r2 = r0.noOffsetText
                int r9 = r2.length()
                if (r9 != 0) goto L_0x0022
                if (r8 != r1) goto L_0x0045
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                r1 = r16
                r5 = r18
                r6 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L_0x0022:
                if (r8 != r1) goto L_0x0026
                int r1 = ~r8
                return r1
            L_0x0026:
                java.lang.String r4 = r0.noOffsetText
                r5 = 0
                r1 = r16
                r2 = r17
                r3 = r18
                r6 = r9
                boolean r1 = r1.subSequenceEquals(r2, r3, r4, r5, r6)
                if (r1 == 0) goto L_0x0045
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r16
                r5 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L_0x0045:
                char r1 = r17.charAt(r18)
                r2 = 43
                r3 = 45
                if (r1 == r2) goto L_0x0051
                if (r1 != r3) goto L_0x00a5
            L_0x0051:
                r2 = 1
                if (r1 != r3) goto L_0x0056
                r1 = -1
                goto L_0x0057
            L_0x0056:
                r1 = 1
            L_0x0057:
                r3 = 4
                int[] r3 = new int[r3]
                int r4 = r8 + 1
                r5 = 0
                r3[r5] = r4
                boolean r4 = r15.parseNumber(r3, r2, r7, r2)
                r6 = 2
                r10 = 3
                if (r4 != 0) goto L_0x007d
                int r4 = r0.type
                if (r4 < r10) goto L_0x006d
                r4 = 1
                goto L_0x006e
            L_0x006d:
                r4 = 0
            L_0x006e:
                boolean r4 = r15.parseNumber(r3, r6, r7, r4)
                if (r4 != 0) goto L_0x007d
                boolean r4 = r15.parseNumber(r3, r10, r7, r5)
                if (r4 == 0) goto L_0x007b
                goto L_0x007d
            L_0x007b:
                r4 = 0
                goto L_0x007e
            L_0x007d:
                r4 = 1
            L_0x007e:
                if (r4 != 0) goto L_0x00a5
                long r11 = (long) r1
                r1 = r3[r2]
                long r1 = (long) r1
                r13 = 3600(0xe10, double:1.7786E-320)
                long r1 = r1 * r13
                r4 = r3[r6]
                long r6 = (long) r4
                r13 = 60
                long r6 = r6 * r13
                long r1 = r1 + r6
                r4 = r3[r10]
                long r6 = (long) r4
                long r1 = r1 + r6
                long r6 = r11 * r1
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r9 = r3[r5]
                r1 = r16
                r3 = r6
                r5 = r18
                r6 = r9
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L_0x00a5:
                if (r9 != 0) goto L_0x00b6
                org.threeten.bp.temporal.ChronoField r2 = org.threeten.bp.temporal.ChronoField.OFFSET_SECONDS
                r3 = 0
                int r6 = r8 + r9
                r1 = r16
                r5 = r18
                int r1 = r1.setParsedField(r2, r3, r5, r6)
                return r1
            L_0x00b6:
                int r1 = ~r8
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.format.DateTimeFormatterBuilder.OffsetIdPrinterParser.parse(org.threeten.bp.format.DateTimeParseContext, java.lang.CharSequence, int):int");
        }

        private boolean parseNumber(int[] iArr, int i, CharSequence charSequence, boolean z) {
            int i2;
            int i3 = this.type;
            if ((i3 + 3) / 2 < i) {
                return false;
            }
            int i4 = iArr[0];
            if (i3 % 2 == 0 && i > 1) {
                int i5 = i4 + 1;
                if (i5 > charSequence.length() || charSequence.charAt(i4) != ':') {
                    return z;
                }
                i4 = i5;
            }
            if (i4 + 2 > charSequence.length()) {
                return z;
            }
            int i6 = i4 + 1;
            char charAt = charSequence.charAt(i4);
            int i7 = i6 + 1;
            char charAt2 = charSequence.charAt(i6);
            if (charAt < '0' || charAt > '9' || charAt2 < '0' || charAt2 > '9' || (i2 = ((charAt - '0') * 10) + (charAt2 - '0')) < 0 || i2 > 59) {
                return z;
            }
            iArr[i] = i2;
            iArr[0] = i7;
            return false;
        }

        public String toString() {
            String replace = this.noOffsetText.replace("'", "''");
            return "Offset(" + PATTERNS[this.type] + ",'" + replace + "')";
        }
    }

    static final class LocalizedOffsetPrinterParser implements DateTimePrinterParser {
        private final TextStyle style;

        public LocalizedOffsetPrinterParser(TextStyle textStyle) {
            this.style = textStyle;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Long value = dateTimePrintContext.getValue((TemporalField) ChronoField.OFFSET_SECONDS);
            if (value == null) {
                return false;
            }
            sb.append("GMT");
            if (this.style == TextStyle.FULL) {
                return new OffsetIdPrinterParser("", "+HH:MM:ss").print(dateTimePrintContext, sb);
            }
            int safeToInt = Jdk8Methods.safeToInt(value.longValue());
            if (safeToInt == 0) {
                return true;
            }
            int abs = Math.abs((safeToInt / DateTimeConstants.SECONDS_PER_HOUR) % 100);
            int abs2 = Math.abs((safeToInt / 60) % 60);
            int abs3 = Math.abs(safeToInt % 60);
            sb.append(safeToInt < 0 ? "-" : "+");
            sb.append(abs);
            if (abs2 <= 0 && abs3 <= 0) {
                return true;
            }
            sb.append(":");
            sb.append((char) ((abs2 / 10) + 48));
            sb.append((char) ((abs2 % 10) + 48));
            if (abs3 <= 0) {
                return true;
            }
            sb.append(":");
            sb.append((char) ((abs3 / 10) + 48));
            sb.append((char) ((abs3 % 10) + 48));
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            char charAt;
            CharSequence charSequence2 = charSequence;
            int i2 = i;
            if (!dateTimeParseContext.subSequenceEquals(charSequence, i, "GMT", 0, 3)) {
                return ~i2;
            }
            int i3 = i2 + 3;
            if (this.style == TextStyle.FULL) {
                DateTimeParseContext dateTimeParseContext2 = dateTimeParseContext;
                return new OffsetIdPrinterParser("", "+HH:MM:ss").parse(dateTimeParseContext, charSequence2, i3);
            }
            DateTimeParseContext dateTimeParseContext3 = dateTimeParseContext;
            int length = charSequence.length();
            if (i3 == length) {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0, i3, i3);
            }
            char charAt2 = charSequence2.charAt(i3);
            if (charAt2 != '+' && charAt2 != '-') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, 0, i3, i3);
            }
            int i4 = charAt2 == '-' ? -1 : 1;
            if (i3 == length) {
                return ~i3;
            }
            int i5 = i3 + 1;
            char charAt3 = charSequence2.charAt(i5);
            if (charAt3 < '0' || charAt3 > '9') {
                return ~i5;
            }
            int i6 = i5 + 1;
            int i7 = charAt3 - '0';
            if (i6 != length && (charAt = charSequence2.charAt(i6)) >= '0' && charAt <= '9') {
                i7 = (i7 * 10) + (charAt - '0');
                if (i7 > 23) {
                    return ~i6;
                }
                i6++;
            }
            int i8 = i6;
            if (i8 == length || charSequence2.charAt(i8) != ':') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, (long) (i4 * DateTimeConstants.SECONDS_PER_HOUR * i7), i8, i8);
            }
            int i9 = i8 + 1;
            int i10 = length - 2;
            if (i9 > i10) {
                return ~i9;
            }
            char charAt4 = charSequence2.charAt(i9);
            if (charAt4 < '0' || charAt4 > '9') {
                return ~i9;
            }
            int i11 = i9 + 1;
            int i12 = charAt4 - '0';
            char charAt5 = charSequence2.charAt(i11);
            if (charAt5 < '0' || charAt5 > '9') {
                return ~i11;
            }
            int i13 = i11 + 1;
            int i14 = (i12 * 10) + (charAt5 - '0');
            if (i14 > 59) {
                return ~i13;
            }
            if (i13 == length || charSequence2.charAt(i13) != ':') {
                return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, (long) (i4 * ((i7 * DateTimeConstants.SECONDS_PER_HOUR) + (i14 * 60))), i13, i13);
            }
            int i15 = i13 + 1;
            if (i15 > i10) {
                return ~i15;
            }
            char charAt6 = charSequence2.charAt(i15);
            if (charAt6 < '0' || charAt6 > '9') {
                return ~i15;
            }
            int i16 = i15 + 1;
            int i17 = charAt6 - '0';
            char charAt7 = charSequence2.charAt(i16);
            if (charAt7 < '0' || charAt7 > '9') {
                return ~i16;
            }
            int i18 = i16 + 1;
            int i19 = (i17 * 10) + (charAt7 - '0');
            if (i19 > 59) {
                return ~i18;
            }
            return dateTimeParseContext.setParsedField(ChronoField.OFFSET_SECONDS, (long) (i4 * ((i7 * DateTimeConstants.SECONDS_PER_HOUR) + (i14 * 60) + i19)), i18, i18);
        }
    }

    static final class ZoneTextPrinterParser implements DateTimePrinterParser {
        private static final Comparator<String> LENGTH_COMPARATOR = new Comparator<String>() {
            public int compare(String str, String str2) {
                int length = str2.length() - str.length();
                return length == 0 ? str.compareTo(str2) : length;
            }
        };
        private final TextStyle textStyle;

        ZoneTextPrinterParser(TextStyle textStyle2) {
            this.textStyle = (TextStyle) Jdk8Methods.requireNonNull(textStyle2, "textStyle");
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            boolean z;
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(TemporalQueries.zoneId());
            int i = 0;
            if (zoneId == null) {
                return false;
            }
            if (zoneId.normalized() instanceof ZoneOffset) {
                sb.append(zoneId.getId());
                return true;
            }
            TemporalAccessor temporal = dateTimePrintContext.getTemporal();
            if (temporal.isSupported(ChronoField.INSTANT_SECONDS)) {
                z = zoneId.getRules().isDaylightSavings(Instant.ofEpochSecond(temporal.getLong(ChronoField.INSTANT_SECONDS)));
            } else {
                z = false;
            }
            TimeZone timeZone = TimeZone.getTimeZone(zoneId.getId());
            if (this.textStyle.asNormal() == TextStyle.FULL) {
                i = 1;
            }
            sb.append(timeZone.getDisplayName(z, i, dateTimePrintContext.getLocale()));
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            TreeMap treeMap = new TreeMap(LENGTH_COMPARATOR);
            for (String next : ZoneId.getAvailableZoneIds()) {
                treeMap.put(next, next);
                TimeZone timeZone = TimeZone.getTimeZone(next);
                int i2 = this.textStyle.asNormal() == TextStyle.FULL ? 1 : 0;
                String displayName = timeZone.getDisplayName(false, i2, dateTimeParseContext.getLocale());
                if (next.startsWith("Etc/") || (!displayName.startsWith("GMT+") && !displayName.startsWith("GMT+"))) {
                    treeMap.put(displayName, next);
                }
                String displayName2 = timeZone.getDisplayName(true, i2, dateTimeParseContext.getLocale());
                if (next.startsWith("Etc/") || (!displayName2.startsWith("GMT+") && !displayName2.startsWith("GMT+"))) {
                    treeMap.put(displayName2, next);
                }
            }
            for (Map.Entry entry : treeMap.entrySet()) {
                String str = (String) entry.getKey();
                if (dateTimeParseContext.subSequenceEquals(charSequence, i, str, 0, str.length())) {
                    dateTimeParseContext.setParsed(ZoneId.of((String) entry.getValue()));
                    return i + str.length();
                }
            }
            return ~i;
        }

        public String toString() {
            return "ZoneText(" + this.textStyle + ")";
        }
    }

    static final class ZoneIdPrinterParser implements DateTimePrinterParser {
        private static volatile Map.Entry<Integer, SubstringTree> cachedSubstringTree;
        private final String description;
        private final TemporalQuery<ZoneId> query;

        ZoneIdPrinterParser(TemporalQuery<ZoneId> temporalQuery, String str) {
            this.query = temporalQuery;
            this.description = str;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            ZoneId zoneId = (ZoneId) dateTimePrintContext.getValue(this.query);
            if (zoneId == null) {
                return false;
            }
            sb.append(zoneId.getId());
            return true;
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            int i2;
            int length = charSequence.length();
            if (i > length) {
                throw new IndexOutOfBoundsException();
            } else if (i == length) {
                return ~i;
            } else {
                char charAt = charSequence.charAt(i);
                if (charAt == '+' || charAt == '-') {
                    DateTimeParseContext copy = dateTimeParseContext.copy();
                    int parse = OffsetIdPrinterParser.INSTANCE_ID.parse(copy, charSequence, i);
                    if (parse < 0) {
                        return parse;
                    }
                    dateTimeParseContext.setParsed((ZoneId) ZoneOffset.ofTotalSeconds((int) copy.getParsed(ChronoField.OFFSET_SECONDS).longValue()));
                    return parse;
                }
                int i3 = i + 2;
                if (length >= i3) {
                    char charAt2 = charSequence.charAt(i + 1);
                    if (dateTimeParseContext.charEquals(charAt, 'U') && dateTimeParseContext.charEquals(charAt2, ASCIIPropertyListParser.DATE_APPLE_DATE_TIME_DELIMITER)) {
                        int i4 = i + 3;
                        if (length < i4 || !dateTimeParseContext.charEquals(charSequence.charAt(i3), 'C')) {
                            return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i3);
                        }
                        return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i4);
                    } else if (dateTimeParseContext.charEquals(charAt, 'G') && length >= (i2 = i + 3) && dateTimeParseContext.charEquals(charAt2, 'M') && dateTimeParseContext.charEquals(charSequence.charAt(i3), ASCIIPropertyListParser.DATE_APPLE_DATE_TIME_DELIMITER)) {
                        return parsePrefixedOffset(dateTimeParseContext, charSequence, i, i2);
                    }
                }
                Set<String> availableZoneIds = ZoneRulesProvider.getAvailableZoneIds();
                int size = availableZoneIds.size();
                Map.Entry<Integer, SubstringTree> entry = cachedSubstringTree;
                if (entry == null || entry.getKey().intValue() != size) {
                    synchronized (this) {
                        entry = cachedSubstringTree;
                        if (entry == null || entry.getKey().intValue() != size) {
                            entry = new AbstractMap.SimpleImmutableEntry<>(Integer.valueOf(size), prepareParser(availableZoneIds));
                            cachedSubstringTree = entry;
                        }
                    }
                }
                SubstringTree value = entry.getValue();
                String str = null;
                String str2 = null;
                while (value != null) {
                    int i5 = value.length + i;
                    if (i5 > length) {
                        break;
                    }
                    String charSequence2 = charSequence.subSequence(i, i5).toString();
                    value = value.get(charSequence2, dateTimeParseContext.isCaseSensitive());
                    String str3 = charSequence2;
                    str2 = str;
                    str = str3;
                }
                ZoneId convertToZone = convertToZone(availableZoneIds, str, dateTimeParseContext.isCaseSensitive());
                if (convertToZone == null) {
                    convertToZone = convertToZone(availableZoneIds, str2, dateTimeParseContext.isCaseSensitive());
                    if (convertToZone != null) {
                        str = str2;
                    } else if (!dateTimeParseContext.charEquals(charAt, ASCIIPropertyListParser.DATE_APPLE_END_TOKEN)) {
                        return ~i;
                    } else {
                        dateTimeParseContext.setParsed((ZoneId) ZoneOffset.UTC);
                        return i + 1;
                    }
                }
                dateTimeParseContext.setParsed(convertToZone);
                return i + str.length();
            }
        }

        private ZoneId convertToZone(Set<String> set, String str, boolean z) {
            if (str == null) {
                return null;
            }
            if (!z) {
                for (String next : set) {
                    if (next.equalsIgnoreCase(str)) {
                        return ZoneId.of(next);
                    }
                }
                return null;
            } else if (set.contains(str)) {
                return ZoneId.of(str);
            } else {
                return null;
            }
        }

        private int parsePrefixedOffset(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i, int i2) {
            String upperCase = charSequence.subSequence(i, i2).toString().toUpperCase();
            DateTimeParseContext copy = dateTimeParseContext.copy();
            if (i2 >= charSequence.length() || !dateTimeParseContext.charEquals(charSequence.charAt(i2), ASCIIPropertyListParser.DATE_APPLE_END_TOKEN)) {
                int parse = OffsetIdPrinterParser.INSTANCE_ID.parse(copy, charSequence, i2);
                if (parse < 0) {
                    dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.UTC));
                    return i2;
                }
                dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.ofTotalSeconds((int) copy.getParsed(ChronoField.OFFSET_SECONDS).longValue())));
                return parse;
            }
            dateTimeParseContext.setParsed(ZoneId.ofOffset(upperCase, ZoneOffset.UTC));
            return i2;
        }

        private static final class SubstringTree {
            final int length;
            private final Map<CharSequence, SubstringTree> substringMap;
            private final Map<String, SubstringTree> substringMapCI;

            private SubstringTree(int i) {
                this.substringMap = new HashMap();
                this.substringMapCI = new HashMap();
                this.length = i;
            }

            /* access modifiers changed from: private */
            public SubstringTree get(CharSequence charSequence, boolean z) {
                if (z) {
                    return this.substringMap.get(charSequence);
                }
                return this.substringMapCI.get(charSequence.toString().toLowerCase(Locale.ENGLISH));
            }

            /* access modifiers changed from: private */
            public void add(String str) {
                int length2 = str.length();
                int i = this.length;
                if (length2 == i) {
                    this.substringMap.put(str, (Object) null);
                    this.substringMapCI.put(str.toLowerCase(Locale.ENGLISH), (Object) null);
                } else if (length2 > i) {
                    String substring = str.substring(0, i);
                    SubstringTree substringTree = this.substringMap.get(substring);
                    if (substringTree == null) {
                        substringTree = new SubstringTree(length2);
                        this.substringMap.put(substring, substringTree);
                        this.substringMapCI.put(substring.toLowerCase(Locale.ENGLISH), substringTree);
                    }
                    substringTree.add(str);
                }
            }
        }

        private static SubstringTree prepareParser(Set<String> set) {
            ArrayList<String> arrayList = new ArrayList<>(set);
            Collections.sort(arrayList, DateTimeFormatterBuilder.LENGTH_SORT);
            SubstringTree substringTree = new SubstringTree(((String) arrayList.get(0)).length());
            for (String access$300 : arrayList) {
                substringTree.add(access$300);
            }
            return substringTree;
        }

        public String toString() {
            return this.description;
        }
    }

    static final class ChronoPrinterParser implements DateTimePrinterParser {
        private final TextStyle textStyle;

        ChronoPrinterParser(TextStyle textStyle2) {
            this.textStyle = textStyle2;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            Chronology chronology = (Chronology) dateTimePrintContext.getValue(TemporalQueries.chronology());
            if (chronology == null) {
                return false;
            }
            if (this.textStyle == null) {
                sb.append(chronology.getId());
                return true;
            }
            try {
                sb.append(ResourceBundle.getBundle("org.threeten.bp.format.ChronologyText", dateTimePrintContext.getLocale(), DateTimeFormatterBuilder.class.getClassLoader()).getString(chronology.getId()));
                return true;
            } catch (MissingResourceException unused) {
                sb.append(chronology.getId());
                return true;
            }
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            if (i < 0 || i > charSequence.length()) {
                throw new IndexOutOfBoundsException();
            }
            Chronology chronology = null;
            int i2 = -1;
            for (Chronology next : Chronology.getAvailableChronologies()) {
                String id = next.getId();
                int length = id.length();
                if (length > i2 && dateTimeParseContext.subSequenceEquals(charSequence, i, id, 0, length)) {
                    chronology = next;
                    i2 = length;
                }
            }
            if (chronology == null) {
                return ~i;
            }
            dateTimeParseContext.setParsed(chronology);
            return i + i2;
        }
    }

    static final class LocalizedPrinterParser implements DateTimePrinterParser {
        private final FormatStyle dateStyle;
        private final FormatStyle timeStyle;

        LocalizedPrinterParser(FormatStyle formatStyle, FormatStyle formatStyle2) {
            this.dateStyle = formatStyle;
            this.timeStyle = formatStyle2;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return formatter(dateTimePrintContext.getLocale(), Chronology.from(dateTimePrintContext.getTemporal())).toPrinterParser(false).print(dateTimePrintContext, sb);
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return formatter(dateTimeParseContext.getLocale(), dateTimeParseContext.getEffectiveChronology()).toPrinterParser(false).parse(dateTimeParseContext, charSequence, i);
        }

        private DateTimeFormatter formatter(Locale locale, Chronology chronology) {
            return DateTimeFormatStyleProvider.getInstance().getFormatter(this.dateStyle, this.timeStyle, chronology, locale);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Localized(");
            Object obj = this.dateStyle;
            Object obj2 = "";
            if (obj == null) {
                obj = obj2;
            }
            sb.append(obj);
            sb.append(",");
            Object obj3 = this.timeStyle;
            if (obj3 != null) {
                obj2 = obj3;
            }
            sb.append(obj2);
            sb.append(")");
            return sb.toString();
        }
    }

    static final class WeekFieldsPrinterParser implements DateTimePrinterParser {
        private final int count;
        private final char letter;

        public WeekFieldsPrinterParser(char c, int i) {
            this.letter = c;
            this.count = i;
        }

        public boolean print(DateTimePrintContext dateTimePrintContext, StringBuilder sb) {
            return evaluate(WeekFields.of(dateTimePrintContext.getLocale())).print(dateTimePrintContext, sb);
        }

        public int parse(DateTimeParseContext dateTimeParseContext, CharSequence charSequence, int i) {
            return evaluate(WeekFields.of(dateTimeParseContext.getLocale())).parse(dateTimeParseContext, charSequence, i);
        }

        private DateTimePrinterParser evaluate(WeekFields weekFields) {
            SignStyle signStyle;
            char c = this.letter;
            if (c == 'W') {
                return new NumberPrinterParser(weekFields.weekOfMonth(), 1, 2, SignStyle.NOT_NEGATIVE);
            }
            if (c != 'Y') {
                if (c == 'c') {
                    return new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
                }
                if (c == 'e') {
                    return new NumberPrinterParser(weekFields.dayOfWeek(), this.count, 2, SignStyle.NOT_NEGATIVE);
                }
                if (c != 'w') {
                    return null;
                }
                return new NumberPrinterParser(weekFields.weekOfWeekBasedYear(), this.count, 2, SignStyle.NOT_NEGATIVE);
            } else if (this.count == 2) {
                return new ReducedPrinterParser(weekFields.weekBasedYear(), 2, 2, 0, ReducedPrinterParser.BASE_DATE);
            } else {
                TemporalField weekBasedYear = weekFields.weekBasedYear();
                int i = this.count;
                if (i < 4) {
                    signStyle = SignStyle.NORMAL;
                } else {
                    signStyle = SignStyle.EXCEEDS_PAD;
                }
                return new NumberPrinterParser(weekBasedYear, i, 19, signStyle, -1);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Localized(");
            char c = this.letter;
            if (c == 'Y') {
                int i = this.count;
                if (i == 1) {
                    sb.append("WeekBasedYear");
                } else if (i == 2) {
                    sb.append("ReducedValue(WeekBasedYear,2,2,2000-01-01)");
                } else {
                    sb.append("WeekBasedYear,");
                    sb.append(this.count);
                    sb.append(",");
                    sb.append(19);
                    sb.append(",");
                    sb.append(this.count < 4 ? SignStyle.NORMAL : SignStyle.EXCEEDS_PAD);
                }
            } else {
                if (c == 'c' || c == 'e') {
                    sb.append("DayOfWeek");
                } else if (c == 'w') {
                    sb.append("WeekOfWeekBasedYear");
                } else if (c == 'W') {
                    sb.append("WeekOfMonth");
                }
                sb.append(",");
                sb.append(this.count);
            }
            sb.append(")");
            return sb.toString();
        }
    }
}
