package org.joda.time;

import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Years extends BaseSingleFieldPeriod {
    public static final Years MAX_VALUE = new Years(Integer.MAX_VALUE);
    public static final Years MIN_VALUE = new Years(Integer.MIN_VALUE);
    public static final Years ONE = new Years(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.years());
    public static final Years THREE = new Years(3);
    public static final Years TWO = new Years(2);
    public static final Years ZERO = new Years(0);
    private static final long serialVersionUID = 87525275727380868L;

    public static Years years(int i) {
        if (i == Integer.MIN_VALUE) {
            return MIN_VALUE;
        }
        if (i == Integer.MAX_VALUE) {
            return MAX_VALUE;
        }
        if (i == 0) {
            return ZERO;
        }
        if (i == 1) {
            return ONE;
        }
        if (i != 2) {
            return i != 3 ? new Years(i) : THREE;
        }
        return TWO;
    }

    public static Years yearsBetween(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        return years(BaseSingleFieldPeriod.between(readableInstant, readableInstant2, DurationFieldType.years()));
    }

    public static Years yearsBetween(ReadablePartial readablePartial, ReadablePartial readablePartial2) {
        if (!(readablePartial instanceof LocalDate) || !(readablePartial2 instanceof LocalDate)) {
            return years(BaseSingleFieldPeriod.between(readablePartial, readablePartial2, (ReadablePeriod) ZERO));
        }
        return years(DateTimeUtils.getChronology(readablePartial.getChronology()).years().getDifference(((LocalDate) readablePartial2).getLocalMillis(), ((LocalDate) readablePartial).getLocalMillis()));
    }

    public static Years yearsIn(ReadableInterval readableInterval) {
        if (readableInterval == null) {
            return ZERO;
        }
        return years(BaseSingleFieldPeriod.between((ReadableInstant) readableInterval.getStart(), (ReadableInstant) readableInterval.getEnd(), DurationFieldType.years()));
    }

    @FromString
    public static Years parseYears(String str) {
        if (str == null) {
            return ZERO;
        }
        return years(PARSER.parsePeriod(str).getYears());
    }

    private Years(int i) {
        super(i);
    }

    private Object readResolve() {
        return years(getValue());
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.years();
    }

    public PeriodType getPeriodType() {
        return PeriodType.years();
    }

    public int getYears() {
        return getValue();
    }

    public Years plus(int i) {
        return i == 0 ? this : years(FieldUtils.safeAdd(getValue(), i));
    }

    public Years plus(Years years) {
        return years == null ? this : plus(years.getValue());
    }

    public Years minus(int i) {
        return plus(FieldUtils.safeNegate(i));
    }

    public Years minus(Years years) {
        return years == null ? this : minus(years.getValue());
    }

    public Years multipliedBy(int i) {
        return years(FieldUtils.safeMultiply(getValue(), i));
    }

    public Years dividedBy(int i) {
        return i == 1 ? this : years(getValue() / i);
    }

    public Years negated() {
        return years(FieldUtils.safeNegate(getValue()));
    }

    public boolean isGreaterThan(Years years) {
        if (years == null) {
            return getValue() > 0;
        }
        if (getValue() > years.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isLessThan(Years years) {
        if (years == null) {
            return getValue() < 0;
        }
        if (getValue() < years.getValue()) {
            return true;
        }
        return false;
    }

    @ToString
    public String toString() {
        return "P" + String.valueOf(getValue()) + "Y";
    }
}
