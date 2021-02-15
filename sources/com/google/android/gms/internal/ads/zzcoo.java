package com.google.android.gms.internal.ads;

import android.webkit.CookieManager;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcoo implements Callable {
    private final CookieManager zzgjk;

    zzcoo(CookieManager cookieManager) {
        this.zzgjk = cookieManager;
    }

    public final Object call() {
        CookieManager cookieManager = this.zzgjk;
        if (cookieManager == null) {
            return "";
        }
        return cookieManager.getCookie((String) zzwm.zzpx().zzd(zzabb.zzcoi));
    }
}
