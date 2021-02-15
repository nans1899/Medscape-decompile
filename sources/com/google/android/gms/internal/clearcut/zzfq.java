package com.google.android.gms.internal.clearcut;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public enum zzfq {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzbb.zzfi),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzlj;

    private zzfq(Object obj) {
        this.zzlj = obj;
    }
}
