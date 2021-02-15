package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdce implements zzdgu {
    private final ArrayList zzgvl;

    zzdce(ArrayList arrayList) {
        this.zzgvl = arrayList;
    }

    public final void zzs(Object obj) {
        ((Bundle) obj).putStringArrayList("ad_types", this.zzgvl);
    }
}
