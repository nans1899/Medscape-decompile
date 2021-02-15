package com.google.android.gms.internal.icing;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public enum zzej {
    VOID(Void.class, Void.class, (Class<?>) null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzct.class, zzct.class, zzct.zzgi),
    ENUM(Integer.TYPE, Integer.class, (Class<?>) null),
    MESSAGE(Object.class, Object.class, (Class<?>) null);
    
    private final Class<?> zzlo;
    private final Class<?> zzlp;
    private final Object zzlq;

    private zzej(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzlo = cls;
        this.zzlp = cls2;
        this.zzlq = obj;
    }

    public final Class<?> zzcb() {
        return this.zzlp;
    }
}
