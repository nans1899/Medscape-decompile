package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
final class zzgt implements Comparator<zzgr> {
    zzgt() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzgr zzgr = (zzgr) obj;
        zzgr zzgr2 = (zzgr) obj2;
        zzgw zzgw = (zzgw) zzgr.iterator();
        zzgw zzgw2 = (zzgw) zzgr2.iterator();
        while (zzgw.hasNext() && zzgw2.hasNext()) {
            int compare = Integer.compare(zzgr.zzb(zzgw.zza()), zzgr.zzb(zzgw2.zza()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzgr.zza(), zzgr2.zza());
    }
}
