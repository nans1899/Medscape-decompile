package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzjc implements zzjk {
    private zzjk[] zzaab;

    zzjc(zzjk... zzjkArr) {
        this.zzaab = zzjkArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzjk zza : this.zzaab) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzjl zzb(Class<?> cls) {
        for (zzjk zzjk : this.zzaab) {
            if (zzjk.zza(cls)) {
                return zzjk.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
