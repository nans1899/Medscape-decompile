package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcul implements zzccm {
    private final zzbcg zzbvu;

    zzcul(zzbcg zzbcg) {
        this.zzbvu = zzbcg;
    }

    public final void zza(boolean z, Context context) {
        zzbcg zzbcg = this.zzbvu;
        try {
            zzp.zzkq();
            zzl.zza(context, (AdOverlayInfoParcel) zzbcg.get(), true);
        } catch (Exception unused) {
        }
    }
}
