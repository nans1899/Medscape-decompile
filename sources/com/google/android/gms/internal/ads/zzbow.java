package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbow implements zzbvs {
    private final Context zzcyt;
    private final zzbbx zzfqm;
    private final zzdnv zzfqn;
    private final zzdok zzfqo;

    zzbow(Context context, zzbbx zzbbx, zzdnv zzdnv, zzdok zzdok) {
        this.zzcyt = context;
        this.zzfqm = zzbbx;
        this.zzfqn = zzdnv;
        this.zzfqo = zzdok;
    }

    public final void onAdLoaded() {
        zzp.zzlb().zzb(this.zzcyt, this.zzfqm.zzbre, this.zzfqn.zzhdw.toString(), this.zzfqo.zzhfb);
    }
}
