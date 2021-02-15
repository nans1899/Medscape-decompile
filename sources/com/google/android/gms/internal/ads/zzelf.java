package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzelf implements zzelg {
    zzelf() {
    }

    public final Map<?, ?> zzan(Object obj) {
        return (zzeld) obj;
    }

    public final zzele<?, ?> zzao(Object obj) {
        zzelb zzelb = (zzelb) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzap(Object obj) {
        return (zzeld) obj;
    }

    public final boolean zzaq(Object obj) {
        return !((zzeld) obj).isMutable();
    }

    public final Object zzar(Object obj) {
        ((zzeld) obj).zzbec();
        return obj;
    }

    public final Object zzas(Object obj) {
        return zzeld.zzbhp().zzbhq();
    }

    public final Object zzf(Object obj, Object obj2) {
        zzeld zzeld = (zzeld) obj;
        zzeld zzeld2 = (zzeld) obj2;
        if (!zzeld2.isEmpty()) {
            if (!zzeld.isMutable()) {
                zzeld = zzeld.zzbhq();
            }
            zzeld.zza(zzeld2);
        }
        return zzeld;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzeld zzeld = (zzeld) obj;
        zzelb zzelb = (zzelb) obj2;
        if (zzeld.isEmpty()) {
            return 0;
        }
        Iterator it = zzeld.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
