package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzelc implements zzelk {
    private zzelk[] zzilg;

    zzelc(zzelk... zzelkArr) {
        this.zzilg = zzelkArr;
    }

    public final boolean zze(Class<?> cls) {
        for (zzelk zze : this.zzilg) {
            if (zze.zze(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzelh zzf(Class<?> cls) {
        for (zzelk zzelk : this.zzilg) {
            if (zzelk.zze(cls)) {
                return zzelk.zzf(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
