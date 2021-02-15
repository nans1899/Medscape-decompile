package com.google.android.gms.internal.vision;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zziy extends zzix {
    private zziy() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        zzik zzc = zzc(obj, j);
        if (zzc.zzei()) {
            return zzc;
        }
        int size = zzc.size();
        zzik zzan = zzc.zzan(size == 0 ? 10 : size << 1);
        zzld.zza(obj, j, (Object) zzan);
        return zzan;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        zzc(obj, j).zzej();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzik zzc = zzc(obj, j);
        zzik zzc2 = zzc(obj2, j);
        int size = zzc.size();
        int size2 = zzc2.size();
        if (size > 0 && size2 > 0) {
            if (!zzc.zzei()) {
                zzc = zzc.zzan(size2 + size);
            }
            zzc.addAll(zzc2);
        }
        if (size > 0) {
            zzc2 = zzc;
        }
        zzld.zza(obj, j, (Object) zzc2);
    }

    private static <E> zzik<E> zzc(Object obj, long j) {
        return (zzik) zzld.zzp(obj, j);
    }
}
