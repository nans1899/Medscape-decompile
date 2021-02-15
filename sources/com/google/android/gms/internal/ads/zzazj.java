package com.google.android.gms.internal.ads;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.zzp;
import java.io.InputStream;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzazj extends zzazg {
    public final int zzxz() {
        return 16974374;
    }

    public final CookieManager zzbh(Context context) {
        if (zzxy()) {
            return null;
        }
        try {
            return CookieManager.getInstance();
        } catch (Throwable th) {
            zzayp.zzc("Failed to obtain CookieManager.", th);
            zzp.zzkv().zza(th, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public final zzbgi zza(zzbgj zzbgj, zzts zzts, boolean z) {
        return new zzbhm(zzbgj, zzts, z);
    }

    public final WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, i, str3, map, inputStream);
    }
}
