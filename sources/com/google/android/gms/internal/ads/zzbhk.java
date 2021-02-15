package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbhk extends zzbhn {
    public zzbhk(zzbgj zzbgj, zzts zzts, boolean z) {
        super(zzbgj, zzts, z);
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, (Map<String, String>) null);
    }
}
