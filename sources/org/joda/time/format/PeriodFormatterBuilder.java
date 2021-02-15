package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;
import org.joda.time.DurationFieldType;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadablePeriod;

public class PeriodFormatterBuilder {
    private static final int DAYS = 3;
    private static final int HOURS = 4;
    private static final int MAX_FIELD = 9;
    private static final int MILLIS = 7;
    private static final int MINUTES = 5;
    private static final int MONTHS = 1;
    /* access modifiers changed from: private */
    public static final ConcurrentMap<String, Pattern> PATTERNS = new ConcurrentHashMap();
    private static final int PRINT_ZERO_ALWAYS = 4;
    private static final int PRINT_ZERO_IF_SUPPORTED = 3;
    private static final int PRINT_ZERO_NEVER = 5;
    private static final int PRINT_ZERO_RARELY_FIRST = 1;
    private static final int PRINT_ZERO_RARELY_LAST = 2;
    private static final int SECONDS = 6;
    private static final int SECONDS_MILLIS = 8;
    private static final int SECONDS_OPTIONAL_MILLIS = 9;
    private static final int WEEKS = 2;
    private static final int YEARS = 0;
    private List<Object> iElementPairs;
    private FieldFormatter[] iFieldFormatters;
    private int iMaxParsedDigits;
    private int iMinPrintedDigits;
    private boolean iNotParser;
    private boolean iNotPrinter;
    private PeriodFieldAffix iPrefix;
    private int iPrintZeroSetting;
    private boolean iRejectSignedValues;

    interface PeriodFieldAffix {
        int calculatePrintedLength(int i);

        void finish(Set<PeriodFieldAffix> set);

        String[] getAffixes();

        int parse(String str, int i);

        void printTo(Writer writer, int i) throws IOException;

        void printTo(StringBuffer stringBuffer, int i);

        int scan(String str, int i);
    }

    public PeriodFormatterBuilder() {
        clear();
    }

    public PeriodFormatter toFormatter() {
        PeriodFormatter formatter = toFormatter(this.iElementPairs, this.iNotPrinter, this.iNotParser);
        for (FieldFormatter fieldFormatter : this.iFieldFormatters) {
            if (fieldFormatter != null) {
                fieldFormatter.finish(this.iFieldFormatters);
            }
        }
        this.iFieldFormatters = (FieldFormatter[]) this.iFieldFormatters.clone();
        return formatter;
    }

    public PeriodPrinter toPrinter() {
        if (this.iNotPrinter) {
            return null;
        }
        return toFormatter().getPrinter();
    }

    public PeriodParser toParser() {
        if (this.iNotParser) {
            return null;
        }
        return toFormatter().getParser();
    }

    public void clear() {
        this.iMinPrintedDigits = 1;
        this.iPrintZeroSetting = 2;
        this.iMaxParsedDigits = 10;
        this.iRejectSignedValues = false;
        this.iPrefix = null;
        List<Object> list = this.iElementPairs;
        if (list == null) {
            this.iElementPairs = new ArrayList();
        } else {
            list.clear();
        }
        this.iNotPrinter = false;
        this.iNotParser = false;
        this.iFieldFormatters = new FieldFormatter[10];
    }

    public PeriodFormatterBuilder append(PeriodFormatter periodFormatter) {
        if (periodFormatter != null) {
            clearPrefix();
            append0(periodFormatter.getPrinter(), periodFormatter.getParser());
            return this;
        }
        throw new IllegalArgumentException("No formatter supplied");
    }

    public PeriodFormatterBuilder append(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        if (periodPrinter == null && periodParser == null) {
            throw new IllegalArgumentException("No printer or parser supplied");
        }
        clearPrefix();
        append0(periodPrinter, periodParser);
        return this;
    }

    public PeriodFormatterBuilder appendLiteral(String str) {
        if (str != null) {
            clearPrefix();
            Literal literal = new Literal(str);
            append0(literal, literal);
            return this;
        }
        throw new IllegalArgumentException("Literal must not be null");
    }

    public PeriodFormatterBuilder minimumPrintedDigits(int i) {
        this.iMinPrintedDigits = i;
        return this;
    }

    public PeriodFormatterBuilder maximumParsedDigits(int i) {
        this.iMaxParsedDigits = i;
        return this;
    }

    public PeriodFormatterBuilder rejectSignedValues(boolean z) {
        this.iRejectSignedValues = z;
        return this;
    }

    public PeriodFormatterBuilder printZeroRarelyLast() {
        this.iPrintZeroSetting = 2;
        return this;
    }

    public PeriodFormatterBuilder printZeroRarelyFirst() {
        this.iPrintZeroSetting = 1;
        return this;
    }

    public PeriodFormatterBuilder printZeroIfSupported() {
        this.iPrintZeroSetting = 3;
        return this;
    }

    public PeriodFormatterBuilder printZeroAlways() {
        this.iPrintZeroSetting = 4;
        return this;
    }

    public PeriodFormatterBuilder printZeroNever() {
        this.iPrintZeroSetting = 5;
        return this;
    }

