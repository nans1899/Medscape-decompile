package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeky extends zzekt {
    private zzeky() {
        super();
    }

    /* access modifiers changed from: package-private */
    public final <L> List<L> zza(Object obj, long j) {
        zzekk zzd = zzd(obj, j);
        if (zzd.zzbeb()) {
            return zzd;
        }
        int size = zzd.size();
        zzekk zzft = zzd.zzft(size == 0 ? 10 : size << 1);
        zzend.zza(obj, j, (Object) zzft);
        return zzft;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Object obj, long j) {
        zzd(obj, j).zzbec();
    }

    /* access modifiers changed from: package-private */
    public final <E> void zza(Object obj, Object obj2, long j) {
        zzekk zzd = zzd(obj, j);
        zzekk zzd2 = zzd(obj2, j);
        int size = zzd.size();
        int size2 = zzd2.size();
        if (size > 0 && size2 > 0) {
            if (!zzd.zzbeb()) {
                zzd = zzd.zzft(size2 + size);
            }
            zzd.addAll(zzd2);
        }
        if (size > 0) {
            zzd2 = zzd;
        }
        zzend.zza(obj, j, (Object) zzd2);
    }

    private static <E> zzekk<E> zzd(Object obj, long j) {
        return (zzekk) zzend.zzp(obj, j);
    }
}
