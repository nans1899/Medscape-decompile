package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzabg {
    static List<String> zzrm() {
        ArrayList arrayList = new ArrayList();
        zza(arrayList, zzaci.zzi("gad:dynamite_module:experiment_id", ""));
        zza(arrayList, zzacu.zzdbf);
        zza(arrayList, zzacu.zzdbg);
        zza(arrayList, zzacu.zzdbh);
        zza(arrayList, zzacu.zzdbi);
        zza(arrayList, zzacu.zzdbj);
        zza(arrayList, zzacu.zzdbp);
        zza(arrayList, zzacu.zzdbk);
        zza(arrayList, zzacu.zzdbl);
        zza(arrayList, zzacu.zzdbm);
        zza(arrayList, zzacu.zzdbn);
        zza(arrayList, zzacu.zzdbo);
        return arrayList;
    }

    static List<String> zzrn() {
        ArrayList arrayList = new ArrayList();
        zza(arrayList, zzadd.zzdck);
        return arrayList;
    }

    private static void zza(List<String> list, zzaci<String> zzaci) {
        String str = zzaci.get();
        if (!TextUtils.isEmpty(str)) {
            list.add(str);
        }
    }
}
