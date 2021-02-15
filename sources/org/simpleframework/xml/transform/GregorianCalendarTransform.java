package org.simpleframework.xml.transform;

import java.util.Date;
import java.util.GregorianCalendar;

class GregorianCalendarTransform implements Transform<GregorianCalendar> {
    private final DateTransform transform;

    public GregorianCalendarTransform() throws Exception {
        this(Date.class);
    }

    public GregorianCalendarTransform(Class cls) throws Exception {
        this.transform = new DateTransform(cls);
    }

    public GregorianCalendar read(String str) throws Exception {
        return read(this.transform.read(str));
    }

    private GregorianCalendar read(Date date) throws Exception {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (date != null) {
            gregorianCalendar.setTime(date);
        }
        return gregorianCalendar;
    }

    public String write(GregorianCalendar gregorianCalendar) throws Exception {
        return this.transform.write(gregorianCalendar.getTime());
    }
}
