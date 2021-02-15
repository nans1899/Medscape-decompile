package org.simpleframework.xml.transform;

import java.text.SimpleDateFormat;
import java.util.Date;

enum DateType {
    FULL("yyyy-MM-dd HH:mm:ss.S z"),
    LONG("yyyy-MM-dd HH:mm:ss z"),
    NORMAL("yyyy-MM-dd z"),
    SHORT("yyyy-MM-dd");
    
    private DateFormat format;

    private DateType(String str) {
        this.format = new DateFormat(str);
    }

    private DateFormat getFormat() {
        return this.format;
    }

    public static String getText(Date date) throws Exception {
        return FULL.getFormat().getText(date);
    }

    public static Date getDate(String str) throws Exception {
        return getType(str).getFormat().getDate(str);
    }

    public static DateType getType(String str) {
        int length = str.length();
        if (length > 23) {
            return FULL;
        }
        if (length > 20) {
            return LONG;
        }
        if (length > 11) {
            return NORMAL;
        }
        return SHORT;
    }

    private static class DateFormat {
        private SimpleDateFormat format;

        public DateFormat(String str) {
            this.format = new SimpleDateFormat(str);
        }

        public synchronized String getText(Date date) throws Exception {
            return this.format.format(date);
        }

        public synchronized Date getDate(String str) throws Exception {
            return this.format.parse(str);
        }
    }
}
