package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzear;
import java.security.GeneralSecurityException;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeat implements zzear.zza {
    private final /* synthetic */ zzeag zzhuz;

    zzeat(zzeag zzeag) {
        this.zzhuz = zzeag;
    }

    public final Class<?> zzaza() {
        return null;
    }

    public final <Q> zzeab<Q> zzb(Class<Q> cls) throws GeneralSecurityException {
        try {
            return new zzeae(this.zzhuz, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzeab<?> zzayy() {
        zzeag zzeag = this.zzhuz;
        return new zzeae(zzeag, zzeag.zzayq());
    }

    public final Class<?> zzayz() {
        return this.zzhuz.getClass();
    }

    public final Set<Class<?>> zzayp() {
        return this.zzhuz.zzayp();
    }
}
