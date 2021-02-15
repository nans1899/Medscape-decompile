package com.wbmd.wbmdcommons.utils;

import com.appboy.Constants;
import com.facebook.appevents.AppEventsConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class UTCConverter {
    public static String getUTCStringFromLocalCalendar(Calendar calendar) {
        return getFormattedDateTimeString(getUtcCalendarFromLocalCalendar(calendar));
    }

    public static String getLocalTimeStringFromUtcCalender(Calendar calendar) {
        TimeZone timeZone = TimeZone.getDefault();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(calendar.getTimeInMillis());
        instance.setTimeZone(timeZone);
        return getFormattedDateTimeString(getLocalCalendarFromUtcCalendar(instance));
    }

    public static Calendar getLocalCalendarFromUtcString(String str) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z").parse(str));
        instance.setTimeZone(TimeZone.getDefault());
        return instance;
    }

    public static Calendar getUtcCalendarFromUtcString(String str) throws ParseException {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z").parse(str));
        instance.setTimeZone(TimeZone.getTimeZone("UTC"));
        return instance;
    }

    public static Calendar getUtcCalendarFromLocalCalendar(Calendar calendar) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(calendar.getTimeInMillis());
        instance.setTimeZone(timeZone);
        return instance;
    }

    public static Calendar getLocalCalendarFromUtcCalendar(Calendar calendar) {
        TimeZone timeZone = TimeZone.getDefault();
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(calendar.getTimeInMillis());
        instance.setTimeZone(timeZone);
        return instance;
    }

    public static String getLocalHourMinStringFromUtcString(String str) {
        if (str == null) {
            return "";
        }
        try {
            return getFormattedHourMinString(getLocalCalendarFromUtcString(str));
        } catch (ParseException unused) {
            return "";
        }
    }

    private static String getFormattedHourMinString(Calendar calendar) {
        return new SimpleDateFormat(Constants.DEFAULT_TWELVE_HOUR_TIME_FORMAT).format(getLocalCalendarFromUtcCalendar(calendar).getTime());
    }

    private static String getFormattedDateTimeString(Calendar calendar) {
        int i = calendar.get(5);
        int i2 = calendar.get(1);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        int i5 = calendar.get(13);
        String formatTimeString = formatTimeString(calendar.get(2) + 1);
        return String.format("%s/%s/%s %s:%s:%s %s", new Object[]{formatTimeString(i), formatTimeString, Integer.valueOf(i2), formatTimeString(i3), formatTimeString(i4), formatTimeString(i5), calendar.getTimeZone().getDisplayName()});
    }

    private static String formatTimeString(int i) {
        if (i > 9) {
            return String.valueOf(i);
        }
        return AppEventsConstants.EVENT_PARAM_VALUE_NO + i;
    }

    public static Calendar convertUTCStampToCalendar(String str, String str2) {
        Calendar instance = Calendar.getInstance();
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            try {
                instance.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z").parse(str));
                instance.setTimeZone(TimeZone.getTimeZone(str2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}
