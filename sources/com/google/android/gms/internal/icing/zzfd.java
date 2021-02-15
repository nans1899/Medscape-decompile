package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfd implements zzfa {
    zzfd() {
    }

    public final zzey<?, ?> zzk(Object obj) {
        zzez zzez = (zzez) obj;
        throw new NoSuchMethodError();
    }

    public final Map<?, ?> zzi(Object obj) {
        return (zzfb) obj;
    }

    public final Object zzj(Object obj) {
        ((zzfb) obj).zzai();
        return obj;
    }

    public final Object zzb(Object obj, Object obj2) {
        zzfb zzfb = (zzfb) obj;
        zzfb zzfb2 = (zzfb) obj2;
        if (!zzfb2.isEmpty()) {
            if (!zzfb.isMutable()) {
                zzfb = zzfb.zzcj();
            }
            zzfb.zza(zzfb2);
        }
        return zzfb;
    }

    public final int zzb(int i, Object obj, Object obj2) {
        zzfb zzfb = (zzfb) obj;
        zzez zzez = (zzez) obj2;
        if (zzfb.isEmpty()) {
            return 0;
        }
        Iterator it = zzfb.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
