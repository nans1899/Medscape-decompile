package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcaz implements zzsu {
    private zzdvp zzfwp;

    public final void zza(Activity activity, WebView webView) {
        try {
            this.zzfwp = new zzdvp(activity, webView);
        } catch (RuntimeException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
            sb.append(" Failed to initialize the internal ArWebView: ");
            sb.append(valueOf);
            zzayp.zzfc(sb.toString());
        }
    }

    public final void zzc(String str, String str2) {
        if (this.zzfwp == null) {
            zzayp.zzfc("ArWebView is not initialized.");
        } else {
            zzdvp.getWebView().loadDataWithBaseURL(str, str2, "text/html", "UTF-8", (String) null);
        }
    }

    public final WebView getWebView() {
        if (this.zzfwp == null) {
            return null;
        }
        return zzdvp.getWebView();
    }

    public final View getView() {
        return this.zzfwp;
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (this.zzfwp != null) {
            zzdvp.onPageStarted(webView, str, bitmap);
        }
    }
}
