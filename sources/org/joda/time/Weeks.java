package org.joda.time;

import androidx.exifinterface.media.ExifInterface;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Weeks extends BaseSingleFieldPeriod {
    public static final Weeks MAX_VALUE = new Weeks(Integer.MAX_VALUE);
    public static final Weeks MIN_VALUE = new Weeks(Integer.MIN_VALUE);
    public static final Weeks ONE = new Weeks(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.weeks());
    public static final Weeks THREE = new Weeks(3);
    public static final Weeks TWO = new Weeks(2);
    public static final Weeks ZERO = new Weeks(0);
    private static final long serialVersionUID = 87525275727380866L;

    public static Weeks weeks(int i) {
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
            return i != 3 ? new Weeks(i) : THREE;
        }
        return TWO;
    }

    public static Weeks weeksBetween(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        return weeks(BaseSingleFieldPeriod.between(readableInstant, readableInstant2, DurationFieldType.weeks()));
    }

    public static Weeks weeksBetween(ReadablePartial readablePartial, ReadablePartial readablePartial2) {
        if (!(readablePartial instanceof LocalDate) || !(readablePartial2 instanceof LocalDate)) {
            return weeks(BaseSingleFieldPeriod.between(readablePartial, readablePartial2, (ReadablePeriod) ZERO));
        }
        return weeks(DateTimeUtils.getChronology(readablePartial.getChronology()).weeks().getDifference(((LocalDate) readablePartial2).getLocalMillis(), ((LocalDate) readablePartial).getLocalMillis()));
    }

    public static Weeks weeksIn(ReadableInterval readableInterval) {
        if (readableInterval == null) {
            return ZERO;
        }
        return weeks(BaseSingleFieldPeriod.between((ReadableInstant) readableInterval.getStart(), (ReadableInstant) readableInterval.getEnd(), DurationFieldType.weeks()));
    }

    public static Weeks standardWeeksIn(ReadablePeriod readablePeriod) {
        return weeks(BaseSingleFieldPeriod.standardPeriodIn(readablePeriod, 604800000));
    }

    @FromString
    public static Weeks parseWeeks(String str) {
        if (str == null) {
            return ZERO;
        }
        return weeks(PARSER.parsePeriod(str).getWeeks());
    }

    private Weeks(int i) {
        super(i);
    }

    private Object readResolve() {
        return weeks(getValue());
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.weeks();
    }

    public PeriodType getPeriodType() {
        return PeriodType.weeks();
    }

    public Days toStandardDays() {
        return Days.days(FieldUtils.safeMultiply(getValue(), 7));
    }

    public Hours toStandardHours() {
        return Hours.hours(FieldUtils.safeMultiply(getValue(), 168));
    }

    public Minutes toStandardMinutes() {
        return Minutes.minutes(FieldUtils.safeMultiply(getValue(), (int) DateTimeConstants.MINUTES_PER_WEEK));
    }

    public Seconds toStandardSeconds() {
        return Seconds.seconds(FieldUtils.safeMultiply(getValue(), (int) DateTimeConstants.SECONDS_PER_WEEK));
    }

    public Duration toStandardDuration() {
        return new Duration(((long) getValue()) * 604800000);
    }

    public int getWeeks() {
        return getValue();
    }

    public Weeks plus(int i) {
        return i == 0 ? this : weeks(FieldUtils.safeAdd(getValue(), i));
    }

    public Weeks plus(Weeks weeks) {
        return weeks == null ? this : plus(weeks.getValue());
    }

    public Weeks minus(int i) {
        return plus(FieldUtils.safeNegate(i));
    }

    public Weeks minus(Weeks weeks) {
        return weeks == null ? this : minus(weeks.getValue());
    }

    public Weeks multipliedBy(int i) {
        return weeks(FieldUtils.safeMultiply(getValue(), i));
    }

    public Weeks dividedBy(int i) {
        return i == 1 ? this : weeks(getValue() / i);
    }

    public Weeks negated() {
        return weeks(FieldUtils.safeNegate(getValue()));
    }

    public boolean isGreaterThan(Weeks weeks) {
        if (weeks == null) {
            return getValue() > 0;
        }
        if (getValue() > weeks.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isLessThan(Weeks weeks) {
        if (weeks == null) {
            return getValue() < 0;
        }
        if (getValue() < weeks.getValue()) {
            return true;
        }
        return false;
    }

    @ToString
    public String toString() {
        return "P" + String.valueOf(getValue()) + ExifInterface.LONGITUDE_WEST;
    }
}
