package com.google.android.gms.internal.ads;

import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdcq implements zzdvu {
    static final zzdvu zzdwz = new zzdcq();

    private zzdcq() {
    }

    public final Object apply(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        if (arrayList.isEmpty()) {
            return null;
        }
        return new zzdct(arrayList);
    }
}
