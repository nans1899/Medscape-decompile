package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzrs implements Comparator<zzry> {
    zzrs(zzrp zzrp) {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzry zzry = (zzry) obj;
        zzry zzry2 = (zzry) obj2;
        int i = zzry.zzbtz - zzry2.zzbtz;
        if (i != 0) {
            return i;
        }
        return (int) (zzry.value - zzry2.value);
    }
}
