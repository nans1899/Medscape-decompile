package org.joda.time;

import androidx.exifinterface.media.ExifInterface;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.base.BaseSingleFieldPeriod;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;

public final class Seconds extends BaseSingleFieldPeriod {
    public static final Seconds MAX_VALUE = new Seconds(Integer.MAX_VALUE);
    public static final Seconds MIN_VALUE = new Seconds(Integer.MIN_VALUE);
    public static final Seconds ONE = new Seconds(1);
    private static final PeriodFormatter PARSER = ISOPeriodFormat.standard().withParseType(PeriodType.seconds());
    public static final Seconds THREE = new Seconds(3);
    public static final Seconds TWO = new Seconds(2);
    public static final Seconds ZERO = new Seconds(0);
    private static final long serialVersionUID = 87525275727380862L;

    public static Seconds seconds(int i) {
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
            return i != 3 ? new Seconds(i) : THREE;
        }
        return TWO;
    }

    public static Seconds secondsBetween(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        return seconds(BaseSingleFieldPeriod.between(readableInstant, readableInstant2, DurationFieldType.seconds()));
    }

    public static Seconds secondsBetween(ReadablePartial readablePartial, ReadablePartial readablePartial2) {
        if (!(readablePartial instanceof LocalTime) || !(readablePartial2 instanceof LocalTime)) {
            return seconds(BaseSingleFieldPeriod.between(readablePartial, readablePartial2, (ReadablePeriod) ZERO));
        }
        return seconds(DateTimeUtils.getChronology(readablePartial.getChronology()).seconds().getDifference(((LocalTime) readablePartial2).getLocalMillis(), ((LocalTime) readablePartial).getLocalMillis()));
    }

    public static Seconds secondsIn(ReadableInterval readableInterval) {
        if (readableInterval == null) {
            return ZERO;
        }
        return seconds(BaseSingleFieldPeriod.between((ReadableInstant) readableInterval.getStart(), (ReadableInstant) readableInterval.getEnd(), DurationFieldType.seconds()));
    }

    public static Seconds standardSecondsIn(ReadablePeriod readablePeriod) {
        return seconds(BaseSingleFieldPeriod.standardPeriodIn(readablePeriod, 1000));
    }

    @FromString
    public static Seconds parseSeconds(String str) {
        if (str == null) {
            return ZERO;
        }
        return seconds(PARSER.parsePeriod(str).getSeconds());
    }

    private Seconds(int i) {
        super(i);
    }

    private Object readResolve() {
        return seconds(getValue());
    }

    public DurationFieldType getFieldType() {
        return DurationFieldType.seconds();
    }

    public PeriodType getPeriodType() {
        return PeriodType.seconds();
    }

    public Weeks toStandardWeeks() {
        return Weeks.weeks(getValue() / DateTimeConstants.SECONDS_PER_WEEK);
    }

    public Days toStandardDays() {
        return Days.days(getValue() / DateTimeConstants.SECONDS_PER_DAY);
    }

    public Hours toStandardHours() {
        return Hours.hours(getValue() / DateTimeConstants.SECONDS_PER_HOUR);
    }

    public Minutes toStandardMinutes() {
        return Minutes.minutes(getValue() / 60);
    }

    public Duration toStandardDuration() {
        return new Duration(((long) getValue()) * 1000);
    }

    public int getSeconds() {
        return getValue();
    }

    public Seconds plus(int i) {
        return i == 0 ? this : seconds(FieldUtils.safeAdd(getValue(), i));
    }

    public Seconds plus(Seconds seconds) {
        return seconds == null ? this : plus(seconds.getValue());
    }

    public Seconds minus(int i) {
        return plus(FieldUtils.safeNegate(i));
    }

    public Seconds minus(Seconds seconds) {
        return seconds == null ? this : minus(seconds.getValue());
    }

    public Seconds multipliedBy(int i) {
        return seconds(FieldUtils.safeMultiply(getValue(), i));
    }

    public Seconds dividedBy(int i) {
        return i == 1 ? this : seconds(getValue() / i);
    }

    public Seconds negated() {
        return seconds(FieldUtils.safeNegate(getValue()));
    }

    public boolean isGreaterThan(Seconds seconds) {
        if (seconds == null) {
            return getValue() > 0;
        }
        if (getValue() > seconds.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isLessThan(Seconds seconds) {
        if (seconds == null) {
            return getValue() < 0;
        }
        if (getValue() < seconds.getValue()) {
            return true;
        }
        return false;
    }

    @ToString
    public String toString() {
        return "PT" + String.valueOf(getValue()) + ExifInterface.LATITUDE_SOUTH;
    }
}
