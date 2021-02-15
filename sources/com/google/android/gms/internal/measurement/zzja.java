package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzja implements zzji {
    private zzji[] zza;

    zzja(zzji... zzjiArr) {
        this.zza = zzjiArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzji zza2 : this.zza) {
            if (zza2.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzjf zzb(Class<?> cls) {
        for (zzji zzji : this.zza) {
            if (zzji.zza(cls)) {
                return zzji.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
