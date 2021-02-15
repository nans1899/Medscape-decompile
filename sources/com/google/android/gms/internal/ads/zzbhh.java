package com.google.android.gms.internal.ads;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbhh implements zzbhj {
    private final zzbgj zzeqk;

    zzbhh(zzbgj zzbgj) {
        this.zzeqk = zzbgj;
    }

    public final void zzh(Uri uri) {
        zzbhv zzabw = this.zzeqk.zzabw();
        if (zzabw == null) {
            zzayp.zzfc("Unable to pass GMSG, no AdWebViewClient for AdWebView!");
        } else {
            zzabw.zzh(uri);
        }
    }
}
