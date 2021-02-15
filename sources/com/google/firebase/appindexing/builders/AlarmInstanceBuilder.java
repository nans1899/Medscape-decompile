package com.google.firebase.appindexing.builders;

import com.google.firebase.appindexing.internal.zzad;
import java.util.Calendar;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class AlarmInstanceBuilder extends IndexableBuilder<AlarmInstanceBuilder> {
    public static final String DISMISSED = "Dismissed";
    public static final String FIRED = "Fired";
    public static final String MISSED = "Missed";
    public static final String SCHEDULED = "Scheduled";
    public static final String SNOOZED = "Snoozed";
    public static final String UNKNOWN = "Unknown";

    AlarmInstanceBuilder() {
        super("AlarmInstance");
    }

    public AlarmInstanceBuilder setScheduledTime(Calendar calendar) {
        return (AlarmInstanceBuilder) put("scheduledTime", zzad.zza(calendar));
    }

    public AlarmInstanceBuilder setAlarmStatus(String str) {
        if (FIRED.equals(str) || SNOOZED.equals(str) || "Missed".equals(str) || DISMISSED.equals(str) || SCHEDULED.equals(str) || "Unknown".equals(str)) {
            return (AlarmInstanceBuilder) put("alarmStatus", str);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid alarm status ".concat(valueOf) : new String("Invalid alarm status "));
    }
}
