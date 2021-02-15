package com.google.android.gms.internal.vision;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzgu implements Comparator<zzgs> {
    zzgu() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgs zzgs = (zzgs) obj;
        zzgs zzgs2 = (zzgs) obj2;
        zzhb zzhb = (zzhb) zzgs.iterator();
        zzhb zzhb2 = (zzhb) zzgs2.iterator();
        while (zzhb.hasNext() && zzhb2.hasNext()) {
            int compare = Integer.compare(zzgs.zza(zzhb.nextByte()), zzgs.zza(zzhb2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzgs.size(), zzgs2.size());
    }
}
