package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.zzp;
import java.io.File;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbhn extends zzbgi {
    public zzbhn(zzbgj zzbgj, zzts zzts, boolean z) {
        super(zzbgj, zzts, z);
    }

    /* access modifiers changed from: protected */
    public final WebResourceResponse zza(WebView webView, String str, Map<String, String> map) {
        String str2;
        if (!(webView instanceof zzbgj)) {
            zzayp.zzfe("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return null;
        }
        zzbgj zzbgj = (zzbgj) webView;
        if (this.zzenb != null) {
            this.zzenb.zza(str, map, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
            if (map == null) {
                map = Collections.emptyMap();
            }
            return super.zzd(str, map);
        }
        if (zzbgj.zzabw() != null) {
            zzbgj.zzabw().zzuu();
        }
        if (zzbgj.zzabu().zzadb()) {
            str2 = (String) zzwm.zzpx().zzd(zzabb.zzcmz);
        } else if (zzbgj.zzacb()) {
            str2 = (String) zzwm.zzpx().zzd(zzabb.zzcmy);
        } else {
            str2 = (String) zzwm.zzpx().zzd(zzabb.zzcmx);
        }
        zzp.zzkr();
        return zzayu.zzd(zzbgj.getContext(), zzbgj.zzzx().zzbre, str2);
    }
}
