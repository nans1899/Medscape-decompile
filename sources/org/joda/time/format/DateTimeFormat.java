package org.joda.time.format;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

public class DateTimeFormat {
    static final int DATE = 0;
    static final int DATETIME = 2;
    static final int FULL = 0;
    static final int LONG = 1;
    static final int MEDIUM = 2;
    static final int NONE = 4;
    private static final int PATTERN_CACHE_SIZE = 500;
    static final int SHORT = 3;
    static final int TIME = 1;
    private static final ConcurrentHashMap<String, DateTimeFormatter> cPatternCache = new ConcurrentHashMap<>();
    private static final AtomicReferenceArray<DateTimeFormatter> cStyleCache = new AtomicReferenceArray<>(25);

    public static DateTimeFormatter forPattern(String str) {
        return createFormatterForPattern(str);
    }

    public static DateTimeFormatter forStyle(String str) {
        return createFormatterForStyle(str);
    }

    public static String patternForStyle(String str, Locale locale) {
        DateTimeFormatter createFormatterForStyle = createFormatterForStyle(str);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return ((StyleFormatter) createFormatterForStyle.getPrinter0()).getPattern(locale);
    }

    public static DateTimeFormatter shortDate() {
        return createFormatterForStyleIndex(3, 4);
    }

    public static DateTimeFormatter shortTime() {
        return createFormatterForStyleIndex(4, 3);
    }

    public static DateTimeFormatter shortDateTime() {
        return createFormatterForStyleIndex(3, 3);
    }

    public static DateTimeFormatter mediumDate() {
        return createFormatterForStyleIndex(2, 4);
    }

    public static DateTimeFormatter mediumTime() {
        return createFormatterForStyleIndex(4, 2);
    }

    public static DateTimeFormatter mediumDateTime() {
        return createFormatterForStyleIndex(2, 2);
    }

    public static DateTimeFormatter longDate() {
        return createFormatterForStyleIndex(1, 4);
    }

    public static DateTimeFormatter longTime() {
        return createFormatterForStyleIndex(4, 1);
    }

    public static DateTimeFormatter longDateTime() {
        return createFormatterForStyleIndex(1, 1);
    }

    public static DateTimeFormatter fullDate() {
        return createFormatterForStyleIndex(0, 4);
    }

    public static DateTimeFormatter fullTime() {
        return createFormatterForStyleIndex(4, 0);
    }

    public static DateTimeFormatter fullDateTime() {
        return createFormatterForStyleIndex(0, 0);
    }

