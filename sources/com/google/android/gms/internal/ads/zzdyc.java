package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdyc<V> extends zzdyd<V, List<V>> {
    zzdyc(zzdwk<? extends zzdzc<? extends V>> zzdwk, boolean z) {
        super(zzdwk, true);
        zzaxy();
    }

    public final /* synthetic */ Object zzk(List list) {
        ArrayList zzep = zzdwx.zzep(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzdyf zzdyf = (zzdyf) it.next();
            zzep.add(zzdyf != null ? zzdyf.value : null);
        }
        return Collections.unmodifiableList(zzep);
    }
}
