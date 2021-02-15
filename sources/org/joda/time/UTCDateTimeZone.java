package org.joda.time;

import java.util.SimpleTimeZone;
import java.util.TimeZone;

final class UTCDateTimeZone extends DateTimeZone {
    static final DateTimeZone INSTANCE = new UTCDateTimeZone();
    private static final long serialVersionUID = -3513011772763289092L;

    public String getNameKey(long j) {
        return "UTC";
    }

    public int getOffset(long j) {
        return 0;
    }

    public int getOffsetFromLocal(long j) {
        return 0;
    }

    public int getStandardOffset(long j) {
        return 0;
    }

    public boolean isFixed() {
        return true;
    }

    public long nextTransition(long j) {
        return j;
    }

    public long previousTransition(long j) {
        return j;
    }

    public UTCDateTimeZone() {
        super("UTC");
    }

    public TimeZone toTimeZone() {
        return new SimpleTimeZone(0, getID());
    }

    public boolean equals(Object obj) {
        return obj instanceof UTCDateTimeZone;
    }

    public int hashCode() {
        return getID().hashCode();
    }
}
