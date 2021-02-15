package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.android.gms.internal.ads.zzbhi;
import com.google.android.gms.internal.ads.zzbhq;
import com.google.android.gms.internal.ads.zzbhs;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbhe<WebViewT extends zzbhi & zzbhq & zzbhs> {
    private final zzbhj zzeqh;
    private final WebViewT zzeqi;

    public static zzbhe<zzbgj> zzc(zzbgj zzbgj) {
        return new zzbhe<>(zzbgj, new zzbhh(zzbgj));
    }

    private zzbhe(WebViewT webviewt, zzbhj zzbhj) {
        this.zzeqh = zzbhj;
        this.zzeqi = webviewt;
    }

    @JavascriptInterface
    public final void notify(String str) {
        if (TextUtils.isEmpty(str)) {
            zzayp.zzfe("URL is empty, ignoring message");
        } else {
            zzayu.zzeba.post(new zzbhg(this, str));
        }
    }

    @JavascriptInterface
    public final String getClickSignals(String str) {
        if (TextUtils.isEmpty(str)) {
            zzayp.zzei("Click string is empty, not proceeding.");
            return "";
        }
        zzeg zzabz = ((zzbhq) this.zzeqi).zzabz();
        if (zzabz == null) {
            zzayp.zzei("Signal utils is empty, ignoring.");
            return "";
        }
        zzdw zzca = zzabz.zzca();
        if (zzca == null) {
            zzayp.zzei("Signals object is empty, ignoring.");
            return "";
        } else if (this.zzeqi.getContext() != null) {
            return zzca.zza(this.zzeqi.getContext(), str, ((zzbhs) this.zzeqi).getView(), this.zzeqi.zzzu());
        } else {
            zzayp.zzei("Context is null, ignoring.");
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzfu(String str) {
        this.zzeqh.zzh(Uri.parse(str));
    }
}
