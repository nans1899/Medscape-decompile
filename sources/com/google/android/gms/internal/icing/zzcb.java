package com.google.android.gms.internal.icing;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzcb {
    public static <T> zzcc<T> zza(zzcc<T> zzcc) {
        if ((zzcc instanceof zzcd) || (zzcc instanceof zzce)) {
            return zzcc;
        }
        if (zzcc instanceof Serializable) {
            return new zzce(zzcc);
        }
        return new zzcd(zzcc);
    }

    public static <T> zzcc<T> zzc(@NullableDecl T t) {
        return new zzcg(t);
    }
}
