package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbtk implements zzbuy, zzbvs {
    private final zzarj zzbom;
    private final zzdnv zzeot;
    private final Context zzvr;

    public zzbtk(Context context, zzdnv zzdnv, zzarj zzarj) {
        this.zzvr = context;
        this.zzeot = zzdnv;
        this.zzbom = zzarj;
    }

    public final void zzca(Context context) {
    }

    public final void zzcb(Context context) {
    }

    public final void onAdLoaded() {
        if (this.zzeot.zzhef != null && this.zzeot.zzhef.zzdpy) {
            ArrayList arrayList = new ArrayList();
            if (!this.zzeot.zzhef.zzdpz.isEmpty()) {
                arrayList.add(this.zzeot.zzhef.zzdpz);
            }
            this.zzbom.zza(this.zzvr, arrayList);
        }
    }

    public final void zzcc(Context context) {
        this.zzbom.detach();
    }
}
