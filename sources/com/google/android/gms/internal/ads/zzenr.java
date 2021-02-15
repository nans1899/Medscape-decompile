package com.google.android.gms.internal.ads;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public enum zzenr {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzeip.zzier),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzikp;

    private zzenr(Object obj) {
        this.zzikp = obj;
    }
}
