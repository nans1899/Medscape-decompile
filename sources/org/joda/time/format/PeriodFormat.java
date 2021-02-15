package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormat {
    private static final String BUNDLE_NAME = "org.joda.time.format.messages";
    private static final ConcurrentMap<Locale, PeriodFormatter> FORMATTERS = new ConcurrentHashMap();

    protected PeriodFormat() {
    }

    public static PeriodFormatter getDefault() {
        return wordBased(Locale.ENGLISH);
    }

    public static PeriodFormatter wordBased() {
        return wordBased(Locale.getDefault());
    }

    public static PeriodFormatter wordBased(Locale locale) {
        PeriodFormatter periodFormatter = (PeriodFormatter) FORMATTERS.get(locale);
        if (periodFormatter != null) {
            return periodFormatter;
        }
        DynamicWordBased dynamicWordBased = new DynamicWordBased(buildWordBased(locale));
        PeriodFormatter periodFormatter2 = new PeriodFormatter(dynamicWordBased, dynamicWordBased, locale, (PeriodType) null);
        PeriodFormatter putIfAbsent = FORMATTERS.putIfAbsent(locale, periodFormatter2);
        return putIfAbsent != null ? putIfAbsent : periodFormatter2;
    }

    private static PeriodFormatter buildWordBased(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        if (containsKey(bundle, "PeriodFormat.regex.separator")) {
            return buildRegExFormatter(bundle, locale);
        }
        return buildNonRegExFormatter(bundle, locale);
    }

    private static PeriodFormatter buildRegExFormatter(ResourceBundle resourceBundle, Locale locale) {
        String[] retrieveVariants = retrieveVariants(resourceBundle);
        String string = resourceBundle.getString("PeriodFormat.regex.separator");
        PeriodFormatterBuilder periodFormatterBuilder = new PeriodFormatterBuilder();
        periodFormatterBuilder.appendYears();
        if (containsKey(resourceBundle, "PeriodFormat.years.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.years.regex").split(string), resourceBundle.getString("PeriodFormat.years.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.year"), resourceBundle.getString("PeriodFormat.years"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendMonths();
        if (containsKey(resourceBundle, "PeriodFormat.months.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.months.regex").split(string), resourceBundle.getString("PeriodFormat.months.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.month"), resourceBundle.getString("PeriodFormat.months"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendWeeks();
        if (containsKey(resourceBundle, "PeriodFormat.weeks.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.weeks.regex").split(string), resourceBundle.getString("PeriodFormat.weeks.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.week"), resourceBundle.getString("PeriodFormat.weeks"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendDays();
        if (containsKey(resourceBundle, "PeriodFormat.days.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.days.regex").split(string), resourceBundle.getString("PeriodFormat.days.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.day"), resourceBundle.getString("PeriodFormat.days"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendHours();
        if (containsKey(resourceBundle, "PeriodFormat.hours.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.hours.regex").split(string), resourceBundle.getString("PeriodFormat.hours.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.hour"), resourceBundle.getString("PeriodFormat.hours"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendMinutes();
        if (containsKey(resourceBundle, "PeriodFormat.minutes.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.minutes.regex").split(string), resourceBundle.getString("PeriodFormat.minutes.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.minute"), resourceBundle.getString("PeriodFormat.minutes"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendSeconds();
        if (containsKey(resourceBundle, "PeriodFormat.seconds.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.seconds.regex").split(string), resourceBundle.getString("PeriodFormat.seconds.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.second"), resourceBundle.getString("PeriodFormat.seconds"));
        }
        periodFormatterBuilder.appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants);
        periodFormatterBuilder.appendMillis();
        if (containsKey(resourceBundle, "PeriodFormat.milliseconds.regex")) {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.milliseconds.regex").split(string), resourceBundle.getString("PeriodFormat.milliseconds.list").split(string));
        } else {
            periodFormatterBuilder.appendSuffix(resourceBundle.getString("PeriodFormat.millisecond"), resourceBundle.getString("PeriodFormat.milliseconds"));
        }
        return periodFormatterBuilder.toFormatter().withLocale(locale);
    }

    private static PeriodFormatter buildNonRegExFormatter(ResourceBundle resourceBundle, Locale locale) {
        String[] retrieveVariants = retrieveVariants(resourceBundle);
        return new PeriodFormatterBuilder().appendYears().appendSuffix(resourceBundle.getString("PeriodFormat.year"), resourceBundle.getString("PeriodFormat.years")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendMonths().appendSuffix(resourceBundle.getString("PeriodFormat.month"), resourceBundle.getString("PeriodFormat.months")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendWeeks().appendSuffix(resourceBundle.getString("PeriodFormat.week"), resourceBundle.getString("PeriodFormat.weeks")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendDays().appendSuffix(resourceBundle.getString("PeriodFormat.day"), resourceBundle.getString("PeriodFormat.days")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendHours().appendSuffix(resourceBundle.getString("PeriodFormat.hour"), resourceBundle.getString("PeriodFormat.hours")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendMinutes().appendSuffix(resourceBundle.getString("PeriodFormat.minute"), resourceBundle.getString("PeriodFormat.minutes")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendSeconds().appendSuffix(resourceBundle.getString("PeriodFormat.second"), resourceBundle.getString("PeriodFormat.seconds")).appendSeparator(resourceBundle.getString("PeriodFormat.commaspace"), resourceBundle.getString("PeriodFormat.spaceandspace"), retrieveVariants).appendMillis().appendSuffix(resourceBundle.getString("PeriodFormat.millisecond"), resourceBundle.getString("PeriodFormat.milliseconds")).toFormatter().withLocale(locale);
    }

    private static String[] retrieveVariants(ResourceBundle resourceBundle) {
        return new String[]{resourceBundle.getString("PeriodFormat.space"), resourceBundle.getString("PeriodFormat.comma"), resourceBundle.getString("PeriodFormat.commandand"), resourceBundle.getString("PeriodFormat.commaspaceand")};
    }

    private static boolean containsKey(ResourceBundle resourceBundle, String str) {
        Enumeration<String> keys = resourceBundle.getKeys();
        while (keys.hasMoreElements()) {
            if (keys.nextElement().equals(str)) {
                return true;
            }
        }
        return false;
    }

    static class DynamicWordBased implements PeriodPrinter, PeriodParser {
        private final PeriodFormatter iFormatter;

        DynamicWordBased(PeriodFormatter periodFormatter) {
            this.iFormatter = periodFormatter;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            return getPrinter(locale).countFieldsToPrint(readablePeriod, i, locale);
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            return getPrinter(locale).calculatePrintedLength(readablePeriod, locale);
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            getPrinter(locale).printTo(stringBuffer, readablePeriod, locale);
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            getPrinter(locale).printTo(writer, readablePeriod, locale);
        }

        private PeriodPrinter getPrinter(Locale locale) {
            if (locale == null || locale.equals(this.iFormatter.getLocale())) {
                return this.iFormatter.getPrinter();
            }
            return PeriodFormat.wordBased(locale).getPrinter();
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            return getParser(locale).parseInto(readWritablePeriod, str, i, locale);
        }

        private PeriodParser getParser(Locale locale) {
            if (locale == null || locale.equals(this.iFormatter.getLocale())) {
                return this.iFormatter.getParser();
            }
            return PeriodFormat.wordBased(locale).getParser();
        }
    }
}
