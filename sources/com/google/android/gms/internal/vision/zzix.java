package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
abstract class zzix {
    private static final zzix zzzu = new zziz();
    private static final zzix zzzv = new zziy();

    private zzix() {
    }

    /* access modifiers changed from: package-private */
    public abstract <L> List<L> zza(Object obj, long j);

    /* access modifiers changed from: package-private */
    public abstract <L> void zza(Object obj, Object obj2, long j);

    /* access modifiers changed from: package-private */
    public abstract void zzb(Object obj, long j);

    static zzix zzhu() {
        return zzzu;
    }

    static zzix zzhv() {
        return zzzv;
    }
}
