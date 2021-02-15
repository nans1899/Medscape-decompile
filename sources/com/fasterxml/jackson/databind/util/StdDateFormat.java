package com.fasterxml.jackson.databind.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = {DATE_FORMAT_STR_ISO8601, DATE_FORMAT_STR_ISO8601_Z, DATE_FORMAT_STR_RFC1123, DATE_FORMAT_STR_PLAIN};
    protected static final DateFormat DATE_FORMAT_ISO8601;
    protected static final DateFormat DATE_FORMAT_ISO8601_Z;
    protected static final DateFormat DATE_FORMAT_PLAIN;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    public static final StdDateFormat instance = new StdDateFormat();
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_STR_RFC1123, DEFAULT_LOCALE);
        DATE_FORMAT_RFC1123 = simpleDateFormat;
        simpleDateFormat.setTimeZone(DEFAULT_TIMEZONE);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601, DEFAULT_LOCALE);
        DATE_FORMAT_ISO8601 = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(DEFAULT_TIMEZONE);
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601_Z, DEFAULT_LOCALE);
        DATE_FORMAT_ISO8601_Z = simpleDateFormat3;
        simpleDateFormat3.setTimeZone(DEFAULT_TIMEZONE);
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN, DEFAULT_LOCALE);
        DATE_FORMAT_PLAIN = simpleDateFormat4;
        simpleDateFormat4.setTimeZone(DEFAULT_TIMEZONE);
    }

    public StdDateFormat() {
        this._locale = DEFAULT_LOCALE;
    }

    @Deprecated
    public StdDateFormat(TimeZone timeZone, Locale locale) {
        this._timezone = timeZone;
        this._locale = locale;
    }

    protected StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool) {
        this._timezone = timeZone;
        this._locale = locale;
        this._lenient = bool;
    }

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        TimeZone timeZone2 = this._timezone;
        return (timeZone == timeZone2 || timeZone.equals(timeZone2)) ? this : new StdDateFormat(timeZone, this._locale, this._lenient);
    }

    public StdDateFormat withLocale(Locale locale) {
        if (locale.equals(this._locale)) {
            return this;
        }
        return new StdDateFormat(this._timezone, locale, this._lenient);
    }

    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient);
    }

    @Deprecated
    public static DateFormat getISO8601Format(TimeZone timeZone) {
        return getISO8601Format(timeZone, DEFAULT_LOCALE);
    }

    public static DateFormat getISO8601Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, timeZone, locale, (Boolean) null);
    }

    public static DateFormat getRFC1123Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, timeZone, locale, (Boolean) null);
    }

    @Deprecated
    public static DateFormat getRFC1123Format(TimeZone timeZone) {
        return getRFC1123Format(timeZone, DEFAULT_LOCALE);
    }

    public TimeZone getTimeZone() {
        return this._timezone;
    }

    public void setTimeZone(TimeZone timeZone) {
        if (!timeZone.equals(this._timezone)) {
            _clearFormats();
            this._timezone = timeZone;
        }
    }

    public void setLenient(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        if (this._lenient != valueOf) {
            this._lenient = valueOf;
            _clearFormats();
        }
    }

    public boolean isLenient() {
        Boolean bool = this._lenient;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Date parse(java.lang.String r11) throws java.text.ParseException {
        /*
            r10 = this;
            java.lang.String r11 = r11.trim()
            java.text.ParsePosition r0 = new java.text.ParsePosition
            r1 = 0
            r0.<init>(r1)
            boolean r2 = r10.looksLikeISO8601(r11)
            r3 = 1
            if (r2 == 0) goto L_0x0016
            java.util.Date r2 = r10.parseAsISO8601(r11, r0, r3)
            goto L_0x004c
        L_0x0016:
            int r2 = r11.length()
        L_0x001a:
            int r2 = r2 + -1
            r4 = 45
            if (r2 < 0) goto L_0x0030
            char r5 = r11.charAt(r2)
            r6 = 48
            if (r5 < r6) goto L_0x002c
            r6 = 57
            if (r5 <= r6) goto L_0x001a
        L_0x002c:
            if (r2 > 0) goto L_0x0030
            if (r5 == r4) goto L_0x001a
        L_0x0030:
            if (r2 >= 0) goto L_0x0048
            char r2 = r11.charAt(r1)
            if (r2 == r4) goto L_0x003e
            boolean r2 = com.fasterxml.jackson.core.io.NumberInput.inLongRange(r11, r1)
            if (r2 == 0) goto L_0x0048
        L_0x003e:
            java.util.Date r2 = new java.util.Date
            long r4 = java.lang.Long.parseLong(r11)
            r2.<init>(r4)
            goto L_0x004c
        L_0x0048:
            java.util.Date r2 = r10.parseAsRFC1123(r11, r0)
        L_0x004c:
            if (r2 == 0) goto L_0x004f
            return r2
        L_0x004f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String[] r4 = ALL_FORMATS
            int r5 = r4.length
            r6 = 0
        L_0x0058:
            r7 = 34
            if (r6 >= r5) goto L_0x0073
            r8 = r4[r6]
            int r9 = r2.length()
            if (r9 <= 0) goto L_0x006a
            java.lang.String r7 = "\", \""
            r2.append(r7)
            goto L_0x006d
        L_0x006a:
            r2.append(r7)
        L_0x006d:
            r2.append(r8)
            int r6 = r6 + 1
            goto L_0x0058
        L_0x0073:
            r2.append(r7)
            java.text.ParseException r4 = new java.text.ParseException
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r1] = r11
            java.lang.String r11 = r2.toString()
            r5[r3] = r11
            java.lang.String r11 = "Can not parse date \"%s\": not compatible with any of standard forms (%s)"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            int r0 = r0.getErrorIndex()
            r4.<init>(r11, r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.StdDateFormat.parse(java.lang.String):java.util.Date");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Date parse(java.lang.String r6, java.text.ParsePosition r7) {
        /*
            r5 = this;
            boolean r0 = r5.looksLikeISO8601(r6)
            r1 = 0
            if (r0 == 0) goto L_0x000e
            java.util.Date r6 = r5.parseAsISO8601(r6, r7, r1)     // Catch:{ ParseException -> 0x000c }
            return r6
        L_0x000c:
            r6 = 0
            return r6
        L_0x000e:
            int r0 = r6.length()
        L_0x0012:
            int r0 = r0 + -1
            r2 = 45
            if (r0 < 0) goto L_0x0028
            char r3 = r6.charAt(r0)
            r4 = 48
            if (r3 < r4) goto L_0x0024
            r4 = 57
            if (r3 <= r4) goto L_0x0012
        L_0x0024:
            if (r0 > 0) goto L_0x0028
            if (r3 == r2) goto L_0x0012
        L_0x0028:
            if (r0 >= 0) goto L_0x0040
            char r0 = r6.charAt(r1)
            if (r0 == r2) goto L_0x0036
            boolean r0 = com.fasterxml.jackson.core.io.NumberInput.inLongRange(r6, r1)
            if (r0 == 0) goto L_0x0040
        L_0x0036:
            java.util.Date r7 = new java.util.Date
            long r0 = java.lang.Long.parseLong(r6)
            r7.<init>(r0)
            return r7
        L_0x0040:
            java.util.Date r6 = r5.parseAsRFC1123(r6, r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.StdDateFormat.parse(java.lang.String, java.text.ParsePosition):java.util.Date");
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, this._timezone, this._locale, this._lenient);
        }
        return this._formatISO8601.format(date, stringBuffer, fieldPosition);
    }

    public String toString() {
        String str = "DateFormat " + getClass().getName();
        TimeZone timeZone = this._timezone;
        if (timeZone != null) {
            str = str + " (timezone: " + timeZone + ")";
        }
        return str + "(locale: " + this._locale + ")";
    }

    /* access modifiers changed from: protected */
    public boolean looksLikeISO8601(String str) {
        return str.length() >= 5 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-';
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ca, code lost:
        r1.insert(r13, ":00.000");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00cf, code lost:
        r11 = r1.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ff, code lost:
        r0.append('0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0102, code lost:
        r0.append('0');
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Date parseAsISO8601(java.lang.String r11, java.text.ParsePosition r12, boolean r13) throws java.text.ParseException {
        /*
            r10 = this;
            int r13 = r11.length()
            int r0 = r13 + -1
            char r1 = r11.charAt(r0)
            r2 = 2
            java.lang.String r3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            r4 = 1
            r5 = 10
            if (r13 > r5) goto L_0x002e
            boolean r5 = java.lang.Character.isDigit(r1)
            if (r5 == 0) goto L_0x002e
            java.text.DateFormat r13 = r10._formatPlain
            java.lang.String r3 = "yyyy-MM-dd"
            if (r13 != 0) goto L_0x011e
            java.text.DateFormat r13 = DATE_FORMAT_PLAIN
            java.util.TimeZone r0 = r10._timezone
            java.util.Locale r1 = r10._locale
            java.lang.Boolean r5 = r10._lenient
            java.text.DateFormat r13 = _cloneFormat(r13, r3, r0, r1, r5)
            r10._formatPlain = r13
            goto L_0x011e
        L_0x002e:
            r5 = 58
            r6 = 90
            java.lang.String r7 = ".000"
            if (r1 != r6) goto L_0x005f
            java.text.DateFormat r1 = r10._formatISO8601_z
            if (r1 != 0) goto L_0x0048
            java.text.DateFormat r1 = DATE_FORMAT_ISO8601_Z
            java.util.TimeZone r6 = r10._timezone
            java.util.Locale r8 = r10._locale
            java.lang.Boolean r9 = r10._lenient
            java.text.DateFormat r1 = _cloneFormat(r1, r3, r6, r8, r9)
            r10._formatISO8601_z = r1
        L_0x0048:
            int r13 = r13 + -4
            char r13 = r11.charAt(r13)
            if (r13 != r5) goto L_0x005c
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r11)
            r13.insert(r0, r7)
            java.lang.String r11 = r13.toString()
        L_0x005c:
            r13 = r1
            goto L_0x011e
        L_0x005f:
            boolean r0 = hasTimeZone(r11)
            r1 = 12
            r8 = 84
            r9 = 48
            if (r0 == 0) goto L_0x00e8
            int r0 = r13 + -3
            char r3 = r11.charAt(r0)
            java.lang.String r6 = "00"
            if (r3 != r5) goto L_0x0083
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r11)
            int r13 = r13 - r2
            r3.delete(r0, r13)
            java.lang.String r11 = r3.toString()
            goto L_0x009a
        L_0x0083:
            r13 = 43
            if (r3 == r13) goto L_0x008b
            r13 = 45
            if (r3 != r13) goto L_0x009a
        L_0x008b:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r11)
            r13.append(r6)
            java.lang.String r11 = r13.toString()
        L_0x009a:
            int r13 = r11.length()
            int r0 = r11.lastIndexOf(r8)
            int r0 = r13 - r0
            int r0 = r0 + -6
            if (r0 >= r1) goto L_0x00d3
            int r13 = r13 + -5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r11)
            switch(r0) {
                case 5: goto L_0x00ca;
                case 6: goto L_0x00c5;
                case 7: goto L_0x00b2;
                case 8: goto L_0x00c1;
                case 9: goto L_0x00bb;
                case 10: goto L_0x00b7;
                case 11: goto L_0x00b3;
                default: goto L_0x00b2;
            }
        L_0x00b2:
            goto L_0x00cf
        L_0x00b3:
            r1.insert(r13, r9)
            goto L_0x00cf
        L_0x00b7:
            r1.insert(r13, r6)
            goto L_0x00cf
        L_0x00bb:
            java.lang.String r11 = "000"
            r1.insert(r13, r11)
            goto L_0x00cf
        L_0x00c1:
            r1.insert(r13, r7)
            goto L_0x00cf
        L_0x00c5:
            java.lang.String r11 = "00.000"
            r1.insert(r13, r11)
        L_0x00ca:
            java.lang.String r11 = ":00.000"
            r1.insert(r13, r11)
        L_0x00cf:
            java.lang.String r11 = r1.toString()
        L_0x00d3:
            java.text.DateFormat r13 = r10._formatISO8601
            java.lang.String r3 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
            if (r13 != 0) goto L_0x011e
            java.text.DateFormat r13 = DATE_FORMAT_ISO8601
            java.util.TimeZone r0 = r10._timezone
            java.util.Locale r1 = r10._locale
            java.lang.Boolean r5 = r10._lenient
            java.text.DateFormat r13 = _cloneFormat(r13, r3, r0, r1, r5)
            r10._formatISO8601 = r13
            goto L_0x011e
        L_0x00e8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            int r11 = r11.lastIndexOf(r8)
            int r13 = r13 - r11
            int r13 = r13 - r4
            if (r13 >= r1) goto L_0x0105
            switch(r13) {
                case 9: goto L_0x0102;
                case 10: goto L_0x00ff;
                case 11: goto L_0x00fc;
                default: goto L_0x00f8;
            }
        L_0x00f8:
            r0.append(r7)
            goto L_0x0105
        L_0x00fc:
            r0.append(r9)
        L_0x00ff:
            r0.append(r9)
        L_0x0102:
            r0.append(r9)
        L_0x0105:
            r0.append(r6)
            java.lang.String r11 = r0.toString()
            java.text.DateFormat r13 = r10._formatISO8601_z
            if (r13 != 0) goto L_0x011e
            java.text.DateFormat r13 = DATE_FORMAT_ISO8601_Z
            java.util.TimeZone r0 = r10._timezone
            java.util.Locale r1 = r10._locale
            java.lang.Boolean r5 = r10._lenient
            java.text.DateFormat r13 = _cloneFormat(r13, r3, r0, r1, r5)
            r10._formatISO8601_z = r13
        L_0x011e:
            java.util.Date r13 = r13.parse(r11, r12)
            if (r13 == 0) goto L_0x0125
            return r13
        L_0x0125:
            java.text.ParseException r13 = new java.text.ParseException
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            r0[r1] = r11
            r0[r4] = r3
            java.lang.Boolean r11 = r10._lenient
            r0[r2] = r11
            java.lang.String r11 = "Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)"
            java.lang.String r11 = java.lang.String.format(r11, r0)
            int r12 = r12.getErrorIndex()
            r13.<init>(r11, r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.StdDateFormat.parseAsISO8601(java.lang.String, java.text.ParsePosition, boolean):java.util.Date");
    }

    /* access modifiers changed from: protected */
    public Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    private static final boolean hasTimeZone(String str) {
        char charAt;
        char charAt2;
        int length = str.length();
        if (length < 6) {
            return false;
        }
        char charAt3 = str.charAt(length - 6);
        if (charAt3 == '+' || charAt3 == '-' || (charAt = str.charAt(length - 5)) == '+' || charAt == '-' || (charAt2 = str.charAt(length - 3)) == '+' || charAt2 == '-') {
            return true;
        }
        return false;
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, String str, TimeZone timeZone, Locale locale, Boolean bool) {
        DateFormat dateFormat2;
        if (!locale.equals(DEFAULT_LOCALE)) {
            dateFormat2 = new SimpleDateFormat(str, locale);
            if (timeZone == null) {
                timeZone = DEFAULT_TIMEZONE;
            }
            dateFormat2.setTimeZone(timeZone);
        } else {
            dateFormat2 = (DateFormat) dateFormat.clone();
            if (timeZone != null) {
                dateFormat2.setTimeZone(timeZone);
            }
        }
        if (bool != null) {
            dateFormat2.setLenient(bool.booleanValue());
        }
        return dateFormat2;
    }

    /* access modifiers changed from: protected */
    public void _clearFormats() {
        this._formatRFC1123 = null;
        this._formatISO8601 = null;
        this._formatISO8601_z = null;
        this._formatPlain = null;
    }
}
