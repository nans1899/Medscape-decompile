package com.google.android.gms.measurement.internal;

import com.facebook.AccessToken;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public class zzgu {
    public static final String[] zza = {"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", AccessToken.USER_ID_KEY, "first_open_after_install", "lifetime_user_engagement", "session_user_engagement", "non_personalized_ads", "ga_session_number", "ga_session_id", "last_gclid", "session_number", "session_id"};
    public static final String[] zzb = {"_ln", "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte", "_se", "_npa", "_sno", "_sid", "_lgclid", "_sno", "_sid"};

    protected zzgu() {
    }

    public static String zza(String str) {
        return zzih.zza(str, zza, zzb);
    }
}
