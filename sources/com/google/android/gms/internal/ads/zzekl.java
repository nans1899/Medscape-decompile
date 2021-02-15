package com.google.android.gms.internal.ads;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public enum zzekl {
    VOID(Void.class, Void.class, (Class<?>) null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzeip.class, zzeip.class, zzeip.zzier),
    ENUM(Integer.TYPE, Integer.class, (Class<?>) null),
    MESSAGE(Object.class, Object.class, (Class<?>) null);
    
    private final Class<?> zzikn;
    private final Class<?> zziko;
    private final Object zzikp;

    private zzekl(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzikn = cls;
        this.zziko = cls2;
        this.zzikp = obj;
    }

    public final Class<?> zzbhg() {
        return this.zziko;
    }
}
