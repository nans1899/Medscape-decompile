package com.google.android.gms.internal.ads;

import android.webkit.JavascriptInterface;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzajx {
    private final zzake zzdgp;

    private zzajx(zzake zzake) {
        this.zzdgp = zzake;
    }

    @JavascriptInterface
    public final void notify(String str) {
        this.zzdgp.zzdg(str);
    }
}
