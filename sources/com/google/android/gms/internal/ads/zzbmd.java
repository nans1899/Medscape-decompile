package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzp;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmd implements zzbma {
    private final Context zzvr;

    public zzbmd(Context context) {
        this.zzvr = context;
    }

    public final void zzl(Map<String, String> map) {
        CookieManager zzbh;
        String str = map.get("cookie");
        if (!TextUtils.isEmpty(str) && (zzbh = zzp.zzkt().zzbh(this.zzvr)) != null) {
            zzbh.setCookie((String) zzwm.zzpx().zzd(zzabb.zzcoi), str);
        }
    }
}
