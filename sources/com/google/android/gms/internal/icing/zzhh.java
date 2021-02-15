package com.google.android.gms.internal.icing;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public enum zzhh {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzct.zzgi),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzlq;

    private zzhh(Object obj) {
        this.zzlq = obj;
    }
}
