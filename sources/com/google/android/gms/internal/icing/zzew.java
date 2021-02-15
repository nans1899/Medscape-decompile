package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzew implements zzfe {
    private zzfe[] zzmh;

    zzew(zzfe... zzfeArr) {
        this.zzmh = zzfeArr;
    }

    public final boolean zzb(Class<?> cls) {
        for (zzfe zzb : this.zzmh) {
            if (zzb.zzb(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzff zzc(Class<?> cls) {
        for (zzfe zzfe : this.zzmh) {
            if (zzfe.zzb(cls)) {
                return zzfe.zzc(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
