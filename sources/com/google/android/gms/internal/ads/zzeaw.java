package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzear;
import java.security.GeneralSecurityException;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeaw implements zzear.zza {
    private final /* synthetic */ zzeas zzhvc;
    private final /* synthetic */ zzeag zzhvd;

    zzeaw(zzeas zzeas, zzeag zzeag) {
        this.zzhvc = zzeas;
        this.zzhvd = zzeag;
    }

    public final <Q> zzeab<Q> zzb(Class<Q> cls) throws GeneralSecurityException {
        try {
            return new zzeap(this.zzhvc, this.zzhvd, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    public final zzeab<?> zzayy() {
        zzeas zzeas = this.zzhvc;
        return new zzeap(zzeas, this.zzhvd, zzeas.zzayq());
    }

    public final Class<?> zzayz() {
        return this.zzhvc.getClass();
    }

    public final Set<Class<?>> zzayp() {
        return this.zzhvc.zzayp();
    }

    public final Class<?> zzaza() {
        return this.zzhvd.getClass();
    }
}
