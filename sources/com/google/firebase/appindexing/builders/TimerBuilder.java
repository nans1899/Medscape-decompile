package com.google.firebase.appindexing.builders;

import com.facebook.share.internal.ShareConstants;
import com.google.firebase.appindexing.internal.zzad;
import java.util.Calendar;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class TimerBuilder extends IndexableBuilder<TimerBuilder> {
    public static final String EXPIRED = "Expired";
    public static final String MISSED = "Missed";
    public static final String PAUSED = "Paused";
    public static final String RESET = "Reset";
    public static final String STARTED = "Started";
    public static final String UNKNOWN = "Unknown";

    TimerBuilder() {
        super("Timer");
    }

    public final TimerBuilder setRingtone(String str) {
        return (TimerBuilder) put("ringtone", str);
    }

    public final TimerBuilder setMessage(String str) {
        return (TimerBuilder) put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, str);
    }

    public final TimerBuilder setLength(long j) {
        return (TimerBuilder) put(Name.LENGTH, j);
    }

    public final TimerBuilder setVibrate(boolean z) {
        return (TimerBuilder) put("vibrate", z);
    }

    public final TimerBuilder setRemainingTime(long j) {
        return (TimerBuilder) put("remainingTime", j);
    }

    public final TimerBuilder setTimerStatus(String str) {
        if ("Started".equals(str) || "Paused".equals(str) || EXPIRED.equals(str) || "Missed".equals(str) || RESET.equals(str) || "Unknown".equals(str)) {
            return (TimerBuilder) put("timerStatus", str);
        }
        String valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid timer status ".concat(valueOf) : new String("Invalid timer status "));
    }

    public final TimerBuilder setIdentifier(String str) {
        return (TimerBuilder) put("identifier", str);
    }

    public final TimerBuilder setExpireTime(Calendar calendar) {
        return (TimerBuilder) put("expireTime", zzad.zza(calendar));
    }
}
