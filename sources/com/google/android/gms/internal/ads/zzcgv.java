package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcgv implements zzdvu {
    static final zzdvu zzdwz = new zzcgv();

    private zzcgv() {
    }

    public final Object apply(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (zzadq zzadq : (List) obj) {
            if (zzadq != null) {
                arrayList.add(zzadq);
            }
        }
        return arrayList;
    }
}
