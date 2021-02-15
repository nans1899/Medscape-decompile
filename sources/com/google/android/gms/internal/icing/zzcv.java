package com.google.android.gms.internal.icing;

import java.util.Comparator;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzcv implements Comparator<zzct> {
    zzcv() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzct zzct = (zzct) obj;
        zzct zzct2 = (zzct) obj2;
        zzdc zzdc = (zzdc) zzct.iterator();
        zzdc zzdc2 = (zzdc) zzct2.iterator();
        while (zzdc.hasNext() && zzdc2.hasNext()) {
            int compare = Integer.compare(zzct.zza(zzdc.nextByte()), zzct.zza(zzdc2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzct.size(), zzct2.size());
    }
}
