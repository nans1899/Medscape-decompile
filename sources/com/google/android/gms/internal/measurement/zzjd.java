package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzjd implements zzje {
    zzjd() {
    }

    public final Map<?, ?> zza(Object obj) {
        return (zzjb) obj;
    }

    public final zzjc<?, ?> zzb(Object obj) {
        zziz zziz = (zziz) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzc(Object obj) {
        return (zzjb) obj;
    }

    public final boolean zzd(Object obj) {
        return !((zzjb) obj).zzd();
    }

    public final Object zze(Object obj) {
        ((zzjb) obj).zzc();
        return obj;
    }

    public final Object zzf(Object obj) {
        return zzjb.zza().zzb();
    }

    public final Object zza(Object obj, Object obj2) {
        zzjb zzjb = (zzjb) obj;
        zzjb zzjb2 = (zzjb) obj2;
        if (!zzjb2.isEmpty()) {
            if (!zzjb.zzd()) {
                zzjb = zzjb.zzb();
            }
            zzjb.zza(zzjb2);
        }
        return zzjb;
    }

    public final int zza(int i, Object obj, Object obj2) {
        zzjb zzjb = (zzjb) obj;
        zziz zziz = (zziz) obj2;
        if (zzjb.isEmpty()) {
            return 0;
        }
        Iterator it = zzjb.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
