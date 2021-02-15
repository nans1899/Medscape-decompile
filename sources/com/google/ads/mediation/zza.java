package com.google.ads.mediation;

import com.google.android.gms.ads.reward.AdMetadataListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zza extends AdMetadataListener {
    private final /* synthetic */ AbstractAdViewAdapter zzmp;

    zza(AbstractAdViewAdapter abstractAdViewAdapter) {
        this.zzmp = abstractAdViewAdapter;
    }

    public final void onAdMetadataChanged() {
        if (this.zzmp.zzmm != null && this.zzmp.zzmn != null) {
            this.zzmp.zzmn.zzb(this.zzmp.zzmm.getAdMetadata());
        }
    }
}
