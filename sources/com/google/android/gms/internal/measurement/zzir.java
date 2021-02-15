package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
abstract class zzir {
    private static final zzir zza = new zzit();
    private static final zzir zzb = new zziw();

    private zzir() {
    }

    /* access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    static zzir zza() {
        return zza;
    }

    static zzir zzb() {
        return zzb;
    }
}
