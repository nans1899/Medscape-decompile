package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbio {
    private static Boolean zzeqy;

    private zzbio() {
    }

    private static boolean zzb(WebView webView) {
        boolean booleanValue;
        synchronized (zzbio.class) {
            if (zzeqy == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", (ValueCallback) null);
                    zzeqy = true;
                } catch (IllegalStateException unused) {
                    zzeqy = false;
                }
            }
            booleanValue = zzeqy.booleanValue();
        }
        return booleanValue;
    }

    static void zza(WebView webView, String str) {
        if (!PlatformVersion.isAtLeastKitKat() || !zzb(webView)) {
            String valueOf = String.valueOf(str);
            webView.loadUrl(valueOf.length() != 0 ? InAppMessageWebViewClient.JAVASCRIPT_PREFIX.concat(valueOf) : new String(InAppMessageWebViewClient.JAVASCRIPT_PREFIX));
            return;
        }
        webView.evaluateJavascript(str, (ValueCallback) null);
    }
}
