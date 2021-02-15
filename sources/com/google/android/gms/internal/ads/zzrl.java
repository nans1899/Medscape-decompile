package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzrl implements ValueCallback<String> {
    private final /* synthetic */ zzrm zzbtr;

    zzrl(zzrm zzrm) {
        this.zzbtr = zzrm;
    }

    public final /* synthetic */ void onReceiveValue(Object obj) {
        this.zzbtr.zzbte.zza(this.zzbtr.zzbtt, this.zzbtr.zzbtu, (String) obj, this.zzbtr.zzbtv);
    }
}
