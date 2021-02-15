package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbcf implements zzdyr<Object> {
    private final /* synthetic */ String zzeey;

    zzbcf(String str) {
        this.zzeey = str;
    }

    public final void onSuccess(Object obj) {
    }

    public final void zzb(Throwable th) {
        zzp.zzkv().zza(th, this.zzeey);
    }
}
