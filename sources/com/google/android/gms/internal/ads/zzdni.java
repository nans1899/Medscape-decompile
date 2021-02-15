package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdni extends AdMetadataListener {
    private final /* synthetic */ zzyh zzhcv;
    private final /* synthetic */ zzdnj zzhcw;

    zzdni(zzdnj zzdnj, zzyh zzyh) {
        this.zzhcw = zzdnj;
        this.zzhcv = zzyh;
    }

    public final void onAdMetadataChanged() {
        if (this.zzhcw.zzhcz != null) {
            try {
                this.zzhcv.onAdMetadataChanged();
            } catch (RemoteException e) {
                zzayp.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
