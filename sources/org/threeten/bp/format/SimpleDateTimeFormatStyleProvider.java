package org.threeten.bp.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.threeten.bp.chrono.Chronology;

final class SimpleDateTimeFormatStyleProvider extends DateTimeFormatStyleProvider {
    private static final ConcurrentMap<String, Object> FORMATTER_CACHE = new ConcurrentHashMap(16, 0.75f, 2);

    SimpleDateTimeFormatStyleProvider() {
    }

    public Locale[] getAvailableLocales() {
        return DateFormat.getAvailableLocales();
    }

    public DateTimeFormatter getFormatter(FormatStyle formatStyle, FormatStyle formatStyle2, Chronology chronology, Locale locale) {
        DateFormat dateFormat;
        if (formatStyle == null && formatStyle2 == null) {
            throw new IllegalArgumentException("Date and Time style must not both be null");
        }
        String str = chronology.getId() + '|' + locale.toString() + '|' + formatStyle + formatStyle2;
        Object obj = FORMATTER_CACHE.get(str);
        if (obj == null) {
            if (formatStyle == null) {
                dateFormat = DateFormat.getTimeInstance(convertStyle(formatStyle2), locale);
            } else if (formatStyle2 != null) {
                dateFormat = DateFormat.getDateTimeInstance(convertStyle(formatStyle), convertStyle(formatStyle2), locale);
            } else {
                dateFormat = DateFormat.getDateInstance(convertStyle(formatStyle), locale);
            }
            if (dateFormat instanceof SimpleDateFormat) {
                DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(((SimpleDateFormat) dateFormat).toPattern()).toFormatter(locale);
                FORMATTER_CACHE.putIfAbsent(str, formatter);
                return formatter;
            }
            FORMATTER_CACHE.putIfAbsent(str, "");
            throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
        } else if (!obj.equals("")) {
            return (DateTimeFormatter) obj;
        } else {
            throw new IllegalArgumentException("Unable to convert DateFormat to DateTimeFormatter");
        }
    }

    private int convertStyle(FormatStyle formatStyle) {
        return formatStyle.ordinal();
    }
}
