package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.AdSize;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdoq {
    public static zzdnu zza(List<zzdnu> list, zzdnu zzdnu) {
        return list.get(0);
    }

    public static zzvn zzb(Context context, List<zzdnu> list) {
        ArrayList arrayList = new ArrayList();
        for (zzdnu next : list) {
            if (next.zzhdh) {
                arrayList.add(AdSize.FLUID);
            } else {
                arrayList.add(new AdSize(next.width, next.height));
            }
        }
        return new zzvn(context, (AdSize[]) arrayList.toArray(new AdSize[arrayList.size()]));
    }

    public static zzdnu zzf(zzvn zzvn) {
        if (zzvn.zzchv) {
            return new zzdnu(-3, 0, true);
        }
        return new zzdnu(zzvn.width, zzvn.height, false);
    }
}
