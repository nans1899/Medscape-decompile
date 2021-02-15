package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgp implements zzdyr<Map<String, String>> {
    private final /* synthetic */ zzbgi zzeni;
    private final /* synthetic */ List zzenl;
    private final /* synthetic */ String zzenm;

    zzbgp(zzbgi zzbgi, List list, String str) {
        this.zzeni = zzbgi;
        this.zzenl = list;
        this.zzenm = str;
    }

    public final void zzb(Throwable th) {
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zzeni.zza((Map<String, String>) (Map) obj, (List<zzahq<? super zzbgj>>) this.zzenl, this.zzenm);
    }
}
