package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcoc implements Runnable {
    private final zzcnu zzgic;
    private final zzbcg zzgim;

    zzcoc(zzcnu zzcnu, zzbcg zzbcg) {
        this.zzgic = zzcnu;
        this.zzgim = zzbcg;
    }

    public final void run() {
        zzbcg zzbcg = this.zzgim;
        String zzwg = zzp.zzkv().zzwt().zzxk().zzwg();
        if (!TextUtils.isEmpty(zzwg)) {
            zzbcg.set(zzwg);
        } else {
            zzbcg.setException(new Exception());
        }
    }
}
