package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfn implements zzahq {
    private final zzcfm zzgbw;

    zzcfn(zzcfm zzcfm) {
        this.zzgbw = zzcfm;
    }

    public final void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        zzbgj.zzabw().zza((zzbhu) new zzcfs(this.zzgbw, map));
        String str = (String) map.get("overlayHtml");
        String str2 = (String) map.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
            zzbgj.loadData(str, "text/html", "UTF-8");
        } else {
            zzbgj.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", (String) null);
        }
    }
}
