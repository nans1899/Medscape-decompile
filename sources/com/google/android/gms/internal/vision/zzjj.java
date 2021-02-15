package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzjj implements zzjg {
    zzjj() {
    }

    public final Map<?, ?> zzn(Object obj) {
        return (zzjh) obj;
    }

    public final zzje<?, ?> zzs(Object obj) {
        zzjf zzjf = (zzjf) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzo(Object obj) {
        return (zzjh) obj;
    }

    public final boolean zzp(Object obj) {
        return !((zzjh) obj).isMutable();
    }

    public final Object zzq(Object obj) {
        ((zzjh) obj).zzej();
        return obj;
    }

    public final Object zzr(Object obj) {
        return zzjh.zzhx().zzhy();
    }

    public final Object zzc(Object obj, Object obj2) {
        zzjh zzjh = (zzjh) obj;
        zzjh zzjh2 = (zzjh) obj2;
        if (!zzjh2.isEmpty()) {
            if (!zzjh.isMutable()) {
                zzjh = zzjh.zzhy();
            }
            zzjh.zza(zzjh2);
        }
        return zzjh;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzjh zzjh = (zzjh) obj;
        zzjf zzjf = (zzjf) obj2;
        if (zzjh.isEmpty()) {
            return 0;
        }
        Iterator it = zzjh.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