    public PeriodFormatterBuilder appendPrefix(String str) {
        if (str != null) {
            return appendPrefix((PeriodFieldAffix) new SimpleAffix(str));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendPrefix(String str, String str2) {
        if (str != null && str2 != null) {
            return appendPrefix((PeriodFieldAffix) new PluralAffix(str, str2));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendPrefix(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length >= 1 && strArr.length == strArr2.length) {
            return appendPrefix((PeriodFieldAffix) new RegExAffix(strArr, strArr2));
        }
        throw new IllegalArgumentException();
    }

    private PeriodFormatterBuilder appendPrefix(PeriodFieldAffix periodFieldAffix) {
        if (periodFieldAffix != null) {
            PeriodFieldAffix periodFieldAffix2 = this.iPrefix;
            if (periodFieldAffix2 != null) {
                periodFieldAffix = new CompositeAffix(periodFieldAffix2, periodFieldAffix);
            }
            this.iPrefix = periodFieldAffix;
            return this;
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendYears() {
        appendField(0);
        return this;
    }

    public PeriodFormatterBuilder appendMonths() {
        appendField(1);
        return this;
    }

    public PeriodFormatterBuilder appendWeeks() {
        appendField(2);
        return this;
    }

    public PeriodFormatterBuilder appendDays() {
        appendField(3);
        return this;
    }

    public PeriodFormatterBuilder appendHours() {
        appendField(4);
        return this;
    }

    public PeriodFormatterBuilder appendMinutes() {
        appendField(5);
        return this;
    }

    public PeriodFormatterBuilder appendSeconds() {
        appendField(6);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithMillis() {
        appendField(8);
        return this;
    }

    public PeriodFormatterBuilder appendSecondsWithOptionalMillis() {
        appendField(9);
        return this;
    }

    public PeriodFormatterBuilder appendMillis() {
        appendField(7);
        return this;
    }

    public PeriodFormatterBuilder appendMillis3Digit() {
        appendField(7, 3);
        return this;
    }

    private void appendField(int i) {
        appendField(i, this.iMinPrintedDigits);
    }

    private void appendField(int i, int i2) {
        FieldFormatter fieldFormatter = new FieldFormatter(i2, this.iPrintZeroSetting, this.iMaxParsedDigits, this.iRejectSignedValues, i, this.iFieldFormatters, this.iPrefix, (PeriodFieldAffix) null);
        append0(fieldFormatter, fieldFormatter);
        this.iFieldFormatters[i] = fieldFormatter;
        this.iPrefix = null;
    }

    public PeriodFormatterBuilder appendSuffix(String str) {
        if (str != null) {
            return appendSuffix((PeriodFieldAffix) new SimpleAffix(str));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendSuffix(String str, String str2) {
        if (str != null && str2 != null) {
            return appendSuffix((PeriodFieldAffix) new PluralAffix(str, str2));
        }
        throw new IllegalArgumentException();
    }

    public PeriodFormatterBuilder appendSuffix(String[] strArr, String[] strArr2) {
        if (strArr != null && strArr2 != null && strArr.length >= 1 && strArr.length == strArr2.length) {
            return appendSuffix((PeriodFieldAffix) new RegExAffix(strArr, strArr2));
        }
        throw new IllegalArgumentException();
    }

    private PeriodFormatterBuilder appendSuffix(PeriodFieldAffix periodFieldAffix) {
        Object obj;
        Object obj2 = null;
        if (this.iElementPairs.size() > 0) {
            List<Object> list = this.iElementPairs;
            obj2 = list.get(list.size() - 2);
            List<Object> list2 = this.iElementPairs;
            obj = list2.get(list2.size() - 1);
        } else {
            obj = null;
        }
        if (obj2 == null || obj == null || obj2 != obj || !(obj2 instanceof FieldFormatter)) {
            throw new IllegalStateException("No field to apply suffix to");
        }
        clearPrefix();
        FieldFormatter fieldFormatter = new FieldFormatter((FieldFormatter) obj2, periodFieldAffix);
        List<Object> list3 = this.iElementPairs;
        list3.set(list3.size() - 2, fieldFormatter);
        List<Object> list4 = this.iElementPairs;
        list4.set(list4.size() - 1, fieldFormatter);
        this.iFieldFormatters[fieldFormatter.getFieldType()] = fieldFormatter;
        return this;
    }

    public PeriodFormatterBuilder appendSeparator(String str) {
        return appendSeparator(str, str, (String[]) null, true, true);
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsAfter(String str) {
        return appendSeparator(str, str, (String[]) null, false, true);
    }

    public PeriodFormatterBuilder appendSeparatorIfFieldsBefore(String str) {
        return appendSeparator(str, str, (String[]) null, true, false);
    }

    public PeriodFormatterBuilder appendSeparator(String str, String str2) {
        return appendSeparator(str, str2, (String[]) null, true, true);
    }

    public PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr) {
        return appendSeparator(str, str2, strArr, true, true);
    }

    private PeriodFormatterBuilder appendSeparator(String str, String str2, String[] strArr, boolean z, boolean z2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException();
        }
        clearPrefix();
        List<Object> list = this.iElementPairs;
        if (list.size() == 0) {
            if (z2 && !z) {
                Separator separator = new Separator(str, str2, strArr, Literal.EMPTY, Literal.EMPTY, z, z2);
                append0(separator, separator);
            }
            return this;
        }
        Separator separator2 = null;
        int size = list.size();
        while (true) {
            int i = size - 1;
            if (i < 0) {
                break;
            } else if (list.get(i) instanceof Separator) {
                separator2 = (Separator) list.get(i);
                list = list.subList(i + 1, list.size());
                break;
            } else {
                size = i - 1;
            }
        }
        List<Object> list2 = list;
        if (separator2 == null || list2.size() != 0) {
            Object[] createComposite = createComposite(list2);
            list2.clear();
            Separator separator3 = new Separator(str, str2, strArr, (PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1], z, z2);
            list2.add(separator3);
            list2.add(separator3);
            return this;
        }
        throw new IllegalStateException("Cannot have two adjacent separators");
    }

    private void clearPrefix() throws IllegalStateException {
        if (this.iPrefix == null) {
            this.iPrefix = null;
            return;
        }
        throw new IllegalStateException("Prefix not followed by field");
    }

    private PeriodFormatterBuilder append0(PeriodPrinter periodPrinter, PeriodParser periodParser) {
        this.iElementPairs.add(periodPrinter);
        this.iElementPairs.add(periodParser);
        boolean z = true;
        this.iNotPrinter = (periodPrinter == null) | this.iNotPrinter;
        boolean z2 = this.iNotParser;
        if (periodParser != null) {
            z = false;
        }
        this.iNotParser = z2 | z;
        return this;
    }

    private static PeriodFormatter toFormatter(List<Object> list, boolean z, boolean z2) {
        if (!z || !z2) {
            int size = list.size();
            if (size >= 2 && (list.get(0) instanceof Separator)) {
                Separator separator = (Separator) list.get(0);
                if (separator.iAfterParser == null && separator.iAfterPrinter == null) {
                    PeriodFormatter formatter = toFormatter(list.subList(2, size), z, z2);
                    Separator finish = separator.finish(formatter.getPrinter(), formatter.getParser());
                    return new PeriodFormatter(finish, finish);
                }
            }
            Object[] createComposite = createComposite(list);
            if (z) {
                return new PeriodFormatter((PeriodPrinter) null, (PeriodParser) createComposite[1]);
            }
            if (z2) {
                return new PeriodFormatter((PeriodPrinter) createComposite[0], (PeriodParser) null);
            }
            return new PeriodFormatter((PeriodPrinter) createComposite[0], (PeriodParser) createComposite[1]);
        }
        throw new IllegalStateException("Builder has created neither a printer nor a parser");
    }

    private static Object[] createComposite(List<Object> list) {
        int size = list.size();
        if (size == 0) {
            return new Object[]{Literal.EMPTY, Literal.EMPTY};
        } else if (size != 1) {
            Composite composite = new Composite(list);
            return new Object[]{composite, composite};
        } else {
            return new Object[]{list.get(0), list.get(1)};
        }
    }

    static abstract class IgnorableAffix implements PeriodFieldAffix {
        private volatile String[] iOtherAffixes;

        IgnorableAffix() {
        }

        public void finish(Set<PeriodFieldAffix> set) {
            if (this.iOtherAffixes == null) {
                int i = Integer.MAX_VALUE;
                String str = null;
                for (String str2 : getAffixes()) {
                    if (str2.length() < i) {
                        i = str2.length();
                        str = str2;
                    }
                }
                HashSet hashSet = new HashSet();
                for (PeriodFieldAffix next : set) {
                    if (next != null) {
                        for (String str3 : next.getAffixes()) {
                            if (str3.length() > i || (str3.equalsIgnoreCase(str) && !str3.equals(str))) {
                                hashSet.add(str3);
                            }
                        }
                    }
                }
                this.iOtherAffixes = (String[]) hashSet.toArray(new String[hashSet.size()]);
            }
        }

        /* access modifiers changed from: protected */
        public boolean matchesOtherAffix(int i, String str, int i2) {
            if (this.iOtherAffixes != null) {
                for (String str2 : this.iOtherAffixes) {
                    int length = str2.length();
                    if (i < length && str.regionMatches(true, i2, str2, 0, length)) {
                        return true;
                    }
                    if (i == length && str.regionMatches(false, i2, str2, 0, length)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class SimpleAffix extends IgnorableAffix {
        private final String iText;

        SimpleAffix(String str) {
            this.iText = str;
        }

        public int calculatePrintedLength(int i) {
            return this.iText.length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iText);
        }

        public void printTo(Writer writer, int i) throws IOException {
            writer.write(this.iText);
        }

        public int parse(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            return (!str.regionMatches(true, i, str2, 0, length) || matchesOtherAffix(length, str, i)) ? ~i : i + length;
        }

        public int scan(String str, int i) {
            String str2 = this.iText;
            int length = str2.length();
            int length2 = str.length();
            int i2 = i;
            while (i2 < length2) {
                if (str.regionMatches(true, i2, str2, 0, length) && !matchesOtherAffix(length, str, i2)) {
                    return i2;
                }
                switch (str.charAt(i2)) {
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        i2++;
                }
                return ~i;
            }
            return ~i;
        }

        public String[] getAffixes() {
            return new String[]{this.iText};
        }
    }

    static class PluralAffix extends IgnorableAffix {
        private final String iPluralText;
        private final String iSingularText;

        PluralAffix(String str, String str2) {
            this.iSingularText = str;
            this.iPluralText = str2;
        }

        public int calculatePrintedLength(int i) {
            return (i == 1 ? this.iSingularText : this.iPluralText).length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(i == 1 ? this.iSingularText : this.iPluralText);
        }

        public void printTo(Writer writer, int i) throws IOException {
            writer.write(i == 1 ? this.iSingularText : this.iPluralText);
        }

        public int parse(String str, int i) {
            String str2;
            int length;
            String str3 = this.iPluralText;
            String str4 = this.iSingularText;
            if (str3.length() < str4.length()) {
                str2 = str3;
                str3 = str4;
            } else {
                str2 = str4;
            }
            if (!str.regionMatches(true, i, str3, 0, str3.length()) || matchesOtherAffix(str3.length(), str, i)) {
                if (!str.regionMatches(true, i, str2, 0, str2.length()) || matchesOtherAffix(str2.length(), str, i)) {
                    return ~i;
                }
                length = str2.length();
            } else {
                length = str3.length();
            }
            return i + length;
        }

        public int scan(String str, int i) {
            String str2;
            String str3;
            String str4 = str;
            String str5 = this.iPluralText;
            String str6 = this.iSingularText;
            if (str5.length() < str6.length()) {
                str2 = str5;
                str3 = str6;
            } else {
                str3 = str5;
                str2 = str6;
            }
            int length = str3.length();
            int length2 = str2.length();
            int length3 = str.length();
            for (int i2 = i; i2 < length3; i2++) {
                if (str.regionMatches(true, i2, str3, 0, length) && !matchesOtherAffix(str3.length(), str, i2)) {
                    return i2;
                }
                if (str.regionMatches(true, i2, str2, 0, length2) && !matchesOtherAffix(str2.length(), str, i2)) {
                    return i2;
                }
            }
            return ~i;
        }

        public String[] getAffixes() {
            return new String[]{this.iSingularText, this.iPluralText};
        }
    }

    static class RegExAffix extends IgnorableAffix {
        private static final Comparator<String> LENGTH_DESC_COMPARATOR = new Comparator<String>() {
            public int compare(String str, String str2) {
                return str2.length() - str.length();
            }
        };
        private final Pattern[] iPatterns;
        private final String[] iSuffixes;
        private final String[] iSuffixesSortedDescByLength;

        RegExAffix(String[] strArr, String[] strArr2) {
            this.iSuffixes = (String[]) strArr2.clone();
            this.iPatterns = new Pattern[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                Pattern pattern = (Pattern) PeriodFormatterBuilder.PATTERNS.get(strArr[i]);
                if (pattern == null) {
                    pattern = Pattern.compile(strArr[i]);
                    PeriodFormatterBuilder.PATTERNS.putIfAbsent(strArr[i], pattern);
                }
                this.iPatterns[i] = pattern;
            }
            String[] strArr3 = (String[]) this.iSuffixes.clone();
            this.iSuffixesSortedDescByLength = strArr3;
            Arrays.sort(strArr3, LENGTH_DESC_COMPARATOR);
        }

        private int selectSuffixIndex(int i) {
            String valueOf = String.valueOf(i);
            int i2 = 0;
            while (true) {
                Pattern[] patternArr = this.iPatterns;
                if (i2 >= patternArr.length) {
                    return patternArr.length - 1;
                }
                if (patternArr[i2].matcher(valueOf).matches()) {
                    return i2;
                }
                i2++;
            }
        }

        public int calculatePrintedLength(int i) {
            return this.iSuffixes[selectSuffixIndex(i)].length();
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append(this.iSuffixes[selectSuffixIndex(i)]);
        }

        public void printTo(Writer writer, int i) throws IOException {
            writer.write(this.iSuffixes[selectSuffixIndex(i)]);
        }

        public int parse(String str, int i) {
            for (String str2 : this.iSuffixesSortedDescByLength) {
                if (str.regionMatches(true, i, str2, 0, str2.length()) && !matchesOtherAffix(str2.length(), str, i)) {
                    return i + str2.length();
                }
            }
            return ~i;
        }

        public int scan(String str, int i) {
            int length = str.length();
            for (int i2 = i; i2 < length; i2++) {
                for (String str2 : this.iSuffixesSortedDescByLength) {
                    if (str.regionMatches(true, i2, str2, 0, str2.length()) && !matchesOtherAffix(str2.length(), str, i2)) {
                        return i2;
                    }
                }
            }
            return ~i;
        }

        public String[] getAffixes() {
            return (String[]) this.iSuffixes.clone();
        }
    }

    static class CompositeAffix extends IgnorableAffix {
        private final PeriodFieldAffix iLeft;
        private final String[] iLeftRightCombinations;
        private final PeriodFieldAffix iRight;

        CompositeAffix(PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iLeft = periodFieldAffix;
            this.iRight = periodFieldAffix2;
            HashSet hashSet = new HashSet();
            for (String str : this.iLeft.getAffixes()) {
                for (String str2 : this.iRight.getAffixes()) {
                    hashSet.add(str + str2);
                }
            }
            this.iLeftRightCombinations = (String[]) hashSet.toArray(new String[hashSet.size()]);
        }

        public int calculatePrintedLength(int i) {
            return this.iLeft.calculatePrintedLength(i) + this.iRight.calculatePrintedLength(i);
        }

        public void printTo(StringBuffer stringBuffer, int i) {
            this.iLeft.printTo(stringBuffer, i);
            this.iRight.printTo(stringBuffer, i);
        }

        public void printTo(Writer writer, int i) throws IOException {
            this.iLeft.printTo(writer, i);
            this.iRight.printTo(writer, i);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
            r0 = r2.iRight.parse(r3, r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parse(java.lang.String r3, int r4) {
            /*
                r2 = this;
                org.joda.time.format.PeriodFormatterBuilder$PeriodFieldAffix r0 = r2.iLeft
                int r0 = r0.parse(r3, r4)
                if (r0 < 0) goto L_0x001d
                org.joda.time.format.PeriodFormatterBuilder$PeriodFieldAffix r1 = r2.iRight
                int r0 = r1.parse(r3, r0)
                if (r0 < 0) goto L_0x001d
                int r1 = r2.parse(r3, r0)
                int r1 = r1 - r0
                boolean r3 = r2.matchesOtherAffix(r1, r3, r4)
                if (r3 == 0) goto L_0x001d
                int r3 = ~r4
                return r3
            L_0x001d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.PeriodFormatterBuilder.CompositeAffix.parse(java.lang.String, int):int");
        }

        public int scan(String str, int i) {
            int scan;
            int scan2 = this.iLeft.scan(str, i);
            if (scan2 < 0 || ((scan = this.iRight.scan(str, this.iLeft.parse(str, scan2))) >= 0 && matchesOtherAffix(this.iRight.parse(str, scan) - scan2, str, i))) {
                return ~i;
            }
            return scan2 > 0 ? scan2 : scan;
        }

        public String[] getAffixes() {
            return (String[]) this.iLeftRightCombinations.clone();
        }
    }

    static class FieldFormatter implements PeriodPrinter, PeriodParser {
        private final FieldFormatter[] iFieldFormatters;
        private final int iFieldType;
        private final int iMaxParsedDigits;
        private final int iMinPrintedDigits;
        private final PeriodFieldAffix iPrefix;
        private final int iPrintZeroSetting;
        private final boolean iRejectSignedValues;
        private final PeriodFieldAffix iSuffix;

        FieldFormatter(int i, int i2, int i3, boolean z, int i4, FieldFormatter[] fieldFormatterArr, PeriodFieldAffix periodFieldAffix, PeriodFieldAffix periodFieldAffix2) {
            this.iMinPrintedDigits = i;
            this.iPrintZeroSetting = i2;
            this.iMaxParsedDigits = i3;
            this.iRejectSignedValues = z;
            this.iFieldType = i4;
            this.iFieldFormatters = fieldFormatterArr;
            this.iPrefix = periodFieldAffix;
            this.iSuffix = periodFieldAffix2;
        }

        FieldFormatter(FieldFormatter fieldFormatter, PeriodFieldAffix periodFieldAffix) {
            this.iMinPrintedDigits = fieldFormatter.iMinPrintedDigits;
            this.iPrintZeroSetting = fieldFormatter.iPrintZeroSetting;
            this.iMaxParsedDigits = fieldFormatter.iMaxParsedDigits;
            this.iRejectSignedValues = fieldFormatter.iRejectSignedValues;
            this.iFieldType = fieldFormatter.iFieldType;
            this.iFieldFormatters = fieldFormatter.iFieldFormatters;
            this.iPrefix = fieldFormatter.iPrefix;
            PeriodFieldAffix periodFieldAffix2 = fieldFormatter.iSuffix;
            this.iSuffix = periodFieldAffix2 != null ? new CompositeAffix(periodFieldAffix2, periodFieldAffix) : periodFieldAffix;
        }

        public void finish(FieldFormatter[] fieldFormatterArr) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (FieldFormatter fieldFormatter : fieldFormatterArr) {
                if (fieldFormatter != null && !equals(fieldFormatter)) {
                    hashSet.add(fieldFormatter.iPrefix);
                    hashSet2.add(fieldFormatter.iSuffix);
                }
            }
            PeriodFieldAffix periodFieldAffix = this.iPrefix;
            if (periodFieldAffix != null) {
                periodFieldAffix.finish(hashSet);
            }
            PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
            if (periodFieldAffix2 != null) {
                periodFieldAffix2.finish(hashSet2);
            }
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            if (i <= 0) {
                return 0;
            }
            return (this.iPrintZeroSetting == 4 || getFieldValue(readablePeriod) != Long.MAX_VALUE) ? 1 : 0;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue == Long.MAX_VALUE) {
                return 0;
            }
            int max = Math.max(FormatUtils.calculateDigitCount(fieldValue), this.iMinPrintedDigits);
            if (this.iFieldType >= 8) {
                max = Math.max(max, fieldValue < 0 ? 5 : 4) + 1;
                if (this.iFieldType == 9 && Math.abs(fieldValue) % 1000 == 0) {
                    max -= 4;
                }
                fieldValue /= 1000;
            }
            int i = (int) fieldValue;
            PeriodFieldAffix periodFieldAffix = this.iPrefix;
            if (periodFieldAffix != null) {
                max += periodFieldAffix.calculatePrintedLength(i);
            }
            PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
            return periodFieldAffix2 != null ? max + periodFieldAffix2.calculatePrintedLength(i) : max;
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                PeriodFieldAffix periodFieldAffix = this.iPrefix;
                if (periodFieldAffix != null) {
                    periodFieldAffix.printTo(stringBuffer, i);
                }
                int length = stringBuffer.length();
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.appendUnpaddedInteger(stringBuffer, i);
                } else {
                    FormatUtils.appendPaddedInteger(stringBuffer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    int abs = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || abs > 0) {
                        if (fieldValue < 0 && fieldValue > -1000) {
                            stringBuffer.insert(length, '-');
                        }
                        stringBuffer.append('.');
                        FormatUtils.appendPaddedInteger(stringBuffer, abs, 3);
                    }
                }
                PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
                if (periodFieldAffix2 != null) {
                    periodFieldAffix2.printTo(stringBuffer, i);
                }
            }
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            long fieldValue = getFieldValue(readablePeriod);
            if (fieldValue != Long.MAX_VALUE) {
                int i = (int) fieldValue;
                if (this.iFieldType >= 8) {
                    i = (int) (fieldValue / 1000);
                }
                PeriodFieldAffix periodFieldAffix = this.iPrefix;
                if (periodFieldAffix != null) {
                    periodFieldAffix.printTo(writer, i);
                }
                int i2 = this.iMinPrintedDigits;
                if (i2 <= 1) {
                    FormatUtils.writeUnpaddedInteger(writer, i);
                } else {
                    FormatUtils.writePaddedInteger(writer, i, i2);
                }
                if (this.iFieldType >= 8) {
                    int abs = (int) (Math.abs(fieldValue) % 1000);
                    if (this.iFieldType == 8 || abs > 0) {
                        writer.write(46);
                        FormatUtils.writePaddedInteger(writer, abs, 3);
                    }
                }
                PeriodFieldAffix periodFieldAffix2 = this.iSuffix;
                if (periodFieldAffix2 != null) {
                    periodFieldAffix2.printTo(writer, i);
                }
            }
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            int i2;
            int i3;
            PeriodFieldAffix periodFieldAffix;
            int i4;
            int i5;
            char charAt;
            ReadWritablePeriod readWritablePeriod2 = readWritablePeriod;
            String str2 = str;
            int i6 = i;
            boolean z = this.iPrintZeroSetting == 4;
            if (i6 >= str.length()) {
                return z ? ~i6 : i6;
            }
            PeriodFieldAffix periodFieldAffix2 = this.iPrefix;
            if (periodFieldAffix2 != null) {
                i6 = periodFieldAffix2.parse(str2, i6);
                if (i6 < 0) {
                    return !z ? ~i6 : i6;
                }
                z = true;
            }
            PeriodFieldAffix periodFieldAffix3 = this.iSuffix;
            int i7 = -1;
            if (periodFieldAffix3 == null || z) {
                i2 = -1;
            } else {
                i2 = periodFieldAffix3.scan(str2, i6);
                if (i2 < 0) {
                    return !z ? ~i2 : i2;
                }
                z = true;
            }
            if (!z && !isSupported(readWritablePeriod.getPeriodType(), this.iFieldType)) {
                return i6;
            }
            if (i2 > 0) {
                i3 = Math.min(this.iMaxParsedDigits, i2 - i6);
            } else {
                i3 = Math.min(this.iMaxParsedDigits, str.length() - i6);
            }
            int i8 = 0;
            boolean z2 = false;
            boolean z3 = false;
            while (i8 < i3) {
                int i9 = i6 + i8;
                char charAt2 = str2.charAt(i9);
                if (i8 == 0 && ((charAt2 == '-' || charAt2 == '+') && !this.iRejectSignedValues)) {
                    z3 = charAt2 == '-';
                    int i10 = i8 + 1;
                    if (i10 >= i3 || (charAt = str2.charAt(i9 + 1)) < '0' || charAt > '9') {
                        break;
                    }
                    if (z3) {
                        i8 = i10;
                    } else {
                        i6++;
                    }
                    i3 = Math.min(i3 + 1, str.length() - i6);
                } else {
                    if (charAt2 < '0' || charAt2 > '9') {
                        if ((charAt2 != '.' && charAt2 != ',') || (((i5 = this.iFieldType) != 8 && i5 != 9) || i7 >= 0)) {
                            break;
                        }
                        i3 = Math.min(i3 + 1, str.length() - i6);
                        i7 = i9 + 1;
                    } else {
                        z2 = true;
                    }
                    i8++;
                }
            }
            if (!z2) {
                return ~i6;
            }
            if (i2 >= 0 && i6 + i8 != i2) {
                return i6;
            }
            int i11 = this.iFieldType;
            if (i11 != 8 && i11 != 9) {
                setFieldValue(readWritablePeriod2, i11, parseInt(str2, i6, i8));
            } else if (i7 < 0) {
                setFieldValue(readWritablePeriod2, 6, parseInt(str2, i6, i8));
                setFieldValue(readWritablePeriod2, 7, 0);
            } else {
                int parseInt = parseInt(str2, i6, (i7 - i6) - 1);
                setFieldValue(readWritablePeriod2, 6, parseInt);
                int i12 = (i6 + i8) - i7;
                if (i12 <= 0) {
                    i4 = 0;
                } else {
                    if (i12 >= 3) {
                        i4 = parseInt(str2, i7, 3);
                    } else {
                        int parseInt2 = parseInt(str2, i7, i12);
                        i4 = i12 == 1 ? parseInt2 * 100 : parseInt2 * 10;
                    }
                    if (z3 || parseInt < 0) {
                        i4 = -i4;
                    }
                }
                setFieldValue(readWritablePeriod2, 7, i4);
            }
            int i13 = i6 + i8;
            return (i13 < 0 || (periodFieldAffix = this.iSuffix) == null) ? i13 : periodFieldAffix.parse(str2, i13);
        }

        private int parseInt(String str, int i, int i2) {
            if (i2 >= 10) {
                return Integer.parseInt(str.substring(i, i2 + i));
            }
            boolean z = false;
            if (i2 <= 0) {
                return 0;
            }
            int i3 = i + 1;
            char charAt = str.charAt(i);
            int i4 = i2 - 1;
            if (charAt == '-') {
                i4--;
                if (i4 < 0) {
                    return 0;
                }
                char charAt2 = str.charAt(i3);
                i3++;
                charAt = charAt2;
                z = true;
            }
            int i5 = charAt - '0';
            while (true) {
                int i6 = i4 - 1;
                if (i4 <= 0) {
                    break;
                }
                i3++;
                i5 = (((i5 << 3) + (i5 << 1)) + str.charAt(i3)) - 48;
                i4 = i6;
            }
            return z ? -i5 : i5;
        }

        /* access modifiers changed from: package-private */
        public long getFieldValue(ReadablePeriod readablePeriod) {
            PeriodType periodType;
            long j;
            int i;
            if (this.iPrintZeroSetting == 4) {
                periodType = null;
            } else {
                periodType = readablePeriod.getPeriodType();
            }
            if (periodType != null && !isSupported(periodType, this.iFieldType)) {
                return Long.MAX_VALUE;
            }
            switch (this.iFieldType) {
                case 0:
                    i = readablePeriod.get(DurationFieldType.years());
                    break;
                case 1:
                    i = readablePeriod.get(DurationFieldType.months());
                    break;
                case 2:
                    i = readablePeriod.get(DurationFieldType.weeks());
                    break;
                case 3:
                    i = readablePeriod.get(DurationFieldType.days());
                    break;
                case 4:
                    i = readablePeriod.get(DurationFieldType.hours());
                    break;
                case 5:
                    i = readablePeriod.get(DurationFieldType.minutes());
                    break;
                case 6:
                    i = readablePeriod.get(DurationFieldType.seconds());
                    break;
                case 7:
                    i = readablePeriod.get(DurationFieldType.millis());
                    break;
                case 8:
                case 9:
                    j = (((long) readablePeriod.get(DurationFieldType.seconds())) * 1000) + ((long) readablePeriod.get(DurationFieldType.millis()));
                    break;
                default:
                    return Long.MAX_VALUE;
            }
            j = (long) i;
            if (j == 0) {
                int i2 = this.iPrintZeroSetting;
                if (i2 == 1) {
                    if (isZero(readablePeriod)) {
                        FieldFormatter[] fieldFormatterArr = this.iFieldFormatters;
                        int i3 = this.iFieldType;
                        if (fieldFormatterArr[i3] == this) {
                            int min = Math.min(i3, 8);
                            while (true) {
                                min--;
                                if (min >= 0 && min <= 9) {
                                    if (!isSupported(periodType, min) || this.iFieldFormatters[min] == null) {
                                    }
                                }
                            }
                        }
                    }
                    return Long.MAX_VALUE;
                } else if (i2 == 2) {
                    if (isZero(readablePeriod)) {
                        FieldFormatter[] fieldFormatterArr2 = this.iFieldFormatters;
                        int i4 = this.iFieldType;
                        if (fieldFormatterArr2[i4] == this) {
                            for (int i5 = i4 + 1; i5 <= 9; i5++) {
                                if (isSupported(periodType, i5) && this.iFieldFormatters[i5] != null) {
                                    return Long.MAX_VALUE;
                                }
                            }
                        }
                    }
                    return Long.MAX_VALUE;
                } else if (i2 != 5) {
                    return j;
                } else {
                    return Long.MAX_VALUE;
                }
            }
            return j;
        }

        /* access modifiers changed from: package-private */
        public boolean isZero(ReadablePeriod readablePeriod) {
            int size = readablePeriod.size();
            for (int i = 0; i < size; i++) {
                if (readablePeriod.getValue(i) != 0) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean isSupported(PeriodType periodType, int i) {
            switch (i) {
                case 0:
                    return periodType.isSupported(DurationFieldType.years());
                case 1:
                    return periodType.isSupported(DurationFieldType.months());
                case 2:
                    return periodType.isSupported(DurationFieldType.weeks());
                case 3:
                    return periodType.isSupported(DurationFieldType.days());
                case 4:
                    return periodType.isSupported(DurationFieldType.hours());
                case 5:
                    return periodType.isSupported(DurationFieldType.minutes());
                case 6:
                    return periodType.isSupported(DurationFieldType.seconds());
                case 7:
                    return periodType.isSupported(DurationFieldType.millis());
                case 8:
                case 9:
                    return periodType.isSupported(DurationFieldType.seconds()) || periodType.isSupported(DurationFieldType.millis());
                default:
                    return false;
            }
        }

        /* access modifiers changed from: package-private */
        public void setFieldValue(ReadWritablePeriod readWritablePeriod, int i, int i2) {
            switch (i) {
                case 0:
                    readWritablePeriod.setYears(i2);
                    return;
                case 1:
                    readWritablePeriod.setMonths(i2);
                    return;
                case 2:
                    readWritablePeriod.setWeeks(i2);
                    return;
                case 3:
                    readWritablePeriod.setDays(i2);
                    return;
                case 4:
                    readWritablePeriod.setHours(i2);
                    return;
                case 5:
                    readWritablePeriod.setMinutes(i2);
                    return;
                case 6:
                    readWritablePeriod.setSeconds(i2);
                    return;
                case 7:
                    readWritablePeriod.setMillis(i2);
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: package-private */
        public int getFieldType() {
            return this.iFieldType;
        }
    }

    static class Literal implements PeriodPrinter, PeriodParser {
        static final Literal EMPTY = new Literal("");
        private final String iText;

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            return 0;
        }

        Literal(String str) {
            this.iText = str;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            return this.iText.length();
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            stringBuffer.append(this.iText);
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            writer.write(this.iText);
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            String str2 = this.iText;
            return str.regionMatches(true, i, str2, 0, str2.length()) ? i + this.iText.length() : ~i;
        }
    }

    static class Separator implements PeriodPrinter, PeriodParser {
        /* access modifiers changed from: private */
        public volatile PeriodParser iAfterParser;
        /* access modifiers changed from: private */
        public volatile PeriodPrinter iAfterPrinter;
        private final PeriodParser iBeforeParser;
        private final PeriodPrinter iBeforePrinter;
        private final String iFinalText;
        private final String[] iParsedForms;
        private final String iText;
        private final boolean iUseAfter;
        private final boolean iUseBefore;

        Separator(String str, String str2, String[] strArr, PeriodPrinter periodPrinter, PeriodParser periodParser, boolean z, boolean z2) {
            this.iText = str;
            this.iFinalText = str2;
            if ((str2 == null || str.equals(str2)) && (strArr == null || strArr.length == 0)) {
                this.iParsedForms = new String[]{str};
            } else {
                TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                treeSet.add(str);
                treeSet.add(str2);
                if (strArr != null) {
                    int length = strArr.length;
                    while (true) {
                        length--;
                        if (length < 0) {
                            break;
                        }
                        treeSet.add(strArr[length]);
                    }
                }
                ArrayList arrayList = new ArrayList(treeSet);
                Collections.reverse(arrayList);
                this.iParsedForms = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            this.iBeforePrinter = periodPrinter;
            this.iBeforeParser = periodParser;
            this.iUseBefore = z;
            this.iUseAfter = z2;
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            int countFieldsToPrint = this.iBeforePrinter.countFieldsToPrint(readablePeriod, i, locale);
            return countFieldsToPrint < i ? countFieldsToPrint + this.iAfterPrinter.countFieldsToPrint(readablePeriod, i, locale) : countFieldsToPrint;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            int i;
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            int calculatePrintedLength = periodPrinter.calculatePrintedLength(readablePeriod, locale) + periodPrinter2.calculatePrintedLength(readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                    return calculatePrintedLength;
                }
                if (this.iUseAfter) {
                    int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                    if (countFieldsToPrint <= 0) {
                        return calculatePrintedLength;
                    }
                    i = (countFieldsToPrint > 1 ? this.iText : this.iFinalText).length();
                } else {
                    i = this.iText.length();
                }
            } else if (!this.iUseAfter || periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) <= 0) {
                return calculatePrintedLength;
            } else {
                i = this.iText.length();
            }
            return calculatePrintedLength + i;
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(stringBuffer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            stringBuffer.append(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        stringBuffer.append(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                stringBuffer.append(this.iText);
            }
            periodPrinter2.printTo(stringBuffer, readablePeriod, locale);
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            PeriodPrinter periodPrinter = this.iBeforePrinter;
            PeriodPrinter periodPrinter2 = this.iAfterPrinter;
            periodPrinter.printTo(writer, readablePeriod, locale);
            if (this.iUseBefore) {
                if (periodPrinter.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                    if (this.iUseAfter) {
                        int countFieldsToPrint = periodPrinter2.countFieldsToPrint(readablePeriod, 2, locale);
                        if (countFieldsToPrint > 0) {
                            writer.write(countFieldsToPrint > 1 ? this.iText : this.iFinalText);
                        }
                    } else {
                        writer.write(this.iText);
                    }
                }
            } else if (this.iUseAfter && periodPrinter2.countFieldsToPrint(readablePeriod, 1, locale) > 0) {
                writer.write(this.iText);
            }
            periodPrinter2.printTo(writer, readablePeriod, locale);
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            String str2;
            ReadWritablePeriod readWritablePeriod2 = readWritablePeriod;
            String str3 = str;
            int i2 = i;
            Locale locale2 = locale;
            int parseInto = this.iBeforeParser.parseInto(readWritablePeriod2, str3, i2, locale2);
            if (parseInto < 0) {
                return parseInto;
            }
            int i3 = -1;
            boolean z = false;
            if (parseInto > i2) {
                String[] strArr = this.iParsedForms;
                int length = strArr.length;
                int i4 = 0;
                while (true) {
                    if (i4 < length) {
                        str2 = strArr[i4];
                        if (str2 == null || str2.length() == 0) {
                            break;
                        }
                        if (str.regionMatches(true, parseInto, str2, 0, str2.length())) {
                            break;
                        }
                        i4++;
                    } else {
                        break;
                    }
                }
                if (str2 == null) {
                    i3 = 0;
                } else {
                    i3 = str2.length();
                }
                parseInto += i3;
                z = true;
            }
            int parseInto2 = this.iAfterParser.parseInto(readWritablePeriod2, str3, parseInto, locale2);
            if (parseInto2 < 0) {
                return parseInto2;
            }
            if (!z || parseInto2 != parseInto || i3 <= 0) {
                return (parseInto2 <= parseInto || z || this.iUseBefore) ? parseInto2 : ~parseInto;
            }
            return ~parseInto;
        }

        /* access modifiers changed from: package-private */
        public Separator finish(PeriodPrinter periodPrinter, PeriodParser periodParser) {
            this.iAfterPrinter = periodPrinter;
            this.iAfterParser = periodParser;
            return this;
        }
    }

    static class Composite implements PeriodPrinter, PeriodParser {
        private final PeriodParser[] iParsers;
        private final PeriodPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.size() <= 0) {
                this.iPrinters = null;
            } else {
                this.iPrinters = (PeriodPrinter[]) arrayList.toArray(new PeriodPrinter[arrayList.size()]);
            }
            if (arrayList2.size() <= 0) {
                this.iParsers = null;
            } else {
                this.iParsers = (PeriodParser[]) arrayList2.toArray(new PeriodParser[arrayList2.size()]);
            }
        }

        public int countFieldsToPrint(ReadablePeriod readablePeriod, int i, Locale locale) {
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            int i2 = 0;
            while (i2 < i) {
                length--;
                if (length < 0) {
                    break;
                }
                i2 += periodPrinterArr[length].countFieldsToPrint(readablePeriod, Integer.MAX_VALUE, locale);
            }
            return i2;
        }

        public int calculatePrintedLength(ReadablePeriod readablePeriod, Locale locale) {
            PeriodPrinter[] periodPrinterArr = this.iPrinters;
            int length = periodPrinterArr.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                i += periodPrinterArr[length].calculatePrintedLength(readablePeriod, locale);
            }
        }

        public void printTo(StringBuffer stringBuffer, ReadablePeriod readablePeriod, Locale locale) {
            for (PeriodPrinter printTo : this.iPrinters) {
                printTo.printTo(stringBuffer, readablePeriod, locale);
            }
        }

        public void printTo(Writer writer, ReadablePeriod readablePeriod, Locale locale) throws IOException {
            for (PeriodPrinter printTo : this.iPrinters) {
                printTo.printTo(writer, readablePeriod, locale);
            }
        }

        public int parseInto(ReadWritablePeriod readWritablePeriod, String str, int i, Locale locale) {
            PeriodParser[] periodParserArr = this.iParsers;
            if (periodParserArr != null) {
                int length = periodParserArr.length;
                for (int i2 = 0; i2 < length && i >= 0; i2++) {
                    i = periodParserArr[i2].parseInto(readWritablePeriod, str, i, locale);
                }
                return i;
            }
            throw new UnsupportedOperationException();
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof PeriodPrinter) {
                    if (obj instanceof Composite) {
                        addArrayToList(list2, ((Composite) obj).iPrinters);
                    } else {
                        list2.add(obj);
                    }
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof PeriodParser) {
                    if (obj2 instanceof Composite) {
                        addArrayToList(list3, ((Composite) obj2).iParsers);
                    } else {
                        list3.add(obj2);
                    }
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object add : objArr) {
                    list.add(add);
                }
            }
        }
    }
}
