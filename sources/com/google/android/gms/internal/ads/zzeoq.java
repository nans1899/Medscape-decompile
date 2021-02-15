package com.google.android.gms.internal.ads;

import org.objenesis.strategy.PlatformDescription;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzeoq {
    public abstract void zzik(String str);

    public static zzeoq zzn(Class cls) {
        if (System.getProperty("java.vm.name").equalsIgnoreCase(PlatformDescription.DALVIK)) {
            return new zzeoj(cls.getSimpleName());
        }
        return new zzeol(cls.getSimpleName());
    }
}
