package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzadj {
    private static final AtomicReference<zzadk> zzdcw = new AtomicReference<>();
    static final AtomicBoolean zzdcx = new AtomicBoolean();

    static zzadk zzsa() {
        return zzdcw.get();
    }

    public static void zza(zzadk zzadk) {
        zzdcw.set(zzadk);
    }
}
