package org.threeten.bp.temporal;

import java.io.Serializable;
import org.threeten.bp.DateTimeException;

public final class ValueRange implements Serializable {
    private static final long serialVersionUID = -7317881728594519368L;
    private final long maxLargest;
    private final long maxSmallest;
    private final long minLargest;
    private final long minSmallest;

    public static ValueRange of(long j, long j2) {
        if (j <= j2) {
            return new ValueRange(j, j, j2, j2);
        }
        throw new IllegalArgumentException("Minimum value must be less than maximum value");
    }

    public static ValueRange of(long j, long j2, long j3) {
        return of(j, j, j2, j3);
    }

    public static ValueRange of(long j, long j2, long j3, long j4) {
        if (j > j2) {
            throw new IllegalArgumentException("Smallest minimum value must be less than largest minimum value");
        } else if (j3 > j4) {
            throw new IllegalArgumentException("Smallest maximum value must be less than largest maximum value");
        } else if (j2 <= j4) {
            return new ValueRange(j, j2, j3, j4);
        } else {
            throw new IllegalArgumentException("Minimum value must be less than maximum value");
        }
    }

    private ValueRange(long j, long j2, long j3, long j4) {
        this.minSmallest = j;
        this.minLargest = j2;
        this.maxSmallest = j3;
        this.maxLargest = j4;
    }

    public boolean isFixed() {
        return this.minSmallest == this.minLargest && this.maxSmallest == this.maxLargest;
    }

    public long getMinimum() {
        return this.minSmallest;
    }

    public long getLargestMinimum() {
        return this.minLargest;
    }

    public long getSmallestMaximum() {
        return this.maxSmallest;
    }

    public long getMaximum() {
        return this.maxLargest;
    }

    public boolean isIntValue() {
        return getMinimum() >= -2147483648L && getMaximum() <= 2147483647L;
    }

    public boolean isValidValue(long j) {
        return j >= getMinimum() && j <= getMaximum();
    }

    public boolean isValidIntValue(long j) {
        return isIntValue() && isValidValue(j);
    }

    public long checkValidValue(long j, TemporalField temporalField) {
        if (isValidValue(j)) {
            return j;
        }
        if (temporalField != null) {
            throw new DateTimeException("Invalid value for " + temporalField + " (valid values " + this + "): " + j);
        }
        throw new DateTimeException("Invalid value (valid values " + this + "): " + j);
    }

    public int checkValidIntValue(long j, TemporalField temporalField) {
        if (isValidIntValue(j)) {
            return (int) j;
        }
        throw new DateTimeException("Invalid int value for " + temporalField + ": " + j);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ValueRange)) {
            return false;
        }
        ValueRange valueRange = (ValueRange) obj;
        if (this.minSmallest == valueRange.minSmallest && this.minLargest == valueRange.minLargest && this.maxSmallest == valueRange.maxSmallest && this.maxLargest == valueRange.maxLargest) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.minSmallest;
        long j2 = this.minLargest;
        long j3 = this.maxSmallest;
        long j4 = this.maxLargest;
        long j5 = ((((((j + j2) << ((int) (j2 + 16))) >> ((int) (j3 + 48))) << ((int) (j3 + 32))) >> ((int) (32 + j4))) << ((int) (j4 + 48))) >> 16;
        return (int) (j5 ^ (j5 >>> 32));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.minSmallest);
        if (this.minSmallest != this.minLargest) {
            sb.append('/');
            sb.append(this.minLargest);
        }
        sb.append(" - ");
        sb.append(this.maxSmallest);
        if (this.maxSmallest != this.maxLargest) {
            sb.append('/');
            sb.append(this.maxLargest);
        }
        return sb.toString();
    }
}
