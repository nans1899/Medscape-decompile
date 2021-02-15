package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdnr extends AdMetadataListener {
    private final /* synthetic */ zzdnp zzhda;
    private final /* synthetic */ zzxj zzhdb;

    zzdnr(zzdnp zzdnp, zzxj zzxj) {
        this.zzhda = zzdnp;
        this.zzhdb = zzxj;
    }

    public final void onAdMetadataChanged() {
        if (this.zzhda.zzhcz != null) {
            try {
                this.zzhdb.onAdMetadataChanged();
            } catch (RemoteException e) {
                zzayp.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
