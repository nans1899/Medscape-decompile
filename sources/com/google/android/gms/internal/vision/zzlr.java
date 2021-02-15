package com.google.android.gms.internal.vision;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public enum zzlr {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzgs.zztt),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzzk;

    private zzlr(Object obj) {
        this.zzzk = obj;
    }
}
