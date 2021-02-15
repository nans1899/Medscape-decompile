package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeir implements Comparator<zzeip> {
    zzeir() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzeip zzeip = (zzeip) obj;
        zzeip zzeip2 = (zzeip) obj2;
        zzeiu zzeiu = (zzeiu) zzeip.iterator();
        zzeiu zzeiu2 = (zzeiu) zzeip2.iterator();
        while (zzeiu.hasNext() && zzeiu2.hasNext()) {
            int compare = Integer.compare(zzeip.zzb(zzeiu.nextByte()), zzeip.zzb(zzeiu2.nextByte()));
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzeip.size(), zzeip2.size());
    }
}
