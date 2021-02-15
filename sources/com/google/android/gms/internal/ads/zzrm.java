package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzrm implements Runnable {
    final /* synthetic */ zzrk zzbte;
    private ValueCallback<String> zzbts = new zzrl(this);
    final /* synthetic */ zzre zzbtt;
    final /* synthetic */ WebView zzbtu;
    final /* synthetic */ boolean zzbtv;

    zzrm(zzrk zzrk, zzre zzre, WebView webView, boolean z) {
        this.zzbte = zzrk;
        this.zzbtt = zzre;
        this.zzbtu = webView;
        this.zzbtv = z;
    }

    public final void run() {
        if (this.zzbtu.getSettings().getJavaScriptEnabled()) {
            try {
                this.zzbtu.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzbts);
            } catch (Throwable unused) {
                this.zzbts.onReceiveValue("");
            }
        }
    }
}
