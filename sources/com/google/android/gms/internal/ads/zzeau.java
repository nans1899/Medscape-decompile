package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzear;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeau implements zzear.zza {
    private final /* synthetic */ zzeab zzhva;

    zzeau(zzeab zzeab) {
        this.zzhva = zzeab;
    }

    public final Class<?> zzaza() {
        return null;
    }

    public final <Q> zzeab<Q> zzb(Class<Q> cls) throws GeneralSecurityException {
        if (this.zzhva.zzayk().equals(cls)) {
            return this.zzhva;
        }
        throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
    }

    public final zzeab<?> zzayy() {
        return this.zzhva;
    }

    public final Class<?> zzayz() {
        return this.zzhva.getClass();
    }

    public final Set<Class<?>> zzayp() {
        return Collections.singleton(this.zzhva.zzayk());
    }
}
