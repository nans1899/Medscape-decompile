package com.google.android.gms.internal.vision;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public enum zzip {
    VOID(Void.class, Void.class, (Class<?>) null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzgs.class, zzgs.class, zzgs.zztt),
    ENUM(Integer.TYPE, Integer.class, (Class<?>) null),
    MESSAGE(Object.class, Object.class, (Class<?>) null);
    
    private final Class<?> zzzi;
    private final Class<?> zzzj;
    private final Object zzzk;

    private zzip(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzzi = cls;
        this.zzzj = cls2;
        this.zzzk = obj;
    }

    public final Class<?> zzhq() {
        return this.zzzj;
    }
}
