package org.simpleframework.xml.transform;

import java.util.TimeZone;

class TimeZoneTransform implements Transform<TimeZone> {
    TimeZoneTransform() {
    }

    public TimeZone read(String str) {
        return TimeZone.getTimeZone(str);
    }

    public String write(TimeZone timeZone) {
        return timeZone.getID();
    }
}
