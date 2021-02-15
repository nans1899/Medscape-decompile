package org.joda.time.field;

import java.util.Locale;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.ReadablePartial;

public abstract class BaseDateTimeField extends DateTimeField {
    private final DateTimeFieldType iType;

    public abstract int get(long j);

    public abstract DurationField getDurationField();

    public int getLeapAmount(long j) {
        return 0;
    }

    public DurationField getLeapDurationField() {
        return null;
    }

    public abstract int getMaximumValue();

    public abstract int getMinimumValue();

    public abstract DurationField getRangeDurationField();

    public boolean isLeap(long j) {
        return false;
    }

    public final boolean isSupported() {
        return true;
    }

    public abstract long roundFloor(long j);

    public abstract long set(long j, int i);

    protected BaseDateTimeField(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            this.iType = dateTimeFieldType;
            return;
        }
        throw new IllegalArgumentException("The type must not be null");
    }

    public final DateTimeFieldType getType() {
        return this.iType;
    }

    public final String getName() {
        return this.iType.getName();
    }

    public String getAsText(long j, Locale locale) {
        return getAsText(get(j), locale);
    }

    public final String getAsText(long j) {
        return getAsText(j, (Locale) null);
    }

    public String getAsText(ReadablePartial readablePartial, int i, Locale locale) {
        return getAsText(i, locale);
    }

    public final String getAsText(ReadablePartial readablePartial, Locale locale) {
        return getAsText(readablePartial, readablePartial.get(getType()), locale);
    }

    public String getAsText(int i, Locale locale) {
        return Integer.toString(i);
    }

    public String getAsShortText(long j, Locale locale) {
        return getAsShortText(get(j), locale);
    }

    public final String getAsShortText(long j) {
        return getAsShortText(j, (Locale) null);
    }

    public String getAsShortText(ReadablePartial readablePartial, int i, Locale locale) {
        return getAsShortText(i, locale);
    }

    public final String getAsShortText(ReadablePartial readablePartial, Locale locale) {
        return getAsShortText(readablePartial, readablePartial.get(getType()), locale);
    }

    public String getAsShortText(int i, Locale locale) {
        return getAsText(i, locale);
    }

    public long add(long j, int i) {
        return getDurationField().add(j, i);
    }

    public long add(long j, long j2) {
        return getDurationField().add(j, j2);
    }

    public int[] add(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        if (i2 == 0) {
            return iArr;
        }
        DateTimeField dateTimeField = null;
        while (true) {
            if (i2 <= 0) {
                break;
            }
            int maximumValue = getMaximumValue(readablePartial, iArr);
            long j = (long) (iArr[i] + i2);
            if (j <= ((long) maximumValue)) {
                iArr[i] = (int) j;
                break;
            }
            if (dateTimeField == null) {
                if (i != 0) {
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                } else {
                    throw new IllegalArgumentException("Maximum value exceeded for add");
                }
            }
            i2 -= (maximumValue + 1) - iArr[i];
            iArr = dateTimeField.add(readablePartial, i - 1, iArr, 1);
            iArr[i] = getMinimumValue(readablePartial, iArr);
        }
        while (true) {
            if (i2 >= 0) {
                break;
            }
            int minimumValue = getMinimumValue(readablePartial, iArr);
            long j2 = (long) (iArr[i] + i2);
            if (j2 >= ((long) minimumValue)) {
                iArr[i] = (int) j2;
                break;
            }
            if (dateTimeField == null) {
                if (i != 0) {
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                } else {
                    throw new IllegalArgumentException("Maximum value exceeded for add");
                }
            }
            i2 -= (minimumValue - 1) - iArr[i];
            iArr = dateTimeField.add(readablePartial, i - 1, iArr, -1);
            iArr[i] = getMaximumValue(readablePartial, iArr);
        }
        return set(readablePartial, i, iArr, iArr[i]);
    }

    public int[] addWrapPartial(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        if (i2 == 0) {
            return iArr;
        }
        DateTimeField dateTimeField = null;
        while (true) {
            if (i2 <= 0) {
                break;
            }
            int maximumValue = getMaximumValue(readablePartial, iArr);
            long j = (long) (iArr[i] + i2);
            if (j <= ((long) maximumValue)) {
                iArr[i] = (int) j;
                break;
            }
            if (dateTimeField == null) {
                if (i == 0) {
                    i2 -= (maximumValue + 1) - iArr[i];
                    iArr[i] = getMinimumValue(readablePartial, iArr);
                } else {
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                }
            }
            i2 -= (maximumValue + 1) - iArr[i];
            iArr = dateTimeField.addWrapPartial(readablePartial, i - 1, iArr, 1);
            iArr[i] = getMinimumValue(readablePartial, iArr);
        }
        while (true) {
            if (i2 >= 0) {
                break;
            }
            int minimumValue = getMinimumValue(readablePartial, iArr);
            long j2 = (long) (iArr[i] + i2);
            if (j2 >= ((long) minimumValue)) {
                iArr[i] = (int) j2;
                break;
            }
            if (dateTimeField == null) {
                if (i == 0) {
                    r12 = i2 - ((minimumValue - 1) - iArr[i]);
                    iArr[i] = getMaximumValue(readablePartial, iArr);
                } else {
                    dateTimeField = readablePartial.getField(i - 1);
                    if (getRangeDurationField().getType() != dateTimeField.getDurationField().getType()) {
                        throw new IllegalArgumentException("Fields invalid for add");
                    }
                }
            }
            r12 = i2 - ((minimumValue - 1) - iArr[i]);
            iArr = dateTimeField.addWrapPartial(readablePartial, i - 1, iArr, -1);
            iArr[i] = getMaximumValue(readablePartial, iArr);
        }
        return set(readablePartial, i, iArr, iArr[i]);
    }

    public long addWrapField(long j, int i) {
        return set(j, FieldUtils.getWrappedValue(get(j), i, getMinimumValue(j), getMaximumValue(j)));
    }

    public int[] addWrapField(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        return set(readablePartial, i, iArr, FieldUtils.getWrappedValue(iArr[i], i2, getMinimumValue(readablePartial), getMaximumValue(readablePartial)));
    }

    public int getDifference(long j, long j2) {
        return getDurationField().getDifference(j, j2);
    }

    public long getDifferenceAsLong(long j, long j2) {
        return getDurationField().getDifferenceAsLong(j, j2);
    }

    public int[] set(ReadablePartial readablePartial, int i, int[] iArr, int i2) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i2, getMinimumValue(readablePartial, iArr), getMaximumValue(readablePartial, iArr));
        iArr[i] = i2;
        while (true) {
            i++;
            if (i >= readablePartial.size()) {
                return iArr;
            }
            DateTimeField field = readablePartial.getField(i);
            if (iArr[i] > field.getMaximumValue(readablePartial, iArr)) {
                iArr[i] = field.getMaximumValue(readablePartial, iArr);
            }
            if (iArr[i] < field.getMinimumValue(readablePartial, iArr)) {
                iArr[i] = field.getMinimumValue(readablePartial, iArr);
            }
        }
    }

    public long set(long j, String str, Locale locale) {
        return set(j, convertText(str, locale));
    }

    public final long set(long j, String str) {
        return set(j, str, (Locale) null);
    }

    public int[] set(ReadablePartial readablePartial, int i, int[] iArr, String str, Locale locale) {
        return set(readablePartial, i, iArr, convertText(str, locale));
    }

    /* access modifiers changed from: protected */
    public int convertText(String str, Locale locale) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            throw new IllegalFieldValueException(getType(), str);
        }
    }

    public int getMinimumValue(long j) {
        return getMinimumValue();
    }

    public int getMinimumValue(ReadablePartial readablePartial) {
        return getMinimumValue();
    }

    public int getMinimumValue(ReadablePartial readablePartial, int[] iArr) {
        return getMinimumValue(readablePartial);
    }

    public int getMaximumValue(long j) {
        return getMaximumValue();
    }

    public int getMaximumValue(ReadablePartial readablePartial) {
        return getMaximumValue();
    }

    public int getMaximumValue(ReadablePartial readablePartial, int[] iArr) {
        return getMaximumValue(readablePartial);
    }

    public int getMaximumTextLength(Locale locale) {
        int maximumValue = getMaximumValue();
        if (maximumValue >= 0) {
            if (maximumValue < 10) {
                return 1;
            }
            if (maximumValue < 100) {
                return 2;
            }
            if (maximumValue < 1000) {
                return 3;
            }
        }
        return Integer.toString(maximumValue).length();
    }

    public int getMaximumShortTextLength(Locale locale) {
        return getMaximumTextLength(locale);
    }

    public long roundCeiling(long j) {
        long roundFloor = roundFloor(j);
        return roundFloor != j ? add(roundFloor, 1) : j;
    }

    public long roundHalfFloor(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        return j - roundFloor <= roundCeiling - j ? roundFloor : roundCeiling;
    }

    public long roundHalfCeiling(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        return roundCeiling - j <= j - roundFloor ? roundCeiling : roundFloor;
    }

    public long roundHalfEven(long j) {
        long roundFloor = roundFloor(j);
        long roundCeiling = roundCeiling(j);
        long j2 = j - roundFloor;
        long j3 = roundCeiling - j;
        if (j2 < j3) {
            return roundFloor;
        }
        return (j3 >= j2 && (get(roundCeiling) & 1) != 0) ? roundFloor : roundCeiling;
    }

    public long remainder(long j) {
        return j - roundFloor(j);
    }

    public String toString() {
        return "DateTimeField[" + getName() + ']';
    }
}
