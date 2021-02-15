package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzes extends zzer {
    private zzes() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final void zza(Object obj, long j) {
        zzb(obj, j).zzai();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzee zzb = zzb(obj, j);
        zzee zzb2 = zzb(obj2, j);
        int size = zzb.size();
        int size2 = zzb2.size();
        if (size > 0 && size2 > 0) {
            if (!zzb.zzah()) {
                zzb = zzb.zzj(size2 + size);
            }
            zzb.addAll(zzb2);
        }
        if (size > 0) {
            zzb2 = zzb;
        }
        zzgs.zza(obj, j, (Object) zzb2);
    }

    private static <E> zzee<E> zzb(Object obj, long j) {
        return (zzee) zzgs.zzo(obj, j);
    }
}
