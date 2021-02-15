package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdct implements zzdgu {
    private final ArrayList zzgvl;

    zzdct(ArrayList arrayList) {
        this.zzgvl = arrayList;
    }

    public final void zzs(Object obj) {
        ((Bundle) obj).putStringArrayList("android_permissions", this.zzgvl);
    }
}