    static void appendPatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        parsePatternTo(dateTimeFormatterBuilder, str);
    }

    protected DateTimeFormat() {
    }

    private static void parsePatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        boolean z;
        int length = str.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String parseToken = parseToken(str, iArr);
            int i2 = iArr[0];
            int length2 = parseToken.length();
            if (length2 != 0) {
                char charAt = parseToken.charAt(0);
                if (charAt == '\'') {
                    String substring = parseToken.substring(1);
                    if (substring.length() == 1) {
                        dateTimeFormatterBuilder.appendLiteral(substring.charAt(0));
                    } else {
                        dateTimeFormatterBuilder.appendLiteral(new String(substring));
                    }
                } else if (charAt == 'K') {
                    dateTimeFormatterBuilder.appendHourOfHalfday(length2);
                } else if (charAt != 'M') {
                    if (charAt == 'S') {
                        dateTimeFormatterBuilder.appendFractionOfSecond(length2, length2);
                    } else if (charAt == 'a') {
                        dateTimeFormatterBuilder.appendHalfdayOfDayText();
                    } else if (charAt == 'h') {
                        dateTimeFormatterBuilder.appendClockhourOfHalfday(length2);
                    } else if (charAt == 'k') {
                        dateTimeFormatterBuilder.appendClockhourOfDay(length2);
                    } else if (charAt == 'm') {
                        dateTimeFormatterBuilder.appendMinuteOfHour(length2);
                    } else if (charAt == 's') {
                        dateTimeFormatterBuilder.appendSecondOfMinute(length2);
                    } else if (charAt == 'G') {
                        dateTimeFormatterBuilder.appendEraText();
                    } else if (charAt != 'H') {
                        if (charAt != 'Y') {
                            if (charAt != 'Z') {
                                if (charAt == 'd') {
                                    dateTimeFormatterBuilder.appendDayOfMonth(length2);
                                } else if (charAt != 'e') {
                                    switch (charAt) {
                                        case 'C':
                                            dateTimeFormatterBuilder.appendCenturyOfEra(length2, length2);
                                            continue;
                                        case 'D':
                                            dateTimeFormatterBuilder.appendDayOfYear(length2);
                                            continue;
                                        case 'E':
                                            if (length2 < 4) {
                                                dateTimeFormatterBuilder.appendDayOfWeekShortText();
                                                break;
                                            } else {
                                                dateTimeFormatterBuilder.appendDayOfWeekText();
                                                continue;
                                            }
                                        default:
                                            switch (charAt) {
                                                case 'w':
                                                    dateTimeFormatterBuilder.appendWeekOfWeekyear(length2);
                                                    continue;
                                                case 'x':
                                                case 'y':
                                                    break;
                                                case 'z':
                                                    if (length2 < 4) {
                                                        dateTimeFormatterBuilder.appendTimeZoneShortName((Map<String, DateTimeZone>) null);
                                                        break;
                                                    } else {
                                                        dateTimeFormatterBuilder.appendTimeZoneName();
                                                        continue;
                                                        continue;
                                                    }
                                                default:
                                                    throw new IllegalArgumentException("Illegal pattern component: " + parseToken);
                                            }
                                    }
                                } else {
                                    dateTimeFormatterBuilder.appendDayOfWeek(length2);
                                }
                            } else if (length2 == 1) {
                                dateTimeFormatterBuilder.appendTimeZoneOffset((String) null, "Z", false, 2, 2);
                            } else if (length2 == 2) {
                                dateTimeFormatterBuilder.appendTimeZoneOffset((String) null, "Z", true, 2, 2);
                            } else {
                                dateTimeFormatterBuilder.appendTimeZoneId();
                            }
                        }
                        if (length2 == 2) {
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                z = !isNumericToken(parseToken(str, iArr));
                                iArr[0] = iArr[0] - 1;
                            } else {
                                z = true;
                            }
                            if (charAt != 'x') {
                                dateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, z);
                            } else {
                                dateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, z);
                            }
                        } else {
                            int i3 = 9;
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                if (isNumericToken(parseToken(str, iArr))) {
                                    i3 = length2;
                                }
                                iArr[0] = iArr[0] - 1;
                            }
                            if (charAt == 'Y') {
                                dateTimeFormatterBuilder.appendYearOfEra(length2, i3);
                            } else if (charAt == 'x') {
                                dateTimeFormatterBuilder.appendWeekyear(length2, i3);
                            } else if (charAt == 'y') {
                                dateTimeFormatterBuilder.appendYear(length2, i3);
                            }
                        }
                    } else {
                        dateTimeFormatterBuilder.appendHourOfDay(length2);
                    }
                } else if (length2 < 3) {
                    dateTimeFormatterBuilder.appendMonthOfYear(length2);
                } else if (length2 >= 4) {
                    dateTimeFormatterBuilder.appendMonthOfYearText();
                } else {
                    dateTimeFormatterBuilder.appendMonthOfYearShortText();
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    private static String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (true) {
                if (i >= length) {
                    break;
                }
                char charAt2 = str.charAt(i);
                if (charAt2 == '\'') {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        i = i3;
                    }
                } else if (z || ((charAt2 < 'A' || charAt2 > 'Z') && (charAt2 < 'a' || charAt2 > 'z'))) {
                    sb.append(charAt2);
                }
                i++;
            }
            i--;
        }
        iArr[0] = i;
        return sb.toString();
    }

    private static boolean isNumericToken(String str) {
        int length = str.length();
        if (length > 0) {
            switch (str.charAt(0)) {
                case 'C':
                case 'D':
                case 'F':
                case 'H':
                case 'K':
                case 'S':
                case 'W':
                case 'Y':
                case 'c':
                case 'd':
                case 'e':
                case 'h':
                case 'k':
                case 'm':
                case 's':
                case 'w':
                case 'x':
                case 'y':
                    break;
                case 'M':
                    if (length <= 2) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0028, code lost:
        r3 = cPatternCache.putIfAbsent(r3, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.joda.time.format.DateTimeFormatter createFormatterForPattern(java.lang.String r3) {
        /*
            if (r3 == 0) goto L_0x0034
            int r0 = r3.length()
            if (r0 == 0) goto L_0x0034
            java.util.concurrent.ConcurrentHashMap<java.lang.String, org.joda.time.format.DateTimeFormatter> r0 = cPatternCache
            java.lang.Object r0 = r0.get(r3)
            org.joda.time.format.DateTimeFormatter r0 = (org.joda.time.format.DateTimeFormatter) r0
            if (r0 != 0) goto L_0x0033
            org.joda.time.format.DateTimeFormatterBuilder r0 = new org.joda.time.format.DateTimeFormatterBuilder
            r0.<init>()
            parsePatternTo(r0, r3)
            org.joda.time.format.DateTimeFormatter r0 = r0.toFormatter()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, org.joda.time.format.DateTimeFormatter> r1 = cPatternCache
            int r1 = r1.size()
            r2 = 500(0x1f4, float:7.0E-43)
            if (r1 >= r2) goto L_0x0033
            java.util.concurrent.ConcurrentHashMap<java.lang.String, org.joda.time.format.DateTimeFormatter> r1 = cPatternCache
            java.lang.Object r3 = r1.putIfAbsent(r3, r0)
            org.joda.time.format.DateTimeFormatter r3 = (org.joda.time.format.DateTimeFormatter) r3
            if (r3 == 0) goto L_0x0033
            r0 = r3
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Invalid pattern specification"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormat.createFormatterForPattern(java.lang.String):org.joda.time.format.DateTimeFormatter");
    }

    private static DateTimeFormatter createFormatterForStyle(String str) {
        if (str == null || str.length() != 2) {
            throw new IllegalArgumentException("Invalid style specification: " + str);
        }
        int selectStyle = selectStyle(str.charAt(0));
        int selectStyle2 = selectStyle(str.charAt(1));
        if (selectStyle != 4 || selectStyle2 != 4) {
            return createFormatterForStyleIndex(selectStyle, selectStyle2);
        }
        throw new IllegalArgumentException("Style '--' is invalid");
    }

    private static DateTimeFormatter createFormatterForStyleIndex(int i, int i2) {
        int i3 = (i << 2) + i + i2;
        if (i3 >= cStyleCache.length()) {
            return createDateTimeFormatter(i, i2);
        }
        DateTimeFormatter dateTimeFormatter = cStyleCache.get(i3);
        if (dateTimeFormatter != null) {
            return dateTimeFormatter;
        }
        DateTimeFormatter createDateTimeFormatter = createDateTimeFormatter(i, i2);
        return !cStyleCache.compareAndSet(i3, (Object) null, createDateTimeFormatter) ? cStyleCache.get(i3) : createDateTimeFormatter;
    }

    private static DateTimeFormatter createDateTimeFormatter(int i, int i2) {
        StyleFormatter styleFormatter = new StyleFormatter(i, i2, i == 4 ? 1 : i2 == 4 ? 0 : 2);
        return new DateTimeFormatter((InternalPrinter) styleFormatter, (InternalParser) styleFormatter);
    }

    private static int selectStyle(char c) {
        if (c == '-') {
            return 4;
        }
        if (c == 'F') {
            return 0;
        }
        if (c == 'S') {
            return 3;
        }
        if (c == 'L') {
            return 1;
        }
        if (c == 'M') {
            return 2;
        }
        throw new IllegalArgumentException("Invalid style character: " + c);
    }

    static class StyleFormatter implements InternalPrinter, InternalParser {
        private static final ConcurrentHashMap<StyleFormatterCacheKey, DateTimeFormatter> cCache = new ConcurrentHashMap<>();
        private final int iDateStyle;
        private final int iTimeStyle;
        private final int iType;

        public int estimateParsedLength() {
            return 40;
        }

        public int estimatePrintedLength() {
            return 40;
        }

        StyleFormatter(int i, int i2, int i3) {
            this.iDateStyle = i;
            this.iTimeStyle = i2;
            this.iType = i3;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            Locale locale2 = locale;
            getFormatter(locale2).getPrinter0().printTo(appendable, j, chronology, i, dateTimeZone, locale2);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            getFormatter(locale).getPrinter0().printTo(appendable, readablePartial, locale);
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            return getFormatter(dateTimeParserBucket.getLocale()).getParser0().parseInto(dateTimeParserBucket, charSequence, i);
        }

        private DateTimeFormatter getFormatter(Locale locale) {
            if (locale == null) {
                locale = Locale.getDefault();
            }
            StyleFormatterCacheKey styleFormatterCacheKey = new StyleFormatterCacheKey(this.iType, this.iDateStyle, this.iTimeStyle, locale);
            DateTimeFormatter dateTimeFormatter = cCache.get(styleFormatterCacheKey);
            if (dateTimeFormatter != null) {
                return dateTimeFormatter;
            }
            DateTimeFormatter forPattern = DateTimeFormat.forPattern(getPattern(locale));
            DateTimeFormatter putIfAbsent = cCache.putIfAbsent(styleFormatterCacheKey, forPattern);
            return putIfAbsent != null ? putIfAbsent : forPattern;
        }

        /* access modifiers changed from: package-private */
        public String getPattern(Locale locale) {
            DateFormat dateFormat;
            int i = this.iType;
            if (i == 0) {
                dateFormat = DateFormat.getDateInstance(this.iDateStyle, locale);
            } else if (i == 1) {
                dateFormat = DateFormat.getTimeInstance(this.iTimeStyle, locale);
            } else if (i != 2) {
                dateFormat = null;
            } else {
                dateFormat = DateFormat.getDateTimeInstance(this.iDateStyle, this.iTimeStyle, locale);
            }
            if (dateFormat instanceof SimpleDateFormat) {
                return ((SimpleDateFormat) dateFormat).toPattern();
            }
            throw new IllegalArgumentException("No datetime pattern for locale: " + locale);
        }
    }

    static class StyleFormatterCacheKey {
        private final int combinedTypeAndStyle;
        private final Locale locale;

        public StyleFormatterCacheKey(int i, int i2, int i3, Locale locale2) {
            this.locale = locale2;
            this.combinedTypeAndStyle = i + (i2 << 4) + (i3 << 8);
        }

        public int hashCode() {
            int i = (this.combinedTypeAndStyle + 31) * 31;
            Locale locale2 = this.locale;
            return i + (locale2 == null ? 0 : locale2.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof StyleFormatterCacheKey)) {
                return false;
            }
            StyleFormatterCacheKey styleFormatterCacheKey = (StyleFormatterCacheKey) obj;
            if (this.combinedTypeAndStyle != styleFormatterCacheKey.combinedTypeAndStyle) {
                return false;
            }
            Locale locale2 = this.locale;
            if (locale2 == null) {
                if (styleFormatterCacheKey.locale != null) {
                    return false;
                }
            } else if (!locale2.equals(styleFormatterCacheKey.locale)) {
                return false;
            }
            return true;
        }
    }
}
