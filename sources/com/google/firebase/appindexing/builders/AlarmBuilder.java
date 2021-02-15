package com.google.firebase.appindexing.builders;

import com.facebook.share.internal.ShareConstants;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class AlarmBuilder extends IndexableBuilder<AlarmBuilder> {
    public static final String FRIDAY = "Friday";
    public static final String MONDAY = "Monday";
    public static final String SATURDAY = "Saturday";
    public static final String SUNDAY = "Sunday";
    public static final String THURSDAY = "Thursday";
    public static final String TUESDAY = "Tuesday";
    public static final String WEDNESDAY = "Wednesday";

    AlarmBuilder() {
        super("Alarm");
    }

    public final AlarmBuilder setHour(int i) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException("Invalid alarm hour");
        }
        return (AlarmBuilder) put("hour", (long) i);
    }

    public final AlarmBuilder setMinute(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException("Invalid alarm minute");
        }
        return (AlarmBuilder) put("minute", (long) i);
    }

    public final AlarmBuilder setMessage(String str) {
        return (AlarmBuilder) put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, str);
    }

    public final AlarmBuilder setRingtone(String str) {
        return (AlarmBuilder) put("ringtone", str);
    }

    public final AlarmBuilder setVibrate(boolean z) {
        return (AlarmBuilder) put("vibrate", z);
    }

    public final AlarmBuilder setEnabled(boolean z) {
        return (AlarmBuilder) put("enabled", z);
    }

    public final AlarmBuilder setIdentifier(String str) {
        return (AlarmBuilder) put("identifier", str);
    }

    public final AlarmBuilder setDayOfWeek(String... strArr) {
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            if (SUNDAY.equals(str) || MONDAY.equals(str) || TUESDAY.equals(str) || WEDNESDAY.equals(str) || THURSDAY.equals(str) || FRIDAY.equals(str) || SATURDAY.equals(str)) {
                i++;
            } else {
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid weekday ".concat(valueOf) : new String("Invalid weekday "));
            }
        }
        return (AlarmBuilder) put("dayOfWeek", strArr);
    }

    public final AlarmBuilder setAlarmInstances(AlarmInstanceBuilder... alarmInstanceBuilderArr) {
        return (AlarmBuilder) put("alarmInstances", (S[]) alarmInstanceBuilderArr);
    }
}
