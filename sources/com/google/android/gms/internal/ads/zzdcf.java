package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdcf implements zzdgx<zzdgu<Bundle>> {
    private final Set<String> zzgvm;

    zzdcf(Set<String> set) {
        this.zzgvm = set;
    }

    public final zzdzc<zzdgu<Bundle>> zzarj() {
        ArrayList arrayList = new ArrayList();
        for (String add : this.zzgvm) {
            arrayList.add(add);
        }
        return zzdyq.zzaf(new zzdce(arrayList));
    }
}
